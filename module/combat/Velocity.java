package module.combat;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import module.Category;
import module.Module;

public class Velocity extends Module{
	public Velocity() {
		super("Velocity", 0, Category.COMBAT);
		
	}
	public void onEvent(Event e)  {
    	if(e instanceof EventUpdate) {
    		if(e.isPre()) {
    			float horizontal = (float) Golden.instance.settingsManager.getSettingByName("Horizontal").getValDouble();
    			float vertical =  (float) Golden.instance.settingsManager.getSettingByName("Vertical").getValDouble();
    			
  			if(mc.thePlayer.hurtTime > 0) {
  				if(Golden.instance.settingsManager.getSettingByName("Velocity Mode").getValString().equalsIgnoreCase("Motion")) {
  				mc.thePlayer.motionX *= horizontal / 100;
  				
  				
  				
  				mc.thePlayer.motionZ *= horizontal / 100;
  					//mc.thePlayer.setVelocity(0, 0, 0);
  				}
  			}
  				
  					
  			
  				
    			
    			
    		}
    	}
	}
	@Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("Motion");
    	options.add("Velocity");
    	Golden.instance.settingsManager.rSetting(new Setting("Velocity Mode", this, "Motion", options));
        Golden.instance.settingsManager.rSetting(new Setting("Horizontal", this, 90, 0, 100, true));
        Golden.instance.settingsManager.rSetting(new Setting("Vertical", this, 90, 0, 100, true));

	}

}
