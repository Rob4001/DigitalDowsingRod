package controllers.actors.messages;

public class Update {
	
	private int progress;
	private String reason;

	public Update(int p,String reason){
		this.progress = p;
		this.reason = reason;
	}

	public int getProgress() {
		return progress;
	}

	public String getReason() {
		return reason;
	}

}
