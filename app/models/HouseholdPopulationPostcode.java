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
public class HouseholdPopulationPostcode extends Model {

    @Id
    public int id;
    public String postcode;
    public int population;
    public int household;
    
    public HouseholdPopulationPostcode(String postcode, int population, int household) {
      this.postcode = postcode;
      this.population = population;
      this.household = household;
    }

    public static Finder<String,HouseholdPopulationPostcode> find = new Finder<String,HouseholdPopulationPostcode>(
        String.class, HouseholdPopulationPostcode.class
    );

    public static String importFromCSV(String path){
      try {
        CSVReader reader = new CSVReader(new FileReader(path));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            new HouseholdPopulationPostcode(nextLine[0], Integer.parseInt(nextLine[1]), Integer.parseInt(nextLine[2])).save();
        }
      } catch (Exception e){

      }
      return "";
    }
}