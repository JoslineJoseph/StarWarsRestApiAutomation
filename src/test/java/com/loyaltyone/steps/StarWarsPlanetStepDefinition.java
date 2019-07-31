package com.loyaltyone.steps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.junit.Assert;

import com.google.gson.Gson;
import com.loyaltyone.response.Planet;
import com.loyaltyone.response.Planets;
import com.loyaltyone.utility.HttpUtility;
import com.loyaltyone.utility.LoggerUtility;
import com.loyaltyone.utility.StarWarsPropertyReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StarWarsPlanetStepDefinition {
	
	Planet aPlanet = new Planet();
	ArrayList < Planets > planetsList = new ArrayList < Planets > ();
	Planets aPlanetRecord = new Planets();
	StarWarsPropertyReader reader = new StarWarsPropertyReader();
	int planetListCounter = 0;
	int loopCounter = 0;
	LoggerUtility log = new LoggerUtility("StarWarsPlanetStepDefinition");


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
	
	@Then("^User can search for \"([^\"]*)\" planet$")
	public void searchForAPlanet(String planetName) {
		boolean isPlanetSearchedFound = false;
		for(Planets planets : planetsList ) {
			for(Planet  planet: planets.getResults()) {
				if (planet.getName().equalsIgnoreCase(planetName)) {
					isPlanetSearchedFound = true;
					break;
				}
			}
			if (isPlanetSearchedFound) {
				break;
			}
		}
		Assert.assertTrue(planetName+ " is not found in Planet List", isPlanetSearchedFound);
		log.info("Verified User is able to search for "+ planetName);

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

}