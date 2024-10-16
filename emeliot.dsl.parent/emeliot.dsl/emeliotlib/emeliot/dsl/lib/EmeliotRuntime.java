package emeliot.dsl.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EmeliotRuntime {

	/**
	 * For loading ecores and all other runtime {@link EPackage} management.
	 */
	private EmeliotModelManager modelManager = null;

	/**
	 * For migrating model instances
	 */
	private EmeliotTimeSeriesMutator modelMigrator = null;

	private Logger logger = Logger.getLogger(getClass());


	private Collection<EmeliotRuntime> children = new ArrayList<>();

	/**
	 * Reuses the {@link EdeltaEPackageManager} from the other object
	 * and also keeps track of updating the children of the other objects.
	 * 
	 * @param other
	 */
	protected EmeliotRuntime(EmeliotRuntime other) {
		this(other.modelManager);
		modelMigrator = other.modelMigrator;
		other.children.add(this);
	}

	/**
	 * Uses the passed {@link EdeltaModelManager}.
	 * 
	 * @param modelManager
	 */
	protected EmeliotRuntime(EmeliotModelManager modelManager) {
		this.modelManager = modelManager;
	}

	/**
	 * Uses the passed {@link EdeltaModelMigrator}.
	 * 
	 * @param modelMigrator
	 */
	protected EmeliotRuntime(EmeliotTimeSeriesMutator modelMigrator) {
//		this(modelMigrator.getEvolvingModelManager());
		this.modelMigrator = modelMigrator;
	}

	/**
	 * Performs the actual execution of the transformation, to be
	 * called by clients.
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		performSanityChecks();
		doExecute();
	}

	/**
	 * This is meant to be implemented by the code generated by
	 * the Edelta DSL, in order to perform sanity checks, such as if
	 * all EPackages (their ecores) are loaded.
	 * 
	 * @throws Exception
	 */
	protected void performSanityChecks() throws Exception { // NOSONAR we can't predict a specific exception
		// to be implemented by the generated code
	}

	/**
	 * Actual implementation of the transformation, to be generated
	 * by the Edelta DSL compiler.
	 * 
	 * @throws Exception
	 */
	protected void doExecute() throws Exception { // NOSONAR we can't predict a specific exception
		// to be implemented by the generated code
	}

	/**
	 * Throws an {@link EdeltaPackageNotLoadedException} if the specified
	 * EPackage (its Ecore) has not been loaded.
	 * 
	 * @param packageName
	 * @throws EdeltaPackageNotLoadedException
	 */
	protected void ensureEPackageIsLoaded(String packageName) throws EdeltaPackageNotLoadedException {
		if (getEPackage(packageName) == null) {
			throw new EdeltaPackageNotLoadedException(packageName);
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * This also propagates the passed {@link EdeltaIssuePresenter} to the
	 * children (that is, the used Edelta libraries).
	 * 
	 * @param issuePresenter
	 */

	public void logError(Supplier<String> messageSupplier) {
		internalLog(Level.ERROR, messageSupplier);
	}

	public void logWarn(Supplier<String> messageSupplier) {
		internalLog(Level.WARN, messageSupplier);
	}

	public void logInfo(Supplier<String> messageSupplier) {
		internalLog(Level.INFO, messageSupplier);
	}

	public void logDebug(Supplier<String> messageSupplier) {
		internalLog(Level.DEBUG, messageSupplier);
	}

	private void internalLog(Level level, Supplier<String> messageSupplier) {
		if (logger.isEnabledFor(level)) {
			logger.log(level, messageSupplier.get());
		}
	}

	
	/**
	 * Retrieves an {@link EPackage} given its fully qualified name, that is, for
	 * subpackages, the string is expected to contain the names of the full EPackage
	 * hierarchy separated by dots. For example,
	 * "rootpackage.subpackage.subsubpackage".
	 * 
	 * @param packageName
	 * @return the found {@link EPackage} or null
	 */
	public EPackage getEPackage(String packageName) {
		var packageNames = packageName.split("\\.");
		var currentPackage = getEPackageFromPackageManager(packageNames[0]);
		var packageNameIndex = 1;
		while (currentPackage != null && packageNameIndex < packageNames.length) {
			var currentPackageName = packageNames[packageNameIndex];
			currentPackage = currentPackage.getESubpackages().stream()
					.filter(p -> currentPackageName.equals(p.getName()))
					.findFirst()
					.orElse(null);
			packageNameIndex++;
		}
		return currentPackage;
	}

	private EPackage getEPackageFromPackageManager(String packageName) {
		return modelManager.getEPackage(packageName);
	}

	public EClassifier getEClassifier(String packageName, String classifierName) {
		var p = getEPackage(packageName);
		if (p == null) {
			return null;
		}
		return p.getEClassifier(classifierName);
	}

	public EClass getEClass(String packageName, String className) {
		var c = getEClassifier(packageName, className);
		if (c instanceof EClass) {
			return (EClass) c;
		}
		return null;
	}

	public EClass getEClass(EPackage ePackage, String className) {
		var c = ePackage.getEClassifier(className);
		if (c instanceof EClass) {
			return (EClass) c;
		}
		return null;
	}

	public EDataType getEDataType(String packageName, String datatypeName) {
		var c = getEClassifier(packageName, datatypeName);
		if (c instanceof EDataType) {
			return (EDataType) c;
		}
		return null;
	}

	public EEnum getEEnum(String packageName, String enumName) {
		var c = getEClassifier(packageName, enumName);
		if (c instanceof EEnum) {
			return (EEnum) c;
		}
		return null;
	}

	public EStructuralFeature getEStructuralFeature(String packageName, String className, String featureName) {
		var c = getEClass(packageName, className);
		if (c == null) {
			return null;
		}
		return c.getEStructuralFeature(featureName);
	}

	public EAttribute getEAttribute(String packageName, String className, String attributeName) {
		var f = getEStructuralFeature(packageName, className, attributeName);
		if (f instanceof EAttribute) {
			return (EAttribute) f;
		}
		return null;
	}

	public EReference getEReference(String packageName, String className, String referenceName) {
		var f = getEStructuralFeature(packageName, className, referenceName);
		if (f instanceof EReference) {
			return (EReference) f;
		}
		return null;
	}

	public EEnumLiteral getEEnumLiteral(String packageName, String enumName, String enumLiteralName) {
		var eenum = getEEnum(packageName, enumName);
		if (eenum == null) {
			return null;
		}
		return eenum.getEEnumLiteral(enumLiteralName);
	}

	/**
	 * Passes an {@link EdeltaModelMigrator} to the passed {@link Consumer}.
	 * 
	 * The default implementation does not nothing, since it is meant to be
	 * implemented during the execution of the engine.
	 * 
	 * @param migratorConsumer
	 */
	public void modelMigration(Consumer<EmeliotTimeSeriesMutator> migratorConsumer) {
		if (modelMigrator != null)
			migratorConsumer.accept(modelMigrator);
	}
}
