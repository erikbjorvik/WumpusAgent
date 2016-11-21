package jadeExample;

import java.util.ArrayList;

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
import jade.util.leap.HashMap;




public class Company extends Agent {
	
	private String numberOfSteps;
	private AID[] carrierAgents;
	
	// Set if vickrey mode should be active or not.
	private final boolean VICKREY_MODE = false;
		
	protected void setup() {
		
		// Printing out a welcome message in the console.
		System.out.println("Starting new company agent " + getAID().getName() );
		System.out.print("MODE: ");
		
		if (VICKREY_MODE)
			System.out.println("VICKREY");
		else
			System.out.println("NON-VICKREY");
			
		
		
		
		// Get the arguments provided.
		Object[] arguments = getArguments();
		
		// Get the argument, number of steps to destination.
		if (arguments!=null && arguments.length>0) {
			numberOfSteps = (String) arguments[0];
			
			addBehaviour(new TickerBehaviour(this, 6000) {
				protected void onTick() {
			//Defining the type of agents we want to communicate with:
			DFAgentDescription dfad = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("carrier");
			dfad.addServices(sd);
			
			try {
				DFAgentDescription[] result = DFService.search(myAgent, dfad); 
				System.out.println("Found some carrier agents:");
				carrierAgents = new AID[result.length];
				for (int i = 0; i < result.length; ++i) {
					carrierAgents[i] = result[i].getName();
					System.out.println(carrierAgents[i].getName());
				}
			}
			catch (FIPAException fe) {
				fe.printStackTrace();
			}
			
			myAgent.addBehaviour(new CompanyRequestBehaviour());
			
			
		}
			});
		}
				
		
		else {
			// Terminate the agent if no arguments are provided.
			System.out.println("No arguments given, terminating the agent");
			doDelete();
		}
		
		
		
		
		}
	

	// A generic behaviour.
	
	public class CompanyRequestBehaviour extends Behaviour {
		
		private ArrayList prices = new ArrayList();
		
		// The agent who provides the best offer
		private AID bestCarrier;  
		
		// The final price
		private int finalPrice; 
		
		// The lowest price offered
		private int bestPrice; 
		
		// The second lowest price offered
		private int secondBestPrice = 0;
		
		// Number of replies from carrier agents
		private int numberOfReplies = 0; 
		
		// The template to receive replies
		private MessageTemplate mt; 
		
		private int step = 0;
		
		@Override
		public void action() {
			switch (step) {
			case 0:
				
				// Send the cfp to all sellers
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < carrierAgents.length; ++i) {
					cfp.addReceiver(carrierAgents[i]);
				} 
				cfp.setContent(numberOfSteps);
				cfp.setConversationId("carrier-trade");
				cfp.setReplyWith("cfp"+System.currentTimeMillis()); // Unique value
				System.out.println("CFP  content:" + cfp.getContent());
				myAgent.send(cfp);
				// Prepare the template to get proposals
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("carrier-trade"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				step = 1;
				break;
			case 1:
				// Receive all proposals/refusals from the carrier agents.
				ACLMessage reply = myAgent.receive(mt);
				if (reply != null) {
					// Reply received
					
					System.out.println("Got response: " + reply.getContent());
					
					
					if (reply.getPerformative() == ACLMessage.PROPOSE) {
						// This is an offer 
						int price = Integer.parseInt(reply.getContent());
						prices.add(price);
						
						if (bestCarrier == null || price < bestPrice) {
			
							// This is the best offer at present
							bestPrice = price;

							bestCarrier = reply.getSender();
						}
					}
					numberOfReplies++;
					if (numberOfReplies >= carrierAgents.length) {
						// We received all replies
						step = 2; 
					}
				}
				else {
					System.out.println("The response was empty");
					block();
				}
				break;
			case 2:
				
				if (VICKREY_MODE) {
					
					Object minPrice = prices.get(0);
					Integer mValue = (Integer) minPrice;
					int removeIndex = 0;
					int count = 0;
					
					for (Object i : prices) {
					
						Integer cValue = (Integer) i;
						
						if (cValue<mValue) {
							
							mValue = cValue;
							removeIndex = count;

						}
						
						count++;

					}
					
					prices.remove(removeIndex);
					
					minPrice = prices.get(0);
					mValue = (Integer) minPrice;
					
					for (Object i : prices) {
						
						Integer cValue = (Integer) i;
						
						if (cValue<mValue) {
							
							mValue = cValue;
							
						}
						
					}
					
					finalPrice = mValue;
					
				}
				
				else {
					finalPrice = bestPrice;
				}
				
				// Send the purchase order to the carrier that provided the lowest price
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(bestCarrier);
				order.setContent(""+finalPrice);
				order.setConversationId("carrier-trade");
				order.setReplyWith("order"+System.currentTimeMillis());
				myAgent.send(order);
				// Prepare the template to get the purchase order reply
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("carrier-trade"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				step = 3;
				break;
			case 3:      
				// Receive the purchase order reply
				reply = myAgent.receive(mt);
				if (reply != null) {
					// Purchase order reply received
					if (reply.getPerformative() == ACLMessage.INFORM) {
						// Purchase successful. We can terminate
						System.out.println("A deal was made with carrier agent "+reply.getSender().getName());
						
						System.out.println("Price = "+finalPrice);
						myAgent.doDelete();
					}
					else {
						System.out.println("A trade has already been made.");
					}

					step = 4;
				}
				else {
					block();
				}
				break;
			}
		}        

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return false;
		}

	}


}
