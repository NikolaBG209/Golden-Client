package module.player;

import org.lwjgl.input.Keyboard;

import module.Category;
import module.Module;


public class FastPlace extends Module{

	public FastPlace() {
		super("FastPlace", Keyboard.KEY_B, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.rightClickDelayTimer = 0;
		}
	}

}