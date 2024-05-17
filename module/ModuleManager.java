package module;

import java.util.ArrayList;

import me.nikolabg209.golden.events.Event;
import module.combat.*;
import module.exploit.*;
import module.movement.*;

import module.player.*;
import module.render.*;
import module.ghost.*;

public class ModuleManager {
	
	private static ArrayList<Module> module;
	
	public ModuleManager() {
		module = new ArrayList<Module>();
		//COMBAT	
		
		newModule(new KillAura());
		newModule(new Velocity());
		newModule(new OldKillAura());
	
		//MOVEMENT
		
		newModule(new Step());
	//	newModule(new BHop());
		newModule(new Glide());
		newModule(new Timer());
		newModule(new Speed());
	    newModule(new Jesus());
	    newModule(new NoSlow());
	    newModule(new Flight());
	    newModule(new Sprint());
	    newModule(new Spider());
	    newModule(new Jetpack());
	    newModule(new Dolphin());
	    newModule(new Parkour());
	    newModule(new AutoWalk());
	   
	   
		//PLAYER
	    newModule(new NoFall());
	    newModule(new AntiAFK());
	    newModule(new FastBow());
	    newModule(new Scaffold());
	    newModule(new FastPlace());
	    newModule(new AntiCobweb());
	    newModule(new FastLadder());
	    newModule(new ScaffoldRecode());
	   
	    
		
		//RENDER
	    newModule(new ESP());
	    newModule(new ClickGui());
	    newModule(new ModsList());
	    newModule(new FullBright());
	    
	   // newModule(new ChestESP());
	    
		//MISC
	    
	    //GHOST
	    newModule(new AimBot());
	    newModule(new AutoClicker());
	    newModule(new LegitScaffold());
	    
	    //EXPLOIT
	    newModule(new Disabler());
	}

	
	public static void newModule(Module m) {
		module.add(m);
	}
	
	
	
	public static ArrayList<Module> getModules(){
		return module;
	}
	
	public static void onUpdate() {
		for(Module m : module) {
			m.onUpdate();
		}
	}
	
	public static void onRender() {
		for(Module m : module) {
			m.onRender();
		}
	}
	
	public static void onKey(int k) {
		for(Module m : ModuleManager.getModules()) {
			if(m.getKey() == k) {
				m.toggle();
			}
		}
	}
			public static void onEvent(Event e) {
				for(Module m : module) {
					if(!m.toggled)
						continue;	
					m.onEvent(e);
		}
	}
			public static Module getModuleByName(String moduleName) {
				for(Module m : ModuleManager.getModules()) {
					if(!m.getName().trim().equalsIgnoreCase(moduleName) && !m.toString().equalsIgnoreCase(moduleName.trim())) continue;
					return m;
				}
				return null;
			}
			
	}

