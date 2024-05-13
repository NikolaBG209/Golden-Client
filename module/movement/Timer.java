package module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.utils.Invoker;
import module.Category;
import module.Module;

public class Timer extends Module{
	
    public Timer() {
        super("Timer", Keyboard.KEY_NONE, Category.MOVEMENT);
    }
    public void onDisable() {
    	Invoker.setTimerSpeed(1);
    }
    
    @Override
    public void onUpdate() {
    	if(this.isToggled()) {
    		Invoker.setTimerSpeed((float) Golden.instance.settingsManager.getSettingByName("Speed").getValDouble());
    	}
    }
    @Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<String>();
       
        
        
        
        Golden.instance.settingsManager.rSetting(new Setting("Speed", this, 2, 1, 5, true));
}
}
