package states;

import java.util.ArrayList;

public class Imports {

	private ArrayList<String> kernelImports = new ArrayList<String>();
	private ArrayList<String> applicationImports = new ArrayList<String>();
	
	//Setters
	public void addKernelImport(String kernelImport){kernelImports.add("#"+kernelImport);}	
	public void addApplicationImport(String applicationImport){applicationImports.add(applicationImport);}
	
	//Getters
	public ArrayList<String> getKernelImports(){return kernelImports;}	
	public ArrayList<String> getApplicationImports(){return applicationImports;}
	
	//List all imports defined for C code...
	public String toCCode(){
		String text = "#include <asm/unistd.h>\n"
		+ "		#include <asm/segment.h>\n"
		+ "		#include <asm/uaccess.h>\n"
		+ "		#include <linux/linkage.h>\n"
		+ "		#include <linux/string.h>\n";
		for(int i =0; i< kernelImports.size(); i++)text+= kernelImports.get(i);
		return text;
	}
	
	//List all imports defined for Java code...
	public String toJavaCode(){
		String text = "import com.java.runtimeverification.RVSyscall;\n"
					+"import com.java.runtimeverification.RVStates;\n"
					+"import com.java.runtimeverification.RVTimers;\n";
		for(int i =0; i< applicationImports.size(); i++)text+= applicationImports.get(i);
		return text;		
	}
	
	//Standard... not really needed.. 
	public String toString(){
		String text = "";
		for(int i =0; i< kernelImports.size(); i++)text+= kernelImports.get(i);
		for(int i =0; i< applicationImports.size(); i++)text+= applicationImports.get(i);		
		return text;
	}
	
}
