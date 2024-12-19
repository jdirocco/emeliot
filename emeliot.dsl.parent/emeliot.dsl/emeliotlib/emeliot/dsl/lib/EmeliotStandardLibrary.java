package emeliot.dsl.lib;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;


public class EmeliotStandardLibrary extends EmeliotRuntime{

	
	protected EmeliotStandardLibrary(EmeliotModelManager modelManager) {
		super(modelManager);
	}
	
	
	public EmeliotStandardLibrary(EmeliotRuntime other) {
		super(other);
	}
	
	
	
	/********** TODO: ADD OPERATORS (ALL ADDRESS COMMISSION MUTATION) **********/
	
	public void addTimeAndValue(TimeSeries s, double value, double time) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(time);
		tv.setValue(value);
		s.getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	public void addTimeAndValue_File(Path tsInputPath, Path tsOutputPath, double value, double time) throws IOException {
		TimeSeries s = readTSFromFile(tsInputPath);
		addTimeAndValue(s, value, time);
		writeTSToFile(s, tsOutputPath);
	}
	
	public void addRandomTimeAndValue(TimeSeries s, double value, double minTime, double maxTime) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double timeRandom = r.nextDouble(minTime, maxTime);
		tv.setTime(timeRandom);
		tv.setValue(value);
		s.getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	public void addRandomTimeAndValue_File(Path tsInputPath, Path tsOutputPath, double value, double minTime, double maxTime) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addRandomTimeAndValue(s, value, minTime, maxTime);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void addTimeAndRandomValue(TimeSeries s, double time, double minValue, double maxValue) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
		tv.setTime(time);
		tv.setValue(valueRandom);
		s.getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	public void addTimeAndRandomValue_File(Path tsInputPath, Path tsOutputPath, double time, double minValue, double maxValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addTimeAndRandomValue(s, time, minValue, maxValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void addRandomTimeAndRandomValue(TimeSeries s, double minTime, double maxTime, 
			double minValue, double maxValue) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double timeRandom = r.nextDouble(minTime, maxTime);
		double valueRandom = r.nextDouble(minValue, maxValue);
		tv.setTime(timeRandom);
		tv.setValue(valueRandom);
		s.getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	public void addRandomTimeAndRandomValue_File(Path tsInputPath, Path tsOutputPath, double minTime, double maxTime, double minValue, double maxValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addRandomTimeAndRandomValue(s, minTime, maxTime, minValue, maxValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void addMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values) {
		if(times.size() != values.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < times.size(); i++) {
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(times.get(i));
            tv.setValue(values.get(i));
            s.getTimeValues().add(tv);
        }
        reorderTimeSeries(s);
    }
	
	public void addMultipleTimeValues_File(Path tsInputPath, Path tsOutputPath, List<Double> times, List<Double> values) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addMultipleTimeValues(s, times, values);
	    writeTSToFile(s, tsOutputPath);
	}
	
    public void addMultipleRandomTimeValues(TimeSeries s, double minTime, double maxTime, double minValue, 
    		double maxValue, int count) {
        Random r = new Random();
        for (int i=0; i<count; i++) {
            double timeRandom = r.nextDouble(minTime, maxTime);
            double valueRandom = r.nextDouble(minValue, maxValue);
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(timeRandom);
            tv.setValue(valueRandom);
            s.getTimeValues().add(tv);
        }
        reorderTimeSeries(s);
    }
	
    public void addMultipleRandomTimeValues_File(Path tsInputPath, Path tsOutputPath, double minTime, double maxTime, double minValue, 
    		double maxValue, int count) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        addMultipleRandomTimeValues(s, minTime, maxTime, minValue, maxValue, count);
        writeTSToFile(s, tsOutputPath);
    }

    
    //duplicates allowed
    public void appendTimeSeries(TimeSeries s, TimeSeries s1) {
    	s.getTimeValues().addAll(s1.getTimeValues());
        reorderTimeSeries(s);
    }
    
    public void appendTimeSeries_File(Path tsInputPath, Path tsAppendPath, Path tsOutputPath) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        TimeSeries s1 = readTSFromFile(tsAppendPath);
        appendTimeSeries(s, s1);
        writeTSToFile(s, tsOutputPath);
    }
    
    //no time duplicates allowed
    public void mergeTimeSeries(TimeSeries s, TimeSeries s1) {
        for (TimeValue tv1 : s1.getTimeValues())
        	if(!existTimeValue(s, tv1.getTime(), tv1.getValue())) {
        		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
                tv.setTime(tv1.getTime());
                tv.setValue(tv1.getValue());
        		s.getTimeValues().add(tv);
        	}
        reorderTimeSeries(s);
    }
    
    public void mergeTimeSeries_File(Path tsInputPath, Path tsMergePath, Path tsOutputPath) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        TimeSeries s1 = readTSFromFile(tsMergePath);
        mergeTimeSeries(s, s1);
        writeTSToFile(s, tsOutputPath);
    }
    
    public void replaceTimeSeries(TimeSeries s, TimeSeries s1) {
    	s.getTimeValues().clear();
        appendTimeSeries(s, s1);
        reorderTimeSeries(s);
    }

    public void replaceTimeSeries_File(Path tsInputPath, Path tsReplacePath, Path tsOutputPath) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        TimeSeries s1 = readTSFromFile(tsReplacePath);
        replaceTimeSeries(s, s1);
        writeTSToFile(s, tsOutputPath);
    }




    
    
	
    
    
    
    
    
    
	
	
	/********** TODO: EDIT OPERATORS (SOME ADDRESS LATE/EARLY/VALUECOARSE/VALUESUBTLE MUTATIONS)**********/
	
    public void changeValue(TimeSeries s, double value, double time) {
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            tv.setValue(value);
	            break;
	        }
	    }
	}
    
    public void changeValue_File(Path tsInputPath, Path tsOutputPath, double value, double time) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeValue(s, value, time);
        writeTSToFile(s, tsOutputPath);
    }
	
	public void changeTime(TimeSeries s, double timeOld, double timeNew) {
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            break;
	        }
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeTime_File(Path tsInputPath, Path tsOutputPath, double timeOld, double timeNew) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTime(s, timeOld, timeNew);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeValueWithRandom(TimeSeries s, double minValue, double maxValue, double time) {
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            tv.setValue(valueRandom);
	            break;
	        }
	    }
	}
	
	public void changeValueWithRandom_File(Path tsInputPath, Path tsOutputPath, double minValue, double maxValue, double time) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeValueWithRandom(s, minValue, maxValue, time);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeTimeWithRandom(TimeSeries s, double minTime, double maxTime, double time) {
		Random r = new Random();
		double timeRandom = r.nextDouble(minTime, maxTime);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            tv.setTime(timeRandom);
	            break;
	        }
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeTimeWithRandom_File(Path tsInputPath, Path tsOutputPath, double minTime, double maxTime, double time) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeWithRandom(s, minTime, maxTime, time);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeTimeAndValue(TimeSeries s, double timeOld, double timeNew, double newValue) {
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            tv.setValue(newValue);
	            break;
	        }
	    }
	    reorderTimeSeries(s);
	}

	public void changeTimeAndValue_File(Path tsInputPath, Path tsOutputPath, double timeOld, double timeNew, double newValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeAndValue(s, timeOld, timeNew, newValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeTimeWithRandomAndValue(TimeSeries s, double timeOld, double minTime, double maxTime, 
			double valueNew) {
	    Random r = new Random();
	    double timeRandom = r.nextDouble(minTime, maxTime);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeRandom);
	            tv.setValue(valueNew);
	            break;
	        }
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeTimeWithRandomAndValue_File(Path tsInputPath, Path tsOutputPath, double timeOld, double minTime, double maxTime, double valueNew) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeWithRandomAndValue(s, timeOld, minTime, maxTime, valueNew);
	    writeTSToFile(s, tsOutputPath);
	}

	public void changeTimeAndValueWithRandom(TimeSeries s, double timeOld, double timeNew, double minValue, 
			double maxValue) {
	    Random r = new Random();
	    double valueRandom = r.nextDouble(minValue, maxValue);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            tv.setValue(valueRandom);
	            break;
	        }
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeTimeAndValueWithRandom_File(Path tsInputPath, Path tsOutputPath, double timeOld, double timeNew, double minValue, double maxValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeAndValueWithRandom(s, timeOld, timeNew, minValue, maxValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeTimeWithRandomAndValueWithRandom(TimeSeries s, double timeOld, double minTime, double maxTime, 
			double minValue, double maxValue) {
	    Random r = new Random();
	    double timeRandom = r.nextDouble(minTime, maxTime);
	    double valueRandom = r.nextDouble(minValue, maxValue);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeRandom);
	            tv.setValue(valueRandom);
	            break;
	        }
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeTimeWithRandomAndValueWithRandom_File(Path tsInputPath, Path tsOutputPath, double timeOld, double minTime, double maxTime, double minValue, double maxValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeWithRandomAndValueWithRandom(s, timeOld, minTime, maxTime, minValue, maxValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeARandomTimeValue(TimeSeries s, double newTime, double newValue) {
	    TimeValue tvRandom = selectRandomTimeValue(s);
	    if (tvRandom != null) {
	        tvRandom.setTime(newTime);
	        tvRandom.setValue(newValue);
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeARandomTimeValue_File(Path tsInputPath, Path tsOutputPath, double newTime, double newValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeARandomTimeValue(s, newTime, newValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeARandomTimeValueWithRandomTimeValue(TimeSeries s, double minTime, double maxTime,
					double minValue, double maxValue) {
	    TimeValue randomTV = selectRandomTimeValue(s);
	    Random r = new Random();
	    double timeRandom = r.nextDouble(minTime, maxTime);
	    double valueRandom = r.nextDouble(minValue, maxValue);
	    if (randomTV != null) {
	        randomTV.setTime(timeRandom);
	        randomTV.setValue(valueRandom);
	    }
	    reorderTimeSeries(s);
	}
	
	public void changeARandomTimeValue_File(Path tsInputPath, Path tsOutputPath, double minTime, double maxTime,
			double minValue, double maxValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeARandomTimeValueWithRandomTimeValue(s, minTime, maxTime, minValue, maxValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeMultipleTimeValues(TimeSeries s, List<Double> timesOld, List<Double> timesNew,
			List<Double> valuesNew) {
		if(timesOld.size() != valuesNew.size() || timesOld.size() != timesNew.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < timesOld.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == timesOld.get(i)) {
		            tv.setTime(timesNew.get(i));
		            tv.setValue(valuesNew.get(i));
		            continue;
		        }
    	    }
        }
        reorderTimeSeries(s);
    }
	
	public void changeMultipleTimeValues_File(Path tsInputPath, Path tsOutputPath, List<Double> timesOld, List<Double> timesNew, List<Double> valuesNew) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleTimeValues(s, timesOld, timesNew, valuesNew);
	    writeTSToFile(s, tsOutputPath);
	}

	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeries s, List<Double> timesOld, double minTime, 
			double maxTime, double minValue, double maxValue) {
        Random r = new Random();
        for (int i = 0; i < timesOld.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == timesOld.get(i)) {
		        	double timeRandom = r.nextDouble(minTime, maxTime);
				    double valueRandom = r.nextDouble(minValue, maxValue);
		            tv.setTime(timeRandom);
		            tv.setValue(valueRandom);
		            continue;
		        }
    	    }
        }
        reorderTimeSeries(s);
    }
	
	public void changeMultipleTimeValuesWithRandomTimeValues_File(Path tsInputPath, Path tsOutputPath, List<Double> timesOld, double minTime, 
			double maxTime, double minValue, double maxValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleTimeValuesWithRandomTimeValues(s, timesOld, minTime, maxTime, minValue, maxValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
    public void changeTimeLate(TimeSeries s, double eps, double time, double maxDomainTime) {
        Random r = new Random();
        for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	        	double timeNew = time + (double) (r.nextDouble() * (maxDomainTime - time - eps) + eps);
	        	tv.setTime(timeNew);	            
	            break;
	        } 
        }
        reorderTimeSeries(s);
    }
    
    public void changeTimeLate_File(Path tsInputPath, Path tsOutputPath, double eps, double time, double maxDomainTime) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeTimeLate(s, eps, time, maxDomainTime);
        writeTSToFile(s, tsOutputPath);
    }
	
	public void changeRandomTimeLate(TimeSeries s, double eps, double maxDomainTime) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
        	double timeOld = randomTV.getTime();
        	double timeNew = timeOld + (double) (r.nextDouble() * (maxDomainTime - timeOld - eps) + eps);
            randomTV.setTime(timeNew);
        }
        reorderTimeSeries(s);
    }
	
	public void changeRandomTimeLate_File(Path tsInputPath, Path tsOutputPath, double eps, double maxDomainTime) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeRandomTimeLate(s, eps, maxDomainTime);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeMultipleTimeLate(TimeSeries s, double eps, List<Double> times, double maxDomainTime) {
        Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == times.get(i)) {
		        	double timeOld = times.get(i);
		        	double timeNew = timeOld + (double) (r.nextDouble() * (maxDomainTime - timeOld - eps) + eps);
		        	tv.setTime(timeNew);	  
		        	continue;
		        }
    	    }
        }
        reorderTimeSeries(s);
	}
	
	public void changeMultipleTimeLate_File(Path tsInputPath, Path tsOutputPath, double eps, List<Double> times, double maxDomainTime) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleTimeLate(s, eps, times, maxDomainTime);
	    writeTSToFile(s, tsOutputPath);
	}
	
    public void changeTimeEarly(TimeSeries s, double eps, double time, double minDomainTime) {
        Random r = new Random();
        for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	        	double timeNew = time - (double) (r.nextDouble() * (time - minDomainTime - eps) + eps);
	            tv.setTime(timeNew);
	            break;
	        }
        }
        reorderTimeSeries(s);
    }
    
    public void changeTimeEarly_File(Path tsInputPath, Path tsOutputPath, double eps, double time, double minDomainTime) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeTimeEarly(s, eps, time, minDomainTime);
        writeTSToFile(s, tsOutputPath);
    }
    
    public void changeRandomTimeEarly(TimeSeries s, double eps, double minDomainTime) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
        	double timeOld = randomTV.getTime();
        	double timeNew = timeOld - (double) (r.nextDouble() * (timeOld - minDomainTime - eps) + eps);
            randomTV.setTime(timeNew);
        }
        reorderTimeSeries(s);
    }
    
    public void changeRandomTimeEarly_File(Path tsInputPath, Path tsOutputPath, double eps, double minDomainTime) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeRandomTimeEarly(s, eps, minDomainTime);
        writeTSToFile(s, tsOutputPath);
    }

	public void changeMultipleTimeEarly(TimeSeries s, double eps, List<Double> times, double minDomainTime) {
        Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == times.get(i)) {
		        	double timeOld = times.get(i);
		        	double timeNew = timeOld - (double) (r.nextDouble() * (timeOld - minDomainTime - eps) + eps);
		        	tv.setTime(timeNew);	  
		        	continue;
		        }
    	    }
        }
        reorderTimeSeries(s);
	}
	
    public void changeMultipleTimeEarly_File(Path tsInputPath, Path tsOutputPath, double eps, List<Double> times, double minDomainTime) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeMultipleTimeEarly(s, eps, times, minDomainTime);
        writeTSToFile(s, tsOutputPath);
    }
	
    public void changeValueCoarse(TimeSeries s, double eps, double minDomainValue, double maxDomainValue, double time) {
        Random r = new Random();
        for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            double valueNew = (r.nextBoolean()) ? 
	            		(r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
	                    : (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
	            tv.setValue(valueNew);
	            break;
	        }
        }
    }
	
    public void changeValueCoarse_File(Path tsInputPath, Path tsOutputPath, double eps, double minDomainValue, double maxDomainValue, double time) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeValueCoarse(s, eps, minDomainValue, maxDomainValue, time);
        writeTSToFile(s, tsOutputPath);
    }
    
    public void changeRandomValueCoarse(TimeSeries s, double eps, double minDomainValue, double maxDomainValue) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            double valueNew = (r.nextBoolean()) ? 
            		(r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
                    : (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
            randomTV.setValue(valueNew);
        }
    }
    
    public void changeRandomValueCoarse_File(Path tsInputPath, Path tsOutputPath, double eps, double minDomainValue, double maxDomainValue) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        changeRandomValueCoarse(s, eps, minDomainValue, maxDomainValue);
        writeTSToFile(s, tsOutputPath);
    }

	public void changeMultipleValueCoarse(TimeSeries s, double eps, List<Double> times, 
			double minDomainValue, double maxDomainValue) {
		Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == times.get(i)) {
		        	double valueNew = (r.nextBoolean()) ?
		        			(r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
		                    : (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
		            tv.setValue(valueNew);
		        	continue;
		        }
    	    }
        }
        reorderTimeSeries(s);
	}
	
	public void changeMultipleValueCoarse_File(Path tsInputPath, Path tsOutputPath, double eps, List<Double> times, double minDomainValue, double maxDomainValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleValueCoarse(s, eps, times, minDomainValue, maxDomainValue);
	    writeTSToFile(s, tsOutputPath);
	}

	public void changeValueSubtle(TimeSeries s, double eps, double time, double minDomainValue, double maxDomainValue) {
		Random r = new Random();
        for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
	                    : (r.nextDouble() * (eps - minDomainValue) - eps);
	            tv.setValue(valueNew);
	            break;
	        }
        }
    }
	
	public void changeValueSubtle_File(Path tsInputPath, Path tsOutputPath, double eps, double time, double minDomainValue, double maxDomainValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeValueSubtle(s, eps, time, minDomainValue, maxDomainValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeRandomValueSubtle(TimeSeries s, double eps, double minDomainValue, double maxDomainValue) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
                    : (r.nextDouble() * (eps - minDomainValue) - eps);
            randomTV.setValue(valueNew);
        }
    }
	
	public void changeRandomValueSubtle_File(Path tsInputPath, Path tsOutputPath, double eps, double minDomainValue, double maxDomainValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeRandomValueSubtle(s, eps, minDomainValue, maxDomainValue);
	    writeTSToFile(s, tsOutputPath);
	}
	
	public void changeMultipleValueSubtle(TimeSeries s, double eps, List<Double> times, double minDomainValue, 
			double maxDomainValue) {
		Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == times.get(i)) {
		        	double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
		                    : (r.nextDouble() * (eps - minDomainValue) - eps);
		            tv.setValue(valueNew);
		        	continue;
		        }
    	    }
        }
        reorderTimeSeries(s);
	}

	public void changeMultipleValueSubtle_File(Path tsInputPath, Path tsOutputPath, double eps, List<Double> times, double minDomainValue, double maxDomainValue) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleValueSubtle(s, eps, times, minDomainValue, maxDomainValue);
	    writeTSToFile(s, tsOutputPath);
	}

	
	
	
	
	





    
	
	
	
	
	
	
	
	
	/********** TODO: REMOVE OPERATORS (ALL ADDRESS OMISSION MUTATION)**********/
    
	public void removeTimeValue(TimeSeries s, double time, double value) {
        s.getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
    }
	
	public void removeTimeValue_File(Path tsInputPath, Path tsOutputPath, double time, double value) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    removeTimeValue(s, time, value);
	    writeTSToFile(s, tsOutputPath);
	}


    public void removeTimeValue(TimeSeries s, TimeValue tvToRemove) {
        s.getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && (tv.getValue() == tvToRemove.getValue()));
    }
    
    public void removeTimeValue_File(Path tsInputPath, Path tsOutputPath, TimeValue tvToRemove) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValue(s, tvToRemove);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeRandomTimeValue(TimeSeries s) {
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null)
            s.getTimeValues().remove(randomTV);
    }
    
    public void removeRandomTimeValue_File(Path tsInputPath, Path tsOutputPath) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeRandomTimeValue(s);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values) {
        for (int i=0; i<times.size(); i++) {
            double time = times.get(i);
            double value = values.get(i);
            s.getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }
    
    public void removeMultipleTimeValues_File(Path tsInputPath, Path tsOutputPath, List<Double> times, List<Double> values) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, times, values);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeMultipleTimeValues(TimeSeries s, List<TimeValue> timeValues) {
    	for (int i=0; i<timeValues.size(); i++) {
    		double time = timeValues.get(i).getTime();
            double value = timeValues.get(i).getValue();
            s.getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }
    
    public void removeMultipleTimeValues_File(Path tsInputPath, Path tsOutputPath, List<TimeValue> timeValues) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, timeValues);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeMultipleTimeValues(TimeSeries s, TimeValue... timeValues) {
        for (TimeValue tvToRemove: timeValues)
            s.getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == tvToRemove.getValue());
    }
    

    public void removeMultipleTimeValues_File(Path tsInputPath, Path tsOutputPath, TimeValue... timeValues) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    removeMultipleTimeValues(s, timeValues);
	    writeTSToFile(s, tsOutputPath);
    }

    public void removeAllTimeValues(TimeSeries s) {
        s.getTimeValues().clear();
    }
    
    public void removeAllTimeValues_File(Path tsInputPath, Path tsOutputPath) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeAllTimeValues(s);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeTimeValuesBeforeTime(TimeSeries s, double time) {
        s.getTimeValues().removeIf(tv -> tv.getTime() < time);
    }
    
    public void removeTimeValuesBeforeTime_File(Path tsInputPath, Path tsOutputPath, double time) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesBeforeTime(s, time);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeTimeValuesAfterTime(TimeSeries s, double time) {
        s.getTimeValues().removeIf(tv -> tv.getTime() > time);
    }
    
    public void removeTimeValuesAfterTime_File(Path tsInputPath, Path tsOutputPath, double time) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesAfterTime(s, time);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeTimeValuesBelowValue(TimeSeries s, double value) {
        s.getTimeValues().removeIf(tv -> tv.getValue() < value);
    }
    
    public void removeTimeValuesBelowValue_File(Path tsInputPath, Path tsOutputPath, double value) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesBelowValue(s, value);
        writeTSToFile(s, tsOutputPath);
    }

    public void removeTimeValuesAboveValue(TimeSeries s, double value) {
        s.getTimeValues().removeIf(tv -> tv.getValue() > value);
    }
    
    public void removeTimeValuesAboveValue_File(Path tsInputPath, Path tsOutputPath, double value) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesAboveValue(s, value);
        writeTSToFile(s, tsOutputPath);
    }
    
    
    
    
    
    
	
	

	
	
	
	/**********TODO: UTILS OPERATORS **********/
	
    public void reorderTimeSeries(TimeSeries s) {
		List<TimeValue> timeValues = new ArrayList<>(s.getTimeValues());
	    timeValues.sort(Comparator.comparingDouble(TimeValue::getTime));
	    s.getTimeValues().clear();
	    for (TimeValue tv: timeValues)
	        s.getTimeValues().add(tv);
	}

	public TimeValue selectRandomTimeValue(TimeSeries s) {
	    Random r = new Random();
	    List<TimeValue> timeValues = s.getTimeValues();
	    if (!timeValues.isEmpty()) {
	        int randomIndex = r.nextInt(timeValues.size());
	        return timeValues.get(randomIndex);
	    }
	    return null;
	}

	public boolean existTime(TimeSeries s, double time) {
	    for (TimeValue tv: s.getTimeValues())
	    	if (tv.getTime() == time)
	            return true;
	    return false;
	}
	
	public boolean existValue(TimeSeries s, double value) {
	    for (TimeValue tv: s.getTimeValues()) {
	    	double v1 = tv.getValue();
	        if (value == v1)
	            return true;
	    }
	    return false;
	}
	
	public boolean existTimeValue(TimeSeries s, double time, double value) {
	    return (existTime(s, time) && existValue(s, value));
	}

	public List<Double> getAllTimes(TimeSeries s) {
	    List<Double> times = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues())
	        times.add(tv.getTime());
	    return times;
	}

	public List<Double> getAllValues(TimeSeries s) {
	    List<Double> values = new ArrayList<Double>();
	    for (TimeValue tv: s.getTimeValues())
	        values.add(tv.getValue());
	    return values;
	}

	public List<Double> getTimesInRange(TimeSeries s, double minTime, double maxTime) {
	    List<Double> times = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues())
	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime)
	        	times.add(tv.getTime());
	    return times;
	}
	
	public List<Double> getValuesInRange(TimeSeries s, double minValue, double maxValue) {
	    List<Double> values = new ArrayList<Double>();
	    for (TimeValue tv: s.getTimeValues()) {
	        double value = tv.getValue();
	        if (value >= minValue && value <= maxValue)
	        	values.add(value);
	    }
	    return values;
	}
	
	public List<TimeValue> getTimeValuesInRange(TimeSeries s, double minTime, double maxTime, double minValue, 
			double maxValue) {
	    List<TimeValue> timeValues = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues()) {
	        double value = tv.getValue();
	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime && value >= minValue && value <= maxValue)
	            timeValues.add(tv);
	    }
	    return timeValues;
	}

	public double getValueAt(TimeSeries s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size())
	    	return s.getTimeValues().get(index).getValue();
        throw new IllegalArgumentException("Index is out of range");
	}

	public double getTimeAt(TimeSeries s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size())
	    	return s.getTimeValues().get(index).getTime();
        throw new IllegalArgumentException("Index is out of range");
	}

	public TimeValue getTimeValueAt(TimeSeries s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size())
	    	return s.getTimeValues().get(index);
        throw new IllegalArgumentException("Index is out of range");
	}

	public double getMaxTime(TimeSeries s) {
	    return s.getTimeValues().stream()
	            .mapToDouble(TimeValue::getTime)
	            .max()
	            .orElseThrow(NoSuchElementException::new);
	}

	public double getMinTime(TimeSeries s) {
	    return s.getTimeValues().stream()
	            .mapToDouble(TimeValue::getTime)
	            .min()
	            .orElseThrow(NoSuchElementException::new);
	}

	public double getMaxValue(TimeSeries s) {
	    return s.getTimeValues().stream()
	            .mapToDouble(tv -> tv.getValue())
	            .max()
	            .orElseThrow(NoSuchElementException::new);
	}

	public double getMinValue(TimeSeries s) {
	    return s.getTimeValues().stream()
	            .mapToDouble(tv -> tv.getValue())
	            .min()
	            .orElseThrow(NoSuchElementException::new);
	}
	
	public TimeValue getNextTimeValue(TimeSeries s, TimeValue tv) {
	    int index = s.getTimeValues().indexOf(tv);
	    if (index >= 0 && index < s.getTimeValues().size() - 1) {
	        return s.getTimeValues().get(index + 1);
	    }
        throw new IllegalArgumentException("Index is out of range");
	}

	public double getNextTime(TimeSeries s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}

	public double getNextValue(TimeSeries s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}

	public TimeValue getNextTimeValue(TimeSeries s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size() - 1) {
	        return s.getTimeValues().get(index + 1);
	    }
        throw new IllegalArgumentException("Index is out of range");
	}

	public double getNextTime(TimeSeries s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}

	public double getNextValue(TimeSeries s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}

	public TimeValue getPreviousTimeValue(TimeSeries s, TimeValue tv) {
	    int index = s.getTimeValues().indexOf(tv);
	    if (index > 0) {
	        return s.getTimeValues().get(index - 1);
	    }
	    return null;
	}

	public double getPreviousTime(TimeSeries s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}

	public double getPreviousValue(TimeSeries s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}

	public TimeValue getPreviousTimeValue(TimeSeries s, int index) {
	    if (index > 0 && index < s.getTimeValues().size())
	        return s.getTimeValues().get(index - 1);
	    return null;
	}

	public double getPreviousTime(TimeSeries s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}

	public double getPreviousValue(TimeSeries s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}

	public double getFirstTime(TimeSeries s) {
	    return s.getTimeValues().isEmpty() ? -1 : s.getTimeValues().get(0).getTime();
	}

	public double getFirstValue(TimeSeries s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(0).getValue();
	}

	public TimeValue getFirstTimeValue(TimeSeries s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(0);
	}

	public double getLastTime(TimeSeries s) {
	    return s.getTimeValues().isEmpty() ? -1 : s.getTimeValues().get(s.getTimeValues().size() - 1).getTime();
	}

	public double getLastValue(TimeSeries s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(s.getTimeValues().size() - 1).getValue();
	}

	public TimeValue getLastTimeValue(TimeSeries s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(s.getTimeValues().size() - 1);
	}

	public TimeSeries copyTimeSeries(TimeSeries s) {
	    TimeSeries copy = ReadFactory.eINSTANCE.createTimeSeries();
	    for (TimeValue tv : s.getTimeValues()) {
	        TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	        tvCopy.setTime(tv.getTime());
	        tvCopy.setValue(tv.getValue());
	        copy.getTimeValues().add(tvCopy);
	    }
	    return copy;
	}

	public void setAllTimesToZero(TimeSeries s) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setTime(0);
	}

	public void setAllValuesToZero(TimeSeries s) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setValue(0);
	}

	public void setAllToZero(TimeSeries s) {
	    setAllTimesToZero(s);
	    setAllValuesToZero(s);
	}

	public void setAllTimesToTime(TimeSeries s, double time) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setTime(time);
	}

	public void setAllValuesToValue(TimeSeries s, double value) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setValue(value);
	}

	public void setAllToTimeValue(TimeSeries s, double time, double value) {
	    setAllTimesToTime(s, time);
	    setAllValuesToValue(s, value);
	}

	public TimeSeries getSubTimeSeriesInTimeRange(TimeSeries s, double minTime, double maxTime) {
	    TimeSeries subSeries = ReadFactory.eINSTANCE.createTimeSeries();
	    for (TimeValue tv : s.getTimeValues()) {
	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime) {
	            TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	            tvCopy.setTime(tv.getTime());
	            tvCopy.setValue(tv.getValue());
	            subSeries.getTimeValues().add(tvCopy);
	        }
	    }
	    return subSeries;
	}

	public int countTimeValues(TimeSeries s) {
	    return s.getTimeValues().size();
	}

	public boolean isEmpty(TimeSeries s) {
	    return s.getTimeValues().size() == 0;
	}

	public void printTimeSeries(TimeSeries s) {
		for(TimeValue tv: s.getTimeValues())
			System.out.println(tv.getTime() + " " + tv.getValue());
	}
	
	
	
	
	
	
	
	
	
	
	
	//TODO: aux methods for Proteus only
	//this method reads from file a Proteus timeserie (to be used during timeseries mutation phase)
	public void writeTSToFile(TimeSeries timeSeries, Path filePath) throws IOException {
		reorderTimeSeries(timeSeries);
        List<String> lines = timeSeries.getTimeValues().stream()
            .map(tv -> tv.getTime() + " " + tv.getValue())
            .collect(Collectors.toList());
        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

	//this method writes to file a Proteus timeserie (to be used during timeseries mutation phase)
    public TimeSeries readTSFromFile(Path filePath) throws IOException {
    	TimeSeries timeSeries = ReadFactory.eINSTANCE.createTimeSeries();
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 2)
                throw new IOException("Invalid line format: " + line);
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
    		tv.setTime(Double.parseDouble(parts[0].trim()));
            double value = (Double.parseDouble(parts[1].trim()));
            tv.setValue(value);
            timeSeries.getTimeValues().add(tv);
        }
        reorderTimeSeries(timeSeries);
        return timeSeries;
    }
	
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********** TODO: DISCOVERY OPERATORS **********/
	public boolean isCommission(TimeSeries tsOriginal, TimeSeries tsMutated) {
		return tsOriginal.getTimeValues().size() < tsMutated.getTimeValues().size(); //TODO: what if more elements are added and removed?
	}
	
	public boolean isOmission(TimeSeries tsOriginal, TimeSeries tsMutated) {
		return tsOriginal.getTimeValues().size() > tsMutated.getTimeValues().size(); //TODO: what if more elements are added and removed?
	}
	
	public boolean isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			double time1 = tv1.getTime();			
			double value1 = tv1.getValue();
			double closestDiffTime = Double.MAX_VALUE;
			for(int j=1; j<tsMutated.getTimeValues().size(); j++) {
				TimeValue tv2 = tsMutated.getTimeValues().get(i);
				double time2 = tv2.getTime();			
				double value2 = tv2.getValue();
				//if time and value are the same, no early/late
				if(time1 == time2 && value1 == value2) {
					closestDiffTime = 0;
					break;
				}
				//if only value is the same, check closest time
				else if (value1 == value2){
					double timeDiff = time1 - time2;//diff between times
					if(Math.abs(timeDiff)<=Math.abs(closestDiffTime)) {
						closestDiffTime = timeDiff;
					}
					else
						break;//the timeseries are ordered so once the diff is getting bigger the cycle can stop
				}
			}
			if(closestDiffTime == 0)//no early/late detected for timeseries j as diff time is 0
				continue;
			if(Math.abs(closestDiffTime)>eps && closestDiffTime<0)//if diff time bigger than epsilon, late detected
				return true;
		}	
		return false;
	}
	
	public boolean isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			double time1 = tv1.getTime();			
			double value1 = tv1.getValue();
			double closestDiffTime = Double.MAX_VALUE;
			for(int j=1; j<tsMutated.getTimeValues().size(); j++) {
				TimeValue tv2 = tsMutated.getTimeValues().get(i);
				double time2 = tv2.getTime();			
				double value2 = tv2.getValue();
				//if time and value are the same, no early/late
				if(time1 == time2 && value1 == value2) {
					closestDiffTime = 0;
					break;
				}
				//if only value is the same, check closest time
				else if (value1 == value2){
					double timeDiff = time1 - time2;//diff between times
					if(Math.abs(timeDiff)<=Math.abs(closestDiffTime)) {
						closestDiffTime = timeDiff;
					}
					else
						break;//the timeseries are ordered so once the diff is getting bigger the cycle can stop
				}
			}
			if(closestDiffTime == 0)//no early/late detected for timeseries j as diff time is 0
				continue;
			if(Math.abs(closestDiffTime)> eps && closestDiffTime>0) //if diff time bigger than epsilon, early detected
				return true;
		}	
		return false;
	}
	
	public boolean isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double minValue, double maxValue) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			TimeValue tv2 = tsMutated.getTimeValues().get(i);
			double valueOriginal = tv1.getValue();
			double valueMutated = tv2.getValue();
			double valueDiff = valueOriginal - valueMutated;
			//value subtle failure found as mutated value is bigger than eps and out of boundary
			if(Math.abs(valueDiff)> eps && (valueMutated < minValue || valueMutated > maxValue))
				return true;
		}	
		return false;
	}
	
	public boolean isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double minValue, double maxValue) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			TimeValue tv2 = tsMutated.getTimeValues().get(i);
			double valueOriginal = tv1.getValue();
			double valueMutated = tv2.getValue();
			double valueDiff = valueOriginal - valueMutated;
			//value subtle failure found as mutated value is bigger than eps and within boundary
			if(Math.abs(valueDiff)> eps && (valueMutated >= minValue && valueMutated <= maxValue))
				return true;
		}
		return false;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************TODO: SIMULATION OPERATORS (Proteus)********************/
	
	//this method runs a test case (i.e., a set of mutated timeseries) on a Proteus component
	//output ports are needed to interact with the GUI to activate the proper simulation graphs
	public void runTestCase(Path componentPath, Path mutationsPath, Path outputsPath, List<String> outputPorts) throws Exception {
		
		//params checks
		if(componentPath == null || mutationsPath == null || outputsPath == null ||
				!(Files.exists(componentPath)) || !(Files.exists(mutationsPath)) || !(Files.exists(outputsPath)))
			throw new Exception("A file path does not exist");
		if(outputPorts.isEmpty()||outputPorts.size()>9)
			throw new Exception("Num output ports is out of range"); //up to 9 output ports are supported by the UI
		
		//backup original component timeseries
		backupOriginalTS(componentPath);
		
        //copy mutated timeseries into component folder   
		moveMutatedFiles(componentPath, mutationsPath);        
        
        //execute test case (i.e., component is run with mutated timeseries)
        runProteus(componentPath, outputPorts);
        
        //save outputs generated by output ports to test case folder
        moveOutputFiles(componentPath, outputsPath);
        
        //restore component state for next execution, removing any file originated from last test case execution
        restoreOriginalTS(componentPath);	        
	}
	
	//this method performs a backup of the original timeseries (.txt) feeding the input ports of a Proteus component
	private static void backupOriginalTS(Path componentPath) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(componentPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                if (fileName.toLowerCase().endsWith(".txt") && !fileName.contains("_backup")) {
                    String newFileName = fileName.replace(".txt", "_backup" + ".txt");
                    Path newFilePath = file.resolveSibling(newFileName);
                    Files.move(file, newFilePath);
                }
            }
        }
	}
	
	//this method moves the mutated timeseries into the Proteus component folder, to be injected to input ports
	private static void moveMutatedFiles(Path componentPath, Path mutationsPath) throws IOException {
        Files.walkFileTree(mutationsPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path destFile = componentPath.resolve(mutationsPath.relativize(file));
                Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path destDir = componentPath.resolve(mutationsPath.relativize(dir));
                if (Files.notExists(destDir)) {
                    Files.createDirectory(destDir);
                }
                return FileVisitResult.CONTINUE;
            }
        });
	}
	
	//this method runs Proteus simulation for the component.
	//it uses output ports names to save output files once the simulation is completed
	private void runProteus(Path componentPath, List<String> outputPorts) throws Exception {
		//input checks
	    Path component = Files.find(componentPath, 1, (path, attrs) -> path.toString().endsWith(".pdsprj"))
	            .findFirst()
	            .orElse(null);
		if (component == null)
	        throw new FileNotFoundException("No .pdsprj file found in " + componentPath.toString());  
	    //start Proteus
		long WAIT_TIME = 5000;
	    Thread.sleep(WAIT_TIME);
	    System.out.println("RUNNING PROTEUS TEST CASE " + componentPath.getFileName() + " >>>");
	    Runtime.getRuntime().exec("cmd /c start " + component.toString());
	    Thread.sleep(2 * WAIT_TIME);
	    Robot robot = new Robot();
	    for (int i = 0; i < outputPorts.size(); i++) {
	        try {
	    		//open Graph Menu 
	        	Thread.sleep(WAIT_TIME);
	            robot.keyPress(KeyEvent.VK_ALT);
	            robot.keyPress(KeyEvent.VK_G);
	            robot.keyRelease(KeyEvent.VK_G);
	            robot.keyRelease(KeyEvent.VK_ALT);
	            Thread.sleep(WAIT_TIME);
	    		//digit i to focus on graph associated with probe/output port i 
	            switch(i) {
	            	case 0: {robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1); break;}
	            	case 1: {robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2); break;}
	            	case 2: {robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3); break;}
	            	case 3: {robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4); break;}
	            	case 4: {robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5); break;}
	            	case 5: {robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6); break;}
	            	case 6: {robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7); break;}
	            	case 7: {robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8); break;}
	            	case 8: {robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9); break;}
	            	default: {throw new IllegalArgumentException("Too many output ports!");}
	            }
	            //start simulation via space bar
	    	    robot.keyPress(KeyEvent.VK_SPACE);
	            robot.keyRelease(KeyEvent.VK_SPACE);
	            Thread.sleep(5*WAIT_TIME);
	            //open Graph menu and select Export data option
	            robot.keyPress(KeyEvent.VK_ALT);
	            robot.keyPress(KeyEvent.VK_G);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_G);
	            robot.keyRelease(KeyEvent.VK_ALT);
	            Thread.sleep(WAIT_TIME);
	            //save output file as the name of the output port i
	            StringSelection stringSelection = new StringSelection(outputPorts.get(i));
	            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	            clipboard.setContents(stringSelection, stringSelection);
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
	            Thread.sleep(WAIT_TIME);
	            //close the Graph window
	            robot.keyPress(KeyEvent.VK_ESCAPE);robot.keyRelease(KeyEvent.VK_ESCAPE);
	            Thread.sleep(WAIT_TIME);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    //close Proteus
	    Runtime.getRuntime().exec("cmd /c taskkill /F /IM PDS.exe");
	    System.out.println("PROTEUS TEST CASE " + componentPath.getFileName() + " COMPLETED.");
	}
	
	//this method saves the outputs to test case folder once a simulation is completed
	private static void moveOutputFiles(Path componentPath, Path outputsPath) throws IOException {
	    Files.walkFileTree(componentPath, new SimpleFileVisitor<Path>() {
	        @Override
	        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	            String fileName = file.getFileName().toString().toLowerCase();
	            if (fileName.endsWith(".dat")) {
	                Path destFile = outputsPath.resolve(componentPath.relativize(file));
	                Files.createDirectories(destFile.getParent());
	                Files.move(file, destFile, StandardCopyOption.REPLACE_EXISTING);
	            }
	            return FileVisitResult.CONTINUE;
	        }
	        @Override
	        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	            return FileVisitResult.CONTINUE;
	        }
	    });
	}
	
	//this method cleans the component folder from any file generated by a mutated simulation and restore the original timeseries
	public static void restoreOriginalTS(Path componentPath) throws IOException {
        String[] extensions = {".txt", ".csv", ".DAT", ".workspace"};
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(componentPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString().toLowerCase();
                boolean isBackupFile = fileName.contains("_backup");
                if (!isBackupFile) {
                    for (String ext : extensions) {
                        if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
                            Files.delete(file);
                            break;
                        }
                    }
                }
            }
        }
        Path backupDir = componentPath.resolve("Project Backups");
        if (Files.exists(backupDir)) {
            Files.walk(backupDir)
                .sorted(Comparator.reverseOrder())
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        System.err.println("Error deleting file: " + p + " - " + e.getMessage());
                    }
                });
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(componentPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                if (fileName.contains("_backup")) {
                    String originalFileName = fileName.replace("_backup", "");
                    Path originalFilePath = file.resolveSibling(originalFileName);
                    Files.move(file, originalFilePath);
                }
            }
        } 
    }


	
	
	
	
	
	
	
	
	
}
