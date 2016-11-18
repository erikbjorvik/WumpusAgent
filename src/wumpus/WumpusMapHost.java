package wumpus;

/**
 * This is a version of the wumpus map that is intended for the
 * host-agent in the MAS. This object has the blueprint of the
 * wumpus map. When a agent has visited a cell, it should get to know 
 * what objects/percepts is in this cell. This should be distributed to 
 * all the agents in the MAS.
 * 
 * @author erikbjorvik
 *
 */
public class WumpusMapHost extends WumpusMap {

	public WumpusMapHost() {
		
		// Set cells blank
		setBlank();
		
		// Set the walls.
		setWalls();
		
		// Set the hard coded map
		setHardcodedMap();
		
	}
	
	/**
	 * Set blank in all the cells.
	 * @param map
	 */
	private void setBlank() {
			
			boolean[] buffer = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
			buffer[BLANK]=true;
			CellData blankCell = new CellData(buffer);
			
			for(int i=0; i<MAP_SIZE; i++) {
				//the columns.
				for (int j=0; j<MAP_SIZE; j++) {
						map[i][j] = blankCell;
				}
				
			}
		
	}
	
	/**
	 * Set walls in all the outer cells.
	 * @param map
	 */
	private void setWalls() {
		
		for (int i=0; i<MAP_SIZE;i++) {
			
			boolean[] buffer_w = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
			buffer_w[WALL]=true;
			CellData wallCell = new CellData(buffer_w);
			
			//Set first row to walls.
			map[0][i] = wallCell;
			
			// Set last row to walls.
			map[MAP_SIZE-1][i] = wallCell;
			
			// Set all the cells at left side to walls.
			map[i][0] = wallCell;
			
			// Set all the cells at left side to walls.
			map[i][MAP_SIZE-1] = wallCell;
			
		}
		
	}
	
	/**
	 * Sets out some objects and corresponding percepts for
	 * the 10x10 map.
	 * 
	 */
	protected void setHardcodedMap() {
		
		boolean[] buffer_wall = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
		buffer_wall[WALL]=true;
		
		boolean[] buffer_pit = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
		buffer_pit[PIT]=true;
		
		boolean[] buffer_breeze = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
		buffer_breeze[BREEZE]=true;
		
		boolean[] buffer_glitter = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
		buffer_glitter[GLITTER]=true;
		
		boolean[] buffer_wumpus = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
		buffer_wumpus[WUMPUS]=true;
		
		boolean[] buffer_stench = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
		buffer_stench[STENCH]=true;
		
		map[4][2] = new CellData(buffer_pit); 
		map[4][1] = new CellData(buffer_breeze); 
		map[4][3] = new CellData(buffer_breeze);
		map[3][2] = new CellData(buffer_breeze); 
		map[5][2] = new CellData(buffer_breeze); 
		
		map[5][5] = new CellData(buffer_pit); 
		map[5][4] = new CellData(buffer_breeze); 
		map[5][6] = new CellData(buffer_breeze);
		// map[4][5] = BREEZE; THE WUMPUS IS HERE.
		map[6][5] = new CellData(buffer_breeze); 
		
		map[1][7] = new CellData(buffer_pit);  
		map[1][6] = new CellData(buffer_breeze); 
		map[1][8] = new CellData(buffer_breeze);
		map[2][7] = new CellData(buffer_breeze); 
		// map[0][7] = BREEZE; THIS IS A WALL!
		
		map[6][7] = new CellData(buffer_glitter); 
		
		map[4][5] = new CellData(buffer_wumpus); 
		map[4][4] = new CellData(buffer_stench); 
		map[4][6] = new CellData(buffer_stench);
		map[3][5] = new CellData(buffer_stench);
		//map[5][5] = STENCH; PIT IS HERE
		
		
	}
	
}
