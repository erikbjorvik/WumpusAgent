package jadeAgents;

import java.io.IOException;
import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import wumpus.CellData;
import wumpus.WumpusLogic;
import wumpus.WumpusMapAgent;
import wumpus.WumpusMapHost;


public class HostAgent extends Agent {
	
	private WumpusMapHost hostMap;
	private CellData[][] agentMapGlobal = null;
	private WumpusLogic logic;
	
	protected void setup() {
		
		// Register agent in the yellow pages
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.setName(getAID());
					ServiceDescription sd = new ServiceDescription();
					sd.setType("wHOST");
					sd.setName("wumpus-host-" + Math.random());
					dfd.addServices(sd);
					
					try {
						DFService.register(this, dfd);
						hostMap = new WumpusMapHost();
						System.out.println("Registered as a host in the DF Service.");
						logic = new WumpusLogic();
						logic.setHostMap(hostMap.getMap());
						
						
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}
		
		// Printing out a message stating that the agent is ready for service.
		System.out.println("Wumpus host "+getAID().getName()+" is ready.");
		
		addBehaviour(new TickerBehaviour(this, 1000) {
			protected void onTick() {
				myAgent.addBehaviour(new HandleRequest());
			}
		});
	  	
		
	}
	
	private class HandleRequest extends OneShotBehaviour {

		public void action() {
			
			ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
			ACLMessage reply;
			
			int x = 0;
			int y = 0;
			
			if(msg != null) {
				
				if (msg.getPerformative() == ACLMessage.PROPOSE) {
				
				System.out.println("Got a move propsal from " + msg.getSender().getName());
				
				// Get the cordinates
				Object[] co = null;
				try {
					co = (Object[]) msg.getContentObject();
				} catch (UnreadableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String strCor = (String) co[0];
				
				
				String[] crd = strCor.split(",");
				
				WumpusMapAgent agentMap = (WumpusMapAgent) co[1];
				
				if (agentMapGlobal == null)
					agentMapGlobal = agentMap.getMap();
				
				logic.setAgentMap(agentMapGlobal);
				
				int perceived = logic.move(Integer.parseInt(crd[0]), Integer.parseInt(crd[1]));
				agentMapGlobal = logic.getAgentMap();
				
				reply = msg.createReply();

				
				// If a bump is perceived, the agent should get a reject on the move.
				if (perceived == WumpusMapHost.BUMP)
					reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
				else
					reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
				
				try {
					Object[] sendThis = new Object[2];
					sendThis[0] = new Integer(perceived);
					sendThis[1] = agentMapGlobal;
				
					reply.setContentObject(sendThis);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Now, send the new agent map to all players!
				
				myAgent.send(reply);
				
				}
				
				
				
				else {
					reply = msg.createReply();
					
					// Won't deliver to location.
					System.out.println("Didnt get any message");
					reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
					reply.setContent("not-able");
				}
				
				//myAgent.send(reply);
			}
			
			
			msg = null;
			
		}
			
		}
	
}
