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

public class SmallLargeUserPostcodes extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
    // I can't be arsed; they are all strings
	public String postcode;
	public String pcSector;
	public String pcDistrict;
	public String dateOfIntroduction;
	public String dateOfDeletion;
	public String outputArea2011;
	public String outputArea2001;
	public String outputArea1991;
	public String datazone;
	public String intZone;
	public String elecWard07;
	public String elecWard99;
	public String multiMemberWardName;
	public String gridReferenceEasting;
	public String gridReferenceNorthing;
	public String councilArea;
	public String healthBoard;
	public String healthBoard2006;
	public String ukParlCon;
	public String europeanParlCon	;
	public String locality2010;
	public String locality2008;
	public String locality2006;
	public String locality2004;
	public String locality2003;
	public String locality2001;
	public String locality1991;
	public String settlement2010;
	public String settlement2008;
	public String settlement2006;
	public String settlement2004;
	public String settlement2003;
	public String settlement2001;
	public String islandCode;
	public String civilParish;
	public String registrationDistrict;
	public String simd2012;
	public String censusHouseholdCount2001;
	public String censusPopulationCount2001;
	public String usuallyResidentHouseholdCount1991;
	public String usuallyResidentCount1991;
	public String lgd1991;
	public String lgd1995;
	public String lgr1991;
	public String lgr1995;
	public String ew1995;
	public String reddw1991;
	public String ttwa;
	public String entRegion2008;
	public String lau2;
	public String scottishParlCon;
	public String scottishParlRegion;
	public String scottishParlCon0511;
	public String scottishParlRegion0511;
	public String urbRur82011_2012;
	public String urbRur62011_2012;
	public String nationalPark;
	public String commHealthParts;
	public String regOutcomeAreaLOC;
	public String regOutcomeAreaCPP;
	public String commHealthParts2011;
	public String commHealthParts2012;
	public String chpSubSector2011;
	public String strategicDevPlanArea;
	public String multStoreyAccomInd;
	public String studentAccomInd;
	public String shelteredHousingAccomInd;
	public String gridlinkCoordinateIndicator;
	public String splitIndicator;

    
    public SmallLargeUserPostcodes(String postcode, String pcSector, String pcDistrict, String dateOfIntroduction, String dateOfDeletion, String outputArea2011, String outputArea2001, String outputArea1991, String datazone, String intZone, String elecWard07, String elecWard99, String multiMemberWardName, String gridReferenceEasting, String gridReferenceNorthing, String councilArea, String healthBoard, String healthBoard2006, String ukParlCon, String europeanParlCon	, String locality2010, String locality2008, String locality2006, String locality2004, String locality2003, String locality2001, String locality1991, String settlement2010, String settlement2008, String settlement2006, String settlement2004, String settlement2003, String settlement2001, String islandCode, String civilParish, String registrationDistrict, String simd2012, String censusHouseholdCount2001, String censusPopulationCount2001, String usuallyResidentHouseholdCount1991, String usuallyResidentCount1991, String lgd1991, String lgd1995, String lgr1991, String lgr1995, String ew1995, String reddw1991, String ttwa, String entRegion2008, String lau2, String scottishParlCon, String scottishParlRegion, String scottishParlCon0511, String scottishParlRegion0511, String urbRur82011_2012, String urbRur62011_2012, String nationalPark, String commHealthParts, String regOutcomeAreaLOC, String regOutcomeAreaCPP, String commHealthParts2011, String commHealthParts2012, String chpSubSector2011, String strategicDevPlanArea, String multStoreyAccomInd, String studentAccomInd, String shelteredHousingAccomInd, String gridlinkCoordinateIndicator, String splitIndicator) {
      	this.postcode = postcode;
		this.pcSector = pcSector;
		this.pcDistrict = pcDistrict;
		this.dateOfIntroduction = dateOfIntroduction;
		this.dateOfDeletion = dateOfDeletion;
		this.outputArea2011 = outputArea2011;
		this.outputArea2001 = outputArea2001;
		this.outputArea1991 = outputArea1991;
		this.datazone = datazone;
		this.intZone = intZone;
		this.elecWard07 = elecWard07;
		this.elecWard99 = elecWard99;
		this.multiMemberWardName = multiMemberWardName;
		this.gridReferenceEasting = gridReferenceEasting;
		this.gridReferenceNorthing = gridReferenceNorthing;
		this.councilArea = councilArea;
		this.healthBoard = healthBoard;
		this.healthBoard2006 = healthBoard2006;
		this.ukParlCon = ukParlCon;
		this.europeanParlCon	 = europeanParlCon	;
		this.locality2010 = locality2010;
		this.locality2008 = locality2008;
		this.locality2006 = locality2006;
		this.locality2004 = locality2004;
		this.locality2003 = locality2003;
		this.locality2001 = locality2001;
		this.locality1991 = locality1991;
		this.settlement2010 = settlement2010;
		this.settlement2008 = settlement2008;
		this.settlement2006 = settlement2006;
		this.settlement2004 = settlement2004;
		this.settlement2003 = settlement2003;
		this.settlement2001 = settlement2001;
		this.islandCode = islandCode;
		this.civilParish = civilParish;
		this.registrationDistrict = registrationDistrict;
		this.simd2012 = simd2012;
		this.censusHouseholdCount2001 = censusHouseholdCount2001;
		this.censusPopulationCount2001 = censusPopulationCount2001;
		this.usuallyResidentHouseholdCount1991 = usuallyResidentHouseholdCount1991;
		this.usuallyResidentCount1991 = usuallyResidentCount1991;
		this.lgd1991 = lgd1991;
		this.lgd1995 = lgd1995;
		this.lgr1991 = lgr1991;
		this.lgr1995 = lgr1995;
		this.ew1995 = ew1995;
		this.reddw1991 = reddw1991;
		this.ttwa = ttwa;
		this.entRegion2008 = entRegion2008;
		this.lau2 = lau2;
		this.scottishParlCon = scottishParlCon;
		this.scottishParlRegion = scottishParlRegion;
		this.scottishParlCon0511 = scottishParlCon0511;
		this.scottishParlRegion0511 = scottishParlRegion0511;
		this.urbRur82011_2012 = urbRur82011_2012;
		this.urbRur62011_2012 = urbRur62011_2012;
		this.nationalPark = nationalPark;
		this.commHealthParts = commHealthParts;
		this.regOutcomeAreaLOC = regOutcomeAreaLOC;
		this.regOutcomeAreaCPP = regOutcomeAreaCPP;
		this.commHealthParts2011 = commHealthParts2011;
		this.commHealthParts2012 = commHealthParts2012;
		this.chpSubSector2011 = chpSubSector2011;
		this.strategicDevPlanArea = strategicDevPlanArea;
		this.multStoreyAccomInd = multStoreyAccomInd;
		this.studentAccomInd = studentAccomInd;
		this.shelteredHousingAccomInd = shelteredHousingAccomInd;
		this.gridlinkCoordinateIndicator = gridlinkCoordinateIndicator;
		this.splitIndicator = splitIndicator;
    }

    public static Finder<String,SmallLargeUserPostcodes> find = new Finder<String,SmallLargeUserPostcodes>(
        String.class, SmallLargeUserPostcodes.class
    );


}
