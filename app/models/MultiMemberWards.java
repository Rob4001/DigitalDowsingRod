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

public class MultiMemberWards extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int id;
	public String datazone;
	public String intermediateGeographyCode;
	public String intermediateGeographyName;
	public String multiMemberWardCode;
	public String multiMemberWardName;
	public String scottishParliamentaryConstituencyCode;
	public String scottishParliamentaryConstituencyName;
	public String localAuthorityCode;
	public String localAuthorityName;
    
    public MultiMemberWards(String datazone,String intermediateGeographyCode,String intermediateGeographyName,String multiMemberWardCode,String multiMemberWardName,String scottishParliamentaryConstituencyCode,String scottishParliamentaryConstituencyName,String localAuthorityCode,String localAuthorityName) {
      	this.datazone = datazone;
		this.intermediateGeographyCode = intermediateGeographyCode;
		this.intermediateGeographyName = intermediateGeographyName;
		this.multiMemberWardCode = multiMemberWardCode;
		this.multiMemberWardName = multiMemberWardName;
		this.scottishParliamentaryConstituencyCode = scottishParliamentaryConstituencyCode;
		this.scottishParliamentaryConstituencyName = scottishParliamentaryConstituencyName;
		this.localAuthorityCode = localAuthorityCode;
		this.localAuthorityName = localAuthorityName;
    }

    public static Finder<String,MultiMemberWards> find = new Finder<String,MultiMemberWards>(
        String.class, MultiMemberWards.class
    );


}



