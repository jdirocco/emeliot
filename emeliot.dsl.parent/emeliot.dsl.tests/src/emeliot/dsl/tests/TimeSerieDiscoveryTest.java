package emeliot.dsl.tests;

import java.io.IOException;
import java.nio.file.Paths;

import emeliot.dsl.lib.DiscoveryOutcome;
import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;

public class TimeSerieDiscoveryTest extends MainStdLibraryTest {
	
	
	
	public static void testIsCommission(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createCommissionTimeSerie(factory, e);
		DiscoveryOutcome res = e.isCommission(ts1, ts2);
		System.out.println("Expected commission. Actual: " + res.getOutcomeMsg());
		res = e.isCommission(ts1, ts1);
		System.out.println("Expected no commission. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsOmission(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createOmissionTimeSerie(factory, e);
		DiscoveryOutcome res = e.isOmission(ts1, ts2);
		System.out.println("Expected omission. Actual: " + res.getOutcomeMsg());
		res = e.isOmission(ts1, ts1);
		System.out.println("Expected no omission. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsLate(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createLateTimeSerie(factory, e, 0.5);
		DiscoveryOutcome res = e.isLate(ts1, ts2, 0.4);
		System.out.println("Expected late. Actual: " + res.getOutcomeMsg());
		res = e.isLate(ts1, ts2, 0.5);
		System.out.println("Expected no late. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsEarly(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createEarlyTimeSerie(factory, e, 0.5);
		DiscoveryOutcome res = e.isEarly(ts1, ts2, 0.4);
		System.out.println("Expected early. Actual: " + res.getOutcomeMsg());
		res = e.isEarly(ts1, ts2, 0.5);
		System.out.println("Expected no early. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsValueCoarse(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createValueCoarseTimeSerie(factory, e, 110);
		DiscoveryOutcome res = e.isValueCoarse(ts1, ts2, 0.5, 1, 100);
		System.out.println("Expected value coarse. Actual: " + res.getOutcomeMsg());
		res = e.isValueCoarse(ts1, ts2, 0.5, 1, 110);
		System.out.println("Expected no value coarse. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsValueSubtle(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createValueSubtleTimeSerie(factory, e, 1.0);
		DiscoveryOutcome res = e.isValueSubtle(ts1, ts2, 0.5, 1, 100);
		System.out.println("Expected value subtle. Actual: " + res.getOutcomeMsg());
		res = e.isValueSubtle(ts1, ts2, 3.0, 1, 110);
		System.out.println("Expected no value subtle. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testAreSameSize(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createSmallerTimeSerie(factory, e);
		DiscoveryOutcome res = e.areSameSize(ts1, ts1);
		System.out.println("Expected same size. Actual: " + res.getOutcomeMsg());
		res = e.areSameSize(ts1, ts2);
		System.out.println("Expected no same size. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsSmaller(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createSmallerTimeSerie(factory, e);
		DiscoveryOutcome res = e.isSmaller(ts2, ts1);
		System.out.println("Expected smaller size. Actual: " + res.getOutcomeMsg());
		res = e.isSmaller(ts1, ts2);
		System.out.println("Expected no smaller size. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testIsBigger(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeSerie(factory, e);
		TimeSeries ts2 = createBiggerTimeSerie(factory, e);
		DiscoveryOutcome res = e.isBigger(ts2, ts1);
		System.out.println("Expected bigger size. Actual: " + res.getOutcomeMsg());
		res = e.isBigger(ts1, ts2);
		System.out.println("Expected no bigger size. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testTimeOutRange(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createTimeOutRangeTimeSerie(factory, e, 105);
		DiscoveryOutcome res = e.hasTimeOutRange(ts1, 1, 100);
		System.out.println("Expected time out range. Actual: " + res.getOutcomeMsg());
		res = e.hasTimeOutRange(ts1, 1, 105);
		System.out.println("Expected no time out range. Actual: " + res.getOutcomeMsg());
	}
	
	public static void testValueOutRange(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts1 = createValueOutRangeTimeSerie(factory, e, 105);
		DiscoveryOutcome res = e.hasValueOutRange(ts1, 1, 100);
		System.out.println("Expected value out range. Actual: " + res.getOutcomeMsg());
		res = e.hasValueOutRange(ts1, 1, 105);
		System.out.println("Expected no value out range. Actual: " + res.getOutcomeMsg());
	}
	
	
	 
	
	
	 
	public static void testDiscoveryOperatorsFiles(EmeliotLib e) throws IOException {
		
	    // Test commission file
	    String pathOriginal= Paths.get("./proteus-example/io/outTSOriginal.DAT").toString();
	    String pathMutated = Paths.get("./proteus-example/io/outTSCommission.DAT").toString();
	    DiscoveryOutcome res = e.isCommission_File(pathOriginal, pathMutated); //changed time series by removing 1 element removed
	    System.out.println("Expected commission. Actual: " + res.getOutcomeMsg());
	   
	    // Test omission file
	    pathMutated = Paths.get("./proteus-example/io/outTSOmission.DAT").toString();
	    res = e.isOmission_File(pathOriginal, pathMutated); //changed time series by adding 1 element
	    System.out.println("Expected omission. Actual: " + res.getOutcomeMsg());

	    // Test early file
	    pathMutated = Paths.get("./proteus-example/io/outTSEarly.DAT").toString();
	    res = e.isEarly_File(pathOriginal, pathMutated, 1.0); //changed one of first time to 2.34218664 (out eps) instead of 4.34218664
	    System.out.println("Expected early. Actual: " + res.getOutcomeMsg());

	    // Test late file
	    pathMutated = Paths.get("./proteus-example/io/outTSLate.DAT").toString();
	    res = e.isLate_File(pathOriginal, pathMutated, 1.0); //changed one of first time to 6.34218664 (out eps) instead of 4.34218664
	    System.out.println("Expected late. Actual: " + res.getOutcomeMsg());
	    
	    // Test value coarse file
	    pathMutated = Paths.get("./proteus-example/io/outTSVCOarse.DAT").toString();
	    res = e.isValueCoarse_File(pathOriginal, pathMutated, 1.0, 0, 100); //changed first value to 105 (out range and out eps) instead of 6e-007
	    System.out.println("Expected value coarse. Actual: " + res.getOutcomeMsg());
	    
	    // Test value subtle file
	    pathMutated = Paths.get("./proteus-example/io/outTSVSubtle.DAT").toString();
	    res = e.isValueSubtle_File(pathOriginal, pathMutated, 1.0, 0, 100); //changed first value to 5 (in range but out eps) instead of 6e-007
	    System.out.println("Expected value subtle. Actual: " + res.getOutcomeMsg());
	    
	    // Test are same size file
	    res = e.areSameSize_File(pathOriginal, pathOriginal);
	    System.out.println("Expected same size. Actual: " + res.getOutcomeMsg());
	    
	    // Test is bigger file
	    pathMutated = Paths.get("./proteus-example/io/outTSBigger.DAT").toString();
	    res = e.isBigger_File(pathMutated, pathOriginal);//added 1 element on top
	    System.out.println("Expected bigger size. Actual: " + res.getOutcomeMsg());
	    
	    // Test is smaller file
	    pathMutated = Paths.get("./proteus-example/io/outTSSmaller.DAT").toString();
	    res = e.isSmaller_File(pathMutated, pathOriginal); //removed first element
	    System.out.println("Expected smaller size. Actual: " + res.getOutcomeMsg());
	    
	    // Test has time out range file
	    pathMutated = Paths.get("./proteus-example/io/outTSTimeOutRange.DAT").toString();
	    res = e.hasTimeOutRange_File(pathMutated, 0, 100); //set first time to 110
	    System.out.println("Expected time out range. Actual: " + res.getOutcomeMsg());
	    
	    // Test has value out range file
	    pathMutated = Paths.get("./proteus-example/io/outTSValueOutRange.DAT").toString();
	    res = e.hasValueOutRange_File(pathMutated, 0, 100); //set first value to 110
	    System.out.println("Expected value out range. Actual: " + res.getOutcomeMsg());
		
	}
	
	
	
	
	
	

}
