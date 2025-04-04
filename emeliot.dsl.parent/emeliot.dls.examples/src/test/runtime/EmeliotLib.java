package test.runtime;

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
import java.nio.file.Paths;
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

import emeliot.dsl.lib.DiscoveryOutcome;
import emeliot.dsl.lib.EmeliotDiscoveryService;
import emeliot.dsl.lib.EmeliotMutationService;
import emeliot.dsl.lib.EmeliotService;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;




public abstract class EmeliotLib implements EmeliotService, EmeliotMutationService, EmeliotDiscoveryService {

	
	
	
	
	//TODO: ADD OPERATORS
	
	
	@Override
	public void addTimeAndValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(time);
		tv.setValue(value);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addTimeAndValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
		TimeSeries s = readInTSFromFile(tsInputPath);
		addTimeAndValue(s, time, value);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void addRandomTimeAndValue(TimeSeries s, double timeMin, double timeMax, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double timeRandom = r.nextDouble(timeMin, timeMax);
		tv.setTime(timeRandom);
		tv.setValue(value);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addRandomTimeAndValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addRandomTimeAndValue(s, timeMin, timeMax, value);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void addTimeAndRandomValue(TimeSeries s, double time, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double valueRandom = r.nextDouble(valueMin, valueMax);
		tv.setTime(time);
		tv.setValue(valueRandom);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addTimeAndRandomValue_File(String tsInputPath, String tsOutputPath, double time, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addTimeAndRandomValue(s, time, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void addRandomTimeAndRandomValue(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double timeRandom = r.nextDouble(timeMin, timeMax);
		double valueRandom = r.nextDouble(valueMin, valueMax);
		tv.setTime(timeRandom);
		tv.setValue(valueRandom);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addRandomTimeAndRandomValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addRandomTimeAndRandomValue(s, timeMin, timeMax, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void addMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if(times.size() != values.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < times.size(); i++) {
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(times.get(i));
            tv.setValue(values.get(i));
    		((TimeSeriesValue) s).getTimeValues().add(tv);
        }
        reorderTimeSeries(s);
    }
	
	@Override
	public void addMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> times, List<Double> values) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addMultipleTimeValues(s, times, values);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
    public void addMultipleRandomTimeValues(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax, int count) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        Random r = new Random();
        for (int i=0; i<count; i++) {
            double timeRandom = r.nextDouble(timeMin, timeMax);
            double valueRandom = r.nextDouble(valueMin, valueMax);
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(timeRandom);
            tv.setValue(valueRandom);
    		((TimeSeriesValue) s).getTimeValues().add(tv);
        }
        reorderTimeSeries(s);
    }
	
	@Override
    public void addMultipleRandomTimeValues_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax, int count) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        addMultipleRandomTimeValues(s, timeMin, timeMax, valueMin, valueMax, count);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

    
	@Override
    //duplicates allowed
    public void appendTimeSeries(TimeSeries s, TimeSeries s1) {
		if (!(s instanceof TimeSeriesValue) || !(s1 instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().addAll(((TimeSeriesValue) s1).getTimeValues());
        reorderTimeSeries(s);
    }
    
	@Override
    public void appendTimeSeries_File(String tsInputPath, String tsAppendPath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        TimeSeries s1 = readInTSFromFile(tsAppendPath);
        appendTimeSeries(s, s1);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
    
	@Override
    //no time duplicates allowed
    public void mergeTimeSeries(TimeSeries s, TimeSeries s1) {
		if (!(s instanceof TimeSeriesValue) || !(s1 instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        for (TimeValue tv1 : ((TimeSeriesValue) s1).getTimeValues())
        	if(!existTimeValue(s, tv1.getTime(), tv1.getValue())) {
        		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
                tv.setTime(tv1.getTime());
                tv.setValue(tv1.getValue());
        		((TimeSeriesValue) s).getTimeValues().add(tv);
        	}
        reorderTimeSeries(s);
    }
    
	@Override
    public void mergeTimeSeries_File(String tsInputPath, String tsMergePath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        TimeSeries s1 = readInTSFromFile(tsMergePath);
        mergeTimeSeries(s, s1);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
    
	@Override
    public void replaceTimeSeries(TimeSeries s, TimeSeries s1) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().clear();
        appendTimeSeries(s, s1);
        reorderTimeSeries(s);
    }

	@Override
    public void replaceTimeSeries_File(String tsInputPath, String tsReplacePath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        TimeSeries s1 = readInTSFromFile(tsReplacePath);
        replaceTimeSeries(s, s1);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	//TODO: EDIT OPERATORS
	
	
	@Override
    public void changeValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() == time) {
	            tv.setValue(value);
	            break;
	        }
	}
    
	@Override
    public void changeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeValue(s, time, value);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
	
	@Override
	public void changeTime(TimeSeries s, double timeOld, double timeNew) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            break;
	        }
		reorderTimeSeries(s);
	}
	
	@Override
	public void changeTime_File(String tsInputPath, String tsOutputPath, double timeOld, double timeNew) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeTime(s, timeOld, timeNew);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeValueWithRandom(TimeSeries s, double time, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
		double valueRandom = r.nextDouble(valueMin, valueMax);
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() == time) {
	            tv.setValue(valueRandom);
	            break;
	        }
	}
	
	@Override
	public void changeValueWithRandom_File(String tsInputPath, String tsOutputPath, double time, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeValueWithRandom(s, time, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeWithRandom(TimeSeries s, double timeMin, double timeMax, double time) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
		double timeRandom = r.nextDouble(timeMin, timeMax);
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
	        if (tv.getTime() == time) {
	            tv.setTime(timeRandom);
	            break;
	        }
	    reorderTimeSeries(s);
	}
	
	@Override
	public void changeTimeWithRandom_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double time) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeTimeWithRandom(s, timeMin, timeMax, time);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeAndValue(TimeSeries s, double timeOld, double timeNew, double valueNew) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            tv.setValue(valueNew);
	            break;
	        }
	    reorderTimeSeries(s);
	}

	@Override
	public void changeTimeAndValue_File(String tsInputPath, String tsOutputPath, double timeOld, double timeNew, double valueNew) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeTimeAndValue(s, timeOld, timeNew, valueNew);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeWithRandomAndValue(TimeSeries s, double timeOld, double timeMin, double timeMax, double valueNew) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    Random r = new Random();
	    double timeRandom = r.nextDouble(timeMin, timeMax);
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeRandom);
	            tv.setValue(valueNew);
	            break;
	        }
	    reorderTimeSeries(s);
	}
	
	@Override
	public void changeTimeWithRandomAndValue_File(String tsInputPath, String tsOutputPath, double timeOld, double timeMin, double timeMax, double valueNew) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeTimeWithRandomAndValue(s, timeOld, timeMin, timeMax, valueNew);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public void changeTimeAndValueWithRandom(TimeSeries s, double timeOld, double timeNew, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    Random r = new Random();
	    double valueRandom = r.nextDouble(valueMin, valueMax);
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            tv.setValue(valueRandom);
	            break;
	        }
	    reorderTimeSeries(s);
	}
	
	@Override
	public void changeTimeAndValueWithRandom_File(String tsInputPath, String tsOutputPath, double timeOld, double timeNew, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeTimeAndValueWithRandom(s, timeOld, timeNew, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeWithRandomAndValueWithRandom(TimeSeries s, double timeOld, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
	    double timeRandom = r.nextDouble(timeMin, timeMax);
	    double valueRandom = r.nextDouble(valueMin, valueMax);
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeRandom);
	            tv.setValue(valueRandom);
	            break;
	        }
	    reorderTimeSeries(s);
	}
	
	@Override
	public void changeTimeWithRandomAndValueWithRandom_File(String tsInputPath, String tsOutputPath, double timeOld, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeTimeWithRandomAndValueWithRandom(s, timeOld, timeMin, timeMax, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeARandomTimeValue(TimeSeries s, double timeNew, double valueNew) {
	    TimeValue tvRandom = selectRandomTimeValue(s);
	    if (tvRandom != null) {
	        tvRandom.setTime(timeNew);
	        tvRandom.setValue(valueNew);
	    }
	    reorderTimeSeries(s);
	}
	
	@Override
	public void changeARandomTimeValue_File(String tsInputPath, String tsOutputPath, double timeNew, double valueNew) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeARandomTimeValue(s, timeNew, valueNew);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeARandomTimeValueWithRandomTimeValue(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax) {
	    TimeValue randomTV = selectRandomTimeValue(s);
	    Random r = new Random();
	    double timeRandom = r.nextDouble(timeMin, timeMax);
	    double valueRandom = r.nextDouble(valueMin, valueMax);
	    if (randomTV != null) {
	        randomTV.setTime(timeRandom);
	        randomTV.setValue(valueRandom);
	    }
	    reorderTimeSeries(s);
	}
	
	@Override
	public void changeARandomTimeValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeARandomTimeValueWithRandomTimeValue(s, timeMin, timeMax, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeMultipleTimeValues(TimeSeries s, List<Double> timesOld, List<Double> timesNew, List<Double> valuesNew) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if(timesOld.size() != valuesNew.size() || timesOld.size() != timesNew.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < timesOld.size(); i++)
    	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
		        if (tv.getTime() == timesOld.get(i)) {
		            tv.setTime(timesNew.get(i));
		            tv.setValue(valuesNew.get(i));
		            continue;
		        }
        reorderTimeSeries(s);
    }
	
	@Override
	public void changeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> timesOld, List<Double> timesNew, List<Double> valuesNew) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeMultipleTimeValues(s, timesOld, timesNew, valuesNew);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeries s, List<Double> timesOld, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
        for (int i = 0; i < timesOld.size(); i++)
    	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
		        if (tv.getTime() == timesOld.get(i)) {
		        	double timeRandom = r.nextDouble(timeMin, timeMax);
				    double valueRandom = r.nextDouble(valueMin, valueMax);
		            tv.setTime(timeRandom);
		            tv.setValue(valueRandom);
		            continue;
		        }  
        reorderTimeSeries(s);
    }
	
	@Override
	public void changeMultipleTimeValuesWithRandomTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> timesOld, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeMultipleTimeValuesWithRandomTimeValues(s, timesOld, timeMin, timeMax, valueMin, valueMax);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
    public void changeTimeLate(TimeSeries s, double eps, double time, double timeMaxDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        Random r = new Random();
        for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() == time) {
	        	double timeNew = time + (double) (r.nextDouble() * (timeMaxDomain - time - eps) + eps);
	        	tv.setTime(timeNew);	            
	            break;
	        } 
        reorderTimeSeries(s);
    }
    
	@Override
    public void changeTimeLate_File(String tsInputPath, String tsOutputPath, double eps, double time, double timeMaxDomain) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeTimeLate(s, eps, time, timeMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
	
	@Override
	public void changeRandomTimeLate(TimeSeries s, double eps, double timeMaxDomain) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
        	double timeOld = randomTV.getTime();
        	double timeNew = timeOld + (double) (r.nextDouble() * (timeMaxDomain - timeOld - eps) + eps);
            randomTV.setTime(timeNew);
        }
        reorderTimeSeries(s);
    }
	
	@Override
	public void changeRandomTimeLate_File(String tsInputPath, String tsOutputPath, double eps, double timeMaxDomain) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeRandomTimeLate(s, eps, timeMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeMultipleTimeLate(TimeSeries s, double eps, List<Double> times, double timeMaxDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        Random r = new Random();
		for (int i = 0; i < times.size(); i++)
    	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
		        if (tv.getTime() == times.get(i)) {
		        	double timeOld = times.get(i);
		        	double timeNew = timeOld + (double) (r.nextDouble() * (timeMaxDomain - timeOld - eps) + eps);
		        	tv.setTime(timeNew);	  
		        	continue;
		        }    
        reorderTimeSeries(s);
	}
	
	@Override
	public void changeMultipleTimeLate_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double timeMaxDomain) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeMultipleTimeLate(s, eps, times, timeMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
    public void changeTimeEarly(TimeSeries s, double eps, double time, double timeMinDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        Random r = new Random();
        for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
	        if (tv.getTime() == time) {
	        	double timeNew = time - (double) (r.nextDouble() * (time - timeMinDomain - eps) + eps);
	            tv.setTime(timeNew);
	            break;
	        }
        reorderTimeSeries(s);
    }
    
	@Override
    public void changeTimeEarly_File(String tsInputPath, String tsOutputPath, double eps, double time, double timeMinDomain) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeTimeEarly(s, eps, time, timeMinDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
    
	@Override
    public void changeRandomTimeEarly(TimeSeries s, double eps, double timeMinDomain) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
        	double timeOld = randomTV.getTime();
        	double timeNew = timeOld - (double) (r.nextDouble() * (timeOld - timeMinDomain - eps) + eps);
            randomTV.setTime(timeNew);
        }
        reorderTimeSeries(s);
    }
    
	@Override
    public void changeRandomTimeEarly_File(String tsInputPath, String tsOutputPath, double eps, double timeMinDomain) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeRandomTimeEarly(s, eps, timeMinDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
	public void changeMultipleTimeEarly(TimeSeries s, double eps, List<Double> times, double timeMinDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        Random r = new Random();
		for (int i = 0; i < times.size(); i++) 
    	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
		        if (tv.getTime() == times.get(i)) {
		        	double timeOld = times.get(i);
		        	double timeNew = timeOld - (double) (r.nextDouble() * (timeOld - timeMinDomain - eps) + eps);
		        	tv.setTime(timeNew);	  
		        	continue;
		        }
        reorderTimeSeries(s);
	}
	
	@Override
    public void changeMultipleTimeEarly_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double timeMinDomain) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeMultipleTimeEarly(s, eps, times, timeMinDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
	
	@Override
    public void changeValueCoarse(TimeSeries s, double eps, double time, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
        for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
	        if (tv.getTime() == time) {
	            double valueNew = (r.nextBoolean()) ? 
	            		(r.nextDouble() * (Double.MAX_VALUE - valueMaxDomain - eps) + valueMaxDomain + eps)
	                    : (r.nextDouble() * (valueMinDomain - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
	            tv.setValue(valueNew);
	            break;
	        }
    }
	
	@Override
    public void changeValueCoarse_File(String tsInputPath, String tsOutputPath, double eps, double time, double valueMinDomain, double valueMaxDomain) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeValueCoarse(s, eps, time, valueMinDomain, valueMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
    
	@Override
    public void changeRandomValueCoarse(TimeSeries s, double eps, double valueMinDomain, double valueMaxDomain) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            double valueNew = (r.nextBoolean()) ? 
            		(r.nextDouble() * (Double.MAX_VALUE - valueMaxDomain - eps) + valueMaxDomain + eps)
                    : (r.nextDouble() * (valueMinDomain - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
            randomTV.setValue(valueNew);
        }
    }
    
	@Override
    public void changeRandomValueCoarse_File(String tsInputPath, String tsOutputPath, double eps, double valueMinDomain, double valueMaxDomain) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeRandomValueCoarse(s, eps, valueMinDomain, valueMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
	public void changeMultipleValueCoarse(TimeSeries s, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
		for (int i = 0; i < times.size(); i++)
    	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
		        if (tv.getTime() == times.get(i)) {
		        	double valueNew = (r.nextBoolean()) ?
		        			(r.nextDouble() * (Double.MAX_VALUE - valueMaxDomain - eps) + valueMaxDomain + eps)
		                    : (r.nextDouble() * (valueMinDomain - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
		            tv.setValue(valueNew);
		        	continue;
		        }
        reorderTimeSeries(s);
	}
	
	@Override
	public void changeMultipleValueCoarse_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeMultipleValueCoarse(s, eps, times, valueMinDomain, valueMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public void changeValueSubtle(TimeSeries s, double eps, double time, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
        for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) 
	        if (tv.getTime() == time) {
	            double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (valueMaxDomain - eps) + eps)
	                    : (r.nextDouble() * (eps - valueMinDomain) - eps);
	            tv.setValue(valueNew);
	            break;
	        }
    }
	
	@Override
	public void changeValueSubtle_File(String tsInputPath, String tsOutputPath, double eps, double time, double valueMinDomain, double valueMaxDomain) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeValueSubtle(s, eps, time, valueMinDomain, valueMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeRandomValueSubtle(TimeSeries s, double eps, double valueMinDomain, double valueMaxDomain) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (valueMaxDomain - eps) + eps)
                    : (r.nextDouble() * (eps - valueMinDomain) - eps);
            randomTV.setValue(valueNew);
        }
    }
	
	@Override
	public void changeRandomValueSubtle_File(String tsInputPath, String tsOutputPath, double eps, double valueMinDomain, double valueMaxDomain) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeRandomValueSubtle(s, eps, valueMinDomain, valueMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public void changeMultipleValueSubtle(TimeSeries s, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		Random r = new Random();
		for (int i = 0; i < times.size(); i++)
    	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
		        if (tv.getTime() == times.get(i)) {
		        	double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (valueMaxDomain - eps) + eps)
		                    : (r.nextDouble() * (eps - valueMinDomain) - eps);
		            tv.setValue(valueNew);
		        	continue;
		        }
        reorderTimeSeries(s);
	}

	@Override
	public void changeMultipleValueSubtle_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeMultipleValueSubtle(s, eps, times, valueMinDomain, valueMaxDomain);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	
	
	
	
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO: REMOVE OPERATORS
	
	@Override
	public void removeTimeValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
    }
	
	@Override
	public void removeTimeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    removeTimeValue(s, time, value);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
    public void removeTimeValue(TimeSeries s, TimeValue tvToRemove) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");			
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && (tv.getValue() == tvToRemove.getValue()));
    }
    
	@Override
    public void removeTimeValue_File(String tsInputPath, String tsOutputPath, TimeValue tvToRemove) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValue(s, tvToRemove);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeRandomTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null)
        	((TimeSeriesValue) s).getTimeValues().remove(randomTV);
    }
    
	@Override
    public void removeRandomTimeValue_File(String tsInputPath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeRandomTimeValue(s);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
	
	@Override
    public void removeMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if(times.size() != values.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i=0; i<times.size(); i++) {
            double time = times.get(i);
            double value = values.get(i);
    		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }
    
	@Override
    public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> times, List<Double> values) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, times, values);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeMultipleTimeValues(TimeSeries s, List<TimeValue> timeValues) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
    	for (int i=0; i<timeValues.size(); i++) {
    		double time = timeValues.get(i).getTime();
            double value = timeValues.get(i).getValue();
    		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }
    
	@Override
    public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<TimeValue> timeValues) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, timeValues);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeMultipleTimeValues(TimeSeries s, TimeValue... timeValues) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
        for (TimeValue tvToRemove: timeValues)
    		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == tvToRemove.getValue());
    }

	@Override
    public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, TimeValue... timeValues) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    removeMultipleTimeValues(s, timeValues);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeAllTimeValues(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().clear();
    }
    
	@Override
    public void removeAllTimeValues_File(String tsInputPath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeAllTimeValues(s);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesBeforeTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() < time);
    }
    
	@Override
    public void removeTimeValuesBeforeTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesBeforeTime(s, time);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesAfterTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() > time);
    }
    
	@Override
    public void removeTimeValuesAfterTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesAfterTime(s, time);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesBelowValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getValue() < value);
    }
    
	@Override
    public void removeTimeValuesBelowValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesBelowValue(s, value);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesAboveValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getValue() > value);
    }
    
	@Override
    public void removeTimeValuesAboveValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesAboveValue(s, value);
		writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO: UTILS OPERATORS
    

	@Override
	public void printTimeSeries(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		System.out.print("[");
		var first = true;
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (!first)
				System.out.print(", ");
			System.out.print("(" + tv.getTime() + ":" + tv.getValue() + ")");
			first = false;
		}
		System.out.print("]\n");
	}
	
	@Override
	public void printTimeSeries_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    printTimeSeries(s);
	}

	@Override	
    public void reorderTimeSeries(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		List<TimeValue> timeValues = new ArrayList<>(((TimeSeriesValue) s).getTimeValues());
	    timeValues.sort(Comparator.comparingDouble(TimeValue::getTime));
	    ((TimeSeriesValue) s).getTimeValues().clear();
	    for (TimeValue tv: timeValues)
	    	((TimeSeriesValue) s).getTimeValues().add(tv);
	}

	@Override
	public void reorderTimeSeries_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    reorderTimeSeries(s);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}
	
	@Override
	public TimeValue selectRandomTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    Random r = new Random();
	    List<TimeValue> timeValues = ((TimeSeriesValue) s).getTimeValues();
	    if (!timeValues.isEmpty()) {
	        int randomIndex = r.nextInt(timeValues.size());
	        return timeValues.get(randomIndex);
	    }
	    return null;
	}
	
	@Override
	public TimeValue selectRandomTimeValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return selectRandomTimeValue(s);
	}

	@Override
	public boolean existTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
	    	if (tv.getTime() == time)
	            return true;
	    return false;
	}
	
	@Override
	public boolean existTime_File(String tsInputPath, double time) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return existTime(s, time);
	}
	
	@Override
	public boolean existValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getValue() == value)
	            return true;
	    return false;
	}
	
	@Override
	public boolean existValue_File(String tsInputPath, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return existValue(s, value);
	}
	
	@Override
	public boolean existTimeValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
	    	if (tv.getTime() == time && tv.getValue() == value)
	            return true;
	    return false;
	}
	
	@Override
	public boolean existTimeValue_File(String tsInputPath, double time, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return existTimeValue(s, time, value);
	}

	@Override
	public List<Double> getAllTimes(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    List<Double> times = new ArrayList<>();
	    for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
	    	times.add(tv.getTime());
	    return times;
	}
	
	@Override
	public List<Double> getAllTimes_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getAllTimes(s);
	}

	@Override
	public List<Double> getAllValues(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    List<Double> values = new ArrayList<Double>();
	    for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
	    	values.add(tv.getValue());
	    return values;
	}
	
	@Override
	public List<Double> getAllValues_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getAllValues(s);
	}

	@Override
	public List<Double> getTimesInRange(TimeSeries s, double timeMin, double timeMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    List<Double> times = new ArrayList<>();
    	for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
    		if (tv.getTime() >= timeMin && tv.getTime() <= timeMax)
    			times.add(tv.getTime());
	    return times;
	}
	
	@Override
	public List<Double> getTimesInRange_File(String tsInputPath, double timeMin, double timeMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getTimesInRange(s, timeMin, timeMax);
	}
	
	@Override
	public List<Double> getValuesInRange(TimeSeries s, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    List<Double> values = new ArrayList<Double>();
    	for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
    		if (tv.getValue() >= valueMin && tv.getValue() <= valueMax)
    			values.add(tv.getValue());
	    return values;
	}
	
	@Override
	public List<Double> getValuesInRange_File(String tsInputPath, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getValuesInRange(s, valueMin, valueMax);
	}
	
	@Override
	public List<TimeValue> getTimeValuesInRange(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    List<TimeValue> timeValues = new ArrayList<>();
	    for (TimeValue tv: ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() >= timeMin && tv.getTime() <= timeMax && tv.getValue() >= valueMin && tv.getValue() <= valueMax)
	            timeValues.add(tv);
	    return timeValues;
	}
	
	@Override
	public List<TimeValue> getTimeValuesInRange_File(String tsInputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getTimeValuesInRange(s, timeMin, timeMax, valueMin, valueMax);
	}

	@Override
	public double getValueAt(TimeSeries s, int index) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size())
			return ((TimeSeriesValue) s).getTimeValues().get(index).getValue();
		else throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public double getValueAt_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getValueAt(s, index);
	}

	@Override
	public double getTimeAt(TimeSeries s, int index) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size())
			return ((TimeSeriesValue) s).getTimeValues().get(index).getTime();
		else throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public double getTimeAt_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getTimeAt(s, index);
	}

	@Override
	public TimeValue getTimeValueAt(TimeSeries s, int index) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size())
			return ((TimeSeriesValue) s).getTimeValues().get(index);
		else 
			throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public TimeValue getTimeValueAt_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getTimeValueAt(s, index);
	}

	@Override
	public double getMaxTime(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		return ((TimeSeriesValue) s).getTimeValues().stream()
	            .mapToDouble(TimeValue::getTime)
	            .max()
	            .orElseThrow(NoSuchElementException::new);
	}
	
	@Override
	public double getMaxTime_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getMaxTime(s);
	}

	@Override
	public double getMinTime(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		return ((TimeSeriesValue) s).getTimeValues().stream()
		            .mapToDouble(TimeValue::getTime)
		            .min()
		            .orElseThrow(NoSuchElementException::new);
	}
	
	@Override
	public double getMinTime_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getMinTime(s);
	}

	@Override
	public double getMaxValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		return ((TimeSeriesValue) s).getTimeValues().stream()
		            .mapToDouble(tv -> tv.getValue())
		            .max()
		            .orElseThrow(NoSuchElementException::new);
	}
	
	@Override
	public double getMaxValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getMaxValue(s);
	}

	@Override
	public double getMinValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		return ((TimeSeriesValue) s).getTimeValues().stream()
		            .mapToDouble(tv -> tv.getValue())
		            .min()
		            .orElseThrow(NoSuchElementException::new);
	}
	
	@Override
	public double getMinValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getMinValue(s);
	}
	
	@Override
	public TimeValue getNextTimeValue(TimeSeries s, TimeValue tv) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		int index = ((TimeSeriesValue) s).getTimeValues().indexOf(tv);
	    if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size() - 1)
	        return ((TimeSeriesValue) s).getTimeValues().get(index + 1);
        throw new IllegalArgumentException("Index is out of range");
	}

	@Override
	public TimeValue getNextTimeValue_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getNextTimeValue(s, tv);
	}
	
	@Override
	public double getNextTime(TimeSeries s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}
	
	@Override
	public double getNextTime_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getNextTime(s, tv);
	}

	@Override
	public double getNextValue(TimeSeries s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}
	
	@Override
	public double getNextValue_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getNextValue(s, tv);
	}
	
	@Override
	public TimeValue getNextTimeValue(TimeSeries s, int index) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size() - 1)
	        return ((TimeSeriesValue) s).getTimeValues().get(index + 1);
        throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public TimeValue getNextTimeValue_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getNextTimeValue(s, index);
	}

	@Override
	public double getNextTime(TimeSeries s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}
	
	@Override
	public double getNextTime_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getNextTime(s, index);
	}

	@Override
	public double getNextValue(TimeSeries s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}
	
	@Override
	public double getNextValue_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getNextValue(s, index);
	}

	@Override
	public TimeValue getPreviousTimeValue(TimeSeries s, TimeValue tv) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		int index = ((TimeSeriesValue) s).getTimeValues().indexOf(tv);
	    if (index > 0)
	        return ((TimeSeriesValue) s).getTimeValues().get(index - 1);
        throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public TimeValue getPreviousTimeValue_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getPreviousTimeValue(s, tv);
	}
	
	@Override
	public double getPreviousTime(TimeSeries s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}
	
	@Override
	public double getPreviousTime_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getPreviousTime(s, tv);
	}
	
	@Override
	public double getPreviousValue(TimeSeries s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}
	
	@Override
	public double getPreviousValue_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getPreviousValue(s, tv);
	}

	@Override
	public TimeValue getPreviousTimeValue(TimeSeries s, int index) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    if (index > 0 && index < ((TimeSeriesValue) s).getTimeValues().size())
	        return ((TimeSeriesValue) s).getTimeValues().get(index - 1);
	    return null;
	}
	
	@Override
	public TimeValue getPreviousTimeValue_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getPreviousTimeValue(s, index);
	}

	@Override
	public double getPreviousTime(TimeSeries s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}
	
	@Override
	public double getPreviousTime_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getPreviousTime(s, index);
	}

	@Override
	public double getPreviousValue(TimeSeries s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}
	
	@Override
	public double getPreviousValue_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getPreviousValue(s, index);
	}

	@Override
	public double getFirstTime(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (!((TimeSeriesValue) s).getTimeValues().isEmpty())
			return ((TimeSeriesValue) s).getTimeValues().get(0).getTime();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getFirstTime_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getFirstTime(s);
	}

	@Override
	public double getFirstValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (!((TimeSeriesValue) s).getTimeValues().isEmpty())
			return ((TimeSeriesValue) s).getTimeValues().get(0).getValue();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getFirstValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getFirstValue(s);
	}
	
	@Override
	public TimeValue getFirstTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (!((TimeSeriesValue) s).getTimeValues().isEmpty())
			return ((TimeSeriesValue) s).getTimeValues().get(0);
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public TimeValue getFirstTimeValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getFirstTimeValue(s);
	}

	@Override
	public double getLastTime(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (!((TimeSeriesValue) s).getTimeValues().isEmpty())
			return ((TimeSeriesValue) s).getTimeValues().get(((TimeSeriesValue) s).getTimeValues().size() - 1).getTime();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getLastTime_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getLastTime(s);
	}

	@Override
	public double getLastValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (!((TimeSeriesValue) s).getTimeValues().isEmpty())
			return ((TimeSeriesValue) s).getTimeValues().get(((TimeSeriesValue) s).getTimeValues().size() - 1).getValue();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getLastValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getLastValue(s);
	}

	@Override
	public TimeValue getLastTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		if (!((TimeSeriesValue) s).getTimeValues().isEmpty())
			return ((TimeSeriesValue) s).getTimeValues().get(((TimeSeriesValue) s).getTimeValues().size() - 1);
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public TimeValue getLastTimeValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getLastTimeValue(s);
	}

	@Override
	public TimeSeries copyTimeSeries(TimeSeries s) {
	    TimeSeries copy = ReadFactory.eINSTANCE.createTimeSeriesValue();
		if (!(s instanceof TimeSeriesValue) || !(copy instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
	        TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	        tvCopy.setTime(tv.getTime());
	        tvCopy.setValue(tv.getValue());
	        ((TimeSeriesValue) copy).getTimeValues().add(tvCopy);
	    }
	    return copy;
	}
	
	@Override
	public TimeSeries copyTimeSeries_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return copyTimeSeries(s);
	}

	@Override
	public void setAllTimesToZero(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        tv.setTime(0);
	}
	
	@Override
	public void setAllTimesToZero_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllTimesToZero(s);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}	

	@Override
	public void setAllValuesToZero(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        tv.setValue(0);
	}
	
	@Override
	public void setAllValuesToZero_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllValuesToZero(s);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}	

	@Override
	public void setAllToZero(TimeSeries s) {
	    setAllTimesToZero(s);
	    setAllValuesToZero(s);
	}
	
	@Override
	public void setAllToZero_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllToZero(s);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public void setAllTimesToTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        tv.setTime(time);
	}
	
	@Override
	public void setAllTimesToTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllTimesToTime(s, time);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public void setAllValuesToValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        tv.setValue(value);
	}
	
	@Override
	public void setAllValuesToValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllValuesToValue(s, value);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public void setAllToTimeValue(TimeSeries s, double time, double value) {
	    setAllTimesToTime(s, time);
	    setAllValuesToValue(s, value);
	}
	
	@Override
	public void setAllToTimeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllToTimeValue(s, time, value);
	    writeInTSToFile(((TimeSeriesValue) s), tsOutputPath);
	}

	@Override
	public TimeSeries getSubTimeSeriesInTimeRange(TimeSeries s, double timeMin, double timeMax) {
	    TimeSeries subSeries = ReadFactory.eINSTANCE.createTimeSeriesValue();
		if (!(s instanceof TimeSeriesValue) || !(subSeries instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
	        if (tv.getTime() >= timeMin && tv.getTime() <= timeMax) {
	            TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	            tvCopy.setTime(tv.getTime());
	            tvCopy.setValue(tv.getValue());
	            ((TimeSeriesValue) subSeries).getTimeValues().add(tvCopy);
	        }
	    return subSeries;
	}
	
	@Override
	public TimeSeries getSubTimeSeriesInTimeRange_File(String tsInputPath, double timeMin, double timeMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getSubTimeSeriesInTimeRange(s, timeMin, timeMax);
	}

	@Override
	public int countTimeValues(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		return ((TimeSeriesValue) s).getTimeValues().size();
	}
	
	@Override
	public int countTimeValues_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return countTimeValues(s);
	}

	@Override
	public boolean isEmpty(TimeSeries s) {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		return ((TimeSeriesValue) s).getTimeValues().size() == 0;
	}
	
	@Override
	public boolean isEmpty_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return isEmpty(s);
	}
	
	@Override
	public void writeInTSToFile(TimeSeries s, String filePath) throws IOException {
		if (!(s instanceof TimeSeriesValue))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		reorderTimeSeries(s);
		List<String> lines = ((TimeSeriesValue) s).getTimeValues().stream().map(tv -> tv.getTime() + " " + tv.getValue()).collect(Collectors.toList());
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	@Override
	public TimeSeriesValue readInTSFromFile(String filePath) throws IOException {
		TimeSeriesValue TimeSeriesValue = ReadFactory.eINSTANCE.createTimeSeriesValue();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		for (String line : lines) {
			String[] parts = line.trim().split("\\s+");
			if (parts.length != 2)
				throw new IOException("Invalid line format: " + line);
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(Double.parseDouble(parts[0].trim()));
			double value = (Double.parseDouble(parts[1].trim()));
			tv.setValue(value);
			TimeSeriesValue.getTimeValues().add(tv);
		}
		reorderTimeSeries(TimeSeriesValue);
		return TimeSeriesValue;
	}
	
	@Override
	public TimeSeriesValue readOutTSFromFile(String filePath) throws IOException {
	    TimeSeriesValue timeSeriesValue = ReadFactory.eINSTANCE.createTimeSeriesValue();
	    List<String> lines = Files.readAllLines(Paths.get(filePath));
	    if (lines.isEmpty() || !lines.get(0).startsWith("\"TIME\"")) {
	        throw new IOException("Invalid file format: missing or incorrect header");
	    }
	    for (int i = 1; i < lines.size(); i++) { //Skip header
	        String line = lines.get(i).trim();
	        String[] parts = line.split(",");
	        if (parts.length != 2)
	            throw new IOException("Invalid line format: " + line);
	        try {
	            double time = Double.parseDouble(parts[0].trim());
	            double value = Double.parseDouble(parts[1].trim());
	            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
	            tv.setTime(time);
	            tv.setValue(value);
	            timeSeriesValue.getTimeValues().add(tv);
	        } catch (NumberFormatException e) {
	            throw new IOException("Invalid numeric format in line: " + line, e);
	        }
	    }
	    reorderTimeSeries(timeSeriesValue);
	    return timeSeriesValue;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************TODO: SIMULATION OPERATORS (Proteus)********************/
	
	//this method runs a test case (i.e., a set of mutated TimeSeriesValue) on a Proteus component
	//output ports are needed to interact with the GUI to activate the proper simulation graphs
	public void runTestCase(Path componentPath, Path testCasePath, List<String> outputPorts) throws Exception {
		
		//params checks
		if(componentPath == null || testCasePath == null || !(Files.exists(componentPath)) || !(Files.exists(testCasePath)))
			throw new Exception("File path does not exist");
		if(outputPorts.isEmpty()||outputPorts.size()>9)
			throw new Exception("Num output ports is out of range"); //up to 9 output ports are supported by the UI
		
		//backup original component TimeSeriesValue
		backupOriginalTS(componentPath);
		
        //copy mutated TimeSeriesValue into component folder   
		setupTestCase(componentPath, testCasePath);        
        
        //execute test case (i.e., component is run with mutated TimeSeriesValue)
        runProteus(componentPath, outputPorts);
        
        //save outputs generated by output ports to test case folder
        saveTestCaseOutput(componentPath, testCasePath);
        
        //restore component state for next execution, removing any file originated from last test case execution
        restoreOriginalTS(componentPath);	        
	}
	
	//this method performs a backup of the original TimeSeriesValue (.txt) feeding the input ports of a Proteus component
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
	
	//this method moves the mutated TimeSeriesValue into the Proteus component folder, to be injected to input ports
	private static void setupTestCase(Path componentPath, Path testCasePath) throws IOException {
		Path TimeSeriesValue = testCasePath.resolve("inputs");
        Files.walkFileTree(TimeSeriesValue, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path destFile = componentPath.resolve(TimeSeriesValue.relativize(file));
                Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path destDir = componentPath.resolve(TimeSeriesValue.relativize(dir));
                if (Files.notExists(destDir)) {
                    Files.createDirectory(destDir);
                }
                return FileVisitResult.CONTINUE;
            }
        });
	}
	
	//this method runs Proteus simulation for the component.
	//it uses output ports names to save output files once the simulation is completed
	@SuppressWarnings("deprecation")
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
	private static void saveTestCaseOutput(Path componentPath, Path testCasePath) throws IOException {
	    Path outputs = testCasePath.resolve("outputs");
	    Files.walkFileTree(componentPath, new SimpleFileVisitor<Path>() {
	        @Override
	        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	            String fileName = file.getFileName().toString().toLowerCase();
	            if (fileName.endsWith(".dat")) {
	                Path destFile = outputs.resolve(componentPath.relativize(file));
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
	
	//this method cleans the component folder from any file generated by a mutated simulation and restore the original TimeSeriesValue
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
	
	
	
	
	

	

	//TODO: DISCOVERY OPERATORS	
		@Override
		public DiscoveryOutcome isCommission(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeriesValue) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeriesValue) tsMutated).getTimeValues().size();
		    if (originalSize < mutatedSize) {
		    	outcome.setHasError(true);
		    	outcome.setOutcomeMsg("Commission error found. Expected Size: " + originalSize + ", Actual Size: " + mutatedSize);
		    }
		    return outcome;
		}

		
		@Override
		public DiscoveryOutcome isCommission_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return isCommission(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isOmission(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeriesValue) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeriesValue) tsMutated).getTimeValues().size();
		    if (originalSize > mutatedSize) {
		    	outcome.setHasError(true);
		    	outcome.setOutcomeMsg("Omission error found. Expected Size: " + originalSize + ", Actual Size: " + mutatedSize);
		    }
		    return outcome;
		}
		
		@Override
		public DiscoveryOutcome isOmission_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return isOmission(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeriesValue) tsOriginal).getTimeValues().size() != ((TimeSeriesValue) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedTime = 0;
		    double actualTime = 0;
			//for each original time, check the closest time among mutated timeseries and evaluate whether it is late according to an eps threshold
			for(int i=1; i<((TimeSeriesValue) tsOriginal).getTimeValues().size(); i++) {//i=1 as header is skipped
				TimeValue tv1 = ((TimeSeriesValue) tsOriginal).getTimeValues().get(i);
				double time1 = tv1.getTime();			
				double value1 = tv1.getValue();
				double closestDiffTime = Double.MAX_VALUE;
				for(int j=1; j<((TimeSeriesValue) tsMutated).getTimeValues().size(); j++) {//j=1 as header is skipped
					TimeValue tv2 = ((TimeSeriesValue) tsMutated).getTimeValues().get(i);
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
							expectedTime = time1;
							actualTime = time2; 
						}
						else
							break;//the timeseries are ordered so once the diff is getting bigger the cycle can stop
					}
				}
				if(closestDiffTime == 0)//no early/late detected for timeseries i, as diff time is 0
					continue;
				if(Math.abs(closestDiffTime)>eps && closestDiffTime<0){//if diff time bigger than epsilon and negative (mutated time is later), late detected
					outcome.setHasError(true);
					outcome.setOutcomeMsg("Late error found. Expected Time: " + expectedTime + ", Actual Time: " + actualTime);
			        return outcome;
				}
			}	
			return outcome;
		}
		
		@Override
		public DiscoveryOutcome isLate_File(String tsOriginalPath, String tsMutatedPath, double eps) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return isLate(s1, s2, eps);
		}
		
		@Override
		public DiscoveryOutcome isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeriesValue) tsOriginal).getTimeValues().size() != ((TimeSeriesValue) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedTime = 0;
		    double actualTime = 0;
			//for each original time, check the closest time among mutated timeserie and evaluate whether it is early according to an eps threshold
			for(int i=1; i<((TimeSeriesValue) tsOriginal).getTimeValues().size(); i++) {//i=1 as header is skipped
				TimeValue tv1 = ((TimeSeriesValue) tsOriginal).getTimeValues().get(i);
				double time1 = tv1.getTime();			
				double value1 = tv1.getValue();
				double closestDiffTime = Double.MAX_VALUE;
				for(int j=1; j<((TimeSeriesValue) tsMutated).getTimeValues().size(); j++) {//j=1 as header is skipped
					TimeValue tv2 = ((TimeSeriesValue) tsMutated).getTimeValues().get(i);
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
							expectedTime = time1;
							actualTime = time2; 
						}
						else
							break;//the timeseries are ordered so once the diff is getting bigger the cycle can stop
					}
				}
				if(closestDiffTime == 0)//no early/late detected for timeseries i, as diff time is 0
					continue;
				if(Math.abs(closestDiffTime)> eps && closestDiffTime>0) { //if diff time bigger than epsilon and positive (mutated time is earlier), early detected
					outcome.setHasError(true);
					outcome.setOutcomeMsg("Early error found. Expected Time: " + expectedTime + ", Actual Time: " + actualTime);
			        return outcome;
				}
			}	
			return outcome;
		}
		
		@Override
		public DiscoveryOutcome isEarly_File(String tsOriginalPath, String tsMutatedPath, double eps) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return isEarly(s1, s2, eps);
		}
		
		@Override
		public DiscoveryOutcome isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeriesValue) tsOriginal).getTimeValues().size() != ((TimeSeriesValue) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
			DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedValue = 0;
		    double actualValue = 0;
			for(int i=1; i<((TimeSeriesValue) tsOriginal).getTimeValues().size(); i++) { //i=1 as header is skipped
				TimeValue tv1 = ((TimeSeriesValue) tsOriginal).getTimeValues().get(i);
				TimeValue tv2 = ((TimeSeriesValue) tsMutated).getTimeValues().get(i);
				double valueOriginal = tv1.getValue();
				double valueMutated = tv2.getValue();
				double valueDiff = valueOriginal - valueMutated;
				//value subtle failure found if diff is bigger than eps and mutated value is out of boundary
				if(Math.abs(valueDiff)> eps && (valueMutated < valueMin || valueMutated > valueMax)) {
					expectedValue = valueOriginal;
					actualValue = valueMutated;
					outcome.setHasError(true);
					outcome.setOutcomeMsg("Value Coarse error found. Expected Value: " + expectedValue + ", Actual Value: " + actualValue);
					return outcome;
				}
			}	
			return outcome;
		}
		
		@Override
		public DiscoveryOutcome isValueCoarse_File(String tsOriginalPath, String tsMutatedPath, double eps, double valueMin, double valueMax) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
			return isValueCoarse(s1, s2, eps, valueMin, valueMax);
		}
		
		@Override
		public DiscoveryOutcome isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeriesValue) tsOriginal).getTimeValues().size() != ((TimeSeriesValue) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
			DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedValue = 0;
		    double actualValue = 0;		
			for(int i=1; i<((TimeSeriesValue) tsOriginal).getTimeValues().size(); i++) { //i=1 as header is skipped
				TimeValue tv1 = ((TimeSeriesValue) tsOriginal).getTimeValues().get(i);
				TimeValue tv2 = ((TimeSeriesValue) tsMutated).getTimeValues().get(i);
				double valueOriginal = tv1.getValue();
				double valueMutated = tv2.getValue();
				double valueDiff = valueOriginal - valueMutated;
				//value subtle failure found if diff is bigger than eps and mutated value is within boundary
				if(Math.abs(valueDiff)> eps && (valueMutated >= valueMin && valueMutated <= valueMax)) {
					expectedValue = valueOriginal;
					actualValue = valueMutated;
					outcome.setHasError(true);
					outcome.setOutcomeMsg("Value Subtle error found. Expected Value: " + expectedValue + ", Actual Value: " + actualValue);
					return outcome;
				}
			}
			return outcome;
		}
		
		@Override
		public DiscoveryOutcome isValueSubtle_File(String tsOriginalPath, String tsMutatedPath, double eps, double valueMin, double valueMax) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
			return isValueSubtle(s1, s2, eps, valueMin, valueMax);
		}
		
		@Override
		public DiscoveryOutcome areSameSize(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeriesValue) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeriesValue) tsMutated).getTimeValues().size();
		    if (originalSize == mutatedSize)
		    	outcome.setOutcomeMsg("The time series have the same size: " + originalSize);
		    else 
		    	outcome.setOutcomeMsg("The time series have different size. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    return outcome;
		}
		
		@Override
		public DiscoveryOutcome areSameSize_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return areSameSize(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isSmaller(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeriesValue) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeriesValue) tsMutated).getTimeValues().size();
		    if (originalSize < mutatedSize)
		    	outcome.setOutcomeMsg("The first time series has smaller size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    else 
		    	outcome.setOutcomeMsg("The first time series has same or bigger size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    return outcome;
		}
		
		@Override
		public DiscoveryOutcome isSmaller_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return isSmaller(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isBigger(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeriesValue) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeriesValue) tsMutated).getTimeValues().size();
		    if (originalSize > mutatedSize)
		    	outcome.setOutcomeMsg("The first time series has bigger size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    else 
		    	outcome.setOutcomeMsg("The first time series has same or smaller size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		   return outcome;
		    
		}
		
		@Override
		public DiscoveryOutcome isBigger_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeriesValue s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeriesValue s2 = readOutTSFromFile(tsMutatedPath);
		    return isBigger(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome hasTimeOutRange(TimeSeries tsMutated, double timeMin, double timeMax) {
			if (!(tsMutated instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    for (TimeValue tv : ((TimeSeriesValue) tsMutated).getTimeValues()) {
		        double time = tv.getTime();
		        if (time < timeMin || time > timeMax) {
		        	outcome.setHasError(true);
			    	outcome.setOutcomeMsg("The time series has following time as out of range (" + timeMin + "-" + timeMax + "): " + time);
			    	return outcome;
		        }
		    }
		    return outcome;
		}

		@Override
		public DiscoveryOutcome hasTimeOutRange_File(String tsMutatedPath, double timeMin, double timeMax) throws IOException {
			TimeSeriesValue s = readOutTSFromFile(tsMutatedPath);
		    return hasTimeOutRange(s, timeMin, timeMax);
		}
		
		@Override
		public DiscoveryOutcome hasValueOutRange(TimeSeries tsMutated, double valueMin, double valueMax) {
			if (!(tsMutated instanceof TimeSeriesValue) || !(tsMutated instanceof TimeSeriesValue))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    for (TimeValue tv : ((TimeSeriesValue) tsMutated).getTimeValues()) {
		        double value = tv.getValue();
		        if (value < valueMin || value > valueMax){
		        	outcome.setHasError(true);
			    	outcome.setOutcomeMsg("The time series has following value as out of range (" + valueMin + "-" + valueMax + "): " + value);
			    	return outcome;
		        }
		    }
		    return outcome;
		}

		@Override
		public DiscoveryOutcome hasValueOutRange_File(String tsMutatedPath, double valueMin, double valueMax) throws IOException {
			TimeSeriesValue s = readOutTSFromFile(tsMutatedPath);
		    return hasValueOutRange(s, valueMin, valueMax);
		}
		
	

}
