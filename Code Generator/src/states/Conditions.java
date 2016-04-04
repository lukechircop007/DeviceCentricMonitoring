package states;

import java.util.HashMap;

public class Conditions {
	
	HashMap<String, ConditionBasedOnTimers> timerBasedConditions = null;
	HashMap<String, ConditionNormal> normalConditions = null;
	
	public Conditions(){
		timerBasedConditions = new HashMap<String, ConditionBasedOnTimers>();
		normalConditions = new HashMap<String, ConditionNormal>();		
	}
	
	//Setters
	public void addConditionBasedOnTimers(ConditionBasedOnTimers condition){
		timerBasedConditions.put(condition.getConditionName(), condition);
	}
	
	public void addNormalCondition(ConditionNormal condition){
		normalConditions.put(condition.getConditionName(), condition);
	}
	
	//Getters
	public ConditionBasedOnTimers getTimerBasedCondition(String conditionName){
		return timerBasedConditions.get(conditionName);
	}
	
	public ConditionNormal getNormalCondition(String conditionName){
		return normalConditions.get(conditionName);
	}
	
	//CodeGeneration
	
	//Returns if the condition name is a normal condition
	public boolean isNormalCondition(String conditionName){
		return normalConditions.containsKey(conditionName);	
	}
	
	
}
