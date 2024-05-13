package module.combat;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import me.nikolabg209.golden.utils.Timer;
import module.Category;
import module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;


public class OldKillAura extends Module{
	
	public Timer timer = new Timer();
	
    public OldKillAura() {
        super("OldKillAura", Keyboard.KEY_NONE, Category.COMBAT);
    }
    
    public void onEnable(){
    	  
    }
    
    public void onDisable(){
    	
    }
    
    public void onEvent(Event e)  {
    	if(e instanceof EventUpdate) {
    		if(e.isPre()) { 
    		}
    	
    	}
    			
    }
    			
  
    
    
    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;
       
        for(Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
            Object theObject = entities.next();
            if(theObject instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) theObject;
               
                if(entity instanceof EntityPlayerSP)
                	continue;
                if(mc.thePlayer.getDistanceToEntity(entity) <= 3.5F) {
                    if(entity.isEntityAlive()) {
                    	if(timer.hasTimeElapsed(1000 / 10, true)) {
                    		
                    	
                        mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
                        mc.thePlayer.swingItem();
                       
                        continue;
                        }
                    }
               }
            }
        
        }
    }
       
        
    
        
    
        	
        }
        

    

