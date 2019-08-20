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
import com.loyaltyone.response.People;
import com.loyaltyone.response.Peoples;
import com.loyaltyone.utility.HttpUtility;
import com.loyaltyone.utility.StarWarsPropertyReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Defines the steps for each scenario related to people feature file.
 * Implements functional logic to support search of characters, retrieve characters.
 * */

public class StarWarsPeoplesStepDefinition {

	People singleCharacter = new People();
	ArrayList < Peoples > characterList = new ArrayList < Peoples > ();
	Peoples starWarCharactersRecord = new Peoples();
	Peoples searchResult = new Peoples();
	StarWarsPropertyReader reader = new StarWarsPropertyReader();
	int characterListCount = 0;
	int loopCounter = 0;
	Logger log = Logger.getLogger(StarWarsPeoplesStepDefinition.class.getName());
	
	@Given("The Peoples API is called")
	public void the_API_is_returning_success_response() {
		
		String peoplesApi = reader.getAllCharacterRestAPI();
		do {
			StringBuffer result = new StringBuffer();
			HttpResponse response = HttpUtility.sendGet(peoplesApi);
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
			starWarCharactersRecord = gson.fromJson(result.toString(), Peoples.class);
			if (starWarCharactersRecord.getNext()!= null) {
				peoplesApi = starWarCharactersRecord.getNext();

			}
			characterList.add(starWarCharactersRecord);
			loopCounter++;
			characterListCount += starWarCharactersRecord.getResults().size();
		}while(starWarCharactersRecord.getNext()!=null);
		log.info("Called peoples API to get list of all star wars characters");
		log.info("Total No of Pages Accessed to get all star wars characters -" +loopCounter);
	}

	@When("The peoples API returns success message")
	public void the_Response_has_List_of_Characters() {
		log.info("Validating The Peoples API returned Success response");
		Assert.assertEquals("Count from Result Response is not matching with List of characters Retrieved",
				loopCounter, characterList.size());
		for(Peoples characterRecord: characterList)
		{
			Assert.assertNotNull("Count Field in the Response should not be null", characterRecord.getCount());
			Assert.assertFalse("List of characters in each character List should not be null", characterRecord.getResults().isEmpty());
		}
		
		log.info("Validated count and results for each page. ");
	}

	@Then("Ensure All Characters are retrieved")
	public void ensure_All_Charecters_are_retrieved() {
		Assert.assertEquals("Count from Result Response is not matching with List of characters Retrieved", starWarCharactersRecord.getCount(), characterListCount,0);

		for (Peoples peoples : characterList) {
			Assert.assertEquals("Count from Result Response is not matching with List of characters Retrieved",peoples.getCount(), characterListCount,0);
			for(People character : peoples.getResults())
			{
				log.info("Validating details of " + character.getName());
				Assert.assertNotNull("Name Should be present for Character", character.getName());
				Assert.assertNotNull(character.getName() + " Should have a URL", character.getUrl());
				Assert.assertNotNull(character.getName() + " Should have height",character.getHeight());
				Assert.assertNotNull(character.getName() + " Should have mass", character.getMass());
				Assert.assertNotNull(character.getName() + " Should have hair_color",character.getHair_color());
				Assert.assertNotNull(character.getName() + " Should have skin_color", character.getSkin_color());
				Assert.assertNotNull(character.getName() + " Should have birth_year",character.getBirth_year());
				Assert.assertNotNull(character.getName() + " Should have eye_color", character.getEye_color());
				Assert.assertNotNull(character.getName() + " Should have gender",character.getGender());
				Assert.assertNotNull(character.getName() + " Should have a homeworld", character.getHomeworld());
				log.info("Verified Url, Height, Mass, hair color, skin color, birth year, eye color, gender, homeworld of " + character.getName());
			}
		}

	}

	@Given("The API for retrieving single character is called")
	public void getSingleCharacterAPI() {
		String peoplesApi = reader.getSingleCharacterRestAPI();

		StringBuffer result = new StringBuffer();
		HttpResponse response = HttpUtility.sendGet(peoplesApi);
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
		singleCharacter = gson.fromJson(result.toString(), People.class);
		log.info("API returned success message for "+ singleCharacter.getName() );

	}

	@When("The API returns details about a Single Character")
	public void validateSingleCharacterResponseStatus() {
		Assert.assertNotNull("Character Name should not be empty",singleCharacter.getName());
		log.info("Sucessfully Validated single star war character returned success response");

	}

	@Then("Ensure record of Single Character is retrieved")
	public void validateSing() {

		Assert.assertNotNull(singleCharacter.getName() + " should have a mapping URL", singleCharacter.getUrl());
		Assert.assertNotNull("Name Should be present for Character", singleCharacter.getName());
		Assert.assertNotNull(singleCharacter.getName() + " Should have a URL", singleCharacter.getUrl());
		Assert.assertNotNull(singleCharacter.getName() + " Should have height",singleCharacter.getHeight());
		Assert.assertNotNull(singleCharacter.getName() + " Should have mass", singleCharacter.getMass());
		Assert.assertNotNull(singleCharacter.getName() + " Should have hair_color",singleCharacter.getHair_color());
		Assert.assertNotNull(singleCharacter.getName() + " Should have skin_color", singleCharacter.getSkin_color());
		Assert.assertNotNull(singleCharacter.getName() + " Should have birth_year",singleCharacter.getBirth_year());
		Assert.assertNotNull(singleCharacter.getName() + " Should have eye_color", singleCharacter.getEye_color());
		Assert.assertNotNull(singleCharacter.getName() + " Should have gender",singleCharacter.getGender());
		Assert.assertNotNull(singleCharacter.getName() + " Should have a homeworld", singleCharacter.getHomeworld());

		log.info("Validated Details of " + singleCharacter.getName());
	}
	
	
	@Given("^The user can search for \"([^\"]*)\"$")
	public void the_API_can_search_for_character(String searchString)  {
		String character_name;
		character_name=System.getProperty("character_name");
		
		searchString=(character_name==null)?searchString:character_name;
		log.info("Searching for "+ searchString);
		
		String searchApiEndpoint = reader.getCharacterSearchAPI().replace("{{search_input}}",
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
		searchResult = gson.fromJson(result.toString(), Peoples.class);
    
	}

	@When("^The API returns List of People$")
	public void the_API_returns_List_of_People() {

		Assert.assertTrue("Search List Does not have Any People", searchResult.getCount()>=1);
		log.info("Search API returned a list of people. Count: "+ searchResult.getCount());
	}

	@Then("^Validate the List returns People Having \"([^\"]*)\" in their name$")
	public void validate_the_List_returns_People_Having_in_their_name(String searchString){
		String character_name;
		character_name=System.getProperty("character_name");
		
		searchString=(character_name==null)?searchString:character_name;
		for (People people: searchResult.getResults()) {
			log.info(people.getName()+ " is returned in Search List");
			Assert.assertTrue(searchString +" is not Found in Name "+ people.getName(), 
					people.getName().toUpperCase().contains(searchString.toUpperCase()));
		}
		log.info("Sucessfully validated User is able to search character name");
}

}