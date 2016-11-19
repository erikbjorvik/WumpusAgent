package wumpus;

/**
 * This contains the data (objects/percepts) for each
 * cell in the game.
 * 
 * @author erikbjorvik
 *
 */
public class CellData extends WumpusMap {
	
	protected boolean[] cellData = new boolean[NUMBER_OF_OBJECTS_AND_PERCEPTS];
	
	protected CellData() {
		
		cellData[BLANK] = true;
		cellData[WALL] = false;
		cellData[WUMPUS] = false;
		cellData[PIT] = false;
		cellData[BREEZE] = false;
		cellData[STENCH] = false;
		cellData[GLITTER] = false;
		cellData[BUMP] = false;
		cellData[SCREAM] = false;
		
	}
	
	protected CellData(boolean blank, boolean wall, boolean wumpus, boolean pit, 
			boolean breeze, boolean stench, boolean glitter, boolean bump, boolean scream) {
		
		cellData[BLANK] = blank;
		cellData[WALL] = wall;
		cellData[WUMPUS] = wumpus;
		cellData[PIT] = pit;
		cellData[BREEZE] = breeze;
		cellData[STENCH] = stench;
		cellData[GLITTER] = glitter;
		cellData[BUMP] = bump;
		cellData[SCREAM] = scream;
		
	}
	
	protected CellData(boolean[] cellData) {
		
		this.cellData = null;
		this.cellData = cellData;
		
	}
	
	public void addTrue(int ob) {
		cellData[ob] = true;
	}
	
	public void addFalse(int ob) {
		cellData[ob] = false;
	}
	
	public String toString() {
		
		String toReturn = "";
		boolean blank = true;
		
		int spaceLeft = 3;
		int spaceRight = 3;
		
		String strLeft = "";
		String strRight = "";
		
		for (int i=NUMBER_OF_OBJECTS_AND_PERCEPTS-1; i>=0; i--) {
			
			// For simplicity in this example, we're only going 
			// to show one object in the graphic model
			
			
			
			if (cellData[i]) {
				
				if ( (i == BLANK || i == SAFE) && !blank)
					break; //break the loop.
				
				toReturn+=SYMBOL_LIST[i];
				
				if (spaceLeft==spaceRight)
					spaceLeft--;
				else if (spaceLeft<spaceRight)
					spaceRight--;
				else
					spaceLeft--;
				
				
				if (i>0)
					blank=false;
				
				
			}
			

			
		}
		// add spaces
		for (int l=0; l<=spaceLeft;l++)
			strLeft+=" ";
		
		for (int r=0; r<=spaceRight;r++)
			strRight+=" ";
		return strLeft + toReturn + strRight;
			
	}
	
	protected boolean[] getArray() {
		return cellData;
	}
	
	

}
