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

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;




public abstract class EmeliotLib implements EmeliotService, EmeliotMutationService {

	
	
	
	
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
		TimeSeries s = readTSFromFile(tsInputPath);
		addTimeAndValue(s, time, value);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addRandomTimeAndValue(s, timeMin, timeMax, value);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addTimeAndRandomValue(s, time, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addRandomTimeAndRandomValue(s, timeMin, timeMax, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    addMultipleTimeValues(s, times, values);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        addMultipleRandomTimeValues(s, timeMin, timeMax, valueMin, valueMax, count);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        TimeSeries s1 = readTSFromFile(tsAppendPath);
        appendTimeSeries(s, s1);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        TimeSeries s1 = readTSFromFile(tsMergePath);
        mergeTimeSeries(s, s1);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        TimeSeries s1 = readTSFromFile(tsReplacePath);
        replaceTimeSeries(s, s1);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeValue(s, time, value);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTime(s, timeOld, timeNew);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeValueWithRandom(s, time, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeWithRandom(s, timeMin, timeMax, time);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeAndValue(s, timeOld, timeNew, valueNew);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeWithRandomAndValue(s, timeOld, timeMin, timeMax, valueNew);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeAndValueWithRandom(s, timeOld, timeNew, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeTimeWithRandomAndValueWithRandom(s, timeOld, timeMin, timeMax, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeARandomTimeValue(s, timeNew, valueNew);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeARandomTimeValueWithRandomTimeValue(s, timeMin, timeMax, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleTimeValues(s, timesOld, timesNew, valuesNew);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleTimeValuesWithRandomTimeValues(s, timesOld, timeMin, timeMax, valueMin, valueMax);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeTimeLate(s, eps, time, timeMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeRandomTimeLate(s, eps, timeMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleTimeLate(s, eps, times, timeMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeTimeEarly(s, eps, time, timeMinDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeRandomTimeEarly(s, eps, timeMinDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeMultipleTimeEarly(s, eps, times, timeMinDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeValueCoarse(s, eps, time, valueMinDomain, valueMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        changeRandomValueCoarse(s, eps, valueMinDomain, valueMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleValueCoarse(s, eps, times, valueMinDomain, valueMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeValueSubtle(s, eps, time, valueMinDomain, valueMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeRandomValueSubtle(s, eps, valueMinDomain, valueMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    changeMultipleValueSubtle(s, eps, times, valueMinDomain, valueMaxDomain);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    removeTimeValue(s, time, value);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
    public void removeTimeValue(TimeSeries s, TimeValue tvToRemove) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");			
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && (tv.getValue() == tvToRemove.getValue()));
    }
    
	@Override
    public void removeTimeValue_File(String tsInputPath, String tsOutputPath, TimeValue tvToRemove) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValue(s, tvToRemove);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        removeRandomTimeValue(s);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, times, values);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
        TimeSeries s = readTSFromFile(tsInputPath);
        removeMultipleTimeValues(s, timeValues);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    removeMultipleTimeValues(s, timeValues);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeAllTimeValues(TimeSeries s) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().clear();
    }
    
	@Override
    public void removeAllTimeValues_File(String tsInputPath, String tsOutputPath) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeAllTimeValues(s);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesBeforeTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() < time);
    }
    
	@Override
    public void removeTimeValuesBeforeTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesBeforeTime(s, time);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesAfterTime(TimeSeries s, double time) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getTime() > time);
    }
    
	@Override
    public void removeTimeValuesAfterTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesAfterTime(s, time);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesBelowValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getValue() < value);
    }
    
	@Override
    public void removeTimeValuesBelowValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesBelowValue(s, value);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
    }

	@Override
    public void removeTimeValuesAboveValue(TimeSeries s, double value) {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeries");
		((TimeSeries) s).getTimeValues().removeIf(tv -> tv.getValue() > value);
    }
    
	@Override
    public void removeTimeValuesAboveValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException {
        TimeSeries s = readTSFromFile(tsInputPath);
        removeTimeValuesAboveValue(s, value);
		writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    reorderTimeSeries(s);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getNextTimeValue(s, tv);
	}
	
	@Override
	public double getNextTime(TimeSeries s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}
	
	@Override
	public double getNextTime_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getNextTime(s, tv);
	}

	@Override
	public double getNextValue(TimeSeries s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}
	
	@Override
	public double getNextValue_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getNextTimeValue(s, index);
	}

	@Override
	public double getNextTime(TimeSeries s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}
	
	@Override
	public double getNextTime_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getNextTime(s, index);
	}

	@Override
	public double getNextValue(TimeSeries s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}
	
	@Override
	public double getNextValue_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getPreviousTimeValue(s, tv);
	}
	
	@Override
	public double getPreviousTime(TimeSeries s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}
	
	@Override
	public double getPreviousTime_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getPreviousTime(s, tv);
	}
	
	@Override
	public double getPreviousValue(TimeSeries s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}
	
	@Override
	public double getPreviousValue_File(String tsInputPath, TimeValue tv) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getPreviousTimeValue(s, index);
	}

	@Override
	public double getPreviousTime(TimeSeries s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}
	
	@Override
	public double getPreviousTime_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return getPreviousTime(s, index);
	}

	@Override
	public double getPreviousValue(TimeSeries s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}
	
	@Override
	public double getPreviousValue_File(String tsInputPath, int index) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    setAllTimesToZero(s);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    setAllValuesToZero(s);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
	}	

	@Override
	public void setAllToZero(TimeSeries s) {
	    setAllTimesToZero(s);
	    setAllValuesToZero(s);
	}
	
	@Override
	public void setAllToZero_File(String tsInputPath, String tsOutputPath) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    setAllToZero(s);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    setAllTimesToTime(s, time);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    setAllValuesToValue(s, value);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
	}

	@Override
	public void setAllToTimeValue(TimeSeries s, double time, double value) {
	    setAllTimesToTime(s, time);
	    setAllValuesToValue(s, value);
	}
	
	@Override
	public void setAllToTimeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException {
	    TimeSeries s = readTSFromFile(tsInputPath);
	    setAllToTimeValue(s, time, value);
	    writeTSToFile(((TimeSeries) s), tsOutputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
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
	    TimeSeries s = readTSFromFile(tsInputPath);
	    return isEmpty(s);
	}
	
	@Override
	public void writeTSToFile(TimeSeries TimeSeries, String filePath) throws IOException {
		reorderTimeSeries(TimeSeries);
		List<String> lines = TimeSeries.getTimeValues().stream().map(tv -> tv.getTime() + " " + tv.getValue()).collect(Collectors.toList());
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	@Override
	public TimeSeries readTSFromFile(String filePath) throws IOException {
		TimeSeries TimeSeries = ReadFactory.eINSTANCE.createTimeSeries();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		for (String line : lines) {
			String[] parts = line.trim().split("\\s+");
			if (parts.length != 2)
				throw new IOException("Invalid line format: " + line);
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(Double.parseDouble(parts[0].trim()));
			double value = (Double.parseDouble(parts[1].trim()));
			tv.setValue(value);
			TimeSeries.getTimeValues().add(tv);
		}
		reorderTimeSeries(TimeSeries);
		return TimeSeries;
	}
	
	
	
	
	
	
	
	
	

	

	//TODO: DISCOVERY OPERATORS
	
	
	public boolean isCommission(TimeSeries tsOriginal, TimeSeries tsMutated) {
		return tsOriginal.getTimeValues().size() < tsMutated.getTimeValues().size(); 
	}
	
	public boolean isOmission(TimeSeries tsOriginal, TimeSeries tsMutated) {
		return tsOriginal.getTimeValues().size() > tsMutated.getTimeValues().size();
	}
	
	public boolean isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
		//for each original time, check the closest time among mutated timeserie and evaluate whether it is late 
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
			if(Math.abs(closestDiffTime)>eps && closestDiffTime<0)//if diff time bigger than epsilon and negative (mutated time is later), late detected
				return true;
		}	
		return false;
	}
	
	public boolean isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
		//for each original time, check the closest time among mutated timeserie and evaluate whether it is early 
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
			if(Math.abs(closestDiffTime)> eps && closestDiffTime>0) //if diff time bigger than epsilon and positive (mutated time is earlier), early detected
				return true;
		}	
		return false;
	}
	
	public boolean isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			TimeValue tv2 = tsMutated.getTimeValues().get(i);
			double valueOriginal = tv1.getValue();
			double valueMutated = tv2.getValue();
			double valueDiff = valueOriginal - valueMutated;
			//value subtle failure found as mutated value is bigger than eps and out of boundary
			if(Math.abs(valueDiff)> eps && (valueMutated < valueMin || valueMutated > valueMax))
				return true;
		}	
		return false;
	}
	
	public boolean isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			TimeValue tv2 = tsMutated.getTimeValues().get(i);
			double valueOriginal = tv1.getValue();
			double valueMutated = tv2.getValue();
			double valueDiff = valueOriginal - valueMutated;
			//value subtle failure found as mutated value is bigger than eps and within boundary
			if(Math.abs(valueDiff)> eps && (valueMutated >= valueMin && valueMutated <= valueMax))
				return true;
		}
		return false;
	}
	
	public boolean areSameSize(TimeSeries tsOriginal, TimeSeries tsMutated) {
	    return tsOriginal.getTimeValues().size() == tsMutated.getTimeValues().size();
	}
	
	public boolean isSmaller(TimeSeries tsOriginal, TimeSeries tsMutated) {
	    return tsOriginal.getTimeValues().size() < tsMutated.getTimeValues().size();
	}
	
	public boolean isBigger(TimeSeries tsOriginal, TimeSeries tsMutated) {
	    return tsOriginal.getTimeValues().size() > tsMutated.getTimeValues().size();
	}
	
	public boolean hasTimeOutRange(TimeSeries tsMutated, double timeMin, double timeMax) {
	    for (TimeValue tv : tsMutated.getTimeValues()) {
	        double time = tv.getTime();
	        if (time < timeMin || time > timeMax) {
	            return true;
	        }
	    }
	    return false;
	}

}
