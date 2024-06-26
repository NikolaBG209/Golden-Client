package module.movement;

import org.lwjgl.input.Keyboard;

import module.Category;
import module.Module;
import net.minecraft.entity.Entity;

public class Parkour extends Module{

	public Parkour() {
		super("Parkour", Keyboard.KEY_Z, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.onGround && !mc.thePlayer.isSneaking() && !this.mc.gameSettings.keyBindSneak.pressed &&
					this.mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D, -0.1D, 0.0D).expand(-0.001D, 0.0D, -0.001D)).isEmpty())
				mc.thePlayer.jump();
		}
	}

}