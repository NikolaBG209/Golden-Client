package module.ghost;

import module.Category;
import module.Module;
import net.minecraft.entity.Entity;

public class LegitScaffold extends Module{
	
	public LegitScaffold() {
		super("LegitScaffold", 0, Category.GHOST);
	}
	@Override
	public void onUpdate() {
	if(this.isToggled()) {	
	
	if(mc.thePlayer.onGround && !mc.thePlayer.isSprinting() &&
			this.mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(-0.3D, -0.1D, -0.3D).offset(0.3D, -0.1D, 0.3D).expand(-0.3D, 0.0D, -0.3D)).isEmpty() 
			) {
		mc.gameSettings.keyBindSneak.pressed = true;
		
		
	}else {
		mc.gameSettings.keyBindSneak.pressed = false;
	}
	
	
	}
	}
}
