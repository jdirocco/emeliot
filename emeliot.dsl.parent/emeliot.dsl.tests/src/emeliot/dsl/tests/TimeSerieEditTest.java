package emeliot.dsl.tests;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import emeliot.dsl.lib.EmeliotLib;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;


public class TimeSerieEditTest extends MainStdLibraryTest {
	
	public static void testChangeValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		double value = 666;
		e.changeValue(ts, time, value);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTime(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		int timeNew = 666;
		e.changeTime(ts, timeOld, timeNew);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeValueWithRandom(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeValueWithRandom(ts, time, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeWithRandom(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeTimeWithRandom(ts, 1, 100, time);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeAndValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		int timeNew = 666;
		double value = 666; 
		e.changeTimeAndValue(ts, timeOld, timeNew, value);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeWithRandomAndValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		double value = 666; 
		e.changeTimeWithRandomAndValue(ts, timeOld, 1, 100, value);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeAndValueWithRandom(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		int timeNew = 666;
		e.changeTimeAndValueWithRandom(ts, timeOld, timeNew, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeWithRandomAndValueWithRandom(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int timeOld = 3;
		e.changeTimeWithRandomAndValueWithRandom(ts, timeOld, 1, 100, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomTimeValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 666;
		double value = 666;
		e.changeARandomTimeValue(ts, time, value);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeARandomTimeValueWithRandomTimeValue(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeARandomTimeValueWithRandomTimeValue(ts, 1, 100, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeValues(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Double> timesOld = Arrays.asList(3.0, 5.0);
        List<Double> timesNew= Arrays.asList(111.0, 222.0);
        List<Double> valuesNew = new ArrayList<Double>();
		double value = 111;
		valuesNew.add(value);
		double value2 = 222;
		valuesNew.add(value2);
		e.changeMultipleTimeValues(ts, timesOld, timesNew, valuesNew);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeValuesWithRandomTimeValues(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Double> timesOld = Arrays.asList(3.0, 5.0);
		e.changeMultipleTimeValuesWithRandomTimeValues(ts, timesOld, 1, 100, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeLate(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeTimeLate(ts, 0.5, time, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomTimeLate(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomTimeLate(ts, 0.5, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeLate(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Double> times = Arrays.asList(3.0, 5.0);
		e.changeMultipleTimeLate(ts, 0.5, times, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeTimeEarly(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		double time = 3;
		e.changeTimeEarly(ts, 0.5, time, 1);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomTimeEarly(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomTimeEarly(ts, 0.5, 1);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleTimeEarly(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Double> times = Arrays.asList(3.0, 5.0);
		e.changeMultipleTimeEarly(ts, 1, times, 1);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeValueCoarse(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeValueCoarse(ts, 0.5, time, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomValueCoarse(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomValueCoarse(ts, 0.5, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleValueCoarse(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Double> times = Arrays.asList(3.0, 5.0);
		e.changeMultipleValueCoarse(ts, 0.5, times, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeValueSubtle(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		int time = 3;
		e.changeValueSubtle(ts, 0.5, time, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeRandomValueSubtle(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
		e.changeRandomValueSubtle(ts, 0.5, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeMultipleValueSubtle(ReadFactory factory, EmeliotLib e) {
		TimeSeries ts = createTimeSerie(factory, e);
        List<Double> times = Arrays.asList(3.0, 5.0);
		e.changeMultipleValueSubtle(ts, 0.5, times, 1, 100);
		e.printTimeSeries(ts);
	}
	
	public static void testChangeOperatorsFiles(EmeliotLib e) throws IOException {

	    double eps = 0.1;
	    double minDomainValue = 1.0;
	    double maxDomainValue = 10.0;
	    int minDomainTime = 1;
	    int maxDomainTime = 100;
	    String pathIN = Paths.get("./proteus-example/io/tsIN.txt").toString();

	    // Test for changeValue_File
	    double value = 666.0;
	    double time = 3;
	    String pathOUT1 = Paths.get("./proteus-example/io/tsOUT_changeValue.txt").toString();
	    e.changeValue_File(pathIN, pathOUT1, time, value);
	    
	    // Test for changeTime_File
	    double timeOld = 5;
	    double timeNew = 10;
	    String pathOUT2 = Paths.get("./proteus-example/io/tsOUT_changeTime.txt").toString();
	    e.changeTime_File(pathIN, pathOUT2, timeOld, timeNew);

	    // Test for changeValueWithRandom_File
	    String pathOUT3 = Paths.get("./proteus-example/io/tsOUT_changeValueWithRandom.txt").toString();
	    e.changeValueWithRandom_File(pathIN, pathOUT3, time, minDomainValue, maxDomainValue);

	    // Test for changeTimeWithRandom_File
	    double minTime = 2;
	    double maxTime = 8;
	    String pathOUT4 = Paths.get("./proteus-example/io/tsOUT_changeTimeWithRandom.txt").toString();
	    e.changeTimeWithRandom_File(pathIN, pathOUT4, minTime, maxTime, time);

	    // Test for changeTimeAndValue_File
	    double newValue = 999.0;
	    String pathOUT5 = Paths.get("./proteus-example/io/tsOUT_changeTimeAndValue.txt").toString();
	    e.changeTimeAndValue_File(pathIN, pathOUT5, timeOld, timeNew, newValue);

	    // Test for changeTimeWithRandomAndValue_File
	    double valueNew = 123.0;
	    String pathOUT6 = Paths.get("./proteus-example/io/tsOUT_changeTimeWithRandomAndValue.txt").toString();
	    e.changeTimeWithRandomAndValue_File(pathIN, pathOUT6, timeOld, minTime, maxTime, valueNew);

	    // Test for changeTimeAndValueWithRandom_File
	    String pathOUT7 = Paths.get("./proteus-example/io/tsOUT_changeTimeAndValueWithRandom.txt").toString();
	    e.changeTimeAndValueWithRandom_File(pathIN, pathOUT7, timeOld, timeNew, minDomainValue, maxDomainValue);

	    // Test for changeTimeWithRandomAndValueWithRandom_File
	    String pathOUT8 = Paths.get("./proteus-example/io/tsOUT_changeTimeWithRandomAndValueWithRandom.txt").toString();
	    e.changeTimeWithRandomAndValueWithRandom_File(pathIN, pathOUT8, timeOld, minTime, maxTime, minDomainValue, maxDomainValue);

	    // Test for changeARandomTimeValue_File
	    double newTime = 6;
	    String pathOUT9 = Paths.get("./proteus-example/io/tsOUT_changeARandomTimeValue.txt").toString();
	    e.changeARandomTimeValue_File(pathIN, pathOUT9, newTime, value);

	    // Test for changeMultipleTimeValues_File
	    List<Double> timesOld = Arrays.asList(3.0, 5.0, 8.0);
	    List<Double> timesNew = Arrays.asList(1.0, 4.0, 7.0);
	    List<Double> valuesNew = Arrays.asList(100.0, 200.0, 300.0);
	    String pathOUT10 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeValues.txt").toString();
	    e.changeMultipleTimeValues_File(pathIN, pathOUT10, timesOld, timesNew, valuesNew);

	    // Test for changeMultipleTimeValuesWithRandomTimeValues_File
	    String pathOUT11 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeValuesWithRandomTimeValues.txt").toString();
	    e.changeMultipleTimeValuesWithRandomTimeValues_File(pathIN, pathOUT11, timesOld, minTime, maxTime, minDomainValue, maxDomainValue);

	    // Test for changeTimeLate_File
	    String pathOUT12 = Paths.get("./proteus-example/io/tsOUT_changeTimeLate.txt").toString();
	    e.changeTimeLate_File(pathIN, pathOUT12, eps, time, maxDomainTime);

	    // Test for changeRandomTimeLate_File
	    String pathOUT13 = Paths.get("./proteus-example/io/tsOUT_changeRandomTimeLate.txt").toString();
	    e.changeRandomTimeLate_File(pathIN, pathOUT13, eps, maxDomainTime);

	    // Test for changeMultipleTimeLate_File
	    List<Double> times = Arrays.asList(1.0, 3.0, 5.0);
	    String pathOUT14 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeLate.txt").toString();
	    e.changeMultipleTimeLate_File(pathIN, pathOUT14, eps, times, maxDomainTime);

	    // Test for changeTimeEarly_File
	    String pathOUT15 = Paths.get("./proteus-example/io/tsOUT_changeTimeEarly.txt").toString();
	    e.changeTimeEarly_File(pathIN, pathOUT15, eps, time, minDomainTime);

	    // Test for changeRandomTimeEarly_File
	    String pathOUT16 = Paths.get("./proteus-example/io/tsOUT_changeRandomTimeEarly.txt").toString();
	    e.changeRandomTimeEarly_File(pathIN, pathOUT16, eps, minDomainTime);

	    // Test for changeMultipleTimeEarly_File
	    String pathOUT17 = Paths.get("./proteus-example/io/tsOUT_changeMultipleTimeEarly.txt").toString();
	    e.changeMultipleTimeEarly_File(pathIN, pathOUT17, eps, times, minDomainTime);

	    // Test for changeValueCoarse_File
	    String pathOUT18 = Paths.get("./proteus-example/io/tsOUT_changeValueCoarse.txt").toString();
	    e.changeValueCoarse_File(pathIN, pathOUT18, eps, time, minDomainValue, maxDomainValue);

	    // Test for changeRandomValueCoarse_File
	    String pathOUT19 = Paths.get("./proteus-example/io/tsOUT_changeRandomValueCoarse.txt").toString();
	    e.changeRandomValueCoarse_File(pathIN, pathOUT19, eps, minDomainValue, maxDomainValue);

	    // Test for changeMultipleValueCoarse_File
	    String pathOUT20 = Paths.get("./proteus-example/io/tsOUT_changeMultipleValueCoarse.txt").toString();
	    e.changeMultipleValueCoarse_File(pathIN, pathOUT20, eps, times, minDomainValue, maxDomainValue);

	    // Test for changeValueSubtle_File
	    String pathOUT21 = Paths.get("./proteus-example/io/tsOUT_changeValueSubtle.txt").toString();
	    e.changeValueSubtle_File(pathIN, pathOUT21, eps, time, minDomainValue, maxDomainValue);

	    // Test for changeRandomValueSubtle_File
	    String pathOUT22 = Paths.get("./proteus-example/io/tsOUT_changeRandomValueSubtle.txt").toString();
	    e.changeRandomValueSubtle_File(pathIN, pathOUT22, eps, minDomainValue, maxDomainValue);

	    // Test for changeMultipleValueSubtle_File
	    String pathOUT23 = Paths.get("./proteus-example/io/tsOUT_changeMultipleValueSubtle.txt").toString();
	    e.changeMultipleValueSubtle_File(pathIN, pathOUT23, eps, times, minDomainValue, maxDomainValue);
	}


	
}
