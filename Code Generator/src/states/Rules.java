package states;

import java.util.ArrayList;
import java.util.HashMap;

public class Rules {
	
	private ArrayList<Rule> rules = null;

	public Rules(){rules = new ArrayList<Rule>();}
	
	//Setters
	public void addRule(Rule rule){rules.add(rule);}
	
	//Getters
	public ArrayList<Rule> getRules(){return rules;}
	public Rule getRule(int position){return rules.get(position);}
	
	
	//KernelSide Code Generation..
	//Calls for all the prototypes to be generated..
	public String generatePrototypes(HashMap<Integer, HashMap<String,Integer>> commonEvent, Events events, Actions actions){
		String text = "";
		//For every rule
		for(Rule rule: rules){		
			//Check if it has at least one kernelAction...
			for(String actn: rule.getRuleActions()){
				//If it does... generate the prototype...
				if(actions.getNormalAction(actn).kernelSideAction != null){
					if(events.getEvent(rule.getRuleEvent().split("\\(")[0]) == null){
						System.out.println("Error: Did not find event: \""+rule.getRuleEvent()+"\" defined in Rule: "+rule.getRuleName());
						System.exit(1);
					}
					text+= rule.generateMethodName(commonEvent.get(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup), events)+"; \n";
					break;
				}
			}
		}
		return text;
	}
	
	//Calls for the actual methods to be generated inside the kernelside monitor... 
	public String generateMethods(HashMap<Integer, HashMap<String,Integer>> commonEvent, Events events, Conditions conditions, Actions actions){
		String text = "";
		//For every rule
		for(Rule rule: rules){		
			//Check if it has at least one KernelAction...
			for(String actn: rule.getRuleActions()){
				if(actions.getNormalAction(actn).kernelSideAction != null){
					//If it does... generate the actual Method...
					text+= rule.generateMethod(commonEvent,events, conditions,actions)+" \n";
					break;
				}
			}
		}
		return text;
	}
	
	//Generates System calls... 
	public String generateMethodCallsForSyscall(int groupId, HashMap<String,Integer> numOfOccuranceForTypes, Events events, Actions actions){				
		String text = "";
				
		boolean methodcall = false;
		boolean hasKernelcode = false;
		String paramList = null;
		//for every rule..
		for(Rule rule : rules){
			//If its event is equal to the current group id...
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){	
				//Loop through each of its action..
				for(String actn: rule.getRuleActions()){
					//Until an action is found that has kernel side code..
					if(actions.getNormalAction(actn).kernelSideAction != null){
						hasKernelcode = true;
						break;
					}
				}
				//If at least one  action was found to contain kernel side code... 
				if(hasKernelcode){	
					//We first declare the systemcall along with the parameters that are accepted..
					if(!methodcall){
						String temp = "";
						temp = events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateParamListKernelSide(numOfOccuranceForTypes, events);
						if(!temp.equals("")){ 
							text+= "\n asmlinkage long sys_rv_monitor_"+groupId+"(char *event, "+temp+"){\n";
						}else{
							text+= "\n asmlinkage long sys_rv_monitor_"+groupId+"(char *event, char* action_name){\n";						
						}
						methodcall = true;
					}
					text+= "if(strcmp(event,\""+rule.getRuleEvent().split("\\(")[0]+"\") == 0){\n";
					if(paramList == null)
						paramList = events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateExtraParamVarNameList(numOfOccuranceForTypes, events);
					text+= "return "+rule.getRuleName()+"("+paramList+");\n";
					text+= "}\n";
				}	
			}		
		}
		if(methodcall)text+= "return -1; \n}\n";
		return text;
	}
	
	//Generates the Call names for kernel...
	public String generateCALLS(int groupId, HashMap<String,Integer> numOfOccuranceForTypes, Events events){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){	
				return "CALL(sys_rv_monitor_"+groupId+") \n";
			}			
		}
		return text;
	}
	
	//Generates the SystemCall prototypes...
	public String generateSyscallPrototypes(int groupId, HashMap<String,Integer> numOfOccuranceForTypes, Events events){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){	
				String temp = "";
				temp = events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateParamListKernelSide(numOfOccuranceForTypes, events);
				if(!temp.equals("")){ 
					text+= "\n asmlinkage long sys_rv_monitor_"+groupId+"(char *event, "+temp+");";
				}else{
					text+= "\n asmlinkage long sys_rv_monitor_"+groupId+"(char *event, char* action_name);";						
				}
				return text;
			}			
		}
		return text;
	}
	
	//Generates the Unistd.h code...
	public String generateUnistd(int groupId,int position, HashMap<String,Integer> numOfOccuranceForTypes, Events events,Actions actions){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){					
				return "#define __NR_sys_rv_monitor_"+groupId+" \t (_NR_sys_rv_monitor_"+groupId+"+ "+position+") \n";
			}			
		}
		return text;
	}
	
	//Generates SyscallFramework prototypes.. (for jni...)
	public String generateSyscallFrameworkPrototypesJava(int groupId, HashMap<String,Integer> numOfOccuranceForTypes, Events events){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){	
				String temp = "";
				temp = events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateExtraParamListJavaSyscall(numOfOccuranceForTypes, events);
				if(!temp.equals("")){ 
					text+= "\n public native long sysRvMonitor"+groupId+"(String event, "+temp+");";
				}else{
					text+= "\n public native long sysRvMonitor"+groupId+"(String event, String action_name);";						
				}
				return text;
			}			
		}
		return text;
	}
	
	//Generates the system call java side...
	public String generateSyscallFrameworkJava(int groupId, HashMap<String,Integer> numOfOccuranceForTypes, Events events){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){	
				String temp = "";
				temp = events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateExtraParamListJavaSyscall(numOfOccuranceForTypes, events);
				if(!temp.equals("")){ 
					text+= "\n public static long sysCall_"+groupId+"(String event, "+temp+"){\n";
					text+= "return (new RVSyscall()).sysRvMonitor"+groupId+"(event, "+events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateExtraParamVarNameList(numOfOccuranceForTypes, events)+");";
				}else{
					text+= "\n public static long sysCall_"+groupId+"(String event){\n";
					text+= "return (new RVSyscall()).sysRvMonitor"+groupId+"(event,action_name);";
				}				
				text += "\n}\n";
				return text;
			}			
		}
		return text;
	}
	
	//Generates the c code that actually calls the system calls from the user level... (also takes care to translate all the necessary variables in their respective types...
	public String generateSyscallFrameworkPrototypesC(int groupId, HashMap<String,Integer> numOfOccuranceForTypes, Events events){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){	
				String temp = "";
				temp = events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateExtraParamListForSyscall(numOfOccuranceForTypes, events);
				if(!temp.equals("")){ 
					text+= "\n JNIEXPORT jlong JNICALL Java_com_java_runtimeverification_RVSyscall_sysRvMonitor"+groupId+"(JNIEnv *env, jobject thisobject, jstring event, jstring action_name, "+temp+"){\n";
					text+="const char *nativeEvent = (*env)->GetStringUTFChars(env, event, 0);\n";
					text+="const char *nativeAction = (*env)->GetStringUTFChars(env, action_name, 0);\n";
					// translate all the strings... for every string type... 
					text+=events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateStringTranslation(numOfOccuranceForTypes, events);
					//call syscall.. 
					text+="return (jlong)syscall(sys_rv_monitor_"+groupId+", nativeEvent, nativeAction, "+events.getEvent(rule.getRuleEvent().split("\\(")[0]).generateParamVarNameListForSyscall(numOfOccuranceForTypes, events)+");\n";				
				}else{
					text+= "\n JNIEXPORT jlong JNICALL Java_com_java_runtimeverification_RVSyscall_sysRvMonitor"+groupId+"(JNIEnv *env, jobject thisobject, jstring event, jstring action_name){\n";	
					text+="const char *nativeEvent = (*env)->GetStringUTFChars(env, event, 0);\n";
					text+="const char *nativeAction = (*env)->GetStringUTFChars(env, action_name, 0);\n";
					text+="return (jlong)syscall(sys_rv_monitor_"+groupId+",nativeEvent,nativeAction);\n";					
				}
				
				text+="}\n";
				return text;
			}			
		}
		return text;
	}
	
	//Generates the #defines needed to be able to call the systems calls...
	public String generateCalls(int groupId,int position, HashMap<String,Integer> numOfOccuranceForTypes, Events events,Actions actions){				
		String text = "";
				
		for(Rule rule : rules){
			if(events.getEvent(rule.getRuleEvent().split("\\(")[0]).eventGroup == groupId){					
				return "#define sys_rv_monitor_"+groupId+" "+position+" \n";
			}			
		}
		return text;
	}

	
}
