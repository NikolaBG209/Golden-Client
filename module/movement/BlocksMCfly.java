package module.movement;

import com.ibm.icu.text.Normalizer.Mode;

import module.Category;
import module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import me.nikolabg209.golden.events.listeners.*;
import me.nikolabg209.golden.utils.Invoker;




public class BlocksMCfly extends Module {
   
    public BlocksMCfly() {
		super("BlocksMCfly", 0, Category.MOVEMENT);
		
	}
    
	private double moveSpeed;
    private boolean started;
    private boolean notUnder;
    private boolean clipped;
    private boolean teleport;
    
	
   
    public void onUpdate() {
    	if(this.isToggled()) {
    
    
        

    
        block9: {
            block8: {
             
			
                
    
                AxisAlignedBB bb = mc.thePlayer.getEntityBoundingBox().offset(0.0, 1.0, 0.0);
                if (!mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, bb).isEmpty() && !this.started) break block8;
                 
                switch(mc.thePlayer.offGroundTicks) {
                    case 0: {
                        if (this.notUnder && this.clipped) {
                            this.started = false;
                            setSpeed(9.0);
                           mc.thePlayer.motionY = 0.42f;
                            this.notUnder = false;
                            break;
                        }
                        break block9;
                    }
                    case 1: {
                        if (this.started) {
                            setSpeed(8.6);
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
    	}
    
    
    public void onEnable() {
       
        this.moveSpeed = 0.0;
        this.notUnder = false;
        this.started = false;
        this.clipped = false;
        this.teleport = false;
        
    }
    public void onDisable() {
    	this.moveSpeed = 1;
    	mc.timer.timerSpeed = 1;
    	Invoker.setTimerSpeed(1);
    	mc.thePlayer.motionX = 0;
    
    	mc.thePlayer.motionZ = 0;
    	
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
}
