package emeliot.dsl.lib;

import java.util.List;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

public interface EmeliotMutationService {

	public void addTimeAndValue(TimeSeries s, Double value, int time);

	public void addRandomTimeAndValue(TimeSeries s, Double value, int minTime, int maxTime);

	public void addTimeAndRandomValue(TimeSeries s, int time, double minValue, double maxValue, ReadFactory factory);

	public void addRandomTimeAndRandomValue(TimeSeries s, int minTime, int maxTime, double minValue, double maxValue);

	public void addMultipleTimeValues(TimeSeries s, List<Integer> times, List<Double> values);

	public void addMultipleRandomTimeValues(TimeSeries s, int minTime, int maxTime, double minValue, double maxValue,
			int count);

	/**********
	 * TODO: EDIT OPERATORS (SOME ADDRESS LATE/EARLY/VALUECOARSE/VALUESUBTLE
	 * MUTATIONS)
	 **********/

	public void changeValue(TimeSeries s, double value, int time);

	public void changeTime(TimeSeries s, int timeOld, int timeNew);

	public void changeValueWithRandom(TimeSeries s, double minValue, double maxValue, int time, ReadFactory factory);

	public void changeTimeWithRandom(TimeSeries s, int minTime, int maxTime, int time);

	public void changeTimeAndValue(TimeSeries s, int timeOld, int timeNew, double newValue);

	public void changeTimeWithRandomAndValue(TimeSeries s, int timeOld, int minTime, int maxTime, double valueNew);

	public void changeTimeAndValueWithRandom(TimeSeries s, int timeOld, int timeNew, int minValue, int maxValue,
			ReadFactory factory);

	public void changeTimeWithRandomAndValueWithRandom(TimeSeries s, int timeOld, int minTime, int maxTime,
			double minValue, double maxValue, ReadFactory factory);

	public void changeARandomTimeValue(TimeSeries s, int newTime, double newValue);

	public void changeARandomTimeValueWithRandomTimeValue(TimeSeries s, int minTime, int maxTime, double minValue,
			double maxValue, ReadFactory factory);

	public void changeMultipleTimeValues(TimeSeries s, List<Integer> timesOld, List<Integer> timesNew,
			List<Double> valuesNew);

	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeries s, List<Integer> timesOld, int minTime,
			int maxTime, double minValue, double maxValue, ReadFactory factory);

	public void changeTimeLate(TimeSeries s, double eps, int time, double maxDomainTime);

	public void changeRandomTimeLate(TimeSeries s, double eps, int maxDomainTime);

	public void changeMultipleTimeLate(TimeSeries s, double eps, List<Integer> times, int maxDomainTime);

	public void changeTimeEarly(TimeSeries s, double eps, int time, double minDomainTime);

	public void changeRandomTimeEarly(TimeSeries s, double eps, double minDomainTime);

	public void changeMultipleTimeEarly(TimeSeries s, double eps, List<Integer> times, double minDomainTime);

	public void changeValueCoarse(TimeSeries s, double eps, double minDomainValue, double maxDomainValue, int time);

	public void changeRandomValueCoarse(TimeSeries s, double eps, double minDomainValue, double maxDomainValue);

	public void changeMultipleValueCoarse(TimeSeries s, double eps, List<Integer> times, double minDomainValue,
			double maxDomainValue);

	public void changeValueSubtle(TimeSeries s, double eps, int time, double minDomainValue, double maxDomainValue);

	public void changeRandomValueSubtle(TimeSeries s, double eps, double minDomainValue, double maxDomainValue);

	public void changeMultipleValueSubtle(TimeSeries s, double eps, List<Integer> times, double minDomainValue,
			double maxDomainValue);

	/********** TODO: REMOVE OPERATORS (ALL ADDRESS OMISSION MUTATION) **********/

	public void removeTimeValue(TimeSeries s, int time, double value);

	public void removeTimeValue(TimeSeries s, TimeValue tvToRemove);

	public void removeRandomTimeValue(TimeSeries s);

	public void removeMultipleTimeValues(TimeSeries s, List<Integer> times, List<Double> values);

	public void removeMultipleTimeValues(TimeSeries s, Object... timesAndValues);

	public void removeMultipleTimeValues(TimeSeries s, List<TimeValue> timeValues);

	public void removeMultipleTimeValues(TimeSeries s, TimeValue... timeValues);

	public void removeAllTimeValues(TimeSeries s);

	public void removeTimeValuesBeforeTime(TimeSeries s, int time);

	public void removeTimeValuesAfterTime(TimeSeries s, int time);

	public void removeTimeValuesBelowValue(TimeSeries s, double value);

	public void removeTimeValuesAboveValue(TimeSeries s, double value);

	public void setAllTimesToZero(TimeSeries s);

	public void setAllValuesToZero(TimeSeries s);

	public void setAllToZero(TimeSeries s);

	public void setAllTimesToTime(TimeSeries s, int time);

	public void setAllValuesToValue(TimeSeries s, double value);

	public void setAllToTimeValue(TimeSeries s, int time, int value);
}
