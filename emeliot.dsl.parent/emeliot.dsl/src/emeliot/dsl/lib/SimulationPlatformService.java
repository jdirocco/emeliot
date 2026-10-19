package emeliot.dsl.lib;

import java.nio.file.Path;
import java.util.List;

/**
 * Contract that a simulation platform must fulfil to execute FATE-DSL models.
 * <p>
 * It isolates the only platform-dependent step of the execution procedure of a
 * FATE-DSL model, i.e., running the system under test (SUT) on the input time
 * series mutated by the injections. Time series manipulation, injection and
 * discovery operators are platform independent and are inherited from
 * {@link EmeliotLib}.
 * <p>
 * New platforms are contributed by subclassing {@link AbstractPlatformService}
 * and registering the subclass to the {@code emeliot.dsl.simulationPlatforms}
 * extension point (or, outside OSGi, through a
 * {@link emeliot.dsl.platform.SimulationPlatformProvider}).
 */
public interface SimulationPlatformService {

	/**
	 * Runs the system under test.
	 *
	 * @param project      the platform-specific project declared in the model
	 *                     ({@code project_file})
	 * @param inputSeries  paths of the mutated input time series produced by the
	 *                     configured injections
	 * @param outputSeries paths where the output time series observed by the
	 *                     configured discoveries must be stored
	 * @throws Exception if the simulation cannot be completed
	 */
	void runSimulation(Path project, List<String> inputSeries, List<String> outputSeries) throws Exception;
}
