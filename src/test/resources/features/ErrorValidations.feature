@tag
  Feature: Error Validations

    @ErrorValidation
    Scenario Outline: Negative Test of Submitting the order
      Given I am on Ecommance website
      When logged in with username <username> and password <password>
      Then "Incorrect email or password." message is displayed
      Examples:
        | username               | password |
        | abbeylincon@gmail.comm | Abbe0!   |

