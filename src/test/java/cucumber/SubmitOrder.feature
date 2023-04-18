@tag

Feature: Purchase the order from ecommerce website

  Background:
    Given I landed on Ecommerce page

  @regression
  Scenario Outline: Positive Test of Submitting order
    Given User is logged in with username <name> and password <password>
    When User adds the <product> to cart
    And User checkout <product> and submit to order
    Then User verify "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples:
      | name                        | password    | product     |
      | seleniumframework@gmail.com | Selenium@10 | ZARA COAT 3 |