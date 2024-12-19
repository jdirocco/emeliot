package emeliot.dsl.tests;

import java.nio.file.Paths;
import java.util.Arrays;
import emeliot.dsl.lib.*;

public class MainProteusTest {

	public static void main(String[] args) {
		//EmeliotModelManager em = new EmeliotModelManager();
		//em.loadEcoreFile("..\\emeliot.dsl.xtext\\model\\generated\\Emeliot.ecore");
		//em.loadEcoreFile("..\\emeliot.dsl\\model\\generated\\Emeliot.ecore");
		//EmeliotLib e = new EmeliotLib(em);
		ProteusService e = new ProteusService();
		try {
			e.runTestCase(
					Paths.get("./proteus-example/Component"), 
					Paths.get("./proteus-example/TestCase/inputs"), 
					Paths.get("./proteus-example/TestCase/outputs"), 
					Arrays.asList("irrigationUnit1", "irrigationUnit2"));			
		} catch (Exception exc) {
			exc.printStackTrace();
		}		
	}

}
