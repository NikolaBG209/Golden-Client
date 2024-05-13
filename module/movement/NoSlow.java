package module.movement;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventNoSlow;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import module.Category;
import module.Module;

public class NoSlow extends Module{
	
	public NoSlow() {
		super("NoSlow", 0, Category.MOVEMENT);
	}
	
	 public void onEvent(Event e)  {
	    	if(e instanceof EventNoSlow) {
	    		
	    			if(mc.thePlayer.isUsingItem()) {
	    				e.setCancelled(true);
	    			
	    		}
	    	}
	   	}
	}
