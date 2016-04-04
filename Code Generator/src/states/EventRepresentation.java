package states;

import java.util.ArrayList;

public class EventRepresentation {

	
	private String className;
	private String instanceName;
	private String eventName;
	ArrayList <Parameter> parameters = null;
	
	public boolean isSpecialEvent = false;
	public boolean isTimerEvent = false;
	
	public EventRepresentation(){
		isSpecialEvent = false;
		isTimerEvent = false;
		className = "";
		instanceName = "";
		eventName = "";
		parameters = new ArrayList<Parameter>();
	}
	
	//Setters
	public void setClassName(String className){this.className = className;}
	public void setInstanceName(String instanceName){this.instanceName = instanceName;}
	public void setEventName(String eventName){this.eventName = eventName;}
	public void addParameter(Parameter parameters){this.parameters.add(parameters);}
	
	//Getters
	public String getClassName(){return this.className;}
	public String getInstanceName(){return this.instanceName;}
	public String getEventName(){return this.eventName;}
	public ArrayList<Parameter> getParameters(){return this.parameters;}
	
	
	
}
