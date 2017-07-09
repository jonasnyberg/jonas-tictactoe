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

import com.jonas.tictactoe.Board;
import com.jonas.tictactoe.Marker;

public class BoardTests {
	private Board board;
	
	@Before
	public void initializeBoard() {
		this.board = new Board(4);
	}
	
	@After
	public void cleanup() {
		this.board = null;
	}
	
	@Test
	public void testAddMarker() {
		int row, col;
		
		row = 0;
		col = 0;
		board.addMarker(row, col, Marker.CROSS);
		Assert.assertEquals(board.getMarker(row, col), Marker.CROSS);
		
		row = 1;
		col = 1;
		board.addMarker(row, col, Marker.CIRCLE);
		Assert.assertEquals(board.getMarker(row, col), Marker.CIRCLE);
		
		row = 2;
		col = 2;
		board.addMarker(row, col, Marker.EMPTY);
		Assert.assertEquals(board.getMarker(row, col), Marker.EMPTY);

		row = 3;
		col = 3;
		board.addMarker(row, col, Marker.WILD_CARD);
		Assert.assertEquals(board.getMarker(row, col), Marker.WILD_CARD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullMarker() {
		board.addMarker(0, 0, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMarkerColTooBig() {
		board.addMarker(0, 4, Marker.WILD_CARD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMarkerRowTooBig() {
		board.addMarker(4, 2, Marker.WILD_CARD);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMarkerColNegative() {
		board.addMarker(0, -1, Marker.WILD_CARD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddMarkerRowNegative() {
		board.addMarker(-1, 3, Marker.WILD_CARD);
	}
	
	@Test
	public void testGetRowMarkers() {
		int row = 1;
		board.addMarker(row, 0, 'X');
		board.addMarker(row, 1, 'O');
		board.addMarker(row, 2, '.');
		board.addMarker(row, 3, 'T');
		
		Marker[] markers = board.getRowMarkers(row);
		Assert.assertEquals(markers[0], Marker.CROSS);
		Assert.assertEquals(markers[1], Marker.CIRCLE);
		Assert.assertEquals(markers[2], Marker.EMPTY);
		Assert.assertEquals(markers[3], Marker.WILD_CARD);
	}

	@Test
	public void testGetColMarkers() {
		int col = 2;
		board.addMarker(0, col, '.');
		board.addMarker(1, col, 'X');
		board.addMarker(2, col, 'T');
		board.addMarker(3, col, 'O');
		
		Marker[] markers = board.getColumnMarkers(col);
		Assert.assertEquals(markers[0], Marker.EMPTY);
		Assert.assertEquals(markers[1], Marker.CROSS);
		Assert.assertEquals(markers[2], Marker.WILD_CARD);
		Assert.assertEquals(markers[3], Marker.CIRCLE);
	}
	
	@Test
	public void testGetDiagonalMarkers() {
		Marker[] markers;
		
		// First diagonal:
		
		board.addMarker(0, 0, 'X');
		board.addMarker(1, 1, '.');
		board.addMarker(2, 2, 'O');
		board.addMarker(3, 3, 'T');

		markers = board.getFirstDiagonalMarkers();
		Assert.assertEquals(markers[0], Marker.CROSS);
		Assert.assertEquals(markers[1], Marker.EMPTY);
		Assert.assertEquals(markers[2], Marker.CIRCLE);
		Assert.assertEquals(markers[3], Marker.WILD_CARD);

		// Second diagonal:

		board.addMarker(0, 3, 'T');
		board.addMarker(1, 2, 'O');
		board.addMarker(2, 1, '.');
		board.addMarker(3, 0, 'X');
		
		markers = board.getSecondDiagonalMarkers();
		Assert.assertEquals(markers[0], Marker.WILD_CARD);
		Assert.assertEquals(markers[1], Marker.CIRCLE);
		Assert.assertEquals(markers[2], Marker.EMPTY);
		Assert.assertEquals(markers[3], Marker.CROSS);
		
	}
}
