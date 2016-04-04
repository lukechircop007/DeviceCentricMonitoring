package codeGeneration;

import states.Program;


public class TimerCodeGeneration {
	
	//Fixed method to Generate the Timer Manage Code... 
	public static String generateTimerManagerCode(){
		String text = "package com.java.runtimeverification;\n"
			+"public class TimerManager implements Runnable {"
			+"\n"
			+"\nprivate static boolean on = false;\nprivate static IterableList events;\nprivate static Object lock;"
			+"\n"
			+"\nstatic {\nstart();\n}"
			+"\n"		
			+"\npublic static void start(){\nif (!on) {\non = true;\nevents = new IterableList();\nlock = new Object();\nThread t = new Thread(new TimerManager());\nt.setPriority(Thread.MAX_PRIORITY);\nt.setDaemon(true);\nt.start();\n}\n}"
			+"\n"
			+"\npublic static void register(Long l, Long current, Timer c){\nTimerManager.events.add(l + current,l,c);"
			+"\nsynchronized (lock) {\nlock.notify();//in case the clock is idle\n}\n}"
			+"\n"
			+"\npublic static void register(Long l, Long current, Long paused, Timer c){\nTimerManager.events.add(l + current + paused,l,c);\nsynchronized (lock) {\nlock.notify();//in case the clock is idle\n}\n}"
			+"\n"
			+"\npublic void run() {\ntry{\nwhile (on)"
			+"\nif (events.getNext() != null) {\nlong next = events.current();\n\nlong cur = System.currentTimeMillis();\nlong tmp = next - cur;"
			+"\nif (on && tmp > 0) \nsynchronized (lock){\nlock.wait(tmp);\n}\ncur = System.currentTimeMillis();"
			+"\nif (on && next <= cur){\nevents.remove();"
			+"\nfor (int i = 0; i < events.currentClocks().size(); i++){\nTimer c = events.currentClocks().get(i);"
			+"\nlong d = events.currentDurations().get(i);\nif (c.verified(next-d))c.fire(d);\n}"
			+"\n}\n\n}\nelse\n{\nsynchronized (lock) {lock.wait();}\n}\n}"
			+"\ncatch(Exception ex){\nex.printStackTrace();\n}\n}\n}";		
		return text;
	}
	
	//Fixed method to generate the IterableList code....
	public static String generateIterableListCode(){
		String text =  "package com.java.runtimeverification;\n"
			+"import java.util.ArrayList;\npublic class IterableList {"
			+"\n\nArrayList<Long> actual = new ArrayList<Long>();\nArrayList<ArrayList<Long>> drs = new ArrayList<ArrayList<Long>>();\nArrayList<ArrayList<Timer>> clks = new ArrayList<ArrayList<Timer>>();\nint iterator = 0;\nboolean keeping = false;\nArrayList<Timer> clocks = null;\nLong l = null;\nArrayList<Long> durations = null;"
			+"\npublic void add(Long l, Long d, Timer c){\nsynchronized (this){\nif (!actual.contains(l)){\nint i = 0;\nwhile (i < actual.size() && l > actual.get(i)) i++;\nactual.add(i,l);\nArrayList<Long> ds = new ArrayList<Long>();\nArrayList<Timer> cs = new ArrayList<Timer>();\nds.add(d);\ncs.add(c);\ndrs.add(i,ds);\nclks.add(i,cs);\n}"
			+"\nelse //if (!clks.get(actual.indexOf(l)).contains(c))\n{\nclks.get(actual.indexOf(l)).add(c);\ndrs.get(actual.indexOf(l)).add(d);\n}\n}\n}"
			+"\n//skip the next getNext() by returning the current values again"
			+"\npublic void keep(){synchronized (this){keeping = true;\n}\n}"
			+"\npublic ArrayList<Timer> currentClocks(){\nsynchronized (this){\nreturn clocks;\n\n}\n}"			
			+"\npublic ArrayList<Long> currentDurations(){\nsynchronized (this){\nreturn durations;\n}\n}"
			+"\npublic Long current(){\nsynchronized (this){\nreturn l;\n}\n}"
			+"\npublic void remove()\n{\nsynchronized (this){\nif (actual.size() > 0){\nactual.remove(0);\nclks.remove(0);\ndrs.remove(0);\n}\n}\n}"
			+"\npublic Long getNext(){\nsynchronized (this){\nif (actual.size() == 0)\nreturn null;\nelse"
			+"\n{\nl = actual.get(0);\nclocks = clks.get(0);\ndurations = drs.get(0);\nreturn l;\n}\n}\n}\n}";
		return text;
	}
	
	//Method to generate the fire() method for timers...
	//This will contain all the actions that are going to execute corresponding to their timer
	protected static String generateFireMethodCode(Program p){
		String text = "\npublic void fire (Long millis){\n";
		
		text+= p.getGlobal().generateTimerConditionAndAction();
		
		text +="\n}\n}\n";
		return text;
	}
	
	//Fixed method to generate the Timer code (has included the fire method)
	public static String generateTimerCode(Program p){ 
		String text ="package com.java.runtimeverification;\n\n"
		//+ p.getAllImports().toJavaCode()+ "\n"
		+ "import java.util.ArrayList;\npublic class Timer{"
		+"\n//a timer identifier\nprivate String name;\n//a list of time elapses at which the timer will trigger"
		+"\nprivate ArrayList<Long> registered = new ArrayList<Long>();\n//whether the timer is enabled or not (no events will fire if not enabled)\n"
		+"\nprivate boolean enabled = true;	\n//whether the timer is currently paused"
		+"\nprivate boolean paused = false;\nprivate long durationPaused = 0;\nprivate long whenPaused;\nprivate long starting;\n"
		+"\npublic Timer(){\nthis.name = this.toString();\n}"
		+"\npublic Timer(String name){\nthis.name = name;\n}"
		+"\npublic Timer(String name, Long firingTime){\nthis.name = name;\nthis.addFiringTime(firingTime);\n}\n"
		+"\npublic String getIdentifier(){\nreturn name;\n}"
		+"\npublic Timer disable(){\nsynchronized (this){\nenabled = false;\nreturn this;\n}\n}"
		+"\npublic Timer enable(){\nsynchronized (this){\nenabled = true;\nreturn this;\n}\n}"
		+"\npublic boolean isEnabled(){\nreturn enabled;\n}"
		+"\npublic Timer reset(){\nsynchronized (this){\npaused = false;\ndurationPaused = 0;\nstarting = System.currentTimeMillis();\nfor (int i = 0; i < registered.size(); i++)\nregisterWithTimerManager(registered.get(i),starting);\n//no need to un-register the existing events which belong to this clock\n//this will be automatically ignored\nreturn this;\n}\n}"
		+"\nprotected boolean verified (Long starting){\nsynchronized (this){\nif (enabled && !paused)\nreturn (this.starting + durationPaused) == starting;\nelse \nreturn false;\n}\n}"
		+"\npublic Timer pause(){\nsynchronized (this){\npaused = true;\nwhenPaused = System.currentTimeMillis();\nreturn this;\n}\n}"
		+"\npublic Timer resume(){\nsynchronized (this){\nlong now = System.currentTimeMillis();\ndurationPaused += now - whenPaused;	\npaused = false;//unpause here because this will effect the current time of the clock\nfor (int i = 0; i < registered.size(); i++)\nif (registered.get(i) > current_long(now))//filter those events which occurred before pause\nTimerManager.register(registered.get(i), starting, durationPaused, this);\nreturn this;\n}\n}"
		+"\npublic Long time() {\nsynchronized (this){\nreturn current_long();\n}\n}"
		+"\nprivate long current_long(long now) {\nsynchronized (this){\nif (paused) return (whenPaused - starting - durationPaused);\nelse return (now - starting - durationPaused);\n}\n}"
		+"\nprivate long current_long(){\nsynchronized (this){\nif (paused) return (whenPaused - starting - durationPaused);\nelse return (System.currentTimeMillis() - starting - durationPaused);\n}\n}"
		+"\npublic Timer addFiringTime(Long millis){\nsynchronized (this){\nregistered.add(millis);\nreturn this;\n}\n}"
		+"\nprivate void registerWithTimerManager(Long millis, Long current){\nTimerManager.register(millis,current, this);\n}";	
		text += generateFireMethodCode(p);		
		return text;	
	}

	//method to generate the TimerFramework that will be used to reset,pause,resume,turnoff applications side timers.. 
	public static String generateRVTimerFrameworkCode(Program p){
		String text = "package com.java.runtimeverification;\n\npublic class RVTimers {\n\n";

		// static timer declaration.. 
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text+="private static Timer "+timerName+";\n";
		}			
			
		//Initialization.. of timers.. 
		text+="\nstatic{\n";
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text += timerName+"= new Timer(\""+timerName+"\",(long)"+p.getGlobal().getTimers().getTimerValueApp(timerName)+");\n";
		}
		text+="\n}\n";			
				
		text+="\npublic static boolean timerUnder(String timerName, long timeValue){\n";
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text+="if(timerName.equals(\""+timerName+"\")){\n";
			text+="if("+timerName+".time() > timeValue)return true;\n}\n";
		}
		text+="return false;\n}";
				
		text+="\npublic static boolean pauseTimer(String timerName){\n";
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text+= "if(timerName.equals(\""+timerName+"\")){\n";
			text+= timerName+".pause();\n return true;";
			text+= "}\n";
		}
		text+="return false;\n}";
			
		text+="\npublic static boolean resumeTimer(String timerName){\n";
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text+="if(timerName.equals(\""+timerName+"\")){\n";
			text+= timerName+".resume();\n return true;";
			text+="}\n";
		}
		text+="return false;\n}";
			
		text+="\npublic static boolean resetTimer(String timerName){\n";
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text+="if(timerName.equals(\""+timerName+"\")){\n";
			text+= timerName+".reset();\n return true;";
			text+="}\n";
		}		
		text+="return false;\n}";
			
		text+="\npublic static boolean timerOff(String timerName){\n";
		for(String timerName: p.getGlobal().getTimers().getTimerNameListApp().keySet()){
			text+="if(timerName.equals(\""+timerName+"\")){\n";
			text+= timerName+".disable();\n return true;";
			text+="}\n";
		}		
		text+="return false;\n}\n}";

		return text;
	}
	
}
