package module.player;

import module.Category;
import module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NewScaffold extends Module{
	public NewScaffold() {
		super("NewScaffold", 0, Category.PLAYER);
	}
	private BlockPos currentPos;
	private EnumFacing currentFacing;
	private boolean rotated = false;


private void setBlockAndFacing(BlockPos var1) {
	if(mc.theWorld.getBlockState(var1.add(0, -1, 0)).getBlock() != Blocks.air) {
		this.currentPos = var1.add(0, -1, 0);
		currentFacing = EnumFacing.UP;
	}else if(mc.theWorld.getBlockState(var1.add(-1, 0, 0)).getBlock() != Blocks.air) {
		this.currentPos = var1.add(-1, 0, 0);
		currentFacing = EnumFacing.EAST;
	}else if(mc.theWorld.getBlockState(var1.add(1, 0, 0)).getBlock() != Blocks.air) {
		this.currentPos = var1.add(1, 0, 0);
		currentFacing = EnumFacing.WEST;
	}else if(mc.theWorld.getBlockState(var1.add(0, 0, -1)).getBlock() != Blocks.air) {
		this.currentPos = var1.add(0, 0, -1);
		currentFacing = EnumFacing.SOUTH;
	}else if(mc.theWorld.getBlockState(var1.add(0, 0, 1)).getBlock() != Blocks.air) {
		this.currentPos = var1.add(0, 0, 1);
		currentFacing = EnumFacing.NORTH;
	}else {
		currentPos = null;
		currentFacing = null;
	}
	}
}

