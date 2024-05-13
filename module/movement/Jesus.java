package module.movement;

import module.Category;
import module.Module;

public class Jesus extends Module{
	public Jesus() {
		super("Jesus", 0, Category.MOVEMENT);
	}
	
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.isInWater()) {
				mc.thePlayer.motionY = 0.5;
			}
		}
	}

}
