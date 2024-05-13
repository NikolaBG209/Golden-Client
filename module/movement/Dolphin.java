package module.movement;

import org.lwjgl.input.Keyboard;

import module.Category;
import module.Module;

public class Dolphin extends Module{

	public Dolphin() {
		super("Dolphin", Keyboard.KEY_B, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.isInWater()) {
				mc.thePlayer.motionY += 0.04;
			}
		}
	}

}