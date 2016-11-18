package util;


/**
 * This is a very simple version of the Wumpus Game.
 * 
 * @author erikbjorvik
 *
 */
public class WumpusMap {
	
	private static final int MAP_SIZE = 10; 	// The size of the map. (nxn)
	private int MODE = 0;						// 0 for host, 1 for agent.
	
	// OBJECTS
	private static final int BLANK = 0;
	private static final int WALL = 1;
	private static final int WUMPUS = 2;
	private static final int PIT = 3;
	
	// PERCEPTS 
	private static final int BREEZE = 4;
	private static final int STENCH = 5;
	private static final int GLITTER = 6;
	private static final int BUMP = 7;
	
	// FOR THE SAKE OF SIMPLICITY (MULTIPLE PERCEPTS)
	private static final int BREEZE_STENCH = 8;
	private static final int BREEZE_GLITTER = 9;
	private static final int STENCH_GLITTER = 10;
	private static final int BREEZE_STENCH_GLITTER = 11;
	
	private static final int BUMP_BREEZE = 13;
	private static final int BUMP_STENCH = 12;
	private static final int BUMP_GLITTER = 13;

	private static final int BUMP_BREEZE_STENCH_GLITTER = 13;
	
	
	
	// STATES
	private static final int UNDISCOVERED = 11;
	private static final int SAFE = 12;
	
	// THE MAP - FOR THE HOST-AGENT!
	private int[][] map = new int[MAP_SIZE][MAP_SIZE];
	
	// THE MAP - FOR THE NORMAL-AGENT!
	private int[][] mapAgent = new int[MAP_SIZE][MAP_SIZE];
	
	// SYMBOLS
	private static final String SYMBOL_LIST[] = 
		{"|___|", "  #  ", "  @  ", "  o  ", "  =  ", "  s  ", "  *  ", "  >  ", "  =s ",
		 "  =* ", "  s* ", " =s* ", "|???|", "|___|" };
	
	
	// This will not be used.
	public WumpusMap() {
		
	}
	
	/** Creates a new, undiscovered wumpus map
	 *  This constructor is for the host agent!
	 *  @param Either 'host' or 'agent'
	 */
	public WumpusMap(String agentType) {
		
		
		if (agentType.equals("host")) {
			
			MODE = 0;
			
			// Set the walls.
			setWalls(map);
			
			// Set the hardcoded map
			setHardcodedMap();
			
		}
		
		else if (agentType.equals("agent")) {
			
			MODE = 1;
			
			// Set all the cells as undiscovered
			setUndiscovered(mapAgent);
			
			// Now the agent map is ready to be discovered!
			
		}
		
	
		
	}
	
	/**
	 * Set walls in all the outer cells.
	 * @param aMap
	 */
	private void setWalls(int[][] aMap) {
		
		for (int i=0; i<MAP_SIZE;i++) {
			
			// Set first row to walls.
			aMap[0][i] = WALL;
			// Set bumps under
			aMap[1][i] = BUMP;
			
			// Set last row to walls.
			aMap[MAP_SIZE-1][i] = WALL;
			
			// Set all the cells at left side to walls.
			aMap[i][0] = WALL;
			
			// Set all the cells at left side to walls.
			aMap[i][MAP_SIZE-1] = WALL;
			
		}
		
	}
	
	/**
	 * Set all all cells to undiscovered.
	 * @param aMap
	 */
	private void setUndiscovered(int[][] aMap) {
		
		// the rows.
		for(int i=0; i<MAP_SIZE; i++) {
			//the columns.
			for (int j=0; j<MAP_SIZE; j++) {
				mapAgent[i][j] = UNDISCOVERED;
			}
		}
		
	}
	
	/**
	 * Sets out some objects and corresponding percepts for
	 * the 10x10 map.
	 * 
	 * @param aMap
	 */
	public void setHardcodedMap() {
		
		map[4][2] = PIT; 
		map[4][1] = BREEZE; 
		map[4][3] = BREEZE;
		map[3][2] = BREEZE; 
		map[5][2] = BREEZE; 
		
		map[5][5] = PIT; 
		map[5][4] = BREEZE; 
		map[5][6] = BREEZE;
		// map[4][5] = BREEZE; THE WUMPUS IS HERE.
		map[6][5] = BREEZE; 
		
		map[1][7] = PIT; 
		map[1][6] = BREEZE; 
		map[1][8] = BREEZE;
		map[2][7] = BREEZE; 
		// map[0][7] = BREEZE; THIS IS A WALL!
		
		map[6][7] = GLITTER;
		
		map[4][5] = WUMPUS; 
		map[4][4] = STENCH; 
		map[4][6] = STENCH;
		map[3][5] = STENCH; 
		//map[5][5] = STENCH; PIT IS HERE
		
		
	}
	
	public String toString() {
		String toReturn = "";
		
		// the rows.
				for(int i=0; i<MAP_SIZE; i++) {
					//the columns.
					for (int j=0; j<MAP_SIZE; j++) {
						if (MODE==0)
							toReturn += SYMBOL_LIST[map[i][j]];
						else 
							toReturn += SYMBOL_LIST[mapAgent[i][j]];
					}
					toReturn += System.getProperty("line.separator");
				}
				
		return toReturn;
		
	}
	

}
