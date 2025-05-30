package emeliot.dsl.lib;

import java.io.IOException;
import java.util.List;

import emeliot.dsl.read.TimeSeries;

public interface EmeliotDiscoveryService {
	


	/**
	 * Checks if the mutated time series contains more time-values than the original, indicating a commission error.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the number of time-values in the mutated time series is greater than in the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * */
	public DiscoveryOutcome isCommission(TimeSeries tsOriginal, TimeSeries tsMutated);
	
	/**
	 * Checks if the mutated time series contains more time-values than the original, both read from files, indicating a commission error.
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the number of time-values in the mutated time series is greater than in the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isCommission_File(String tsOriginalPath, String tsMutatedPath) throws IOException;

	/**
	 * Checks if the mutated time series contains fewer time-values than the original, indicating an omission error.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the number of time-values in the mutated time series is less than in the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isOmission(TimeSeries tsOriginal, TimeSeries tsMutated);
	
	/**
	 * Checks if the mutated time series contains fewer time-values than the original, both read from files, indicating an omission error.
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the number of time-values in the mutated time series is less than in the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isOmission_File(String tsOriginalPath, String tsMutatedPath) throws IOException;

	/**
	 * Checks if the mutated time series has times that are late compared to the original time series, beyond a specified threshold.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for time differences.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the mutated time series contains times that are late compared to the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps);
	
	/**
	 * Checks if the mutated time series has times that are late compared to the original time series, both read from files, beyond a specified threshold.
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @param eps the allowed threshold for time differences.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the mutated time series contains times that are late compared to the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isLate_File(String tsOriginalPath, String tsMutatedPath, double eps) throws IOException;

	/**
	 * Checks if the mutated time series has times that are early compared to the original time series, beyond a specified threshold.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for time differences.
	 * @return {@code true} if the mutated time series contains times that are early compared to the original; {@code false} otherwise.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the mutated time series contains times that are early compared to the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps);
	
	/**
	 * Checks if the mutated time series has times that are early compared to the original time series, both read from files, beyond a specified threshold.
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @param eps the allowed threshold for time differences.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the mutated time series contains times that are early compared to the original; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isEarly_File(String tsOriginalPath, String tsMutatedPath, double eps) throws IOException;

	/**
	 * Checks if the mutated time series has coarse value deviations compared to the original time series, outside a specified range.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for value differences.
	 * @param valueMin the minimum allowed value in the range.
	 * @param valueMax the maximum allowed value in the range.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if coarse value deviations are detected; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax);
	
	/**
	 * Checks if the mutated time series has coarse value deviations compared to the original time series, both read from files, outside a specified range.
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @param eps the allowed threshold for value differences.
	 * @param valueMin the minimum allowed value in the range.
	 * @param valueMax the maximum allowed value in the range.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if coarse value deviations are detected; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isValueCoarse_File(String tsOriginalPath, String tsMutatedPath, double eps, double valueMin, double valueMax) throws IOException;

	/**
	 * Checks if the mutated time series has subtle value deviations compared to the original time series, within a specified range.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @param eps the allowed threshold for value differences.
	 * @param valueMin the minimum allowed value in the range.
	 * @param valueMax the maximum allowed value in the range.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if subtle value deviations are detected; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double valueMin, double valueMax);
	
	/**
	 * Checks if the mutated time series has subtle value deviations compared to the original time series, both read from files, within a specified range.
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @param eps the allowed threshold for value differences.
	 * @param valueMin the minimum allowed value in the range.
	 * @param valueMax the maximum allowed value in the range.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if subtle value deviations are detected; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isValueSubtle_File(String tsOriginalPath, String tsMutatedPath, double eps, double valueMin, double valueMax) throws IOException;
	
	/**
	 * Checks if the original and mutated time series have the same number of time values.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the size of time values is the same for both time series; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome areSameSize(TimeSeries tsOriginal, TimeSeries tsMutated);
	
	/**
	 * Checks if the original and mutated time series have the same number of time values, both read from files
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the size of time values is the same for both time series; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome areSameSize_File(String tsOriginalPath, String tsMutatedPath) throws IOException;
	
	/**
	 * Checks if the original time series has fewer time values than the mutated time series.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the size of time values in the original time series is smaller; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isSmaller(TimeSeries tsOriginal, TimeSeries tsMutated);
	
	/**
	 * Checks if the original time series has fewer time values than the mutated time series, both read from files
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the size of time values in the original time series is smaller; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isSmaller_File(String tsOriginalPath, String tsMutatedPath) throws IOException;
	
	/**
	 * Checks if the original time series has more time values than the mutated time series.
	 * @param tsOriginal the original time series.
	 * @param tsMutated the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the size of time values in the original time series is larger; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome isBigger(TimeSeries tsOriginal, TimeSeries tsMutated);
	
	/**
	 * Checks if the original time series has more time values than the mutated time series, both read from files
	 * @param tsOriginalPath the path to the original time series.
	 * @param tsMutatedPath the path to the mutated time series.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if the size of time values in the original time series is larger; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome isBigger_File(String tsOriginalPath, String tsMutatedPath) throws IOException;
	
	/**
	 * Checks if the mutated time series contains any time outside the specified range.
	 * @param tsMutated the mutated time series.
	 * @param timeMin the minimum allowed time value.
	 * @param timeMax the maximum allowed time value.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if any time in the mutated time series is outside the range; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome hasTimeOutRange(TimeSeries tsMutated, double timeMin, double timeMax);
	
	/**
	 * Checks if the mutated time series contains any time outside the specified range, both read from files
	 * @param tsMutatedPath the path to the mutated time series.
	 * @param timeMin the minimum allowed time value.
	 * @param timeMax the maximum allowed time value.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if any time in the mutated time series is outside the range; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome hasTimeOutRange_File(String tsMutatedPath, double timeMin, double timeMax) throws IOException;
	
	/**
	 * Checks if the mutated time series contains any value outside the specified range.
	 * @param tsMutated the mutated time series.
	 * @param valueMin the minimum allowed value.
	 * @param valueMax the maximum allowed value.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if any value in the mutated time series is outside the range; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 */
	public DiscoveryOutcome hasValueOutRange(TimeSeries tsMutated, double valueMin, double valueMax);
	
	/**
	 * Checks if the mutated time series contains any value outside the specified range, both read from files
	 * @param tsMutatedPath the path to the mutated time series.
	 * @param valueMin the minimum allowed value.
	 * @param valueMax the maximum allowed value.
	 * @return a {@code discovery outcome} object having {@code hasError} field set to {@code true} and {@code errorMsg} field set to a string describing the error found, if any value in the mutated time series is outside the range; a {@code discovery outcome} object having fields set to false and to empty string otherwise.
	 * @throws IOException if an I/O error occurs during file operations.
	 */
	public DiscoveryOutcome hasValueOutRange_File(String tsMutatedPath, double valueMin, double valueMax) throws IOException;
	/**
	 * Serialize to a json file the Discovery outcome
	 * 
	 * @param outocome the discovery analysis result 
	 * @param filename the output absolute path  
	 */
	public void serializeDiscovery(DiscoveryOutcome outcome, String filename);
	/**
	 * Serialize to a json file the Discovery outcome
	 * 
	 * @param outocome the discovery analysis result 
	 * @param filename the output absolute path  
	 */
	public void serializeDiscovery(List<DiscoveryOutcome> outcomes, String filename);
	

}
