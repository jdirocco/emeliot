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
	 * Reads a TimeSeries from a file and reorders its time values.
	 *
	 * @param tsInputPath  the path to the input file containing the TimeSeries.
	 * @param tsOutputPath the path to the output file to save the reordered TimeSeries.
	 * @throws IOException if an I/O error occurs while reading the files.
	 */
	public void reorderTimeSeries_File(String tsInputPath, String tsOutputPath) throws IOException;

	
	/**
	 * Selects a random time-value pair from the time series.
	 *
	 * @param s the time series from which to select
	 * @return a randomly selected time-value pair
	 */
	public TimeValue selectRandomTimeValue(TimeSeries s);
	
	
	/**
	 * Reads a TimeSeries from a file and selects a random TimeValue from it.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @return a randomly selected TimeValue from the TimeSeries, or {@code null} if the TimeSeries is empty.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public TimeValue selectRandomTimeValue_File(String tsInputPath) throws IOException;

	
	/**
	 * Checks if the specified time exists in the time series.
	 *
	 * @param s the time series to search
	 * @param time the time to look for
	 * @return {@code true} if the time exists, {@code false} otherwise
	 */
	public boolean existTime(TimeSeries s, double time);
	
	
	/**
	 * Checks if a given time value exists in a TimeSeries read from a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param time        the time value to check for existence.
	 * @return {@code true} if the time value exists in the TimeSeries, {@code false} otherwise.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public boolean existTime_File(String tsInputPath, double time) throws IOException;

	
	/**
	 * Checks if the specified value exists in the time series.
	 *
	 * @param s the time series to search
	 * @param value the value to look for
	 * @return {@code true} if the value exists, {@code false} otherwise
	 */
	public boolean existValue(TimeSeries s, double value);

	
	/**
	 * Checks if a given value exists in a TimeSeries read from a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param value       the value to check for existence.
	 * @return {@code true} if the value exists in the TimeSeries, {@code false} otherwise.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public boolean existValue_File(String tsInputPath, double value) throws IOException;

	
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
	 * Checks if a given time-value pair exists in a TimeSeries read from a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param time        the time value to check for existence.
	 * @param value       the value to check for existence.
	 * @return {@code true} if the time-value pair exists in the TimeSeries, {@code false} otherwise.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public boolean existTimeValue_File(String tsInputPath, double time, double value) throws IOException;

	
	/**
	 * Retrieves all times from the time series.
	 *
	 * @param s the time series to query
	 * @return a list of all time values
	 */
	public List<Double> getAllTimes(TimeSeries s);
	
	
	/**
	 * Reads a TimeSeries from a file and retrieves all its time values.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @return a list of all time values in the TimeSeries.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public List<Double> getAllTimes_File(String tsInputPath) throws IOException;

	
	/**
	 * Retrieves all values from the time series.
	 *
	 * @param s the time series to query
	 * @return a list of all values
	 */
	public List<Double> getAllValues(TimeSeries s);

	
	/**
	 * Reads a TimeSeries from a file and retrieves all its values.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @return a list of all values in the TimeSeries.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public List<Double> getAllValues_File(String tsInputPath) throws IOException;

	
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
	 * Reads a TimeSeries from a file and retrieves all time values within a given range.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param timeMin     the minimum time value of the range.
	 * @param timeMax     the maximum time value of the range.
	 * @return a list of time values within the specified range.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public List<Double> getTimesInRange_File(String tsInputPath, double timeMin, double timeMax) throws IOException;

	
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
	 * Reads a TimeSeries from a file and retrieves all values within a given range.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param valueMin    the minimum value of the range.
	 * @param valueMax    the maximum value of the range.
	 * @return a list of values within the specified range.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public List<Double> getValuesInRange_File(String tsInputPath, double valueMin, double valueMax) throws IOException;

	
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
	 * Reads a TimeSeries from a file and retrieves all TimeValue pairs within specified time and value ranges.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param timeMin     the minimum time value of the range.
	 * @param timeMax     the maximum time value of the range.
	 * @param valueMin    the minimum value of the range.
	 * @param valueMax    the maximum value of the range.
	 * @return a list of TimeValue pairs within the specified ranges.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public List<TimeValue> getTimeValuesInRange_File(String tsInputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException;

	
	/**
	 * Retrieves the value at a specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the value
	 * @return the value at the specified index
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getValueAt(TimeSeries s, int index);

	
	/**
	 * Reads a TimeSeries from a file and retrieves the value at the specified index.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param index       the index of the value to retrieve.
	 * @return the value at the specified index.
	 * @throws IOException if an I/O error occurs while reading the file.
	 * @throws IllegalArgumentException if the index is out of range.
	 */
	public double getValueAt_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the time at a specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the time
	 * @return the time at the specified index
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getTimeAt(TimeSeries s, int index);

	
	/**
	 * Reads a TimeSeries from a file and retrieves the time at the specified index.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param index       the index of the time to retrieve.
	 * @return the time at the specified index.
	 * @throws IOException if an I/O error occurs while reading the file.
	 * @throws IllegalArgumentException if the index is out of range.
	 */
	public double getTimeAt_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the time-value pair at a specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the time-value pair
	 * @return the time-value pair at the specified index
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public TimeValue getTimeValueAt(TimeSeries s, int index);
	
	
	/**
	 * Reads a TimeSeries from a file and retrieves the TimeValue at the specified index.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @param index       the index of the TimeValue to retrieve.
	 * @return the TimeValue at the specified index.
	 * @throws IOException if an I/O error occurs while reading the file.
	 * @throws IllegalArgumentException if the index is out of range.
	 */
	public TimeValue getTimeValueAt_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the maximum time in the series.
	 *
	 * @param s the time series to query
	 * @return the maximum time value
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMaxTime(TimeSeries s);
	
	
	/**
	 * Reads a TimeSeries from a file and retrieves the maximum time value.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @return the maximum time value in the TimeSeries.
	 * @throws IOException if an I/O error occurs while reading the file.
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMaxTime_File(String tsInputPath) throws IOException;

	
	/**
	 * Retrieves the minimum time in the series.
	 *
	 * @param s the time series to query
	 * @return the minimum time value
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMinTime(TimeSeries s);

	
	/**
	 * Retrieves the minimum time from a TimeSeries stored in a file.
	 * 
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the minimum time value in the TimeSeries.
	 * @throws IOException if an I/O error occurs while reading the file.
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMinTime_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Retrieves the maximum value in the series.
	 *
	 * @param s the time series to query
	 * @return the maximum value
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMaxValue(TimeSeries s);

	
	/**
	 * Retrieves the maximum value from a TimeSeries stored in a file.
	 * 
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the maximum value
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMaxValue_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Retrieves the minimum value in the series.
	 *
	 * @param s the time series to query
	 * @return the minimum value
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMinValue(TimeSeries s);
	
	
	/**
	 * Retrieves the minimum value from a TimeSeries stored in a file.
	 * 
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the minimum value
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the TimeSeries is empty.
	 */
	public double getMinValue_File(String tsInputPath) throws IOException;

	
	/**
	 * Retrieves the time-value pair immediately following the specified pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the next time-value pair
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public TimeValue getNextTimeValue(TimeSeries s, TimeValue tv);
	
	
	/**
	 * Retrieves the next TimeValue following the given TimeValue in a TimeSeries stored in a file.
	 * 
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param tv          the current TimeValue
	 * @return the next TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public TimeValue getNextTimeValue_File(String tsInputPath, TimeValue tv) throws IOException;

	
	/**
	 * Retrieves the time immediately following the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the time of the next TimeValue
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getNextTime(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the time of the next TimeValue following the given TimeValue in a TimeSeries stored in a file.
	 * 
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param tv          the current TimeValue
	 * @return the time of the next TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getNextTime_File(String tsInputPath, TimeValue tv) throws IOException;

	
	/**
	 * Retrieves the value immediately following the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the value of the next TimeValue
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getNextValue(TimeSeries s, TimeValue tv);
	
	
	/**
	 * Retrieves the value of the next TimeValue following the given TimeValue in a TimeSeries stored in a file.
	 * 
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param tv          the current TimeValue
	 * @return the value of the next TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getNextValue_File(String tsInputPath, TimeValue tv) throws IOException;

	
	/**
	 * Retrieves the time-value pair immediately following the specified index.
	 *
	 * @param s the time series to query
	 * @param index       the current index
	 * @return the next TimeValue
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public TimeValue getNextTimeValue(TimeSeries s, int index);

	
	/**
	 * Retrieves the time-value pair immediately following the specified index from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param index       the current index
	 * @return the next TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public TimeValue getNextTimeValue_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the time immediately following the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the next time in the time series
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getNextTime(TimeSeries s, int index);

	
	/**
	 * Retrieves the time of the next TimeValue at the specified index from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param index       the current index
	 * @return the next time in the time series
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getNextTime_File(String tsInputPath, int index) throws IOException;
	
	
	/**
	 * Retrieves the value immediately following the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the next value
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getNextValue(TimeSeries s, int index);
	
	
	/**
	 * Retrieves the value of the next TimeValue at the specified index from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param index       the current index
	 * @return the value of the next TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getNextValue_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the time-value pair immediately preceding the specified pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the previous time-value pair
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public TimeValue getPreviousTimeValue(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the previous TimeValue relative to the given TimeValue from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param tv          the current TimeValue
	 * @return the previous TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public TimeValue getPreviousTimeValue_File(String tsInputPath, TimeValue tv) throws IOException;
	
	
	/**
	 * Retrieves the time immediately preceding the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the previous time
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getPreviousTime(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the time of the previous TimeValue relative to the given TimeValue from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param tv          the current TimeValue
	 * @return the time of the previous TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getPreviousTime_File(String tsInputPath, TimeValue tv) throws IOException;

	
	/**
	 * Retrieves the value immediately preceding the specified time-value pair.
	 *
	 * @param s the time series to query
	 * @param tv the reference time-value pair
	 * @return the previous value
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getPreviousValue(TimeSeries s, TimeValue tv);

	
	/**
	 * Retrieves the value of the previous TimeValue relative to the given TimeValue from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param tv          the current TimeValue
	 * @return the value of the previous TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if tv is not in the timeseries
	 */
	public double getPreviousValue_File(String tsInputPath, TimeValue tv) throws IOException;

	
	/**
	 * Retrieves the time-value pair immediately preceding the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the previous time-value pair
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public TimeValue getPreviousTimeValue(TimeSeries s, int index);
		
	
	/**
	 * Retrieves the previous TimeValue at the specified index from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param index       the current index
	 * @return the previous TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public TimeValue getPreviousTimeValue_File(String tsInputPath, int index) throws IOException;


	/**
	 * Retrieves the time immediately preceding the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the previous time
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getPreviousTime(TimeSeries s, int index);
	
	
	/**
	 * Retrieves the time of the previous TimeValue at the specified index from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param index       the current index
	 * @return the time of the previous TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getPreviousTime_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the value immediately preceding the specified index.
	 *
	 * @param s the time series to query
	 * @param index the index of the reference time-value pair
	 * @return the previous value
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getPreviousValue(TimeSeries s, int index);

	
	/**
	 * Retrieves the value of the previous TimeValue at the specified index from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @param index       the current index
	 * @return the value of the previous TimeValue
	 * @throws IOException if there is an error reading the file
	 * @throws IllegalArgumentException if the index is out of range
	 */
	public double getPreviousValue_File(String tsInputPath, int index) throws IOException;

	
	/**
	 * Retrieves the first time in the series.
	 *
	 * @param s the time series to query
	 * @return the first time value
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getFirstTime(TimeSeries s);

	
	/**
	 * Retrieves the first time from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the first time value
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getFirstTime_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Retrieves the first value in the series.
	 *
	 * @param s the time series to query
	 * @return the first value
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getFirstValue(TimeSeries s);
	
	
	/**
	 * Retrieves the first value from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the first value
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getFirstValue_File(String tsInputPath) throws IOException;

	
	/**
	 * Retrieves the first time-value pair in the series.
	 *
	 * @param s the time series to query
	 * @return the first time-value pair
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public TimeValue getFirstTimeValue(TimeSeries s);
	
	
	/**
	 * Retrieves the first TimeValue from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the first time-value pair
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public TimeValue getFirstTimeValue_File(String tsInputPath) throws IOException;

	
	/**
	 * Retrieves the last time in the series.
	 *
	 * @param s the time series to query
	 * @return the last time value
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getLastTime(TimeSeries s);

	
	/**
	 * Retrieves the last time from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the last time value
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getLastTime_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Retrieves the last value in the series.
	 *
	 * @param s the time series to query
	 * @return the last value
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getLastValue(TimeSeries s);

	
	/**
	 * Retrieves the last value from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the last value
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public double getLastValue_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Retrieves the last time-value pair in the series.
	 *
	 * @param s the time series to query
	 * @return the last time-value pair
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public TimeValue getLastTimeValue(TimeSeries s);
	
	
	/**
	 * Retrieves the last TimeValue from a TimeSeries stored in a file.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries
	 * @return the last time-value pair
	 * @throws IOException if there is an error reading the file
	 * @throws NoSuchElementException if the timeseries is empty
	 */
	public TimeValue getLastTimeValue_File(String tsInputPath) throws IOException;

	
	/**
	 * Creates a copy of the specified time series.
	 *
	 * @param s the time series to copy
	 * @return a new time series that is a copy of the input
	 */
	public TimeSeries copyTimeSeries(TimeSeries s);

	
	/**
	 * Reads a TimeSeries from a file and creates a copy.
	 * 
	 * @param tsInputPath the path to the file containing the TimeSeries
	 * @return a new TimeSeries instance with copied values
	 * @throws IOException if an error occurs while reading the file
	 */
	public TimeSeries copyTimeSeries_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Sets all times in the series to zero.
	 *
	 * @param s the time series to modify
	 */
	public void setAllTimesToZero(TimeSeries s);

	
	/**
	 * Reads a TimeSeries from a file and sets all time values to zero.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param tsOutputPath the path to the output file to save the modified TimeSeries.
	 * @throws IOException if an error occurs while reading the file
	 */
	public void setAllTimesToZero_File(String tsInputPath, String tsOutputPath) throws IOException;
	
	
	/**
	 * Sets all values in the series to zero.
	 *
	 * @param s the time series to modify
	 */
	public void setAllValuesToZero(TimeSeries s);
	
		
	/**
	 * Reads a TimeSeries from a file and sets all value fields to zero.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param tsOutputPath the path to the output file to save the modified TimeSeries.
	 * @throws IOException if an error occurs while reading the file
	 */
	public void setAllValuesToZero_File(String tsInputPath, String tsOutputPath) throws IOException;
	
	
	/**
	 * Sets all times and values in the series to zero.
	 *
	 * @param s the time series to modify
	 */
	public void setAllToZero(TimeSeries s);
	
	
	/**
	 * Reads a TimeSeries from a file and  sets all time and value fields to zero.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param tsOutputPath the path to the output file to save the modified TimeSeries.
	 * @throws IOException if an error occurs while reading the file
	 */
	public void setAllToZero_File(String tsInputPath, String tsOutputPath) throws IOException;
	
	
	/**
	 * Sets all times in the series to a specified value.
	 *
	 * @param s the time series to modify
	 * @param time the time to set
	 */
	public void setAllTimesToTime(TimeSeries s, double time);
	
	
	/**
	 * Reads a TimeSeries from a file and sets all time fields.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param tsOutputPath the path to the output file to save the modified TimeSeries.
	 * @param time         the time value to set
	 * @throws IOException if an error occurs while reading the file
	 */
	public void setAllTimesToTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException;

	
	/**
	 * Sets all values in the series to a specified value.
	 *
	 * @param s the time series to modify
	 * @param value the value to set
	 */
	public void setAllValuesToValue(TimeSeries s, double value);
	
	
	/**
	 * Reads a TimeSeries from a file and sets all values to the specified value.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param tsOutputPath the path to the output file to save the modified TimeSeries.
	 * @param value        the value to set
	 * @throws IOException if an error occurs while reading or writing the file
	 */
	public void setAllValuesToValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException;

	
	/**
	 * Sets all times and values in the series to a specified time and value.
	 *
	 * @param s the time series to modify
	 * @param time the time to set
	 * @param value the value to set
	 */
	public void setAllToTimeValue(TimeSeries s, double time, double value);

	
	/**
	 * Reads a TimeSeries from a file and sets all time and value fields to the specified times and values.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param tsOutputPath the path to the output file to save the modified TimeSeries.
	 * @param time         the time value to set
	 * @param value        the value to set
	 * @throws IOException if an error occurs while reading the file
	 */
	public void setAllToTimeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException;

	
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
	 * Reads a TimeSeries from a file and extracts a sub-TimeSeries within the specified time range.
	 * 
	 * @param tsInputPath  the path to the input file
	 * @param timeMin      the minimum time value (inclusive)
	 * @param timeMax      the maximum time value (inclusive)
	 * @return a sub-time series within the specified range
	 * @throws IOException if an error occurs while reading the file
	 */
	public TimeSeries getSubTimeSeriesInTimeRange_File(String tsInputPath, double timeMin, double timeMax) throws IOException;

	
	/**
	 * Counts the total number of time-value pairs in the time series.
	 *
	 * @param s the time series to query
	 * @return the count of time-value pairs
	 */
	public int countTimeValues(TimeSeries s);

	
	/**
	 * Reads a TimeSeries from a file and counts the number of TimeValue entries.
	 * 
	 * @param tsInputPath the path to the file containing the TimeSeries
	 * @return the number of TimeValue entries
	 * @throws IOException if an error occurs while reading the file
	 */
	public int countTimeValues_File(String tsInputPath) throws IOException;
	
	
	/**
	 * Checks if the time series is empty.
	 *
	 * @param s the time series to query
	 * @return {@code true} if the time series is empty, {@code false} otherwise
	 */
	public boolean isEmpty(TimeSeries s);

	
	/**
	 * Reads a TimeSeries from a file and checks if it is empty.
	 * 
	 * @param tsInputPath the path to the file containing the TimeSeries
	 * @return {@code true} if the time series is empty, {@code false} otherwise
	 * @throws IOException if an error occurs while reading the file
	 */
	public boolean isEmpty_File(String tsInputPath) throws IOException;

	
	/**
	 * Prints all time-value pairs in the time series to the console.
	 *
	 * @param s the time series to print
	 */
	public void printTimeSeries(TimeSeries s);

	
	/**
	 * Reads a TimeSeries from a file and prints it in a formatted way.
	 *
	 * @param tsInputPath the path to the input file containing the TimeSeries.
	 * @throws IOException if an I/O error occurs while reading the file.
	 */
	public void printTimeSeries_File(String tsInputPath) throws IOException;

	
	/**
	 * Writes an input time series (format is a list of T V doubles) to a file.
	 *
	 * @param s the time series to write
	 * @param filePath the path of the file to write to
	 * @throws IOException if an I/O error occurs
	 */
	public void writeInTSToFile(TimeSeries s, String filePath) throws IOException;

	
	/**
	 * Reads an input time series (format is a list of T V doubles) from a file.
	 *
	 * @param filePath the path of the file to read from
	 * @return the time series read from the file
	 * @throws IOException if an I/O error occurs
	 */
	public TimeSeriesValue readInTSFromFile(String filePath) throws IOException;

	
	/**
	 * Reads an input time series (format is a "TIME","outputPortName" header followed by a list of T,V doubles) from a file.
	 *
	 * @param filePath the path of the file to read from
	 * @return the time series read from the file
	 * @throws IOException if an I/O error occurs
	 */
	public TimeSeriesValue readOutTSFromFile(String filePath) throws IOException;
	

}
