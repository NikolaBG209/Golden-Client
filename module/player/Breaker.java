package module.player;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import module.Category;
import module.Module;
import net.minecraft.block.BlockBed;

public class Breaker extends Module{
	
	public Breaker() {
		super("Breaker", Keyboard.KEY_K, Category.PLAYER);
	}
	
	public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) { 
    			for(Object o : mc.theWorld.loadedTileEntityList) {
    				if(o instanceof BlockBed) {
    					
    				}
    			}
    		}
    	}
   	}

}
