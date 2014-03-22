package models;

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
}