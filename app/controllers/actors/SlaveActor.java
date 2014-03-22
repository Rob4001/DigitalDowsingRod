package controllers.actors;

import controllers.actors.messages.Update;
import akka.actor.UntypedActor;

public class SlaveActor extends UntypedActor{
	public SlaveActor(String s) {
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		for (int x=0; x<100 ; x++){
			Thread.sleep(1000);
			this.getSender().tell(new Update(1), this.getSelf());
		}
		
	}

}
