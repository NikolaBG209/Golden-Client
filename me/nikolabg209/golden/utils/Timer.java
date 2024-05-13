package me.nikolabg209.golden.utils;

public class Timer {
	public long lastMS = System.currentTimeMillis();
	
	public void reset() {
		lastMS = System.currentTimeMillis();
		
	}
	
	public boolean hasTimeElapsed(double d, boolean reset) {
			if(System.currentTimeMillis() -lastMS > d) {
				if(reset)
					reset();
				
				return true;
			}
			
			return false;
			
	}
	

}
