Feature: Login into the Naiofy album shop

  Scenario Outline: Succesfully login into the platform
    Given I have the credentials of an user type '<user>'
    When I consume the web service to login into the platform
    Then the web service to login responses successfully
    And  the schema should match with the specification defined in "login.json"

    Examples:
      | user    |
      | admin   |
      | regular |

  Scenario: Login with non existent user
    Given I don't have an existing user in the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a validation error of unable to authenticate credentials
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Login with incorrect password
    Given I have the credentials for a correct user with wrong password in the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a validation error of unable to authenticate credentials
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Login with email with an incorrect format
    Given I have an email with an incorrect format to login into the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a validation error of invalid email
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Login with different email than the accepted for the platform
    Given I have an user with different email than the accepted for the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a validation error of incorrect domain email
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Login without email
    Given I have an user without email to login into the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a missing data error of required fields email
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Login without password
    Given I have an user without password to login into the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a missing data error of required fields password
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Login without email and password
    Given I have an user without email and password to login into the platform
    When I consume the web service to login into the platform
    Then the web service will respond with a missing data error of required fields email and password
    And  the schema should match with the specification defined in "errors.json"