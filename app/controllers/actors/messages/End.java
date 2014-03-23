package controllers.actors.messages;

import play.api.templates.Html;

public class End {
	private Html rend;

	public End(Html rend) {
		this.rend = rend;

	}

	public Html getRendered() {
		return rend;
	}

}
