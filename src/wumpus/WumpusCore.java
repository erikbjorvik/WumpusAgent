package wumpus;

<<<<<<< HEAD
import java.io.Serializable;

public class WumpusCore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
=======
public class WumpusCore {

>>>>>>> edc014495a7ff67027f140e024db97c76a1f6dc6
	protected static final int MAP_SIZE = 10; 	// The size of the map. (nxn)
	protected static final int NUMBER_OF_OBJECTS_AND_PERCEPTS = 12;
	
	// OBJECTS
<<<<<<< HEAD
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
=======
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
>>>>>>> edc014495a7ff67027f140e024db97c76a1f6dc6

	
	
	// SYMBOLS
<<<<<<< HEAD
	public static final String SYMBOL_LIST[] = 
		{"-", "#", "@", "O", "=", "s", "*", ">", "?", "-", "x", "!"};
	
	
=======
	protected static final String SYMBOL_LIST[] = 
		{"-", "#", "@", "O", "=", "s", "*", ">", "?", "-", "x", "!"};
	
>>>>>>> edc014495a7ff67027f140e024db97c76a1f6dc6
}
