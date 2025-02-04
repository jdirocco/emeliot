package emeliot.dsl.lib;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import static java.util.Comparator.comparing;

public class EmeliotResourceUtils {
	private EmeliotResourceUtils() {
		// empty constructor never to be called
	}

	/**
	 * Returns all the {@link EPackage} instances found as first element
	 * of the given {@link Resource}s.
	 * 
	 * @param resources
	 * @return
	 */
	public static Collection<EPackage> getEPackages(Collection<Resource> resources) {
		return getEPackagesStream(resources) // we must be deterministic
			.collect(Collectors.toList());
	}

	/**
	 * Returns a stream with all the {@link EPackage} instances found as first
	 * element of the given {@link Resource}s.
	 * 
	 * @param resources
	 * @return
	 */
	public static Stream<EPackage> getEPackagesStream(Collection<Resource> resources) {
		return resources.stream()
			.map(EmeliotResourceUtils::getEPackage)
			.filter(Objects::nonNull)
			.sorted(ePackageComparator());
	}

	/**
	 * Return the first element of the {@link Resource} as an {@link EPackage} or
	 * null otherwise (for example, the resource is empty or the first element is
	 * not an {@link EPackage}),
	 * 
	 * @param r
	 * @return
	 */
	public static EPackage getEPackage(Resource r) {
		var contents = r.getContents();
		if (contents.isEmpty())
			return null;
		var first = contents.get(0);
		if (!(first instanceof EPackage))
			return null;
		return (EPackage) first;
	}

	/**
	 * Compares the {@link EPackage} by their {@link EPackage#getNsURI()}.
	 * 
	 * @return
	 */
	public static Comparator<EPackage> ePackageComparator() {
		return comparing(EPackage::getNsURI);
	}

	/**
	 * Returns the file name of the given {@link Resource}
	 * 
	 * @param resource
	 * @return
	 */
	public static String getFileName(Resource resource) {
		var resourceURI = resource.getURI();
		var resourcePath = Paths.get(resourceURI.toFileString());
		return resourcePath.getFileName().toString();
	}
}
