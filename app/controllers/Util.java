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
		DomesticElectricityConsumption dec =  DomesticElectricityConsumption.getDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
		NonDomesticElectricityConsumption nondec =  NonDomesticElectricityConsumption.getNonDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
		DatazoneWindspeed windspeed = DatazoneWindspeed.getDatazoneWindspeedFromDatazone(postcodeObj.datazone);

		return ok(userstats.render(postcodeObj,wardData, dec, nondec, windspeed));
	}

	//public static Result getSolarRecommendation(){
	//	SolarIrradianceData solar = SolarIrradianceData.
	//}


}