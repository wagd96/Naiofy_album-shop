Feature: User list

  Scenario: Consult the user list with regular user access token
    Given I have an access Token of a regular user to search users
    When I consume the web service to see the user list on the platform
    Then the web service responses with the list of users
    And the schema should match with the specification defined in "user_list.json"

  Scenario: Consult the user list with admin user access token
    Given I have an access Token of an admin user to search users
    When I consume the web service to see the user list on the platform
    Then the web service responses with the list of users
    And the schema should match with the specification defined in "user_list.json"
    And the total number of users on the platform will be greater than the result for a regular user

  Scenario: Consult the user list without access token
    When I consume the web service to see the user list on the platform without access token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult the user list with invalid access token
    When I consume the web service to see the user list on the platform with an invalid access Token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult the user list for a specific regular user
    Given I have an access Token of a regular user to search users
    When I consume the web service to search a specific user on the platform
    Then the web service responses with the information of the regular user

  Scenario: Consult the user list for a non existent user
    Given I have an access Token of a regular user to search users
    When I consume the web service to search an non-existent user on the platform
    Then the web service will respond with an error of not found user
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult the user list for a non existent page
    Given I have an access Token of a regular user to search users
    When I consume the web service to see the user list in an non-existent page on the platform
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"