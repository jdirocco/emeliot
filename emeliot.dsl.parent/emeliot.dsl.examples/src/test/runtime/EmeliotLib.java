package test.runtime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

public class EmeliotLib {
	
	
	public void reorderTimeSeries(TimeSeries s) {
		List<TimeValue> timeValues = new ArrayList<>(s.getTimeValues());
	    timeValues.sort(Comparator.comparingInt(TimeValue::getTime));
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
	
	public boolean existTime(TimeSeries s, int time) {
	    for (TimeValue tv: s.getTimeValues())
	    	if (tv.getTime() == time)
	            return true;
	    return false;
	}
	
	

	public List<Integer> getAllTimes(TimeSeries s) {
	    List<Integer> times = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues())
	        times.add(tv.getTime());
	    return times;
	}
	
	public List<TimeValue> getTimesInRange(TimeSeries s, int minTime, int maxTime) {
	    List<TimeValue> timeValues = new ArrayList<>();
	    for (TimeValue tv: s.getTimeValues())
	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime)
	            timeValues.add(tv);
	    return timeValues;
	}

	
	

}