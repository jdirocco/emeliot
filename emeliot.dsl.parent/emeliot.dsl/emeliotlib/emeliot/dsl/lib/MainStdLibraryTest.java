package emeliot.dsl.lib;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;


public class MainStdLibraryTest {

	
	public static void main(String[] args) throws IOException {
		ReadFactory factory = ReadFactory.eINSTANCE;
		EmeliotModelManager em = new EmeliotModelManager();
		//em.loadEcoreFile("..\\emeliot.dsl.xtext\\model\\generated\\Emeliot.ecore");
		em.loadEcoreFile("..\\emeliot.dsl\\model\\generated\\Emeliot.ecore");
		EmeliotStandardLibrary e = new EmeliotStandardLibrary(em);
		
		
		
		//testAddOpFilesMethods(e);
		//testChangeOpFilesMethods(e);
		testRemoveOpFilesMethods(e);
		
		
		//testAddOperators(factory, e);
		//testEditOperators(factory, e);
		//testRemoveOperators(factory, e);
		//testAuxOperators(factory, e);
		//TODO: test discovery methods
	}

	public static void testAddOperators(ReadFactory factory, EmeliotStandardLibrary e) {
		System.out.println("TEST ADD-TIME-VALUE");
		TimeSerieAddTest.testAddTimeValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-TIME-AND-RANDOM-VALUE");
		TimeSerieAddTest.testAddTimeAndRandomValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-RANDOM-TIME-AND-VALUE");
		TimeSerieAddTest.testAddRandomTimeAndValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-RANDOM-TIME-AND-RANDOM-VALUE");
		TimeSerieAddTest.testAddRandomTimeAndRandomValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-MULTIPLE-TIME-VALUES");
		TimeSerieAddTest.testAddMultipleTimeValues(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-MULTIPLE-RANDOM-TIME-VALUES");
		TimeSerieAddTest.testAddMultipleRandomTimeValues(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-APPEND-TIME-SERIES");
		TimeSerieAddTest.testAppendTimeSeries(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-MERGE-TIME-SERIES");
		TimeSerieAddTest.testMergeTimeSeries(factory, e);
		System.out.println("=================================");
		System.out.println("TEST ADD-REPLACE-TIME-SERIES");
		TimeSerieAddTest.testReplaceTimeSeries(factory, e);
		System.out.println("=================================");
	}
	
	
	
	public static void testEditOperators(ReadFactory factory, EmeliotStandardLibrary e) {
		System.out.println("TEST CHANGE-VALUE");
		TimeSerieEditTest.testChangeValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME");
		TimeSerieEditTest.testChangeTime(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-VALUE-WITH-RANDOM");
		TimeSerieEditTest.testChangeValueWithRandom(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-WITH-RANDOM");
		TimeSerieEditTest.testChangeTimeWithRandom(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-AND-VALUE");
		TimeSerieEditTest.testChangeTimeAndValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-WITH-RANDOM-AND-VALUE");
		TimeSerieEditTest.testChangeTimeWithRandomAndValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-AND-VALUE-WITH-RANDOM");
		TimeSerieEditTest.testChangeTimeAndValueWithRandom(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-WITH-RANDOM-AND-VALUE-WITH-RANDOM");
		TimeSerieEditTest.testChangeTimeWithRandomAndValueWithRandom(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-RANDOM-TIME-VALUE");
		TimeSerieEditTest.testChangeRandomTimeValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-A-RANDOM-TIME-VALUE-WITH-RANDOM-TIME-VALUE");
		TimeSerieEditTest.testChangeARandomTimeValueWithRandomTimeValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-MULTIPLE-TIME-VALUES");
		TimeSerieEditTest.testChangeMultipleTimeValues(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-MULTIPLE-TIME-VALUES-WITH-RANDOM-TIME-VALUES");
		TimeSerieEditTest.testChangeMultipleTimeValuesWithRandomTimeValues(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-LATE");
		TimeSerieEditTest.testChangeTimeLate(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-RANDOM-TIME-LATE");
		TimeSerieEditTest.testChangeRandomTimeLate(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-MULTIPLE-TIME-LATE");
		TimeSerieEditTest.testChangeMultipleTimeLate(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-TIME-EARLY");
		TimeSerieEditTest.testChangeTimeEarly(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-RANDOM-TIME-EARLY");
		TimeSerieEditTest.testChangeRandomTimeEarly(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-MULTIPLE-TIME-EARLY");
		TimeSerieEditTest.testChangeMultipleTimeEarly(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-VALUE-COARSE");
		TimeSerieEditTest.testChangeValueCoarse(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-RANDOM-VALUE-COARSE");
		TimeSerieEditTest.testChangeRandomValueCoarse(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-MULTIPLE-VALUE-COARSE");
		TimeSerieEditTest.testChangeMultipleValueCoarse(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-VALUE-SUBTLE");
		TimeSerieEditTest.testChangeValueSubtle(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-RANDOM-VALUE-SUBTLE");
		TimeSerieEditTest.testChangeRandomValueSubtle(factory, e);
		System.out.println("=================================");
		System.out.println("TEST CHANGE-MULTIPLE-VALUE-SUBTLE");
		TimeSerieEditTest.testChangeMultipleValueSubtle(factory, e);
		System.out.println("=================================");
	}
	
	
	
	
	
	public static void testRemoveOperators(ReadFactory factory, EmeliotStandardLibrary e) {
		System.out.println("TEST REMOVE-TIME-VALUE1");
		TimeSerieRemoveTest.testRemoveTimeValue1(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-TIME-VALUE2");
		TimeSerieRemoveTest.testRemoveTimeValue2(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-RANDOM-TIME-VALUE1");
		TimeSerieRemoveTest.testRemoveRandomTimeValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-MULTIPLE-TIME-VALUES1");
		TimeSerieRemoveTest.testRemoveMultipleTimeValues1(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-MULTIPLE-TIME-VALUES2");
		TimeSerieRemoveTest.testRemoveMultipleTimeValues2(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-MULTIPLE-TIME-VALUES3");
		TimeSerieRemoveTest.testRemoveMultipleTimeValues3(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-ALL-TIME-VALUES");
		TimeSerieRemoveTest.testRemoveAllTimeValues(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-ALL-TIME-VALUES-BEFORE-TIME");
		TimeSerieRemoveTest.testRemoveTimeValuesBeforeTime(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-ALL-TIME-VALUES-AFTER-TIME");
		TimeSerieRemoveTest.testRemoveTimeValuesAfterTime(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-ALL-TIME-VALUES-BELOW-VALUE");
		TimeSerieRemoveTest.testRemoveTimeValuesBelowValue(factory, e);
		System.out.println("=================================");
		System.out.println("TEST REMOVE-ALL-TIME-VALUES-ABOVE-VALUE");
		TimeSerieRemoveTest.testRemoveTimeValuesAboveValue(factory, e);
		System.out.println("=================================");
	}
	
	public static void testAuxOperators(ReadFactory factory, EmeliotStandardLibrary e) {
	    System.out.println("TEST REORDER-TIME-SERIES");
	    TimeSerieAuxTest.testReorderTimeSeries(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SELECT-RANDOM-TIME-VALUE");
	    TimeSerieAuxTest.testSelectRandomTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST EXIST-TIME");
	    TimeSerieAuxTest.testExistTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST EXIST-VALUE");
	    TimeSerieAuxTest.testExistValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST EXIST-TIME-VALUE");
	    TimeSerieAuxTest.testExistTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-ALL-TIMES");
	    TimeSerieAuxTest.testGetAllTimes(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-ALL-VALUES");
	    TimeSerieAuxTest.testGetAllValues(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-TIMES-IN-RANGE");
	    TimeSerieAuxTest.testGetTimesInRange(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-VALUES-IN-RANGE");
	    TimeSerieAuxTest.testGetValuesInRange(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-TIMES-VALUES-IN-RANGE");
	    TimeSerieAuxTest.testGetTimeValuesInRange(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-VALUE-AT");
	    TimeSerieAuxTest.testGetValueAt(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-TIME-AT");
	    TimeSerieAuxTest.testGetTimeAt(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-TIME-VALUE-AT");
	    TimeSerieAuxTest.testGetTimeValueAt(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST MAX-TIME");
	    TimeSerieAuxTest.testMaxTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST MIN-TIME");
	    TimeSerieAuxTest.testMinTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST MAX-VALUE");
	    TimeSerieAuxTest.testMaxValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST MIN-VALUE");
	    TimeSerieAuxTest.testMinValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-NEXT-TIME-VALUE");
	    TimeSerieAuxTest.testGetNextTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-NEXT-TIME");
	    TimeSerieAuxTest.testGetNextTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-NEXT-VALUE");
	    TimeSerieAuxTest.testGetNextValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-NEXT-TIME-VALUE1");
	    TimeSerieAuxTest.testGetNextTimeValue1(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-NEXT-TIME1");
	    TimeSerieAuxTest.testGetNextTime1(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-NEXT-VALUE1");
	    TimeSerieAuxTest.testGetNextValue1(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-PREVIOUS-TIME-VALUE");
	    TimeSerieAuxTest.testGetPreviousTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-PREVIOUS-TIME");
	    TimeSerieAuxTest.testGetPreviousTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-PREVIOUS-VALUE");
	    TimeSerieAuxTest.testGetPreviousValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-PREVIOUS-TIME-VALUE1");
	    TimeSerieAuxTest.testGetPreviousTimeValue1(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-PREVIOUS-TIME1");
	    TimeSerieAuxTest.testGetPreviousTime1(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-PREVIOUS-VALUE1");
	    TimeSerieAuxTest.testGetPreviousValue1(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-FIRST-TIME");
	    TimeSerieAuxTest.testGetFirstTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-FIRST-VALUE");
	    TimeSerieAuxTest.testGetFirstValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-FIRST-TIME-VALUE");
	    TimeSerieAuxTest.testGetFirstTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-LAST-TIME");
	    TimeSerieAuxTest.testGetLastTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-LAST-VALUE");
	    TimeSerieAuxTest.testGetLastValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-LAST-TIME-VALUE");
	    TimeSerieAuxTest.testGetLastTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST COPY-TIME-SERIES");
	    TimeSerieAuxTest.testCopyTimeSeries(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SET-ALL-TIMES-TO-ZERO");
	    TimeSerieAuxTest.testSetAllTimesToZero(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SET-ALL-VALUES-TO-ZERO");
	    TimeSerieAuxTest.testSetAllValuesToZero(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SET-ALL-TO-ZERO");
	    TimeSerieAuxTest.testSetAllToZero(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SET-ALL-TIMES-TO-TIME");
	    TimeSerieAuxTest.testSetAllTimesToTime(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SET-ALL-VALUES-TO-VALUE");
	    TimeSerieAuxTest.testSetAllValuesToValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST SET-ALL-TO-TIME-VALUE");
	    TimeSerieAuxTest.testSetAllToTimeValue(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST GET-SUB-TIME-SERIES-IN-TIME-RANGE");
	    TimeSerieAuxTest.testGetSubTimeSeriesInTimeRange(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST COUNT-TIME-VALUES");
	    TimeSerieAuxTest.testCountTimeValues(factory, e);
	    System.out.println("=================================");
	    System.out.println("TEST IS-EMPTY");
	    TimeSerieAuxTest.testIsEmpty(factory, e);
	    System.out.println("TEST WRITE-AND-READ");
	    TimeSerieAuxTest.testWriteAndReadTSFromFile(factory, e);
	    System.out.println("=================================");
	}

		

	public static void testAddOpFilesMethods(EmeliotStandardLibrary e) throws IOException {

        double value = 666.0;
        int time = 666;
        Path pathIN = Paths.get("./proteus-example/io/tsIN.txt");

        
        // Test: addTimeAndValue_File
        Path pathOUT1 = Paths.get("./proteus-example/io/tsOUT_addTimeAndValue.txt");
        e.addTimeAndValue_File(pathIN, pathOUT1, value, time);

        // Test: addRandomTimeAndValue_File
        int minTime = 100;
        int maxTime = 500;
        Path pathOUT2 = Paths.get("./proteus-example/io/tsOUT_addRandomTimeAndValue.txt");
        e.addRandomTimeAndValue_File(pathIN, pathOUT2, value, minTime, maxTime);

        // Test: addTimeAndRandomValue_File
        double minValue = 1.0;
        double maxValue = 10.0;
        Path pathOUT3 = Paths.get("./proteus-example/io/tsOUT_addTimeAndRandomValue.txt");
        e.addTimeAndRandomValue_File(pathIN, pathOUT3, time, minValue, maxValue);

        // Test: addRandomTimeAndRandomValue_File
        Path pathOUT4 = Paths.get("./proteus-example/io/tsOUT_addRandomTimeAndRandomValue.txt");
        e.addRandomTimeAndRandomValue_File(pathIN, pathOUT4, minTime, maxTime, minValue, maxValue);

        // Test: addMultipleTimeValues_File
        List<Integer> times = Arrays.asList(100, 200, 300);
        List<Double> values = Arrays.asList(5.0, 10.0, 15.0);
        Path pathOUT5 = Paths.get("./proteus-example/io/tsOUT_addMultipleTimeValues.txt");
        e.addMultipleTimeValues_File(pathIN, pathOUT5, times, values);

        // Test: addMultipleRandomTimeValues_File
        int count = 5;
        Path pathOUT6 = Paths.get("./proteus-example/io/tsOUT_addMultipleRandomTimeValues.txt");
        e.addMultipleRandomTimeValues_File(pathIN, pathOUT6, minTime, maxTime, minValue, maxValue, count);

        // Test: appendTimeSeries_File
        Path pathAppend = Paths.get("./proteus-example/io/tsAppend.txt");
        Path pathOUT7 = Paths.get("./proteus-example/io/tsOUT_appendTimeSeries.txt");
        e.appendTimeSeries_File(pathIN, pathAppend, pathOUT7);

        // Test: mergeTimeSeries_File
        Path pathMerge = Paths.get("./proteus-example/io/tsMerge.txt");
        Path pathOUT8 = Paths.get("./proteus-example/io/tsOUT_mergeTimeSeries.txt");
        e.mergeTimeSeries_File(pathIN, pathMerge, pathOUT8);

        // Test: replaceTimeSeries_File
        Path pathReplace = Paths.get("./proteus-example/io/tsReplace.txt");
        Path pathOUT9 = Paths.get("./proteus-example/io/tsOUT_replaceTimeSeries.txt");
        e.replaceTimeSeries_File(pathIN, pathReplace, pathOUT9);
        
	}
	
	
	public static void testChangeOpFilesMethods(EmeliotStandardLibrary e) throws IOException {

	    double eps = 0.1;
	    double minDomainValue = 1.0;
	    double maxDomainValue = 10.0;
	    int minDomainTime = 1;
	    int maxDomainTime = 100;
	    Path pathIN = Paths.get("./proteus-example/io/tsIN.txt");

	    double value = 666.0;
	    int time = 3;
	    Path pathOUT1 = Paths.get("./proteus-example/io/tsOUT_changeValueSubtle.txt");
	    e.changeValue_File(pathIN, pathOUT1, value, time);
	    
	    // Test for changeTime_File
	    int timeOld = 5;
	    int timeNew = 10;
	    Path pathOUT2 = Paths.get("./proteus-example/io/tsOUT_changeTime.txt");
	    e.changeTime_File(pathIN, pathOUT2, timeOld, timeNew);

	    // Test for changeValueWithRandom_File
	    Path pathOUT3 = Paths.get("./proteus-example/io/tsOUT_changeValueWithRandom.txt");
	    e.changeValueWithRandom_File(pathIN, pathOUT3, minDomainValue, maxDomainValue, time);

	    // Test for changeTimeWithRandom_File
	    int minTime = 2;
	    int maxTime = 8;
	    Path pathOUT4 = Paths.get("./proteus-example/io/tsOUT_changeTimeWithRandom.txt");
	    e.changeTimeWithRandom_File(pathIN, pathOUT4, minTime, maxTime, time);

	    // Test for changeTimeAndValue_File
	    double newValue = 999.0;
	    Path pathOUT5 = Paths.get("./proteus-example/io/tsOUT_changeTimeAndValue.txt");
	    e.changeTimeAndValue_File(pathIN, pathOUT5, timeOld, timeNew, newValue);

	    // Test for changeTimeWithRandomAndValue_File
	    double valueNew = 123.0;
	    Path pathOUT6 = Paths.get("./proteus-example/io/tsOUT_changeTimeWithRandomAndValue.txt");
	    e.changeTimeWithRandomAndValue_File(pathIN, pathOUT6, timeOld, minTime, maxTime, valueNew);

	    // Test for changeTimeAndValueWithRandom_File
	    Path pathOUT7 = Paths.get("./proteus-example/io/tsOUT_changeTimeAndValueWithRandom.txt");
	    e.changeTimeAndValueWithRandom_File(pathIN, pathOUT7, timeOld, timeNew, minDomainValue, maxDomainValue);

	    // Test for changeTimeWithRandomAndValueWithRandom_File
	    Path pathOUT8 = Paths.get("./proteus-example/io/tsOUT_changeTimeWithRandomAndValueWithRandom.txt");
	    e.changeTimeWithRandomAndValueWithRandom_File(pathIN, pathOUT8, timeOld, minTime, maxTime, minDomainValue, maxDomainValue);

	    // Test for changeARandomTimeValue_File
	    int newTime = 6;
	    Path pathOUT9 = Paths.get("./proteus-example/io/tsOUT_changeARandomTimeValue.txt");
	    e.changeARandomTimeValue_File(pathIN, pathOUT9, newTime, value);

	    // Test for changeMultipleTimeValues_File
	    List<Integer> timesOld = Arrays.asList(1, 4, 7);
	    List<Integer> timesNew = Arrays.asList(2, 5, 8);
	    List<Double> valuesNew = Arrays.asList(100.0, 200.0, 300.0);
	    Path pathOUT10 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeValues.txt");
	    e.changeMultipleTimeValues_File(pathIN, pathOUT10, timesOld, timesNew, valuesNew);

	    // Test for changeMultipleTimeValuesWithRandomTimeValues_File
	    Path pathOUT11 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeValuesWithRandomTimeValues.txt");
	    e.changeMultipleTimeValuesWithRandomTimeValues_File(pathIN, pathOUT11, timesOld, minTime, maxTime, minDomainValue, maxDomainValue);

	    // Test for changeTimeLate_File
	    Path pathOUT12 = Paths.get("./proteus-example/io/tsOUT_changeTimeLate.txt");
	    e.changeTimeLate_File(pathIN, pathOUT12, eps, time, maxDomainTime);

	    // Test for changeRandomTimeLate_File
	    Path pathOUT13 = Paths.get("./proteus-example/io/tsOUT_changeRandomTimeLate.txt");
	    e.changeRandomTimeLate_File(pathIN, pathOUT13, eps, maxDomainTime);

	    // Test for changeMultipleTimeLate_File
	    List<Integer> times = Arrays.asList(1, 3, 5);
	    Path pathOUT14 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeLate.txt");
	    e.changeMultipleTimeLate_File(pathIN, pathOUT14, eps, times, maxDomainTime);

	    // Test for changeTimeEarly_File
	    Path pathOUT15 = Paths.get("./proteus-example/io/tsOUT_changeTimeEarly.txt");
	    e.changeTimeEarly_File(pathIN, pathOUT15, eps, time, minDomainTime);

	    // Test for changeRandomTimeEarly_File
	    Path pathOUT16 = Paths.get("./proteus-example/io/tsOUT_changeRandomTimeEarly.txt");
	    e.changeRandomTimeEarly_File(pathIN, pathOUT16, eps, minDomainTime);

	    // Test for changeMultipleTimeEarly_File
	    Path pathOUT17 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeEarly.txt");
	    e.changeMultipleTimeEarly_File(pathIN, pathOUT17, eps, times, minDomainTime);

	    // Test for changeValueCoarse_File
	    Path pathOUT18 = Paths.get("./proteus-example/io/tsOUT_changeValueCoarse.txt");
	    e.changeValueCoarse_File(pathIN, pathOUT18, eps, minDomainValue, maxDomainValue, time);

	    // Test for changeRandomValueCoarse_File
	    Path pathOUT19 = Paths.get("./proteus-example/io/tsOUT_changeRandomValueCoarse.txt");
	    e.changeRandomValueCoarse_File(pathIN, pathOUT19, eps, minDomainValue, maxDomainValue);

	    // Test for changeMultipleValueCoarse_File
	    Path pathOUT20 = Paths.get("./proteus-example/io/tsOUT_changeMultipleValueCoarse.txt");
	    e.changeMultipleValueCoarse_File(pathIN, pathOUT20, eps, times, minDomainValue, maxDomainValue);

	    // Test for changeValueSubtle_File
	    Path pathOUT21 = Paths.get("./proteus-example/io/tsOUT_changeValueSubtle.txt");
	    e.changeValueSubtle_File(pathIN, pathOUT21, eps, time, minDomainValue, maxDomainValue);

	    // Test for changeRandomValueSubtle_File
	    Path pathOUT22 = Paths.get("./proteus-example/io/tsOUT_changeRandomValueSubtle.txt");
	    e.changeRandomValueSubtle_File(pathIN, pathOUT22, eps, minDomainValue, maxDomainValue);

	    // Test for changeMultipleValueSubtle_File
	    Path pathOUT23 = Paths.get("./proteus-example/io/tsOUT_changeMultipleValueSubtle.txt");
	    e.changeMultipleValueSubtle_File(pathIN, pathOUT23, eps, times, minDomainValue, maxDomainValue);
	}

	
	public static void testRemoveOpFilesMethods(EmeliotStandardLibrary e) throws IOException {

	  
	    Path pathIN = Paths.get("./proteus-example/io/tsIN.txt");

	    // Test: removeTimeValue_File
	    double value = 20.0;
	    int time = 3;
	    Path pathOUT1 = Paths.get("./proteus-example/io/tsOUT_removeTimeValue.txt");
	    e.removeTimeValue_File(pathIN, pathOUT1, time, value);

	    // Test: removeTimeValue_File with TimeValue object
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(3);
		tv.setValue(20);
	    Path pathOUT2 = Paths.get("./proteus-example/io/tsOUT_removeTimeValue_TV.txt");
	    e.removeTimeValue_File(pathIN, pathOUT2, tv);

	    // Test: removeRandomTimeValue_File
	    Path pathOUT3 = Paths.get("./proteus-example/io/tsOUT_removeRandomTimeValue.txt");
	    e.removeRandomTimeValue_File(pathIN, pathOUT3);

	    // Test: removeMultipleTimeValues_File with lists of times and values
	    List<Integer> times = Arrays.asList(3, 5);
	    List<Double> values = Arrays.asList(20.0, 10.0);
	    Path pathOUT4 = Paths.get("./proteus-example/io/tsOUT_removeMultipleTimeValues.txt");
	    e.removeMultipleTimeValues_File(pathIN, pathOUT4, times, values);

	    // Test: removeMultipleTimeValues_File with list of TimeValue objects
		TimeValue tv1 = ReadFactory.eINSTANCE.createTimeValue();
		tv1.setTime(3);
		tv1.setValue(20);
		TimeValue tv2 = ReadFactory.eINSTANCE.createTimeValue();
		tv2.setTime(5);
		tv2.setValue(10);
	    List<TimeValue> timeValues = Arrays.asList(
	        tv1, tv2
	    );
	    Path pathOUT5 = Paths.get("./proteus-example/io/tsOUT_removeMultipleTimeValues_TV.txt");
	    e.removeMultipleTimeValues_File(pathIN, pathOUT5, timeValues);

	    // Test: removeMultipleTimeValues_File with varargs of TimeValue objects
	    Path pathOUT6 = Paths.get("./proteus-example/io/tsOUT_removeMultipleTimeValues_Varargs.txt");
		TimeValue tv3 = ReadFactory.eINSTANCE.createTimeValue();
		tv3.setTime(3);
		tv3.setValue(20);
		TimeValue tv4 = ReadFactory.eINSTANCE.createTimeValue();
		tv4.setTime(5);
		tv4.setValue(10);
	    e.removeMultipleTimeValues_File(pathIN, pathOUT6, tv3, tv4);

	    // Test: removeAllTimeValues_File
	    Path pathOUT7 = Paths.get("./proteus-example/io/tsOUT_removeAllTimeValues.txt");
	    e.removeAllTimeValues_File(pathIN, pathOUT7);

	    // Test: removeTimeValuesBeforeTime_File
	    int timeThreshold = 5;
	    Path pathOUT8 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesBeforeTime.txt");
	    e.removeTimeValuesBeforeTime_File(pathIN, pathOUT8, timeThreshold);

	    // Test: removeTimeValuesAfterTime_File
	    timeThreshold = 5;
	    Path pathOUT9 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesAfterTime.txt");
	    e.removeTimeValuesAfterTime_File(pathIN, pathOUT9, timeThreshold);

	    // Test: removeTimeValuesBelowValue_File
	    double valueThreshold = 20.0;
	    Path pathOUT10 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesBelowValue.txt");
	    e.removeTimeValuesBelowValue_File(pathIN, pathOUT10, valueThreshold);

	    // Test: removeTimeValuesAboveValue_File
	    valueThreshold = 20.0;
	    Path pathOUT11 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesAboveValue.txt");
	    e.removeTimeValuesAboveValue_File(pathIN, pathOUT11, valueThreshold);
	}

	
	
	
	
	
	
	
	
	public static TimeSeries createTimeSerie(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = factory.createTimeSeries();
		int time = 5;
		double value = 10;
		e.addTimeAndValue(ts, value, time);
		time = 3;
		value = 20;
		e.addTimeAndValue(ts, value, time);
		time = 8;
		value = 30;
		e.addTimeAndValue(ts, value, time);
		return ts;
	}
	
	public static TimeSeries createTimeSerieDiff(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = factory.createTimeSeries();
		int time = 4;
		double value = 33;
		e.addTimeAndValue(ts, value, time);
		time = 6;
		value = 44;
		e.addTimeAndValue(ts, value, time);
		time = 9;
		value = 55;
		e.addTimeAndValue(ts, value, time);
		return ts;
	}
	
	public static TimeSeries createTimeSerieEq(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = factory.createTimeSeries();
		int time = 5;
		double value = 10;
		e.addTimeAndValue(ts, value, time);
		time = 3;
		value = 20;
		e.addTimeAndValue(ts, value, time);
		time = 8;
		value = 30;
		e.addTimeAndValue(ts, value, time);
		return ts;
	}
	
	public static TimeSeries createTimeSerieIntersect(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = factory.createTimeSeries();
		int time = 4;
		double value = 33;
		e.addTimeAndValue(ts, value, time);
		time = 3;
		value = 20;
		e.addTimeAndValue(ts, value, time);
		time = 9;
		value = 55;
		e.addTimeAndValue(ts, value, time);
		return ts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
