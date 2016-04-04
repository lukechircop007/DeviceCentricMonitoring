package states;

import java.util.HashMap;

public class Actions {

	HashMap<String, Action> normalActions = null;
	
	public Actions(){
		normalActions = new HashMap<String, Action>();
	}
	
	//Setters
	public void addNormalAction(Action normalAction){
		normalActions.put(normalAction.actionName, normalAction);
	}
	
	
	//Getters
	public Action getNormalAction(String actionName){
		if(!normalActions.containsKey(actionName)){
			System.out.println("Warning: action with name: "+actionName+" defined in rules not found! ");
		}
		return normalActions.get(actionName);
	}
	
	public HashMap<String,Action> getAllActions(){
		return this.normalActions;
	}
	
	//Generates the actions for the timer events....
	public String generateTimerActions(HashMap<Integer, HashMap<String,Integer>> commonEvent, Events events,Conditions conditions, Rules rules){
		String text = "";
		
		//For every event that is a timer event... or a special event that has a timer event..
		for(Event event: events.getallEventS()){
			if(!event.isSpecialEvent() && event.getEventRepresentations().get(0).isTimerEvent){
				//boolean exists = false;
				String action = "";
				// copy the actions of all the rules that it is defined in.. 
				for(Rule rule: rules.getRules()){
					if(events.existsInsideEvent(event,  rule.getRuleEvent())){
						//exists = true;
						action += "\n"+rule.generateActions(commonEvent.get(event.eventGroup), events,conditions, this);
					}					
				}
				// Generate condition and add action... 
				text+= "\nif(name.equals(\""+event.getEventRepresentation(0).getClassName()+"\")){\n";
				text += "long sm; \n";			
				//To add the Code To generate Where code... 		
				text += events.getAllWhereCode(event, rules);				
				text+= "\n "+action+"\n";
				if(event.getEventRepresentation(0).getInstanceName().equals("@%")){
					text+= "\n this.reset();";
				}
				text+= "\n}";
			}
		}
		
		return text;
	}
}
