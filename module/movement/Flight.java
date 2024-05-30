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
import me.nikolabg209.golden.events.listeners.BlockEvent;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import me.nikolabg209.golden.utils.Invoker;
import module.Category;
import module.Module;
import module.ModuleManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;

public class Flight extends Module{
	private double moveSpeed;
    private boolean started;
    private boolean notUnder;
    private boolean clipped;
    private boolean teleport;
    private double x;
    private double y;
    private double z;
    Block block = new Block(Material.air);
	
    public Flight() {
        super("Flight", Keyboard.KEY_G, Category.MOVEMENT);
    }
    
    @Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("Air Jump");
    	options.add("Creative");
    //	options.add("Verus");
    	options.add("Collide");
    	options.add("Motion");
    	options.add("Hypixel");
    	options.add("BlocksMC");
        Golden.instance.settingsManager.rSetting(new Setting("Motion Speed", this, 1.5, 0.1, 5,  false));

    	Golden.instance.settingsManager.rSetting(new Setting("Fly Mode", this, "BlocksMC", options));
    }
    
   
    
    
    public void onDisable(){
    	Invoker.setTimerSpeed(1);
    	mc.thePlayer.capabilities.isFlying = false;
    	this.moveSpeed = 1;
    	mc.timer.timerSpeed = 1;
    	Invoker.setTimerSpeed(1);
    	mc.thePlayer.motionX = 0;
    
    	mc.thePlayer.motionZ = 0;
    	
    }
    public void onEnable() {
    	this.moveSpeed = 0.0;
        this.notUnder = false;
        this.started = false;
        this.clipped = false;
        this.teleport = false;
       
        if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("BlocksMC")) {
        	Golden.addChatMessage(String.format("Start the fly under a block and walk forward."));
        	 mc.timer.timerSpeed = 0.4f;
        }
    	
    }

    
    
    public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) { 
    			
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("Creative")) {
    				mc.thePlayer.capabilities.isFlying = true;
    		}
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("Verus")) {
    			//if(mc.thePlayer.moveForward > 0) {
    				//setSpeed(1, 0, 1, 0);
    			
    		//	}
    		}
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("Collide")) {
    				((EventMotion) e).setOnGround(true);
    				mc.thePlayer.motionY = 0;
    				
    			}
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("Motion")) {
    				mc.thePlayer.onGround = true;
    				mc.thePlayer.motionY = 0;
    				setSpeed(Golden.instance.settingsManager.getSettingByName("Motion Speed").getValDouble());
    			}
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("Hypixel")) {
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
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("BlocksMC")) {
    				
    				 block9: {
    	            block8: {
    	             
    	                AxisAlignedBB bb = mc.thePlayer.getEntityBoundingBox().offset(0.0, 1.0, 0.0);
    	                if (!mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, bb).isEmpty() && !this.started) break block8;
    	                 
    	                switch(mc.thePlayer.offGroundTicks) {
    	                    case 0: {
    	                        if (this.notUnder && this.clipped) {
    	                            this.started = false;
    	                            setSpeed(9);
    	                           mc.thePlayer.motionY = 0.42f;
    	                            this.notUnder = false;
    	                            break;
    	                        }
    	                        break block9;
    	                    }
    	                    case 1: {
    	                        if (this.started) {
    	                            setSpeed(7.6);
    	                            break;
    	                        }
    	                        break block9;
    	                    }
    	                }
    	                break block9;
    	    }
    	            this.notUnder = true;
    	            if (this.clipped) {
    	                return;
    	                    }
    	            
    	            this.clipped = true;
    	            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, false));
    	            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY - 0.1, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, false));
    	            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, false));
    	            this.teleport = true;
    	    
    	      
    	     mc.timer.timerSpeed = 0.4f;}
    	    	}
    			if(Golden.instance.settingsManager.getSettingByName("Fly Mode").getValString().equalsIgnoreCase("Air Jump")) {
    				
    		            this.y = mc.thePlayer.posY;
    		        
    		        if (mc.thePlayer.onGround) {
    		            mc.thePlayer.jump();
    		        }
    			}
    			BlockEvent event = new BlockEvent(world(), null, null, null, null);
    		        if (BlockEvent.getBlock() instanceof BlockAir && (mc.thePlayer.posY < this.y + 1.0)) {
    		            double x = BlockEvent.getBlockPos().getX();
    		            double y = BlockEvent.getBlockPos().getY();
    		            double z = BlockEvent.getBlockPos().getZ();
    		           
    		                BlockEvent.setBoundingBox(AxisAlignedBB.fromBounds(-15.0, -1.0, -15.0, 15.0, 1.0, 15.0).offset(x, y, z));
    		            }
    		        
    		    }
    			}
    
    	    	}
   
    
    	
    					
    				
    					
    				//}	
    				
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

	
    		
    
    	
    	
	
	