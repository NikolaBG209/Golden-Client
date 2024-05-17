package module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import module.Category;
import module.Module;
import module.ModuleManager;
import me.nikolabg209.golden.*;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventUpdate;
public class ClickGui extends Module{

        public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.RENDER);
    }

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("New");
        options.add("JellyLike");
       
        Golden.instance.settingsManager.rSetting(new Setting("Design", this, "New", options));
        Golden.instance.settingsManager.rSetting(new Setting("Sound", this, false));
       
        Golden.instance.settingsManager.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
        Golden.instance.settingsManager.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
        Golden.instance.settingsManager.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        mc.displayGuiScreen(Golden.instance.clickGUI);
        toggle();
    }
    public void onUpdate() {
    	if(this.getKey() == Keyboard.KEY_NONE && this.getKey() == 0) {
    	this.setKey(Keyboard.KEY_RSHIFT);
    	}
    }
    
    			
}