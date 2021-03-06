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

public class MasterActor extends UntypedActor {

	private ActorRef slave;
	private Update update;
	private End end;

	public MasterActor(String s) {
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Start) {
			slave = Akka.system().actorOf(Props.create(SlaveActor.class, ""));
			slave.tell(msg, this.getSelf());
		} else if (msg instanceof Update) {
			this.update = ((Update) msg);
		} else if (msg instanceof Ping) {
			System.out.println(((Ping)msg).getType().name());
			if (((Ping) msg).getType() == Ping.Type.PROGRESS) {
				this.getSender().tell(update, this.getSelf());
			} else if (((Ping) msg).getType() == Ping.Type.RESULT) {	
				if (end != null) {
					this.getSender().tell(end, this.getSelf());
					getSelf().tell(PoisonPill.getInstance(), getSelf());
				}
			}

		} else if (msg instanceof End) {
			end = (End) msg;
		}

	}

}
