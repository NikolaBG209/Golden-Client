package module.ghost;

import module.Category;
import module.Module;

public class NoClickDelay extends Module{
	
	public NoClickDelay() {
		super("NoClickDelay", 0, Category.GHOST);
	}
	
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer != null && mc.theWorld != null) {
				mc.rightClickDelayTimer = 0;
			}
		}
	}

}
