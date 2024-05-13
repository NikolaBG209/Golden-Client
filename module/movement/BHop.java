package module.movement;

import org.lwjgl.input.Keyboard;

import module.Category;
import module.Module;
public class BHop extends Module{

	public BHop() {
		super("BHop", Keyboard.KEY_F, Category.MOVEMENT);
	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.keyBindJump.pressed = false;
		super.onDisable();
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.gameSettings.keyBindJump.pressed = true;
		}
	}

}
