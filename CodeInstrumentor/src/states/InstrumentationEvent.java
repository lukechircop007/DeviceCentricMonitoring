package states;

import java.util.ArrayList;

public class InstrumentationEvent {

	private String pointOfInstrumentation;
	private String eventName;
	private Parameters eventParameters;
	private String eventInstance;
	private String uponReturning = null;
	private String codeToBeInstrumented;
		
	public InstrumentationEvent(){
	}
	
	//Setters
	public void setPointOfInstrumentation(String pointOfInstrumentation){
		this.pointOfInstrumentation = pointOfInstrumentation;
	}
	
	public void setEventName(String eventName){
		this.eventName = eventName;
	}
	
	public void setEventParameters(Parameters eventParameters){
		this.eventParameters = eventParameters;
	}
	
	public void setEventInstance(String eventInstance){
		this.eventInstance = eventInstance;
	}
	
	public void setUponReturning(String uponReturning){
		this.uponReturning = uponReturning;		
	}
	
	public void setCodeToBeInstrumented(String codeToBeInstrumented){
		this.codeToBeInstrumented = codeToBeInstrumented;
	}
	
	//Getters
	public String getPointOfInstrumentation(){
		return pointOfInstrumentation;
	}
	
	public String getEventName(){
		return eventName;
	}
	
	public Parameters getEventParameters(){
		return eventParameters;
	}
	
	public String getEventInstance(){
		return eventInstance;
	}
	
	public String getUponReturning(){
		return uponReturning;
	}
	
	public String getCodeToBeInstrumented(){
		return codeToBeInstrumented;
	}
}
