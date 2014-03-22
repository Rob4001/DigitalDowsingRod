package controllers.actors;

import play.mvc.Http.MultipartFormData;
import controllers.actors.messages.End;
import controllers.actors.messages.Start;
import controllers.actors.messages.Update;
import akka.actor.PoisonPill;
import akka.actor.UntypedActor;

public class SlaveActor extends UntypedActor{
	private MultipartFormData data;
	public SlaveActor(String s) {
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Start){
			this.data=((Start)msg).getData();
		for (int x=0; x<=105 ; x++){
			Thread.sleep(1000);
			this.getSender().tell(new Update(x), this.getSelf());
			
		}
		getSelf().tell(new End(), getSelf());
		getSelf().tell(PoisonPill.getInstance(), getSelf());
		}
		
	}

}
