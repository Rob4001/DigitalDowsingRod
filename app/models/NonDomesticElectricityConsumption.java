

package models;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity

public class NonDomesticElectricityConsumption extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
    public String localAuthorityName;
	public String localAuthorityCode;
	public String middleLayerSuperOutputAreaCode;
	public double consumption;
	public int numberOfMeters;
	public double averageConsumption;


    
    public NonDomesticElectricityConsumption(String localAuthorityName,String localAuthorityCode,String middleLayerSuperOutputAreaCode,double consumption,int numberOfMeters,double averageConsumption) {
		this.localAuthorityName = localAuthorityName;
		this.localAuthorityCode = localAuthorityCode;
		this.middleLayerSuperOutputAreaCode = middleLayerSuperOutputAreaCode;
		this.consumption = consumption;
		this.numberOfMeters = numberOfMeters;
		this.averageConsumption = averageConsumption;
    }

    public static NonDomesticElectricityConsumption getNonDomesticElectricityConsumptionFromLocalAuthorityName(String localAuthorityName){
    	NonDomesticElectricityConsumption consumption = find.where().eq("local_authority_name", localAuthorityName).findUnique();
    	return consumption;
    }

    public static NonDomesticElectricityConsumption getNonDomesticElectricityConsumptionFromLocalAuthorityCode(String localAuthorityCode){
    	NonDomesticElectricityConsumption consumption = find.where().eq("local_authority_code", localAuthorityCode).findUnique();
    	return consumption;
    }

    public static Finder<String,NonDomesticElectricityConsumption> find = new Finder<String,NonDomesticElectricityConsumption>(
        String.class, NonDomesticElectricityConsumption.class
    );


}