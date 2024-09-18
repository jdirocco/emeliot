package test.runtime;

import org.eclipse.emf.common.util.EList;


import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

@SuppressWarnings("all")
public class EmeliotTest extends EmeliotLib {
	  public static void main(final String[] args) {
		  EmeliotTest th1s = new EmeliotTest();
		    	try{
		    		th1s.doExecute();
		    	} catch(Exception e){e.printStackTrace();}
		  }

		  public void omission(final TimeSeries timeSeries) {
		    TimeValue tvOut = this.selectRandomTimeValue(this.input1);
		    boolean isTime = this.existTime(this.input1, tvOut.getTime());
		    System.out.println(isTime);
		  }

		  private TimeSeries input1 =  ReadFactory.eINSTANCE.createTimeSeries() ;

		  private TimeValue tv1 =  ReadFactory.eINSTANCE.createTimeValue() ;

		  private TimeValue tv2 =  ReadFactory.eINSTANCE.createTimeValue() ;

		  protected void doExecute() throws Exception {
		    
		    	
		    	
		    		tv1.setTime(5);
		    		tv1.setValue(2);
		    		
		    		input1.getTimeValues().add(tv1);
		    						
		    	
		    		tv2.setTime(5);
		    		tv2.setValue(4);
		    		
		    		input1.getTimeValues().add(tv2);
		    						
		    	
		    				
		    	omission(input1);
		    		
		    						
		  }
	}
