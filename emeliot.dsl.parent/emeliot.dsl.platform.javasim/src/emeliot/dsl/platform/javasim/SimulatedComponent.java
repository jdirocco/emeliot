package emeliot.dsl.platform.javasim;

import java.util.List;

import emeliot.dsl.read.TimeSeries;

/**
 * An IoT component simulated in Java. The behavior of the component is a
 * function from its input time series to its output time series.
 * <p>
 * Implementations must provide a public no-argument constructor. The fully
 * qualified name of the implementation is used as {@code project_file} in
 * FATE-DSL models whose {@code simulation_platform} is {@code javasim}.
 */
public interface SimulatedComponent {

	/**
	 * Simulates the component.
	 *
	 * @param inputs  the input time series, in the order of the configured injections
	 * @param outputs the number of output time series expected by the model
	 * @return the output time series, one for each requested output
	 * @throws Exception if the simulation fails
	 */
	List<TimeSeries> simulate(List<TimeSeries> inputs, int outputs) throws Exception;
}
