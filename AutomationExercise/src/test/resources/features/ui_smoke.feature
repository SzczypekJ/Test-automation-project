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

  Scenario: Login User with incorrect email and password
    Given user is on the home page
    When user logs in with incorrect email and password
    Then login error "Your email or password is incorrect!" is visible

  @requiresApiUser
  Scenario: Logout User
    Given user is on the home page
    When user login to the existing account
    Then user is logged in
    When user logout from the account
    Then user is redirected to the Login Page

  @requiresApiUser
  Scenario: Register User with existing email
    Given user is on the home page
    When user registers to existing account
    Then register error "Email Address already exist!" is visible

    Scenario: Contact Us Form
      Given user is on the home page
      When user opens the Contact Us page
      And user submits the contact form with valid data and an attachment
      Then success message "Success! Your details have been submitted successfully." is visible
      And user is back on the home page
