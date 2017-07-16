/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

public final class GameHelper {
	private static final GameHelper instance = new GameHelper();
	
	private GameHelper() {
	}
	
	public static GameHelper getInstance() {
		return instance;
	}
	
	/**
	 * Helper method for calculating the overall state for an array of Markers. 
	 */
	public GameState getOverallStateForMarkers(Marker[] markers) {
		GameState state = null;
		Marker prevCircleOrCrossMarker = null;
		
		for (Marker marker : markers) {
			switch (marker) {
				case CROSS:
				case CIRCLE:
					if (state == GameState.DRAW) {
						break;
					}
					if (prevCircleOrCrossMarker == null || prevCircleOrCrossMarker == marker) {
						state = marker == Marker.CROSS ? GameState.WINNER_CROSS : GameState.WINNER_CIRCLE;
					} else {
						state = GameState.DRAW;
					}
					prevCircleOrCrossMarker = marker;
					break;
				case WILD_CARD:
					// Do nothing.
					break;
				case EMPTY:
				default:
					return GameState.NOT_COMPLETE;
			}
		}
		return state;
	}
	
	
	public GameState mergeGameStates(GameState currentState, GameState newState) {
		if (currentState == null) {
			return newState;
		} else if (newState == null) {
			return currentState;
		}
		
		switch (currentState) {
			case WINNER_CROSS:
			case WINNER_CIRCLE:
				return currentState;
			case NOT_COMPLETE:
				return hasWinner(newState) ? newState : currentState;
			case DRAW:
				return hasWinner(newState) || newState == GameState.NOT_COMPLETE ? newState : currentState;
			default:
				return newState;
		}
	}
	
	public boolean hasWinner(GameState state) {
		return state != null && 
			(state == GameState.WINNER_CROSS || state == GameState.WINNER_CIRCLE);
	}
	
	
	public String getGameStateMessage(GameState state) {
		if (state == null) {
			return null;
		}
		
		switch (state) {
			case WINNER_CIRCLE:
				return "O won";
			case WINNER_CROSS:
				return "X won";
			case DRAW:
				return "Draw";
			case NOT_COMPLETE:
				return "Game has not completed";
			default:
				return "Unknown Game State: " + state;
		}
	}
}
