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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import controllers.actors.MasterActor;
import controllers.actors.messages.End;
import controllers.actors.messages.Ping;
import controllers.actors.messages.Start;
import controllers.actors.messages.Update;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Identify;
import akka.actor.Props;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.japi.Function;
import akka.pattern.Patterns;
import akka.util.Timeout;
import views.html.*;
import models.*;
import static akka.pattern.Patterns.ask;

public class Application extends Controller implements F.Function<Object,Result>{

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
    	myActor.tell(new Start(body), ActorRef.noSender());
    	try {
			return ok(results.render(URLEncoder.encode(myActor.path().toSerializationFormat(),"utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return internalServerError("Server Derp");
		}
    }
    
    public static Result progress(String uuid) {
    	try {
			uuid= URLDecoder.decode(uuid,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return internalServerError("Server Derp");
		}
    	 ActorRef myActor = Akka.system().actorFor(uuid);
    	 try{
         return async(
             Promise.wrap(ask(myActor, new Ping(Ping.Type.PROGRESS), 10000)).map(
                new Application()
             )
         );
    	 }catch(Exception e){
    		 return ok("100");
    	 }

        }
    
    public static Result result(String uuid) {
    	try {
			uuid= URLDecoder.decode(uuid,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return internalServerError("Server Derp");
		}
    	 ActorRef myActor = Akka.system().actorFor(uuid);
    	 try{
         return async(
             Promise.wrap(ask(myActor, new Ping(Ping.Type.RESULT), 10000)).map(
                new Application()
             )
         );
    	 }catch(Exception e){
    		 return ok("100");
    	 }

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

	@Override
	public Result apply(Object response) {
		if(response instanceof Update){
			return ok(Integer.toString(((Update)response).getProgress()));
		}else if(response instanceof End){
			End e = (End)response;
			return ok(result.render("Hello World"));
		}else{
			return ok(response.toString());
		}
        
    }

}
