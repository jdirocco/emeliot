package examples.javasim;

import java.util.ArrayList;
import java.util.List;

import emeliot.dsl.platform.javasim.SimulatedComponent;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

/**
 * Java model of the irrigation unit of the running example: the valve is
 * opened (5 V) when the irrigation request exceeds the activation threshold,
 * and closed (0 V) otherwise.
 */
public class IrrigationUnit implements SimulatedComponent {

	private static final double THRESHOLD = 2.5;
	private static final double OPEN = 5.0;
	private static final double CLOSED = 0.0;

	@Override
	public List<TimeSeries> simulate(List<TimeSeries> inputs, int outputs) {
		List<TimeSeries> result = new ArrayList<>();
		for (int i = 0; i < outputs; i++) {
			TimeSeries request = inputs.get(Math.min(i, inputs.size() - 1));
			TimeSeries valve = ReadFactory.eINSTANCE.createTimeSeries();
			for (TimeValue in : request.getTimeValues()) {
				TimeValue out = ReadFactory.eINSTANCE.createTimeValue();
				out.setTime(in.getTime());
				out.setValue(in.getValue() >= THRESHOLD ? OPEN : CLOSED);
				valve.getTimeValues().add(out);
			}
			result.add(valve);
		}
		return result;
	}
}
