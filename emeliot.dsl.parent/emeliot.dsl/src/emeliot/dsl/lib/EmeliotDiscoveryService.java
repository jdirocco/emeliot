package emeliot.dsl.lib;

import emeliot.dsl.read.TimeSeriesValue;

public interface EmeliotDiscoveryService {
	


	/**
	 * Checks if the mutated time series contains more values than the original, indicating a commission error.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return {@code true} if the number of values in the mutated time series is greater than in the original; {@code false} otherwise.
	 */
	public boolean isCommission(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated);

	/**
	 * Checks if the mutated time series contains fewer values than the original, indicating an omission error.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return {@code true} if the number of values in the mutated time series is less than in the original; {@code false} otherwise.
	 */
	public boolean isOmission(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated);

	/**
	 * Determines if the mutated time series has times that are late compared to the original time series, beyond a specified threshold.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for time differences.
	 * @return {@code true} if the mutated time series contains times that are late compared to the original; {@code false} otherwise.
	 */
	public boolean isLate(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps);

	/**
	 * Determines if the mutated time series has times that are early compared to the original time series, beyond a specified threshold.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for time differences.
	 * @return {@code true} if the mutated time series contains times that are early compared to the original; {@code false} otherwise.
	 */
	public boolean isEarly(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps);

	/**
	 * Checks if the mutated time series has coarse value deviations compared to the original time series, outside a specified range.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for value differences.
	 * @param valueMin the minimum allowed value in the range.
	 * @param valueMax the maximum allowed value in the range.
	 * @return {@code true} if coarse value deviations are detected; {@code false} otherwise.
	 */
	public boolean isValueCoarse(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps, double valueMin, double valueMax);

	/**
	 * Checks if the mutated time series has subtle value deviations compared to the original time series, within a specified range.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for value differences.
	 * @param valueMin the minimum allowed value in the range.
	 * @param valueMax the maximum allowed value in the range.
	 * @return {@code true} if subtle value deviations are detected; {@code false} otherwise.
	 */
	public boolean isValueSubtle(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated, double eps, double valueMin, double valueMax);
	
	/**
	 * Checks if the original and mutated time series have the same number of time values.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return {@code true} if the size of time values is the same for both time series; {@code false} otherwise.
	 */
	public boolean areSameSize(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated);
	
	/**
	 * Checks if the original time series has fewer time values than the mutated time series.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return {@code true} if the size of time values in the original time series is smaller; {@code false} otherwise.
	 */
	public boolean isSmaller(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated);
	
	/**
	 * Checks if the original time series has more time values than the mutated time series.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return {@code true} if the size of time values in the original time series is larger; {@code false} otherwise.
	 */
	public boolean isBigger(TimeSeriesValue tsOriginal, TimeSeriesValue tsMutated);
	
	/**
	 * Checks if the mutated time series contains any time outside the specified range.
	 * @param tsMutated the mutated time series.
	 * @param timeMin the minimum allowed time value.
	 * @param timeMax the maximum allowed time value.
	 * @return {@code true} if any time in the mutated time series is outside the range; {@code false} otherwise.
	 */
	public boolean hasTimeOutRange(TimeSeriesValue tsMutated, double timeMin, double timeMax);
	
	/**
	 * Checks if the mutated time series contains any value outside the specified range.
	 * @param tsMutated the mutated time series.
	 * @param valueMin the minimum allowed value.
	 * @param valueMax the maximum allowed value.
	 * @return {@code true} if any value in the mutated time series is outside the range; {@code false} otherwise.
	 */
	public boolean hasValueOutRange(TimeSeriesValue tsMutated, double valueMin, double valueMax);
	
	
	
	

}
