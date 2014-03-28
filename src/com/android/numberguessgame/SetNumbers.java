package com.android.numberguessgame;

import java.util.Random;


public class SetNumbers {

	private Random randNum;
	private int numGuess;
	private boolean isFinish;
	private int correctNum;
	private static int range;
	
	public SetNumbers() {
		this(1000);
	}

	 
	public SetNumbers(int i) {
		randNum = new Random();
		SetNumbers.range = i;
		numGuess = 0;
		correctNum = randNum.nextInt(i) + 1;
		isFinish = false; 
	}
	
	public int getRange(){
		return range;
	}
	
	public void setRange(int i) {
		SetNumbers.range = i ;
	}
	
	public int getNumGuesses() {
		return numGuess;
	}
	
	public boolean isFinished() {
		return isFinish;
	}
	
	
}
