package emeliot.dsl.tests;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;


public class TimeSerieAddTest extends MainStdLibraryTest {

	
	public static void testAddTimeValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 666;
		double value = 666;
		e.addTimeAndValue(ts, time, value);
		e.printTimeSeries(ts);
	}
	
    public static void testAddRandomTimeAndValue(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        double value = 666;
        e.addRandomTimeAndValue(ts, 1, 100, value);
		e.printTimeSeries(ts);
    }

    public static void testAddTimeAndRandomValue(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        int time = 666;
        e.addTimeAndRandomValue(ts, time, 1, 100);
		e.printTimeSeries(ts);
    }

    public static void testAddRandomTimeAndRandomValue(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.addRandomTimeAndRandomValue(ts, 1, 100, 1, 100);
		e.printTimeSeries(ts);
    }

    public static void testAddMultipleTimeValues(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        List<Double> times = Arrays.asList(111.0, 222.0, 333.0);
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
    
    public static void testAddMultipleRandomTimeValues(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        e.addMultipleRandomTimeValues(ts, 1, 100, 1, 100, 5);
        e.printTimeSeries(ts);
    }
    
    public static void testAppendTimeSeries(ReadFactory factory, EmeliotLib e) {
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
    
    public static void testMergeTimeSeries(ReadFactory factory, EmeliotLib e) {
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
    
    public static void testReplaceTimeSeries(ReadFactory factory, EmeliotLib e) {
        TimeSeries ts = createTimeSerie(factory, e);
        TimeSeries ts1 = createTimeSerieDiff(factory, e);
        e.replaceTimeSeries(ts, ts1);
		e.printTimeSeries(ts);
    }
    
    
    
    public static void testAddOperatorsFiles(EmeliotLib e) throws IOException {

        double value = 666.0;
        double time = 666;
        String pathIN = Paths.get("./proteus-example/io/tsIN.txt").toString();

        // Test: addTimeAndValue_File
        String pathOUT1 = Paths.get("./proteus-example/io/tsOUT_addTimeAndValue.txt").toString();
        e.addTimeAndValue_File(pathIN, pathOUT1, time, value);

        // Test: addRandomTimeAndValue_File
        double minTime = 100;
        double maxTime = 500;
        String pathOUT2 = Paths.get("./proteus-example/io/tsOUT_addRandomTimeAndValue.txt").toString();
        e.addRandomTimeAndValue_File(pathIN, pathOUT2, minTime, maxTime, value);

        // Test: addTimeAndRandomValue_File
        double minValue = 1.0;
        double maxValue = 10.0;
        String pathOUT3 = Paths.get("./proteus-example/io/tsOUT_addTimeAndRandomValue.txt").toString();
        e.addTimeAndRandomValue_File(pathIN, pathOUT3, time, minValue, maxValue);

        // Test: addRandomTimeAndRandomValue_File
        String pathOUT4 = Paths.get("./proteus-example/io/tsOUT_addRandomTimeAndRandomValue.txt").toString();
        e.addRandomTimeAndRandomValue_File(pathIN, pathOUT4, minTime, maxTime, minValue, maxValue);

        // Test: addMultipleTimeValues_File
        List<Double> times = Arrays.asList(100.0, 200.0, 300.0);
        List<Double> values = Arrays.asList(5.0, 10.0, 15.0);
        String pathOUT5 = Paths.get("./proteus-example/io/tsOUT_addMultipleTimeValues.txt").toString();
        e.addMultipleTimeValues_File(pathIN, pathOUT5, times, values);

        // Test: addMultipleRandomTimeValues_File
        int count = 5;
        String pathOUT6 = Paths.get("./proteus-example/io/tsOUT_addMultipleRandomTimeValues.txt").toString();
        e.addMultipleRandomTimeValues_File(pathIN, pathOUT6, minTime, maxTime, minValue, maxValue, count);

        // Test: appendTimeSeries_File
        String pathAppend = Paths.get("./proteus-example/io/tsAppend.txt").toString();
        String pathOUT7 = Paths.get("./proteus-example/io/tsOUT_appendTimeSeries.txt").toString();
        e.appendTimeSeries_File(pathIN, pathAppend, pathOUT7);

        // Test: mergeTimeSeries_File
        String pathMerge = Paths.get("./proteus-example/io/tsMerge.txt").toString();
        String pathOUT8 = Paths.get("./proteus-example/io/tsOUT_mergeTimeSeries.txt").toString();
        e.mergeTimeSeries_File(pathIN, pathMerge, pathOUT8);

        // Test: replaceTimeSeries_File
        String pathReplace = Paths.get("./proteus-example/io/tsReplace.txt").toString();
        String pathOUT9 = Paths.get("./proteus-example/io/tsOUT_replaceTimeSeries.txt").toString();
        e.replaceTimeSeries_File(pathIN, pathReplace, pathOUT9);
        
	}
    
   
    
   
	
}

