package controllers.actors;

import play.libs.Akka;
import controllers.actors.messages.End;
import controllers.actors.messages.Ping;
import controllers.actors.messages.Start;
import controllers.actors.messages.Update;
import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MasterActor extends UntypedActor{

	private ActorRef slave;
	private int progress;
	private End end;

	public MasterActor(String s) {
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Start){
			slave = Akka.system().actorOf(Props.create(SlaveActor.class,""));
			slave.tell(msg, this.getSelf());
		}else if (msg instanceof Update){
			this.progress = ((Update)msg).getProgress();
			
			if(progress >= 105){
				getSelf().tell(PoisonPill.getInstance(), getSelf());
			}
		}else if (msg instanceof Ping){
			if(((Ping)msg).getType()==Ping.Type.PROGRESS){
			this.getSender().tell(new Update(progress), this.getSelf());
			}else if(((Ping)msg).getType()==Ping.Type.RESULT){
				this.getSender().tell(end, this.getSelf());
			}
			
		}else if (msg instanceof End){
			end = (End) msg;
		}
		
	}

}
