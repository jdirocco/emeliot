package emeliot.dsl.lib;

import java.io.IOException;
import java.util.List;

import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;

public interface EmeliotService {

    
		/**
	 * Reorders the time series in ascending order based on time values.
	 *
	 * @param s the time series to reorder
	 */
	public void reorderTimeSeries(TimeSeries s);
	
	
	/**
	 * Selects a random time-value pair from the time series.
	 *
	 * @param s the time series from which to select
	 * @return a randomly selected time-value pair
	 */
	public TimeValue selectRandomTimeValue(TimeSeries s);
	
	
	/**
	 * Checks if the specified time exists in the time series.
	 *
	 * @param s the time series to search
	 * @param time the time to look for
	 * @return {@code true} if the time exists, {@code false} otherwise
	 */
	public boolean existTime(TimeSeries s, double time);
	
	
	/**
	 * Checks if the specified value exists in the time series.
	 *
	 * @param s the time series to search
	 * @param value the value to look for
	 * @return {@code true} if the value exists, {@code false} otherwise
	 */
	public boolean existValue(TimeSeries s, double value);

	
	/**
	 * Checks if a specific time-value pair exists in the time series.
	 *
	 * @param s the time series to search
	 * @param time the time to look for
	 * @param value the value to look for
	 * @return {@code true} if the time-value pair exists, {@code false} otherwise
	 */
	public boolean existTimeValue(TimeSeries s, double time, double value);

	
	/**
	 * Retrieves all times from the time series.
	 *
	 * @param s the time series to query
	 * @return a list of all time values
	 */
	public List<Double> getAllTimes(TimeSeries s);
	
	
	/**
	 * Retrieves all values from the time series.
	 *
	 * @param s the time series to query
	 * @return a list of all values
	 */
	public List<Double> getAllValues(TimeSeries s);

	
	/**
	 * Retrieves all times within a specified range.
	 *
	 * @param s the time series to query
	 * @param timeMin the minimum time (inclusive)
	 * @param timeMax the maximum time (inclusive)
	 * @return a list of time values within the range
	 */
	public List<Double> getTimesInRange(TimeSeries s, double timeMin, double timeMax);

	
	/**
	 * Retrieves all values within a specified range.
	 *
	 * @param s the time series to query
	 * @param valueMin the minimum value (inclusive)
	 * @param valueMax the maximum value (inclusive)
	 * @return a list of values within the range
	 */
	public List<Double> getValuesInRange(TimeSeries s, double valueMin, double valueMax);

	
	/**
	 * Retrieves all time-value pairs within specified ranges for both time and value.
	 *
	 * @param s the time series to query
	 * @param timeMin the minimum time (inclusive)
	 * @param timeMax the maximum time (inclusive)
	 * @param valueMin the minimum value (inclusive)
	 * @param valueMax the maximum value (inclusive)
	 * @return a list of time-value pairs within the specified ranges
	 */
	public List<TimeValue> getTimeValuesInRange(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax);
	
	
	/**
	 * Retrieves the value at a specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the value
	 * @return the value at the specified index
	 */
	public double getValueAt(TimeSeries s, int index);

	
	/**
	 * Retrieves the time at a specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the time
	 * @return the time at the specified index
	 */
	public double getTimeAt(TimeSeries s, int index);

	
	/**
	 * Retrieves the time-value pair at a specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the time-value pair
	 * @return the time-value pair at the specified index
	 */
	public TimeValue getTimeValueAt(TimeSeries s, int index);

	
	/**
	 * Retrieves the maximum time in the series.
	 *
	 * @param s the time series to query
	 * @return the maximum time value
	 */
	public double getMaxTime(TimeSeries s);
	
	
	/**
	 * Retrieves the minimum time in the series.
	 *
	 * @param s the time series to query
	 * @return the minimum time value
	 */
	public double getMinTime(TimeSeries s);

	
	/**
	 * Retrieves the maximum value in the series.
	 *
	 * @param s the time series to query
	 * @return the maximum value
	 */
	public double getMaxValue(TimeSeries s);

	
	/**
	 * Retrieves the minimum value in the series.
	 *
	 * @param s the time series to query
	 * @return the minimum value
	 */
	public double getMinValue(TimeSeries s);

	
	/**
	 * Retrieves the time-value pair immediately following the specified pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the next time-value pair, or {@code null} if none exists
	 */
	public TimeValue getNextTimeValue(TimeSeries s, TimeValue tv);
	
	
	/**
	 * Retrieves the time immediately following the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the next time, or {@code Double.NaN} if none exists
	 */
	public double getNextTime(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the value immediately following the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the next value, or {@code Double.NaN} if none exists
	 */
	public double getNextValue(TimeSeries s, TimeValue tv);
	
	
	/**
	 * Retrieves the time-value pair immediately following the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the next time-value pair, or {@code null} if none exists
	 */
	public TimeValue getNextTimeValue(TimeSeries s, int index);

	
	/**
	 * Retrieves the time immediately following the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the next time, or {@code Double.NaN} if none exists
	 */
	public double getNextTime(TimeSeries s, int index);

	
	/**
	 * Retrieves the value immediately following the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the next value, or {@code Double.NaN} if none exists
	 */
	public double getNextValue(TimeSeries s, int index);

	
	/**
	 * Retrieves the time-value pair immediately preceding the specified pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the previous time-value pair, or {@code null} if none exists
	 */
	public TimeValue getPreviousTimeValue(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the time immediately preceding the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the previous time, or {@code Double.NaN} if none exists
	 */
	public double getPreviousTime(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the value immediately preceding the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the previous value, or {@code Double.NaN} if none exists
	 */
	public double getPreviousValue(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the time-value pair immediately preceding the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the previous time-value pair, or {@code null} if none exists
	 */
	public TimeValue getPreviousTimeValue(TimeSeries s, int index);

	
	/**
	 * Retrieves the time immediately preceding the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the previous time, or {@code Double.NaN} if none exists
	 */
	public double getPreviousTime(TimeSeries s, int index);

	
	/**
	 * Retrieves the value immediately preceding the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the previous value, or {@code Double.NaN} if none exists
	 */
	public double getPreviousValue(TimeSeries s, int index);

	
	/**
	 * Retrieves the first time in the series.
	 *
	 * @param s the time series to query
	 * @return the first time value, or {@code Double.NaN} if the series is empty
	 */
	public double getFirstTime(TimeSeries s);

	
	/**
	 * Retrieves the first value in the series.
	 *
	 * @param s the time series to query
	 * @return the first value, or {@code Double.NaN} if the series is empty
	 */
	public double getFirstValue(TimeSeries s);

	
	/**
	 * Retrieves the first time-value pair in the series.
	 *
	 * @param s the time series to query
	 * @return the first time-value pair, or {@code null} if the series is empty
	 */
	public TimeValue getFirstTimeValue(TimeSeries s);

	
	/**
	 * Retrieves the last time in the series.
	 *
	 * @param s the time series to query
	 * @return the last time value, or {@code Double.NaN} if the series is empty
	 */
	public double getLastTime(TimeSeries s);

	
	/**
	 * Retrieves the last value in the series.
	 *
	 * @param s the time series to query
	 * @return the last value, or {@code Double.NaN} if the series is empty
	 */
	public double getLastValue(TimeSeries s);

	
	/**
	 * Retrieves the last time-value pair in the series.
	 *
	 * @param s the time series to query
	 * @return the last time-value pair, or {@code null} if the series is empty
	 */
	public TimeValue getLastTimeValue(TimeSeries s);

	
	/**
	 * Creates a copy of the specified time series.
	 *
	 * @param s the time series to copy
	 * @return a new time series that is a copy of the input
	 */
	public TimeSeries copyTimeSeries(TimeSeries s);

	
	/**
	 * Sets all times in the series to zero.
	 *
	 * @param s the time series to modify
	 */
	public void setAllTimesToZero(TimeSeries s);

	
	/**
	 * Sets all values in the series to zero.
	 *
	 * @param s the time series to modify
	 */
	public void setAllValuesToZero(TimeSeries s);

	
	/**
	 * Sets all times and values in the series to zero.
	 *
	 * @param s the time series to modify
	 */
	public void setAllToZero(TimeSeries s);

	
	/**
	 * Sets all times in the series to a specified value.
	 *
	 * @param s the time series to modify
	 * @param time the time to set
	 */
	public void setAllTimesToTime(TimeSeries s, double time);

	
	/**
	 * Sets all values in the series to a specified value.
	 *
	 * @param s the time series to modify
	 * @param value the value to set
	 */
	public void setAllValuesToValue(TimeSeries s, double value);

	
	/**
	 * Sets all times and values in the series to a specified time and value.
	 *
	 * @param s the time series to modify
	 * @param time the time to set
	 * @param value the value to set
	 */
	public void setAllToTimeValue(TimeSeries s, double time, double value);

	
	/**
	 * Retrieves a sub-time series containing time-value pairs within a specified time range.
	 *
	 * @param s the time series to query
	 * @param timeMin the minimum time (inclusive)
	 * @param timeMax the maximum time (inclusive)
	 * @return a sub-time series within the specified range
	 */
	public TimeSeries getSubTimeSeriesInTimeRange(TimeSeries s, double timeMin, double timeMax);

	
	/**
	 * Counts the total number of time-value pairs in the time series.
	 *
	 * @param s the time series to query
	 * @return the count of time-value pairs
	 */
	public int countTimeValues(TimeSeries s);

	
	/**
	 * Checks if the time series is empty.
	 *
	 * @param s the time series to query
	 * @return {@code true} if the time series is empty, {@code false} otherwise
	 */
	public boolean isEmpty(TimeSeries s);

	
	/**
	 * Prints all time-value pairs in the time series to the console.
	 *
	 * @param s the time series to print
	 */
	public void printTimeSeries(TimeSeries s);

	
	/**
	 * Writes a time series to a file.
	 *
	 * @param timeSeriesValue the time series to write
	 * @param filePath the path of the file to write to
	 * @throws IOException if an I/O error occurs
	 */
	public void writeTSToFile(TimeSeriesValue timeSeriesValue, String filePath) throws IOException;

	
	/**
	 * Reads a time series from a file.
	 *
	 * @param filePath the path of the file to read from
	 * @return the time series read from the file
	 * @throws IOException if an I/O error occurs
	 */
	public TimeSeriesValue readTSFromFile(String filePath) throws IOException;

	

}
