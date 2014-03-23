package controllers.actors;

import models.DatazoneWindspeed;
import models.DomesticElectricityConsumption;
import models.MultiMemberWards;
import models.NonDomesticElectricityConsumption;
import models.SmallLargeUserPostcodes;
import models.SolarIrradianceData;
import play.api.templates.Html;
import play.mvc.Http.MultipartFormData;
import views.html.result;
import controllers.actors.messages.End;
import controllers.actors.messages.Start;
import controllers.actors.messages.Update;
import akka.actor.PoisonPill;
import akka.actor.UntypedActor;

public class SlaveActor extends UntypedActor{
	private MultipartFormData data;
	public SlaveActor(String s) {
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Start){


			this.data=((Start)msg).getData();

			String postcode = data.asFormUrlEncoded().get("postcode")[0];
			int solarArea = Integer.parseInt(data.asFormUrlEncoded().get("solarArea")[0]);

			SmallLargeUserPostcodes postcodeObj = SmallLargeUserPostcodes.getSmallLargeUserPostcodesFromPostcode(postcode);
			this.getSender().tell(new Update(20), this.getSelf());
			MultiMemberWards wardData = MultiMemberWards.getMultiMemberWardsFromDatazone(postcodeObj.datazone);
			this.getSender().tell(new Update(40), this.getSelf());
			DomesticElectricityConsumption dec =  DomesticElectricityConsumption.getDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
			this.getSender().tell(new Update(60), this.getSelf());
			NonDomesticElectricityConsumption nondec =  NonDomesticElectricityConsumption.getNonDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
			this.getSender().tell(new Update(80), this.getSelf());

			double solarGenerationCapacity = SolarIrradianceData.calculateAverageEnergyGeneration(solarArea, 0.19);
			
			int noOfCells = (int) (solarArea/1.44);
			double solarCostMonoCell = SolarIrradianceData.calculateAverageEnergyGeneration(noOfCells, 0.15);
			double solarCostPolyCell = SolarIrradianceData.calculateAverageEnergyGeneration(noOfCells, 0.13);
			double solarCostMonoCellPayback = SolarIrradianceData.getFeedInPotentialPayback(noOfCells, 0.15,dec.averageOrdinaryDomesticconsumption);
			double solarCostPolyCellPayback = SolarIrradianceData.getFeedInPotentialPayback(noOfCells, 0.13,dec.averageOrdinaryDomesticconsumption);
			double windGenerationCapacity10m = DatazoneWindspeed.getWindGenerationValue10m(postcodeObj.datazone,20);
			double windGenerationPayback10m = DatazoneWindspeed.getWindGenerationValue10mPayback(postcodeObj.datazone,20,dec.averageOrdinaryDomesticconsumption);
			double windGenerationCapacity25m = DatazoneWindspeed.getWindGenerationValue25m(postcodeObj.datazone,20);
			double windGenerationPayback25m = DatazoneWindspeed.getWindGenerationValue25mPayback(postcodeObj.datazone,20,dec.averageOrdinaryDomesticconsumption);
			double windGenerationCapacity45m = DatazoneWindspeed.getWindGenerationValue45m(postcodeObj.datazone,20);

			DatazoneWindspeed windspeed = DatazoneWindspeed.getDatazoneWindspeedFromDatazone(postcodeObj.datazone);
			System.out.println(windspeed.windspeed10m);
			this.getSender().tell(new Update(100), this.getSelf());
			Html render = result.render(wardData, postcodeObj, dec, nondec,"Wello Horld",solarCostMonoCell,solarCostMonoCellPayback,solarCostPolyCell,solarCostPolyCellPayback,windGenerationCapacity10m,windGenerationPayback10m,windGenerationCapacity25m,windGenerationPayback25m);
			getSender().tell(new End(render), getSelf());
			getSelf().tell(PoisonPill.getInstance(), getSelf());
			
		}
		
	}

}
