package emeliot.dsl.tests;

import java.io.IOException;
import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.lib.ProteusService;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;


public class MainStdLibraryTest {

	
	public static void main(String[] args) throws IOException {
		//EmeliotModelManager em = new EmeliotModelManager();
		//em.loadEcoreFile("..\\emeliot.dsl\\model\\generated\\Emeliot.ecore");
		//EmeliotLib e = new ProteusService(em);
		ReadFactory factory = ReadFactory.eINSTANCE;
		EmeliotLib e = new ProteusService();
		testAddOperators(factory, e);
		testEditOperators(factory, e);
		testRemoveOperators(factory, e);
		testAuxOperators(factory, e);
		testAddOperatorsFiles(e);
		testChangeOperatorsFiles(e);
		testRemoveOperatorsFiles(e);
		//TODO: test discovery methods
	}

	
	

	

	


	public static void testAddOperators(ReadFactory factory, EmeliotLib e) {
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
	
	
	
	public static void testAddOperatorsFiles(EmeliotLib e) throws IOException {
		TimeSerieAddTest.testAddOperatorsFiles(e);
	}
	
	
	
	public static void testEditOperators(ReadFactory factory, EmeliotLib e) {
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
	
	
	
	public static void testChangeOperatorsFiles(EmeliotLib e) throws IOException {
		TimeSerieEditTest.testChangeOperatorsFiles(e);
	}
	
	
	
	public static void testRemoveOperators(ReadFactory factory, EmeliotLib e) {
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
	
	
	
	public static void testRemoveOperatorsFiles(EmeliotLib e) throws IOException {
		TimeSerieRemoveTest.testRemoveOperatorsFiles(e);
	}
	
	
	
	public static void testAuxOperators(ReadFactory factory, EmeliotLib e) {
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
	    System.out.println("=================================");
	    System.out.println("TEST WRITE-AND-READ");
	    TimeSerieAuxTest.testWriteAndReadTSFromFile(factory, e);
	    System.out.println("=================================");
	}

	
	
	public static TimeSeries createTimeSerie(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = factory.createTimeSeriesValue();
		int time = 5;
		double value = 10;
		e.addTimeAndValue(ts, time, value);
		time = 3;
		value = 20;
		e.addTimeAndValue(ts, time, value);
		time = 8;
		value = 30;
		e.addTimeAndValue(ts, time, value);
		return ts;
	}
	
	
	
	public static TimeSeries createTimeSerieDiff(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = factory.createTimeSeriesValue();
		int time = 4;
		double value = 33;
		e.addTimeAndValue(ts, time, value);
		time = 6;
		value = 44;
		e.addTimeAndValue(ts, time, value);
		time = 9;
		value = 55;
		e.addTimeAndValue(ts, time, value);
		return ts;
	}
	
	
	
	public static TimeSeries createTimeSerieEq(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = factory.createTimeSeriesValue();
		int time = 5;
		double value = 10;
		e.addTimeAndValue(ts, time, value);
		time = 3;
		value = 20;
		e.addTimeAndValue(ts, time, value);
		time = 8;
		value = 30;
		e.addTimeAndValue(ts, time, value);
		return ts;
	}
	
	
	
	public static TimeSeries createTimeSerieIntersect(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = factory.createTimeSeriesValue();
		int time = 4;
		double value = 33;
		e.addTimeAndValue(ts, time, value);
		time = 3;
		value = 20;
		e.addTimeAndValue(ts, time, value);
		time = 9;
		value = 55;
		e.addTimeAndValue(ts, time, value);
		return ts;
	}
	
	
	
	
	
	
	
}
