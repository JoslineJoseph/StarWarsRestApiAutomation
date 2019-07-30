package com.loyaltyone.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class StarWarsPropertyReader {

	private Properties starWarsProperty;

	public StarWarsPropertyReader() {

		try {
			FileReader reader=new FileReader("src/test/resources/starwarsrest.properties");
			starWarsProperty = new Properties();
			starWarsProperty.load(reader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  


	}

	public String getReportConfigPath(){
		String reportConfigPath = starWarsProperty.getProperty("reportConfigPath");
		return reportConfigPath;

	}

	public String getUrl() {
		return starWarsProperty.getProperty("url");
	}
	private String getFetch_all_characters() {
		return starWarsProperty.getProperty("fetch_all_characters");
	}
	private String getFetch_single_character() {
		return starWarsProperty.getProperty("fetch_single_character");
	}
	private String getFetch_all_planets() {
		return starWarsProperty.getProperty("fetch_all_planets");
	}
	private String getFetch_single_planet() {
		return starWarsProperty.getProperty("fetch_single_planet");
	}

	public String getAllCharacterRestAPI() {
		return getUrl()+getFetch_all_characters();
	}

	public String getSingleCharacterRestAPI() {
		return getUrl()+getFetch_single_character().replace("{{id}}", starWarsProperty.getProperty("character_id"));

	}

	public String getAllPlanetAPI() {
		return getUrl()+getFetch_all_planets();
	}


	public String getSinglePlanetAPI() {
		return getUrl()+ getFetch_single_planet().replace("{{id}}", starWarsProperty.getProperty("planet_id"));
	}

	public String getCharacterName() {
		return starWarsProperty.getProperty("character_name");
	}

}
