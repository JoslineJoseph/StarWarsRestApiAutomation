#Author: joslinepallithanam@gmail.com
#Purpose: To test Star Wars Rest API for Retrieving all star wars characters
Feature: This feature tests if we can retrieve all star wars characters or retrieve detail about a single star war character

  Scenario: Scenario to retrieve all the characters present in Star Wars Movie
    Given The Peoples API is called
    When The peoples API returns success message
    Then Ensure All Characters are retrieved

  Scenario: Scenario to check if we can retrive details of a single character of star wars movie. This tests for character 1 Luke Skywalker
    Given The API for retrieving single character is called
    When The API returns details about a Single Character
    Then Ensure record of Single Character is retrieved

  Scenario: Scenario to check if user can search for a character
    Given The user can search for "Lu"
    When The API returns List of People
    Then Validate the List returns People Having "Lu" in their name
