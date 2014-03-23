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

			double solarGenerationCapacity = SolarIrradianceData.calculateAverageEnergyGeneration(solarArea);
			double windGenerationCapacity10m = DatazoneWindspeed.getWindGenerationValue10m(postcodeObj.datazone,1);
			double windGenerationCapacity25m = DatazoneWindspeed.getWindGenerationValue25m(postcodeObj.datazone,1);
			double windGenerationCapacity45m = DatazoneWindspeed.getWindGenerationValue45m(postcodeObj.datazone,1);

			DatazoneWindspeed windspeed = DatazoneWindspeed.getDatazoneWindspeedFromDatazone(postcodeObj.datazone);
			this.getSender().tell(new Update(100), this.getSelf());
			Html render = result.render("Hello World",0.0,0,0.0,0);
			getSender().tell(new End(render), getSelf());
			getSelf().tell(PoisonPill.getInstance(), getSelf());
			
		}
		
	}

}
