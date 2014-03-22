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

/**
 * Daily Solar Irradiance Data for Glasgow from SODA
 *
 * HelioClim-3 Database of Solar Irradiance v4 (derived from satellite data)	
 * Provider	MINES ParisTech - Armines (France)
 * More information at	http://www.soda-is.com
 * Site latitude (positive means North)	55.83
 * Site longitude (positive means East)	-4.25
 * Elevation (m)	24
 * Date beginning	2004-02-01
 * Date end	2005-12-31
 * Summarization (period of integration)	Day (d)
 * Date - Instant	The date or instant given for each value corresponds to the end of the summarization
 * Irradiance (W/m2)	Irradiance averaged over the period (-999 if no data)
 * Lower bound (W/m2)	Uncertainty in irradiance. Lower bound
 * Upper bound (W/m2)	Uncertainty in irradiance. Upper bound. There is a 68% chance that the actual value is comprised between lower and upper bounds
 * Nb slot (%)	In percent. Ratio of the number of valid slots and the number of slots during the daylength
 * Top of Atmosphere (W/m2)	Irradiance averaged over the period at the top of the atmosphere (extraterrestrial)
 * Clear-Sky (W/m2)	Typical irradiance averaged over the period if the sky were clear
 * Irradiation (Wh/m2)	Irradiation over the period (-999 if no data)
 * Irradiation (J/cm2)	Irradiation over the period (-999 if no data)
 * 
 */
@Entity
public class SolarIrradianceData extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
    public int year;
    public int month;
    public int day;
    public int irradiance;
    public int lowerBoundIrradiance;
    public int upperBoundIrradiance;
    public int nbSlots;
    public int topOfAtmosphere;
    public int clearSky;
    public int irradiation;
    public int irradiationJMS;

    public SolarIrradianceData(int year, int month, int day, int irradiance, int lowerBoundIrradiance,int upperBoundIrradiance, int nbSlots, int topOfAtmosphere, int clearSky,int irradiation, int irradiationJMS) {
      	this.id = id;
    	this.year = year;
    	this.month = month;
    	this.day = day;
    	this.irradiance = irradiance;
    	this.lowerBoundIrradiance = lowerBoundIrradiance;
    	this.upperBoundIrradiance = upperBoundIrradiance;
    	this.nbSlots = nbSlots;
    	this.topOfAtmosphere = topOfAtmosphere;
    	this.clearSky = clearSky;
    	this.irradiation = irradiation;
    	this.irradiationJMS = irradiationJMS;
    }

    public static Finder<String,SolarIrradianceData> find = new Finder<String,SolarIrradianceData>(
        String.class, SolarIrradianceData.class
    );

}