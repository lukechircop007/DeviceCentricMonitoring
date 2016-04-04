package codeGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import states.Program;

public class CodeGenerationMain {

	public CodeGenerationMain(){}
	
	//KernelSide monitor Generation... and systemcall adding
	public boolean generateKernelSideCode(Program program, int syscalPos){
		File kernelCode = FileCreation.createKernelFile();		
		if(kernelCode.exists()){
			try {
				FileWriter fw = new FileWriter(kernelCode.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//First we write imports...
				text = program.getAllImports().toCCode();
				bw.write(text);
				//Then we write the actual code...
				text = program.getGlobal().generateKernelCode();
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write kernel code: "+e.getMessage());
			}		
		}
		
		File callsCode = FileCreation.createCALLSFile();		
		if(callsCode.exists()){
			try {
				FileWriter fw = new FileWriter(callsCode.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//Then we write the actual code...
				text = program.getGlobal().generateKernelCallCode();
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write calls.S code: "+e.getMessage());
			}			
		}
		
		File syscallsCode = FileCreation.createSyscallsFile();		
		if(syscallsCode.exists()){
			try {
				FileWriter fw = new FileWriter(syscallsCode.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//Then we write the actual code...
				text = program.getGlobal().generateKernelSyscallPrototypes();
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Syscall.h code: "+e.getMessage());
			}			
		}
		
		File unistdCode = FileCreation.createUnistdFile();		
		if(unistdCode.exists()){
			try {
				FileWriter fw = new FileWriter(unistdCode.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//Then we write the actual code...
				text = program.getGlobal().generateKernelUnistdCode(syscalPos);
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Unistd.h code: "+e.getMessage());
			}			
		}
		return true;
	}
	
	public boolean generateApplicationSideCode(Program program, int syscalPos){
		//Event Code Generation... 
		File syscallJava = FileCreation.createAppSideFile("RVSyscall.java");
		if(syscallJava.exists()){
			try {
				FileWriter fw = new FileWriter(syscallJava.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);					
				bw.write(program.getGlobal().generateRVSyscallJavaCode());
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Events code: "+e.getMessage());
			}				
		}	
				
		//Syscallrv.c Code Generation... 
		File syscallC = FileCreation.createAppSideFile("syscallrv.c");
		if(syscallC.exists()){
			try {
				FileWriter fw = new FileWriter(syscallC.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);					
				bw.write(program.getGlobal().generateSyscallFrameworkCCode(syscalPos));
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Syscallrv code: "+e.getMessage());
			}				
		}
		
		// Generate Imports... 		
		File importsJava = FileCreation.createAppSideFile("Imports.tmp");
		if(importsJava.exists()){
			try {
				FileWriter fw = new FileWriter(importsJava.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "/*Custom imports*/\n";
				//First we write imports...
				text += program.getAllImports().toJavaCode(); 
				text += "/*End of Custom imports*/";
				bw.write(text);
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Java Imports code: "+e.getMessage());
			}		
		}
		
		// Generate AppSideStates... 		
		File statesJava = FileCreation.createAppSideFile("RVStates.java");
		if(statesJava.exists()){
			try {
				FileWriter fw = new FileWriter(statesJava.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);	
				//Writing the states for application side.. 
				String text = program.getGlobal().getStates().toJavaCode(program.getAllImports().toJavaCode()); 
				bw.write(text);
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write RVStates code: "+e.getMessage());
			}		
		}
		
		//Event Code Generation... 
		File eventCode = FileCreation.createAppSideFile("EventInstrumentations.txt");
		if(eventCode.exists()){
			try {
				FileWriter fw = new FileWriter(eventCode.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);					
				bw.write(program.getGlobal().generateEventCode());
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Events code: "+e.getMessage());
			}				
		}	
		
		return false;
	}

	public boolean generateTimerFrameworkCode(Program program){
		System.out.println("Generating TimerManager.java...");
		//Generate TimerManager
		File timerManager = FileCreation.createTimerFile("TimerManager.java");
		
		if(timerManager.exists()){
			try {
				FileWriter fw = new FileWriter(timerManager.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//First we write imports...
				text = TimerCodeGeneration.generateTimerManagerCode();
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write TimerManager code: "+e.getMessage());
			}		
		}
		System.out.println("Generating IterableList.java...");
		
		File iterableList = FileCreation.createTimerFile("IterableList.java");
		
		if(iterableList.exists()){
			try {
				FileWriter fw = new FileWriter(iterableList.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//First we write imports...
				text = TimerCodeGeneration.generateIterableListCode();
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write IterableList code: "+e.getMessage());
			}		
		}

		System.out.println("Generating Timer.java...");
		File timer = FileCreation.createTimerFile("Timer.java");
		
		if(timer.exists()){
			try {
				FileWriter fw = new FileWriter(timer.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//First we write imports...
				text = TimerCodeGeneration.generateTimerCode(program);
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write Timer code: "+e.getMessage());
			}		
		}

		System.out.println("Generating RVTimer.java...");
		File timerFramework = FileCreation.createTimerFile("RVTimers.java");
		
		if(timerFramework.exists()){
			try {
				FileWriter fw = new FileWriter(timerFramework.getAbsolutePath());
				BufferedWriter bw = new BufferedWriter(fw);				
				String text = "";
				//First we write imports...
				text = TimerCodeGeneration.generateRVTimerFrameworkCode(program);
				bw.write(text);
				//When done just close file and that is it!
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while trying to write RVTimer code: "+e.getMessage());
			}		
		}
		return false;
	}
}
