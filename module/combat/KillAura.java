package module.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import module.Category;
import module.Module;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import me.nikolabg209.golden.utils.*;
import me.nikolabg209.golden.utils.esp.MobESPUtils;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.network.play.client.C0APacketAnimation;
import de.Hero.settings.Setting;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.*;


public class KillAura extends Module{
	
	public Timer timer = new Timer();
	
	
    public KillAura() {
        super("KillAura", 0, Category.COMBAT);
    }
    
    public void onEnable(){
    	  
    }
    
    public void onDisable(){
    	
    }
    @Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	
        Golden.instance.settingsManager.rSetting(new Setting("Range", this, 4.0, 1.0, 6.0, true));
        Golden.instance.settingsManager.rSetting(new Setting("APS", this, 10.0, 1.0, 20.0, true));
        Golden.instance.settingsManager.rSetting(new Setting("NoSwing", this, false));
        Golden.instance.settingsManager.rSetting(new Setting("SilentRotation", this, false));
        Golden.instance.settingsManager.rSetting(new Setting("Keep Sprint", this,  false));
       // Golden.instance.settingsManager.rSetting(new Setting("Players", this, true));
       // Golden.instance.settingsManager.rSetting(new Setting("Animals", this, false));
        //Golden.instance.settingsManager.rSetting(new Setting("Mods", this, false));
        
        Setting Range = Golden.instance.settingsManager.getSettingByName("Range");
    }
    public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) {
    			
    			Setting Range = Golden.instance.settingsManager.getSettingByName("Range");
    			Setting APS = Golden.instance.settingsManager.getSettingByName("APS");
    			Setting NoSwing = Golden.instance.settingsManager.getSettingByName("NoSwing");

    			
    			EventMotion event = (EventMotion) e;
    			
    			List<EntityLivingBase> targets = (List<EntityLivingBase>) mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
    			
    		    targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < Range.getValDouble() && entity != mc.thePlayer && !entity.isDead && entity.getHealth() > 0 && !entity.hasCustomName()).collect(Collectors.toList());
    		    
    		    targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
    		   
    		    targets = targets.stream().filter(EntityPlayer.class :: isInstance).collect(Collectors.toList());
    		    
    		   
    		    if(!targets.isEmpty()) {
    		    	EntityLivingBase target = targets.get(0);
    		    	if(Golden.instance.settingsManager.getSettingByName("SilentRotation").getValBoolean()) {
    		    		// ROTATIONS
    		    		// SILENT ROTATION
    		    	event.setYaw ((float) (getRotations(target)[0] + (Math.random() - 0.5) * 2));
    		    	event.setPitch ((float) (getRotations(target)[1] + (Math.random() - 0.5) * 2));
    		    	mc.thePlayer.rotationYawHead = (float) (getRotations(target)[0] + (Math.random() - 0.5) * 2);
    		    	mc.thePlayer.renderYawOffset = (float) (getRotations(target)[0] + (Math.random() - 0.5) * 2);
    		    	mc.thePlayer.rotationPitchHead = (float) (getRotations(target)[1] + (Math.random() - 0.5) * 2);
    		    	mc.thePlayer.setSprinting(Golden.instance.settingsManager.getSettingByName("Keep sprint").getValBoolean());
    		    	
    		    	} else {
    		    		
    		    		// LEGIT ROTATION
    		    		mc.thePlayer.rotationYaw = (float) (getRotations(target)[0] + (Math.random() - 0.5) * 2);
    		    		mc.thePlayer.setSprinting(Golden.instance.settingsManager.getSettingByName("Keep sprint").getValBoolean());
    		    		mc.thePlayer.rotationPitch = (float) (getRotations(target)[1] + (Math.random() - 0.5) * 2);
    		    		
    		    	}
    		    		// ATTACK
    		    	if(timer.hasTimeElapsed(1000 / APS.getValDouble(), true) && !mc.thePlayer.isBlocking()) {
    		    		if(Golden.instance.settingsManager.getSettingByName("NoSwing").getValBoolean()) {
    		    			// NO SWING
    		    			mc.thePlayer.setSprinting(Golden.instance.settingsManager.getSettingByName("Keep sprint").getValBoolean());
        		    		mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
    		    		
    		    		}else {
    		    			
    		    			// NORMAL
    		    		mc.thePlayer.swingItem();
    		    		mc.thePlayer.setSprinting(Golden.instance.settingsManager.getSettingByName("Keep sprint").getValBoolean());
    		    		 
    		    		mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
    		    		}
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
    
    