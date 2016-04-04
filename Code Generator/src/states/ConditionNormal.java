package states;

public class ConditionNormal {

	private String conditionName;
	private String kernelSideConditions = null;
	private String applicationSideConditions = null;
	
	//Setters
	public void setConditionName(String conditionName){
		this.conditionName = conditionName;
	}
	
	public void setKernelSideConditions(String kernelSideConditions){
		this.kernelSideConditions = kernelSideConditions;
	}
	
	public void setApplicationSideConditions(String applicationSideConditions){
		this.applicationSideConditions = applicationSideConditions;
	}
	
	//Getters
	public String getConditionName(){return this.conditionName;}
	public String getKernelSideConditions(){return this.kernelSideConditions;}
	public String getApplicationSideConditions(){return this.applicationSideConditions;}
	
	
}
