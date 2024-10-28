package emeliot.dsl.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;


public class TimeSerieEditTest extends MainStdLibraryTest {
	
	public static void testChangeValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		double value = 666;
		e.changeValue(ts, value, time);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTime(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		int timeNew = 666;
		e.changeTime(ts, timeOld, timeNew);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeValueWithRandom(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeValueWithRandom(ts, 1, 100, time);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeWithRandom(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeTimeWithRandom(ts, 1, 100, time);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeAndValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		int timeNew = 666;
		double value = 666; 
		e.changeTimeAndValue(ts, timeOld, timeNew, value);
		for(TimeValue tv: ts.getTimeValues())
			System.out.println(tv.getTime() + " " + tv.getValue());
	}
	
	public static void testChangeTimeWithRandomAndValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		double value = 666; 
		e.changeTimeWithRandomAndValue(ts, timeOld, 1, 100, value);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeAndValueWithRandom(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		int timeNew = 666;
		e.changeTimeAndValueWithRandom(ts, timeOld, timeNew, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeWithRandomAndValueWithRandom(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		e.changeTimeWithRandomAndValueWithRandom(ts, timeOld, 1, 100, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 666;
		double value = 666;
		e.changeARandomTimeValue(ts, time, value);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeARandomTimeValueWithRandomTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeARandomTimeValueWithRandomTimeValue(ts, 1, 100, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeValues(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> timesOld = Arrays.asList(3, 5);
        List<Integer> timesNew= Arrays.asList(111, 222);
        List<Double> valuesNew = new ArrayList<Double>();
		double value = 111;
		valuesNew.add(value);
		double value2 = 222;
		valuesNew.add(value2);
		e.changeMultipleTimeValues(ts, timesOld, timesNew, valuesNew);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeValuesWithRandomTimeValues(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> timesOld = Arrays.asList(3, 5);
		e.changeMultipleTimeValuesWithRandomTimeValues(ts, timesOld, 1, 100, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeLate(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeTimeLate(ts, 0.5, time, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomTimeLate(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomTimeLate(ts, 0.5, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeLate(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> times = Arrays.asList(3, 5);
		e.changeMultipleTimeLate(ts, 0.5, times, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeEarly(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeTimeEarly(ts, 0.5, time, 1);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomTimeEarly(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomTimeEarly(ts, 0.5, 1);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeEarly(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> times = Arrays.asList(3, 5);
		e.changeMultipleTimeEarly(ts, 1, times, 1);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeValueCoarse(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeValueCoarse(ts, 0.5, 1, 100, time);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomValueCoarse(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomValueCoarse(ts, 0.5, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleValueCoarse(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> times = Arrays.asList(3, 5);
		e.changeMultipleValueCoarse(ts, 0.5, times, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeValueSubtle(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeValueSubtle(ts, 0.5, time, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomValueSubtle(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomValueSubtle(ts, 0.5, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleValueSubtle(ReadFactory factory, EmeliotStandardLibrary e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> times = Arrays.asList(3, 5);
		e.changeMultipleValueSubtle(ts, 0.5, times, 1, 100);
		e.printTimeSeries(ts);
	}

	
}
