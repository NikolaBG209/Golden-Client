package module.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.utils.Timer;
import module.Category;
import module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class AimBot extends Module{
	public Timer timer = new Timer();

	public AimBot() {
		super("AimBot", Keyboard.KEY_NONE, Category.GHOST);
		
	}
	 public void onEnable(){
   	  
	    }
	    
	    public void onDisable(){
	    	
	    }
	    
	    public void onEvent(Event e)  {
	    	if(e instanceof EventMotion) {
	    		if(e.isPre()) {
	    			EventMotion event = (EventMotion) e;
	    			
	    			List<EntityLivingBase> targets = (List<EntityLivingBase>) mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
	    			
	    		    targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < 4 && entity != mc.thePlayer && !entity.isDead && entity.getHealth() > 0).collect(Collectors.toList());
	    		    
	    		    targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
	    		    
	    		    if(!targets.isEmpty()) {
	    		    	EntityLivingBase target = targets.get(0);
	    		    	
	    		    	if(mc.gameSettings.keyBindAttack.pressed) {
	    		    	
	    		    	mc.thePlayer.rotationYaw = (getRotations(target)[0]);
	    		    	mc.thePlayer.rotationPitch = (getRotations(target)[1]);
	    		    	}
	    		    	
	    		      	if(timer.hasTimeElapsed(1000 / 10, true)) {
	    		    	
	    		    	
	    		      	}
	    		    	
	    		    		

	    		}
	    	}
	    }
}
	    

public float[] getRotations(Entity e) {
	double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
		   deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
		   deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
		   distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
	
	float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
			pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
	
	if(deltaX < 0 && deltaZ < 0) {
		yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
	}else if(deltaX > 0 && deltaZ < 0) {
		yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
	}
	return new float[] { yaw, pitch};
	}
}
