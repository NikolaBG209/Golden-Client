package module.movement;
import net.minecraft.item.*;
import module.Category;
import module.Module;

public class Tower extends Module{
	
	private static final Object ItemBlock = null;


	public Tower() {
		super("Tower", 0, Category.MOVEMENT);
	}
	
	ItemBlock itemBlock;
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.gameSettings.keyBindJump.pressed) {
				
			//	if(mc.thePlayer.getCurrentEquippedItem().equals(itemBlock)) {
					mc.thePlayer.motionY += 0.08;
				
			}
		}
	}

}
