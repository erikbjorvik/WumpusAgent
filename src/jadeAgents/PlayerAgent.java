package jadeAgents;

import java.io.IOException;
import java.util.Random;

import jade.core.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.util.leap.HashMap;
import jess.JessException;
import jess.Rete;
import wumpus.CellData;
import wumpus.WumpusCore;
import wumpus.WumpusMapAgent;



public class PlayerAgent extends Agent {
	
	private AID[] hostAgents = null;
	private WumpusMapAgent map;
	private int[] nextMove;
	private boolean onGoingCom = false;
	private Rete engine; //JESS Engine
	
	protected void setup() {
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType("wPLAYER");
		sd.setName(getLocalName());
		
		DFAgentDescription dad = new DFAgentDescription();
  		dad.setName(getAID());
  		dad.addServices(sd);
  		
		
  		
  		try {
  			DFService.register(this, dad);
  			map = new WumpusMapAgent();
  			nextMove = new int[2];
  			
  			nextMove[0] = 1;
  			nextMove[1] = 1;
  		}
  		
  		catch(Exception e) {
  			e.printStackTrace();
  		}
		
		// Printing out a message stating that the agent is ready for service.
		System.out.println("Wumpus player "+getAID().getName()+" is ready.");
		
		// Now we need to find the host agent.
		
		addBehaviour(new TickerBehaviour(this, 6000) {
			protected void onTick() {
				
				// If no host, lets find one.
				
				if (hostAgents==null) {
				
					//Defining the type of agents we want to communicate with:
					DFAgentDescription dfad = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("wHOST");
					dfad.addServices(sd);
					
					try {
						DFAgentDescription[] result = DFService.search(myAgent, dfad); 
						System.out.println("Found wumpus host agent:");
						hostAgents = new AID[result.length];
						for (int i = 0; i < result.length; ++i) {
							hostAgents[i] = result[i].getName();
							System.out.println(hostAgents[i].getName());
						}
						System.out.println("Now ready to communicate with " + hostAgents[0].getName());
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}
				}
				
				else {
					// Lets communicate with the host agent.
					
					myAgent.addBehaviour(new Play());
					
				}
			}
		});
		
	}
	
	private class Play extends Behaviour {
		
		int step = 0;
		
		
		@Override
		public void action() {
			
			switch (step) {
			case 0:
				onGoingCom = true;
				// decide where to move and send it to the host.
				ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
				msg.addReceiver(hostAgents[0]);
				
				// JESS HERE!
				engine = new Rete();
				try {
					// Reset engine
					engine.reset();
					// Load the jess file
				    engine.batch("src/jadeAgents/playerBehaviour.clp");
				    engine.add(myAgent);
				    engine.run();

				    
				} catch (JessException e) {
					e.printStackTrace();
				}
				

				
				try {
					
					Object[] sendThis = new Object[2];
					sendThis[0] = nextMove[0] + "," + nextMove[1];
					sendThis[1] = map;
					
					msg.setContentObject(sendThis);
					myAgent.send(msg);
				    String dir = engine.fetch("dir").stringValue(engine.getGlobalContext());
				    System.out.println("FRA JESS:!!" + dir);
					System.out.println("Wanting to move to position " + nextMove[0] + "," + nextMove[1]);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Could not set the map object.");
				} catch (JessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				step++;

				break;
				
			case 1:
				
				// If communication is ongoing, jump out.
				if (onGoingCom) {
					step = 2;
					done();
				}
				
				
					
				
				// get response from host
				msg = receive();
				if (msg != null) {
					
					Object[] response = null;
					
					try {
						response = (Object[]) msg.getContentObject();
						map.setMap((CellData[][]) response[1]);
						System.out.println("The move was accepted! Perceived" + response[0]);
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
					if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
					
						
						
						if ((Integer) response[0]==WumpusCore.PIT) {
							System.out.println(myAgent.getName() + " fell in a pit. GAME OVER!");
							doDelete();
						}
						if ((Integer)response[0]==WumpusCore.WUMPUS) {
							System.out.println(myAgent.getName() + " got killed by a wumpus. GAME OVER!");
							doDelete();
						}
							
						Random r = new Random();
						int Low = 0;
						int High = 10;
						 
						
						nextMove[0] = r.nextInt(High-Low) + Low;
						nextMove[1] = r.nextInt(High-Low) + Low;
						
					}
					else {
						System.out.println("The move was not accepted. Perceived" + response[0]);
					}
				}
				
				System.out.println(myAgent.getName() + " at position x,y, knows this:");
				System.out.println(map.toString());

				step++;
				break;
			}
			
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return (step==2);
		}
		
	}

}
