package wumpus;

/**
 * This contains the data (objects/percepts) for each
 * cell in the game.
 * 
 * @author erikbjorvik
 *
 */
public class CellData extends WumpusMap {
	
	protected boolean[] cellData = new boolean[10];
	
	protected CellData() {
		
	}
	
	protected CellData(boolean blank, boolean wall, boolean wumpus, boolean pit, 
			boolean breeze, boolean stench, boolean glitter, boolean bump) {
		
		cellData[BLANK] = blank;
		cellData[WALL] = wall;
		cellData[WUMPUS] = wumpus;
		cellData[PIT] = pit;
		cellData[BREEZE] = breeze;
		cellData[STENCH] = stench;
		cellData[GLITTER] = glitter;
		cellData[BUMP] = bump;
		
	}
	
	protected CellData(boolean[] cellData) {
		
		this.cellData = null;
		this.cellData = cellData;
		
	}
	
	public String toString() {
		
		String toReturn = "";
		for (int i=0; i<NUMBER_OF_OBJECTS_AND_PERCEPTS;i++) {
			if (cellData[i])
				toReturn+=SYMBOL_LIST[i];
		}
		return toReturn;
			
	}
	
	protected boolean[] getArray() {
		return cellData;
	}
	
	

}
