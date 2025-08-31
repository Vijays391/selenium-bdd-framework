Feature: Login functionality

  Scenario: Successful login
    Given user is on login page
    When user enters valid username and password
    And clicks on login button
    Then user should be redirected to home page
