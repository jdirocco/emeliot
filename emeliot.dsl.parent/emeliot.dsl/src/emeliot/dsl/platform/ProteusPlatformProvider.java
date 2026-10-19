package emeliot.dsl.platform;

import emeliot.dsl.lib.ProteusService;

/**
 * Registers the Proteus platform outside OSGi. Inside Eclipse, the same
 * platform is contributed to the {@code emeliot.dsl.simulationPlatforms}
 * extension point in {@code plugin.xml}.
 */
public class ProteusPlatformProvider implements SimulationPlatformProvider {

	@Override
	public String getId() {
		return "proteus";
	}

	@Override
	public String getServiceClassName() {
		return ProteusService.class.getName();
	}
}
