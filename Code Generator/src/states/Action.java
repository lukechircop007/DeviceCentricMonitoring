package states;

public class Action {

		String actionName;
		String kernelSideAction;
		String applicationSideAction;
		String timerAction;
		
		public Action(){
			String actionName = null;
			String kernelSideAction = null;
			String applicationSideAction = null;
			String timerAction= null;
		}
		
		//Setters
		public void setActionName(String actionName){
			this.actionName = actionName;
		}
		
		public void setKernelSideAction(String kernelSideAction){
			this.kernelSideAction = kernelSideAction;
		}
		
		public void setApplicationSideAction(String applicationSideAction){
			this.applicationSideAction = applicationSideAction;
		}
		
		public void setTimerAction(String timerAction){
			this.timerAction  = timerAction;
		}
		
		//Getters
		public String getActionName(){return this.actionName;}
		public String getKernelSideAction(){return this.kernelSideAction;}
		public String getApplicationSideAction(){return this.applicationSideAction;}
		public String getTimerAction(){return this.timerAction;}
	
}
