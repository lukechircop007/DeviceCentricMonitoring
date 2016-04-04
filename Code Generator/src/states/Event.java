package states;

import java.util.ArrayList;
import java.util.HashMap;

public class Event {

	private String eventCapture;
	private String eventName;		
	private Parameter parameters;	
	private ArrayList<EventRepresentation> eventRepresentation = null;
	private String uponReturningCode = null;
	private String whereCode = null;

	private boolean specialEvent;
	public int eventGroup = -1;
	
	public Event(){	
		specialEvent = false;
		eventRepresentation = new ArrayList<EventRepresentation>();
	}
	
	//Setters
	public void setSpecialEvent(){specialEvent = true;}	
	public void setEventName(String eventName){this.eventName = eventName;}	
	public void setWhereCode(String whereCode){	this.whereCode = whereCode;}
	public void setEventCapture(String eventCapture){this.eventCapture = eventCapture;}	
	public void setEventParameters(Parameter parameters){this.parameters = parameters;}
	public void setUponReturningCode(String uponReturningCode){this.uponReturningCode = uponReturningCode;}	
	public void addEventRepresentation(EventRepresentation eventRepresentation){this.eventRepresentation.add(eventRepresentation);}
	
	
	//Getters
	//public String getWhereCode(){return this.whereCode;}	
	public String getEventName(){return this.eventName;}	
	public Parameter getParameters(){return this.parameters;}	
	public boolean isSpecialEvent(){return this.specialEvent;}
	public String getEventCapture(){return this.eventCapture;}	
	public String getUponReturningCode(){
		if(uponReturningCode != null && parameters != null){
			if(parameters == null || parameters.getParamType(this.uponReturningCode) == null){
				System.out.println("Error no Type defined for upon returning parameter given!");
				System.exit(1);
			}
			return this.uponReturningCode;
		}
		return null;
	}
	public int getEventRepresentationSize(){return this.eventRepresentation.size();}	
	public int getSizeOfEventRepresentation(){return this.eventRepresentation.size();}
	public EventRepresentation getEventRepresentation(int position){return this.eventRepresentation.get(position);}	
	public ArrayList<EventRepresentation> getEventRepresentations(){return this.eventRepresentation;}
	public String getWhereCode() {return whereCode;}
	//Code Generation...
	
	//Generates list of all parameters with their var types... for C side...
	public String generateParamListKernelSide(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "char* action_name";	
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				text += ",";
				if(varType.equals("String")){
					{text += "char* ";}
				}else{
					text+= varType+" ";
				}
				text += allTypeNames.get(varType).get(i);
			}				
		}
		return text;		
	}
	
	//Generates lif of all parameters with their var types... for Java side..
	public String generateExtraParamListJavaSyscall(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "String action_name";		
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		
		//boolean comma = false;
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				//if(comma){
					text += ",";//}else{comma = true;}
				text+= varType+" ";
				text += allTypeNames.get(varType).get(i);
			}				
		}
		return text;		
	}

	//Generates list of all parameters.
	public String generateExtraParamVarNameList(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "action_name";		
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				text += ", ";
				text += allTypeNames.get(varType).get(i);
			}				
		}
		return text;		
	}
	
	//Generates list of all parameters.
	public String generateExtraParamVarNameListForAppSide(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "";		
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				text += ", ";
				text += allTypeNames.get(varType).get(i);
			}				
		}
		return text;		
	}

	//Takes care to list all of the parameters that will be accepted whilst also converting the types to their respective type... not finished!
	public String generateExtraParamListForSyscall(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "";		
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		
		boolean comma = false;
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				String temp;
				if(comma){text += ",";}else{comma = true;}
				if(varType.equals("String")){
					text += "jstring ";
				}else if(varType.equals("int")){
					text += "jint ";
				}else if(varType.equals("char")){
					text+= "jchar ";
				}else if(varType.equals("long")){
					text+= "jlong ";
				}else{
					text+= varType+" ";
				}
				text += allTypeNames.get(varType).get(i);
			}				
		}
		return text;		
	}
	
	public String generateStringTranslation(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "";		
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				if(varType.equals("String")){
					text+="const char *native"+allTypeNames.get(varType).get(i)+" = (*env)->GetStringUTFChars(env, "+allTypeNames.get(varType).get(i)+", 0);\n";
				}
			}				
		}
		return text;		
	}

	public String generateParamVarNameListForSyscall(HashMap<String,Integer> numOfOccuranceForTypes, Events events){
		String text = "";		
		HashMap<String, ArrayList<String>> allTypeNames = events.listAllTypeNames(this);
		
		boolean comma = false;
		//For every Type...
		for(String varType: allTypeNames.keySet()){
			for(int i = 0; i< allTypeNames.get(varType).size(); i++){
				String temp;
				if(comma){text += ", ";}else{comma = true;}	
				if(varType.equals("String")){
					text += "native"+allTypeNames.get(varType).get(i);
				}else if(varType.equals("int")){
					text += "(int)"+allTypeNames.get(varType).get(i);
				}else if(varType.equals("long")){
					text += "(long)"+allTypeNames.get(varType).get(i);
				}else{
					text += allTypeNames.get(varType).get(i);
				}
			}				
		}
		return text;		
	}
}
