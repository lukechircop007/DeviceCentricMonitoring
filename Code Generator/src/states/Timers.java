package states;

import java.util.HashMap;

public class Timers {

	private HashMap<String, Integer> timerNameAppSide;
	private HashMap<String, Integer> timerNameKernelSide;
		
	public Timers(){
		timerNameAppSide = new HashMap<String, Integer>();	
		timerNameKernelSide = new HashMap<String, Integer>();		
	}
	
	//Setters
	public void addTimerNameApp(String timerName){
		this.timerNameAppSide.put(timerName, 0);		
	}
	public void addTimerNameKernel(String timerName){
		this.timerNameKernelSide.put(timerName, 0);		
	}
	
	public boolean addTimerValue(String timerName, int value){
		if(this.timerNameAppSide.containsKey(timerName)){
			this.timerNameAppSide.put(timerName, value);
			if(this.timerNameKernelSide.containsKey(timerName)){
				this.timerNameKernelSide.put(timerName, value);
			}
			return true;
		}
		if(this.timerNameKernelSide.containsKey(timerName)){
			this.timerNameKernelSide.put(timerName, value);
			if(this.timerNameAppSide.containsKey(timerName)){
				this.timerNameAppSide.put(timerName, value);
			}
			return true;
		}
		
		return false;
	}
	
	//Getters
	
	public int getTimerValueKernel(String timerName){
		return this.timerNameKernelSide.get(timerName);
	}
	
	public HashMap<String,Integer> getTimerNameListKernel(){
		return this.timerNameKernelSide;
	}
	public int getTimerValueApp(String timerName){
		return this.timerNameAppSide.get(timerName);
	}
	
	public HashMap<String,Integer> getTimerNameListApp(){
		return this.timerNameAppSide;
	}
	
	//code Generation... 
	
}
