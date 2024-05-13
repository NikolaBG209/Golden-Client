package me.nikolabg209.golden.extensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.Hero.clickgui.ClickGUI;
import me.nikolabg209.golden.utils.JsonUtils;
import module.Module;
import module.ModuleManager;

public class FileManager {
	
	public static File ROOT_DIR = new File("Golden");
	public static File modules = new File(ROOT_DIR, "modules.json");
	
	
	public void init() {
		
		// MAKE ROOT DIRECTORY
		if(!ROOT_DIR.exists()) { ROOT_DIR.mkdirs(); }
		// HANDLES Module
		if(!modules.exists())
			saveMods();
		else
			loadMods();
	}
	
	public void saveMods() {
		try {
			JsonObject json = new JsonObject();
			for(Module mod : ModuleManager.getModules()) {
				JsonObject jsonMod = new JsonObject();
				jsonMod.addProperty("Enabled", mod.isToggled());
				json.add(mod.getName(), jsonMod);
				
			}
			PrintWriter save = new PrintWriter(new FileWriter(modules));
			save.println(JsonUtils.prettyGson.toJson(json));
			save.close();	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private HashSet<String> modBlackList = Sets.newHashSet(ClickGUI.class.getName());
	
	public boolean IsModBlackListed(Module m) {
		return modBlackList.contains(m.getClass().getName());
	}

	public void loadMods() {
		try {
			BufferedReader load = new BufferedReader(new FileReader(modules));
			JsonObject json = (JsonObject)JsonUtils.jsonParser.parse(load);
			load.close();
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
			while(itr.hasNext()){
				Entry<String, JsonElement> entry = itr.next();
				Module mod = ModuleManager.getModuleByName(entry.getKey());
				if(mod != null) {
					JsonObject jsonModule = (JsonObject)entry.getValue();
					boolean enabled = jsonModule.get("Enabled").getAsBoolean();
					if(enabled) 
						mod.toggle();
					}
		
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			
}
	
		
	

