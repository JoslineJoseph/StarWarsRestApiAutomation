package com.loyaltyone.steps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.junit.Assert;

import com.google.gson.Gson;
import com.loyaltyone.response.People;
import com.loyaltyone.response.Peoples;
import com.loyaltyone.utility.HttpUtility;
import com.loyaltyone.utility.StarWarsPropertyReader;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class StarWarsPeoplesStepDefinition {

	People singleCharacter = new People();
	ArrayList < Peoples > characterList = new ArrayList < Peoples > ();
	Peoples starWarCharactersRecord = new Peoples();
	StarWarsPropertyReader reader = new StarWarsPropertyReader();
	int characterListCount = 0;
	int loopCounter = 0;

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

	}

	@When("The peoples API returns success message")
	public void the_Response_has_List_of_Characters() {

		Assert.assertEquals("Count from Result Response is not matching with List of characters Retrieved",
				loopCounter, characterList.size());
		for(Peoples characterRecord: characterList)
		{
			Assert.assertNotNull("Count Field in the Response should not be null", characterRecord.getCount());
			Assert.assertFalse("List of characters in each character List should not be null", characterRecord.getResults().isEmpty());
		}

	}

	@Then("Ensure All Characters are retrieved")
	public void ensure_All_Charecters_are_retrieved() {
		Assert.assertEquals("Count from Result Response is not matching with List of characters Retrieved", starWarCharactersRecord.getCount(), characterListCount,0);

		for (Peoples peoples : characterList) {
			Assert.assertEquals("Count from Result Response is not matching with List of characters Retrieved",peoples.getCount(), characterListCount,0);
			for(People character : peoples.getResults())
			{
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
			}
		}

	}

	@And("User is able to search for a particular character")
	public void searchForUserInCharacterList() {
		boolean isCharacterSearchedFound = false;
		for(Peoples peoples : characterList ) {
			for(People  people: peoples.getResults()) {
				if (people.getName().equalsIgnoreCase(reader.getCharacterName())) {
					isCharacterSearchedFound = true;
					break;
				}
			}
			if (isCharacterSearchedFound) {
				break;
			}
		}
		Assert.assertTrue(reader.getCharacterName()+ " is not found in Character List", isCharacterSearchedFound);
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

	}

	@When("The API returns details about a Single Character")
	public void validateSingleCharacterResponseStatus() {
		Assert.assertNotNull("Character Name should not be empty",singleCharacter.getName());
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

	}

}

