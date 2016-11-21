package wumpus;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLogic extends WumpusCore {

	@Test
	public void test() {
		
		WumpusMapAgent agent = new WumpusMapAgent();
		WumpusMapHost host = new WumpusMapHost();
		WumpusLogic logic = new WumpusLogic(host.getMap(), agent.getMap());
		
		
		System.out.println("Host:");
		System.out.println(host.toString());
		
		System.out.println("Agent:");
		System.out.println(agent.toString());
		
		
		// Test if hitting a wall gives the correct results!
		assertEquals(true, agent.getMap()[0][0].cellData[UNDISCOVERED]); //undiscovered before the move.
		assertEquals(false, agent.getMap()[0][0].cellData[WALL]);		//no wall before the move
		int perceive = logic.move(0, 0);												//make the move
		agent.setMap(logic.getUpdatedAgentMap());									//set the new map
		assertEquals(BUMP, perceive);		//perceived a bump
		assertEquals(true, agent.getMap()[0][0].cellData[WALL]);		//wall in map 
		assertEquals(false, agent.getMap()[0][0].cellData[UNDISCOVERED]);//not undiscovered.
		
		// The stench should be here (host)
		assertEquals(true, host.getMap()[4][4].cellData[STENCH]);
		assertEquals(true, host.getMap()[4][6].cellData[STENCH]);
		assertEquals(true, host.getMap()[3][5].cellData[STENCH]);	
		
		// Test if shooting the wumpus gives the correct results
		assertEquals(true, agent.getMap()[4][5].cellData[UNDISCOVERED]); //undiscovered before the move.
		assertEquals(false, agent.getMap()[4][5].cellData[WUMPUS]);		//no wumpus before the move
		perceive = logic.shoot(4,5);									//make the move
		agent.setMap(logic.getUpdatedAgentMap());									//set the new map
		assertEquals(SCREAM, perceive);									//perceived a scream
		assertEquals(true, agent.getMap()[4][5].cellData[DEAD_WUMPUS]);		//see dead wumpus
		assertEquals(false, agent.getMap()[4][5].cellData[UNDISCOVERED]);//not undiscovered.
		
		// The stench should be gone (host)
		assertEquals(false, host.getMap()[4][4].cellData[STENCH]);
		assertEquals(false, host.getMap()[4][6].cellData[STENCH]);
		assertEquals(false, host.getMap()[3][5].cellData[STENCH]);
		
		// The stench should now be gone (agent)
		assertEquals(false, agent.getMap()[4][4].cellData[STENCH]);
		assertEquals(false, agent.getMap()[4][6].cellData[STENCH]);
		assertEquals(false, agent.getMap()[3][5].cellData[STENCH]);
		assertEquals(false, agent.getMap()[5][5].cellData[STENCH]);
		
		System.out.println("Host:");
		System.out.println(host.toString());
		
		System.out.println("Agent:");
		System.out.println(agent.toString());
		
		
		

		
		
	}

}
