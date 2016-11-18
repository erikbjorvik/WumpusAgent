package wumpus;

public class WumpusMapAgent extends WumpusMap {
	/** 
	 * 
	 * Creates a new, undiscovered wumpus map.
	 *  
	 */
	public WumpusMapAgent() {
			
		// Set all the cells as undiscovered
		setUndiscovered();
		
	}
	
	/**
	 * Set all all cells to undiscovered.
	 * @param map
	 */
	protected void setUndiscovered() {
		
		// the rows.
		for(int i=0; i<MAP_SIZE; i++) {
			//the columns.
			for (int j=0; j<MAP_SIZE; j++) {
				
				boolean[] buffer = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
				buffer[UNDISCOVERED]=true;
				map[i][j] = new CellData(buffer);
				
			}
		}
		
	}
	
}
