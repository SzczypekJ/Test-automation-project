@ui
Feature: UI Smoke Test
  Scenario: Register User
    Given user is on the home page
    When user registers a new account
    Then user is logged in
    When user deletes the account
    Then account is deleted

  @requiresApiUser
  Scenario: Login User with correct email and password
    Given user is on the home page
    When user login to the existing account
    Then user is logged in
    When user deletes the account
    Then account is deleted