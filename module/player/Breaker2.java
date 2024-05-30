package module.player;

import javax.vecmath.Vector3d;

import org.lwjgl.input.Keyboard;

import me.nikolabg209.golden.events.Event;
import me.nikolabg209.golden.events.listeners.EventMotion;
import me.nikolabg209.golden.utils.PlayerUtils;
import me.nikolabg209.golden.utils.SlotComponent;
import me.nikolabg209.golden.utils.SlotUtil;
import me.nikolabg209.golden.utils.Timer;
import module.Category;
import module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBed;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import me.nikolabg209.golden.events.listeners.*;

public class Breaker2 extends Module{
	public Timer timer = new Timer();
	private Vector3d block;
    private Vector3d lastBlock;
    private Vector3d home;
    private double damage;
	private Object emptySurrounding;
	
	public Breaker2() {
		
		
	
		super("Breaker2", Keyboard.KEY_K, Category.PLAYER);
	}
	
	public void onEvent(Event e)  {
    	if(e instanceof EventUpdate) {
    		if(e.isPre()) { 
    			if(timer.hasTimeElapsed(1000 / 10, true)) {
    		this.destroy1();
    			}
    				
    			
    		   
    		        this.lastBlock = this.block;
    		        this.block = this.block();
    		        if (this.block == null) {
    		            return;
    		        }
    		       
    		        
    		        if (this.lastBlock == null || !this.lastBlock.equals(this.block)) {
    		            this.damage = 0.0;
    		        }
    		     
    		};
    	}
	
	}
    		   

    		   
    		    
    		    

    		    private void destroy() {
		// TODO Auto-generated method stub
		
	}

				public Vector3d block() {
    		        if (this.home != null &&  mc.thePlayer.getDistanceSq(this.home.getX(), this.home.getY(), this.home.getZ()) < 1225.0) {
    		            return null;
    		        }
    		        for (int x = -5; x <= 5;) {
    		            for (int y = -5; y <= 5;) {
    		                for (int z = -5; z <= 5; ++z) {
    		                    MovingObjectPosition movingObjectPosition = null;
    		                    Block block = PlayerUtils.blockRelativeToPlayer(x, y, z);
    		                    Vector3d position = new Vector3d(mc.thePlayer.posX + (double)x, mc.thePlayer.posY + (double)y, mc.thePlayer.posZ + (double)z);
    		                    if (!(block instanceof BlockBed)) continue;
    		                     
    		                       
									
    		                    {
    		                    	 
    		                        Vector3d addVec = position;
    		                        double hardness = Double.MAX_VALUE;
    		                        boolean empty = false;
    		                        for (int addX = -1; addX <= 1;) {
    		                            for (int addY = 0; addY <= 1; ++addY) {
    		                                for (int addZ = -1; addZ <= 1; ++addZ) {
    		                                    Block possibleBlock;
    		                                    if (empty || mc.thePlayer.getDistanceSq(position.getX() + (double)addX, position.getY() + (double)addY, position.getZ() + (double)addZ) + 4.0 > 20.25 || Math.abs(addX) + Math.abs(addY) + Math.abs(addZ) != 1 || (possibleBlock = PlayerUtils.block(position.getX() + (double)addX, position.getY() + (double)addY, position.getZ() + (double)addZ)) instanceof BlockBed) continue;
    		                                    if (possibleBlock instanceof BlockAir) {
    		                                        empty = true;
    		                                        continue;
    		                                  
    		                                   
    		                                    }        
    		                                
    		                            }
    		                        }
    		                        if (!empty) {
    		                            if (addVec.equals(position)) {
    		                                return null;
    		                            }
    		                            return addVec;
    		                        }
    		                                                   }
    		                    return position;
    		                }
    		            }
    		            }
    		                           
    		        return null;
    		    }
    		                     
					return block;
				
				}

				
				
    		        

    		    
    		    public void destroy1() {
    		    	
    		    
    		        BlockPos blockPos2 = new BlockPos(this.block.getX(), this.block.getY(), this.block.getZ());
    		        double hardness1 = SlotUtil.getPlayerRelativeBlockHardness(mc.thePlayer, mc.theWorld, blockPos2, SlotComponent.getItemIndex());
    		           
    		            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos2, EnumFacing.UP));
    		            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos2, EnumFacing.UP));
    		            mc.playerController.onPlayerDestroyBlock(blockPos2, EnumFacing.DOWN);
    		            
    	    					
    	    				
    	    				
    		      
    		             {
    		            	mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos2, EnumFacing.UP));
    		                {
    		                    mc.playerController.onPlayerDestroyBlock(blockPos2, EnumFacing.DOWN);
    		                    this.damage = 0.0;
    		                }
    		                
    		            }   {
    		            	mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos2, EnumFacing.UP));
    		                mc.playerController.onPlayerDestroyBlock(blockPos2, EnumFacing.DOWN);
    		                this.damage = 0.0;
    		                
    		            }  {
    		               
    		            }
    		            mc.thePlayer.swingItem();
    		            
    		        }
    		    
    		    
    		


    		




private void updateDamage(BlockPos blockPos1, double hardness1) {
					// TODO Auto-generated method stub
					
				}







{
}
}
   	

