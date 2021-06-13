#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validation Place APIs

# Scenario outline when using examples, if not just mention scenario

@AddPlaceTag
@Regression
  Scenario Outline: Validate adding a place using "AddPlaceAPI"
    Given App API Payload with "<name>" , "<language>" and "<address>"
    When User calls "AddPlaceAPI" with "POST" HTTP request
    Then The API call is success with code 200
    And the "status" reponse is "OK"
    And the "scope" reponse is "APP"
    And verify place_Id created maps to the "<name>" using "GetPlaceAPI"
    
  Examples:
	|name | language | address|
	|library | English | World cross center|
	|Park | English | World cross center|

@DeletePlaceTag
Scenario Outline: Verify if Delete Place functionality is working
    Given App API Delete Place with place_id
    When User calls "DeletePlaceAPI" with "POST" HTTP request
    Then The API call is success with code 200
    And the "status" reponse is "OK" 
    



    

	

    

