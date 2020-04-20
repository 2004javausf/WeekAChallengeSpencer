package com.battle.driver;

import java.util.Random;
import java.util.Scanner;

import com.statistics.vitals.HealthBar;
import com.statistics.vitals.ManaBar;

public class Driver {


	//I can't be the only one who decided to do a battle system, but here's my rudimentary one.
	//Monster attacks unimplemented at the moment. -Casey Spencer
	
	public static void main(String[] args) {
		//Actor Initialization!
		HealthBar playerHp;
		ManaBar playerMp;
		HealthBar monsterHp;
		ManaBar monsterMp;
		Scanner in = new Scanner(System.in);
		
		System.out.println("What is your max HP?");
		playerHp = new HealthBar(in.nextInt());			
		System.out.println("What is your max MP?");
		playerMp = new ManaBar(in.nextInt());
		System.out.println("What is the monster's max HP?");
		monsterHp = new HealthBar(in.nextInt());	
		System.out.println("What is the monster's max MP?");
		monsterMp = new ManaBar(in.nextInt());
		
		//BATTLE START!
		
		while(!(playerHp.getCurrentHp() <= 0) && !(monsterHp.getCurrentHp() <= 0) ) {
			System.out.println("Player: " + playerHp.toString() + " " + playerMp.toString() +"Monster: " + monsterHp.toString() + " " + monsterMp.toString());
			System.out.println("1: Rest (Regain 10 MP)");
			System.out.println("2: Weak Hit");
			System.out.println("3: Spark (10 MP)");
			System.out.println("4: Fireball (20 MP)");
			System.out.println("5: Gambler's Coin (30 MP)");
			System.out.println("6: Ultimate Rush (100 MP)");
			System.out.println("7: Berzerker strike (10 HP)");
			
			boolean failedHit = false; //checks if user selected something they can't use. True skips monster phase for reselection
			
			switch (in.nextInt()){
			case 1:
				if (playerMp.getCurrentMp()+10 > playerMp.getMaxMp()) {
					playerMp.setCurrentMp(playerMp.getMaxMp());
				}else {
					playerMp.setCurrentMp(playerMp.getCurrentMp()+10);
				}
				break;
			case 2:
				hitTarget(2, monsterHp);
				break;
			case 3:
				if(playerMp.getCurrentMp() >= 10) { //check for MP. Could have abstracted but not feeling it.
					hitTarget(5, monsterHp, 10, playerMp);
				}
				else {
					System.out.println("Not Enough MP!");
					failedHit = true;
				}
				break;
			case 4:
				if(playerMp.getCurrentMp() >= 20) {
					hitTarget(8, monsterHp, 20, playerMp);
				}
				else {
					System.out.println("Not Enough MP!");
					failedHit = true;
				}
				break;
			case 5:
				if(playerMp.getCurrentMp() >= 30) { //Big boy here. Here comes a dump of control statements for no reason, killing runtime.
				/*So, an explanation of what's going to be done. Player has a coin flipped 10 times. On correct
				 * guess, a point is added and a free flip is added. Failure does nothing, hence eliminating a flip.
				 * Also, this can easily be done with much less control statements but I need to shoehorn some in.
				 */
					int[] coinFlip = new int[10];
					Random coin = new Random();
					int points = 0;
					for(Integer i = 0; i<coinFlip.length; i++) { //set 10 values for the coin flip to justify a do while.
						coinFlip[i]=coin.nextInt(2); //Also look above! A wrapper!
					}
					for(int i: coinFlip){
						boolean correctGuess = false;
						do {
							System.out.println("Heads or tails! (0 for heads, 1 for tails)");
							if (in.nextInt() == i) {
								points++;
								i = coin.nextInt(2);
								System.out.println("Nice! You're at " + points + " correct guesses!");
								correctGuess = true;
							}else {
								System.out.println("Wrong!");
								correctGuess = false;
							}
						}while(correctGuess);
					}
					System.out.println("You did " + points + " damage!");
					hitTarget(points, monsterHp, 30, playerMp);
			}
			else {
				System.out.println("Not Enough MP!");
				failedHit = true;
			}
				break;
			case 6:
				if(playerMp.getCurrentMp() >= 100) {
					hitTarget(9999, monsterHp, 100, playerMp);
				}
				else {
					System.out.println("Not Enough MP!");
					failedHit = true;
				}
				break;
			case 7:
				if(playerHp.getCurrentHp() >= 10) {
					hitTarget(5, monsterHp, 10, playerHp);
				}
				else {
					System.out.println("Not Enough HP!");
					failedHit = true;
				}
				break;
				default:
					System.out.println("Not a valid option!");
					failedHit = true;
					break;
			}
			
		}
		
		in.close();
	}
	
	private static void hitTarget(int i, HealthBar targetHp, int j, HealthBar userHp) { //For zerker style moves
		// TODO Auto-generated method stub
		targetHp.setCurrentHp(targetHp.getCurrentHp()-i);
		userHp.setCurrentHp(userHp.getCurrentHp()-j);
		
	}

	private static void hitTarget(int i, HealthBar targetHp, int j, ManaBar userMp) { //subtracts target's health and user's MP
		// TODO Auto-generated method stub
		targetHp.setCurrentHp(targetHp.getCurrentHp()-i);
		userMp.setCurrentMp(userMp.getCurrentMp()-j);
		
	}

	private static void hitTarget(int i, HealthBar targetHp) { //subtracts target's health
		// TODO Auto-generated method stub
		targetHp.setCurrentHp(targetHp.getCurrentHp()-i);
	}


}
