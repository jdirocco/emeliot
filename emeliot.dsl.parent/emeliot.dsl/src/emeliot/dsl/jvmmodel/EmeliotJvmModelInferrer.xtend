/*
 * generated by Xtext 2.36.0
 */
package emeliot.dsl.jvmmodel

import com.google.inject.Inject
import emeliot.dsl.read.ConfigMutation
import emeliot.dsl.read.Model
import emeliot.dsl.read.ReadFactory
import emeliot.dsl.read.TimeSeries
import emeliot.dsl.read.TimeValue
import java.util.List
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmTypeParameter
import org.eclipse.xtext.common.types.JvmTypeParameterDeclarator
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import emeliot.dsl.lib.ProteusService
import emeliot.dsl.read.PORT_TYPE
import emeliot.dsl.read.Configuration
import emeliot.dsl.read.Port

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
			if ("proteus".equals(element.simulationPlatform))
				superTypes+= typeRef(ProteusService)
//			superTypes += typeRef(ProteusServiceImpl).cloneWithProxies
			typeRef(ReadFactory)
			typeRef(TimeValue)

			members += element.toMethod("main", typeRef(Void.TYPE)) [
				parameters += element.toParameter("args", typeRef(String).addArrayTypeDimension)
				static = true
				body = '''«element.name» th1s = new «element.name»();
	try{
		th1s.doExecute();
	} catch(Exception e){e.printStackTrace();}'''
			]
			members += element.toField("factory", typeRef(ReadFactory))
			for (mut : element.mutations) {

				members += mut.toMethod(mut.name, typeRef(Void.TYPE)) [
					body = mut.operation				
					parameters += mut.toParameter("inTS", typeRef(TimeSeries))
				]
			}
			for (mut : element.discoveries) {

				members += mut.toMethod(mut.name, typeRef(Void.TYPE)) [
					body = mut.expression				
					parameters += mut.toParameter("inTS", typeRef(TimeSeries))
					parameters += mut.toParameter("outTS", typeRef(TimeSeries))
				]
			}
			
			//NEW
			val inport = newArrayList()
			val outport = newArrayList()
			for (component :element.specification.components)
				for (port : component.ports)
					if (port.type == PORT_TYPE.INPUT)
						inport.add(port.name)
					else
						outport.add(port.name)
			members += element.specification.toField("inport", typeRef(List))[
				initializer = '''new ArrayList();'''
					
			]
			members += element.specification.toField("outport", typeRef(List))[
				initializer = '''new ArrayList();'''
					
			]
				
			/*for (config : element.configurations.filter[e|e instanceof ConfigMutation]) {
				var mutation =  config as ConfigMutation
				
				
				var mut = mutation.mut
				
				members += mutation.timeSeriesValues.toField(mutation.timeSeriesValues.name, typeRef(TimeSeries)) [
					initializer = ''' ReadFactory.eINSTANCE.createTimeSeries() '''
				]
				for (tv : mutation.timeSeriesValues.timeValues) {

					members += tv.toField(tv.name, typeRef(TimeValue)) [
						initializer = ''' ReadFactory.eINSTANCE.createTimeValue() '''

					]

				}

			}*/
			members += element.toField('p', typeRef(Port))
			members += element.toField('tv', typeRef(TimeValue))
			for (ts : element.timeSeries) {
				members += ts.toField(ts.name, typeRef(TimeSeries)) [
					initializer = ''' ReadFactory.eINSTANCE.createTimeSeries() '''
				]
			}
			
			for (config : element.configurations.filter[e|e instanceof ConfigMutation]) {
				var mutation =  config as ConfigMutation
				
				
				var mut = mutation.mut
				
				
				
				
				
//				for (tv : mutation.timeSeriesValues.timeValues) {

//					members += tv.toField(tv.name, typeRef(TimeValue)) [
//						initializer = ''' ReadFactory.eINSTANCE.createTimeValue() '''
//
//					]

//				}

			}
			
			
			
		/* 	members += element.discovery.toMethod(element.discovery.name, typeRef(Void.TYPE))[
				body = element.discovery.expression
					parameters += element.discovery.toParameter("expectedSeries", typeRef(TimeSeries))
			]*/
			

			members += element.toMethod("doExecute", Void.TYPE.typeRef) [
				visibility = JvmVisibility.PROTECTED
//					annotations += Override.annotationRef
				exceptions += Exception.typeRef
				
			
				body = '''	
						 		
					
						«FOR o : element.configurations.filter[e|e instanceof ConfigMutation]»		
									
«««							for (TimeValue tv : «(o as ConfigMutation).timeSeriesValues») 
							«IF (o as ConfigMutation).timeSeries.timeValues !== null»
								
							
							«FOR e : ( (o as ConfigMutation).timeSeries).timeValues»
							
								tv = ReadFactory.eINSTANCE.createTimeValue();	
											
								tv.setTime(«e.time»);
								tv.setValue(«e.value»);								
								« (o as ConfigMutation).timeSeries.name».getTimeValues().add(tv);
												
							«ENDFOR»
							
										
							
							«ENDIF »
							«IF (o as ConfigMutation).timeSeries.name !== null»
								« ((o as ConfigMutation).timeSeries.name)» = readTSFromFile("« (o as ConfigMutation).timeSeries.timeSeriesPath»");
							«ENDIF»
							«(o as ConfigMutation).mut.name»(«(o as ConfigMutation).timeSeries.name»);	
							writeTSToFile((TimeSeries)«(o as ConfigMutation).timeSeries.name»,"«(o as ConfigMutation).timeSeries.name»_M.txt");
							
							
						«ENDFOR»	
						
						final TimeSeries outSeries  =  ReadFactory.eINSTANCE.createTimeSeries() ;
«««						«FOR o: inport»
«««							«getFilePath(element.configurations,o)»
«««						«ENDFOR»
«««						«element.discovery.name»(outSeries);
						«FOR component : element.specification.components»
							
							«FOR port : component.ports.filter[it.type == PORT_TYPE.OUTPUT]»
								p = ReadFactory.eINSTANCE.createPort();
								p.setName("Nome");
								p.setPath("Path");
								outport.add(p);
							«ENDFOR»
						«ENDFOR»
						outport.stream().map(x -> x.path).collect(Collectors.toList();
						
				'''

			]
		])

	}
	def private String getFilePath(List<Configuration> confs, String portName){
		print(confs)
		for (ConfigMutation conf : confs.filter[it instanceof ConfigMutation].map[it as ConfigMutation]){
			if (conf.timeSeries.timeSeriesPath!==null){
				var path = conf.timeSeries
				if(path.name.equals(portName)){
					return path.timeSeriesPath;
				}
					
			}
		}
		throw new Exception();
	}

//	for (ConfigMutation conf : element.configurations.filter[it instanceof ConfigMutation].map[it as ConfigMutation]){
//				var TSpath = conf.timeSeries as TimeSeriesPath
//								 
//			}

	def private void copyTypeParameters(JvmTypeParameterDeclarator target, List<JvmTypeParameter> typeParameters) {
		for (typeParameter : typeParameters) {
			target.typeParameters += typeParameter.cloneWithProxies
		}
	}
}
