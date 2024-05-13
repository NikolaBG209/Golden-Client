package module.player;

import net.minecraft.block.Block;
import module.*;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.*;
import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Scaffold extends Module{

	public Scaffold() {
		super("Scaffold", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@Override
public void onUpdate() {
		if(this.isToggled()) {
	
    			
			Entity p = player();
			//mc.thePlayer.rotationPitch
			//= (-75);
			BlockPos bp = new BlockPos(p.posX, p.getEntityBoundingBox().minY, p.posZ);
			
			// BLOCK PLACEMENT DIRECTIONS 
			
             if(valid(bp.add(0, -2, 0))){
				
				place(bp.add(0, -1, 0), EnumFacing.UP);
             
             }else if (valid(bp.add(-1, -1, 0))){
				
				place(bp.add(0, -1, 0), EnumFacing.EAST);
				mc.thePlayer.rotationPitch = (75.5F);
				mc.thePlayer.rotationYaw = (135);
             }else if (valid(bp.add(1, -1, 0))){
				
				place(bp.add(0, -1, -1), EnumFacing.WEST);
				mc.thePlayer.rotationPitch = (75);
				mc.thePlayer.rotationYaw = (45);
             }else if (valid(bp.add(0, -1, -1))){
				
				place(bp.add(0, -1, 0), EnumFacing.SOUTH);
				mc.thePlayer.rotationPitch = (75.5F);
				mc.thePlayer.rotationYaw = (-135);
             }else if (valid(bp.add(0, -1, 1))){
				
				place(bp.add(0, -1, 0), EnumFacing.NORTH);
				mc.thePlayer.rotationPitch = (75.5F);
				mc.thePlayer.rotationYaw = (45);
             }else if (valid(bp.add(1, -1, 1))) {
				
				if(valid(bp.add(0, -1, 1)))
					
					place(bp.add(0, -1, 1), EnumFacing.NORTH);
				place(bp.add(1, -1, 1), EnumFacing.EAST);
			}else if (valid(bp.add(-1, -1, 1))) {
				if(valid(bp.add(-1, -1, 0)))
					place(bp.add(0, -1, 1), EnumFacing.WEST);
				
				place(bp.add(-1, -1, 1),EnumFacing.SOUTH);
			}else if (valid(bp.add(-1, -1, -1))) {
				if(valid(bp.add(0, -1, -1)))
					place(bp.add(0, -1, 1), EnumFacing.SOUTH);
				place(bp.add(-1, -1, 1), EnumFacing.WEST);
			}else if (valid(bp.add(1, -1 , -1))) {
				if(valid(bp.add(1, -1, 0)))
					place(bp.add(1, -1, 0), EnumFacing.EAST);
				place(bp.add(1, -1, -1), EnumFacing.NORTH);
			}}}
		
	

	// PLACEMENT METHOD
	
	void place(BlockPos p, EnumFacing f) {
		if(f == EnumFacing.UP){
			
			
			p = p.add(0, -1, 0);
			//mc.thePlayer.rotationPitch = (75.5F);
			//mc.thePlayer.rotationYaw = (45);
			
		}else if(f == EnumFacing.NORTH) {
			p = p.add(0, 0, 1);
			mc.thePlayer.rotationPitch = (75.5F);
			mc.thePlayer.rotationYaw = (45);}
	else if(f == EnumFacing.EAST){
			p = p.add(-1, 0, 0);
			mc.thePlayer.rotationPitch = (75.5F);
			mc.thePlayer.rotationYaw = (135);
	}
	else if(f == EnumFacing.SOUTH){
			p = p.add(0, 0, -1);
			mc.thePlayer.rotationPitch = (75.5F);
			mc.thePlayer.rotationYaw = (-135);}
			
	else if(f == EnumFacing.WEST){
	   mc.thePlayer.rotationPitch = (75);
		mc.thePlayer.rotationYaw = (45);
			p = p.add(1, 0, 0);
	
	}
		EntityPlayerSP _p = player();
		
		if(_p.getHeldItem() != null && _p.getHeldItem().getItem() instanceof ItemBlock) {
			_p.swingItem();
			playerController().func_178890_a(_p, world(), _p.getHeldItem(), p, f, new Vec3(0.5, 0.5, 0.5));
			int x = (int) (p.getX() + 0.25 - _p.posX);
			
			int z = (int) (p.getZ() + 0.25 - _p.posZ);
			
			int y = (int) (p.getY() + 0.25 - _p.posY);
			
			double distance = MathHelper.sqrt(x * x + z * z);
			float yaw = (float) (Math.atan2(z, x) * 180 / Math.PI - 90);
			float pitch = (float) - (Math.atan2(y, distance) * 180 / Math.PI);
			sendPacket(new C03PacketPlayer.C06PacketPlayerPosLook(_p.posX, _p.posY, _p.posZ, yaw, pitch, _p.onGround));
		}
	
	}
	
	// VALID METHOD
	
	boolean valid(BlockPos p) {
		Block b = world().getBlock(p);
		return !(b instanceof BlockLiquid && b.getMaterial() != Material.air);
	}

	
public float[] getRotations(Entity e) {
	double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
		   deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
		   deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
		   distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
	
	float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
			pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
	
	if(deltaX < 0 && deltaZ < 0) {
		yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
	}else if(deltaX > 0 && deltaZ < 0) {
		yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
	}
	return new float[] { yaw, pitch};
	}

    	
}

	