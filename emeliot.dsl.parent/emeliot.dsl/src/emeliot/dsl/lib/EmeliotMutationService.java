package emeliot.dsl.lib;

import java.io.IOException;
import java.util.List;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

public interface EmeliotMutationService {

	
	
	/*********** TODO: ADD OPERATORS ***********/

	
	/**
	 * Adds a specific time and value to the provided time series.
	 *
	 * @param s the time series to which the time and value will be added.
	 * @param time the time associated with the value to be added.
	 * @param value the value to be added.
	 */
	public void addTimeAndValue(TimeSeries s, double time, double value);
	
	
	/**
	 * Reads a time series from a file, adds a specific time and value, and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the input time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @param time the time associated with the value to be added.
	 * @param value the value to be added.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void addTimeAndValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException;
	
	
	/**
	 * Adds a random time (within specified bounds) and a specific value to the provided time series.
	 *
	 * @param s the time series to which the value and random time will be added.
	 * @param timeMin the minimum bound for the random time.
	 * @param timeMax the maximum bound for the random time.
	 * @param value the value to be added.
	 */
	public void addRandomTimeAndValue(TimeSeries s, double timeMin, double timeMax, double value);
	
	
	/**
	 * Reads a time series from a file, adds a random time (within specified bounds) and a specific value, 
	 * and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the input time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @param timeMin the minimum bound for the random time.
	 * @param timeMax the maximum bound for the random time.
	 * @param value the value to be added.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void addRandomTimeAndValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double value) throws IOException;
	
	
	/**
	 * Adds a specific time and a random value (within specified bounds) to the provided time series.
	 *
	 * @param s the time series to which the random value and time will be added.
	 * @param time the time associated with the random value.
	 * @param valueMin the minimum bound for the random value.
	 * @param valueMax the maximum bound for the random value.
	 */
	public void addTimeAndRandomValue(TimeSeries s, double time, double valueMin, double valueMax);
	
	
	/**
	 * Reads a time series from a file, adds a specific time and a random value (within specified bounds),
	 * and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the input time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @param time the time associated with the random value.
	 * @param valueMin the minimum bound for the random value.
	 * @param valueMax the maximum bound for the random value.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void addTimeAndRandomValue_File(String tsInputPath, String tsOutputPath, double time, double valueMin, double valueMax) throws IOException;
	
	
	/**
	 * Adds a random time and a random value (both within specified bounds) to the provided time series.
	 *
	 * @param s the time series to which the random time and value will be added.
	 * @param timeMin the minimum bound for the random time.
	 * @param timeMax the maximum bound for the random time.
	 * @param valueMin the minimum bound for the random value.
	 * @param valueMax the maximum bound for the random value.
	 */
	public void addRandomTimeAndRandomValue(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax);
	
	
	/**
	 * Reads a time series from a file, adds a random time and a random value (both within specified bounds), 
	 * and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the input time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @param timeMin the minimum bound for the random time.
	 * @param timeMax the maximum bound for the random time.
	 * @param valueMin the minimum bound for the random value.
	 * @param valueMax the maximum bound for the random value.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void addRandomTimeAndRandomValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException;
	
	
	/**
	 * Adds multiple time and value pairs to the provided time series.
	 *
	 * @param s the time series to which the values and times will be added.
	 * @param times a list of times to be added.
	 * @param values a list of values to be added.
	 */
	public void addMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values);

	
	/**
	 * Reads a time series from a file, adds multiple time and value pairs, and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the input time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @param times a list of times to be added.
	 * @param values a list of values to be added.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void addMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> times, List<Double> values) throws IOException;

	
	/**
	 * Adds multiple random time and value pairs (within specified bounds) to the provided time series.
	 *
	 * @param s the time series to which the random values and times will be added.
	 * @param timeMin the minimum bound for the random times.
	 * @param timeMax the maximum bound for the random times.
	 * @param valueMin the minimum bound for the random values.
	 * @param valueMax the maximum bound for the random values.
	 * @param count the number of random time-value pairs to add.
	 */
	public void addMultipleRandomTimeValues(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax, int count);

	
	/**
	 * Reads a time series from a file, adds multiple random time and value pairs (within specified bounds),
	 * and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the input time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @param timeMin the minimum bound for the random times.
	 * @param timeMax the maximum bound for the random times.
	 * @param valueMin the minimum bound for the random values.
	 * @param valueMax the maximum bound for the random values.
	 * @param count the number of random time-value pairs to add.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void addMultipleRandomTimeValues_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax, int count) throws IOException;

	
	/**
	 * Appends all the time-value pairs from one time series to another.
	 *
	 * @param s the target time series to which values will be appended.
	 * @param s1 the source time series to be appended.
	 */
	public void appendTimeSeries(TimeSeries s, TimeSeries s1);

	
	/**
	 * Reads two time series from files, appends one to the other, and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the target time series file.
	 * @param tsAppendPath the path to the source time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void appendTimeSeries_File(String tsInputPath, String tsAppendPath, String tsOutputPath) throws IOException;

	
	/**
	 * Merges two time series by combining their time-value pairs.
	 *
	 * @param s the target time series.
	 * @param s1 the source time series to be merged.
	 */
	public void mergeTimeSeries(TimeSeries s, TimeSeries s1);

	
	/**
	 * Reads two time series from files, merges them, and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the target time series file.
	 * @param tsMergePath the path to the source time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void mergeTimeSeries_File(String tsInputPath, String tsMergePath, String tsOutputPath) throws IOException;

	
	/**
	 * Replaces the contents of one time series with the contents of another.
	 *
	 * @param s the target time series to be replaced.
	 * @param s1 the source time series used for replacement.
	 */
	public void replaceTimeSeries(TimeSeries s, TimeSeries s1);

	
	/**
	 * Reads two time series from files, replaces one with the other, and writes the updated series to a new file.
	 *
	 * @param tsInputPath the path to the target time series file.
	 * @param tsReplacePath the path to the source time series file.
	 * @param tsOutputPath the path to the output time series file.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public void replaceTimeSeries_File(String tsInputPath, String tsReplacePath, String tsOutputPath) throws IOException;
	
	
	
	

	/*********** TODO: EDIT OPERATORS ***********/
	

	/**
	 * Changes the value of a time series at a specific time.
	 *
	 * @param s The time series to modify.
	 * @param time The time associated with the value to modify.
	 * @param value The new value to set.
	 */
	public void changeValue(TimeSeries s, double time, double value);

	
	/**
	 * Changes the value of a time series at a specific time by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param time The time associated with the value to modify.
	 * @param value The new value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException;

	
	/**
	 * Changes the time in a time series from an old time to a new time.
	 *
	 * @param s The time series to modify.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 */
	public void changeTime(TimeSeries s, double timeOld, double timeNew);

	
	/**
	 * Changes the time in a time series from an old time to a new time by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTime_File(String tsInputPath, String tsOutputPath, double timeOld, double timeNew) throws IOException;

	
	/**
	 * Changes the value of a time series at a specific time with a random value within a given range.
	 *
	 * @param s The time series to modify.
	 * @param time The time associated with the value to modify.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 */
	public void changeValueWithRandom(TimeSeries s, double time, double valueMin, double valueMax);

	
	/**
	 * Changes the value of a time series at a specific time with a random value within a given range by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param time The time associated with the value to modify.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeValueWithRandom_File(String tsInputPath, String tsOutputPath, double time, double valueMin, double valueMax) throws IOException;

	
	/**
	 * Changes the time in a time series from an old time to a new time within a random range.
	 *
	 * @param s The time series to modify.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param time The current time to be changed.
	 */
	public void changeTimeWithRandom(TimeSeries s, double timeMin, double timeMax, double time);

	
	/**
	 * Changes the time in a time series from an old time to a new time within a random range by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param time The current time to be changed.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeWithRandom_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double time) throws IOException;

	
	/**
	 * Changes both the time and value of a time series.
	 *
	 * @param s The time series to modify.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @param valueNew The new value to set.
	 */
	public void changeTimeAndValue(TimeSeries s, double timeOld, double timeNew, double valueNew);

	
	/**
	 * Changes both the time and value of a time series by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @param valueNew The new value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeAndValue_File(String tsInputPath, String tsOutputPath, double timeOld, double timeNew, double valueNew) throws IOException;

	
	/**
	 * Changes the time and value of a time series with a random time and a specified new value.
	 *
	 * @param s The time series to modify.
	 * @param timeOld The old time to replace.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param valueNew The new value to set.
	 */
	public void changeTimeWithRandomAndValue(TimeSeries s, double timeOld, double timeMin, double timeMax, double valueNew);

	
	/**
	 * Changes the time and value of a time series with a random time and a specified new value by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeOld The old time to replace.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param valueNew The new value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeWithRandomAndValue_File(String tsInputPath, String tsOutputPath, double timeOld, double timeMin, double timeMax, double valueNew) throws IOException;

	
	/**
	 * Changes the time and value of a time series with a random value and a specified new time by reading from and writing to files.	 *
	 * @param s The time series to modify.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 */
	public void changeTimeAndValueWithRandom(TimeSeries s, double timeOld, double timeNew, double valueMin, double valueMax);

	
	/**
	 * Changes both the time and value of a time series with a random value and a specified new time by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeAndValueWithRandom_File(String tsInputPath, String tsOutputPath, double timeOld, double timeNew, double valueMin, double valueMax) throws IOException;

	
	/**
	 * Changes both the time and value of a time series with random values for both time and value.
	 *
	 * @param s The time series to modify.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 */
	public void changeTimeWithRandomAndValueWithRandom(TimeSeries s, double timeOld, double timeMin, double timeMax, double valueMin, double valueMax);
	
	
	/**
	 * Changes both the time and value of a time series with random values for both time and value by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeOld The old time to replace.
	 * @param timeNew The new time to set.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeWithRandomAndValueWithRandom_File(String tsInputPath, String tsOutputPath, double timeOld, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException;

	
	/**
	 * Changes a random time and its associated value in a time series.
	 *
	 * @param s The time series to modify.
	 * @param timeNew The new time to set.
	 * @param valueNew The new value to set.
	 */
	public void changeARandomTimeValue(TimeSeries s, double timeNew, double valueNew);

	
	/**
	 * Changes a random time and its associated value in a time series by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeNew The new time to set.
	 * @param valueNew The new value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeARandomTimeValue_File(String tsInputPath, String tsOutputPath, double timeNew, double valueNew) throws IOException;

	
	/**
	 * Changes a random time and its associated value in a time series with random values for both time and value.
	 *
	 * @param s The time series to modify.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 */
	public void changeARandomTimeValueWithRandomTimeValue(TimeSeries s, double timeMin, double timeMax, double valueMin, double valueMax);

	
	/**
	 * Changes a random time and its associated value in a time series with random values for both time and value by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeARandomTimeValue_File(String tsInputPath, String tsOutputPath, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException;

	
	/**
	 * Changes multiple time and value pairs in a time series.
	 *
	 * @param s The time series to modify.
	 * @param timesOld The list of old times to replace.
	 * @param timesNew The list of new times to set.
	 * @param valuesNew The list of new values to set.
	 */
	public void changeMultipleTimeValues(TimeSeries s, List<Double> timesOld, List<Double> timesNew, List<Double> valuesNew);

	
	/**
	 * Changes multiple time and value pairs in a time series by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timesOld The list of old times to replace.
	 * @param timesNew The list of new times to set.
	 * @param valuesNew The list of new values to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> timesOld, List<Double> timesNew, List<Double> valuesNew) throws IOException;

	
	/**
	 * Changes multiple time and value pairs in a time series with random values for both time and value.
	 *
	 * @param s The time series to modify.
	 * @param timesOld The list of old times to replace.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 */
	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeries s, List<Double> timesOld, double timeMin, double timeMax, double valueMin, double valueMax);

	
	/**
	 * Changes multiple time and value pairs in a time series with random values for both time and value by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timesOld The list of old times to replace.
	 * @param timeMin The minimum time for the new time.
	 * @param timeMax The maximum time for the new time.
	 * @param valueMin The minimum value to set.
	 * @param valueMax The maximum value to set.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeMultipleTimeValuesWithRandomTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> timesOld, double timeMin, double timeMax, double valueMin, double valueMax) throws IOException;

	
	/**
	 * Changes the time of a time series by adding a specified time with variation bigger than epsilon within the domain constraints.
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time to change.
	 * @param timeMaxDomain The maximum time allowed in the domain.
	 */
	public void changeTimeLate(TimeSeries s, double eps, double time, double timeMaxDomain);

	
	/**
	 * Changes the time of a time series by adding a specified time with variation bigger than epsilon within the domain constraints by reading and writing from/to files
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time to change.
	 * @param timeMaxDomain The maximum time allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeLate_File(String tsInputPath, String tsOutputPath, double eps, double time, double timeMaxDomain) throws IOException;

	
	/**
	 * Changes a random time in a time series by adding a specified time with variation bigger than epsilon within the domain constraints.
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param timeMaxDomain The maximum time allowed in the domain.
	 */
	public void changeRandomTimeLate(TimeSeries s, double eps, double timeMaxDomain);

	
	/**
	 * Changes a random time in a time series by adding a specified time with variation bigger than epsilon within the domain constraints by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param timeMaxDomain The maximum time allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeRandomTimeLate_File(String tsInputPath, String tsOutputPath, double eps, double timeMaxDomain) throws IOException;

	
	/**
	 * Changes multiple times in a time series by adding a specified time with variation bigger than epsilon within the domain constraints.
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param timeMaxDomain The maximum time allowed in the domain.
	 */
	public void changeMultipleTimeLate(TimeSeries s, double eps, List<Double> times, double timeMaxDomain);

	
	/**
	 * Changes multiple times in a time series by adding a specified time with variation bigger than epsilon within the domain constraints by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param timeMaxDomain The maximum time allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeMultipleTimeLate_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double timeMaxDomain) throws IOException;

	
	/**
	 * Changes the time of a time series by removing a specified time with variation bigger than epsilon within the domain constraints
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time to change.
	 * @param timeMinDomain The minimum time allowed in the domain.
	 */
	public void changeTimeEarly(TimeSeries s, double eps, double time, double timeMinDomain);

	
	/**
	 * Changes the time of a time series by removing a specified time with variation bigger than epsilon within the domain constraints by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time to change.
	 * @param timeMinDomain The minimum time allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeTimeEarly_File(String tsInputPath, String tsOutputPath, double eps, double time, double timeMinDomain) throws IOException;

	
	/**
	 * Changes a random time in a time series by removing a specified time with variation bigger than epsilon within the domain constraints
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param timeMinDomain The minimum time allowed in the domain.
	 */
	public void changeRandomTimeEarly(TimeSeries s, double eps, double timeMinDomain);

	
	/**
	 * Changes a random time in a time series by removing a specified time with variation bigger than epsilon within the domain constraints (early adjustment) by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param timeMinDomain The minimum time allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeRandomTimeEarly_File(String tsInputPath, String tsOutputPath, double eps, double timeMinDomain) throws IOException;

	
	/**
	 * Changes multiple times in a time series by removing a specified time with variation bigger than epsilon within the domain constraints (early adjustment).
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param timeMinDomain The minimum time allowed in the domain.
	 */
	public void changeMultipleTimeEarly(TimeSeries s, double eps, List<Double> times, double timeMinDomain);

	
	/**
	 * Changes multiple times in a time series by removing a specified time with variation bigger than epsilon within the domain constraints (early adjustment) by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param timeMinDomain The minimum time allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeMultipleTimeEarly_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double timeMinDomain) throws IOException;

	
	/**
	 * Changes the value of a time series with a value below min domain value or above max domain value and variation bigger than epsilon
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time at which to change the value.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 */
	public void changeValueCoarse(TimeSeries s, double eps, double time, double valueMinDomain, double valueMaxDomain);

	/**
	 * Changes the value of a time series with a value below min domain value or above max domain value and variation bigger than epsilon from reading from and writing to files
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time at which to change the value.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeValueCoarse_File(String tsInputPath, String tsOutputPath, double eps, double time, double valueMinDomain, double valueMaxDomain) throws IOException;

	
	/**
	 * Changes a random value of a time series with a value below min domain value or above max domain value and variation bigger than epsilon
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 */
	public void changeRandomValueCoarse(TimeSeries s, double eps, double valueMinDomain, double valueMaxDomain);

	
	/**
	 * Changes a random value of a time series with a value below min domain value or above max domain value and variation bigger than epsilon from reading from and writing to files
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeRandomValueCoarse_File(String tsInputPath, String tsOutputPath, double eps, double valueMinDomain, double valueMaxDomain) throws IOException;

	
	/**
	 * Changes multiple values of a time series with values below min domain value or above max domain value and variation bigger than epsilon
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 */
	public void changeMultipleValueCoarse(TimeSeries s, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain);

	
	/**
	 * Changes multiple values of a time series with values below min domain value or above max domain value and variation bigger than epsilon from reading from and writing to files
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeMultipleValueCoarse_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) throws IOException;

	
	/**
	 * Changes the value of a time series with a value between min and max domain values and variation bigger than epsilon
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time at which to change the value.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 */
	public void changeValueSubtle(TimeSeries s, double eps, double time, double valueMinDomain, double valueMaxDomain);

	
	/**
	 * Changes the value of a time series with a value between min and max domain values and variation bigger than epsilon by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param time The time at which to change the value.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeValueSubtle_File(String tsInputPath, String tsOutputPath, double eps, double time, double valueMinDomain, double valueMaxDomain) throws IOException;

	
	/**
	 * Changes a random value of a time series with a value between min and max domain values and variation bigger than epsilon
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 */
	public void changeRandomValueSubtle(TimeSeries s, double eps, double valueMinDomain, double valueMaxDomain);

	
	/**
	 * Changes a random value of a time series with a value between min and max domain values and variation bigger than epsilon by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeRandomValueSubtle_File(String tsInputPath, String tsOutputPath, double eps, double valueMinDomain, double valueMaxDomain) throws IOException;

	
	/**
	 * Changes multiple values of a time series with values between min and max domain values and variation bigger than epsilon
	 *
	 * @param s The time series to modify.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 */
	public void changeMultipleValueSubtle(TimeSeries s, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain);

	
	/**
	 * Changes multiple values of a time series with values between min and max domain values and variation bigger than epsilon by reading from and writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param eps The epsilon value defining the minimum variation time change.
	 * @param times The list of times to change.
	 * @param valueMinDomain The minimum value allowed in the domain.
	 * @param valueMaxDomain The maximum value allowed in the domain.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void changeMultipleValueSubtle_File(String tsInputPath, String tsOutputPath, double eps, List<Double> times, double valueMinDomain, double valueMaxDomain) throws IOException;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********** TODO: REMOVE OPERATORS **********/
	
	
	/**
	 * Removes a specific time-value pair from the time series.
	 *
	 * @param s The time series to modify.
	 * @param time The time of the value to remove.
	 * @param value The value to remove at the specified time.
	 */
	public void removeTimeValue(TimeSeries s, double time, double value);

	
	/**
	 * Removes a specific time-value pair from a time series by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param time The time of the value to remove.
	 * @param value The value to remove at the specified time.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeTimeValue_File(String tsInputPath, String tsOutputPath, double time, double value) throws IOException;

	
	/**
	 * Removes a specific time-value pair from the time series using a TimeValue object.
	 *
	 * @param s The time series to modify.
	 * @param tvToRemove The TimeValue object representing the time-value pair to remove.
	 */
	public void removeTimeValue(TimeSeries s, TimeValue tvToRemove);

	
	/**
	 * Removes a specific time-value pair using a TimeValue object by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param tvToRemove The TimeValue object representing the time-value pair to remove.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeTimeValue_File(String tsInputPath, String tsOutputPath, TimeValue tvToRemove) throws IOException;

	
	/**
	 * Removes a random time-value pair from the time series.
	 *
	 * @param s The time series to modify.
	 */
	public void removeRandomTimeValue(TimeSeries s);

	
	/**
	 * Removes a random time-value pair from a time series by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeRandomTimeValue_File(String tsInputPath, String tsOutputPath) throws IOException;

	
	/**
	 * Removes multiple time-value pairs from the time series using time and value lists.
	 *
	 * @param s The time series to modify.
	 * @param times The list of times to remove.
	 * @param values The list of values to remove corresponding to the times.
	 */
	public void removeMultipleTimeValues(TimeSeries s, List<Double> times, List<Double> values);

	
	/**
	 * Removes multiple time-value pairs from a time series using time and value lists by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param times The list of times to remove.
	 * @param values The list of values to remove corresponding to the times.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<Double> times, List<Double> values) throws IOException;

	
	/**
	 * Removes multiple time-value pairs from the time series using a list of TimeValue objects.
	 *
	 * @param s The time series to modify.
	 * @param timeValues The list of TimeValue objects to remove.
	 */
	public void removeMultipleTimeValues(TimeSeries s, List<TimeValue> timeValues);

	
	/**
	 * Removes multiple time-value pairs from a time series using a list of TimeValue objects by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeValues The list of TimeValue objects to remove.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, List<TimeValue> timeValues) throws IOException;

	
	/**
	 * Removes multiple time-value pairs from the time series using a list of undefined size of TimeValue objects.
	 *
	 * @param s The time series to modify.
	 * @param timeValues The TimeValue objects to remove.
	 */
	public void removeMultipleTimeValues(TimeSeries s, TimeValue... timeValues);

	
	/**
	 * Removes multiple time-value pairs from a time series using a list of undefined size of TimeValue objects by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param timeValues The TimeValue objects to remove.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeMultipleTimeValues_File(String tsInputPath, String tsOutputPath, TimeValue... timeValues) throws IOException;

	
	/**
	 * Removes all time-value pairs from the time series.
	 *
	 * @param s The time series to clear.
	 */
	public void removeAllTimeValues(TimeSeries s);

	
	/**
	 * Removes all time-value pairs from a time seriesby reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeAllTimeValues_File(String tsInputPath, String tsOutputPath) throws IOException;

	
	/**
	 * Removes all time-value pairs before a specified time from the time series.
	 *
	 * @param s The time series to modify.
	 * @param time The cutoff time; all pairs before this time will be removed.
	 */
	public void removeTimeValuesBeforeTime(TimeSeries s, double time);

	
	/**
	 * Removes all time-value pairs before a specified time from a time series by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param time The cutoff time; all pairs before this time will be removed.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeTimeValuesBeforeTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException;

	
	/**
	 * Removes all time-value pairs after a specified time from the time series.
	 *
	 * @param s The time series to modify.
	 * @param time The cutoff time; all pairs after this time will be removed.
	 */
	public void removeTimeValuesAfterTime(TimeSeries s, double time);

	
	/**
	 * Removes all time-value pairs after a specified time from a time series by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param time The cutoff time; all pairs after this time will be removed.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeTimeValuesAfterTime_File(String tsInputPath, String tsOutputPath, double time) throws IOException;

	
	/**
	 * Removes all time-value pairs with a value below the specified threshold from the time series.
	 *
	 * @param s The time series to modify.
	 * @param value The threshold value; all pairs below this value will be removed.
	 */
	public void removeTimeValuesBelowValue(TimeSeries s, double value);

	
	/**
	 * Removes all time-value pairs with a value below the specified threshold from a time series by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param value The threshold value; all pairs below this value will be removed.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeTimeValuesBelowValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException;

	
	/**
	 * Removes all time-value pairs with a value above the specified threshold from the time series.
	 *
	 * @param s The time series to modify.
	 * @param value The threshold value; all pairs above this value will be removed.
	 */
	public void removeTimeValuesAboveValue(TimeSeries s, double value);

	
	/**
	 * Removes all time-value pairs with a value above the specified threshold from a time series by reading from/writing to files.
	 *
	 * @param tsInputPath The path to the input time series file.
	 * @param tsOutputPath The path to the output time series file.
	 * @param value The threshold value; all pairs above this value will be removed.
	 * @throws IOException If an I/O error occurs during file processing.
	 */
	public void removeTimeValuesAboveValue_File(String tsInputPath, String tsOutputPath, double value) throws IOException;


	
	
	
	
}
