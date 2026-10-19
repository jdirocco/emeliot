package emeliot.dsl.lib;

/**
 * Base class of every simulation platform service.
 * <p>
 * The class generated from a FATE-DSL model extends the service registered for
 * the {@code simulation_platform} declared in the model. By extending this
 * class, a platform service inherits the platform-independent standard library
 * ({@link EmeliotService}, {@link EmeliotMutationService},
 * {@link EmeliotDiscoveryService}) and only has to implement the
 * platform-specific operations, i.e., {@link #runSimulation} and the reading of
 * input time series in the platform format.
 */
public abstract class AbstractPlatformService extends EmeliotLib implements SimulationPlatformService {
}
