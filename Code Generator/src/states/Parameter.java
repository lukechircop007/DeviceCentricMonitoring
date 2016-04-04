package states;

import java.util.ArrayList;
import java.util.HashMap;

public class Parameter {
	
	private ArrayList<String> parameterTypes = null;
	private ArrayList<String> parameterNames = null;
	private HashMap<String, String> paramterAndType = null;
	private boolean star = false;
	
	public Parameter(){
		parameterTypes = new ArrayList<String>();
		parameterNames = new ArrayList<String>();
		paramterAndType = new HashMap<String, String>();
	}
	
	public void addParameterType(String parameterType){
		this.parameterTypes.add(parameterType);
	}
	
	public void addParameterName(String parameterName){
		this.paramterAndType.put(parameterName,parameterTypes.get(parameterTypes.size()-1));
		this.parameterNames.add(parameterName);
	}

	public void setStar(){
		this.star = true;
	}
	
	public ArrayList<String> getParameterTypes(){return this.parameterTypes;}
	public ArrayList<String> getParameterNames(){return this.parameterNames;}
	public String getParameterType(String param){return this.paramterAndType.get(param);}
	public boolean hasStart(){return star;}
	
	public String getParameterListAppSide(){
		String text = "";
		boolean ok = false;
		for(int i = 0; i<parameterTypes.size(); i++){
			if(i > 0)text+= ",";
			text += parameterTypes.get(i)+" "+parameterNames.get(i);		
			ok = true;
		}
		if(star && ok)text+=",";
		if(star)text+= "* ";
		return text;
	}
	
	public String getParameterList(){
		String text = "";
		for(int i = 0; i<parameterTypes.size(); i++){
			if(i > 0)text+= ",";
			text += parameterTypes.get(i)+" "+parameterNames.get(i);			
		}
		return text;
	}

	public String getParamType(String paramName){
		for(int i = 0; i < parameterNames.size(); i++){
			if(parameterNames.get(i).equals(paramName)){
				return parameterTypes.get(i);
			}
		}		
		return null;
	}
}
