package states;

public class State {
	
	private String stateType;
	private String stateName;
	
	private String stateDeclaration;
	private String stateLoad;
	private String stateSave;
	
	//Setters
	public void setStateType(String stateType){this.stateType = stateType;}	
	public void setStateName(String stateName){this.stateName = stateName;}	
	public void setStateDeclaration(String stateDeclaration){this.stateDeclaration = stateDeclaration;}
	public void setStateLoad(String stateLoad){this.stateLoad = stateLoad;}
	public void setStateSave(String stateSave){this.stateSave = stateSave;}
	
	//Getters
	public String getStateType(){return this.stateType;}
	public String getStateName(){return this.stateName;}	
	public String getStateDeclaration(){return this.stateDeclaration;}	
	public String getStateLoad(){return this.stateLoad;}	
	public String getStateSave(){return this.stateSave;}
	
	//Code Generation...
	public String toCode(){
		String text = "";		
		text += stateDeclaration+ "\n";		
		return text;
	}
	
}
