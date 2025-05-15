@tag
  Feature: Purchase the order from Ecommance website

    Background:
      Given I am on Ecommance website

  @tag
  Scenario Outline: Positive Test of Submitting the order
    Given logged in with username <username> and password <password>
    When I add product <productName> to cart
    And I proceed to checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message should be displayed on ConfirmationPage
    Examples:
      | username               | password    | productName     |
      | abbeylincoln@gmail.com | Abbey100!   | ZARA COAT 3     |
      | shetty@gmail.com       | Iamking@000 | ADIDAS ORIGINAL |




