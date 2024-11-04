package test.runtime;
import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeSeriesValue;
import emeliot.dsl.read.TimeValue;

@SuppressWarnings("all")
public class EmeliotTest extends EmeliotLib {
  public static void main(final String[] args) {
    EmeliotTest th1s = new EmeliotTest();
    	try{
    		th1s.doExecute();
    	} catch(Exception e){e.printStackTrace();}
  }

  private ReadFactory factory;

  public void omission(final TimeSeries timeSeries) {
    this.addMultipleRandomTimeValues(this.input, 1, 2, 2.0, 3.0, 3);
  }

  private TimeValue tv;

  private TimeSeriesValue input =  ReadFactory.eINSTANCE.createTimeSeriesValue() ;

  private TimeSeriesValue input2 =  ReadFactory.eINSTANCE.createTimeSeriesValue() ;

  private TimeSeriesValue pathFile =  ReadFactory.eINSTANCE.createTimeSeriesValue() ;

  protected void doExecute() throws Exception {
    			
    
    			
    		
    	
    	
    		tv = ReadFactory.eINSTANCE.createTimeValue();	
    					
    		tv.setTime(52);
    		tv.setValue(3.0);								
    		input.getTimeValues().add(tv);
    						
    	
    		tv = ReadFactory.eINSTANCE.createTimeValue();	
    					
    		tv.setTime(8);
    		tv.setValue(4.5);								
    		input.getTimeValues().add(tv);
    						
    	
    				
    	
    	omission(input);	
    						
    			
    		
    	
    	
    		tv = ReadFactory.eINSTANCE.createTimeValue();	
    					
    		tv.setTime(32);
    		tv.setValue(3.0);								
    		input2.getTimeValues().add(tv);
    						
    	
    		tv = ReadFactory.eINSTANCE.createTimeValue();	
    					
    		tv.setTime(5);
    		tv.setValue(4.5);								
    		input2.getTimeValues().add(tv);
    						
    	
    				
    	
    	omission(input2);	
    						
    			
    	pathFile = readTSFromFile("C:\\Users\\claud\\Desktop\\test.txt");
    	omission(pathFile);	
    						
    	
    	final TimeSeries outSeries  =  ReadFactory.eINSTANCE.createTimeSeries() ;
    	
  }
}
