package module.player;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import module.Category;
import module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module{

	
    public NoFall() {
        super("NoFall", Keyboard.KEY_M, Category.PLAYER);
    }
    
    public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) {
    			if(Golden.instance.settingsManager.getSettingByName("  ").getValString().equalsIgnoreCase("Packet")) {
    			if(mc.thePlayer.fallDistance > 2) {
    				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
    			}
    			}if(Golden.instance.settingsManager.getSettingByName("  ").getValString().equalsIgnoreCase("NoGround")) {
    				if(mc.thePlayer.fallDistance > 2) {
    				
    				//mc.thePlayer.onGround = true;
					((EventMotion) e).setOnGround(true);
    			}}
    			}
    		}
    	}
    
    @Override
    public void  setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("Packet");
    	options.add("NoGround");
    	 Golden.instance.settingsManager.rSetting(new Setting("  ", this, "Packet", options));
    }
}
    