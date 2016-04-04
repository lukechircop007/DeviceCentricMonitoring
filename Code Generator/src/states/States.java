package states;

import java.util.ArrayList;

public class States {

	private ArrayList<State> applicationSideStates = null;
	private ArrayList<State> kernelSideStates = null;
	
	public States(){
		applicationSideStates = new ArrayList<State>();
		kernelSideStates = new ArrayList<State>();
	}
		
	//Setters
	public void addKernelSideState(State state){kernelSideStates.add(state);}
	public void addApplicationSideState(State state){applicationSideStates.add(state);}	
		
	//Getters	
	public ArrayList<State> getKernelSideStates(){return this.kernelSideStates;}
	public ArrayList<State> getApplicationSideStates(){return this.applicationSideStates;}	
	
	//Code Generation
	//List all states defined for the kernel monitor
	public String toKernelCCode(){
		String text = "";		
		for(State state: kernelSideStates)text+= state.toCode();				
		return text+"\n";
	}
	
	//List all States defined for applicationSide Monitors
	public String toJavaCode(String imports){
		String text = "package com.java.runtimeverification;\n"
				//+ imports+ "\n"
				+ "public class RVStates {\n\npublic RVStates(){}\n\n";	
		//Paste states codes.. 		
		for(State state: applicationSideStates)text+= state.toCode();			
		return text+"}\n";
	}
	
}
