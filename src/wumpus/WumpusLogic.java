package wumpus;

public class WumpusLogic extends WumpusCore {
	
	private CellData[][] hostMap;
	private CellData[][] agentMap;
	
	public WumpusLogic(CellData[][] hostMap, CellData[][] agentMap) {
		
		this.hostMap = hostMap;
		this.agentMap = agentMap;
		
	}
	
	public WumpusLogic(){}
	
<<<<<<< HEAD
	public int move(int x, int y) {
		
		agentMap[x][y] = hostMap[x][y];
		return percept(x,y);
		
	}
	
	public int shoot(int x, int y) {
=======
	public void move(int x, int y) {
		
		agentMap[x][y] = hostMap[x][y];
		
	}
	
	public void shoot(int x, int y) {
>>>>>>> edc014495a7ff67027f140e024db97c76a1f6dc6
		
		// If an arrow is show at the wumpus, kill it (SCREAM).
		if (hostMap[x][y].cellData[WUMPUS]) {
			hostMap[x][y].addFalse(WUMPUS);
			hostMap[x][y].addTrue(DEAD_WUMPUS);
			
			//Remove stench
			hostMap[x][y-1].addFalse(STENCH);
			hostMap[x][y+1].addFalse(STENCH);
			hostMap[x-1][y].addFalse(STENCH);
			hostMap[x+1][y].addFalse(STENCH);
<<<<<<< HEAD
			agentMap[x][y] = hostMap[x][y];
			return SCREAM;
			
		}
		
		return percept(x,y);
			
	}
	
	public int percept(int x, int y) {
		
		if (hostMap[x][y].cellData[WUMPUS])
			return WUMPUS;
		
		if (hostMap[x][y].cellData[DEAD_WUMPUS])
			return DEAD_WUMPUS;
		
		if (hostMap[x][y].cellData[STENCH])
			return STENCH;
		
		if (hostMap[x][y].cellData[WALL])
			return BUMP;
		
		if (hostMap[x][y].cellData[BREEZE])
			return BREEZE;
		
		if (hostMap[x][y].cellData[PIT])
			return PIT;
		
		if (hostMap[x][y].cellData[GLITTER])
			return GLITTER;
		
		return BLANK;
		
		
	}
	
=======
			
			
			agentMap[x][y] = hostMap[x][y];	
			
		}
			
	}
	
>>>>>>> edc014495a7ff67027f140e024db97c76a1f6dc6
	
	public CellData[][] getUpdatedAgentMap() {
		return agentMap;
	}

	public CellData[][] getHostMap() {
		return hostMap;
	}

	public void setHostMap(CellData[][] hostMap) {
		this.hostMap = hostMap;
	}

	public CellData[][] getAgentMap() {
		return agentMap;
	}

	public void setAgentMap(CellData[][] agentMap) {
		this.agentMap = agentMap;
	}
	
	
}
