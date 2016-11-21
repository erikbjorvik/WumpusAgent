package wumpus;

import java.io.Serializable;

public class WumpusCore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int MAP_SIZE = 10; 	// The size of the map. (nxn)
	protected static final int NUMBER_OF_OBJECTS_AND_PERCEPTS = 12;
	
	// OBJECTS
	public static final int BLANK = 0;
	public static final int WALL = 1;
	public static final int WUMPUS = 2;
	public static final int PIT = 3;
	
	// PERCEPTS 
	public static final int BREEZE = 4; 
	public static final int STENCH = 5;
	public static final int GLITTER = 6;
	public static final int BUMP = 7;
	
	// STATES
	public static final int UNDISCOVERED = 8;
	public static final int SAFE = 9;
	
	public static final int DEAD_WUMPUS = 10;
	public static final int SCREAM = 11;

	
	
	// SYMBOLS
	public static final String SYMBOL_LIST[] = 
		{"-", "#", "@", "O", "=", "s", "*", ">", "?", "-", "x", "!"};
	
	
}
