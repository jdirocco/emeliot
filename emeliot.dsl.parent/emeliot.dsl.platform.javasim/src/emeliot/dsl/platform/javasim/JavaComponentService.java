package emeliot.dsl.platform.javasim;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import emeliot.dsl.lib.AbstractPlatformService;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

/**
 * Simulation platform that executes an IoT component implemented in Java
 * ({@link SimulatedComponent}).
 * <p>
 * It is contributed to the {@code emeliot.dsl.simulationPlatforms} extension
 * point by this bundle, without any change to the FATE-DSL language bundle.
 * Input time series use the same format as the Proteus platform (one
 * {@code time value} pair per line); output time series are written as CSV
 * files with a {@code "TIME","VALUE"} header, which is the format read by
 * {@link #readOutTSFromFile(String)}.
 */
public class JavaComponentService extends AbstractPlatformService {

	/**
	 * Runs the Java component whose fully qualified class name is given as
	 * {@code project}.
	 */
	@Override
	public void runSimulation(Path project, List<String> inputSeries, List<String> outputSeries) throws Exception {
		SimulatedComponent component = instantiate(project.toString());
		List<TimeSeries> inputs = new ArrayList<>();
		for (String input : inputSeries)
			inputs.add(readInTSFromFile(input));
		List<TimeSeries> outputs = component.simulate(inputs, outputSeries.size());
		if (outputs == null || outputs.size() < outputSeries.size())
			throw new IllegalStateException("Component " + project + " produced fewer outputs than required ("
					+ outputSeries.size() + ")");
		for (int i = 0; i < outputSeries.size(); i++)
			writeOutTSToFile(outputs.get(i), outputSeries.get(i));
		System.out.println("JAVA SIMULATION OF " + project + " COMPLETED.");
	}

	@Override
	public TimeSeries readInTSFromFile(String filePath) throws IOException {
		TimeSeries series = ReadFactory.eINSTANCE.createTimeSeries();
		for (String line : Files.readAllLines(Paths.get(filePath))) {
			if (line.trim().isEmpty())
				continue;
			String[] parts = line.trim().split("\\s+");
			if (parts.length != 2)
				throw new IOException("Invalid line format: " + line);
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(Double.parseDouble(parts[0]));
			tv.setValue(Double.parseDouble(parts[1]));
			series.getTimeValues().add(tv);
		}
		reorderTimeSeries(series);
		return series;
	}

	/** Writes an output time series as CSV, the format expected by {@link #readOutTSFromFile(String)}. */
	protected void writeOutTSToFile(TimeSeries series, String filePath) throws IOException {
		reorderTimeSeries(series);
		List<String> lines = new ArrayList<>();
		lines.add("\"TIME\",\"VALUE\"");
		for (TimeValue tv : series.getTimeValues())
			lines.add(tv.getTime() + "," + tv.getValue());
		Path path = Paths.get(filePath);
		if (path.getParent() != null)
			Files.createDirectories(path.getParent());
		Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	private SimulatedComponent instantiate(String className) throws Exception {
		Class<?> type = load(className);
		if (!SimulatedComponent.class.isAssignableFrom(type))
			throw new IllegalArgumentException(className + " does not implement " + SimulatedComponent.class.getName());
		return (SimulatedComponent) type.getDeclaredConstructor().newInstance();
	}

	private Class<?> load(String className) throws ClassNotFoundException {
		// The component belongs to the user project: try the loader of the generated
		// class first, then the context class loader, then the loader of this bundle.
		for (ClassLoader loader : new ClassLoader[] { getClass().getClassLoader(),
				Thread.currentThread().getContextClassLoader(), JavaComponentService.class.getClassLoader() }) {
			if (loader == null)
				continue;
			try {
				return Class.forName(className, true, loader);
			} catch (ClassNotFoundException e) {
				// try the next class loader
			}
		}
		throw new ClassNotFoundException(className);
	}
}
