package module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.utils.esp.ChestESPUtils;
import me.nikolabg209.golden.utils.esp.MobESPUtils;
import module.Category;
import module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;

public class ESP extends Module{

	public ESP() {
		super("ESP", Keyboard.KEY_NONE, Category.RENDER);
	
	}
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<String>();
		Golden.instance.settingsManager.rSetting(new Setting("ChestESP", this, false));
		Golden.instance.settingsManager.rSetting(new Setting("MobESP", this, false));
		
	}

	@Override
	public void onRender() {
		if(this.isToggled()) {
			for(Object p: mc.theWorld.loadedEntityList) {
				if(p instanceof EntityPlayer) {
					if(p != mc.thePlayer) {
					MobESPUtils.entityESPBox((Entity)p, 3);
				
					MobESPUtils.entityESPBox((Entity)p, 0);
					}if(Golden.instance.settingsManager.getSettingByName("ChestESP").getValBoolean()) {
						for(Object o: mc.theWorld.loadedTileEntityList) {
							if(o instanceof TileEntityChest) {
								ChestESPUtils.blockESPbox(((TileEntityChest)o).getPos());
							}
						}
					}if(Golden.instance.settingsManager.getSettingByName("MobESP").getValBoolean()) {
						for(Object m : mc.theWorld.loadedEntityList) {
							if(m instanceof EntityMob) {
								MobESPUtils.entityESPBox((Entity)m, 0);
							
						
					}
				}
				
			}
				
		}
	}

}
	}
}
