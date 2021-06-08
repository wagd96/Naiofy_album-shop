Feature: Register in Naiofy album shop

  Scenario: Create a regular user
    Given I have the data of an user to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service to register a new user responses successfully
    And the user will be created on the platform

  Scenario: Create a regular user with incorrect email format
    Given I have the data of an user with an incorrectly formatted email to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error of invalid email

  Scenario: Create a regular user with invalid email
    Given I have the data of an user with an invalid email to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error of incorrect domain email

  Scenario: Create a regular user with numbers in the firstName
    Given I have the data of an user with numbers in the firstName to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error

  Scenario: Create a regular user with numbers in the lastName
    Given I have the data of an user with numbers in the lastName to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error

  Scenario: Create a regular user with numbers in the firstName and lastName
    Given I have the data of an user with numbers in the firstName and lastName to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error

  Scenario: Create a regular user with incorrect minimum password length
    Given I have the data of an user with incorrect minimum password length to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error

  Scenario: Create a regular user with only numbers in the password
    Given I have the data of an user with only numbers in the password to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error

  Scenario: Create a regular user without numbers in the password
    Given I have the data of an user without numbers in the password to register on the platform
    When I consume the web service to register a regular user on the platform
    Then the web service will respond with a validation error