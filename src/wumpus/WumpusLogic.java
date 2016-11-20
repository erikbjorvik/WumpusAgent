package wumpus;

public class WumpusLogic extends WumpusCore {
	
	private CellData[][] hostMap;
	private CellData[][] agentMap;
	
	public WumpusLogic(CellData[][] hostMap, CellData[][] agentMap) {
		
		this.hostMap = hostMap;
		this.agentMap = agentMap;
		
	}
	
	public WumpusLogic(){}
	
	public void move(int x, int y) {
		
		agentMap[x][y] = hostMap[x][y];
		
	}
	
	public void shoot(int x, int y) {
		
		// If an arrow is show at the wumpus, kill it (SCREAM).
		if (hostMap[x][y].cellData[WUMPUS]) {
			hostMap[x][y].addFalse(WUMPUS);
			hostMap[x][y].addTrue(DEAD_WUMPUS);
			
			//Remove stench
			hostMap[x][y-1].addFalse(STENCH);
			hostMap[x][y+1].addFalse(STENCH);
			hostMap[x-1][y].addFalse(STENCH);
			hostMap[x+1][y].addFalse(STENCH);
			
			
			agentMap[x][y] = hostMap[x][y];	
			
		}
			
	}
	
	
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
