package controllers.actors;

import controllers.actors.messages.StartMessage;
import controllers.actors.messages.Update;
import akka.actor.PoisonPill;
import akka.actor.UntypedActor;

public class SlaveActor extends UntypedActor{
	public SlaveActor(String s) {
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof StartMessage){
		for (int x=0; x<=100 ; x++){
			Thread.sleep(1000);
			this.getSender().tell(new Update(x), this.getSelf());
			
		}
		getSelf().tell(PoisonPill.getInstance(), getSelf());
		}
		
	}

}
