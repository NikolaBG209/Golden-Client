package module.player;

import org.apache.commons.lang3.time.StopWatch;

import de.Hero.settings.Setting;
import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import me.nikolabg209.golden.utils.MathUtil;
import me.nikolabg209.golden.utils.Timer;
import module.Category;
import module.Module;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;

public class ChestStealer extends Module{
	
    private long nextClick;
    private int lastClick;
    private int lastSteal;
	Timer timer = new Timer();
	public ChestStealer() {
		super("ChestStealer", 0, Category.PLAYER);
	}
	
	@Override
	public void setup() {
        //Golden.instance.settingsManager.rSetting(new Setting("Speed", this, 100, 0, 300,  true));

	}
	

    
	public void onEvent(Event e)  {
    	if(e instanceof EventMotion) {
    		if(e.isPre()) { 
    			

    			 if (timer.hasTimeElapsed(1000 / 17, true)) {
        if (mc.currentScreen instanceof GuiChest) {
            ContainerChest container = (ContainerChest)mc.thePlayer.openContainer;
            
            ++this.lastSteal;
            for (int i = 0; i < container.inventorySlots.size(); ++i) {
                ItemStack stack = container.getLowerChestInventory().getStackInSlot(i);
                if (stack == null || this.lastSteal <= 1) continue;
               
                this.nextClick = Math.round(MathUtil.getRandom(20, 17));
                
                mc.playerController.windowClick(container.windowId, i, 0, 1, mc.thePlayer);
                
                this.lastClick = 0;
                if (this.nextClick <= 0L) continue;
                return;
            }
             
        
            ++this.lastClick;
            if (this.lastClick > 1) {
                mc.thePlayer.closeScreen();
            }
             
        } else {
            this.lastClick = 0;
            this.lastSteal = 0;
        }
        }
    };
}
}
}
