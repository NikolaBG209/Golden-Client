
package module.movement;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import module.Category;
import module.Module;
import me.nikolabg209.golden.utils.*;

public class Speed extends Module{

	public Speed() {
		super("Speed", 0, Category.MOVEMENT);
	
	}
	public void onDisable() {
		Invoker.setTimerSpeed(1);
		mc.thePlayer.capabilities.isFlying = false;
		mc.gameSettings.keyBindJump.pressed = false;
	}
	public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) { 
			//if(mc.thePlayer.onGround) {
				
				if(Golden.instance.settingsManager.getSettingByName("Speed Mode").getValString().equalsIgnoreCase("Strafe")) {
					strafe(1);
					setSpeed(0.25);
					if(isMoving() && mc.thePlayer.onGround) {
				    			mc.thePlayer.jump();
				    			//((EventMotion) e).setOnGround(true);
					}
				    		}
				
					
				
				if(Golden.instance.settingsManager.getSettingByName("Speed Mode").getValString().equalsIgnoreCase("Y-Port")) {
					mc.gameSettings.keyBindJump.pressed = true;
					if((mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed || mc.gameSettings.keyBindBack.pressed)) {
						if(mc.thePlayer.onGround) {
							mc.timer.timerSpeed = (float) 1.8 / 1.5999F;
							mc.thePlayer.setSprinting(true);
							mc.thePlayer.jumpMovementFactor = -(float) ((double) mc.thePlayer.jumpMovementFactor * 12);
						//	mc.thePlayer.motionY = 0.25;
						}
					}
				}
				if(Golden.instance.settingsManager.getSettingByName("Speed Mode").getValString().equalsIgnoreCase("Vanilla")) {
					setSpeed(0.5);	
					if(isMoving() && mc.thePlayer.onGround) {
						mc.thePlayer.jump();
						((EventMotion) e).setOnGround(true);
					}
				
				}
				if(Golden.instance.settingsManager.getSettingByName("Speed Mode").getValString().equalsIgnoreCase("test")) {
					mc.thePlayer.onGround = true;
					((EventMotion) e).setOnGround(true);
				}
				
						}
			
					}
				//}
			
	}
		
	
			 @Override
			    public void setup() {
			    	ArrayList<String> options = new ArrayList<String>();
			        
			        options.add("Vanilla");
			        options.add("Strafe");
			        options.add("Y-Port");
			        options.add("test");
			        Golden.instance.settingsManager.rSetting(new Setting("Speed Mode", this, "Strafe", options));
			       Golden.instance.settingsManager.rSetting(new Setting("Speed", this, 1, 0, 2, true));
			        
			       // Golden.instance.settingsManager.rSetting(new Setting("AmountZ", this, 1, 0, 2, true));
			
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


