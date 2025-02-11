@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative Test for Login with Invalid Credential
    Given I landed on login page
    When I Logged in with username <Uname> and password <Pwd>
    Then "Incorrect email or password." message should be displayed

    Examples: 
      | Uname  								  | Pwd 		 |
      | pandit22898qw@gmail.com | Pmod@123 |