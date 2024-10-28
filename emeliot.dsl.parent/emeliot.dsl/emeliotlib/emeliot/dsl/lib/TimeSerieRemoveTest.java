package emeliot.dsl.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;



public class TimeSerieRemoveTest extends MainStdLibraryTest {

	public static void testRemoveTimeValue1(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 10; 
        e.removeTimeValue(ts, 5, value);
        e.printTimeSeries(ts);
    }
	
	public static void testRemoveTimeValue2(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 10;
        TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
        tv.setTime(5);
        tv.setValue(value);
        e.removeTimeValue(ts, tv);
        e.printTimeSeries(ts);
    }

    public static void testRemoveRandomTimeValue(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeRandomTimeValue(ts);
        e.printTimeSeries(ts);
    }

    public static void testRemoveMultipleTimeValues1(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        List<Integer> times = Arrays.asList(5, 3);
        double value1 = 10;
        double value2 = 20;
        List<Double> values = new ArrayList<Double>();
        values.add(value1);
        values.add(value2);
        e.removeMultipleTimeValues(ts, times, values);
        e.printTimeSeries(ts);
    }
    
    public static void testRemoveMultipleTimeValues2(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        List<TimeValue> tvs = new ArrayList<TimeValue>();
        TimeValue tv1 = ReadFactory.eINSTANCE.createTimeValue();
        double value1 = 10;
        tv1.setTime(5);
        tv1.setValue(value1);
        TimeValue tv2 = ReadFactory.eINSTANCE.createTimeValue();
        double value2 = 20;
        tv2.setTime(3);
        tv2.setValue(value2);
        tvs.add(tv1);
        tvs.add(tv2);
        e.removeMultipleTimeValues(ts, tvs);
        e.printTimeSeries(ts);
    }
    
    public static void testRemoveMultipleTimeValues3(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        TimeValue tv1 = ReadFactory.eINSTANCE.createTimeValue();
        double value1 = 10;
        tv1.setTime(5);
        tv1.setValue(value1);
        TimeValue tv2 = ReadFactory.eINSTANCE.createTimeValue();
        double value2 = 20;
        tv2.setTime(3);
        tv2.setValue(value2);
        e.removeMultipleTimeValues(ts, tv1, tv2);
        e.printTimeSeries(ts);
    }
    
    public static void testRemoveAllTimeValues(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeAllTimeValues(ts);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesBeforeTime(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeTimeValuesBeforeTime(ts, 4);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesAfterTime(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeTimeValuesAfterTime(ts, 4);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesBelowValue(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 25;
        e.removeTimeValuesBelowValue(ts, value);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesAboveValue(ReadFactory factory, EmeliotStandardLibrary e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 25;
        e.removeTimeValuesAboveValue(ts, value);
        e.printTimeSeries(ts);
    }

}
