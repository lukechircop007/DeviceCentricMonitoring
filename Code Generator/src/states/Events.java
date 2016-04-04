package states;

import java.util.ArrayList;
import java.util.HashMap;

public class Events {

	private ArrayList<Event> allEvents = null;	
	private HashMap<String, Event> allEventsHash = null;
	public HashMap<Integer, HashMap<String,Integer>> commonEvents;
	
	public Events(){
		allEvents = new ArrayList<Event>();
		allEventsHash = new HashMap<String, Event>();	
	}
	
	//Setters
	public void addEvent(Event normalEvent){
		this.allEvents.add(normalEvent);
		this.allEventsHash.put(normalEvent.getEventName().split("\\(")[0],normalEvent);
	}
		
	//Getters
	public Event getEvent(int position){return this.allEvents.get(position);}
	public Event getEvent(String evenName){return this.allEventsHash.get(evenName);}	
	public ArrayList<Event> getallEventS(){return this.allEvents;}
	
	//Code Generation..
	
	//Lists the number of variable types inside the event... 
	public HashMap<String, Integer> pupulateForTypes(Event event, Parameter parameters){
		HashMap<String, Integer> numOfOccuranceOfType = new HashMap<String, Integer>();
		
		for(int i = 0 ; i< parameters.getParameterTypes().size(); i++){			
				if(!numOfOccuranceOfType.containsKey(parameters.getParameterTypes().get(i))){
					numOfOccuranceOfType.put(parameters.getParameterTypes().get(i), 1);					
				}else{//If already there... increment counter  +1
					numOfOccuranceOfType.put(parameters.getParameterTypes().get(i), numOfOccuranceOfType.get(parameters.getParameterTypes().get(i))+1);	
				}
		}		
		return numOfOccuranceOfType;
	}
	
	//Lists the number of Variable types listed in where
	public HashMap<String, Integer> listAllTypes(Event event){
		HashMap<String, Integer> numOfOccuranceOfType = new HashMap<String, Integer>();
		
		if(event.getParameters().getParameterTypes().size() >0){
			numOfOccuranceOfType = pupulateForTypes(event, event.getParameters());
		}
		
		for(int i = 0; i < event.getEventRepresentationSize(); i++){
			if(allEventsHash.containsKey(event.getEventRepresentation(i).getEventName())){
				HashMap<String, Integer> temp = listAllTypes(allEventsHash.get(event.getEventRepresentation(i).getEventName()));
				for(String eventType: temp.keySet()){
					if(!numOfOccuranceOfType.containsKey(eventType)){
						numOfOccuranceOfType.put(eventType, 1);					
					}else{//If already there... increment counter  +1
						numOfOccuranceOfType.put(eventType, numOfOccuranceOfType.get(eventType)+1);	
					}
				}
			}
		}		
		
		return numOfOccuranceOfType;
	}
	
	//Checks if event exists inside eventName (takes care of nested event calls)
	public boolean existsInsideEvent(Event event, String eventName){
		//If the event name is equal to that of the event defined in the rule... return true
		if(event.getEventName().equals(eventName)){
			//System.out.println("It is matching: "+event.getEventName()+" with "+eventName);
			return true;
		}else{// if not... check if the event is a special event...
			//If yes...  take each event representation... 
			Event tmp = allEventsHash.get(eventName);
			if(tmp == null){
				System.out.println("Error: event with name: \""+eventName+"\" could not be found in Events");
				System.exit(1);
			}
			for(int i = 0; i < tmp.getEventRepresentationSize(); i++){
				//and if it is a special event...
				if(tmp.getEventRepresentation(i).isSpecialEvent){
					//call the method again to check.. and pass on the same name...
					if(existsInsideEvent(event,tmp.getEventRepresentation(i).getEventName().split("\\(")[0])){
						return true;
					}
				}				
			}
		}	
		
		return false;
	}
	
	//Sorts the variable names in a list linked to their types inside the event...
	public HashMap<String, ArrayList<String>> populateTypeNames(Event event, Parameter parameters){
		HashMap<String, ArrayList<String>> numOfOccuranceOfType = new HashMap<String, ArrayList<String>>();
		//For every parameter defined in the event... 
		for(int i = 0 ; i< parameters.getParameterTypes().size(); i++){	
			//If the type does not exist inside the list.. 
			if(!numOfOccuranceOfType.containsKey(parameters.getParameterTypes().get(i))){
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(parameters.getParameterNames().get(i));
				numOfOccuranceOfType.put(parameters.getParameterTypes().get(i), temp);					
			}else{//If already there... increment counter  +1
				numOfOccuranceOfType.get(parameters.getParameterTypes().get(i)).add(parameters.getParameterNames().get(i));	
			}
		}		
		return numOfOccuranceOfType;
	}
	
	//Sorts the variable names inside a list linked to their types...
	public HashMap<String, ArrayList<String>> listAllTypeNames(Event event){
		
		HashMap<String, ArrayList<String>> numOfOccuranceOfType = new HashMap<String, ArrayList<String>>();
		
		if(event.getParameters().getParameterTypes().size() >0){
			numOfOccuranceOfType = populateTypeNames(event, event.getParameters());
		}
		
		for(int i = 0; i < event.getEventRepresentationSize(); i++){
			if(allEventsHash.containsKey(event.getEventRepresentation(i).isSpecialEvent && allEventsHash.containsKey(event.getEventRepresentation(i).getEventName()))){
				HashMap<String, ArrayList<String>> temp = listAllTypeNames(allEventsHash.get(event.getEventRepresentation(i).getEventName()));
				for(String eventType: temp.keySet()){
					if(!numOfOccuranceOfType.containsKey(eventType)){
						numOfOccuranceOfType.put(eventType, temp.get(eventType));					
					}else{//If already there... increment counter  +1
						numOfOccuranceOfType.get(eventType).addAll(temp.get(eventType));
					}
				}
			}
		}		
		return numOfOccuranceOfType;
	}
	
	//Groups all Events that are common (by setting a unique id to the group of events..)
	public HashMap<Integer, HashMap<String,Integer>> groupCommonEvents(){
				
		//HashMap of Events that will be tagged as grouped
		HashMap<String, Boolean> identifiedEvents = new HashMap<String,Boolean>();
		//HashMap of grouped events... 
		HashMap<Integer, HashMap<String,Integer>> commonEventParameters = new HashMap<Integer, HashMap<String,Integer>>();
		//counter for HashMap of grouped events... 
		int count = 0;
		

		//For all Events....
		for(int i = 0; i < allEvents.size(); i++){
			//That have not been tagged yet...
			if(!identifiedEvents.containsKey(allEvents.get(i).getEventName())){//&& (allEvents.get(i).getEventRepresentationSize() == 1 && !allEvents.get(i).getEventRepresentation(0).isTimerEvent)
				HashMap<String, Integer> numOfOccuranceOfType;
				int numOfParameters = 0;
				//First get all the Types and the number of times that they appear...
				if(allEvents.get(i).isSpecialEvent()){
					numOfOccuranceOfType = listAllTypes(allEvents.get(i));	
					//First get the number of parameters...
					for(String type: numOfOccuranceOfType.keySet()){
						numOfParameters+= numOfOccuranceOfType.get(type);
					}					
				}else {
					//First get the number of parameters...
					if(allEvents.get(i).getParameters() != null){
						numOfParameters = allEvents.get(i).getParameters().getParameterTypes().size();
						//List all the types and how many there are of each..
						if(numOfParameters > 0){ //If there are any extra parameters.... populate..
							numOfOccuranceOfType = pupulateForTypes(allEvents.get(i), allEvents.get(i).getParameters());
						}else{// if not... just initialize to empty..
							numOfOccuranceOfType = new HashMap<String, Integer>();
						}
					}else{// if not... just initialize to empty..
						numOfOccuranceOfType = new HashMap<String, Integer>();
					}					
				}
				//Place event in the list of tagged events
				identifiedEvents.put(allEvents.get(i).getEventName(), true);
				commonEventParameters.put(count, numOfOccuranceOfType);
				allEvents.get(i).eventGroup = count;
				
				//Time to identify the other events that have the same number of parameters and types...
				//Loop through each event that has not been tagged yet...
				for(int j = i; j < allEvents.size(); j++){
					//If the event has not been tagged yet...
					if(!identifiedEvents.containsKey(allEvents.get(j).getEventName())){
						HashMap<String, Integer> numOfOccuranceOfTypeTemp;
						int numOfParametersTemp = 0;
						//First get all the Types and the number of times that they appear... 
						if(allEvents.get(j).isSpecialEvent()){
							numOfOccuranceOfTypeTemp = listAllTypes(allEvents.get(j));	
							//First get the number of parameters...
							for(String type: numOfOccuranceOfTypeTemp.keySet()){
								numOfParametersTemp += numOfOccuranceOfTypeTemp.get(type);
							}					
						}else {
							//First get the number of parameters...
							if(allEvents.get(j).getParameters() != null){
								numOfParametersTemp = allEvents.get(j).getParameters().getParameterTypes().size();
								//List all the types and how many there are of each..
								if(numOfParametersTemp > 0){ //If there are any extra parameters.... populate..
									numOfOccuranceOfTypeTemp = pupulateForTypes(allEvents.get(j), allEvents.get(j).getParameters());
								}else{// if not... just initialize to empty..
									numOfOccuranceOfTypeTemp = new HashMap<String, Integer>();
								}
							}else{// if not... just initialize to empty..
								numOfOccuranceOfTypeTemp = new HashMap<String, Integer>();
							}			
						}
						
						//	First we check if it has the same number of extra parameters...				
						if(numOfParameters == numOfParametersTemp){
							if(numOfOccuranceOfType.size() == numOfOccuranceOfTypeTemp.size()){
								//if yes... loop through and identify if they are all the same types and num of occurance..
								boolean status = true;
								for(String type : numOfOccuranceOfType.keySet()){
									if(!numOfOccuranceOfTypeTemp.containsKey(type)){
										status = false;
										break;
									}
									if(numOfOccuranceOfType.get(type) != numOfOccuranceOfTypeTemp.get(type)){
										status = false;
										break;										
									}									
								}
								// if yes.... add to commonEvents...
								if(status){
									identifiedEvents.put(allEvents.get(j).getEventName(), true);
									allEvents.get(j).eventGroup = count;
								}
							}						
						}
					}
				}			
				count++;
			}			
		}						
		commonEvents = commonEventParameters;
		return commonEventParameters;
	}

	//Groups all actions linked to events (according to rules...)
	public HashMap<Event, String> groupEventsWithTheirActions(HashMap<Integer, HashMap<String,Integer>> commonEvents, Rules rules, Conditions conditions, Actions actions){
		HashMap<Event, String>  eventInstrumentationCode = new HashMap<Event,String>();
		boolean atLeastOneRule = false;
		//for every event defined...
		for(Event event: allEvents){
			//System.out.println("Event: "+event.getEventName());			
			atLeastOneRule = false;
			String eventActions = "";
			//And there is at least one rule that references it....
			for(Rule rule: rules.getRules()){
				//System.out.println("We want to check if "+event.getEventName()+" is present inside "+rule.getRuleName());
				//If the rule makes use of this event.........
				if(existsInsideEvent(event,rule.getRuleEvent())){						
					//System.out.println(event.getEventName()+"And it said yes...for rule: "+rule.getRuleName());
					//write its application side code... 
					eventActions += rule.generateActions(commonEvents.get(event.eventGroup),this,conditions, actions);
					//System.out.println("Action: "+eventActions);
					atLeastOneRule = true;
				}
			}
			//If the event is called in atleast one rule... we add it to the list...
			if(atLeastOneRule){
				//System.out.println("Event that was identified: "+event.getEventName());
				eventInstrumentationCode.put(event, eventActions);
			}
		}
		return eventInstrumentationCode;
	}
	
	//Checks if event exists inside eventName (takes care of nested event calls)
	public String getAllWhereCodeForEvent(Event event, String startingEventName){
		String text = "\n";
		//If the event name is equal to that of the event defined in the rule... return true
		if(event.getEventName().equals(startingEventName)){
			Event tmpEvent = allEventsHash.get(startingEventName);
			if(tmpEvent.getParameters() != null){
				for(String param: tmpEvent.getParameters().getParameterNames()){
					//System.out.println("Parameter Name: "+param+ " for event Name: "+event.getEventName());
					text += tmpEvent.getParameters().getParameterType(param)+" "+param+";\n";
				}	
				if(tmpEvent.getWhereCode() != null)	text += tmpEvent.getWhereCode()+"\n";		
			}					
			return text;
		}else{// if not... check if the event is a special event...
			//If yes...  take each event representation... 
			Event tmpEvent = allEventsHash.get(startingEventName);
			
			if(tmpEvent.getParameters() != null){					
				for(String param: tmpEvent.getParameters().getParameterNames()){
					//System.out.println("Parameter Name2: "+param+ " for event Name: "+event.getEventName());
					text += tmpEvent.getParameters().getParameterType(param)+" "+param+";\n";
				}
				if(tmpEvent.getWhereCode() != null) text += tmpEvent.getWhereCode()+"\n";	
			}
			for(int i = 0; i < tmpEvent.getEventRepresentationSize(); i++){
				//and if it is a special event...
				if(tmpEvent.getEventRepresentation(i).isSpecialEvent){
						//call the method again to check.. and pass on the same name...
					text += getAllWhereCodeForEvent(event,tmpEvent.getEventRepresentation(i).getEventName().split("\\(")[0]);
					return text;
				}				
			}
		}			
		return text;
	}
	
	//Generates all the needed code for the extra parameters
	public String getAllWhereCode(Event event, Rules rules){
		String text = "";		
		//For every rule...
		for(Rule rule: rules.getRules()){
			//if the rules event calls event.. 
			if(existsInsideEvent(event,rule.getRuleEvent())){
				//System.out.println("Found that rule: "+rule.getRuleName()+" makes use of event: "+event.getEventName());
				//Get their where code.. 
				text+= getAllWhereCodeForEvent(event,rule.getRuleEvent());
				break;
			}			
		}
		text+= "\n";
		return text;
	}
	
}
