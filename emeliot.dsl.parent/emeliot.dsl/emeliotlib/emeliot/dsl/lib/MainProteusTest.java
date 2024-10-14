package emeliot.dsl.lib;

import java.nio.file.Paths;
import java.util.Arrays;

public class MainProteusTest {

	public static void main(String[] args) {
		EmeliotModelManager em = new EmeliotModelManager();
		//em.loadEcoreFile("..\\emeliot.dsl.xtext\\model\\generated\\Emeliot.ecore");
		em.loadEcoreFile("..\\emeliot.dsl\\model\\generated\\Emeliot.ecore");
		EmeliotStandardLibrary e = new EmeliotStandardLibrary(em);
		try {
			e.runTestCase(
					Paths.get("./proteus-example/Component"), 
					Paths.get("./proteus-example/TestCase"), 
					Arrays.asList("irrigationUnit1", "irrigationUnit2"));			
		} catch (Exception exc) {
			exc.printStackTrace();
		}		
	}

}
