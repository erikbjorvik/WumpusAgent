package wumpus;


/**
 * This is a very simple version of the Wumpus Game.
 * 
 * @author erikbjorvik
 *
 */
public class WumpusMap {
	
	protected static final int MAP_SIZE = 10; 	// The size of the map. (nxn)
	protected static final int NUMBER_OF_OBJECTS_AND_PERCEPTS = 10;
	
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
	
	/* This have to go 
	// FOR THE SAKE OF SIMPLICITY (MULTIPLE PERCEPTS)
	protected static final int BREEZE_STENCH = 8;
	protected static final int BREEZE_GLITTER = 9;
	protected static final int STENCH_GLITTER = 10;
	protected static final int BREEZE_STENCH_GLITTER = 11;
	
	protected static final int BUMP_BREEZE = 13;
	protected static final int BUMP_STENCH = 12;
	protected static final int BUMP_GLITTER = 13;

	protected static final int BUMP_BREEZE_STENCH_GLITTER = 13;*/
	
	
	
	// STATES
	protected static final int UNDISCOVERED = 8;
	protected static final int SAFE = 9;
	
	// THE MAP - FOR THE HOST-AGENT!
	protected CellData[][] map = new CellData[MAP_SIZE][MAP_SIZE];
	
	// SYMBOLS
	protected static final String SYMBOL_LIST[] = 
		{"|___|", "  #  ", "  @  ", "  o  ", "  =  ", "  s  ", "  *  ", "  >  ", "|???|", "|___|" /*, "  =s ",
		 "  =* ", "  s* ", " =s* "*/ };
	
	
	/** 
	 * 
	 * Creates a new, undiscovered wumpus map.
	 *  
	 */
	protected WumpusMap() {
		
	}

	
	public String toString() {
		String toReturn = "";
		
		// the rows.
				for(int i=0; i<MAP_SIZE; i++) {
					//the columns.
					for (int j=0; j<MAP_SIZE; j++) {
							toReturn += map[i][j].toString();
					}
					toReturn += System.getProperty("line.separator");
				}
				
		return toReturn;
	
	}
	

}
