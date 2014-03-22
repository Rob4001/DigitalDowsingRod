package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import java.io.File;

import views.html.*;
import models.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }
    
    public static Result map() {
        return ok(map.render(""));
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
