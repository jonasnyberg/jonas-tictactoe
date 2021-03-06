/*
 *
 * Implementation of Tic-Tac-Toe-Tomek
 * See: https://code.google.com/codejam/contest/2270488/dashboard
 * 
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {
	public static final int BOARD_SIZE = 4;
	
	public static void main(String[] args) {
		Application app = new Application();
		app.start();
	}
	
	private void start() {
		Board[] boards = null;
		
		try {
			boards = parseInput(System.in);
		} catch (Exception exc) {
			System.out.println("ERROR: Halting Application due to an unexpected failure when reading input...");
			exc.printStackTrace();
			System.exit(-1);
		}
		
	    // _________ Calculating results from each Board: __________
	    
	    for (int i = 0; i < boards.length; i++) {
	    	Board board = boards[i];
	    	Game game = new Game(board);

	    	GameState state = game.calculateGameState();
	    	System.out.println("Case #" + (i + 1) + ": " + 
	    		GameHelper.getInstance().getGameStateMessage(state));
	    }
	}
	
	private Board[] parseInput(InputStream inputStream) throws Exception {
		if (inputStream.available() == 0) {
			throw new IOException("Could not read data from input file");
		}

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(inputStream)));
		Board[] boards = null;
		
		try {
		    int nBoards = in.nextInt();
		    boards = new Board[nBoards];
		    
		    // Iterating through all Boards
		    for (int i = 0; i < nBoards; i++) {
		    	Board board = new Board(BOARD_SIZE);

			    // Iterating through all rows in the Board
		    	for (int row = 0; row < BOARD_SIZE; row++) {
		    		char[] rowMarkers = in.next().toCharArray();
		    		
		    		if (rowMarkers.length != BOARD_SIZE) {
		    			throw new IllegalArgumentException("Found invalid number of Markers in Board #" + 
		    				(i + 1) + " > Row #" + (row + 1));
		    		}
		    		
				    // Iterating through all columns for each row
		    		for (int col = 0; col < rowMarkers.length; col++) {
		    			char markerSymbol = rowMarkers[col];
		    			board.addMarker(row, col, markerSymbol);
		    		}
		    	}
		    	
		    	boards[i] = board;
		    }
		} finally {
		    in.close();
		}
	    
	    return boards;
	}
}
