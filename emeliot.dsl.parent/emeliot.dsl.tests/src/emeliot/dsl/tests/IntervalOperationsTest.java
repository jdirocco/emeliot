package emeliot.dsl.tests;

import java.util.List;

import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

public class IntervalOperationsTest extends MainStdLibraryTest{

	
	public static void testGetIntervalsInTimeseries(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerieWitIntervals(factory, e);
		List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testCountIntervalsInTimeseries(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerieWitIntervals(factory, e);
		int intervalsNum = e.countIntervalsInTimeseries(ts);
		System.out.println("Num intervals=" + intervalsNum);
	}
	
	
	public static void testGetIntervalsWithValueInTimeseries(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    List<List<TimeValue>> intervals = e.getIntervalsWithValueInTimeseries(ts, 10);
	    for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Found interval:");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testGetIntervalAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    List<TimeValue> interval = e.getIntervalAt(ts, 3);
	    System.out.println("Interval " + 3 + ":");
	    for (TimeValue tv : interval) {
	        System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	    }
	}
	
	public static void testGetFirstTimeInInterval(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    List<TimeValue> interval = e.getIntervalAt(ts, 2);
	    double time = e.getFirstTimeInInterval(interval);
	    System.out.println("First time in interval " + 2 + " = " + time);
	}
	
	public static void testGetLastTimeInInterval(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    List<TimeValue> interval = e.getIntervalAt(ts, 3);
	    double time = e.getLastTimeInInterval(interval);
	    System.out.println("Last time in interval " + 3 + " = " + time);
	}
	
	public static void testGetValueInInterval(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    List<TimeValue> interval = e.getIntervalAt(ts, 2);
	    double value = e.getValueInInterval(interval);
	    System.out.println("Value in interval " + 2 + " = " + value);
	}
	
	public static void testGetTimeValueAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    List<TimeValue> interval = e.getIntervalAt(ts, 3);
	    TimeValue tv = e.getTimeValueAt(interval, 1);
	    System.out.println("TimeValue at position " + 1 + " in interval " + 3);
	    System.out.println("time=" + tv.getTime() + " value=" + tv.getValue());
	}
	
	public static void testAddIntervalToTimeseries(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.addIntervalToTimeseries(ts, 100, 200, 666);
	    System.out.println("Interval after adding interval:");
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testRemoveIntervalAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.removeIntervalAt(ts, 2);
	    System.out.println("Timeseries after removing interval " + 2);
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testRemoveRandomIntervalFromTimeseries(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.removeRandomIntervalFromTimeseries(ts);
	    System.out.println("Timeseries after removing random interval:");
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testRemoveFirstTimeValueFromIntervalAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.removeFirstTimeValueFromIntervalAt(ts, 1);
	    System.out.println("Timeseries after removing first value from interval " + 1);
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testRemoveLastTimeValueFromIntervalAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.removeLastTimeValueFromIntervalAt(ts, 3);
	    System.out.println("Timeseries after removing last value from interval " + 3);
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testChangeFirstValueFromIntervalAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.changeFirstValueFromIntervalAt(ts, 666, 1);
	    System.out.println("Timeseries after changing first value of interval " + 1);
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	public static void testChangeLastValueFromIntervalAt(ReadFactory factory, EmeliotLib e) {
	    TimeSeries ts = createTimeSerieWitIntervals(factory, e);
	    e.changeLastValueFromIntervalAt(ts, 666, 3);
	    System.out.println("Timeseries after changing last value of interval " + 3);
	    List<List<TimeValue>> intervals = e.getIntervalsInTimeseries(ts);
		for (int i = 0; i < intervals.size(); i++) {
	        System.out.println("Interval " + (i+1) + ":");
	        for (TimeValue tv : intervals.get(i)) {
	            System.out.println("  time=" + tv.getTime() + " value=" + tv.getValue());
	        }
	    }
	}
	
	
	
}
