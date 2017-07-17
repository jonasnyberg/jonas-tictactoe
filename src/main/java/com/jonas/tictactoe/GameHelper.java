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
			if (marker == Marker.EMPTY) {
				return GameState.NOT_COMPLETE;
			} else if (state == GameState.DRAW) {
				continue;
			} else if (marker == Marker.CROSS || marker == Marker.CIRCLE) {
				if (prevCircleOrCrossMarker != null && prevCircleOrCrossMarker != marker) {
					state = GameState.DRAW;
				} else {
					state = marker == Marker.CROSS ? GameState.WINNER_CROSS : GameState.WINNER_CIRCLE;
					prevCircleOrCrossMarker = marker;
				}
			}
		}
		return state;
	}
	
	public GameState calculateNewState(GameState currentState, GameState newState) {
		if (newState == null) {
			return currentState;
		} else if (currentState == null) {
			return newState;
		}
		return currentState.getWeight() >= newState.getWeight() ? currentState : newState;
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
