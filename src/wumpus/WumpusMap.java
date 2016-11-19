package wumpus;


/**
 * This is a very simple version of the Wumpus Game.
 * 
 * @author erikbjorvik
 *
 */
public class WumpusMap extends WumpusCore {
	
	// THE MAP 
	protected CellData[][] map = new CellData[MAP_SIZE][MAP_SIZE];
	
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
							toReturn += map[j][i].toString();
					}
					toReturn += System.getProperty("line.separator");
				}
				
		return toReturn;
	
	}
	
	public CellData[][] getMap() {
		return map;
	}
	
	public void setMap(CellData[][] map) {
		this.map = map;
	}
	
	
 

}
