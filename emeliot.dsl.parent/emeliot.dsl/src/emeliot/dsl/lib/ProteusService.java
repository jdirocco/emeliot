package emeliot.dsl.lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;

public class ProteusService extends EmeliotLib {

	@Override
	public void writeTSToFile(TimeSeriesValue TimeSeriesValue, String filePath) throws IOException {
		reorderTimeSeries(TimeSeriesValue);
		List<String> lines = TimeSeriesValue.getTimeValues().stream().map(tv -> tv.getTime() + " " + tv.getValue())
				.collect(Collectors.toList());
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	@Override
	public TimeSeriesValue readTSFromFile(String filePath) throws IOException {

		TimeSeriesValue TimeSeriesValue = ReadFactory.eINSTANCE.createTimeSeriesValue();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		for (String line : lines) {
			String[] parts = line.trim().split("\\s+");
			if (parts.length != 2)
				throw new IOException("Invalid line format: " + line);
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(Integer.parseInt(parts[1].trim()));
			double value = (Double.parseDouble(parts[0].trim()));
			tv.setValue(value);
			TimeSeriesValue.getTimeValues().add(tv);
		}
		reorderTimeSeries(TimeSeriesValue);
		return TimeSeriesValue;
	}

}
