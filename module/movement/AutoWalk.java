package module.movement;

import module.*;
import org.lwjgl.input.Keyboard;

public class AutoWalk extends Module{
	
	public AutoWalk() {
		
	super ("AutoWalk", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.gameSettings.keyBindForward.pressed = true;
		}
	}
	@Override
	public void onDisable() {
		mc.gameSettings.keyBindForward.pressed = true;
		super.onDisable();
		
	}
	
		
	}


