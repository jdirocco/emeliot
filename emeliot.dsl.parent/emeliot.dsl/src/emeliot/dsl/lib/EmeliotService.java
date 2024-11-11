package emeliot.dsl.lib;

import java.io.IOException;
import java.util.List;

import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;

public interface EmeliotService {

    
    
    
    
    
    
    
    
	
	

	
	
	
	/**********TODO: UTILS OPERATORS **********/
    
    
    public void printTimeSeries(TimeSeries s);
	
    public void reorderTimeSeries(TimeSeries s);

	public TimeValue selectRandomTimeValue(TimeSeries s);

	public boolean existTime(TimeSeries s, int time);
	
	public boolean existValue(TimeSeries s, double value);
	
	public boolean existTimeValue(TimeSeries s, int time, double value);

	public List<Integer> getAllTimes(TimeSeries s);

	public List<Double> getAllValues(TimeSeries s);

	public List<TimeValue> getTimesInRange(TimeSeries s, int minTime, int maxTime) ;

	public List<TimeValue> getValuesInRange(TimeSeries s, double minValue, double maxValue);

	public double getValueAt(TimeSeries s, int index);

	public int getTimeAt(TimeSeries s, int index);

	public TimeValue getTimeValueAt(TimeSeries s, int index);

	public int getMaxTime(TimeSeries s) ;

	public int getMinTime(TimeSeries s);

	public double getMaxValue(TimeSeries s) ;

	public double getMinValue(TimeSeries s) ;


	public TimeValue getNextTimeValue(TimeSeries s, TimeValue tv) ;

	public int getNextTime(TimeSeries s, TimeValue tv);

	public double getNextValue(TimeSeries s, TimeValue tv);

	public TimeValue getNextTimeValue(TimeSeries s, int index);

	public int getNextTime(TimeSeries s, int index);

	public double getNextValue(TimeSeries s, int index) ;

	public TimeValue getPreviousTimeValue(TimeSeries s, TimeValue tv);

	public int getPreviousTime(TimeSeries s, TimeValue tv) ;

	public double getPreviousValue(TimeSeries s, TimeValue tv);

	public TimeValue getPreviousTimeValue(TimeSeries s, int index);

	public int getPreviousTime(TimeSeries s, int index);

	public double getPreviousValue(TimeSeries s, int index);

	public int getFirstTime(TimeSeries s);

	public double getFirstValue(TimeSeries s);

	public TimeValue getFirstTimeValue(TimeSeries s) ;
	public int getLastTime(TimeSeries s);

	public double getLastValue(TimeSeries s);

	public TimeValue getLastTimeValue(TimeSeries s) ;

	public TimeSeries copyTimeSeries(TimeSeries s);


//	public TimeSeries getSubTimeSeriesInTimeRange(TimeSeries s, int minTime, int maxTime) {
//	    TimeSeries subSeries = ReadFactory.eINSTANCE.createTimeSeries();
//	    for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
//	        if (tv.getTime() >= minTime && tv.getTime() <= maxTime) {
//	            TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
//	            tvCopy.setTime(tv.getTime());
//	            tvCopy.setValue(tv.getValue());
//	            subSerie((TimeSeriesValue) s).getTimeValues().add(tvCopy);
//	        }
//	    }
//	    return subSeries;
//	}

	public int countTimeValues(TimeSeries s);

	public boolean isEmpty(TimeSeries s);


	
	
	
	
	
	
	
	
	
	
	

	public void writeTSToFile(TimeSeriesValue TimeSeriesValue, String filePath) throws IOException;

	//this method writes to file a Proteus timeserie (to be used during TimeSeriesValue mutation phase)
    public TimeSeriesValue readTSFromFile(String filePath) throws IOException ;
	

}
