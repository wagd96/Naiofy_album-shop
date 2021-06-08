Feature: Purchased album list

  Scenario: See purchased albums of a regular user access token
    Given I have an access Token of regular user to see their purchased albums
    When I consume the web service to see their albums on the platform
    Then the web service responses with their album list
    And the schema should match with the specification defined in "album_list.json"

  Scenario: See purchased albums of a regular user access token for another userid
    Given I have an access Token of regular user to see purchased albums of other user
    When I consume the web service to see their albums on the platform
    Then the web service will respond with a forbidden error
    And  the schema should match with the specification defined in "errors.json"

  Scenario: See purchased albums with invalid access token
    When I consume the web service to see the purchased albums on the platform with an invalid access Token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario:See purchased albums without token
    When I consume the web service to see the purchased albums on the platform without access Token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: See purchased albums list without user id
    Given I have an access Token of regular user to see their purchased albums
    When I consume the web service to see the purchased albums on the platform without user id
    Then the web service will respond with a validation error of invalid user id
    And  the schema should match with the specification defined in "errors.json"