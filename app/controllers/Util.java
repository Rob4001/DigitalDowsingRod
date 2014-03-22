package controllers;

import play.*;
import play.libs.Akka;
import play.libs.F;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.libs.F.Promise;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.File;

import controllers.actors.MasterActor;
import controllers.actors.messages.Ping;
import controllers.actors.messages.StartMessage;
import controllers.actors.messages.Update;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.japi.Function;
import akka.pattern.Patterns;
import akka.util.Timeout;
import views.html.*;
import models.*;
import static akka.pattern.Patterns.ask;

public class Util extends Controller {

	public static Result userInformationTable(String postcode){
		SmallLargeUserPostcodes postcodeObj = SmallLargeUserPostcodes.getSmallLargeUserPostcodesFromPostcode(postcode);
		MultiMemberWards wardData = MultiMemberWards.getMultiMemberWardsFromDatazone(postcodeObj.datazone);


		return ok(userstats.render(postcodeObj,wardData));
	}


}