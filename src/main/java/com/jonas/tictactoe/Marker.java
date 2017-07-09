/*
 *
 * Copyright Jonas Nyberg. All Rights Reserved.
 * 
 */

package com.jonas.tictactoe;

public enum Marker {
	CROSS('X'), 
	CIRCLE('O'), 
	WILD_CARD('T'), 
	EMPTY('.');

	private char charSymbol;
	
	private Marker(char charSymbol) {
		this.charSymbol = charSymbol;
	}
	
	public char getCharacterSymbol() {
		return this.charSymbol;
	}
	
	public static Marker parse(char charSymbol) {
		switch (charSymbol) {
			case 'X':
				return Marker.CROSS;
			case 'O':
				return Marker.CIRCLE;
			case 'T':
				return Marker.WILD_CARD;
			case '.':
				return Marker.EMPTY;
			default:
				throw new IllegalArgumentException("Invalid symbol: " + charSymbol);
		}
	}
}
