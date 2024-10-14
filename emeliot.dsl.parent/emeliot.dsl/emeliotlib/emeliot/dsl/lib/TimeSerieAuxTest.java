package emeliot.dsl.lib;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;


public class TimeSerieAuxTest extends MainStdLibraryTest{

	

	public static void testReorderTimeSeries(ReadFactory factory, EmeliotStandardLibrary e) {
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
	
	public static void testSelectRandomTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = e.selectRandomTimeValue(ts);
	    System.out.println(tv.getTime() + " " + tv.getValue());
	}

	public static void testExistTime(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    System.out.println(e.existTime(ts, 3));
	    System.out.println(e.existTime(ts, 33));
	}
	
	public static void testExistValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = 10;
	    double value1 = 33; 
	    System.out.println(e.existValue(ts, value));
	    System.out.println(e.existValue(ts, value1));
	}
	
	public static void testExistTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = 10;
	    double value1 = 33;
	    System.out.println(e.existTimeValue(ts, 3, value));
	    System.out.println(e.existTimeValue(ts, 33, value1));
	}
	
	public static void testGetAllTimes(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Integer> times = e.getAllTimes(ts);
	    for(int t: times)
	    	System.out.println(t);
	}
	
	public static void testGetAllValues(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Double> values = e.getAllValues(ts);
	    for(Double v: values)
	    	System.out.println(v);
	}
	
	public static void testGetTimesInRange(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Integer> times = e.getTimesInRange(ts, 3, 5);
	    for(int t: times)
	    	System.out.println(t);
	}
	
	public static void testGetValuesInRange(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<Double> values = e.getValuesInRange(ts, 10, 20);
	    for(Double v: values) 
	    	System.out.println(v);
	}
	
	public static void testGetTimeValuesInRange(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    List<TimeValue> timeValues = e.getTimeValuesInRange(ts, 3, 5, 10, 20);
	    for(TimeValue tv: timeValues)
	    	System.out.println(tv.getTime() + " " + tv.getValue());
	}	
	
	public static void testGetValueAt(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double value = e.getValueAt(ts, 1);
	    System.out.println(value);
	}
	
	public static void testGetTimeAt(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    int time = e.getTimeAt(ts, 1);
	    System.out.println(time);
	}
	
	public static void testGetTimeValueAt(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = e.getTimeValueAt(ts, 1);
    	System.out.println(tv.getTime() + " " + tv.getValue());
	}
	
	public static void testMaxTime(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    int maxTime = e.getMaxTime(ts);
    	System.out.println(maxTime);
	}
	
	public static void testMinTime(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    int minTime = e.getMinTime(ts);
    	System.out.println(minTime);
	}
	
	public static void testMaxValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double maxValue = e.getMaxValue(ts);
    	System.out.println(maxValue);
	}
	
	public static void testMinValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
	    double minValue = e.getMinValue(ts);
    	System.out.println(minValue);
	}
	
	public static void testGetNextTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ts.getTimeValues().get(1);
	    TimeValue nextTv = e.getNextTimeValue(ts, tv);
	    System.out.println(nextTv.getTime() + " " + nextTv.getValue());
	}

	public static void testGetNextTime(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ts.getTimeValues().get(1);
	    int nextTime = e.getNextTime(ts, tv);
	    System.out.println(nextTime);
	}

	public static void testGetNextValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ts.getTimeValues().get(1);
	    double nextValue = e.getNextValue(ts, tv);
	    System.out.println(nextValue);
	}

	public static void testGetNextTimeValue1(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue nextTv = e.getNextTimeValue(ts, 1);
	    System.out.println(nextTv.getTime() + " " + nextTv.getValue());
	}

	public static void testGetNextTime1(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    int nextTime = e.getNextTime(ts, 1);
	    System.out.println(nextTime);
	}

	public static void testGetNextValue1(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double nextValue = e.getNextValue(ts, 1);
	    System.out.println(nextValue);
	}
	
	public static void testGetPreviousTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ts.getTimeValues().get(1);
	    TimeValue prevTv = e.getPreviousTimeValue(ts, tv);
	    System.out.println(prevTv.getTime() + " " + prevTv.getValue());
	}

	public static void testGetPreviousTime(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ts.getTimeValues().get(1);
	    int prevTime = e.getPreviousTime(ts, tv);
	    System.out.println(prevTime);
	}

	public static void testGetPreviousValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue tv = ts.getTimeValues().get(1);
	    double prevValue = e.getPreviousValue(ts, tv);
	    System.out.println(prevValue);
	}

	public static void testGetPreviousTimeValue1(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue prevTv = e.getPreviousTimeValue(ts, 1);
	    System.out.println(prevTv.getTime() + " " + prevTv.getValue());
	}

	public static void testGetPreviousTime1(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    int prevTime = e.getPreviousTime(ts, 1);
	    System.out.println(prevTime);
	}

	public static void testGetPreviousValue1(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double prevValue = e.getPreviousValue(ts, 1);
	    System.out.println(prevValue);
	}
	
	public static void testGetFirstTime(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    int firstTime = e.getFirstTime(ts);
	    System.out.println(firstTime);
	}

	public static void testGetFirstValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double firstValue = e.getFirstValue(ts);
	    System.out.println(firstValue); 
	}

	public static void testGetFirstTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue firstTv = e.getFirstTimeValue(ts);
	    System.out.println(firstTv.getTime() + " " + firstTv.getValue());
	}

	public static void testGetLastTime(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    int lastTime = e.getLastTime(ts);
	    System.out.println(lastTime); 
	}

	public static void testGetLastValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    double lastValue = e.getLastValue(ts);
	    System.out.println(lastValue);
	}

	public static void testGetLastTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeValue lastTv = e.getLastTimeValue(ts);
	    System.out.println(lastTv.getTime() + " " + lastTv.getValue());
	}

	public static void testCopyTimeSeries(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeSeries tsCopy = e.copyTimeSeries(ts);
	    e.printTimeSeries(tsCopy);
	}

	public static void testSetAllTimesToZero(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllTimesToZero(ts);
	    e.printTimeSeries(ts); 
	}

	public static void testSetAllValuesToZero(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllValuesToZero(ts);
	    e.printTimeSeries(ts);
	}

	public static void testSetAllToZero(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllToZero(ts);
	    e.printTimeSeries(ts);
	}
	
	public static void testSetAllTimesToTime(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllTimesToTime(ts, 111);
	    e.printTimeSeries(ts); 
	}

	public static void testSetAllValuesToValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllValuesToValue(ts, 111);
	    e.printTimeSeries(ts);
	}

	public static void testSetAllToTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    e.setAllToTimeValue(ts, 111, 111);
	    e.printTimeSeries(ts);
	}

	public static void testGetSubTimeSeriesInTimeRange(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    TimeSeries subSeries = e.getSubTimeSeriesInTimeRange(ts, 3, 5);
	    e.printTimeSeries(subSeries);
	}

	public static void testCountTimeValues(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    int count = e.countTimeValues(ts);
	    System.out.println("Number of time values: " + count);
	}

	public static void testIsEmpty(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
	    boolean isEmpty = e.isEmpty(ts);
	    System.out.println("Is TimeSeries empty: " + isEmpty); 
	    TimeSeries emptyTs = factory.createTimeSeries();
	    isEmpty = e.isEmpty(emptyTs);
	    System.out.println("Is empty TimeSeries empty: " + isEmpty);
	}

	public static void testWriteAndReadTSFromFile(ReadFactory factory, EmeliotStandardLibrary e) {
	    TimeSeries ts = createTimeSerie(factory, e);
		try {
			//e.writeTSToFile(ts, Paths.get(".\\emeliot.dsl\\emeliotlib\\proteus-example\\io\\ts.txt"));
			//TimeSeries ts1 = e.readTSFromFile(Paths.get(".\\emeliot.dsl\\emeliotlib\\proteus-example\\io\\ts.txt"));
			e.writeTSToFile(ts, Paths.get("./proteus-example/io/ts.txt"));
			TimeSeries ts1 = e.readTSFromFile(Paths.get("./proteus-example/io/ts.txt"));
			e.printTimeSeries(ts1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
		
	
}
