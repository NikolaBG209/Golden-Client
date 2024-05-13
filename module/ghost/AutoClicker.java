package module.ghost;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.utils.Timer;
import module.Category;
import module.Module;

public class AutoClicker extends Module{
	public Timer timer = new Timer();
	public AutoClicker() {
		
		super("AutoClicker", 0, Category.GHOST);
	}
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.gameSettings.keyBindAttack.pressed) {
			Setting CPS = Golden.instance.settingsManager.getSettingByName("CPS");
			if(timer.hasTimeElapsed(1000 / CPS.getValDouble(), true)) {
				mc.clickMouse();
		}
			}
	}
	
		
	}
	@Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
    	
        Golden.instance.settingsManager.rSetting(new Setting("CPS", this, 10, 1.0, 15, true));
	}
}
