package me.nikolabg209.golden.command.impl;

import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.command.Command;
import module.Module;
import module.ModuleManager;

public class Toggle extends Command{

	public Toggle() {
		super("Toggle", "Toggles a module by name.", "toggle <name>", "t" );
		
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0];
			
			boolean foundModule = false;
			
			for(Module module : ModuleManager.getModules()) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					
					Golden.addChatMessage((module.isToggled() ? "Enabled" : "Disabled") + " " + module.name);
					
					
					foundModule = true;
					break;
				}
				
			}
			if(!foundModule) {
				Golden.addChatMessage("Could not find module.");
			}
			
		}
		
	}

}
