package module.render;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.utils.esp.ChestESPUtils;
import module.Category;
import module.Module;
import net.minecraft.tileentity.TileEntityChest;

public class ChestESP extends Module{
	
	public ChestESP() {
		super("ChestESP", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onRender() {
		if(this.isToggled()) {
			for(Object o: mc.theWorld.loadedTileEntityList) {
				if(o instanceof TileEntityChest) {
					ChestESPUtils.blockESPbox(((TileEntityChest)o).getPos());
				}
			}
		}
	}
	

}
