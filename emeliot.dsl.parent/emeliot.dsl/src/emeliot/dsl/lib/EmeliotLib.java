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
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;

public abstract class EmeliotLib implements EmeliotService, EmeliotMutationService {

	@Override
	public void addTimeAndValue(TimeSeries s, Double value, int time) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		tv.setTime(time);
		tv.setValue(value);
		if (s instanceof TimeSeriesValue) {
			((TimeSeriesValue) s).getTimeValues().add(tv);
		}

		reorderTimeSeries(s);

	}

	@Override
	public void addRandomTimeAndValue(TimeSeries s, Double value, int minTime, int maxTime) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		tv.setTime(timeRandom);
		tv.setValue(value);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}

	@Override
	public void addTimeAndRandomValue(TimeSeries s, int time, double minValue, double maxValue, ReadFactory factory) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
		// IntegerType value = factory.createIntegerType();
		// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
		// value.setValue(valueRandom);
		tv.setTime(time);
		tv.setValue(valueRandom);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}

	@Override
	public void addRandomTimeAndRandomValue(TimeSeries s, int minTime, int maxTime, double minValue, double maxValue) {
		TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		double valueRandom = r.nextDouble(minValue, maxValue);

		tv.setTime(timeRandom);
		tv.setValue(valueRandom);
		((TimeSeriesValue) s).getTimeValues().add(tv);
		reorderTimeSeries(s);
	}

	@Override
	public void addMultipleTimeValues(TimeSeries s, List<Integer> times, List<Double> values) {
		if (times.size() != values.size())
			throw new IllegalArgumentException("Times and values sizes do not match");
		for (int i = 0; i < times.size(); i++) {
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(times.get(i));
			tv.setValue(values.get(i));
			((TimeSeriesValue) s).getTimeValues().add(tv);
		}
		reorderTimeSeries(s);
	}

	@Override
	public void addMultipleRandomTimeValues(TimeSeries s, int minTime, int maxTime, double minValue, double maxValue,
			int count) {
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int timeRandom = r.nextInt(minTime, maxTime);
			double valueRandom = r.nextDouble(minValue, maxValue);
			// IntegerType value = factory.createIntegerType();
			// value.setValue(valueRandom);
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(timeRandom);
			tv.setValue(valueRandom);
			((TimeSeriesValue) s).getTimeValues().add(tv);
		}
		reorderTimeSeries(s);
	}

	/**********
	 * TODO: EDIT OPERATORS (SOME ADDRESS LATE/EARLY/VALUECOARSE/VALUESUBTLE
	 * MUTATIONS)
	 **********/

	@Override
	public void changeValue(TimeSeries s, double value, int time) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				tv.setValue(value);
				break;
			}
		}
	}

	@Override
	public void changeTime(TimeSeries s, int timeOld, int timeNew) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == timeOld) {
				tv.setTime(timeNew);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeValueWithRandom(TimeSeries s, double minValue, double maxValue, int time, ReadFactory factory) {
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
		// IntegerType value = factory.createIntegerType();
		// value.setValue(valueRandom);
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				tv.setValue(valueRandom);
				break;
			}
		}
	}

	@Override
	public void changeTimeWithRandom(TimeSeries s, int minTime, int maxTime, int time) {
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				tv.setTime(timeRandom);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeTimeAndValue(TimeSeries s, int timeOld, int timeNew, double newValue) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == timeOld) {
				tv.setTime(timeNew);
				tv.setValue(newValue);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeTimeWithRandomAndValue(TimeSeries s, int timeOld, int minTime, int maxTime, double valueNew) {
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == timeOld) {
				tv.setTime(timeRandom);
				tv.setValue(valueNew);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeTimeAndValueWithRandom(TimeSeries s, int timeOld, int timeNew, int minValue, int maxValue,
			ReadFactory factory) {
		Random r = new Random();
		double valueRandom = r.nextDouble(minValue, maxValue);
		// IntegerType valueNew = factory.createIntegerType();
		// valueNew.setValue(valueRandom);
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == timeOld) {
				tv.setTime(timeNew);
				tv.setValue(valueRandom);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeTimeWithRandomAndValueWithRandom(TimeSeries s, int timeOld, int minTime, int maxTime,
			double minValue, double maxValue, ReadFactory factory) {
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		double valueRandom = r.nextDouble(minValue, maxValue);
		// IntegerType valueNew = factory.createIntegerType();
		// valueNew.setValue(valueRandom);
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == timeOld) {
				tv.setTime(timeRandom);
				tv.setValue(valueRandom);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeARandomTimeValue(TimeSeries s, int newTime, double newValue) {
		TimeValue tvRandom = selectRandomTimeValue(s);
		if (tvRandom != null) {
			tvRandom.setTime(newTime);
			tvRandom.setValue(newValue);
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeARandomTimeValueWithRandomTimeValue(TimeSeries s, int minTime, int maxTime, double minValue,
			double maxValue, ReadFactory factory) {
		TimeValue randomTV = selectRandomTimeValue(s);
		Random r = new Random();
		int timeRandom = r.nextInt(minTime, maxTime);
		double valueRandom = r.nextDouble(minValue, maxValue);
		// IntegerType valueNew = factory.createIntegerType();
		// valueNew.setValue(valueRandom);
		if (randomTV != null) {
			randomTV.setTime(timeRandom);
			randomTV.setValue(valueRandom);
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeMultipleTimeValues(TimeSeries s, List<Integer> timesOld, List<Integer> timesNew,
			List<Double> valuesNew) {
		if (timesOld.size() != valuesNew.size() || timesOld.size() != timesNew.size())
			throw new IllegalArgumentException("Times and values sizes do not match");
		for (int i = 0; i < timesOld.size(); i++) {
			for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
				if (tv.getTime() == timesOld.get(i)) {
					tv.setTime(timesNew.get(i));
					tv.setValue(valuesNew.get(i));
					continue;
				}
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeMultipleTimeValuesWithRandomTimeValues(TimeSeries s, List<Integer> timesOld, int minTime,
			int maxTime, double minValue, double maxValue, ReadFactory factory) {
		Random r = new Random();
		for (int i = 0; i < timesOld.size(); i++) {
			for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
				if (tv.getTime() == timesOld.get(i)) {
					int timeRandom = r.nextInt(minTime, maxTime);
					double valueRandom = r.nextDouble(minValue, maxValue);
					// IntegerType valueNew = factory.createIntegerType();
					// valueNew.setValue(valueRandom);
					tv.setTime(timeRandom);
					tv.setValue(valueRandom);
					continue;
				}
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeTimeLate(TimeSeries s, double eps, int time, double maxDomainTime) {
		Random r = new Random();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				int timeNew = time + (int) (r.nextDouble() * (maxDomainTime - time - eps) + eps);
				tv.setTime(timeNew);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeRandomTimeLate(TimeSeries s, double eps, int maxDomainTime) {
		Random r = new Random();
		TimeValue randomTV = selectRandomTimeValue(s);
		if (randomTV != null) {
			int timeOld = randomTV.getTime();
			int timeNew = timeOld + (int) (r.nextDouble() * (maxDomainTime - timeOld - eps) + eps);
			randomTV.setTime(timeNew);
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeMultipleTimeLate(TimeSeries s, double eps, List<Integer> times, int maxDomainTime) {
		Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
			for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
				if (tv.getTime() == times.get(i)) {
					int timeOld = times.get(i);
					int timeNew = timeOld + (int) (r.nextDouble() * (maxDomainTime - timeOld - eps) + eps);
					tv.setTime(timeNew);
					continue;
				}
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeTimeEarly(TimeSeries s, double eps, int time, double minDomainTime) {
		Random r = new Random();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				int timeNew = time - (int) (r.nextDouble() * (time - minDomainTime - eps) + eps);
				tv.setTime(timeNew);
				break;
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeRandomTimeEarly(TimeSeries s, double eps, double minDomainTime) {
		Random r = new Random();
		TimeValue randomTV = selectRandomTimeValue(s);
		if (randomTV != null) {
			int timeOld = randomTV.getTime();
			int timeNew = timeOld - (int) (r.nextDouble() * (timeOld - minDomainTime - eps) + eps);
			randomTV.setTime(timeNew);
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeMultipleTimeEarly(TimeSeries s, double eps, List<Integer> times, double minDomainTime) {
		Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
			for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
				if (tv.getTime() == times.get(i)) {
					int timeOld = times.get(i);
					int timeNew = timeOld - (int) (r.nextDouble() * (timeOld - minDomainTime - eps) + eps);
					tv.setTime(timeNew);
					continue;
				}
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeValueCoarse(TimeSeries s, double eps, double minDomainValue, double maxDomainValue, int time) {
		Random r = new Random();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				double valueNew = (r.nextBoolean())
						? (r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
						: (int) (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
				// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
				// value.setValue(valueNew);
				tv.setValue(valueNew);
				break;
			}
		}
	}

	@Override
	public void changeRandomValueCoarse(TimeSeries s, double eps, double minDomainValue, double maxDomainValue) {
		Random r = new Random();
		TimeValue randomTV = selectRandomTimeValue(s);
		if (randomTV != null) {
			double valueNew = (r.nextBoolean())
					? (r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
					: (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
			// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
			// value.setValue(valueNew);
			randomTV.setValue(valueNew);
		}
	}

	@Override
	public void changeMultipleValueCoarse(TimeSeries s, double eps, List<Integer> times, double minDomainValue,
			double maxDomainValue) {
		Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
			for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
				if (tv.getTime() == times.get(i)) {
					double valueNew = (r.nextBoolean())
							? (r.nextDouble() * (Double.MAX_VALUE - maxDomainValue - eps) + maxDomainValue + eps)
							: (r.nextDouble() * (minDomainValue - eps - Double.MIN_VALUE) + Double.MIN_VALUE);
					// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
					// value.setValue(valueNew);
					tv.setValue(valueNew);
					continue;
				}
			}
		}
		reorderTimeSeries(s);
	}

	@Override
	public void changeValueSubtle(TimeSeries s, double eps, int time, double minDomainValue, double maxDomainValue) {
		Random r = new Random();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (tv.getTime() == time) {
				double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
						: (r.nextDouble() * (eps - minDomainValue) - eps);
				// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
				// value.setValue(valueNew);
				tv.setValue(valueNew);
				break;
			}
		}
	}

	@Override
	public void changeRandomValueSubtle(TimeSeries s, double eps, double minDomainValue, double maxDomainValue) {
		Random r = new Random();
		TimeValue randomTV = selectRandomTimeValue(s);
		if (randomTV != null) {
			double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
					: (r.nextDouble() * (eps - minDomainValue) - eps);
			// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
			// value.setValue(valueNew);
			randomTV.setValue(valueNew);
		}
	}

	@Override
	public void changeMultipleValueSubtle(TimeSeries s, double eps, List<Integer> times, double minDomainValue,
			double maxDomainValue) {
		Random r = new Random();
		for (int i = 0; i < times.size(); i++) {
			for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
				if (tv.getTime() == times.get(i)) {
					double valueNew = (r.nextBoolean()) ? (r.nextDouble() * (maxDomainValue - eps) + eps)
							: (r.nextDouble() * (eps - minDomainValue) - eps);
					// IntegerType value = ReadFactory.eINSTANCE.createIntegerType();
					// value.setValue(valueNew);
					tv.setValue(valueNew);
					continue;
				}
			}
		}
		reorderTimeSeries(s);
	}

	/********** TODO: REMOVE OPERATORS (ALL ADDRESS OMISSION MUTATION) **********/

	@Override
	public void removeTimeValue(TimeSeries s, int time, double value) {
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
	}

	@Override
	public void removeTimeValue(TimeSeries s, TimeValue tvToRemove) {
		((TimeSeriesValue) s).getTimeValues()
				.removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == tvToRemove.getValue());
	}

	@Override
	public void removeRandomTimeValue(TimeSeries s) {
		TimeValue randomTV = selectRandomTimeValue(s);
		if (randomTV != null) {
			((TimeSeriesValue) s).getTimeValues().remove(randomTV);
		}
	}

	@Override
	public void removeMultipleTimeValues(TimeSeries s, List<Integer> times, List<Double> values) {
		for (int i = 0; i < times.size(); i++) {
			final int time = times.get(i);
			final double value = values.get(i);
			((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
		}
	}

	@Override
	public void removeMultipleTimeValues(TimeSeries s, Object... timesAndValues) {
		if (timesAndValues.length % 2 != 0)
			throw new IllegalArgumentException("Missing timevalues");
		for (int i = 0; i < timesAndValues.length; i += 2) {
			if (!(timesAndValues[i] instanceof Integer) || !(timesAndValues[i + 1] instanceof Double))
				throw new IllegalArgumentException("Wrong timevalues type");
			final int time = (Integer) timesAndValues[i];
			final Double value = (Double) timesAndValues[i + 1];
			((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() == time && tv.getValue() == value);
		}
	}

	@Override
	public void removeMultipleTimeValues(TimeSeries s, List<TimeValue> timeValues) {
		for (TimeValue tvToRemove : timeValues) {
			((TimeSeriesValue) s).getTimeValues()
					.removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == (tvToRemove.getValue()));
		}
	}

	@Override
	public void removeMultipleTimeValues(TimeSeries s, TimeValue... timeValues) {
		for (TimeValue tvToRemove : timeValues) {
			((TimeSeriesValue) s).getTimeValues()
					.removeIf(tv -> tv.getTime() == tvToRemove.getTime() && tv.getValue() == (tvToRemove.getValue()));
		}
	}

	@Override
	public void removeAllTimeValues(TimeSeries s) {
		((TimeSeriesValue) s).getTimeValues().clear();
	}

	@Override
	public void removeTimeValuesBeforeTime(TimeSeries s, int time) {
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() < time);
	}

	@Override
	public void removeTimeValuesAfterTime(TimeSeries s, int time) {
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getTime() > time);
	}

	@Override
	public void removeTimeValuesBelowValue(TimeSeries s, double value) {
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getValue() < value);
	}

	@Override
	public void removeTimeValuesAboveValue(TimeSeries s, double value) {
		((TimeSeriesValue) s).getTimeValues().removeIf(tv -> tv.getValue() > value);
	}

	/********** TODO: UTILS OPERATORS **********/

	@Override
	public void printTimeSeries(TimeSeries s) {
		System.out.print("[");
		var first = true;
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			if (!first) {
				System.out.print(", ");
			}
			System.out.print("(" + tv.getTime() + ":" + tv.getValue() + ")");
			first = false;
		}
		System.out.print("]");
	}

	@Override
	public void reorderTimeSeries(TimeSeries s) {
		List<TimeValue> timeValues = new ArrayList<>(((TimeSeriesValue) s).getTimeValues());
		timeValues.sort(Comparator.comparingInt(TimeValue::getTime));
		((TimeSeriesValue) s).getTimeValues().clear();
		for (TimeValue tv : timeValues)
			((TimeSeriesValue) s).getTimeValues().add(tv);
	}

	@Override
	public TimeValue selectRandomTimeValue(TimeSeries s) {
		Random r = new Random();
		List<TimeValue> timeValues = ((TimeSeriesValue) s).getTimeValues();
		if (!timeValues.isEmpty()) {
			int randomIndex = r.nextInt(timeValues.size());
			return timeValues.get(randomIndex);
		}
		return null;
	}

	@Override
	public boolean existTime(TimeSeries s, int time) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			if (tv.getTime() == time)
				return true;
		return false;
	}

	@Override
	public boolean existValue(TimeSeries s, double value) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			if (tv.getValue() == value)
				return true;
		return false;
	}

	@Override
	public boolean existTimeValue(TimeSeries s, int time, double value) {
		if (existTime(s, time) && existValue(s, value))
			return true;
		return false;
	}

	@Override
	public List<Integer> getAllTimes(TimeSeries s) {
		List<Integer> times = new ArrayList<>();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			times.add(tv.getTime());
		return times;
	}

	@Override
	public List<Double> getAllValues(TimeSeries s) {
		List<Double> values = new ArrayList<>();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			values.add(tv.getValue());
		return values;
	}

	@Override
	public List<TimeValue> getTimesInRange(TimeSeries s, int minTime, int maxTime) {
		List<TimeValue> timeValues = new ArrayList<>();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			if (tv.getTime() >= minTime && tv.getTime() <= maxTime)
				timeValues.add(tv);
		return timeValues;
	}

	@Override
	public List<TimeValue> getValuesInRange(TimeSeries s, double minValue, double maxValue) {
		List<TimeValue> timeValues = new ArrayList<>();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			double value = tv.getValue();
			if (value >= minValue && value <= maxValue)
				timeValues.add(tv);
		}
		return timeValues;
	}

	@Override
	public double getValueAt(TimeSeries s, int index) {
		return ((TimeSeriesValue) s).getTimeValues().get(index).getValue();
	}

	@Override
	public int getTimeAt(TimeSeries s, int index) {
		return ((TimeSeriesValue) s).getTimeValues().get(index).getTime();
	}

	@Override
	public TimeValue getTimeValueAt(TimeSeries s, int index) {
		return ((TimeSeriesValue) s).getTimeValues().get(index);
	}

	@Override
	public int getMaxTime(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().stream().mapToInt(TimeValue::getTime).max()
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public int getMinTime(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().stream().mapToInt(TimeValue::getTime).min()
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public double getMaxValue(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().stream().mapToDouble(tv -> tv.getValue()).max()
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public double getMinValue(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().stream().mapToDouble(tv -> tv.getValue()).min()
				.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public TimeValue getNextTimeValue(TimeSeries s, TimeValue tv) {
		int index = ((TimeSeriesValue) s).getTimeValues().indexOf(tv);
		if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size() - 1) {
			return ((TimeSeriesValue) s).getTimeValues().get(index + 1);
		}
		return null;
	}

	@Override
	public int getNextTime(TimeSeries s, TimeValue tv) {
		TimeValue nextTV = getNextTimeValue(s, tv);
		return (nextTV != null) ? nextTV.getTime() : -1;
	}

	@Override
	public double getNextValue(TimeSeries s, TimeValue tv) {
		TimeValue nextTV = getNextTimeValue(s, tv);
		return (nextTV != null) ? nextTV.getValue() : null;
	}

	@Override
	public TimeValue getNextTimeValue(TimeSeries s, int index) {
		if (index >= 0 && index < ((TimeSeriesValue) s).getTimeValues().size() - 1) {
			return ((TimeSeriesValue) s).getTimeValues().get(index + 1);
		}
		return null;
	}

	@Override
	public int getNextTime(TimeSeries s, int index) {
		TimeValue nextTV = getNextTimeValue(s, index);
		return (nextTV != null) ? nextTV.getTime() : -1;
	}

	@Override
	public double getNextValue(TimeSeries s, int index) {
		TimeValue nextTV = getNextTimeValue(s, index);
		return (nextTV != null) ? nextTV.getValue() : null;
	}

	@Override
	public TimeValue getPreviousTimeValue(TimeSeries s, TimeValue tv) {
		int index = ((TimeSeriesValue) s).getTimeValues().indexOf(tv);
		if (index > 0) {
			return ((TimeSeriesValue) s).getTimeValues().get(index - 1);
		}
		return null;
	}

	@Override
	public int getPreviousTime(TimeSeries s, TimeValue tv) {
		TimeValue prevTV = getPreviousTimeValue(s, tv);
		return (prevTV != null) ? prevTV.getTime() : -1;
	}

	@Override
	public double getPreviousValue(TimeSeries s, TimeValue tv) {
		TimeValue prevTV = getPreviousTimeValue(s, tv);
		return (prevTV != null) ? prevTV.getValue() : null;
	}

	@Override
	public TimeValue getPreviousTimeValue(TimeSeries s, int index) {
		if (index > 0 && index < ((TimeSeriesValue) s).getTimeValues().size())
			return ((TimeSeriesValue) s).getTimeValues().get(index - 1);
		return null;
	}

	@Override
	public int getPreviousTime(TimeSeries s, int index) {
		TimeValue prevTV = getPreviousTimeValue(s, index);
		return (prevTV != null) ? prevTV.getTime() : -1;
	}

	@Override
	public double getPreviousValue(TimeSeries s, int index) {
		TimeValue prevTV = getPreviousTimeValue(s, index);
		return (prevTV != null) ? prevTV.getValue() : null;
	}

	@Override
	public int getFirstTime(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().isEmpty() ? -1
				: ((TimeSeriesValue) s).getTimeValues().get(0).getTime();
	}

	@Override
	public double getFirstValue(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().isEmpty() ? null
				: ((TimeSeriesValue) s).getTimeValues().get(0).getValue();
	}

	@Override
	public TimeValue getFirstTimeValue(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().isEmpty() ? null : ((TimeSeriesValue) s).getTimeValues().get(0);
	}

	@Override
	public int getLastTime(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().isEmpty() ? -1
				: ((TimeSeriesValue) s).getTimeValues().get(((TimeSeriesValue) s).getTimeValues().size() - 1).getTime();
	}

	@Override
	public double getLastValue(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().isEmpty() ? null
				: ((TimeSeriesValue) s).getTimeValues().get(((TimeSeriesValue) s).getTimeValues().size() - 1)
						.getValue();
	}

	@Override
	public TimeValue getLastTimeValue(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().isEmpty() ? null
				: ((TimeSeriesValue) s).getTimeValues().get(((TimeSeriesValue) s).getTimeValues().size() - 1);
	}

	@Override
	public TimeSeries copyTimeSeries(TimeSeries s) {
		TimeSeries copy = ReadFactory.eINSTANCE.createTimeSeries();
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues()) {
			TimeValue tvCopy = ReadFactory.eINSTANCE.createTimeValue();
			tvCopy.setTime(tv.getTime());
			tvCopy.setValue(tv.getValue());
			((TimeSeriesValue) copy).getTimeValues().add(tvCopy);
		}
		return copy;
	}

	@Override
	public void setAllTimesToZero(TimeSeries s) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			tv.setTime(0);
	}

	@Override
	public void setAllValuesToZero(TimeSeries s) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			tv.setValue(0.0);
	}

	@Override
	public void setAllToZero(TimeSeries s) {
		setAllTimesToZero(s);
		setAllValuesToZero(s);
	}

	@Override
	public void setAllTimesToTime(TimeSeries s, int time) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			tv.setTime(time);
	}

	@Override
	public void setAllValuesToValue(TimeSeries s, double value) {
		for (TimeValue tv : ((TimeSeriesValue) s).getTimeValues())
			tv.setValue(value);
	}

	@Override
	public void setAllToTimeValue(TimeSeries s, int time, int value) {
		setAllTimesToTime(s, time);
		setAllValuesToValue(s, value);
	}

//	@Override public TimeSeries getSubTimeSeriesInTimeRange(TimeSeries s, int minTime, int maxTime) {
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

	@Override
	public int countTimeValues(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().size();
	}

	@Override
	public boolean isEmpty(TimeSeries s) {
		return ((TimeSeriesValue) s).getTimeValues().size() == 0;
	}

	// TODO:

//	/********** TODO: DISCOVERY OPERATORS **********/
//	@Override public boolean isCommission(TimeSeries tsOriginal, TimeSeries tsMutated) {
//		return tsOriginal.getTimeValues().size() < tsMutated.getTimeValues().size(); //TODO: what if more elements are added and removed?
//	}
//	
//	@Override public boolean isOmission(TimeSeries tsOriginal, TimeSeries tsMutated) {
//		return tsOriginal.getTimeValues().size() > tsMutated.getTimeValues().size(); //TODO: what if more elements are added and removed?
//	}
//	
//	@Override public boolean isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
//		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
//			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
//			int time1 = tv1.getTime();			
//			double value1 = tv1.getValue();
//			int closestDiffTime = Integer.MAX_VALUE;
//			for(int j=1; j<tsMutated.getTimeValues().size(); j++) {
//				TimeValue tv2 = tsMutated.getTimeValues().get(i);
//				int time2 = tv2.getTime();			
//				double value2 =  tv2.getValue();
//				//if time and value are the same, no early/late
//				if(time1 == time2 && value1 == value2) {
//					closestDiffTime = 0;
//					break;
//				}
//				//if only value is the same, check closest time
//				else if (value1 == value2){
//					int timeDiff = time1 - time2;//diff between times
//					if(Math.abs(timeDiff)<=Math.abs(closestDiffTime)) {
//						closestDiffTime = timeDiff;
//					}
//					else
//						break;//the timeseries are ordered so once the diff is getting bigger the cycle can stop
//				}
//			}
//			if(closestDiffTime == 0)//no early/late detected for timeseries j as diff time is 0
//				continue;
//			if(Math.abs(closestDiffTime)>eps && closestDiffTime<0)//if diff time bigger than epsilon, late detected
//				return true;
//		}	
//		return false;
//	}
//	
//	@Override public boolean isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps) {
//		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
//			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
//			int time1 = tv1.getTime();			
//			double value1 =  tv1.getValue();
//			int closestDiffTime = Integer.MAX_VALUE;
//			for(int j=1; j<tsMutated.getTimeValues().size(); j++) {
//				TimeValue tv2 = tsMutated.getTimeValues().get(i);
//				int time2 = tv2.getTime();			
//				double value2 =  tv2.getValue();
//				//if time and value are the same, no early/late
//				if(time1 == time2 && value1 == value2) {
//					closestDiffTime = 0;
//					break;
//				}
//				//if only value is the same, check closest time
//				else if (value1 == value2){
//					int timeDiff = time1 - time2;//diff between times
//					if(Math.abs(timeDiff)<=Math.abs(closestDiffTime)) {
//						closestDiffTime = timeDiff;
//					}
//					else
//						break;//the timeseries are ordered so once the diff is getting bigger the cycle can stop
//				}
//			}
//			if(closestDiffTime == 0)//no early/late detected for timeseries j as diff time is 0
//				continue;
//			if(Math.abs(closestDiffTime)> eps && closestDiffTime>0) //if diff time bigger than epsilon, early detected
//				return true;
//		}	
//		return false;
//	}
//	
//	@Override public boolean isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double minValue, double maxValue) {
//		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
//			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
//			TimeValue tv2 = tsMutated.getTimeValues().get(i);
//			double valueOriginal =  tv1.getValue();
//			double valueMutated =  tv2.getValue();
//			double valueDiff = valueOriginal - valueMutated;
//			//value subtle failure found as mutated value is bigger than eps and out of boundary
//			if(Math.abs(valueDiff)> eps && (valueMutated < minValue || valueMutated > maxValue))
//				return true;
//		}	
//		return false;
//	}
//	
//	@Override public boolean isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double minValue, double maxValue) {
//		for(int i=1; i<tsOriginal.getTimeValues().size(); i++) {
//			TimeValue tv1 = tsOriginal.getTimeValues().get(i);
//			TimeValue tv2 = tsMutated.getTimeValues().get(i);
//			double valueOriginal =  tv1.getValue();
//			double valueMutated = tv2.getValue();
//			double valueDiff = valueOriginal - valueMutated;
//			//value subtle failure found as mutated value is bigger than eps and within boundary
//			if(Math.abs(valueDiff)> eps && (valueMutated >= minValue && valueMutated <= maxValue))
//				return true;
//		}
//		return false;
//	}
	@Override
	public void writeTSToFile(TimeSeriesValue TimeSeriesValue, String filePath) throws IOException {
		reorderTimeSeries(TimeSeriesValue);
		List<String> lines = TimeSeriesValue.getTimeValues().stream().map(tv -> tv.getTime() + " " + tv.getValue())
				.collect(Collectors.toList());
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	// this method writes to file a Proteus timeserie (to be used during
	// TimeSeriesValue mutation phase)
	@Override
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
		reorderTimeSeries(TimeSeriesValue);
		return TimeSeriesValue;
	}

}
