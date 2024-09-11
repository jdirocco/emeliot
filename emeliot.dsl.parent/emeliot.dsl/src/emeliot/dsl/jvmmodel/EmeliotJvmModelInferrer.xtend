/*
 * generated by Xtext 2.36.0
 */
package emeliot.dsl.jvmmodel

import com.google.inject.Inject
import emeliot.dsl.lib.EmeliotLib
import emeliot.dsl.read.Model
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
class EmeliotJvmModelInferrer extends AbstractModelInferrer {

	/**
	 * convenience API to build and initialize JVM types and their members.
	 */
	@Inject extension JvmTypesBuilder

	/**
	 * The dispatch method {@code infer} is called for each instance of the
	 * given element's type that is contained in a resource.
	 * 
	 * @param element
	 *            the model to create one or more
	 *            {@link JvmDeclaredType declared
	 *            types} from.
	 * @param acceptor
	 *            each created
	 *            {@link JvmDeclaredType type}
	 *            without a container should be passed to the acceptor in order
	 *            get attached to the current resource. The acceptor's
	 *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
	 *            accept(..)} method takes the constructed empty type for the
	 *            pre-indexing phase. This one is further initialized in the
	 *            indexing phase using the lambda you pass as the last argument.
	 * @param isPreIndexingPhase
	 *            whether the method is called in a pre-indexing phase, i.e.
	 *            when the global index is not yet fully updated. You must not
	 *            rely on linking using the index if isPreIndexingPhase is
	 *            <code>true</code>.
	 */
	def dispatch void infer(Model element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
		val className = element.name
		acceptor.accept(element.toClass(className) [
//			members += element.toField('path', typeRef(String))[
//				initializer = '''"«element.timeSeries»"'''
//			]
			superTypes += typeRef(EmeliotLib).cloneWithProxies
			members += element.toMethod("main", typeRef(Void.TYPE)) [
				parameters += element.toParameter("args", typeRef(String).addArrayTypeDimension)
				static = true
				body = '''«element.name» th1s = new «element.name»();
	try{
		th1s.doExecute();
	} catch(Exception e){e.printStackTrace();}'''
			]
			for (mut : element.mutations) {
				members += mut.toMethod(mut.name, typeRef(Void.TYPE)) [
					body = mut.operation
					parameters += mut.toParameter("eobject", typeRef(Object))
				]
			}
			members += element.toMethod("doExecute", Void.TYPE.typeRef) [
				visibility = JvmVisibility.PROTECTED
//					annotations += Override.annotationRef
				exceptions += Exception.typeRef
				body = '''
					«FOR o : element.mutations»
						«o.name»("«o.timeSeriesPath»");
					«ENDFOR»	
				'''
			]
		])
	}
}
