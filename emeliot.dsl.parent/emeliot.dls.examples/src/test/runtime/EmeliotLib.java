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

import emeliot.dsl.read.ReadFactory;


import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;




public class EmeliotLib {
	
	
	
	/********** TODO: ADD OPERATORS (ALL ADDRESS COMMISSION MUTATION) **********/
	
	public void addTimeAndValue(TimeSeriesValue s, double value, int time) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(time);
		tv.setValue(value);
		s.getTimeValues().add(tv);
		reorderTimeSeriesValue(s);
	}
	
	public void addRandomTimeAndValue(TimeSeriesValue s, double value, int minTime, int maxTime) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		tv.setTime(timeRandom);
		tv.setValue(value);
		s.getTimeValues().add(tv);
		reorderTimeSeriesValue(s);
	}
	
	public void addTimeAndRandomValue(TimeSeriesValue s, int time, double minValue, double maxValue) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
		tv.setTime(time);
		tv.setValue(valueRandom);
		s.getTimeValues().add(tv);
		reorderTimeSeriesValue(s);
	}
	
	public void addRandomTimeAndRandomValue(TimeSeriesValue s, int minTime, int maxTime, 
			double minValue, double maxValue) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		double valueRandom = r.nextDouble(minValue, maxValue);
		tv.setTime(timeRandom);
		tv.setValue(valueRandom);
		s.getTimeValues().add(tv);
		reorderTimeSeriesValue(s);
	}
	
	public void addMultipleTimeValues(TimeSeriesValue s, List<Integer> times, List<Double> values) {
		if(times.size() != values.size())
	        throw new IllegalArgumentException("Times and values sizes do not match");
        for (int i = 0; i < times.size(); i++) {
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(times.get(i));
            tv.setValue(values.get(i));
            s.getTimeValues().add(tv);
        }
        reorderTimeSeriesValue(s);
    }
	
    public void addMultipleRandomTimeValues(TimeSeriesValue s, int minTime, int maxTime, double minValue, 
    		double maxValue, int count) {
        Random r = new Random();
        for (int i=0; i<count; i++) {
            int timeRandom = r.nextInt(minTime, maxTime);
            double valueRandom = r.nextDouble(minValue, maxValue);
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
            tv.setTime(timeRandom);
            tv.setValue(valueRandom);
            s.getTimeValues().add(tv);
        }
        reorderTimeSeriesValue(s);
    }
	
    //duplicates allowed
    public void appendTimeSeriesValue(TimeSeriesValue s, TimeSeriesValue s1) {
    	s.getTimeValues().addAll(s1.getTimeValues());
        reorderTimeSeriesValue(s);
    }
    
    //no time duplicates allowed
    public void mergeTimeSeriesValue(TimeSeriesValue s, TimeSeriesValue s1) {
        for (TimeValue tv1 : s1.getTimeValues())
        	if(!existTimeValue(s, tv1.getTime(), tv1.getValue())) {
        		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
                tv.setTime(tv1.getTime());
                tv.setValue(tv1.getValue());
        		s.getTimeValues().add(tv);
        	}
        reorderTimeSeriesValue(s);
    }
    
    public void replaceTimeSeriesValue(TimeSeriesValue s, TimeSeriesValue s1) {
    	s.getTimeValues().clear();
        appendTimeSeriesValue(s, s1);
        reorderTimeSeriesValue(s);
    }




    
    
	
    
    
    
    
    
    
	
	
	/********** TODO: EDIT OPERATORS (SOME ADDRESS LATE/EARLY/VALUECOARSE/VALUESUBTLE MUTATIONS)**********/
	
    public void changeValue(TimeSeriesValue s, double value, int time) {
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            tv.setValue(value);
	            break;
	        }
	    }
	}
	
	public void changeTime(TimeSeriesValue s, int timeOld, int timeNew) {
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            break;
	        }
	    }
	    reorderTimeSeriesValue(s);
	}
	
	public void changeValueWithRandom(TimeSeriesValue s, double minValue, double maxValue, int time) {
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            tv.setValue(valueRandom);
	            break;
	        }
	    }
	}
	
	public void changeTimeWithRandom(TimeSeriesValue s, int minTime, int maxTime, int time) {
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            tv.setTime(timeRandom);
	            break;
	        }
	    }
	    reorderTimeSeriesValue(s);
	}
	
	public void changeTimeAndValue(TimeSeriesValue s, int timeOld, int timeNew, double newValue) {
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeNew);
	            tv.setValue(newValue);
	            break;
	        }
	    }
	    reorderTimeSeriesValue(s);
	}

	public void changeTimeWithRandomAndValue(TimeSeriesValue s, int timeOld, int minTime, int maxTime, 
			double valueNew) {
	    Random r = new Random();
	    int timeRandom = r.nextInt(minTime, maxTime);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeRandom);
	            tv.setValue(valueNew);
	            break;
	        }
	    }
	    reorderTimeSeriesValue(s);
	}

	public void changeTimeAndValueWithRandom(TimeSeriesValue s, int timeOld, int timeNew, double minValue, 
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
	    reorderTimeSeriesValue(s);
	}
	
	public void changeTimeWithRandomAndValueWithRandom(TimeSeriesValue s, int timeOld, int minTime, int maxTime, 
			double minValue, double maxValue) {
	    Random r = new Random();
	    int timeRandom = r.nextInt(minTime, maxTime);
	    double valueRandom = r.nextDouble(minValue, maxValue);
	    for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == timeOld) {
	            tv.setTime(timeRandom);
	            tv.setValue(valueRandom);
	            break;
	        }
	    }
	    reorderTimeSeriesValue(s);
	}
	
	public void changeARandomTimeValue(TimeSeriesValue s, int newTime, double newValue) {
	    TimeValue tvRandom = selectRandomTimeValue(s);
	    if (tvRandom != null) {
	        tvRandom.setTime(newTime);
	        tvRandom.setValue(newValue);
	    }
	    reorderTimeSeriesValue(s);
	}
	
	public void changeARandomTimeValueWithRandomTimeValue(TimeSeriesValue s, int minTime, int maxTime,
					double minValue, double maxValue) {
	    TimeValue randomTV = selectRandomTimeValue(s);
	    Random r = new Random();
	    int timeRandom = r.nextInt(minTime, maxTime);
	    double valueRandom = r.nextDouble(minValue, maxValue);
	    if (randomTV != null) {
	        randomTV.setTime(timeRandom);
	        randomTV.setValue(valueRandom);
	    }
	    reorderTimeSeriesValue(s);
	}
	
	public void changeMultipleTimeValues(TimeSeriesValue s, List<Integer> timesOld, List<Integer> timesNew,
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
        reorderTimeSeriesValue(s);
    }
	
	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeriesValue s, List<Integer> timesOld, int minTime, 
			int maxTime, double minValue, double maxValue) {
        Random r = new Random();
        for (int i = 0; i < timesOld.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == timesOld.get(i)) {
			        int timeRandom = r.nextInt(minTime, maxTime);
				    double valueRandom = r.nextDouble(minValue, maxValue);
		            tv.setTime(timeRandom);
		            tv.setValue(valueRandom);
		            continue;
		        }
    	    }
        }
        reorderTimeSeriesValue(s);
    }
	
    public void changeTimeLate(TimeSeriesValue s, double eps, int time, int maxDomainTime) {
        Random r = new Random();
        for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	        	int timeNew = time + (int) (r.nextDouble() * (maxDomainTime - time - eps) + eps);
	        	tv.setTime(timeNew);	            
	            break;
	        } 
        }
        reorderTimeSeriesValue(s);
    }
	
	public void changeRandomTimeLate(TimeSeriesValue s, double eps, int maxDomainTime) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            int timeOld = randomTV.getTime();
            int timeNew = timeOld + (int) (r.nextDouble() * (maxDomainTime - timeOld - eps) + eps);
            randomTV.setTime(timeNew);
        }
        reorderTimeSeriesValue(s);
    }
	
	public void changeMultipleTimeLate(TimeSeriesValue s, double eps, List<Integer> times, int maxDomainTime) {
        Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == times.get(i)) {
		        	int timeOld = times.get(i);
		        	int timeNew = timeOld + (int) (r.nextDouble() * (maxDomainTime - timeOld - eps) + eps);
		        	tv.setTime(timeNew);	  
		        	continue;
		        }
    	    }
        }
        reorderTimeSeriesValue(s);
	}
	
    public void changeTimeEarly(TimeSeriesValue s, double eps, int time, int minDomainTime) {
        Random r = new Random();
        for (TimeValue tv: s.getTimeValues()) {
	        if (tv.getTime() == time) {
	            int timeNew = time - (int) (r.nextDouble() * (time - minDomainTime - eps) + eps);
	            tv.setTime(timeNew);
	            break;
	        }
        }
        reorderTimeSeriesValue(s);
    }
    
    public void changeRandomTimeEarly(TimeSeriesValue s, double eps, int minDomainTime) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            int timeOld = randomTV.getTime();
            int timeNew = timeOld - (int) (r.nextDouble() * (timeOld - minDomainTime - eps) + eps);
            randomTV.setTime(timeNew);
        }
        reorderTimeSeriesValue(s);
    }

	public void changeMultipleTimeEarly(TimeSeriesValue s, double eps, List<Integer> times, int minDomainTime) {
        Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
    	    for (TimeValue tv: s.getTimeValues()) {
		        if (tv.getTime() == times.get(i)) {
		        	int timeOld = times.get(i);
		            int timeNew = timeOld - (int) (r.nextDouble() * (timeOld - minDomainTime - eps) + eps);
		        	tv.setTime(timeNew);	  
		        	continue;
		        }
    	    }
        }
        reorderTimeSeriesValue(s);
	}
	
    public void changeValueCoarse(TimeSeriesValue s, double eps, double minDomainValue, double maxDomainValue, int time) {
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
	
    public void changeRandomValueCoarse(TimeSeriesValue s, double eps, double minDomainValue, double maxDomainValue) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            double valueNew = (r.nextBoolean()) ? 
            		(r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
                    : (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
            randomTV.setValue(valueNew);
        }
    }

	public void changeMultipleValueCoarse(TimeSeriesValue s, double eps, List<Integer> times, 
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
        reorderTimeSeriesValue(s);
	}

	public void changeValueSubtle(TimeSeriesValue s, double eps, int time, double minDomainValue, double maxDomainValue) {
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
	
	public void changeRandomValueSubtle(TimeSeriesValue s, double eps, double minDomainValue, double maxDomainValue) {
        Random r = new Random();
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
                    : (r.nextDouble() * (eps - minDomainValue) - eps);
            randomTV.setValue(valueNew);
        }
    }
	
	public void changeMultipleValueSubtle(TimeSeriesValue s, double eps, List<Integer> times, double minDomainValue, 
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
        reorderTimeSeriesValue(s);
	}

	
	
	
	
	
	





    
	
	
	
	
	
	
	
	
	/********** TODO: REMOVE OPERATORS (ALL ADDRESS OMISSION MUTATION)**********/
    
	public void removeTimeValue(TimeSeriesValue s, int time, double value) {
        s.getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
    }

    public void removeTimeValue(TimeSeriesValue s, TimeValue tvToRemove) {
        s.getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && (tv.getValue() == tvToRemove.getValue()));
    }

    public void removeRandomTimeValue(TimeSeriesValue s) {
        TimeValue randomTV = selectRandomTimeValue(s);
        if (randomTV != null) {
            s.getTimeValues().remove(randomTV);
        }
    }

    public void removeMultipleTimeValues(TimeSeriesValue s, List<Integer> times, List<Double> values) {
        for (int i=0; i<times.size(); i++) {
            int time = times.get(i);
            double value = values.get(i);
            s.getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }

    public void removeMultipleTimeValues(TimeSeriesValue s, List<TimeValue> timeValues) {
    	for (int i=0; i<timeValues.size(); i++) {
    		int time = timeValues.get(i).getTime();
            double value = timeValues.get(i).getValue();
            s.getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
        }
    }

    public void removeMultipleTimeValues(TimeSeriesValue s, TimeValue... timeValues) {
        for (TimeValue tvToRemove: timeValues) {
            s.getTimeValues().removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == tvToRemove.getValue());
        }
    }

    public void removeAllTimeValues(TimeSeriesValue s) {
        s.getTimeValues().clear();
    }

    public void removeTimeValuesBeforeTime(TimeSeriesValue s, int time) {
        s.getTimeValues().removeIf(tv -> tv.getTime() < time);
    }

    public void removeTimeValuesAfterTime(TimeSeriesValue s, int time) {
        s.getTimeValues().removeIf(tv -> tv.getTime() > time);
    }

    public void removeTimeValuesBelowValue(TimeSeriesValue s, double value) {
        s.getTimeValues().removeIf(tv -> tv.getValue() < value);
    }

    public void removeTimeValuesAboveValue(TimeSeriesValue s, double value) {
        s.getTimeValues().removeIf(tv -> tv.getValue() > value);
    }

    
    
    
    
    
    
    
    
	
	

	
	
	
	/**********TODO: UTILS OPERATORS **********/
	
    public void reorderTimeSeriesValue(TimeSeriesValue s) {
		List<TimeValue> timeValues = new ArrayList<>(s.getTimeValues());
	    timeValues.sort(Comparator.comparingInt(TimeValue::getTime));
	    s.getTimeValues().clear();
	    for (TimeValue tv: timeValues)
	        s.getTimeValues().add(tv);
	}

	public TimeValue selectRandomTimeValue(TimeSeriesValue s) {
	    Random r = new Random();
	    List<TimeValue> timeValues = s.getTimeValues();
	    if (!timeValues.isEmpty()) {
	        int randomIndex = r.nextInt(timeValues.size());
	        return timeValues.get(randomIndex);
	    }
	    return null;
	}

	public boolean existTime(TimeSeriesValue s, int time) {
	    for (TimeValue tv: s.getTimeValues())
	    	if (tv.getTime() == time)
	            return true;
	    return false;
	}
	
	public boolean existValue(TimeSeriesValue s, double value) {
	    for (TimeValue tv: s.getTimeValues()) {
	    	double v1 = tv.getValue();
	        if (value == v1)
	            return true;
	    }
	    return false;
	}
	
	public boolean existTimeValue(TimeSeriesValue s, int time, double value) {
	    return (existTime(s, time) && existValue(s, value));
	}

	public List<Integer> getAllTimes(TimeSeriesValue s) {
	    List<Integer> times = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues())
	        times.add(tv.getTime());
	    return times;
	}

	public List<Double> getAllValues(TimeSeriesValue s) {
	    List<Double> values = new ArrayList<Double>();
	    for (TimeValue tv: s.getTimeValues())
	        values.add(tv.getValue());
	    return values;
	}

	public List<Integer> getTimesInRange(TimeSeriesValue s, int minTime, int maxTime) {
	    List<Integer> times = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues())
	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime)
	        	times.add(tv.getTime());
	    return times;
	}
	
	public List<Double> getValuesInRange(TimeSeriesValue s, double minValue, double maxValue) {
	    List<Double> values = new ArrayList<Double>();
	    for (TimeValue tv: s.getTimeValues()) {
	        double value = tv.getValue();
	        if (value >= minValue && value <= maxValue)
	        	values.add(value);
	    }
	    return values;
	}
	
	public List<TimeValue> getTimeValuesInRange(TimeSeriesValue s, int minTime, int maxTime, double minValue, 
			double maxValue) {
	    List<TimeValue> timeValues = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues()) {
	        double value = tv.getValue();
	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime && value >= minValue && value <= maxValue)
	            timeValues.add(tv);
	    }
	    return timeValues;
	}

	public Double getValueAt(TimeSeriesValue s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size())
	    	return s.getTimeValues().get(index).getValue();
        throw new IllegalArgumentException("Index is out of range");
	}

	public int getTimeAt(TimeSeriesValue s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size())
	    	return s.getTimeValues().get(index).getTime();
        throw new IllegalArgumentException("Index is out of range");
	}

	public TimeValue getTimeValueAt(TimeSeriesValue s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size())
	    	return s.getTimeValues().get(index);
        throw new IllegalArgumentException("Index is out of range");
	}

	public int getMaxTime(TimeSeriesValue s) {
	    return s.getTimeValues().stream()
	            .mapToInt(TimeValue::getTime)
	            .max()
	            .orElseThrow(NoSuchElementException::new);
	}

	public int getMinTime(TimeSeriesValue s) {
	    return s.getTimeValues().stream()
	            .mapToInt(TimeValue::getTime)
	            .min()
	            .orElseThrow(NoSuchElementException::new);
	}

	public double getMaxValue(TimeSeriesValue s) {
	    return s.getTimeValues().stream()
	            .mapToDouble(tv -> tv.getValue())
	            .max()
	            .orElseThrow(NoSuchElementException::new);
	}

	public double getMinValue(TimeSeriesValue s) {
	    return s.getTimeValues().stream()
	            .mapToDouble(tv -> tv.getValue())
	            .min()
	            .orElseThrow(NoSuchElementException::new);
	}
	
	public TimeValue getNextTimeValue(TimeSeriesValue s, TimeValue tv) {
	    int index = s.getTimeValues().indexOf(tv);
	    if (index >= 0 && index < s.getTimeValues().size() - 1) {
	        return s.getTimeValues().get(index + 1);
	    }
        throw new IllegalArgumentException("Index is out of range");
	}

	public int getNextTime(TimeSeriesValue s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}

	public double getNextValue(TimeSeriesValue s, TimeValue tv) {
	    TimeValue nextTV = getNextTimeValue(s, tv);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}

	public TimeValue getNextTimeValue(TimeSeriesValue s, int index) {
	    if (index >= 0 && index < s.getTimeValues().size() - 1) {
	        return s.getTimeValues().get(index + 1);
	    }
        throw new IllegalArgumentException("Index is out of range");
	}

	public int getNextTime(TimeSeriesValue s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getTime() : -1;
	}

	public double getNextValue(TimeSeriesValue s, int index) {
	    TimeValue nextTV = getNextTimeValue(s, index);
	    return (nextTV != null) ? nextTV.getValue() : null;
	}

	public TimeValue getPreviousTimeValue(TimeSeriesValue s, TimeValue tv) {
	    int index = s.getTimeValues().indexOf(tv);
	    if (index > 0) {
	        return s.getTimeValues().get(index - 1);
	    }
	    return null;
	}

	public int getPreviousTime(TimeSeriesValue s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}

	public double getPreviousValue(TimeSeriesValue s, TimeValue tv) {
	    TimeValue prevTV = getPreviousTimeValue(s, tv);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}

	public TimeValue getPreviousTimeValue(TimeSeriesValue s, int index) {
	    if (index > 0 && index < s.getTimeValues().size())
	        return s.getTimeValues().get(index - 1);
	    return null;
	}

	public int getPreviousTime(TimeSeriesValue s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getTime() : -1;
	}

	public double getPreviousValue(TimeSeriesValue s, int index) {
	    TimeValue prevTV = getPreviousTimeValue(s, index);
	    return (prevTV != null) ? prevTV.getValue() : null;
	}

	public int getFirstTime(TimeSeriesValue s) {
	    return s.getTimeValues().isEmpty() ? -1 : s.getTimeValues().get(0).getTime();
	}

	public double getFirstValue(TimeSeriesValue s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(0).getValue();
	}

	public TimeValue getFirstTimeValue(TimeSeriesValue s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(0);
	}

	public int getLastTime(TimeSeriesValue s) {
	    return s.getTimeValues().isEmpty() ? -1 : s.getTimeValues().get(s.getTimeValues().size() - 1).getTime();
	}

	public double getLastValue(TimeSeriesValue s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(s.getTimeValues().size() - 1).getValue();
	}

	public TimeValue getLastTimeValue(TimeSeriesValue s) {
	    return s.getTimeValues().isEmpty() ? null : s.getTimeValues().get(s.getTimeValues().size() - 1);
	}

	public TimeSeriesValue copyTimeSeriesValue(TimeSeriesValue s) {
	    TimeSeriesValue copy = ReadFactory.eINSTANCE.createTimeSeriesValue();
	    for (TimeValue tv : s.getTimeValues()) {
	        TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
	        tvCopy.setTime(tv.getTime());
	        tvCopy.setValue(tv.getValue());
	        copy.getTimeValues().add(tvCopy);
	    }
	    return copy;
	}

	public void setAllTimesToZero(TimeSeriesValue s) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setTime(0);
	}

	public void setAllValuesToZero(TimeSeriesValue s) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setValue(0);
	}

	public void setAllToZero(TimeSeriesValue s) {
	    setAllTimesToZero(s);
	    setAllValuesToZero(s);
	}

	public void setAllTimesToTime(TimeSeriesValue s, int time) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setTime(time);
	}

	public void setAllValuesToValue(TimeSeriesValue s, int value) {
	    for (TimeValue tv : s.getTimeValues())
	        tv.setValue(value);
	}

	public void setAllToTimeValue(TimeSeriesValue s, int time, int value) {
	    setAllTimesToTime(s, time);
	    setAllValuesToValue(s, value);
	}

	public TimeSeriesValue getSubTimeSeriesValueInTimeRange(TimeSeriesValue s, int minTime, int maxTime) {
	    TimeSeriesValue subSeries = ReadFactory.eINSTANCE.createTimeSeriesValue();
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

	public int countTimeValues(TimeSeriesValue s) {
	    return s.getTimeValues().size();
	}

	public boolean isEmpty(TimeSeriesValue s) {
	    return s.getTimeValues().size() == 0;
	}

	public void printTimeSeriesValue(TimeSeriesValue s) {
		for(TimeValue tv: s.getTimeValues())
			System.out.println(tv.getTime() + " " + tv.getValue());
	}
	
	//TODO: aux methods for Proteus only
	//this method reads from file a Proteus timeserie (to be used during TimeSeriesValue mutation phase)
	public void writeTSToFile(TimeSeriesValue TimeSeriesValue, String filePath) throws IOException {
		reorderTimeSeriesValue(TimeSeriesValue);
        List<String> lines = TimeSeriesValue.getTimeValues().stream()
            .map(tv -> tv.getTime() + " " + tv.getValue())
            .collect(Collectors.toList());
        Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

	//this method writes to file a Proteus timeserie (to be used during TimeSeriesValue mutation phase)
    public TimeSeriesValue readTSFromFile(String filePath) throws IOException {
    	
    	
    	
    	TimeSeriesValue TimeSeriesValue = ReadFactory.eINSTANCE.createTimeSeriesValue();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 2)
                throw new IOException("Invalid line format: " + line);
            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
    		tv.setTime(Integer.parseInt(parts[1].trim()));
            double value = (Double.parseDouble(parts[0].trim()));
            tv.setValue(value);
            TimeSeriesValue.getTimeValues().add(tv);
        }
        reorderTimeSeriesValue(TimeSeriesValue);
        return TimeSeriesValue;
    }
	
    
	//TODO: method that create test folder and content after mutation, method that reads/compare output files for the Discoverer
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********** TODO: DISCOVERY OPERATORS **********/
	public boolean isCommission(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated) {
		return tsOriginal.getTimeValues().size() < tsMutated.getTimeValues().size(); //TODO: what if more elements are added and removed?
	}
	
	public boolean isOmission(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated) {
		return tsOriginal.getTimeValues().size() > tsMutated.getTimeValues().size(); //TODO: what if more elements are added and removed?
	}
	
	public boolean isLate(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			int time1 = tv1.getTime();			
			double value1 = tv1.getValue();
			int closestDiffTime = Integer.MAX_VALUE;
			for(int j=1; j<tsMutated.getTimeValues().size(); j++) {
				TimeValue tv2 = tsMutated.getTimeValues().get(i);
				int time2 = tv2.getTime();			
				double value2 = tv2.getValue();
				//if time and value are the same, no early/late
				if(time1 == time2 && value1 == value2) {
					closestDiffTime = 0;
					break;
				}
				//if only value is the same, check closest time
				else if (value1 == value2){
					int timeDiff = time1 - time2;//diff between times
					if(Math.abs(timeDiff)<=Math.abs(closestDiffTime)) {
						closestDiffTime = timeDiff;
					}
					else
						break;//the TimeSeriesValue are ordered so once the diff is getting bigger the cycle can stop
				}
			}
			if(closestDiffTime == 0)//no early/late detected for TimeSeriesValue j as diff time is 0
				continue;
			if(Math.abs(closestDiffTime)>eps && closestDiffTime<0)//if diff time bigger than epsilon, late detected
				return true;
		}	
		return false;
	}
	
	public boolean isEarly(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps) {
		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
			int time1 = tv1.getTime();			
			double value1 = tv1.getValue();
			int closestDiffTime = Integer.MAX_VALUE;
			for(int j=1; j<tsMutated.getTimeValues().size(); j++) {
				TimeValue tv2 = tsMutated.getTimeValues().get(i);
				int time2 = tv2.getTime();			
				double value2 = tv2.getValue();
				//if time and value are the same, no early/late
				if(time1 == time2 && value1 == value2) {
					closestDiffTime = 0;
					break;
				}
				//if only value is the same, check closest time
				else if (value1 == value2){
					int timeDiff = time1 - time2;//diff between times
					if(Math.abs(timeDiff)<=Math.abs(closestDiffTime)) {
						closestDiffTime = timeDiff;
					}
					else
						break;//the TimeSeriesValue are ordered so once the diff is getting bigger the cycle can stop
				}
			}
			if(closestDiffTime == 0)//no early/late detected for TimeSeriesValue j as diff time is 0
				continue;
			if(Math.abs(closestDiffTime)> eps && closestDiffTime>0) //if diff time bigger than epsilon, early detected
				return true;
		}	
		return false;
	}
	
	public boolean isValueCoarse(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps, int minValue, int maxValue) {
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
	
	public boolean isValueSubtle(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps, int minValue, int maxValue) {
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
	
	
	
	
	
	
	
	
	
}
