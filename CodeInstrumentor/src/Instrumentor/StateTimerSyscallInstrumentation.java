package Instrumentor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class StateTimerSyscallInstrumentation {

	private String instrumentationCodeLocation;
	private String rvInstrumentationLocC;
	private String rvInstrumentationLocJava;
	
	//States paths...
	private String RVStates = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/RVStates.java";
	private String RVStatesLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/RVStates.java";
	//Timer paths...
	private String IterableList = instrumentationCodeLocation+"/RuntimeMonitor/Timers/IterableList.java";
	private String IterableListLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/IterableList.java";
	private String RvTimer = instrumentationCodeLocation+"/RuntimeMonitor/Timers/RVTimers.java";
	private String RvTimerLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/RVTimers.java";
	private String Timer = instrumentationCodeLocation+"/RuntimeMonitor/Timers/Timer.java";
	private String TimerLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/Timer.java";
	private String TimerManager = instrumentationCodeLocation+"/RuntimeMonitor/Timers/TimerManager.java";
	private String TimerManagerLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/TimerManager.java";
	//Syscall paths...
	private String syscallJava = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/RVSyscall.java";
	private String syscallJavaLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/RVSyscall.java";
	private String syscallC = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/syscallrv.c";
	private String syscallCLoc = rvInstrumentationLocC+"/com/java/runtimeverification/syscallrv.c";
	
	
	public StateTimerSyscallInstrumentation(String instrumentationCodeLocation, String rvInstrumentationLocC, String rvInstrumentationLocJava){
		this.instrumentationCodeLocation = instrumentationCodeLocation;
		this.rvInstrumentationLocC = rvInstrumentationLocC;
		this.rvInstrumentationLocJava = rvInstrumentationLocJava;	
		setupVariables();
	}
	
	public void setupVariables(){
		//States paths...
		RVStates = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/RVStates.java";
		RVStatesLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/RVStates.java";
		//Timer paths...
		IterableList = instrumentationCodeLocation+"/RuntimeMonitor/Timers/IterableList.java";
		IterableListLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/IterableList.java";
		RvTimer = instrumentationCodeLocation+"/RuntimeMonitor/Timers/RVTimers.java";
		RvTimerLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/RVTimers.java";
		Timer = instrumentationCodeLocation+"/RuntimeMonitor/Timers/Timer.java";
		TimerLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/Timer.java";
		TimerManager = instrumentationCodeLocation+"/RuntimeMonitor/Timers/TimerManager.java";
		TimerManagerLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/TimerManager.java";
		//Syscall paths...
		syscallJava = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/RVSyscall.java";
		syscallJavaLoc = rvInstrumentationLocJava+"/com/java/runtimeverification/RVSyscall.java";
		syscallC = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/syscallrv.c";
		syscallCLoc = rvInstrumentationLocC+"/com/java/runtimeverification/syscallrv.c";		
	}
		
	private String readAllText(File data) throws IOException{
		//Start Buffer
		BufferedReader file = new BufferedReader(new FileReader(data));
		String line;
		String input = "";
		//Read lines...
		while ((line = file.readLine()) != null){
		   	input += line + "\n";
		}
		//Close opened file.
		file.close();
	
		return input;
	}
	
	private boolean writeFile(File file, String output) throws IOException{
		if(!file.exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream os = new FileOutputStream(file);
	    os.write(output.getBytes());

	    os.close();
		return false;
	}
	
	public boolean instrumentStates(){
		//RVStates.java
		try {
			String input = readAllText(new File(RVStates));				
			writeFile(new File(RVStatesLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write States.java: "+e.getMessage());
			System.exit(1);
		}
		return true;
	}
	
	public boolean instrumentTimers(){
		//IterableList.java
		try {
			String input = readAllText(new File(IterableList));				
			writeFile(new File(IterableListLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write IterableList.java: "+e.getMessage());
			System.exit(1);
		}
		//RvTimer.java
		try {
			String input = readAllText(new File(RvTimer));				
			writeFile(new File(RvTimerLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write RVTimer.java: "+e.getMessage());
			System.exit(1);
		}
		//Timer.java
		try {
			String input = readAllText(new File(Timer));				
			writeFile(new File(TimerLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write Timer.java: "+e.getMessage());
			System.exit(1);
		}
		//TimerManager.java
		try {
			String input = readAllText(new File(TimerManager));				
			writeFile(new File(TimerManagerLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write TimerManager.java: "+e.getMessage());
			System.exit(1);
		}
		
		return true;
	}
	
	public boolean instrumentSyscalls(){
		//syscallJava.java
		try {
			String input = readAllText(new File(syscallJava));				
			writeFile(new File(syscallJavaLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write syscallJava.java: "+e.getMessage());
			System.exit(1);
		}
		//syscallC.java
		try {
			String input = readAllText(new File(syscallC));				
			writeFile(new File(syscallCLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write syscallC.java: "+e.getMessage());
			System.exit(1);
		}
		
		return true;
	}
	
}
