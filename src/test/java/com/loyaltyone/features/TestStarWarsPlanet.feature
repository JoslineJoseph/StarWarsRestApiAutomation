#Author: joslinepallithanam@gmail.com
#Purpose: To test Star Wars Rest API for Retrieving all star wars characters
Feature: This feature tests to check if we can retrieve all planets in star wars movie

  Scenario: Scenario to retrieve all the planets present in Star Wars Movie
    Given The Planets API is called
    When The Planets API returns success message
    Then Ensure All Planet details are retrieved

  Scenario: Scenario to check if we can retrive details of a single Planet of star wars movie.
    Given The API for retrieving single Planet is called
    When The API returns details about a Single Planet
    Then Verify the values of Single Planet API

  Scenario: Scenario to check if we can search for a planet using name
    Given The user is able to search for planet "To"
    When The API returns List of Planets
    Then Validate the List returns Planets Having "To" in their name
