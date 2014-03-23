package controllers.actors;

import play.mvc.Http.MultipartFormData;
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
			String postcode = data.postcode;
			SmallLargeUserPostcodes postcodeObj = SmallLargeUserPostcodes.getSmallLargeUserPostcodesFromPostcode(postcode);
			this.getSender().tell(new Update(20), this.getSelf());
			MultiMemberWards wardData = MultiMemberWards.getMultiMemberWardsFromDatazone(postcodeObj.datazone);
			this.getSender().tell(new Update(40), this.getSelf());
			DomesticElectricityConsumption dec =  DomesticElectricityConsumption.getDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
			this.getSender().tell(new Update(60), this.getSelf());
			NonDomesticElectricityConsumption nondec =  NonDomesticElectricityConsumption.getNonDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(wardData.intermediateGeographyCode);
			this.getSender().tell(new Update(80), this.getSelf());
			double generationCapacity = SolarIrradianceData.calculateAverageEnergyGeneration(16)

			DatazoneWindspeed windspeed = DatazoneWindspeed.getDatazoneWindspeedFromDatazone(postcodeObj.datazone);
			this.getSender().tell(new Update(105), this.getSelf());
			this.data=((Start)msg).getData();
			for (int x=0; x<=105 ; x++){
				Thread.sleep(1000);
				this.getSender().tell(new Update(x), this.getSelf());
				
			}
			getSelf().tell(new End(), getSelf());
			getSelf().tell(PoisonPill.getInstance(), getSelf());
		}
		
	}

}
