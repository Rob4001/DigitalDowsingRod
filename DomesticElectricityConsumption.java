
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

public class DomesticElectricityConsumption extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
    public String localAuthorityName;
	public String localAuthorityCode;
	public String middleLayerSuperOutputAreaCode;
	public double ordinaryDomesticconsumption;
	public double economy7consumption;
	public double totaldomesticelectricityconsumption;
	public double numberofOrdinaryDomesticmeters;
	public double numberofEconomy7meters;
	public double totalnumberofdomesticelectricitymeters;
	public double averageOrdinaryDomesticconsumption;
	public double averageEconomy7consumption;
	public double averagedomesticelectricityconsumption;

    public DomesticElectricityConsumption(String localAuthorityName, String localAuthorityCode, String middleLayerSuperOutputAreaCode, double ordinaryDomesticconsumption, double economy7consumption, double totaldomesticelectricityconsumption, double numberofOrdinaryDomesticmeters, double numberofEconomy7meters, double totalnumberofdomesticelectricitymeters, double averageOrdinaryDomesticconsumption, double averageEconomy7consumption, double averagedomesticelectricityconsumption) {
    	this.localAuthorityName = localAuthorityName;
		this.localAuthorityCode = localAuthorityCode;
		this.middleLayerSuperOutputAreaCode = middleLayerSuperOutputAreaCode;
		this.ordinaryDomesticconsumption = ordinaryDomesticconsumption;
		this.economy7consumption = economy7consumption;
		this.totaldomesticelectricityconsumption = totaldomesticelectricityconsumption;
		this.numberofOrdinaryDomesticmeters = numberofOrdinaryDomesticmeters;
		this.numberofEconomy7meters = numberofEconomy7meters;
		this.totalnumberofdomesticelectricitymeters = totalnumberofdomesticelectricitymeters;
		this.averageOrdinaryDomesticconsumption = averageOrdinaryDomesticconsumption;
		this.averageEconomy7consumption = averageEconomy7consumption;
		this.averagedomesticelectricityconsumption = averagedomesticelectricityconsumption;
    }

    public static DomesticElectricityConsumption getDomesticElectricityConsumptionFromLocalAuthorityName(String localAuthorityName){
    	DomesticElectricityConsumption dec = find.where().eq("local_authority_name", localAuthorityName).findUnique();
    	return dec;
    }

    public static DomesticElectricityConsumption getDomesticElectricityConsumptionFromLocalAuthorityCode(String localAuthorityCode){
    	DomesticElectricityConsumption dec = find.where().eq("local_authority_code", localAuthorityCode).findUnique();
    	return dec;
    }

    public static DomesticElectricityConsumption getDomesticElectricityConsumptionFromMiddleLayerSuperOutputAreaCode(String code){
    	DomesticElectricityConsumption dec = find.where().eq("middle_layer_super_output_area_code", code).findUnique();
    	return dec;
    }

    public static Finder<String,DomesticElectricityConsumption> find = new Finder<String,DomesticElectricityConsumption>(
        String.class, DomesticElectricityConsumption.class
    );


}