package com.statistics.vitals;

public class ManaBar {
	private int maxMp;
	private int currentMp;
	
	public ManaBar (){
		maxMp = 10;
		currentMp = 10;
	}
	
	public ManaBar (int maxMp){
		this.maxMp = maxMp;
		currentMp = this.maxMp;
	}
	
	public ManaBar (double maxMp){
		System.out.println("Warning! Double not supported! Truncating to int");
		this.maxMp = (int)maxMp;
		currentMp = this.maxMp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	public int getCurrentMp() {
		return currentMp;
	}

	public void setCurrentMp(int currentMp) {
		this.currentMp = currentMp;
	}

	@Override
	public String toString() {
		return "Mp:" + currentMp + "/" + maxMp;
	}	

}
