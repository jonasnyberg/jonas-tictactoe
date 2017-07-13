/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

public class Board {
	private int size;
	private Marker[][] data;
	
	public Board(int size) {
		this.size = size;
		this.data = new Marker[size][size];
	}
	
	public int getSize() {
		return size;
	}
	
	public void addMarker(int row, int col, Marker marker) {
		if (marker == null) {
			throw new IllegalArgumentException("Invalid Board Marker supplied");
		} else if (row < 0 || row >= size) {
			throw new IllegalArgumentException("Invalid row supplied");
		} else if (col < 0 || col >= size) {
			throw new IllegalArgumentException("Invalid column supplied");
		}
		
		data[row][col] = marker;
	}
	
	public Marker getMarker(int row, int col) {
		return data[row][col];
	}
	
	public Marker[] getRowMarkers(int row) {
		return data[row];
	}
	
	public Marker[] getColumnMarkers(int col) {
		Marker[] markers = new Marker[size];
		
		for (int row = 0; row < size; row++) {
			markers[row] = data[row][col];
		}
		
		return markers;
	}
	
	public Marker[] getFirstDiagonalMarkers() {
		Marker[] markers = new Marker[size];

		for (int i = 0; i < size; i++) {
			markers[i] = data[i][i];
		}
		
		return markers;
	}

	public Marker[] getSecondDiagonalMarkers() {
		Marker[] markers = new Marker[size];

		for (int i = 0; i < size; i++) {
			markers[i] = data[i][size - i - 1];
		}

		return markers;
	}
}
