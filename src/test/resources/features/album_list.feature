Feature: Album list

  Scenario: Consult a album list with regular user access token
    Given I have an access Token of a regular user to search albums
    When I consume the web service to see the album list on the platform
    Then the web service responses with the list of albums
    And the schema should match with the specification defined in "album_list.json"

  Scenario: Consult a album list without access token
    When I consume the web service to see the album list on the platform without access token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult a album list with invalid access token
    When I consume the web service to see the album list on the platform with an invalid access Token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"