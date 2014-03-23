package controllers;

import play.mvc.*;
import views.html.*;
import models.*;

public class Util extends Controller {

	public static Result userInformationTable(String postcode){
		SmallLargeUserPostcodes postcodeObj = SmallLargeUserPostcodes.getSmallLargeUserPostcodesFromPostcode(postcode);
		MultiMemberWards wardData = MultiMemberWards.getMultiMemberWardsFromDatazone(postcodeObj.datazone);
		System.out.println(SolarIrradianceData.calculateAverageEnergyGeneration(16));
		DomesticElectricityConsumption dec =  DomesticElectricityConsumption.getDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
		NonDomesticElectricityConsumption nondec =  NonDomesticElectricityConsumption.getNonDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
		DatazoneWindspeed windspeed = DatazoneWindspeed.getDatazoneWindspeedFromDatazone(postcodeObj.datazone);

		return ok(userstats.render(postcodeObj,wardData, dec, nondec, windspeed));
	}

	//public static Result getSolarRecommendation(){
	//	SolarIrradianceData solar = SolarIrradianceData.
	//}


}