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
			
			for(int i=0; i<MAP_SIZE; i++) {
				//the columns.
				for (int j=0; j<MAP_SIZE; j++) {
						map[i][j] = new CellData();
				}
				
			}
		
	}
	
	/**
	 * Set walls in all the outer cells.
	 * @param map
	 */
	private void setWalls() {
		
		for (int i=0; i<MAP_SIZE;i++) {
			
			
			//Set first row to walls.
			map[0][i].addTrue(WALL);
			map[0][i].addFalse(BLANK);
			
			// Set last row to walls.
			map[MAP_SIZE-1][i].addTrue(WALL);
			map[MAP_SIZE-1][i].addFalse(BLANK);
			// Set all the cells at left side to walls.
			map[i][0].addTrue(WALL);
			map[i][0].addFalse(BLANK);
			
			// Set all the cells at left side to walls.
			map[i][MAP_SIZE-1].addTrue(WALL);
			map[i][MAP_SIZE-1].addFalse(BLANK);
			
		}
		
	}
	
	/**
	 * Sets out some objects and corresponding percepts for
	 * the 10x10 map.
	 * 
	 */
	protected void setHardcodedMap() {
		
		map[4][2].addTrue(PIT); 
		map[4][1].addTrue(BREEZE);
		map[4][3].addTrue(BREEZE);
		map[3][2].addTrue(BREEZE);
		map[5][2].addTrue(BREEZE); 
		
		map[5][5].addTrue(PIT);
		map[5][4].addTrue(BREEZE);
		map[5][6].addTrue(BREEZE);
		map[4][5].addTrue(BREEZE);
		map[6][5].addTrue(BREEZE); 
		
		map[1][7].addTrue(PIT);
		map[1][6].addTrue(BREEZE);
		map[1][8].addTrue(BREEZE);
		map[2][7].addTrue(BREEZE); 
		// map[0][7] THIS IS A WALL!
		
		map[6][7].addTrue(GLITTER);
		
		map[4][5].addTrue(WUMPUS);
		map[4][4].addTrue(STENCH); 
		map[4][6].addTrue(STENCH);
		map[3][5].addTrue(STENCH);
		//map[5][5] PIT IS HERE
		
		
	}
	
}
