package emeliot.dsl.platform;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.inject.Singleton;

/**
 * Default {@link SimulationPlatformRegistry}.
 * <p>
 * Platforms are collected from the {@code emeliot.dsl.simulationPlatforms}
 * extension point when the Eclipse extension registry is available (OSGi), and
 * from {@link SimulationPlatformProvider} implementations found through
 * {@link ServiceLoader} otherwise. The registry is queried on every call, so
 * that bundles installed or removed at runtime are taken into account.
 */
@Singleton
public class DefaultSimulationPlatformRegistry implements SimulationPlatformRegistry {

	private static final Logger LOG = Logger.getLogger(DefaultSimulationPlatformRegistry.class);

	private static final String PLATFORM_ELEMENT = "platform";
	private static final String ID_ATTRIBUTE = "id";
	private static final String SERVICE_CLASS_ATTRIBUTE = "serviceClass";

	@Override
	public Map<String, String> getPlatforms() {
		Map<String, String> platforms = new LinkedHashMap<>();
		collectFromExtensionRegistry(platforms);
		collectFromServiceLoader(platforms, SimulationPlatformRegistry.class.getClassLoader());
		collectFromServiceLoader(platforms, Thread.currentThread().getContextClassLoader());
		return platforms;
	}

	private void collectFromExtensionRegistry(Map<String, String> platforms) {
		try {
			IExtensionRegistry registry = RegistryFactory.getRegistry();
			if (registry == null)
				return;
			for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSION_POINT_ID)) {
				if (!PLATFORM_ELEMENT.equals(element.getName()))
					continue;
				String id = element.getAttribute(ID_ATTRIBUTE);
				String serviceClass = element.getAttribute(SERVICE_CLASS_ATTRIBUTE);
				if (id != null && serviceClass != null)
					platforms.putIfAbsent(id, serviceClass);
			}
		} catch (NoClassDefFoundError | RuntimeException e) {
			// Extension registry not available (e.g., standalone execution)
			LOG.debug("Extension registry not available: " + e.getMessage());
		}
	}

	private void collectFromServiceLoader(Map<String, String> platforms, ClassLoader classLoader) {
		if (classLoader == null)
			return;
		try {
			for (SimulationPlatformProvider provider : ServiceLoader.load(SimulationPlatformProvider.class, classLoader))
				if (provider.getId() != null && provider.getServiceClassName() != null)
					platforms.putIfAbsent(provider.getId(), provider.getServiceClassName());
		} catch (ServiceConfigurationError e) {
			LOG.warn("Invalid simulation platform provider: " + e.getMessage());
		}
	}
}
