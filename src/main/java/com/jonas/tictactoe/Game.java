/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

public class Game {
	private Board board;
	private int size;
	
	private final GameHelper gameHelper;
	
	public Game(Board board) {
		this.board = board;
		this.size = board.getSize();
		this.gameHelper = GameHelper.getInstance();
	}
	
	public GameState calculateGameState() {
		GameState state = null;

		state = gameHelper.mergeGameStates(state, checkRows());
		if (gameHelper.hasWinner(state)) {
			return state;
		}

		state = gameHelper.mergeGameStates(state, checkColumns());
		if (gameHelper.hasWinner(state)) {
			return state;
		}
		
		state = gameHelper.mergeGameStates(state, checkDiagonals());
		
		return state;
	}
	
	private GameState checkRows() {
		GameState state = null;
		
		for (int row = 0; row < size; row++) {
			GameState rowState = gameHelper.getOverallStateForMarkers(board.getRowMarkers(row));
			state = gameHelper.mergeGameStates(state, rowState);
			
			if (gameHelper.hasWinner(state)) {
				break;
			}
		}
		return state;
	}
	
	private GameState checkColumns() {
		GameState state = null;
		
		for (int col = 0; col < size; col++) {
			GameState colState = gameHelper.getOverallStateForMarkers(board.getColumnMarkers(col));
			state = gameHelper.mergeGameStates(state, colState);
			
			if (gameHelper.hasWinner(state)) {
				break;
			}
		}
		return state;
	}
	
	private GameState checkDiagonals() {
		GameState state = null;
		
		GameState firstDiagonalState = gameHelper.getOverallStateForMarkers(board.getFirstDiagonalMarkers());
		state = gameHelper.mergeGameStates(state, firstDiagonalState);
		
		if (gameHelper.hasWinner(state)) {
			return state;
		}
		
		GameState secondDiagonalState = gameHelper.getOverallStateForMarkers(board.getSecondDiagonalMarkers());
		state = gameHelper.mergeGameStates(state, secondDiagonalState);
		
		return state;
	}
}
