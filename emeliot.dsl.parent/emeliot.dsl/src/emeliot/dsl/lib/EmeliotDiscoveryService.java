package emeliot.dsl.lib;

import emeliot.dsl.read.TimeSeries;

public interface EmeliotDiscoveryService {
	


	public boolean isCommission(TimeSeries tsOriginal, TimeSeries tsMutated);

	public boolean isOmission(TimeSeries tsOriginal, TimeSeries tsMutated);

	public boolean isLate(TimeSeries tsOriginal, TimeSeries tsMutated, double eps);

	public boolean isEarly(TimeSeries tsOriginal, TimeSeries tsMutated, double eps);

	public boolean isValueCoarse(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double minValue, double maxValue);

	public boolean isValueSubtle(TimeSeries tsOriginal, TimeSeries tsMutated, double eps, double minValue, double maxValue);
	
	
	
	

}
