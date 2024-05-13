package module.player;

import org.lwjgl.input.Keyboard;

import module.Category;
import module.Module;
import me.nikolabg209.golden.utils.*;

public class FastLadder extends Module{
	
	private int ticks = 0;

	public FastLadder() {
		super("FastLadder", 0, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			ticks++;
			if(Invoker.isOnLadder() && Keyboard.isKeyDown(Invoker.getForwardCode())) {
				Invoker.setMotionY(0.25);
			}else if(Invoker.isOnLadder() && !Keyboard.isKeyDown(Invoker.getForwardCode())) {
				Invoker.setMotionY(-0.25);
			}
		}
	}

}