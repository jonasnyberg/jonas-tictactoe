/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class BoardScore {
	private int size;
	private Map<String, Score> scores;
	
	public BoardScore(int size) {
		this.size = size;
		this.scores = new HashMap<>();
	}
	
	public GameState addScore(int row, int col, Marker marker) {
		GameHelper helper = GameHelper.getInstance();
		GameState state = GameState.DRAW;

		String rowKey = "r" + Integer.toString(row);
		Score rowScore = handleScore(rowKey, marker);
		state = helper.mergeGameStates(state, rowScore.getState());
		if (helper.hasWinner(state)) {
			return state;
		}
		
		String colKey = "c" + Integer.toString(col);
		Score colScore = handleScore(colKey, marker);
		state = helper.mergeGameStates(state, colScore.getState());
		if (helper.hasWinner(state)) {
			return state;
		}
		
		if (isDiagonal(row, col)) {
			String dia1Key = "d1";
			Score dia1Score = handleScore(dia1Key, marker);
			state = helper.mergeGameStates(state, dia1Score.getState());
			if (helper.hasWinner(state)) {
				return state;
			}
		}
		if (isAntiDiagonal(row, col)) {
			String dia2Key = "d2";
			Score dia2Score = handleScore(dia2Key, marker);
			state = helper.mergeGameStates(state, dia2Score.getState());
			if (helper.hasWinner(state)) {
				return state;
			}
		}

		return state;
	}
	
	private Score getScore(String scoreKey) {
		Score score = this.scores.get(scoreKey);
		if (score == null) {
			score = new Score();
		}
		return score;
	}
	
	private Score handleScore(String scoreKey, Marker marker) {
		Score score = getScore(scoreKey);
		
		switch (marker) {
			case CIRCLE:
				score.increment();
				break;
			case CROSS:
				score.decrement();
				break;
			case EMPTY:
				score.setBust(true);
				break;
			case WILD_CARD:
				score.setWildcard(true);
				break;
		}
		this.scores.put(scoreKey, score);
		return score;
	}
	
	private boolean isDiagonal(int row, int col) {
		// TODO - hardcoded to size 4
		return (row == col); 
	}

	private boolean isAntiDiagonal(int row, int col) {
		// TODO - hardcoded to size 4
		return  (row == 0 && col == 3) || 
				(row == 1 && col == 2) || 
				(row == 2 && col == 1) || 
				(row == 3 && col == 0);
	}
	
	private class Score {
		private int score;
		private boolean bust;
		private boolean wildcard;
		
		Score() {
			this.score = 0;
			this.bust = false;
			this.wildcard = false;
		}
		
		public int getScore() {
			return score;
		}
		
		public void increment() {
			score++;
		}
		
		public void decrement() {
			score--;
		}
		
		public void setWildcard(boolean wildcard) {
			this.wildcard = wildcard;
		}
		
		public void setBust(boolean bust) {
			this.bust = bust;
		}
		
		public boolean isBust() {
			return bust;
		}
		
		public boolean hasWinner() {
			if (bust) {
				return false;
			}
			
			return 
				score == size || 
				(score == size-1 && wildcard) ||
				score == -size ||
				(score == -(size-1) && wildcard);
		}
		
		public GameState getState() {
			GameState state = null;
			
			if (this.isBust()) {
				state = GameState.NOT_COMPLETE;
			} else if (this.hasWinner()) {
				state = (this.getScore() > 0) ? GameState.WINNER_CIRCLE : GameState.WINNER_CROSS; 
			}
			return state;
		}
	}
}
