package controllers.actors;

import play.libs.Akka;
import controllers.actors.messages.Ping;
import controllers.actors.messages.StartMessage;
import controllers.actors.messages.Update;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MasterActor extends UntypedActor{

	private ActorRef slave;
	private int progress;

	public MasterActor(String s) {
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof StartMessage){
			slave = Akka.system().actorOf(Props.create(SlaveActor.class,""));
			slave.tell(msg, this.getSelf());
		}else if (msg instanceof Update){
			this.progress = ((Update)msg).getProgress();
		}else if (msg instanceof Ping){
			this.getSender().tell(new Update(progress), this.getSelf());
		}
		
	}

}
