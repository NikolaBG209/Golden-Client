package me.nikolabg209.golden.utils;


import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import me.nikolabg209.golden.*;

/**
 * The type Movement utils.
 */
public final class MovementUtils{
	public Minecraft mc = Minecraft.getMinecraft();
    /**
     * Gets speed.
     *
     * @return the speed
     */
    public float getSpeed() {
       
		return (float) getSpeed(mc.thePlayer.motionX, mc.thePlayer.motionZ);
    }

    /**
     * Gets speed.
     *
     * @param motionX the motion x
     * @param motionZ the motion z
     * @return the speed
     */
    public static double getSpeed(double motionX, double motionZ) {
        return Math.sqrt(motionX * motionX + motionZ * motionZ);
    }

    /**
     * Is on ice boolean.
     *
     * @return the boolean
     */
    public boolean isOnIce() {
        final EntityPlayerSP player = mc.thePlayer;
        final Block blockUnder = mc.theWorld.getBlockState(new BlockPos(player.posX, player.posY - 1.0, player.posZ)).getBlock();
        return blockUnder instanceof BlockIce || blockUnder instanceof BlockPackedIce;
    }

    public void setSpeed(double moveSpeed, float yaw, double strafe, double forward) {
        if (forward != 0.0D) {
            if (strafe > 0.0D) {
                yaw += ((forward > 0.0D) ? -45 : 45);
            } else if (strafe < 0.0D) {
                yaw += ((forward > 0.0D) ? 45 : -45);
            }
            strafe = 0.0D;
            if (forward > 0.0D) {
                forward = 1.0D;
            } else if (forward < 0.0D) {
                forward = -1.0D;
            }
        }
        if (strafe > 0.0D) {
            strafe = 1.0D;
        } else if (strafe < 0.0D) {
            strafe = -1.0D;
        }
        double mx = Math.cos(Math.toRadians((yaw + 90.0F)));
        double mz = Math.sin(Math.toRadians((yaw + 90.0F)));
       mc.thePlayer.motionX = forward * moveSpeed * mx + strafe * moveSpeed * mz;
      mc.thePlayer.motionZ = forward * moveSpeed * mz - strafe * moveSpeed * mx;
    }

    public void setSpeed(double moveSpeed) {
        setSpeed(moveSpeed, mc.thePlayer.rotationYaw, mc.thePlayer.movementInput.moveStrafe, mc.thePlayer.movementInput.moveForward);
    }

    /**
     * Is block under boolean.
     *
     * @return the boolean
     */
    public boolean isBlockUnder() {
        if (mc.thePlayer == null) return false;

        if (mc.thePlayer.posY < 0.0) {
            return false;
        }
        for (int off = 0; off < (int) mc.thePlayer.posY + 2; off += 2) {
            final AxisAlignedBB bb = mc.thePlayer.getEntityBoundingBox().offset(0.0, -off, 0.0);
            if (!mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, bb).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isRidingBlock() {
        return !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.8, mc.thePlayer.posZ)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX + 0.5, mc.thePlayer.posY - 1.8, mc.thePlayer.posZ + 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX - 0.5, mc.thePlayer.posY - 1.8, mc.thePlayer.posZ + 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX + 0.5, mc.thePlayer.posY - 1.8, mc.thePlayer.posZ - 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX - 0.5, mc.thePlayer.posY - 1.8, mc.thePlayer.posZ - 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX + 0.5, mc.thePlayer.posY - 1, mc.thePlayer.posZ + 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX - 0.5, mc.thePlayer.posY - 1, mc.thePlayer.posZ + 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX + 0.5, mc.thePlayer.posY - 1, mc.thePlayer.posZ - 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX - 0.5, mc.thePlayer.posY - 1, mc.thePlayer.posZ - 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX + 0.5, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ + 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX - 0.5, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ + 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX + 0.5, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ - 0.5)).getBlock() instanceof BlockAir) || !(mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX - 0.5, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ - 0.5)).getBlock() instanceof BlockAir);
    }

    /**
     * Accelerate.
     *
     * @param speed the speed
     */
    public void accelerate(final float speed) {
        if (!isMoving())
            return;

        final double yaw = getDirection();
        mc.thePlayer.motionX += -Math.sin(yaw) * speed;
        mc.thePlayer.motionZ += Math.cos(yaw) * speed;
    }

    private double getDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
     * Strafe.
     */
    public void strafe() {
        strafe(getSpeed());
    }
 
    /**
     * Is moving boolean.
     *
     * @return the boolean
     */
    public boolean isMoving() {
        return mc.thePlayer != null && (mc.thePlayer.movementInput.moveForward != 0F || mc.thePlayer.movementInput.moveStrafe != 0F);
    }

    /**
     * Has motion boolean.
     *
     * @return the boolean
     */
    public boolean hasMotion() {
        return mc.thePlayer.motionX != 0D && mc.thePlayer.motionZ != 0D && mc.thePlayer.motionY != 0D;
    }

    private float getMoveYaw() {
        EntityPlayerSP player = mc.thePlayer;
        float moveYaw = player.rotationYaw;

        if (player.moveForward != 0F && player.moveStrafing == 0F) {
            moveYaw += (player.moveForward > 0) ? 0 : 180;
        } else if (player.moveForward != 0F) {
            if (player.moveForward > 0) {
                moveYaw += (player.moveStrafing > 0) ? -45 : 45;
            } else {
                moveYaw -= (player.moveStrafing > 0) ? -45 : 45;
            }
            moveYaw += (player.moveForward > 0) ? 0 : 180;
        } else if (player.moveStrafing != 0F) {
            moveYaw += (player.moveStrafing > 0) ? -90 : 90;
        }

        return moveYaw;
    }

    /**
     * Strafe.
     *
     * @param speed the speed
     */
    public void strafe(final float speed) {
        double shotSpeed = Math.sqrt((mc.thePlayer.motionX * mc.thePlayer.motionX) + (mc.thePlayer.motionZ * mc.thePlayer.motionZ));
        double fixSpeed = (shotSpeed * 1);
        double motionX = (mc.thePlayer.motionX * (0));
        double motionZ = (mc.thePlayer.motionZ * (0));

        if (!isMoving()) {
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionZ = 0.0;
            return;
        }

        float yaw = getMoveYaw();
        mc.thePlayer.motionX = (((-Math.sin(Math.toRadians(yaw)) * fixSpeed) + motionX));
        mc.thePlayer.motionZ = (((Math.cos(Math.toRadians(yaw)) * fixSpeed) + motionZ));

        
    }

    /**
     * Forward.
     *
     * @param length the length
     */
    public void forward(final double length) {
        final double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
        mc.thePlayer.setPosition(mc.thePlayer.posX + (-Math.sin(yaw) * length), mc.thePlayer.posY, mc.thePlayer.posZ + (Math.cos(yaw) * length));
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
   

    /**
     * Gets speed effect.
     *
     * @return the speed effect
     */
    public int getSpeedEffect() {
        return mc.thePlayer.isPotionActive(Potion.moveSpeed) ? mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1 : 0;
    }

    /**
     * Gets base move speed.
     *
     * @return the base move speed
     */
    public double getBaseMoveSpeed() {
        double baseSpeed = 0.2873D;
        if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            baseSpeed *= 1.0D + 0.2D * (double) (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
        }

        return baseSpeed;
    }

    /**
     * Gets base move speed.
     *
     * @param customSpeed the custom speed
     * @return the base move speed
     */
    public double getBaseMoveSpeed(double customSpeed) {
        double baseSpeed = isOnIce() ? 0.258977700006 : customSpeed;
        if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            int amplifier = mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }

    /**
     * Gets jump boost modifier.
     *
     * @param baseJumpHeight the base jump height
     * @param potionJump     the potion jump
     * @return the jump boost modifier
     */
    public double getJumpBoostModifier(double baseJumpHeight, boolean potionJump) {
        if (mc.thePlayer.isPotionActive(Potion.jump) && potionJump) {
            int amplifier = mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier();
            baseJumpHeight += ((float) (amplifier + 1) * 0.1f);
        }

        return baseJumpHeight;
    }

    /**
     * Sets speed.
     *
     * @param moveEvent     the move event
     * @param moveSpeed     the move speed
     * @param pseudoYaw     the pseudo yaw
     * @param pseudoStrafe  the pseudo strafe
     * @param pseudoForward the pseudo forward
     */
    
    }
