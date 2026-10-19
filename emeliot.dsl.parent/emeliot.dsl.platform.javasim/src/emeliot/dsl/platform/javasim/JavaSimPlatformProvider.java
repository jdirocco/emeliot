package emeliot.dsl.platform.javasim;

import emeliot.dsl.platform.SimulationPlatformProvider;

/**
 * Registers the Java simulation platform outside OSGi. Inside Eclipse, the
 * platform is contributed through {@code plugin.xml}.
 */
public class JavaSimPlatformProvider implements SimulationPlatformProvider {

	@Override
	public String getId() {
		return "javasim";
	}

	@Override
	public String getServiceClassName() {
		return JavaComponentService.class.getName();
	}
}
