package emeliot.dsl.tests;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;



public class TimeSerieRemoveTest extends MainStdLibraryTest {

	public static void testRemoveTimeValue1(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 10; 
        e.removeTimeValue(ts, 5, value);
        e.printTimeSeries(ts);
    }
	
	public static void testRemoveTimeValue2(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 10;
        TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
        tv.setTime(5);
        tv.setValue(value);
        e.removeTimeValue(ts, tv);
        e.printTimeSeries(ts);
    }

    public static void testRemoveRandomTimeValue(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeRandomTimeValue(ts);
        e.printTimeSeries(ts);
    }

    public static void testRemoveMultipleTimeValues1(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        List<Double> times = Arrays.asList(5.0, 3.0);
        double value1 = 10;
        double value2 = 20;
        List<Double> values = new ArrayList<Double>();
        values.add(value1);
        values.add(value2);
        e.removeMultipleTimeValues(ts, times, values);
        e.printTimeSeries(ts);
    }
    
    public static void testRemoveMultipleTimeValues2(ReadFactory factory, EmeliotLib e) {
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
    
    public static void testRemoveMultipleTimeValues3(ReadFactory factory, EmeliotLib e) {
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
    
    public static void testRemoveAllTimeValues(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeAllTimeValues(ts);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesBeforeTime(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeTimeValuesBeforeTime(ts, 4);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesAfterTime(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.removeTimeValuesAfterTime(ts, 4);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesBelowValue(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 25;
        e.removeTimeValuesBelowValue(ts, value);
        e.printTimeSeries(ts);
    }

    public static void testRemoveTimeValuesAboveValue(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 25;
        e.removeTimeValuesAboveValue(ts, value);
        e.printTimeSeries(ts);
    }
    
    public static void testRemoveOperatorsFiles(EmeliotLib e) throws IOException {

	    String pathIN = Paths.get("./proteus-example/io/tsIN.txt").toString();

	    // Test: removeTimeValue_File
	    double value = 20.0;
	    double time = 3;
	    String pathOUT1 = Paths.get("./proteus-example/io/tsOUT_removeTimeValue.txt").toString();
	    e.removeTimeValue_File(pathIN, pathOUT1, time, value);

	    // Test: removeTimeValue_File with TimeValue object
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(3);
		tv.setValue(20);
	    String pathOUT2 = Paths.get("./proteus-example/io/tsOUT_removeTimeValue_TV.txt").toString();
	    e.removeTimeValue_File(pathIN, pathOUT2, tv);

	    // Test: removeRandomTimeValue_File
	    String pathOUT3 = Paths.get("./proteus-example/io/tsOUT_removeRandomTimeValue.txt").toString();
	    e.removeRandomTimeValue_File(pathIN, pathOUT3);

	    // Test: removeMultipleTimeValues_File with lists of times and values
	    List<Double> times = Arrays.asList(3.0, 5.0);
	    List<Double> values = Arrays.asList(20.0, 10.0);
	    String pathOUT4 = Paths.get("./proteus-example/io/tsOUT_removeMultipleTimeValues.txt").toString();
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
	    String pathOUT5 = Paths.get("./proteus-example/io/tsOUT_removeMultipleTimeValues_TV.txt").toString();
	    e.removeMultipleTimeValues_File(pathIN, pathOUT5, timeValues);

	    // Test: removeMultipleTimeValues_File with varargs of TimeValue objects
	    String pathOUT6 = Paths.get("./proteus-example/io/tsOUT_removeMultipleTimeValues_Varargs.txt").toString();
		TimeValue tv3 = ReadFactory.eINSTANCE.createTimeValue();
		tv3.setTime(3);
		tv3.setValue(20);
		TimeValue tv4 = ReadFactory.eINSTANCE.createTimeValue();
		tv4.setTime(5);
		tv4.setValue(10);
	    e.removeMultipleTimeValues_File(pathIN, pathOUT6, tv3, tv4);

	    // Test: removeAllTimeValues_File
	    String pathOUT7 = Paths.get("./proteus-example/io/tsOUT_removeAllTimeValues.txt").toString();
	    e.removeAllTimeValues_File(pathIN, pathOUT7);

	    // Test: removeTimeValuesBeforeTime_File
	    int timeThreshold = 5;
	    String pathOUT8 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesBeforeTime.txt").toString();
	    e.removeTimeValuesBeforeTime_File(pathIN, pathOUT8, timeThreshold);

	    // Test: removeTimeValuesAfterTime_File
	    timeThreshold = 5;
	    String pathOUT9 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesAfterTime.txt").toString();
	    e.removeTimeValuesAfterTime_File(pathIN, pathOUT9, timeThreshold);

	    // Test: removeTimeValuesBelowValue_File
	    double valueThreshold = 20.0;
	    String pathOUT10 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesBelowValue.txt").toString();
	    e.removeTimeValuesBelowValue_File(pathIN, pathOUT10, valueThreshold);

	    // Test: removeTimeValuesAboveValue_File
	    valueThreshold = 20.0;
	    String pathOUT11 = Paths.get("./proteus-example/io/tsOUT_removeTimeValuesAboveValue.txt").toString();
	    e.removeTimeValuesAboveValue_File(pathIN, pathOUT11, valueThreshold);
	}

}
