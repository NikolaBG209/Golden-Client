package me.nikolabg209.golden.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.nikolabg209.golden.Golden;
import me.nikolabg209.golden.command.impl.*;
import me.nikolabg209.golden.events.listeners.EventChat;

public class CommandManager {
	
	public List<Command> commands = new ArrayList<Command>();
	public String prefix = ".";
	
	
	public CommandManager() {
		setup();
	}
	
	public void setup() {
		commands.add(new Toggle());
		commands.add(new Bind());

		
	}
	
	public void handleChat(EventChat event) {
		String message = event.getMessage();
		
		if(!message.startsWith(prefix))
			return;
		
		event.setCancelled(true);
		
		message = message.substring(prefix.length());
		
		boolean foundCommand = false;
		
		if(message.split(" ").length > 0) {
		String CommandName = message.split(" ")[0];
		
		for(Command c: commands) {
			if(c.aliases.contains(CommandName) || c.name.equalsIgnoreCase(CommandName)) {
				c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
				foundCommand = true;
						break;
			}
				
		}
	}
	
	if(!foundCommand) {
		Golden.addChatMessage("Could not find command.");
	}
	
	}
}
