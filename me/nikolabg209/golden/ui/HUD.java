package me.nikolabg209.golden.ui;

import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.utils.ColorUtils;
import me.nikolabg209.golden.utils.FontUtil;
import me.nikolabg209.golden.utils.font.FontUtils;
import module.Module;
import module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import module.combat.OldKillAura;
import module.render.ModsList;

import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;

public class HUD {
	float speed;
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
		
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)){
				return -1;
			}
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)){
				return 1;
			}
			return 0;
		}
		
	}
	
	
	public void draw(){
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		FontRenderer fr = mc.fontRendererObj;
		int count = 0;
		Collections.sort(ModuleManager.getModules(), new ModuleComparator());

		//if(Golden.instance.settingsManager.getSettingByName("Golden logo").getValBoolean()) {
		//	mc.getTextureManager().bindTexture(new ResourceLocation("golden/Golden logo.png"));
		//	GuiScreen.drawModalRectWithCustomSizedTexture(-12, -14, 0, 0, 35, 36, 50, 53);
		
		//}else {
		//FontUtil.drawTotalCenteredStringWithShadow("HelloWorld", 200, 100, 0x920D16);
		if(Golden.instance.settingsManager.getSettingByName("Rainbow").getValBoolean()) {
			FontUtil.drawStringWithShadow(Golden.name, 4, 4, ColorUtils.getRainbow(4, 0.8f, 1, count * 200));
		
		} else {
			fr.drawString(Golden.name, 4, 4, 0x920D16);
			
		}
	//0x920D16
		
		
		
		
		
		for(Module m : ModuleManager.getModules()) {
			if(!m.toggled)
				continue;
			
			
			
			if(Golden.instance.settingsManager.getSettingByName("Background").getValBoolean()) {
				
					// RECT
				Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 2,  count*(fr.FONT_HEIGHT + 2), sr.getScaledWidth(), 2 + fr.FONT_HEIGHT + + count*(fr.FONT_HEIGHT + 2), 0x90000000);	
					if(Golden.instance.settingsManager.getSettingByName("Outline").getValBoolean()) {
						if(Golden.instance.settingsManager.getSettingByName("Rainbow").getValBoolean()) {
						// OUTLINE
				Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 4,  count*(fr.FONT_HEIGHT + 2), sr.getScaledWidth() - fr.getStringWidth(m.name) - 2, 2 + fr.FONT_HEIGHT +  count*(fr.FONT_HEIGHT + 2), ColorUtils.getRainbow(4, 0.8f, 1, count * 200));
						}else {
							Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 4,  count*(fr.FONT_HEIGHT + 2), sr.getScaledWidth() - fr.getStringWidth(m.name) - 2, 2 + fr.FONT_HEIGHT +  count*(fr.FONT_HEIGHT + 2), 0xff9A1D1D );
						}
		        //0xff9A1D1D
				}
			}
				if(Golden.instance.settingsManager.getSettingByName("Rainbow").getValBoolean()) {
					FontUtil.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name), 2 + count*(fr.FONT_HEIGHT + 2), ColorUtils.getRainbow(4, 0.8f, 1, count * 200));
				} else {
					FontUtil.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name), 2 + count*(fr.FONT_HEIGHT + 2), 0xff0000);
				
				}
		//0xff0000
		
		count++;
			}		
		}
	}


