Feature: Photo album list

  Scenario: Consult a photo album list with regular user access token
    Given I have an access Token of a regular user to see a photo album list
    When I consume the web service to see the photo list of an album on the platform
    Then the web service responses with the photo album list of the album
    And the schema should match with the specification defined in "photos.json"

  Scenario: Consult a photo album list without access token
    When I consume the web service to see the photo list of an album on the platform without access token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult a photo album list with invalid access token
    When I consume the web service to see the photo list of an album on the platform with an invalid access Token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult a photo album list with invalid album id
    Given I have an access Token of a regular user to see a photo album list
    When I consume the web service to see the photo list of an album on the platform with an invalid album list
    Then the web service will respond with a validation error of invalid album id
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult a photo album list for non existent album id
    Given I have an access Token of a regular user to see a photo album list
    When I consume the web service to see the photo list for an non existent album on the platform
    Then the web service will respond with a validation error of invalid album id
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Consult a photo album list without album id
    Given I have an access Token of a regular user to see a photo album list
    When I consume the web service to see the photo list of an album on the platform without album id
    Then the web service will respond with a validation error of invalid album id
    And  the schema should match with the specification defined in "errors.json"

