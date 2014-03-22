package controllers.actors;

import play.libs.Akka;
import controllers.actors.messages.StartMessage;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MasterActor extends UntypedActor{

	private ActorRef slave;

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof StartMessage){
			slave = Akka.system().actorOf(Props.create(SlaveActor.class,""));
			slave.tell(msg, this.getSelf());
		}
		
	}

}
