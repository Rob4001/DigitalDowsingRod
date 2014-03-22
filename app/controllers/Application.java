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

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }

    public static Result about() {
    	return ok(about.render(""));
    }
    
    public static Result map() {
        return ok(map.render(""));
    }
    
    public static Result questions() {
        return ok(questions.render(""));
    }
    
    public static Result resultsSubmit(){
    	MultipartFormData body = request().body().asMultipartFormData();
    	ActorRef myActor = Akka.system().actorOf(Props.create(MasterActor.class,""));
    	myActor.tell(new StartMessage(body), ActorRef.noSender());
    	return ok(results.render(myActor.path().name()));
    }
    
    public static Result progress(String uuid) {
    	 uuid =  "akka://application/user/"+uuid;
    	 Timeout timeout = new Timeout(Duration.create(5, "seconds"));
         ActorRef myAct=  Akka.system().actorSelection(uuid).anchor();
         Future<Object> future = Patterns.ask(myAct, new Ping(), 3000);
         try {
			return ok(Integer.toString(((Update) Await.result(future, timeout.duration())).getProgress()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return internalServerError("Server Derp");
        }
    
    public static Result results() {
        return ok(results.render(""));
    }

    public static Result dataImport() {
        return ok(dataImport.render(""));
    }

    public static Result householdImport() {

    	

		MultipartFormData body = request().body().asMultipartFormData();
		FilePart household = body.getFile("household");
		if (household != null) {
			String fileName = household.getFilename();
			String contentType = household.getContentType(); 
			File file = household.getFile();
			try {
				HouseholdPopulationPostcode.importFromCSV(file.getCanonicalPath());
			} catch (Exception e){

			}
		} else {
			flash("error", "Missing file");
			return redirect(routes.Application.index());    
		}
		
        return ok(dataImport.render("File Processed Successfully"));
    }

}
