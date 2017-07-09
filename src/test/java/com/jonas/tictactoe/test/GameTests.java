/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jonas.tictactoe.GameHelper;
import com.jonas.tictactoe.GameState;
import com.jonas.tictactoe.Marker;

public class GameTests {
	private GameHelper gameHelper;
	
	@Before
	public void initialize() {
		this.gameHelper = GameHelper.getInstance();
	}
	
	@After
	public void cleanup() {
		this.gameHelper = null;
	}

	@Test
	public void testParialGameStateWinner() {
		Marker[] markers;
		GameState state;
		
		markers = new Marker[4];
		markers[0] = Marker.CIRCLE;
		markers[1] = Marker.CIRCLE;
		markers[2] = Marker.CIRCLE; 
		markers[3] = Marker.CIRCLE;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertTrue(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.WINNER_CIRCLE, state);
		
		markers[0] = Marker.WILD_CARD;
		markers[1] = Marker.CROSS;
		markers[2] = Marker.CROSS; 
		markers[3] = Marker.CROSS;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertTrue(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.WINNER_CROSS, state);

		markers[0] = Marker.CIRCLE;
		markers[1] = Marker.WILD_CARD;
		markers[2] = Marker.CIRCLE; 
		markers[3] = Marker.CIRCLE;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertTrue(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.WINNER_CIRCLE, state);

		markers[0] = Marker.CROSS;
		markers[1] = Marker.CROSS;
		markers[2] = Marker.WILD_CARD; 
		markers[3] = Marker.CROSS;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertTrue(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.WINNER_CROSS, state);
		
		markers[0] = Marker.CIRCLE;
		markers[1] = Marker.CIRCLE;
		markers[2] = Marker.CIRCLE; 
		markers[3] = Marker.WILD_CARD;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertTrue(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.WINNER_CIRCLE, state);
	}
	
	@Test
	public void testPartialGameStateDraw() {
		Marker[] markers;
		GameState state;
		
		markers = new Marker[4];
		markers[0] = Marker.CIRCLE;
		markers[1] = Marker.WILD_CARD;
		markers[2] = Marker.CIRCLE; 
		markers[3] = Marker.CROSS;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.DRAW, state);
		
		markers = new Marker[4];
		markers[0] = Marker.CROSS;
		markers[1] = Marker.CROSS;
		markers[2] = Marker.CIRCLE; 
		markers[3] = Marker.CROSS;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.DRAW, state);

		markers = new Marker[4];
		markers[0] = Marker.WILD_CARD;
		markers[1] = Marker.CROSS;
		markers[2] = Marker.CROSS; 
		markers[3] = Marker.CIRCLE;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.DRAW, state);

		markers = new Marker[4];
		markers[0] = Marker.CROSS;
		markers[1] = Marker.CIRCLE;
		markers[2] = Marker.CROSS; 
		markers[3] = Marker.WILD_CARD;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.DRAW, state);
	}

	@Test
	public void testPartialGameStateNotComplete() {
		Marker[] markers;
		GameState state;
		
		markers = new Marker[4];
		markers[0] = Marker.CIRCLE;
		markers[1] = Marker.WILD_CARD;
		markers[2] = Marker.CROSS; 
		markers[3] = Marker.EMPTY;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.NOT_COMPLETE, state);
		
		markers = new Marker[4];
		markers[0] = Marker.EMPTY;
		markers[1] = Marker.CROSS;
		markers[2] = Marker.CROSS; 
		markers[3] = Marker.CROSS;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.NOT_COMPLETE, state);

		markers = new Marker[4];
		markers[0] = Marker.WILD_CARD;
		markers[1] = Marker.CIRCLE;
		markers[2] = Marker.EMPTY; 
		markers[3] = Marker.CIRCLE;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.NOT_COMPLETE, state);

		markers = new Marker[4];
		markers[0] = Marker.CROSS;
		markers[1] = Marker.EMPTY;
		markers[2] = Marker.CROSS; 
		markers[3] = Marker.WILD_CARD;
		state = gameHelper.getOverallStateForMarkers(markers);
		Assert.assertFalse(gameHelper.hasWinner(state));
		Assert.assertEquals(GameState.NOT_COMPLETE, state);
	}
	
	@Test
	public void testMergeStates_Draw() {
		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.DRAW, null), 
			GameState.DRAW);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.DRAW, GameState.DRAW), 
			GameState.DRAW);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.DRAW, GameState.NOT_COMPLETE), 
			GameState.NOT_COMPLETE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.DRAW, GameState.WINNER_CIRCLE), 
			GameState.WINNER_CIRCLE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.DRAW, GameState.WINNER_CROSS), 
			GameState.WINNER_CROSS);
	}

	@Test
	public void testMergeStates_NotComplete() {
		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.NOT_COMPLETE, null), 
			GameState.NOT_COMPLETE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.NOT_COMPLETE, GameState.DRAW), 
			GameState.NOT_COMPLETE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.NOT_COMPLETE, GameState.NOT_COMPLETE), 
			GameState.NOT_COMPLETE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.NOT_COMPLETE, GameState.WINNER_CIRCLE), 
			GameState.WINNER_CIRCLE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.NOT_COMPLETE, GameState.WINNER_CROSS), 
			GameState.WINNER_CROSS);
	}

	@Test
	public void testMergeStates_Winner() {
		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.WINNER_CROSS, null), 
			GameState.WINNER_CIRCLE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.WINNER_CIRCLE, null), 
			GameState.WINNER_CIRCLE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.WINNER_CROSS, GameState.DRAW), 
			GameState.WINNER_CROSS);
		
		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.WINNER_CIRCLE, GameState.DRAW), 
			GameState.WINNER_CIRCLE);

		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.WINNER_CROSS, GameState.NOT_COMPLETE), 
			GameState.WINNER_CROSS);
		
		Assert.assertEquals(
			gameHelper.mergeGameStates(GameState.WINNER_CIRCLE, GameState.NOT_COMPLETE), 
			GameState.WINNER_CIRCLE);
	}
	
	@Test
	public void testGameStateMessage() {
		Assert.assertEquals(gameHelper.getGameStateMessage(GameState.WINNER_CROSS), "X won");
		Assert.assertEquals(gameHelper.getGameStateMessage(GameState.WINNER_CIRCLE), "O won");
		Assert.assertEquals(gameHelper.getGameStateMessage(GameState.NOT_COMPLETE), "Game has not completed");
		Assert.assertEquals(gameHelper.getGameStateMessage(GameState.DRAW), "Draw");
	}
}
