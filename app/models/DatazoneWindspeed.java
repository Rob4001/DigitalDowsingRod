

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

public class DatazoneWindspeed extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
    public String dataZone;
	public String localAuthorityName;
	public String intermediateGeographyName;
	public int northing;
	public int easting;
	public double longitude;
	public double latitude;
	public double windspeed10m;
	public double windspeed25m;
	public double windspeed45m;

    
    public DatazoneWindspeed(String dataZone,String localAuthorityName,String intermediateGeographyName,int northing,int easting,double longitude,double latitude,double windspeed10m,double windspeed25m,double windspeed45m) {
      	this.dataZone = dataZone;
		this.localAuthorityName = localAuthorityName;
		this.intermediateGeographyName = intermediateGeographyName;
		this.northing = northing;
		this.easting = easting;
		this.longitude = longitude;
		this.latitude = latitude;
		this.windspeed10m = windspeed10m;
		this.windspeed25m = windspeed25m;
		this.windspeed45m = windspeed45m;
    }

    public static DatazoneWindspeed getDatazoneWindspeedFromDatazone(String dataZone){
    	DatazoneWindspeed tasks = find.where()
    	.eq("data_zone", dataZone).findUnique();
    	return tasks;

    }

    public static double getWindGenerationValue10m(String datazone, double rotorDiameter){
    	List<DatazoneWindspeed> speeds = find.where().eq('data_zone', datazone).findList();
    	int count = 0;
    	double average = 0;
    	for (DatazoneWindspeed sp : speeds) {
    		average+=sp.windspeed10m;
    		count++;
    	}
    	average = average/count;
    	return 0.01238 * rotorDiameter * average;
    }

    public static double getWindGenerationValue25m(String datazone, double rotorDiameter){
    	List<DatazoneWindspeed> speeds = find.where().eq('data_zone', datazone).findList();
    	int count = 0;
    	double average = 0;
    	for (DatazoneWindspeed sp : speeds) {
    		average+=sp.windspeed25m;
    		count++;
    	}
    	average = average/count;
    	return 0.01238 * rotorDiameter * average;
    }

    public static double getWindGenerationValue45m(String datazone, double rotorDiameter){
    	List<DatazoneWindspeed> speeds = find.where().eq('data_zone', datazone).findList();
    	int count = 0;
    	double average = 0;
    	for (DatazoneWindspeed sp : speeds) {
    		average+=sp.windspeed45m;
    		count++;
    	}
    	average = average/count;
    	return 0.01238 * rotorDiameter * average;
    }

    public static Finder<String,DatazoneWindspeed> find = new Finder<String,DatazoneWindspeed>(
        String.class, DatazoneWindspeed.class
    );


}