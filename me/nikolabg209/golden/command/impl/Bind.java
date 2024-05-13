package me.nikolabg209.golden.command.impl;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.command.Command;
import module.Module;
import module.ModuleManager;

public class Bind extends Command{

	public Bind() {
		super("Bind", "Binds a module by name.", "bind <name> <key>", "b" );
		
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];
			
			for(Module module : ModuleManager.getModules()) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.setKey(Keyboard.getKeyIndex(keyName.toUpperCase()));
					
					Golden.addChatMessage(String.format("Bound %s to %s", module.name, Keyboard.getKeyName(module.getKey())));
					
					
				}
			}
			
		}
		
		if(args.length ==1) {
			if(args[0].equalsIgnoreCase("clear")) {
				for(Module module : ModuleManager.getModules()) {
				module.setKey(Keyboard.KEY_NONE);
			}
			}
			
			Golden.addChatMessage("Cleared all binds.");
	}
	}
}