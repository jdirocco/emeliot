package emeliot.dsl.lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;




public abstract class EmeliotLib implements EmeliotService, EmeliotMutationService, EmeliotDiscoveryService {

	
	
	
	
	//TODO: ADD OPERATORS
	
	
	@Override
	public void addTimeAndValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(time);
		tv.setValue(value);
		((TimeSeries) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addTimeAndValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
		TimeSeries s = readInTSFromFile(tsInputPath);
		addTimeAndValue(s, time, value);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void addRandomTimeAndValue(TimeSeries s, double timeMin, double timeMax, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double timeRandom = r.nextDouble(timeMin, timeMax);
		tv.setTime(timeRandom);
		tv.setValue(value);
		((TimeSeries) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addRandomTimeAndValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addRandomTimeAndValue(s, timeMin, timeMax, value);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void addTimeAndRandomValue(TimeSeries s, double time, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double valueRandom = r.nextDouble(valueMin, valueMax);
		tv.setTime(time);
		tv.setValue(valueRandom);
		((TimeSeries) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addTimeAndRandomValue_File(String tsInputPath, String tsOutputPath, double time, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addTimeAndRandomValue(s, time, valueMin, valueMax);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void addRandomTimeAndRandomValue(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double timeRandom = r.nextDouble(timeMin, timeMax);
		double valueRandom = r.nextDouble(valueMin, valueMax);
		tv.setTime(timeRandom);
		tv.setValue(valueRandom);
		((TimeSeries) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}
	
	@Override
	public void addRandomTimeAndRandomValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addRandomTimeAndRandomValue(s, timeMin, timeMax, valueMin, valueMax);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void addMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if(times.size() != values.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < times.size(); i++) {
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(times.get(i));
            tv.setValue(values.get(i));
    		((TimeSeries) s).getTimeValues().add(tv);
        }
        reorderTimeSeries(s);
    }
	
	@Override
	public void addMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> times, List<Double> values) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    addMultipleTimeValues(s, times, values);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
    public void addMultipleRandomTimeValues(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax, int count) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        Random r = new Random();
        for (int i=0; i<count; i++) {
            double timeRandom = r.nextDouble(timeMin, timeMax);
            double valueRandom = r.nextDouble(valueMin, valueMax);
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(timeRandom);
            tv.setValue(valueRandom);
    		((TimeSeries) s).getTimeValues().add(tv);
        }
        reorderTimeSeries(s);
    }
	
	@Override
    public void addMultipleRandomTimeValues_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax, int count) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        addMultipleRandomTimeValues(s, timeMin, timeMax, valueMin, valueMax, count);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

    
	@Override
    //duplicates allowed
    public void appendTimeSeries(TimeSeries s, TimeSeries s1) {
		if (!(s instanceof TimeSeries) || !(s1 instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().addAll(((TimeSeries) s1).getTimeValues());
        reorderTimeSeries(s);
    }
    
	@Override
    public void appendTimeSeries_File(String tsInputPath, String tsAppendPath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        TimeSeries s1 = readInTSFromFile(tsAppendPath);
        appendTimeSeries(s, s1);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
    
	@Override
    //no time duplicates allowed
    public void mergeTimeSeries(TimeSeries s, TimeSeries s1) {
		if (!(s instanceof TimeSeries) || !(s1 instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        for (TimeValue tv1 : ((TimeSeries) s1).getTimeValues())
        	if(!existTimeValue(s, tv1.getTime(), tv1.getValue())) {
        		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
                tv.setTime(tv1.getTime());
                tv.setValue(tv1.getValue());
        		((TimeSeries) s).getTimeValues().add(tv);
        	}
        reorderTimeSeries(s);
    }
    
	@Override
    public void mergeTimeSeries_File(String tsInputPath, String tsMergePath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        TimeSeries s1 = readInTSFromFile(tsMergePath);
        mergeTimeSeries(s, s1);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
    
	@Override
    public void replaceTimeSeries(TimeSeries s, TimeSeries s1) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().clear();
        appendTimeSeries(s, s1);
        reorderTimeSeries(s);
    }

	@Override
    public void replaceTimeSeries_File(String tsInputPath, String tsReplacePath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        TimeSeries s1 = readInTSFromFile(tsReplacePath);
        replaceTimeSeries(s, s1);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	//TODO: EDIT OPERATORS
	
	
	@Override
    public void changeValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        if (tv.getTime() == time) {
	            tv.setValue(value);
	            break;
	        }
	}
    
	@Override
    public void changeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        changeValue(s, time, value);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
	
	@Override
	public void changeTime(TimeSeries s, double timeOld, double timeNew) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeValueWithRandom(TimeSeries s, double time, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
		double valueRandom = r.nextDouble(valueMin, valueMax);
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        if (tv.getTime() == time) {
	            tv.setValue(valueRandom);
	            break;
	        }
	}
	
	@Override
	public void changeValueWithRandom_File(String tsInputPath, String tsOutputPath, double time, double valueMin, double valueMax) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    changeValueWithRandom(s, time, valueMin, valueMax);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeWithRandom(TimeSeries s, double timeMin, double timeMax, double time) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
		double timeRandom = r.nextDouble(timeMin, timeMax);
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeAndValue(TimeSeries s, double timeOld, double timeNew, double valueNew) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeWithRandomAndValue(TimeSeries s, double timeOld, double timeMin, double timeMax, double valueNew) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    Random r = new Random();
	    double timeRandom = r.nextDouble(timeMin, timeMax);
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public void changeTimeAndValueWithRandom(TimeSeries s, double timeOld, double timeNew, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    Random r = new Random();
	    double valueRandom = r.nextDouble(valueMin, valueMax);
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeTimeWithRandomAndValueWithRandom(TimeSeries s, double timeOld, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
	    double timeRandom = r.nextDouble(timeMin, timeMax);
	    double valueRandom = r.nextDouble(valueMin, valueMax);
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeMultipleTimeValues(TimeSeries s, List<Double> timesOld, List<Double> timesNew, List<Double> valuesNew) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if(timesOld.size() != valuesNew.size() || timesOld.size() != timesNew.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < timesOld.size(); i++)
    	    for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeries s, List<Double> timesOld, double timeMin, double timeMax, double valueMin, double valueMax) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
        for (int i = 0; i < timesOld.size(); i++)
    	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
    public void changeTimeLate(TimeSeries s, double eps, double time, double timeMaxDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        Random r = new Random();
        for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeMultipleTimeLate(TimeSeries s, double eps, List<Double> times, double timeMaxDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        Random r = new Random();
		for (int i = 0; i < times.size(); i++)
    	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
    public void changeTimeEarly(TimeSeries s, double eps, double time, double timeMinDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        Random r = new Random();
        for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
	public void changeMultipleTimeEarly(TimeSeries s, double eps, List<Double> times, double timeMinDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        Random r = new Random();
		for (int i = 0; i < times.size(); i++) 
    	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
	
	@Override
    public void changeValueCoarse(TimeSeries s, double eps, double time, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
        for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
	public void changeMultipleValueCoarse(TimeSeries s, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
		for (int i = 0; i < times.size(); i++)
    	    for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public void changeValueSubtle(TimeSeries s, double eps, double time, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
        for (TimeValue tv : ((TimeSeries) s).getTimeValues()) 
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public void changeMultipleValueSubtle(TimeSeries s, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		Random r = new Random();
		for (int i = 0; i < times.size(); i++)
    	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
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
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	
	
	
	
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO: REMOVE OPERATORS
	
	@Override
	public void removeTimeValue(TimeSeries s, double time, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
    }
	
	@Override
	public void removeTimeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    removeTimeValue(s, time, value);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
    public void removeTimeValue(TimeSeries s, TimeValue tvToRemove) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");			
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && (tv.getValue() == tvToRemove.getValue()));
    }
    
	@Override
    public void removeTimeValue_File(String tsInputPath, String tsOutputPath, TimeValue tvToRemove) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValue(s, tvToRemove);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeRandomTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null)
        	((TimeSeries) s).getTimeValues().remove(randomTV);
    }
    
	@Override
    public void removeRandomTimeValue_File(String tsInputPath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeRandomTimeValue(s);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
	
	@Override
    public void removeMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if(times.size() != values.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i=0; i<times.size(); i++) {
            double time = times.get(i);
            double value = values.get(i);
    		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }
    
	@Override
    public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> times, List<Double> values) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, times, values);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeMultipleTimeValues(TimeSeries s, List<TimeValue> timeValues) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
    	for (int i=0; i<timeValues.size(); i++) {
    		double time = timeValues.get(i).getTime();
            double value = timeValues.get(i).getValue();
    		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }
    
	@Override
    public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<TimeValue> timeValues) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, timeValues);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeMultipleTimeValues(TimeSeries s, TimeValue... timeValues) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
        for (TimeValue tvToRemove: timeValues)
    		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == tvToRemove.getValue());
    }

	@Override
    public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, TimeValue... timeValues) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    removeMultipleTimeValues(s, timeValues);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeAllTimeValues(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().clear();
    }
    
	@Override
    public void removeAllTimeValues_File(String tsInputPath, String tsOutputPath) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeAllTimeValues(s);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesBeforeTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() < time);
    }
    
	@Override
    public void removeTimeValuesBeforeTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesBeforeTime(s, time);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesAfterTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() > time);
    }
    
	@Override
    public void removeTimeValuesAfterTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesAfterTime(s, time);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesBelowValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getValue() < value);
    }
    
	@Override
    public void removeTimeValuesBelowValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesBelowValue(s, value);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesAboveValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getValue() > value);
    }
    
	@Override
    public void removeTimeValuesAboveValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
        TimeSeries s = readInTSFromFile(tsInputPath);
        removeTimeValuesAboveValue(s, value);
		writeInTSToFile(((TimeSeries) s), tsOutputPath);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO: UTILS OPERATORS
    

	@Override
	public void printTimeSeries(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		System.out.print("[");
		var first = true;
		for (TimeValue tv : ((TimeSeries) s).getTimeValues()) {
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		List<TimeValue> timeValues = new ArrayList<>(((TimeSeries) s).getTimeValues());
	    timeValues.sort(Comparator.comparingDouble(TimeValue::getTime));
	    ((TimeSeries) s).getTimeValues().clear();
	    for (TimeValue tv: timeValues)
	    	((TimeSeries) s).getTimeValues().add(tv);
	}

	@Override
	public void reorderTimeSeries_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    reorderTimeSeries(s);
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}
	
	@Override
	public TimeValue selectRandomTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    Random r = new Random();
	    List<TimeValue> timeValues = ((TimeSeries) s).getTimeValues();
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    List<Double> times = new ArrayList<>();
	    for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    List<Double> values = new ArrayList<Double>();
	    for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    List<Double> times = new ArrayList<>();
    	for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    List<Double> values = new ArrayList<Double>();
    	for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    List<TimeValue> timeValues = new ArrayList<>();
	    for (TimeValue tv: ((TimeSeries) s).getTimeValues())
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (index >= 0 && index < ((TimeSeries) s).getTimeValues().size())
			return ((TimeSeries) s).getTimeValues().get(index).getValue();
		else throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public double getValueAt_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getValueAt(s, index);
	}

	@Override
	public double getTimeAt(TimeSeries s, int index) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (index >= 0 && index < ((TimeSeries) s).getTimeValues().size())
			return ((TimeSeries) s).getTimeValues().get(index).getTime();
		else throw new IllegalArgumentException("Index is out of range");
	}
	
	@Override
	public double getTimeAt_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getTimeAt(s, index);
	}

	@Override
	public TimeValue getTimeValueAt(TimeSeries s, int index) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (index >= 0 && index < ((TimeSeries) s).getTimeValues().size())
			return ((TimeSeries) s).getTimeValues().get(index);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		return ((TimeSeries) s).getTimeValues().stream()
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		return ((TimeSeries) s).getTimeValues().stream()
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		return ((TimeSeries) s).getTimeValues().stream()
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		return ((TimeSeries) s).getTimeValues().stream()
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		int index = ((TimeSeries) s).getTimeValues().indexOf(tv);
	    if (index >= 0 && index < ((TimeSeries) s).getTimeValues().size() - 1)
	        return ((TimeSeries) s).getTimeValues().get(index + 1);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    if (index >= 0 && index < ((TimeSeries) s).getTimeValues().size() - 1)
	        return ((TimeSeries) s).getTimeValues().get(index + 1);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		int index = ((TimeSeries) s).getTimeValues().indexOf(tv);
	    if (index > 0)
	        return ((TimeSeries) s).getTimeValues().get(index - 1);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    if (index > 0 && index < ((TimeSeries) s).getTimeValues().size())
	        return ((TimeSeries) s).getTimeValues().get(index - 1);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (!((TimeSeries) s).getTimeValues().isEmpty())
			return ((TimeSeries) s).getTimeValues().get(0).getTime();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getFirstTime_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getFirstTime(s);
	}

	@Override
	public double getFirstValue(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (!((TimeSeries) s).getTimeValues().isEmpty())
			return ((TimeSeries) s).getTimeValues().get(0).getValue();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getFirstValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getFirstValue(s);
	}
	
	@Override
	public TimeValue getFirstTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (!((TimeSeries) s).getTimeValues().isEmpty())
			return ((TimeSeries) s).getTimeValues().get(0);
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public TimeValue getFirstTimeValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getFirstTimeValue(s);
	}

	@Override
	public double getLastTime(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (!((TimeSeries) s).getTimeValues().isEmpty())
			return ((TimeSeries) s).getTimeValues().get(((TimeSeries) s).getTimeValues().size() - 1).getTime();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getLastTime_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getLastTime(s);
	}

	@Override
	public double getLastValue(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (!((TimeSeries) s).getTimeValues().isEmpty())
			return ((TimeSeries) s).getTimeValues().get(((TimeSeries) s).getTimeValues().size() - 1).getValue();
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public double getLastValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getLastValue(s);
	}

	@Override
	public TimeValue getLastTimeValue(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		if (!((TimeSeries) s).getTimeValues().isEmpty())
			return ((TimeSeries) s).getTimeValues().get(((TimeSeries) s).getTimeValues().size() - 1);
		throw new NoSuchElementException("TimeSeries is empty");
	}
	
	@Override
	public TimeValue getLastTimeValue_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return getLastTimeValue(s);
	}

	@Override
	public TimeSeries copyTimeSeries(TimeSeries s) {
	    TimeSeries copy = ReadFactory.eINSTANCE.createTimeSeries();
		if (!(s instanceof TimeSeries) || !(copy instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues()) {
	        TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	        tvCopy.setTime(tv.getTime());
	        tvCopy.setValue(tv.getValue());
	        ((TimeSeries) copy).getTimeValues().add(tvCopy);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        tv.setTime(0);
	}
	
	@Override
	public void setAllTimesToZero_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllTimesToZero(s);
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}	

	@Override
	public void setAllValuesToZero(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        tv.setValue(0);
	}
	
	@Override
	public void setAllValuesToZero_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllValuesToZero(s);
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public void setAllTimesToTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        tv.setTime(time);
	}
	
	@Override
	public void setAllTimesToTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllTimesToTime(s, time);
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public void setAllValuesToValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        tv.setValue(value);
	}
	
	@Override
	public void setAllValuesToValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    setAllValuesToValue(s, value);
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
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
	    writeInTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public TimeSeries getSubTimeSeriesInTimeRange(TimeSeries s, double timeMin, double timeMax) {
	    TimeSeries subSeries = ReadFactory.eINSTANCE.createTimeSeries();
		if (!(s instanceof TimeSeries) || !(subSeries instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
	    for (TimeValue tv : ((TimeSeries) s).getTimeValues())
	        if (tv.getTime() >= timeMin && tv.getTime() <= timeMax) {
	            TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	            tvCopy.setTime(tv.getTime());
	            tvCopy.setValue(tv.getValue());
	            ((TimeSeries) subSeries).getTimeValues().add(tvCopy);
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
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		return ((TimeSeries) s).getTimeValues().size();
	}
	
	@Override
	public int countTimeValues_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return countTimeValues(s);
	}

	@Override
	public boolean isEmpty(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		return ((TimeSeries) s).getTimeValues().size() == 0;
	}
	
	@Override
	public boolean isEmpty_File(String tsInputPath) throws IOException {
	    TimeSeries s = readInTSFromFile(tsInputPath);
	    return isEmpty(s);
	}
	
	@Override
	public void writeInTSToFile(TimeSeries TimeSeries, String filePath) throws IOException {
		reorderTimeSeries(TimeSeries);
		List<String> lines = TimeSeries.getTimeValues().stream().map(tv -> tv.getTime() + " " + tv.getValue()).collect(Collectors.toList());
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}


	
	@Override
	public TimeSeries readOutTSFromFile(String filePath) throws IOException {
	    TimeSeries TimeSeries = ReadFactory.eINSTANCE.createTimeSeries();
	    List<String> lines = Files.readAllLines(Paths.get(filePath));
	    if (lines.isEmpty())
	        throw new IOException("Invalid file format: file is empty");
	    int startIndex = 0;
	    if (lines.get(0).trim().toUpperCase().startsWith("\"TIME\""))
	        startIndex = 1; // Skip header if present
	    for (int i = startIndex; i < lines.size(); i++) {
	        String line = lines.get(i).trim();
	        if (line.isEmpty()) 
	        	continue; // Skip empty lines
	        String[] parts = line.split(",");
	        if (parts.length != 2)
	            throw new IOException("Invalid line format: " + line);
	        try {
	            double time = Double.parseDouble(parts[0].trim());
	            double value = Double.parseDouble(parts[1].trim());
	            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
	            tv.setTime(time);
	            tv.setValue(value);
	            TimeSeries.getTimeValues().add(tv);
	        } catch (NumberFormatException e) {
	            throw new IOException("Invalid numeric format in line: " + line, e);
	        }
	    }
	    reorderTimeSeries(TimeSeries);
	    return TimeSeries;
	}

	
	
	
	
	
	
	
	

	//TODO: DISCOVERY OPERATORS	
		@Override
		public DiscoveryOutcome isCommission(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeries) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeries) tsMutated).getTimeValues().size();
		    if (originalSize < mutatedSize) {
		    	outcome.setHasError(true);
		    	outcome.setOutcomeMsg("Commission error found. Expected Size: " + originalSize + ", Actual Size: " + mutatedSize);
		    }
		    return outcome;
		}

		
		@Override
		public DiscoveryOutcome isCommission_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return isCommission(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isOmission(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeries) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeries) tsMutated).getTimeValues().size();
		    if (originalSize > mutatedSize) {
		    	outcome.setHasError(true);
		    	outcome.setOutcomeMsg("Omission error found. Expected Size: " + originalSize + ", Actual Size: " + mutatedSize);
		    }
		    return outcome;
		}
		
		@Override
		public DiscoveryOutcome isOmission_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return isOmission(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeries) tsOriginal).getTimeValues().size() != ((TimeSeries) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedTime = 0;
		    double actualTime = 0;
			//for each original time, check the closest time among mutated timeseries and evaluate whether it is late according to an eps threshold
			for(int i=0; i<((TimeSeries) tsOriginal).getTimeValues().size(); i++) {
				TimeValue tv1 = ((TimeSeries) tsOriginal).getTimeValues().get(i);
				double time1 = tv1.getTime();			
				double value1 = tv1.getValue();
				double closestDiffTime = Double.MAX_VALUE;
				for(int j=0; j<((TimeSeries) tsMutated).getTimeValues().size(); j++) {
					TimeValue tv2 = ((TimeSeries) tsMutated).getTimeValues().get(i);
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
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return isLate(s1, s2, eps);
		}
		
		@Override
		public DiscoveryOutcome isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeries) tsOriginal).getTimeValues().size() != ((TimeSeries) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedTime = 0;
		    double actualTime = 0;
			//for each original time, check the closest time among mutated timeserie and evaluate whether it is early according to an eps threshold
			for(int i=0; i<((TimeSeries) tsOriginal).getTimeValues().size(); i++) {
				TimeValue tv1 = ((TimeSeries) tsOriginal).getTimeValues().get(i);
				double time1 = tv1.getTime();			
				double value1 = tv1.getValue();
				double closestDiffTime = Double.MAX_VALUE;
				for(int j=0; j<((TimeSeries) tsMutated).getTimeValues().size(); j++) {
					TimeValue tv2 = ((TimeSeries) tsMutated).getTimeValues().get(i);
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
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return isEarly(s1, s2, eps);
		}
		
		@Override
		public DiscoveryOutcome isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeries) tsOriginal).getTimeValues().size() != ((TimeSeries) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
			DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedValue = 0;
		    double actualValue = 0;
			for(int i=0; i<((TimeSeries) tsOriginal).getTimeValues().size(); i++) {
				TimeValue tv1 = ((TimeSeries) tsOriginal).getTimeValues().get(i);
				TimeValue tv2 = ((TimeSeries) tsMutated).getTimeValues().get(i);
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
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
			return isValueCoarse(s1, s2, eps, valueMin, valueMax);
		}
		
		@Override
		public DiscoveryOutcome isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
			//as we compare time values against time values, the time series must be of the same size
			if(((TimeSeries) tsOriginal).getTimeValues().size() != ((TimeSeries) tsMutated).getTimeValues().size())
		        throw new IllegalArgumentException("Time series sizes do not match");
			DiscoveryOutcome outcome = new DiscoveryOutcome();
		    double expectedValue = 0;
		    double actualValue = 0;		
			for(int i=0; i<((TimeSeries) tsOriginal).getTimeValues().size(); i++) {
				TimeValue tv1 = ((TimeSeries) tsOriginal).getTimeValues().get(i);
				TimeValue tv2 = ((TimeSeries) tsMutated).getTimeValues().get(i);
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
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
			return isValueSubtle(s1, s2, eps, valueMin, valueMax);
		}
		
		@Override
		public DiscoveryOutcome areSameSize(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeries) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeries) tsMutated).getTimeValues().size();
		    if (originalSize == mutatedSize)
		    	outcome.setOutcomeMsg("The time series have the same size (" + originalSize + ")");
		    else 
		    	outcome.setOutcomeMsg("The time series have different size. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    return outcome;
		}
		
		@Override
		public DiscoveryOutcome areSameSize_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return areSameSize(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isSmaller(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeries) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeries) tsMutated).getTimeValues().size();
		    if (originalSize < mutatedSize)
		    	outcome.setOutcomeMsg("The first time series has smaller size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    else 
		    	outcome.setOutcomeMsg("The first time series has same or bigger size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    return outcome;
		}
		
		@Override
		public DiscoveryOutcome isSmaller_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return isSmaller(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome isBigger(TimeSeries tsOriginal, TimeSeries tsMutated) {
			if (!(tsOriginal instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    int originalSize = ((TimeSeries) tsOriginal).getTimeValues().size();
		    int mutatedSize = ((TimeSeries) tsMutated).getTimeValues().size();
		    if (originalSize > mutatedSize)
		    	outcome.setOutcomeMsg("The first time series has bigger size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		    else 
		    	outcome.setOutcomeMsg("The first time series has same or smaller size than the second time series. First time series size: " + originalSize + ", Second time series size: " + mutatedSize);
		   return outcome;
		    
		}
		
		@Override
		public DiscoveryOutcome isBigger_File(String tsOriginalPath, String tsMutatedPath) throws IOException {
			TimeSeries s1 = readOutTSFromFile(tsOriginalPath);
			TimeSeries s2 = readOutTSFromFile(tsMutatedPath);
		    return isBigger(s1, s2);
		}
		
		@Override
		public DiscoveryOutcome hasTimeOutRange(TimeSeries tsMutated, double timeMin, double timeMax) {
			if (!(tsMutated instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    for (TimeValue tv : ((TimeSeries) tsMutated).getTimeValues()) {
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
			TimeSeries s = readOutTSFromFile(tsMutatedPath);
		    return hasTimeOutRange(s, timeMin, timeMax);
		}
		
		@Override
		public DiscoveryOutcome hasValueOutRange(TimeSeries tsMutated, double valueMin, double valueMax) {
			if (!(tsMutated instanceof TimeSeries) || !(tsMutated instanceof TimeSeries))
				throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		    DiscoveryOutcome outcome = new DiscoveryOutcome();
		    for (TimeValue tv : ((TimeSeries) tsMutated).getTimeValues()) {
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
			TimeSeries s = readOutTSFromFile(tsMutatedPath);
		    return hasValueOutRange(s, valueMin, valueMax);
		}

		@Override
		public void serializeDiscovery(DiscoveryOutcome outcome, String filename) {
	        Gson gson = new Gson();
	        String json = gson.toJson(outcome);
	        System.out.println(json);
		}

		@Override
		public void serializeDiscovery(List<DiscoveryOutcome> outcomes, String filename) {
	        Gson gson = new Gson();
	        String json = gson.toJson(outcomes);
	        System.out.println(json);
			
		}
		
		
		
		
		
}
