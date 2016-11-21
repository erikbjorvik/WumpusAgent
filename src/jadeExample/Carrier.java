package jadeExample;

import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Carrier extends Agent {
	private String minPrice;

	protected void setup() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			minPrice = (String) args[0];
			
			// Register agent in the yellow pages
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			ServiceDescription sd = new ServiceDescription();
			sd.setType("carrier");
			sd.setName("JADE-carrier-auction");
			dfd.addServices(sd);
			
			try {
				DFService.register(this, dfd);
			}
			catch (FIPAException fe) {
				fe.printStackTrace();
			}
			
			addBehaviour(new OfferRequestServer());
			addBehaviour(new PurchaseOrdersServer());
		}
		
		else {
			System.out.println("No minimum price specified. Terminating.");
			doDelete();
		}
	}
	
	protected void takeDown() {
		// Remove from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		// Print dismissal message
		System.out.println("Carrier-agent "+getAID().getName()+" terminating.");
	}
	
	private class OfferRequestServer extends CyclicBehaviour {

		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			
			if(msg != null) {
			
				
				Random r = new Random();
				
				String steps = msg.getContent();
				ACLMessage reply = msg.createReply();
				
				int shouldGo = r.nextInt(100);
				
				if(steps != null && true) {
					int stepsInt = Integer.parseInt(steps);
					
					System.out.println("Current carrier may deliver " + steps + " steps.");
					reply.setPerformative(ACLMessage.PROPOSE);
					reply.setContent(minPrice);
				}
				
				else {
					// Won't deliver to location.
					System.out.println("Current carrier can not deliver " + steps + " steps.");
					reply.setPerformative(ACLMessage.REFUSE);
					reply.setContent("not-able");
				}
				
				myAgent.send(reply);
			}
			
			else {
				block();
			}
			
		}

	}
	
	private class PurchaseOrdersServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				// ACCEPT_PROPOSAL Message received. Process it
				ACLMessage reply = msg.createReply();
				if (minPrice != null) {
					reply.setPerformative(ACLMessage.INFORM);
					System.out.println("Delivery for "+ msg.getSender().getName() + " can now be performed.");
				}
				else {
					// The requested job has been sold to another buyer in the meanwhile .
					reply.setPerformative(ACLMessage.FAILURE);
					reply.setContent("not-available");
				}
				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}

}
