package module.render;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import module.Category;
import module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class FullBright extends Module{

	
    public FullBright() {
        super("FullBright", Keyboard.KEY_M, Category.RENDER);
    }
    
    public void onEnable(){
    	mc.gameSettings.gammaSetting = 100;
  
    	}
    	  
    
    
    public void onDisable(){
    	mc.gameSettings.gammaSetting = 1;
    	
    }
    
 
    	
    	}