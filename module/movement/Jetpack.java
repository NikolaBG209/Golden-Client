package module.movement;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.utils.Invoker;
import module.Category;
import module.Module;

public class Jetpack extends Module{

	public Jetpack() {
		super("Jetpack", 0, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.gameSettings.keyBindJump.pressed) {
				if(Golden.instance.settingsManager.getSettingByName("Mode2").getValString().equalsIgnoreCase("Jump")) {
				mc.thePlayer.jump();
			}else if(Golden.instance.settingsManager.getSettingByName("Mode2").getValString().equalsIgnoreCase("Motion")) {
				Invoker.setMotionY(0.30D);
			}
			}
		}
	}
	 @Override
	    public void setup() {
	    	ArrayList<String> options = new ArrayList<String>();
	    	options.add("Motion");
	    	options.add("Jump");
	    	 Golden.instance.settingsManager.rSetting(new Setting("Mode2", this, "Jump", options));
	        
	    }

}