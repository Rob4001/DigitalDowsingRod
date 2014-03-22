package controllers.actors.messages;

import play.mvc.Http.MultipartFormData;

public class Start {

	private MultipartFormData data;

	public Start(MultipartFormData body) {
		this.data = body;
	}

	public MultipartFormData  getData() {

		return data;
	}

}
