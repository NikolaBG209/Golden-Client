package me.nikolabg209.golden;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.SettingsManager;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventChat;
import me.nikolabg209.golden.extensions.FileManager;
import me.nikolabg209.golden.ui.HUD;
import me.nikolabg209.golden.utils.font.FontUtils;
import me.nikolabg209.golden.alt.AltManager;
import me.nikolabg209.golden.command.CommandManager;
import module.Module;
import module.ModuleManager;
import module.combat.*;
import module.render.ModsList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Golden {
	
	public static Golden instance = new Golden();
	public static SettingsManager settingsManager;
	public static ClickGUI clickGUI;
	public static AltManager altManager;
	public static FileManager fileManager;
	
	public static String name = "Golden", version = "B14", creator = "NikolaBG209";
	public static CopyOnWriteArrayList<Module> module = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	public static CommandManager commandManager = new CommandManager();
	
	
	
	public static void startup() {
		System.out.println("Starting " + name + version);
		FontUtils.bootstrap();
		module.add(new OldKillAura());
		
		
		
	}
	
	
	public static void key(int key){
		for(Module m : module){
			if(m.getKey() == key){
				m.toggle();
			}
		}
	}
	
	
	
	public static void onEvent(Event e) {
		if(e instanceof EventChat) {
			commandManager.handleChat((EventChat)e);
		}
		
		for(Module m : ModuleManager.getModules()) {
			if(!m.toggled)
				continue;
			m.onEvent(e);
		}
		
	}

	public static void keyPress(int key) {
		for(Module m : ModuleManager.getModules()) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}

	public static ModuleManager ModuleManager;
	
	public static void startClient() {
		settingsManager = new SettingsManager();
		ModuleManager = new ModuleManager();
		clickGUI = new ClickGUI();
		altManager = new AltManager();
		fileManager = new FileManager();
		
		fileManager.init();
		
		Display.setTitle(name + " " + version + " by " + creator);
	}
	
	public static void stopClient() {
		Golden.instance.fileManager.saveMods();
	}
	
	public static void addChatMessage(String message) {
		message = "\2479" +  name + "\2477: " + message;
		
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}

}