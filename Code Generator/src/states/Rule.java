package states;

import java.util.ArrayList;
import java.util.HashMap;

public class Rule {

	private String ruleName;
	private String ruleEvent;
	private ArrayList<String> ruleCondition = null;
	private ArrayList<String> ruleAction = null;
	
	public Rule(){
		ruleAction = new ArrayList<String>();
	}
	
	//Setters
	public void setRuleName(String ruleName){this.ruleName = ruleName;}	
	public void setRuleEvent(String ruleEvent){this.ruleEvent = ruleEvent;}	
	public void setRuleCondition(ArrayList<String> ruleCondition){this.ruleCondition = ruleCondition;}	
	public void addRuleAction(String ruleAction){this.ruleAction.add(ruleAction);}
		
	//Getters
	public String getRuleName(){return this.ruleName;}	
	public String getRuleCondition(int position){return this.ruleCondition.get(position);}	
	public String getRuleEvent(){return this.ruleEvent;}	
	public String getRuleAction(int position){return this.ruleAction.get(position);}	
	public ArrayList<String> getRuleActions(){return this.ruleAction;}
	
	// KernelSide Code Generation..
	
	//Generates the prototype for kernelside action
	public String generateMethodName(HashMap<String,Integer> numOfOccuranceForTypes, Events events){ 
		return "long "+ruleName+"("+events.getEvent(ruleEvent.split("\\(")[0]).generateParamListKernelSide(numOfOccuranceForTypes, events)+")";
	}

	//Generates KernelSide conditions 
	public String getConditionsForKernelSideMethod(Conditions conditions){
		String text = "";
		
		if(ruleCondition != null){
			boolean okAnd = false;
			//For every condition specified in the rule
			for(int i = 0; i < ruleCondition.size(); i++){
				String temp;
				if((temp = conditions.getNormalCondition(ruleCondition.get(i)).getKernelSideConditions()) != null){
					if(okAnd){text+=" && ";}					
					text+= temp;
					okAnd =true;
				}
			}
		}
		if(text.equals(""))return "true";
		return text;
	}
	
	//Generates KernelSide conditions 
	public String getConditionsForAppSideMethodOnly(Conditions conditions){
		String text = "";
		
		if(ruleCondition != null){
			boolean okAnd = false;
			//For every condition specified in the rule
			for(int i = 0; i < ruleCondition.size(); i++){
				
				String temp;
				//If the condition is a normal one...
				if(conditions.isNormalCondition(ruleCondition.get(i))){
					//is not empty and does not have kernel side conditions...
					if(conditions.getNormalCondition(ruleCondition.get(i)).getKernelSideConditions() == null && (temp = conditions.getNormalCondition(ruleCondition.get(i)).getApplicationSideConditions()) != null){
						if(okAnd){text+=" && ";}					
					text+= temp;
					okAnd =true;
					}	
				}else{ 
					if(conditions.timerBasedConditions.containsKey(ruleCondition.get(i)) == false){
						System.out.println("Error: Condition: \""+ruleCondition.get(i)+"\" defined in Rule: "+getRuleName()+" could not be Found in Conditions...");
						System.exit(1);
					}					
					if((temp = conditions.getTimerBasedCondition(ruleCondition.get(i)).getTimerBasedCondition()) != null){
						if(okAnd){text+=" && ";}					
					text+= temp;
					okAnd =true;
					}
				}
			}
		}
		if(text.equals(""))return "true";
		return text;
	}
		
	//Generates KernelSide conditions 
	public String getConditionsForAppSideMethod(Conditions conditions){
			String text = "";
			
			if(ruleCondition != null){
				boolean okAnd = false;
				//For every condition specified in the rule
				for(int i = 0; i < ruleCondition.size(); i++){
					
					String temp;
					//If the condition is a normal one...
					if(conditions.isNormalCondition(ruleCondition.get(i))){
						//is not empty and does not have kernel side conditions...
						if(conditions.getNormalCondition(ruleCondition.get(i)).getKernelSideConditions() != null && (temp = conditions.getNormalCondition(ruleCondition.get(i)).getApplicationSideConditions()) != null){
							if(okAnd){text+=" && ";}					
						text+= temp;
						okAnd =true;
						}	
					}else{ 
						if(conditions.timerBasedConditions.containsKey(ruleCondition.get(i)) == false){
							System.out.println("Error: Condition: \""+ruleCondition.get(i)+"\" defined in Rule: "+getRuleName()+" could not be Found in Conditions...");
							System.exit(1);
						}					
						if((temp = conditions.getTimerBasedCondition(ruleCondition.get(i)).getTimerBasedCondition()) != null){
							if(okAnd){text+=" && ";}					
						text+= temp;
						okAnd =true;
						}
					}
				}
			}
			if(text.equals(""))return "true";
			return text;
		}
	
	//Generates the action code for a particular rule... 	
	public String getActionsForRule(Actions actions){
		String text ="";		
		//For every Action defined in the rule.....
		for(String action: ruleAction){
			//First we check if it has kernel side code... and if it does...
			if(actions.getNormalAction(action).kernelSideAction != null){
				//Add it to the text ...
				text+= "\nif(strcmp(action_name,\""+actions.getNormalAction(action).getActionName()+"\") == 0){";
				text+= "\n"+actions.getNormalAction(action).kernelSideAction;
				text+= "\n}\n";
			}
		}		
		return text;
	}
	
	//Generates the method name and conditions for the actions to execute.. 
	public String generateMethod(HashMap<Integer, HashMap<String,Integer>> commonEvent, Events events, Conditions conditions,Actions actions){
		String text = "\n";		
		text+= generateMethodName(commonEvent.get(events.getEvent(ruleEvent.split("\\(")[0]).eventGroup), events)+"{ \n";
		text+= "if("+getConditionsForKernelSideMethod(conditions)+"){";		
		text+= getActionsForRule(actions);			
		text+="\n}\n";
		text+="return -1; \n}";
		return text;
	}

	public String generateActions(HashMap<String,Integer> numOfOccuranceForTypes, Events events, Conditions conditions, Actions actions){
		String text = "";
		
		//Generate conditions 
		text+= "if("+getConditionsForAppSideMethodOnly(conditions)+"){\n";		
		
		//For every action defined in the rule...
		for(String action: ruleAction){
			//System.out.println("Rule action: "+action);
			//Get the action contents...
			Action acc = actions.getNormalAction(action);
			//If it is not a timer...
			if(acc.timerAction == null){
				//check if there is kernel side code...
				//If not... and the application side code is not empty.. 
				if(acc.kernelSideAction == null && acc.applicationSideAction != null){
					//Include it in the text
					text+= ""+acc.getApplicationSideAction()+"\n";
				}else{// if there 
					// add systemcall....  add int sm...
					if(events.getEvent(getRuleEvent().split("\\(")[0]).getParameters().getParameterTypes().size() >0){
						text+= "sm = RVSyscall.sysCall_"+events.getEvent(getRuleEvent().split("\\(")[0]).eventGroup+"(\""+getRuleEvent().split("\\(")[0]+"\", \""+action+"\" "+events.getEvent(getRuleEvent().split("\\(")[0]).generateExtraParamVarNameListForAppSide(numOfOccuranceForTypes, events)+");\n";// to add system call....
					}else{
						text+= "sm = RVSyscall.sysCall_"+events.getEvent(getRuleEvent().split("\\(")[0]).eventGroup+"(\""+getRuleEvent().split("\\(")[0]+"\", \""+action+"\");\n";
					}			
					
					text+= "if("+getConditionsForAppSideMethod(conditions)+"){\n";
					// add application side code...	
					if(acc.getApplicationSideAction() != null)
						text+= acc.getApplicationSideAction()+"\n";		
					text += "}\n";
				}
			}else{//If the action is a timer based action..
				String[] timerAction = acc.getTimerAction().split("\\(");
				if(timerAction[0].equals("RV:timerPause")){	
					text+= "RVTimers.pauseTimer(\""+timerAction[1].replace(")", "")+"\");\n";					
				}else if(timerAction[0].equals("RV:timerResume")){
					text+= "RVTimers.resumeTimer(\""+timerAction[1].replace(")", "")+"\");\n";					
				}else if(timerAction[0].equals("RV:timerReset")){
					text+= "RVTimers.resetTimer(\""+timerAction[1].replace(")", "")+"\");\n";					
				}else if(timerAction[0].equals("RV:timerOff")){
					text+= "RVTimers.timerOff(\""+timerAction[1].replace(")", "")+"\");\n";					
				}
			}
		}
		text+= "}\n";
		return text;
	}
}
