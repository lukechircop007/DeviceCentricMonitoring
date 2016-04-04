package codeGeneration;

import java.io.File;

public class FileCreation {

	private static String kernelCodeLoc = "RuntimeMonitor/KernelSide/";
	private static String kernelInsertionCodeLoc = "RuntimeMonitor/KernelSide/";
	private static String applicationCodeLoc = "RuntimeMonitor/ApplicationSide/";	
	private static String timerFramework = "RuntimeMonitor/Timers/";
	
	//Creates sys_rv_monitor.c
	public static File createKernelFile(){
		File kernelCode = new File(kernelCodeLoc);
		
		if(!kernelCode.exists()){
			boolean kern = kernelCode.mkdirs();
			System.out.println("Generation of KernelFile: "+kern);				
		}
		
		kernelCode = new File(kernelCodeLoc + "sys_rv_monitor.c");
		
		if(!kernelCode.exists()){
			try {
				kernelCode.createNewFile();		
			} catch (Exception e) {
				System.out.println("Error :"+e.getMessage());
			}	
		}
		return kernelCode;
	}
	
	//Creates calls.S
	public static File createCALLSFile(){
		File callsCode = new File(kernelInsertionCodeLoc + "calls.S");
		
		if(!callsCode.exists()){
			try {
				callsCode.createNewFile();		
			} catch (Exception e) {
				System.out.println("Error :"+e.getMessage());
			}	
		}
		return callsCode;
	}
	
	//Creates Syscalls.h
	public static File createSyscallsFile(){
		File syscallsCode = new File(kernelInsertionCodeLoc + "syscalls.h");
		
		if(!syscallsCode.exists()){
			try {
				syscallsCode.createNewFile();		
			} catch (Exception e) {
				System.out.println("Error :"+e.getMessage());
			}	
		}
		return syscallsCode;
	}	
	
	//Creates unisdt.h
	public static File createUnistdFile(){
		File unistdCode = new File(kernelInsertionCodeLoc + "unistd.h");
		
		if(!unistdCode.exists()){
			try {
				unistdCode.createNewFile();		
			} catch (Exception e) {
				System.out.println("Error :"+e.getMessage());
			}	
		}
		return unistdCode;
	}
	
	//Creates application side files..
	public static File createAppSideFile(String fileName){
		File appCode = new File(applicationCodeLoc);
		
		if(!appCode.exists()){
			boolean kern = appCode.mkdirs();
			System.out.println("Generation of AppFile: "+kern);				
		}
		
		appCode = new File(applicationCodeLoc+fileName);
		
		if(!appCode.exists()){
			try {
				appCode.createNewFile();		
			} catch (Exception e) {
				System.out.println("Error :"+e.getMessage());
			}	
		}
		return appCode;		
	}
	
	//Creates Timer side files... 
	public static File createTimerFile(String fileName){
		File tmrCode = new File(timerFramework);
		
		if(!tmrCode.exists()){
			boolean kern = tmrCode.mkdirs();
			System.out.println("Generation of AppFile: "+kern);				
		}
		
		tmrCode = new File(timerFramework+fileName);
		
		if(!tmrCode.exists()){
			try {
				tmrCode.createNewFile();		
			} catch (Exception e) {
				System.out.println("Error :"+e.getMessage());
			}	
		}
		return tmrCode;		
	}
	
}
