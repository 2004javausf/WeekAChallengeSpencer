package com.statistics.vitals;

public class HealthBar {
	
	private int maxHp;
	private int currentHp;
	
	public HealthBar (){
		maxHp = 10;
		currentHp = 10;
	}
	
	public HealthBar (int maxHp){
		this.maxHp = maxHp;
		currentHp = this.maxHp;
	}
	
	public HealthBar (double maxHp){
		System.out.println("Warning! Double not supported! Truncating to int");
		this.maxHp = (int)maxHp;
		currentHp = this.maxHp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	@Override
	public String toString() {
		return "Hp:" + currentHp + "/" + maxHp;
	}	

}
