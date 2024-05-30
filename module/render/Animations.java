package module.render;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.events.listeners.EventUpdate;
import me.nikolabg209.golden.events.listeners.RenderItemEvent;
import module.Category;
import module.Module;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumAction;
import net.minecraft.util.MathHelper;

public class Animations extends Module{
	
	EnumAction itemAction = RenderItemEvent.getEnumAction();
	private int attacks;
	    private int swing;
	    private Entity target;
	    
	    
	    
	public Animations() {
		super("Animations", 0, Category.RENDER);
	}
	
	 public void onEvent(Event event)  {
	    	if(event instanceof RenderItemEvent) {
	    		if(event.isPre()) { 
	    			if (mc.thePlayer.isUsingItem()) {
	    			
	    			float animationProgression = ((RenderItemEvent) event).getSwingProgress();
	            	float SwingProgression = RenderItemEvent.getAnimationProgression();
	            	float convertedProgress = MathHelper.sin(MathHelper.sqrt_float(animationProgression) * (float)Math.PI);
	    			
	    			ItemRenderer.transformFirstPersonItem(animationProgression / 2.0f, 0.0f);
                    GlStateManager.translate(0.0f, 0.3f, -0.0f);
                    GlStateManager.rotate(-convertedProgress * 31.0f, 1.0f, 0.0f, 2.0f);
                    GlStateManager.rotate(-convertedProgress * 33.0f, 1.5f, convertedProgress / 1.1f, 0.0f);
                    ItemRenderer.blockTransformation();
	                        
	    			}
	    		}
	    	}
	   	}

}
