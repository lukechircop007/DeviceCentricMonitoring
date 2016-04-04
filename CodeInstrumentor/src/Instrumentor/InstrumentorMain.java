package Instrumentor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import states.ClassRepresentation;

public class InstrumentorMain {


	//private String settingsLoc = "Settings.txt";
	HashMap<String,String> settingsList;
	
	public InstrumentorMain(){
	}
	
	public HashMap<String,String> loadSettings() throws IOException{
		//Need to import locations of all the classes that we can instrument... 
		File settings = new File("Settings.txt");
		settingsList = new HashMap<String,String>();
				
		if(settings.exists()){
			try {				
				BufferedReader file = new BufferedReader(new FileReader(settings));
				String line;
				//Read lines...
				while ((line = file.readLine()) != null){
					String fileLocation = "";
					String temp = line.split("=")[1];
					
					boolean quote = false;
					for(int i =0; i<temp.length();i++){
						if(temp.charAt(i) == '\"'){
							if(quote) break;
							quote = true;
						}else if(quote){
							fileLocation+= temp.charAt(i);
						}					
					}				
					settingsList.put(line.split("=")[0].replace(" ", ""),fileLocation);
				}
				//Close opened file.
				file.close();
				//System.out.println("Loaded Settings containing: "+settingsList.size()+" Locations");
			} catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
			}	
		}
		return settingsList;
	}
	
	public boolean startInstrumentation(ClassRepresentation clsRep){
		System.out.println("Starting Kernel Instrumentation");		
		KernelSideInstrumentation kernel = new KernelSideInstrumentation(settingsList.get("instrumentationCodeLocation"),settingsList.get("kernelCodeLocation"));
		kernel.instrumentKernel();
		System.out.println("Kernel instrumentation sucessfull\n");
		
		System.out.println("Starting States Instrumentation");
		StateTimerSyscallInstrumentation stmr= new StateTimerSyscallInstrumentation(settingsList.get("instrumentationCodeLocation"),settingsList.get("rvInstrumentationLocC"),settingsList.get("rvInstrumentationLocJava"));
		stmr.instrumentStates();
		System.out.println("States instrumentation sucessfull\n");
		
		System.out.println("Starting Timers Instrumentation");
		stmr.instrumentTimers();
		System.out.println("States instrumentation sucessfull\n");

		System.out.println("Starting RVSyscall instrumentation");
		stmr.instrumentSyscalls();
		System.out.println("RVSyscall instrumentation sucessfull\n");
		
		System.out.println("Starting AppSide instrumentation");
		ApplicationSideInstrumentation appSide = new ApplicationSideInstrumentation(settingsList.get("instrumentationCodeLocation"));
		appSide.instrumentCode(settingsList, clsRep);
		System.out.println("AppSide instrumentation sucessfull\n");
		return false;
	}
		
	private void updateLine(File data, String toUpdate, String updated) throws IOException {
	    BufferedReader file = new BufferedReader(new FileReader(data));
	    String line;
	    String input = "";

	    while ((line = file.readLine()) != null){
	    	if(line.contains("//@Instrumentation before:") || line.contains("\\@Instrumentation after:")){
	    		//loop untill you find //@END
	    		input += line + "\n";
	    		while ((line = file.readLine()) != null && !line.contains("//@Instrumentation End"));
	    	}
	    	input += line + "\n";
	    }

	    input = input.replace(toUpdate, updated);

	    FileOutputStream os = new FileOutputStream(data);
	    os.write(input.getBytes());

	    file.close();
	    os.close();
	}

	
}
