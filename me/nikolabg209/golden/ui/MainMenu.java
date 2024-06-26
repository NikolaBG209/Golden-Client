package me.nikolabg209.golden.ui;

import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.alt.GuiAltManager;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen {
	float speed;

	public MainMenu() {

	}

	public void initGui() {

	}

	public void drawScreen(int mouseX, int mouseY, float particalticks) {
		// Drawing the wallpaper and the buttons
		mc.getTextureManager().bindTexture(new ResourceLocation("golden/mainMenuBackground.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

		String[] buttons = { "SinglePlayer", "MultiPlayer", "Alt Manager", "Settings", "Language", "Quit" };

		int count = 0;
		for (String name : buttons) {

			float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8
					- mc.fontRendererObj.getStringWidth(name) / 2f;
			float y = height - 20;

			boolean hovered = (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name)
					&& mouseY < y + mc.fontRendererObj.FONT_HEIGHT);

			this.drawCenteredString(mc.fontRendererObj, name,
					(width / buttons.length) * count + (width / buttons.length / 2f + 8), y, hovered ? 0x920D16 : -1);
			count++;
		}

		GlStateManager.pushMatrix();
		GlStateManager.translate(width / 2f, height / 2f, 0);
		GlStateManager.scale(3, 3, 0);
		GlStateManager.translate(-(width / 2f), -(height / 2f), 0);

		this.drawCenteredString(fontRendererObj, Golden.name, width / 2f,
				height / 2.5f - mc.fontRendererObj.FONT_HEIGHT / 2f, -1);
		GlStateManager.popMatrix();

	}

	public void mouseClicked(int mouseX, int mouseY, int button) {
		// Sets what buttons do
		String[] buttons = { "SinglePlayer", "MultiPlayer", "Alt Manager", "Settings", "Language", "Quit" };

		int count = 0;
		for (String name : buttons) {
			float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8
					- mc.fontRendererObj.getStringWidth(name) / 2f;
			float y = height - 20;

			if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name)
					&& mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
				switch (name) {
				
				case "SinglePlayer":
					mc.displayGuiScreen(new GuiSelectWorld(this));
					break;
					
				case "MultiPlayer":
					mc.displayGuiScreen(new GuiMultiplayer(this));
					break;
					
				case "Alt Manager":
					mc.displayGuiScreen(new GuiAltManager());
					break;
					
				case "Settings":
					mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
					break;
					
				case "Language":
					mc.displayGuiScreen(new GuiLanguage(this,mc.gameSettings,  mc.getLanguageManager()));
					break;
					
				case "Quit":
					mc.shutdown();
					break;
				}
			}
			
			count++;
		}

	}

	public void onGuiClosed() {

	}

}
