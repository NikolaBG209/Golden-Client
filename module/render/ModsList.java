package module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import module.Category;
import module.Module;

public class ModsList extends Module{
	

	

	public ModsList() {
		super("Interface", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@Override
    public void setup() {
        ArrayList<String> options = new ArrayList<String>();
        
        Golden.instance.settingsManager.rSetting(new Setting("Background", this, true));
        Golden.instance.settingsManager.rSetting(new Setting("Outline", this, true));
      //  Golden.instance.settingsManager.rSetting(new Setting("Golden logo", this,false));
        Golden.instance.settingsManager.rSetting(new Setting("Rainbow", this, true));
        
	}
	
	public void onEnable() {
		
	}
		
	}

