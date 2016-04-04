package Instrumentor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import states.ClassRepresentation;
import states.InstrumentationEvent;
import states.Parameters;

public class ApplicationSideInstrumentation {

	private String instrumentationCodeLocation;
	private String importsLoc = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/Imports.tmp";
		
	public ApplicationSideInstrumentation(String instrumentationCodeLocation){
		this.instrumentationCodeLocation = instrumentationCodeLocation;
		setupVariables();
	}
	
	public void setupVariables(){
		importsLoc = instrumentationCodeLocation+"/RuntimeMonitor/ApplicationSide/Imports.tmp";
	}
		
	private boolean hasEventName(String eventName, String instrumentationLine){
		int startBracketLocation = -1;
		// Loop through the instumentation line untill we find the first '('
		for(int i = 0; i< instrumentationLine.length(); i++){
			if(instrumentationLine.charAt(i) == '(') {startBracketLocation = i; break;}			
		}
		//Once found we take the substring and identify if the substring contains the event name...
		return instrumentationLine.substring(0, startBracketLocation).contains(eventName);
	}
	
	private boolean hasParameters(String instrumentationLine, Parameters param){
		
		int startBracketLocation = -1;
		for(int i = 0; i< instrumentationLine.length(); i++){
			if(instrumentationLine.charAt(i) == '(') {startBracketLocation = i; break;}			
		}
		
		int lastBracketLocation = -1;
		for(int i = 0; i< instrumentationLine.length(); i++){
			if(instrumentationLine.charAt(i) == ')') {lastBracketLocation = i;}			
		}
		//System.out.println("Comparing: "+instrumentationLine.substring(startBracketLocation, lastBracketLocation+1).replaceAll(" ",""));
		//get list of parameters... identify if there is a match to the parameters that we want..
		return instrumentationLine.substring(startBracketLocation, lastBracketLocation+1).replaceAll(" ","").contains(param.getParameters());
	}
	
	private String getCode(String tag, String classCode, ArrayList<InstrumentationEvent> evntList){
		String code = "";
		
		if(tag.contains("//@Instrumentation Imports:")){
			//load and add Imports to the class code... 		
			try {
				code += readAllText(new File(importsLoc));
			} catch (IOException e) {
				System.out.println("Error while loading imports..."+e.getMessage());
			}			
		}else if(tag.contains("//@Instrumentation before:")){// should be ok...		
			//System.out.println("The tag: "+tag);
			code += "long sm; \n";
			for(InstrumentationEvent evnt: evntList){
				if(evnt.getPointOfInstrumentation().equals("@before")){
					if(hasEventName(evnt.getEventName(), tag)){	
						//System.out.println("Has Event name: "+evnt.getEventName()+" with param:"+evnt.getEventParameters().getParameters()+" for tag: "+tag);
						if(hasParameters(tag, evnt.getEventParameters())){
							code+= evnt.getCodeToBeInstrumented()+"\n";
						}
					}
				}			
			}			
		}else if(tag.contains("//@Instrumentation after:")){// need to check for the uponReturning....
			for(InstrumentationEvent evnt: evntList){
				if(evnt.getPointOfInstrumentation().equals("@after")){
					if(hasEventName(evnt.getEventName(), tag)){				
						if(hasParameters(tag, evnt.getEventParameters())){
							//If we need to implement uponReturning... code should be added here!
							if(evnt.getUponReturning() != null){
								code += evnt.getUponReturning()+" = uponReturning;\n";
							}							
							code+= evnt.getCodeToBeInstrumented()+"\n";
						}
					}			
				}			
			}						
		}
		return code;
	}
	
	private String loadJavaClassAndInject(File data, String classCode, ArrayList<InstrumentationEvent> evntList) throws IOException{
		//Start Buffer
		BufferedReader file = new BufferedReader(new FileReader(data));
	    String line;
	    String input = "";

	    //Read lines...
	    while ((line = file.readLine()) != null){    	
	    	//If we encounter the instrumetnation tags...    	
	    	if(line.contains("//@Instrumentation before:") || line.contains("//@Instrumentation after:") || line.contains("//@Instrumentation Imports:")){
	    		input += line + "\n";
	    		input += getCode(line, classCode, evntList);
	    		//loop until you find the end and discard what you read... 
	    		while ((line = file.readLine()) != null && !line.contains("//@Instrumentation End"));
	    	}
	    	input += line + "\n";
	    }
	    //Close opened file.
	    file.close();
		
		return input;		
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
		FileOutputStream os = new FileOutputStream(file);
	    os.write(output.getBytes());
	    os.close();
		return false;
	}
	
	public boolean instrumentCode(HashMap<String,String> settings, ClassRepresentation clsRep){
		HashMap<String,ArrayList<InstrumentationEvent>> clsList = clsRep.getInstrumentationList();
		
		for(String className: clsList.keySet()){
			try {
				//System.out.println("Class name: "+className);
				//Load Class code
				String classCode = loadJavaClassAndInject(new File(settings.get(className)),className,clsList.get(className));	
				//Save Class code... 
				writeFile(new File(settings.get(className)), classCode);
			} catch (Exception e) {
				System.out.println("Error while trying to load java class "+e);
				
			}		
		}		
		return false;
	}
}
