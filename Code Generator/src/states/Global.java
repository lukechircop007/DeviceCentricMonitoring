package states;

import java.util.ArrayList;
import java.util.HashMap;

public class Global {

	private Timers timers = null;
	private Events events = null;
	private States states = null;
	private Conditions conditions = null;
	private Actions actions = null;
	private Rules rules = null;
	
	private HashMap<Integer, HashMap<String,Integer>> commonEvents;
		
	//Setters
	public void setRules(Rules rules){this.rules = rules;}
	public void setTimers(Timers timers){this.timers = timers;}	
	public void setEvents(Events events){this.events = events;}
	public void setStates(States states){this.states = states;}	
	public void setActions(Actions actions){this.actions = actions;}	
	public void setConditions(Conditions conditions){this.conditions = conditions;}	
		
	//Getters
	public Rules getRules(){return this.rules;}
	public Timers getTimers(){return this.timers;}	
	public Events getEvents(){return this.events;}	
	public States getStates(){return this.states;}	
	public Actions getActions(){return this.actions;}
	public Conditions getConditions(){return this.conditions;}
		
	//KernelSide Monitor Code Generation
	public String generateKernelCode(){
		System.out.println("Generating KernelSide Monitor Code...");
		String text = "\n";
						
		//First get the States(variables...) and generate all the text needed.....
		if(getStates() != null)	text += getStates().toKernelCCode();

		//Identifying common events... 
		commonEvents  = getEvents().groupCommonEvents();
		//Then generate the prototypes	
		text += getRules().generatePrototypes(commonEvents,getEvents(),getActions());	
		
		//Then generate the actual systemCalls.....
		for(int i = 0; i < commonEvents.size(); i++){
			text+= getRules().generateMethodCallsForSyscall(i, commonEvents.get(i), events, actions);
		}
				
		//Then generate the method calls...
		text += getRules().generateMethods(commonEvents, getEvents(),getConditions(),getActions());
		
		return text;
	}
	
	//KernelSide Call.S Code Generation...
	public String generateKernelCallCode(){
		String text ="";		
		System.out.println("Generating Call.S code...");
		
		text+= "/* Start of Custom SystemCalls */\n";

		for(int i: commonEvents.keySet()){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			if(hasKernelCode){
				text+= getRules().generateCALLS(i, commonEvents.get(i), events);
			}
		}

		text+= "/* End of Custom SystemCalls */";
		return text;		
	}
	
	//KernelSide Syscall.h Code Generation...
	public String generateKernelSyscallPrototypes(){
		System.out.println("Generating kernelSystemCall prototypes...");
		
		String text = "";
		text+= "/* Start of Custom SystemCalls */";		
		
		for(int i = 0; i < commonEvents.size(); i++){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			
			if(hasKernelCode){
				text+= getRules().generateSyscallPrototypes(i, commonEvents.get(i), events);
			}
		}
		
		text+= "\n/* End of Custom SystemCalls */";
		return text;
	}
	
	//KernelSide Unistd.h Code Generation...
	public String generateKernelUnistdCode(int syscalPos){
		System.out.println("Generating Unistd prototypes...");
		
		String text = "";
		text+= "/* Start of Custom SystemCalls */\n";
		
		int position  = syscalPos;
		
		for(int i: commonEvents.keySet()){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			
			if(hasKernelCode){
				text+= getRules().generateUnistd(i,position, commonEvents.get(i), events, actions);				
				position++;
			}
		}
		
		text+= "\n/* End of Custom SystemCalls */";
		return text;		
	}
		
	//Generates Event instrumentation Code
	protected String generateEventInstCode(Event event, EventRepresentation evntRep, HashMap<Event, String>  eventsCalledInRules){
		String text ="";
		
		//Check the event capture method... i.e. before method starts executing or after...
		if(event.getEventCapture() == null || event.getEventCapture().equals("before")){
			text+= "@before\n";
		}else if(event.getEventCapture().equals("after")){
			text+= "@after\n";						
		}
		//Setup details about event that is going to be involved... 
		text+= "@Class: "+evntRep.getClassName()+"\n";
		text+= "@Instance: "+evntRep.getInstanceName()+"\n";
		text+= "@Event: "+evntRep.getEventName();
		text+=  "("+evntRep.getParameters().get(0).getParameterListAppSide()+")\n";
		text+= "@Code:\n";
				
		//To add the Code To generate Where code... 		
		text += getEvents().getAllWhereCode(event, getRules());
		
		if(event.getUponReturningCode() != null)//Upon Returning code... 
			text+= event.getUponReturningCode()+" = uponReturning; \n\n";
		
		// Here we should add the code of the event and also take into consideration the extra parameters....
		text+= eventsCalledInRules.get(event);
							
		text+="@EndCode \n\n";
		
		return text;
	}
	
	//Generates the Code for Every event called by rules..
	public String generateEventCode(){
		System.out.println("Generating Event Instrumentation Code...");

		HashMap<Event, String>  eventsCalledInRules = getEvents().groupEventsWithTheirActions(commonEvents, getRules(), getConditions(), getActions());
		String text = "";
		//For every Event that is called at least once in the rules...
		for(Event event: eventsCalledInRules.keySet()){
			// if it is not a special event....
			if(!event.isSpecialEvent()){
				//System.out.println("Event that is not special: "+event.getEventName()+" Rep: "+event.getEventRepresentationSize() );
				EventRepresentation evntRep = event.getEventRepresentation(0);
				//If it is not a timer event...
				if(!evntRep.isTimerEvent){
					//System.out.println("It is not a timer event...");				
					text += generateEventInstCode(event, evntRep, eventsCalledInRules);						
				}
			}else{//is a special event.. 
				//For every event representation
				for(EventRepresentation evntRep : event.getEventRepresentations()){
					//that does not exist inside the events list...  aka
					if(!evntRep.isSpecialEvent){
						//System.out.println("EventRep name: "+evntRep.getEventName());						
						text += generateEventInstCode(event, evntRep, eventsCalledInRules);						
					}
				}
			}	
		}
		return text;
	}

	//Generates the System call Framework code... Java
	public String generateRVSyscallJavaCode(){
		System.out.println("Generating RVSyscall...");
		
		String text = "package com.java.runtimeverification;\n";
		text+= "public class RVSyscall {\n\n";
		text+= "static {\n";
		text+= "System.loadLibrary(\"syscallrv\");\n";
		text+= "}\n";
		
		//Generate all the protoypes for the SyscallFramework...
		for(int i = 0; i < commonEvents.size(); i++){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			
			if(hasKernelCode){
				text+= getRules().generateSyscallFrameworkPrototypesJava(i, commonEvents.get(i), events)+"\n";
			}
		}
		
		//Generate all methods that call these prototypes...
		for(int i = 0; i < commonEvents.size(); i++){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			
			if(hasKernelCode){
				text+= getRules().generateSyscallFrameworkJava(i, commonEvents.get(i), events)+"\n";
			}
		}
		
		text+= "}\n";	
		return text;
	}
	
	//Generates the SystemCall framework code... C side (the library that will be called by the java side framework..)
	public String generateSyscallFrameworkCCode(int syscalPos){
		String text = "#define _GNU_SOURCE "
				+ "\n#include <jni.h> "
				+ "\n#include <unistd.h> "
				+ "\n#include <sys/syscall.h> "
				+ "\n#include <sys/types.h>\n\n";
		
		int position  = syscalPos;
		// Generating defines to be able to call the new system calls...
		for(int i: commonEvents.keySet()){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			
			if(hasKernelCode){
				text+= getRules().generateCalls(i,position, commonEvents.get(i), events, actions);				
				position++;
			}
		}
		//Generating the calls that will call the systemcall...
		for(int i = 0; i < commonEvents.size(); i++){
			boolean hasKernelCode = false;
			for(Rule rule : rules.getRules()){
				if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == i){				
					for(String actn: rule.getRuleActions()){
						//If it does... generate the prototype...
						if(actions.getNormalAction(actn).kernelSideAction != null){
							hasKernelCode = true;
							break;
						}
					}
				}
			}	
			
			if(hasKernelCode){
				text+= getRules().generateSyscallFrameworkPrototypesC(i, commonEvents.get(i), events);
			}
		}		
		return text;
	}

	public String generateTimerConditionAndAction(){
		return getActions().generateTimerActions(commonEvents, getEvents(),getConditions(),getRules());
	}
}
