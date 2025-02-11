@tag
Feature: Purchase order from E-commerce Website
  I want to use this template for my feature file

Background:
Given I landed on login page

  @Regression
  Scenario Outline: Positive Test Case for Submiting Order
    Given I Logged in with username <Uname> and password <Pwd>
    When I Added Product <ProductName> to Cart
    And Checkout <ProductName> and Submit the order
    Then "THANKYOU FOR THE ORDER." message shoule be displayed

    Examples: 
      | Uname  								| Pwd 			 | ProductName 	 |
      | pandit22898@gmail.com | Pramod@123 | IPHONE 13 PRO |
  
