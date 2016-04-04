package states;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassRepresentation {

	private HashMap<String, ArrayList<InstrumentationEvent>> clsInst = new HashMap<String, ArrayList<InstrumentationEvent>>();
	
	
	public void addInstrumentation(String ClassName, InstrumentationEvent eventInst){
		if(clsInst.containsKey(ClassName)){
			clsInst.get(ClassName).add(eventInst);
		}else{
			ArrayList<InstrumentationEvent> tmp = new ArrayList<InstrumentationEvent>();
			tmp.add(eventInst);
			clsInst.put(ClassName, tmp);
		}		
	}
	
	public HashMap<String, ArrayList<InstrumentationEvent>> getInstrumentationList(){
		return clsInst;		
	}
	
}
