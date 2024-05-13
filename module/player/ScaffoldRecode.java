package module.player;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import me.nikolabg209.golden.utils.Timer;
import module.Category;
import module.Module;
import net.minecraft.util.EnumFacing;

public class ScaffoldRecode extends Module{
	
	Timer timer = new Timer();
	
	public ScaffoldRecode() {
		super("ScaffoldRecode", 0, Category.PLAYER);
	}
	public void onDisable() {
		mc.gameSettings.keyBindUseItem.pressed = false;
	}
	public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) { 
    			EventMotion event = (EventMotion) e;
		
			//if(mc.thePlayer.moveForward > 0) {
				mc.thePlayer.setSprinting(false);
				mc.thePlayer.rotationYaw = (90);
				mc.thePlayer.rotationPitch =  (79.6F);
				mc.thePlayer.rotationYawHead = (90);
		    	mc.thePlayer.renderYawOffset = (90);
		    	mc.thePlayer.rotationPitchHead = (75);
		    	mc.rightClickDelayTimer = 0;
		    	mc.gameSettings.keyBindUseItem.pressed = true;
		    	//mc.gameSettings.keyBindLeft.pressed = true;
				
			//}
		    	
		    		
		    		
		    			
		    	
    		}
		}
		
	}

}
