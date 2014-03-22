package controllers.actors.messages;

import controllers.actors.messages.Ping.Type;

public class Ping {

	private Type type;

	public Ping(Type t){
		this.type = t;
	}
	
	public enum Type{
		PROGRESS,RESULT
	}

	public Type getType() {
		return type;
	}
}
