package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import models.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }

    public static Result dataImport() {
        return ok(dataImport.render(""));
    }

    public static Result householdImport() {

        return ok(dataImport.render("File Processed Successfully"));
    }

}
