package emeliot.dsl.platform;

/**
 * Service provider interface used to register a simulation platform when
 * FATE-DSL runs outside OSGi (e.g., standalone or Maven builds), where the
 * Eclipse extension registry is not available. Implementations are declared in
 * {@code META-INF/services/emeliot.dsl.platform.SimulationPlatformProvider}.
 * <p>
 * Inside Eclipse, platforms are contributed to the
 * {@code emeliot.dsl.simulationPlatforms} extension point instead.
 */
public interface SimulationPlatformProvider {

	/** @return the identifier used in the {@code simulation_platform} clause of a model */
	String getId();

	/**
	 * @return the fully qualified name of the platform service, a subclass of
	 *         {@link emeliot.dsl.lib.AbstractPlatformService}
	 */
	String getServiceClassName();
}
