package emeliot.dsl.platform;

import java.util.Map;

import com.google.inject.ImplementedBy;

/**
 * Registry of the simulation platforms available to FATE-DSL.
 * <p>
 * The JVM model inferrer uses the registry to select the platform service that
 * the class generated from a model extends; the validator and the content
 * assist use it to check and propose the values of {@code simulation_platform}.
 * Adding a platform therefore requires no change to the language
 * implementation.
 */
@ImplementedBy(DefaultSimulationPlatformRegistry.class)
public interface SimulationPlatformRegistry {

	/** Identifier of the extension point to which platforms are contributed. */
	String EXTENSION_POINT_ID = "emeliot.dsl.simulationPlatforms";

	/** @return the registered platforms, mapping each identifier to the fully qualified name of its service class */
	Map<String, String> getPlatforms();

	/**
	 * @param platformId the identifier used in the {@code simulation_platform} clause
	 * @return the fully qualified name of the service class, or {@code null} if the platform is unknown
	 */
	default String getServiceClassName(String platformId) {
		return platformId == null ? null : getPlatforms().get(platformId);
	}
}
