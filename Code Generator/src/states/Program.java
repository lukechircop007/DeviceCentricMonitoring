package states;

public class Program {

	private Imports allImports;
	private Global global;
	
	//Setters
	public void setAllImports(Imports allImports){
		this.allImports = allImports;		
	}
	
	public void setGlobal(Global global){
		this.global = global;
	}
	
	//Getters
	
	public Imports getAllImports(){
		return this.allImports;
	}
	
	public Global getGlobal(){
		return this.global;
	}
	
}
