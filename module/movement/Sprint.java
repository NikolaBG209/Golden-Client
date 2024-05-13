package module.movement;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import module.Category;
import module.Module;
import module.ModuleManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Sprint extends Module{
	
    public Sprint() {
        super("Sprint", Keyboard.KEY_K, Category.MOVEMENT);
    }
    
    public void onEnable(){
    	
    	
    	//mc.thePlayer.setSprinting(true);
    	
    		
    	}
    	
    	
    	  
    
    
  public void onDisable(){
    	mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.getIsKeyPressed());
   	
   }
    
    public void onEvent(Event e)  {
    	if(e instanceof EventUpdate) {
    		if(e.isPre()) { 
    		      if(mc.thePlayer.moveForward > 0 && !mc.thePlayer.isEating() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isBlocking() && !mc.thePlayer.isCollidedHorizontally) {
    				mc.thePlayer.setSprinting(true);
    		      }
    		    	}
            	}
    		
	
    		
    
    	}
    	
	}
	
    			
    