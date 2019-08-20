package com.loyaltyone.steps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.google.gson.Gson;
import com.loyaltyone.response.Planet;
import com.loyaltyone.response.Planets;
import com.loyaltyone.utility.HttpUtility;
import com.loyaltyone.utility.StarWarsPropertyReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Defines the steps for each scenario related to planet feature file.
 * Implements functional logic to support search of planet, retrieve planets.
 * */
public class StarWarsPlanetStepDefinition {

	Planet aPlanet = new Planet();
	ArrayList < Planets > planetsList = new ArrayList < Planets > ();
	Planets aPlanetRecord = new Planets();
	Planets searchPlanets = new Planets();
	StarWarsPropertyReader reader = new StarWarsPropertyReader();
	int planetListCounter = 0;
	int loopCounter = 0;
	Logger log = Logger.getLogger(StarWarsPlanetStepDefinition.class.getName());

	@Given("The Planets API is called")
	public void callPlanetsApi() {

		String planetsApi = reader.getAllPlanetAPI();
		do {
			StringBuffer result = new StringBuffer();
			HttpResponse response = HttpUtility.sendGet(planetsApi);
			Assert.assertEquals(200, response.getStatusLine().getStatusCode());
			try {
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gson gson = new Gson();
			aPlanetRecord = gson.fromJson(result.toString(), Planets.class);
			if (aPlanetRecord.getNext()!= null) {
				planetsApi = aPlanetRecord.getNext();

			}
			planetsList.add(aPlanetRecord);
			loopCounter++;
			planetListCounter += aPlanetRecord.getResults().size();
		}while(aPlanetRecord.getNext()!=null);
		log.info("Successfully retrieved details of all star wars planet");
	}

	@When("The Planets API returns success message")
	public void verifyPlanetsApiStatus() {
		Assert.assertEquals("Count from Result Response is not matching with List of Planets Retrieved",
				loopCounter, planetsList.size());

	}

	@Then("Ensure All Planet details are retrieved")
	public void verifyAllPlanetsDetails() {
		Assert.assertEquals("Count from Result Response is not matching with List of Planets Retrieved", aPlanetRecord.getCount(), planetListCounter,0);

		for (Planets planets : planetsList) {
			Assert.assertEquals("Count field of Planet Record Not matching with actual no of planets",planets.getCount(), planetListCounter,0);
			for(Planet planet : planets.getResults())
			{
				log.info("Validating details of " + planet.getName());

				Assert.assertNotNull("Name Should be present for Planet", planet.getName());
				Assert.assertNotNull(planet.getName() + " Should have a URL", planet.getUrl());
				Assert.assertNotNull(planet.getName()+ " Should have a climate", planet.getClimate());
				Assert.assertNotNull(planet.getName()+ " Should have a rotation_period", planet.getRotation_period());
				Assert.assertNotNull(planet.getName()+ " Should have a orbital_period", planet.getOrbital_period());
				Assert.assertNotNull(planet.getName()+ " Should have a diameter", planet.getDiameter());
				Assert.assertNotNull(planet.getName()+ " Should have  gravity", planet.getGravity());
				Assert.assertNotNull(planet.getName()+ " Should specify Terrain", planet.getTerrain());
				Assert.assertNotNull(planet.getName()+ " Should specify surface_water", planet.getSurface_water());
				log.info("Successfully Validated details of "+planet.getName());

			}
		}

	}


	@Given("The API for retrieving single Planet is called")
	public void callPlanetApiforSinglePlanet() {
		String singlePlanetApi = reader.getSinglePlanetAPI();

		StringBuffer result = new StringBuffer();
		HttpResponse response = HttpUtility.sendGet(singlePlanetApi);
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
		try {
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		aPlanet = gson.fromJson(result.toString(), Planet.class);
		log.info("API returned success message for "+ aPlanet.getName() );

	}

	@When("The API returns details about a Single Planet")
	public void verifySinglePlanetApiStatus() {
		Assert.assertNotNull("Planet Name should not be empty",aPlanet.getName());
		log.info("Sucessfully Validated single star war planet api returned success response");

	}

	@Then("Verify the values of Single Planet API")
	public void verifySinglePlanetApiResponse() {
		Assert.assertNotNull(aPlanet.getName() + " should have a mapping URL", aPlanet.getUrl());
		Assert.assertNotNull("Name Should be present for Planet", aPlanet.getName());
		Assert.assertNotNull(aPlanet.getName() + " Should have a URL", aPlanet.getUrl());
		Assert.assertNotNull(aPlanet.getName()+ " Should have a climate", aPlanet.getClimate());
		Assert.assertNotNull(aPlanet.getName()+ " Should have a rotation_period", aPlanet.getRotation_period());
		Assert.assertNotNull(aPlanet.getName()+ " Should have a orbital_period", aPlanet.getOrbital_period());
		Assert.assertNotNull(aPlanet.getName()+ " Should have a diameter", aPlanet.getDiameter());
		Assert.assertNotNull(aPlanet.getName()+ " Should have  gravity", aPlanet.getGravity());
		Assert.assertNotNull(aPlanet.getName()+ " Should specify Terrain", aPlanet.getTerrain());
		Assert.assertNotNull(aPlanet.getName()+ " Should specify surface_water", aPlanet.getSurface_water());
		log.info("Validated Details of " + aPlanet.getName());

	}


	@Given("^The user is able to search for planet \"([^\"]*)\"$")
	public void the_API_is_able_to_search_for_planet(String searchString) {
		String planet_name;
		planet_name=System.getProperty("planet_name");

		searchString=(planet_name==null)?searchString:planet_name;
		log.info("Searching for "+ searchString);

		String searchApiEndpoint = reader.getPlanetSearchAPI().replace("{{search_input}}",
				URLEncoder.encode(searchString));
		log.info(searchApiEndpoint);
		StringBuffer result = new StringBuffer();
		HttpResponse response = HttpUtility.sendGet(searchApiEndpoint);
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
		try {
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		searchPlanets = gson.fromJson(result.toString(), Planets.class);
	}

	@When("^The API returns List of Planets$")
	public void the_API_returns_List_of_Planets()  {
		Assert.assertTrue("Search List Does not have Any Planets", searchPlanets.getCount()>=1);
		log.info("Search API returned a list of Planets. Count: "+ searchPlanets.getCount());
	}

	@Then("^Validate the List returns Planets Having \"([^\"]*)\" in their name$")
	public void validate_the_List_returns_Planets_Having_in_their_name(String searchString)  {
		String planet_name=System.getProperty("planet_name");

		searchString=(planet_name==null)?searchString:planet_name;
		for (Planet planet: searchPlanets.getResults()) {
			log.info(planet.getName()+ " is returned in Search List");
			Assert.assertTrue(searchString +" is not Found in Name " + planet.getName(), 
					planet.getName().toUpperCase().contains(searchString.toUpperCase()));
		}
		log.info("Sucessfully validated User is able to search with planet name");

	}



}
