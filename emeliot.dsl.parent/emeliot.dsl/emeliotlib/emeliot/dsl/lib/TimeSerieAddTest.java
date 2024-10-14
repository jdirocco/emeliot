package emeliot.dsl.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

public class TimeSerieAddTest extends MainStdLibraryTest {

	
	
	public static void testAddTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 666;
		double value = 666;
		e.addTimeAndValue(ts, value, time);
		e.printTimeSeries(ts);
	}
	
    public static void testAddRandomTimeAndValue(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 666;
        e.addRandomTimeAndValue(ts, value, 1, 100);
		e.printTimeSeries(ts);
    }

    public static void testAddTimeAndRandomValue(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        int time = 666;
        e.addTimeAndRandomValue(ts, time, 1, 100);
		e.printTimeSeries(ts);
    }

    public static void testAddRandomTimeAndRandomValue(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.addRandomTimeAndRandomValue(ts, 1, 100, 1, 100);
		e.printTimeSeries(ts);
    }

    public static void testAddMultipleTimeValues(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> times = Arrays.asList(111, 222, 333);
        List<Double> values = new ArrayList<Double>();
		double value1 = 111; 
		double value2 = 222;
		double value3 = 333;
		values.add(value1);
		values.add(value2);
		values.add(value3);
        e.addMultipleTimeValues(ts, times, values);
		e.printTimeSeries(ts);
    }
    
    public static void testAddMultipleRandomTimeValues(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.addMultipleRandomTimeValues(ts, 1, 100, 1, 100, 5);
        for (TimeValue tv: ts.getTimeValues())
            System.out.println(tv.getTime() + " " + tv.getValue());
    }
    
    public static void testAppendTimeSeries(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        TimeSeries ts1 = createTimeSerieDiff(factory, e);
        TimeSeries ts2 = createTimeSerieEq(factory, e);
        TimeSeries ts3 = createTimeSerieIntersect(factory, e);
        e.appendTimeSeries(ts, ts1);
		e.printTimeSeries(ts);
		System.out.println("=================================");
        ts = createTimeSerie(factory, e);
        e.appendTimeSeries(ts, ts2);
		e.printTimeSeries(ts);
		System.out.println("=================================");
        ts = createTimeSerie(factory, e);
        e.appendTimeSeries(ts, ts3);
		e.printTimeSeries(ts);
    }
    
    public static void testMergeTimeSeries(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        TimeSeries ts1 = createTimeSerieDiff(factory, e);
        TimeSeries ts2 = createTimeSerieEq(factory, e);
        TimeSeries ts3 = createTimeSerieIntersect(factory, e);
        e.mergeTimeSeries(ts, ts1);
		e.printTimeSeries(ts);
		System.out.println("=================================");
        ts = createTimeSerie(factory, e);
        e.mergeTimeSeries(ts, ts2);
		e.printTimeSeries(ts);
		System.out.println("=================================");
        ts = createTimeSerie(factory, e);
        e.mergeTimeSeries(ts, ts3);
		e.printTimeSeries(ts);
    }
    
    public static void testReplaceTimeSeries(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        TimeSeries ts1 = createTimeSerieDiff(factory, e);
        e.replaceTimeSeries(ts, ts1);
		e.printTimeSeries(ts);
    }
    
    
   
    
   
	
}

