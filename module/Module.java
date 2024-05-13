package module;

import me.nikolabg209.golden.events.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.Packet;

public class Module {
	
	public String name;
	public int key;
	public boolean toggled;
	public Category category;
	public int keyCode;
	public Minecraft mc = Minecraft.getMinecraft();	
	
	public Module(String name, int k, Category c) {
		this.name = name;
		key = k;
		category = c;
		toggled = false; 
		setup();
	
		
		}
	public Module(String name, int key) {
		this.name = name;
		this.keyCode = key;
		
	}
			public int getKey() {
			return keyCode;
		}
	
	public void onEvent(Event e) {
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		}else {
			onDisable();
		}
	}
	
	public void onEnable() {}
	public void onDisable() {}
	public void onUpdate() {}
	public void onRender() {}
	public void setup() {}

	public Minecraft getMc() {
		return mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

	public void setKey(int key) {
		this.keyCode = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	protected EntityPlayerSP player() {
		return mc.thePlayer;
	}
	protected PlayerControllerMP playerController() {
		return mc.playerController;
	}
	protected WorldClient world() {
		return mc.theWorld;
	}
	@SuppressWarnings("rawtypes")
	protected void sendPacket(Packet p) {
		
	}
	
	public void enableOnStartUp() {
		toggled = true;
		try {
			toggle();
			onEnable();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
