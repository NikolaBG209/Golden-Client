package module.combat;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import module.Category;
import module.Module;

public class Criticals extends Module{
	
	public Criticals() {
		super("Criticals", 0, Category.COMBAT);
	}
	
	public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) {
    			((EventMotion) e).setOnGround(false);
    		}
    	}
   	}
	
	@Override
	public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("NoGround");
    	Golden.instance.settingsManager.rSetting(new Setting("Criticals Mode", this, "NoGround", options));

	}

}
