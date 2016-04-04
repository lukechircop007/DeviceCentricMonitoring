package states;

public class ConditionBasedOnTimers {

	private String conditionName;
	private String timerName;
	private int conditionValue;
	
	
	//Setters
	public void setConditionValue(int value){this.conditionValue = value;}	
	public void setTimerName(String timerName){this.timerName = timerName;}	
	public void setConditionName(String conditionName){this.conditionName = conditionName;}	
	public void setTimerBasedCondition(String conditionName, String timerName, int conditionValue){
		this.conditionName = conditionName;
		this.timerName = timerName;
		this.conditionValue = conditionValue;	
	}
	
	//Getters
	public String getConditionName(){return this.conditionName;}
	public String getTimerName(){return this.timerName;}
	public int getConditionValue(){return this.conditionValue;}
	
	//Generates timer based condition
	public String getTimerBasedCondition(){
		return "timerUnder("+timerName+", (long)"+conditionValue+")";
	}
	
}
