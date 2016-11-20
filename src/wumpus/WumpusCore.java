package wumpus;

public class WumpusCore {

	protected static final int MAP_SIZE = 10; 	// The size of the map. (nxn)
	protected static final int NUMBER_OF_OBJECTS_AND_PERCEPTS = 12;
	
	// OBJECTS
	protected static final int BLANK = 0;
	protected static final int WALL = 1;
	protected static final int WUMPUS = 2;
	protected static final int PIT = 3;
	
	// PERCEPTS 
	protected static final int BREEZE = 4; 
	protected static final int STENCH = 5;
	protected static final int GLITTER = 6;
	protected static final int BUMP = 7;
	
	// STATES
	protected static final int UNDISCOVERED = 8;
	protected static final int SAFE = 9;
	
	protected static final int DEAD_WUMPUS = 10;
	protected static final int SCREAM = 11;

	
	
	// SYMBOLS
	protected static final String SYMBOL_LIST[] = 
		{"-", "#", "@", "O", "=", "s", "*", ">", "?", "-", "x", "!"};
	
}
