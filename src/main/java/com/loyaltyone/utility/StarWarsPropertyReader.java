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
		return starWarsProperty.getProperty("reportConfigPath");
	}

	public String getUrl() {
		return starWarsProperty.getProperty("url");
	}
	

	public String getAllCharacterRestAPI() {
		return getUrl()+starWarsProperty.getProperty("fetch_all_characters");
	}

	public String getSingleCharacterRestAPI() {
		return getUrl()+starWarsProperty.getProperty("fetch_single_character").replace("{{id}}", starWarsProperty.getProperty("character_id"));

	}

	public String getAllPlanetAPI() {
		return getUrl()+starWarsProperty.getProperty("fetch_all_planets");
	}


	public String getSinglePlanetAPI() {
		return getUrl()+ starWarsProperty.getProperty("fetch_single_planet").replace("{{id}}", starWarsProperty.getProperty("planet_id"));
	}

	public String getCharacterSearchAPI() {
		return getUrl()+ starWarsProperty.getProperty("char_search_api");
	}
	
	public String getPlanetSearchAPI() {
		return getUrl()+ starWarsProperty.getProperty("planet_search_api");
	}
	
	
	

}
