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
			this.getSender().tell(new Update(10), this.getSelf());
			Thread.sleep(500);
			MultiMemberWards wardData = MultiMemberWards.getMultiMemberWardsFromDatazone(postcodeObj.datazone);
			this.getSender().tell(new Update(20), this.getSelf());
			Thread.sleep(250);
			DomesticElectricityConsumption dec =  DomesticElectricityConsumption.getDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
			this.getSender().tell(new Update(30), this.getSelf());
			Thread.sleep(250);
			NonDomesticElectricityConsumption nondec =  NonDomesticElectricityConsumption.getNonDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
			this.getSender().tell(new Update(40), this.getSelf());
			Thread.sleep(250);

			double solarGenerationCapacity = SolarIrradianceData.calculateAverageEnergyGeneration(solarArea, 0.19);
			
			int noOfCells = (int) (solarArea/1.44);
			double solarCostMonoCell = SolarIrradianceData.calculateAverageEnergyGeneration(noOfCells, 0.15);
			double solarCostPolyCell = SolarIrradianceData.calculateAverageEnergyGeneration(noOfCells, 0.13);
			double solarCostMonoCellPayback = SolarIrradianceData.getFeedInPotentialPayback(noOfCells, 0.15,dec.averageOrdinaryDomesticconsumption);
			double solarCostPolyCellPayback = SolarIrradianceData.getFeedInPotentialPayback(noOfCells, 0.13,dec.averageOrdinaryDomesticconsumption);
			this.getSender().tell(new Update(50), this.getSelf());
			Thread.sleep(1000);
			double windGenerationCapacity10m = DatazoneWindspeed.getWindGenerationValue10m(postcodeObj.datazone,20);
			double windGenerationPayback10m = DatazoneWindspeed.getWindGenerationValue10mPayback(postcodeObj.datazone,20,dec.averageOrdinaryDomesticconsumption);
			double windGenerationCapacity25m = DatazoneWindspeed.getWindGenerationValue25m(postcodeObj.datazone,20);
			double windGenerationPayback25m = DatazoneWindspeed.getWindGenerationValue25mPayback(postcodeObj.datazone,20,dec.averageOrdinaryDomesticconsumption);
			double windGenerationCapacity45m = DatazoneWindspeed.getWindGenerationValue45m(postcodeObj.datazone,20);
			this.getSender().tell(new Update(60), this.getSelf());
			Thread.sleep(500);

			double pricePerWatt = 13.52;
			solarCostMonoCellPayback = 13.52 * solarCostMonoCell;
			solarCostPolyCellPayback = 13.52 * solarCostPolyCell;
			windGenerationPayback10m = 13.52 * windGenerationCapacity10m;
			windGenerationPayback25m = 13.52 * windGenerationCapacity25m;
			this.getSender().tell(new Update(70), this.getSelf());
			Thread.sleep(250);

			DatazoneWindspeed windspeed = DatazoneWindspeed.getDatazoneWindspeedFromDatazone(postcodeObj.datazone);
			this.getSender().tell(new Update(80), this.getSelf());
			System.out.println(windspeed.windspeed10m);
			String title = "";
			if (windGenerationCapacity25m > solarCostMonoCell){
				title += "Looks like wind could be right for you";
			} else {
				title += "Looks like solar could be right for you";
			}
			this.getSender().tell(new Update(90), this.getSelf());
			Thread.sleep(1000);
			boolean viewSolar = data.asFormUrlEncoded().get("solar") != null;
			boolean viewWind = data.asFormUrlEncoded().get("wind")  != null;
			this.getSender().tell(new Update(100), this.getSelf());
			Html render = result.render(wardData, postcodeObj, dec, nondec,title,solarCostMonoCell,solarCostMonoCellPayback,solarCostPolyCell,solarCostPolyCellPayback,windGenerationCapacity10m,windGenerationPayback10m,windGenerationCapacity25m,windGenerationPayback25m, viewSolar, viewWind);
			getSender().tell(new End(render), getSelf());
			getSelf().tell(PoisonPill.getInstance(), getSelf());
			
		}
		
	}

}
