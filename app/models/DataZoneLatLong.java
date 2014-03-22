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

public class DataZoneLatLong extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
    public String dataZone;
    public String localAuthoritdName;
    public String intermediateGeographyName;
    public long northing;
    public long easting;
    public double longitude;
    public double latitude;

    
    public DataZoneLatLong(String dataZone,String localAuthoritdName,String intermediateGeographyName,long northing,long easting,double longitude,double latitude) {
      this.dataZone = dataZone;
      this.localAuthoritdName = localAuthoritdName;
      this.intermediateGeographyName = intermediateGeographyName;
      this.northing = northing;
      this.easting = easting;
      this.longitude = longitude;
      this.latitude = latitude;
    }

    public static Finder<String,DataZoneLatLong> find = new Finder<String,DataZoneLatLong>(
        String.class, DataZoneLatLong.class
    );


}