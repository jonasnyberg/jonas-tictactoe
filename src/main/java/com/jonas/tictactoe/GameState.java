/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

public enum GameState {
	WINNER_CROSS(2),
	WINNER_CIRCLE(2),
	NOT_COMPLETE(1),
	DRAW(0);
	
	private int weight;
	
	private GameState(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
}
