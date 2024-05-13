package module.movement;


import module.Category;
import module.Module;
import module.ModuleManager;

import java.util.ArrayList;
import java.util.Iterator;


import java.util.Iterator;

import org.apache.commons.codec.language.Caverphone2;
import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import me.nikolabg209.golden.utils.Invoker;
import module.Category;
import module.Module;
import module.ModuleManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly extends Module{
	
    public Fly() {
        super("Flight", Keyboard.KEY_G, Category.MOVEMENT);
    }
    
    @Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("Creative");
    //	options.add("Verus");
    	options.add("Collide");
    	options.add("Motion");
    	options.add("Hypixel");
    	Golden.instance.settingsManager.rSetting(new Setting(" ", this, "Creative", options));
    }
    
   
    
    
    public void onDisable(){
    	Invoker.setTimerSpeed(1);
    	mc.thePlayer.capabilities.isFlying = false;
    	
    }

    
    
    public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) { 
    			if(Golden.instance.settingsManager.getSettingByName(" ").getValString().equalsIgnoreCase("Creative")) {
    				mc.thePlayer.capabilities.isFlying = true;
    		}
    			if(Golden.instance.settingsManager.getSettingByName(" ").getValString().equalsIgnoreCase("Verus")) {
    			//if(mc.thePlayer.moveForward > 0) {
    				//setSpeed(1, 0, 1, 0);
    			
    		//	}
    		}
    			if(Golden.instance.settingsManager.getSettingByName(" ").getValString().equalsIgnoreCase("Collide")) {
    				((EventMotion) e).setOnGround(true);
    				mc.thePlayer.motionY = 0;
    			}
    			if(Golden.instance.settingsManager.getSettingByName(" ").getValString().equalsIgnoreCase("Motion")) {
    				mc.thePlayer.onGround = true;
    				mc.thePlayer.motionY = 0;
    				setSpeed(1);
    			}
    			if(Golden.instance.settingsManager.getSettingByName(" ").getValString().equalsIgnoreCase("Hypixel")) {
    				double y;
    				double y1;
    				mc.thePlayer.motionY = 0;
    				if (mc.thePlayer.ticksExisted % 3 == 0) 
    				{
    				y = mc.thePlayer.posY - 1.0E-10D;
    				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
    				}
    				y1 = mc.thePlayer.posY + 1.0E-10D;
    				mc.thePlayer.setPosition(mc.thePlayer.posX, y1, mc.thePlayer.posZ);
    			}
    			
    		
    			
    				
    			}
    			
    			
    		}
    	}
     
public void setSpeed(double moveSpeed, float yaw, double strafe, double forward) {
    if (forward != 0.0D) {
        if (strafe > 0.0D) {
            yaw += ((forward > 0.0D) ? -45 : 45);
        } else if (strafe < 0.0D) {
            yaw += ((forward > 0.0D) ? 45 : -45);
        }
        strafe = 0.0D;
        if (forward > 0.0D) {
            forward = 1.0D;
        } else if (forward < 0.0D) {
            forward = -1.0D;
        }
    }
    if (strafe > 0.0D) {
        strafe = 1.0D;
    } else if (strafe < 0.0D) {
        strafe = -1.0D;
    }
    double mx = Math.cos(Math.toRadians((yaw + 90.0F)));
    double mz = Math.sin(Math.toRadians((yaw + 90.0F)));
   mc.thePlayer.motionX = forward * moveSpeed * mx + strafe * moveSpeed * mz;
  mc.thePlayer.motionZ = forward * moveSpeed * mz - strafe * moveSpeed * mx;
}

public void setSpeed(double moveSpeed) {
    setSpeed(moveSpeed, mc.thePlayer.rotationYaw, mc.thePlayer.movementInput.moveStrafe, mc.thePlayer.movementInput.moveForward);
}
public void strafe(final float speed) {
    double shotSpeed = Math.sqrt((mc.thePlayer.motionX * mc.thePlayer.motionX) + (mc.thePlayer.motionZ * mc.thePlayer.motionZ));
    double fixSpeed = (shotSpeed * 1);
    double motionX = (mc.thePlayer.motionX * (0));
    double motionZ = (mc.thePlayer.motionZ * (0));

    if (!isMoving()) {
        mc.thePlayer.motionX = 0.0;
        mc.thePlayer.motionZ = 0.0;
        return;
    
}}
    public boolean isMoving() {
        return mc.thePlayer != null && (mc.thePlayer.movementInput.moveForward != 0F || mc.thePlayer.movementInput.moveStrafe != 0F);
    }
}

	
    		
    
    	
    	
	
	