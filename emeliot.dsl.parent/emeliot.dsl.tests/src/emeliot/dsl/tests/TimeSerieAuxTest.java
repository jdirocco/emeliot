package emeliot.dsl.tests;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;


public class TimeSerieAuxTest extends MainStdLibraryTest{

	

	public static void testReorderTimeSeries(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = 10;
	    e.addTimeAndValue(ts, value, 5);
	    value = 20;
	    e.addTimeAndValue(ts, value, 3);
	    value = 30;
	    e.addTimeAndValue(ts, value, 8);
	    System.out.println("Before reorder:");
		e.printTimeSeries(ts);
	    e.reorderTimeSeries(ts);
	    System.out.println("After reorder:");
		e.printTimeSeries(ts);
	}
	
	public static void testSelectRandomTimeValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = e.selectRandomTimeValue(ts);
	    System.out.println(tv.getTime() + " " + tv.getValue());
	}

	public static void testExistTime(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    System.out.println(e.existTime(ts, 3));
	    System.out.println(e.existTime(ts, 33));
	}
	
	public static void testExistValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = 10;
	    double value1 = 33; 
	    System.out.println(e.existValue(ts, value));
	    System.out.println(e.existValue(ts, value1));
	}
	
	public static void testExistTimeValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = 10;
	    double value1 = 33;
	    System.out.println(e.existTimeValue(ts, 3, value));
	    System.out.println(e.existTimeValue(ts, 33, value1));
	}
	
	public static void testGetAllTimes(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Double> times = e.getAllTimes(ts);
	    for(double t: times)
	    	System.out.println(t);
	}
	
	public static void testGetAllValues(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Double> values = e.getAllValues(ts);
	    for(Double v: values)
	    	System.out.println(v);
	}
	
	public static void testGetTimesInRange(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Double> times = e.getTimesInRange(ts, 3, 5);
	    for(double t: times)
	    	System.out.println(t);
	}
	
	public static void testGetValuesInRange(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Double> values = e.getValuesInRange(ts, 10, 20);
	    for(Double v: values) 
	    	System.out.println(v);
	}
	
	public static void testGetTimeValuesInRange(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<TimeValue> timeValues = e.getTimeValuesInRange(ts, 3, 5, 10, 20);
	    for(TimeValue tv: timeValues)
	    	System.out.println(tv.getTime() + " " + tv.getValue());
	}	
	
	public static void testGetValueAt(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = e.getValueAt(ts, 1);
	    System.out.println(value);
	}
	
	public static void testGetTimeAt(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double time = e.getTimeAt(ts, 1);
	    System.out.println(time);
	}
	
	public static void testGetTimeValueAt(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = e.getTimeValueAt(ts, 1);
    	System.out.println(tv.getTime() + " " + tv.getValue());
	}
	
	public static void testMaxTime(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		double maxTime = e.getMaxTime(ts);
    	System.out.println(maxTime);
	}
	
	public static void testMinTime(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		double minTime = e.getMinTime(ts);
    	System.out.println(minTime);
	}
	
	public static void testMaxValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double maxValue = e.getMaxValue(ts);
    	System.out.println(maxValue);
	}
	
	public static void testMinValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double minValue = e.getMinValue(ts);
    	System.out.println(minValue);
	}
	
	public static void testGetNextTimeValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ((TimeSeriesValue) ts).getTimeValues().get(1);
	    TimeValue nextTv = e.getNextTimeValue(ts, tv);
	    System.out.println(nextTv.getTime() + " " + nextTv.getValue());
	}

	public static void testGetNextTime(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ((TimeSeriesValue) ts).getTimeValues().get(1);
	    double nextTime = e.getNextTime(ts, tv);
	    System.out.println(nextTime);
	}

	public static void testGetNextValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ((TimeSeriesValue) ts).getTimeValues().get(1);
	    double nextValue = e.getNextValue(ts, tv);
	    System.out.println(nextValue);
	}

	public static void testGetNextTimeValue1(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue nextTv = e.getNextTimeValue(ts, 1);
	    System.out.println(nextTv.getTime() + " " + nextTv.getValue());
	}

	public static void testGetNextTime1(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double nextTime = e.getNextTime(ts, 1);
	    System.out.println(nextTime);
	}

	public static void testGetNextValue1(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double nextValue = e.getNextValue(ts, 1);
	    System.out.println(nextValue);
	}
	
	public static void testGetPreviousTimeValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ((TimeSeriesValue) ts).getTimeValues().get(1);
	    TimeValue prevTv = e.getPreviousTimeValue(ts, tv);
	    System.out.println(prevTv.getTime() + " " + prevTv.getValue());
	}

	public static void testGetPreviousTime(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ((TimeSeriesValue) ts).getTimeValues().get(1);
	    double prevTime = e.getPreviousTime(ts, tv);
	    System.out.println(prevTime);
	}

	public static void testGetPreviousValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ((TimeSeriesValue) ts).getTimeValues().get(1);
	    double prevValue = e.getPreviousValue(ts, tv);
	    System.out.println(prevValue);
	}

	public static void testGetPreviousTimeValue1(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue prevTv = e.getPreviousTimeValue(ts, 1);
	    System.out.println(prevTv.getTime() + " " + prevTv.getValue());
	}

	public static void testGetPreviousTime1(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double prevTime = e.getPreviousTime(ts, 1);
	    System.out.println(prevTime);
	}

	public static void testGetPreviousValue1(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double prevValue = e.getPreviousValue(ts, 1);
	    System.out.println(prevValue);
	}
	
	public static void testGetFirstTime(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double firstTime = e.getFirstTime(ts);
	    System.out.println(firstTime);
	}

	public static void testGetFirstValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double firstValue = e.getFirstValue(ts);
	    System.out.println(firstValue); 
	}

	public static void testGetFirstTimeValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue firstTv = e.getFirstTimeValue(ts);
	    System.out.println(firstTv.getTime() + " " + firstTv.getValue());
	}

	public static void testGetLastTime(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double lastTime = e.getLastTime(ts);
	    System.out.println(lastTime); 
	}

	public static void testGetLastValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double lastValue = e.getLastValue(ts);
	    System.out.println(lastValue);
	}

	public static void testGetLastTimeValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue lastTv = e.getLastTimeValue(ts);
	    System.out.println(lastTv.getTime() + " " + lastTv.getValue());
	}

	public static void testCopyTimeSeries(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeSeries tsCopy = e.copyTimeSeries(ts);
	    e.printTimeSeries(tsCopy);
	}

	public static void testSetAllTimesToZero(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllTimesToZero(ts);
	    e.printTimeSeries(ts); 
	}

	public static void testSetAllValuesToZero(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllValuesToZero(ts);
	    e.printTimeSeries(ts);
	}

	public static void testSetAllToZero(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllToZero(ts);
	    e.printTimeSeries(ts);
	}
	
	public static void testSetAllTimesToTime(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllTimesToTime(ts, 111);
	    e.printTimeSeries(ts); 
	}

	public static void testSetAllValuesToValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllValuesToValue(ts, 111);
	    e.printTimeSeries(ts);
	}

	public static void testSetAllToTimeValue(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllToTimeValue(ts, 111, 111);
	    e.printTimeSeries(ts);
	}

	public static void testGetSubTimeSeriesInTimeRange(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeSeries subSeries = e.getSubTimeSeriesInTimeRange(ts, 3, 5);
	    e.printTimeSeries(subSeries);
	}

	public static void testCountTimeValues(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    int count = e.countTimeValues(ts);
	    System.out.println("Number of time values: " + count);
	}

	public static void testIsEmpty(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    boolean isEmpty = e.isEmpty(ts);
	    System.out.println("Is TimeSeries empty: " + isEmpty); 
	    TimeSeries emptyTs = factory.createTimeSeriesValue();
	    isEmpty = e.isEmpty(emptyTs);
	    System.out.println("Is empty TimeSeries empty: " + isEmpty);
	}

	public static void testWriteAndReadTSFromFile(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerie(factory, e);
		try {
			e.writeTSToFile(((TimeSeriesValue) ts), "./proteus-example/io/ts.txt");
			TimeSeries ts1 = e.readTSFromFile("./proteus-example/io/ts.txt");
			e.printTimeSeries(ts1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void testAuxOperatorsFiles(EmeliotLib e) throws IOException {

	    String pathIN = Paths.get("./proteus-example/io/tsIN.txt").toString();

	    // Test: printTimeSeries_File
	    System.out.println("Testing: printTimeSeries_File");
	    e.printTimeSeries_File(pathIN);

	    // Test: reorderTimeSeries_File
	    String pathOUT1 = Paths.get("./proteus-example/io/tsOUTReordered.txt").toString();
	    System.out.println("Testing: reorderTimeSeries_File");
	    e.reorderTimeSeries_File(pathIN, pathOUT1);

	    // Test: selectRandomTimeValue_File
	    System.out.println("Testing: selectRandomTimeValue_File");
	    System.out.println("Result: " + e.selectRandomTimeValue_File(pathIN));

	    // Test: existTime_File
	    System.out.println("Testing: existTime_File with time = 0");
	    System.out.println("Result: " + e.existTime_File(pathIN, 0));
	    System.out.println("Testing: existTime_File with time = 5");
	    System.out.println("Result: " + e.existTime_File(pathIN, 5));

	    // Test: existValue_File
	    System.out.println("Testing: existValue_File with value = 0");
	    System.out.println("Result: " + e.existValue_File(pathIN, 0));
	    System.out.println("Testing: existValue_File with value = 10");
	    System.out.println("Result: " + e.existValue_File(pathIN, 10));

	    // Test: existTimeValue_File
	    System.out.println("Testing: existTimeValue_File with time = 0, value = 0");
	    System.out.println("Result: " + e.existTimeValue_File(pathIN, 0, 0));
	    System.out.println("Testing: existTimeValue_File with time = 3, value = 10");
	    System.out.println("Result: " + e.existTimeValue_File(pathIN, 3, 10));
	    System.out.println("Testing: existTimeValue_File with time = 3, value = 50");
	    System.out.println("Result: " + e.existTimeValue_File(pathIN, 3, 50));
	    System.out.println("Testing: existTimeValue_File with time = 3, value = 20");
	    System.out.println("Result: " + e.existTimeValue_File(pathIN, 3, 20));

	    // Test: getAllTimes_File
	    System.out.println("Testing: getAllTimes_File");
	    System.out.println("Result: " + e.getAllTimes_File(pathIN));

	    // Test: getAllValues_File
	    System.out.println("Testing: getAllValues_File");
	    System.out.println("Result: " + e.getAllValues_File(pathIN));

	    // Test: getTimesInRange_File
	    System.out.println("Testing: getTimesInRange_File with range 3 to 5");
	    System.out.println("Result: " + e.getTimesInRange_File(pathIN, 3, 5));

	    // Test: getValuesInRange_File
	    System.out.println("Testing: getValuesInRange_File with range 10 to 20");
	    System.out.println("Result: " + e.getValuesInRange_File(pathIN, 10, 20));

	    // Test: getTimeValuesInRange_File
	    System.out.println("Testing: getTimeValuesInRange_File with time range 3 to 5, value range 10 to 50");
	    System.out.println("Result: " + e.getTimeValuesInRange_File(pathIN, 3, 5, 10, 50));

	    // Test: getValueAt_File
	    System.out.println("Testing: getValueAt_File at index 2");
	    System.out.println("Result: " + e.getValueAt_File(pathIN, 2));

	    // Test: getTimeAt_File
	    System.out.println("Testing: getTimeAt_File at index 2");
	    System.out.println("Result: " + e.getTimeAt_File(pathIN, 2));

	    // Test: getTimeValueAt_File
	    System.out.println("Testing: getTimeValueAt_File at index 2");
	    System.out.println("Result: " + e.getTimeValueAt_File(pathIN, 2));

	    // Test: getMaxTime_File
	    System.out.println("Testing: getMaxTime_File");
	    System.out.println("Result: " + e.getMaxTime_File(pathIN));

	    // Test: getMinTime_File
	    System.out.println("Testing: getMinTime_File");
	    System.out.println("Result: " + e.getMinTime_File(pathIN));

	    // Test: getMaxValue_File
	    System.out.println("Testing: getMaxValue_File");
	    System.out.println("Result: " + e.getMaxValue_File(pathIN));

	    // Test: getMinValue_File
	    System.out.println("Testing: getMinValue_File");
	    System.out.println("Result: " + e.getMinValue_File(pathIN));

	    // Test: getNextTimeValue
	    System.out.println("Testing: getNextTimeValue_File for time = 1");
	    System.out.println("Result: " + e.getNextTimeValue_File(pathIN, 1));

	    // Test: getNextTime
	    System.out.println("Testing: getNextTime_File for time = 1");
	    System.out.println("Result: " + e.getNextTime_File(pathIN, 1));

	    // Test: getNextValue
	    System.out.println("Testing: getNextValue_File for time = 1");
	    System.out.println("Result: " + e.getNextValue_File(pathIN, 1));

	    // Test: getPreviousTimeValue_File
	    System.out.println("Testing: getPreviousTimeValue_File for time = 1");
	    System.out.println("Result: " + e.getPreviousTimeValue_File(pathIN, 1));

	    // Test: getPreviousTime_File
	    System.out.println("Testing: getPreviousTime_File for time = 1");
	    System.out.println("Result: " + e.getPreviousTime_File(pathIN, 1));

	    // Test: getPreviousValue_File
	    System.out.println("Testing: getPreviousValue_File for time = 1");
	    System.out.println("Result: " + e.getPreviousValue_File(pathIN, 1));

	    // Test: getFirstTime_File
	    System.out.println("Testing: getFirstTime_File");
	    System.out.println("Result: " + e.getFirstTime_File(pathIN));

	    // Test: getFirstValue_File
	    System.out.println("Testing: getFirstValue_File");
	    System.out.println("Result: " + e.getFirstValue_File(pathIN));

	    // Test: getFirstTimeValue_File
	    System.out.println("Testing: getFirstTimeValue_File");
	    System.out.println("Result: " + e.getFirstTimeValue_File(pathIN));

	    // Test: getLastTime_File
	    System.out.println("Testing: getLastTime_File");
	    System.out.println("Result: " + e.getLastTime_File(pathIN));

	    // Test: getLastValue_File
	    System.out.println("Testing: getLastValue_File");
	    System.out.println("Result: " + e.getLastValue_File(pathIN));

	    // Test: getLastTimeValue_File
	    System.out.println("Testing: getLastTimeValue_File");
	    System.out.println("Result: " + e.getLastTimeValue_File(pathIN));

	    // Test: copyTimeSeries_File
	    System.out.println("Testing: copyTimeSeries_File");
	    e.printTimeSeries(e.copyTimeSeries_File(pathIN));

	    // Test: setAllTimesToZero_File
	    String pathOUT2 = Paths.get("./proteus-example/io/tsOUTAllTimesToZero.txt").toString();
	    System.out.println("Testing: setAllTimesToZero_File");
	    e.setAllTimesToZero_File(pathIN, pathOUT2);

	    // Test: setAllValuesToZero_File
	    String pathOUT3 = Paths.get("./proteus-example/io/tsOUTAllValuesToZero.txt").toString();
	    System.out.println("Testing: setAllValuesToZero_File");
	    e.setAllValuesToZero_File(pathIN, pathOUT3);

	    // Test: setAllToZero_File
	    String pathOUT4 = Paths.get("./proteus-example/io/tsOUTAllToZero.txt").toString();
	    System.out.println("Testing: setAllToZero_File");
	    e.setAllToZero_File(pathIN, pathOUT4);

	    // Test: setAllTimesToTime_File
	    String pathOUT5 = Paths.get("./proteus-example/io/tsOUTAllTimesToTime.txt").toString();
	    System.out.println("Testing: setAllTimesToTime_File with time = 33");
	    e.setAllTimesToTime_File(pathIN, pathOUT5, 33);

	    // Test: setAllValuesToValue_File
	    String pathOUT6 = Paths.get("./proteus-example/io/tsOUTAllValuesToValue.txt").toString();
	    System.out.println("Testing: setAllValuesToValue_File with value = 33");
	    e.setAllValuesToValue_File(pathIN, pathOUT6, 33);

	    // Test: setAllToTimeValue_File
	    String pathOUT7 = Paths.get("./proteus-example/io/tsOUTAllToTimeValue.txt").toString();
	    System.out.println("Testing: setAllToTimeValue_File with time = 33, value = 33");
	    e.setAllToTimeValue_File(pathIN, pathOUT7, 33, 33);

	    // Test: getSubTimeSeriesInTimeRange_File
	    System.out.println("Testing: getSubTimeSeriesInTimeRange_File with time range 3 to 5");
	    e.printTimeSeries(e.getSubTimeSeriesInTimeRange_File(pathIN, 3, 5));

	    // Test: countTimeValues_File
	    System.out.println("Testing: countTimeValues_File");
	    System.out.println("Result: " + e.countTimeValues_File(pathIN));

	    // Test: isEmpty_File
	    System.out.println("Testing: isEmpty_File");
	    System.out.println("Result: " + e.isEmpty_File(pathIN));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
}
