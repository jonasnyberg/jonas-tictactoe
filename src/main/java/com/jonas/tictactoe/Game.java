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

		for (int row = 0; row < size; row++) {
			GameState rowState = getStateForRow(row);
			if (gameHelper.hasWinner(rowState)) {
				return rowState;
			} else {
				state = gameHelper.calculateNewState(state, rowState);
			}
		}
		
		for (int col = 0; col < size; col++) {
			GameState colState = getStateForCol(col);
			if (gameHelper.hasWinner(colState)) {
				return colState;
			} else {
				state = gameHelper.calculateNewState(state, colState);
			}
		}
		
		GameState diagonalState = getStateForDiagonal();
		if (gameHelper.hasWinner(diagonalState)) {
			return diagonalState;
		} else {
			state = gameHelper.calculateNewState(state, diagonalState);
		}
		
		GameState antiDiagonalState = getStateForAntiDiagonal();
		if (gameHelper.hasWinner(antiDiagonalState)) {
			return antiDiagonalState;
		} else {
			state = gameHelper.calculateNewState(state, antiDiagonalState);
		}
		
		return state;
	}
	
	public GameState getStateForRow(int row) {
		return gameHelper.getOverallStateForMarkers(board.getRowMarkers(row));
	}
	
	public GameState getStateForCol(int col) {
		return gameHelper.getOverallStateForMarkers(board.getColumnMarkers(col));
	}
	
	public GameState getStateForDiagonal() {
		return gameHelper.getOverallStateForMarkers(board.getFirstDiagonalMarkers());
	}
	
	public GameState getStateForAntiDiagonal() {
		return gameHelper.getOverallStateForMarkers(board.getSecondDiagonalMarkers());
	}
}
