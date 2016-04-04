package Instrumentor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class KernelSideInstrumentation {

	private String instrumentationCodeLocation;
	private String kernelCodeLocation;
	
	private String SyscallMonitor = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/sys_rv_monitor.c";
	private String SyscallMonitorLoc = kernelCodeLocation+"/RuntimeMonitor/sys_rv_monitor.c";
	private String SyscallCalls = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/calls.S";
	private String SyscallCallsLoc = kernelCodeLocation+"/arch/arm/kernel/calls.S";
	private String Syscall = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/syscalls.h";
	private String SyscallLoc = kernelCodeLocation+"/include/linux/syscalls.h";
	private String SyscallUnistd = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/unistd.h";
	private String SyscallUnistdLoc = kernelCodeLocation+"/arch/arm/include/asm/unistd.h";
	
	public KernelSideInstrumentation(String instrumentationCodeLocation, String kernelCodeLocation){
		this.instrumentationCodeLocation = instrumentationCodeLocation;
		this.kernelCodeLocation = kernelCodeLocation;
		setupVariables();
	}
	
	public void setupVariables(){
		SyscallMonitor = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/sys_rv_monitor.c";
		SyscallMonitorLoc = kernelCodeLocation+"/RuntimeMonitor/sys_rv_monitor.c";
		SyscallCalls = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/calls.S";
		SyscallCallsLoc = kernelCodeLocation+"/arch/arm/kernel/calls.S";
		Syscall = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/syscalls.h";
		SyscallLoc = kernelCodeLocation+"/include/linux/syscalls.h";
		SyscallUnistd = instrumentationCodeLocation+"/RuntimeMonitor/KernelSide/unistd.h";
		SyscallUnistdLoc = kernelCodeLocation+"/arch/arm/include/asm/unistd.h";		
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
	
	private String loadKernelModule(File data) throws IOException{
		//Start Buffer
		BufferedReader file = new BufferedReader(new FileReader(data));
	    String line;
	    String input = "";
	    //Read lines...
	    while ((line = file.readLine()) != null){
	    	//If we encounter the instrumetnation tags...
	    	if(line.contains("//@Instrumentation Start")){
	    		input += line + "\n";
	    		//loop until you find the end and discard what you read... 
	    		while ((line = file.readLine()) != null && !line.contains("//@Instrumentation End"));
	    	}
	    	input += line + "\n";
	    }
	    //Close opened file.
	    file.close();
	
		return input;
	}
	
	private boolean writeFile(File file, String output) throws IOException{
		FileOutputStream os = new FileOutputStream(file);
	    os.write(output.getBytes());

	    os.close();
		return false;
	}
	
	public boolean instrumentKernel(){
		
		//System call monitor... nothing to replace so just read and write... 
		try {
			String input = readAllText(new File(SyscallMonitor));	
			writeFile(new File(SyscallMonitorLoc),input);			
		} catch (Exception e) {
			System.out.println("Error While trying to write Monitor: "+e.getMessage());
			System.exit(1);
		}
		
		//Calls.s
		try {
			String textToReplace = readAllText(new File(SyscallCalls));	
			String input = loadKernelModule(new File(SyscallCallsLoc));			
			writeFile(new File(SyscallCallsLoc),input.replace("//@Instrumentation Start", "//@Instrumentation Start\n"+textToReplace));			
		} catch (Exception e) {
			System.out.println("Error While trying to write Calls.S: "+e.getMessage());
			System.exit(1);
		}
		//Syscalls.h
		try {
			String textToReplace = readAllText(new File(Syscall));	
			String input = loadKernelModule(new File(SyscallLoc));			
			writeFile(new File(SyscallLoc),input.replace("//@Instrumentation Start", "//@Instrumentation Start\n"+textToReplace));			
		} catch (Exception e) {
			System.out.println("Error While trying to write Syscalls.h: "+e.getMessage());
			System.exit(1);
		}
		
		//unistd.h
		try {
			String textToReplace = readAllText(new File(SyscallUnistd));	
			String input = loadKernelModule(new File(SyscallUnistdLoc));			
			writeFile(new File(SyscallUnistdLoc),input.replace("//@Instrumentation Start", "//@Instrumentation Start\n"+textToReplace));			
		} catch (Exception e) {
			System.out.println("Error While trying to write unistd.h: "+e.getMessage());
			System.exit(1);
		}
				
		return true;
	}
	
}
