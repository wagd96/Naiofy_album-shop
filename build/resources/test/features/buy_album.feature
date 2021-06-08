Feature: Buy an album

  Scenario: Buy an album with new regular user access token
    Given I have an access Token of a new regular user to buy an album
    When I consume the web service to buy an album on the platform
    Then the web service responses with the info of the album
    And the schema should match with the specification defined in "buy_album.json"
    And the user will be see the album in their purchases

  Scenario: Buy a repeat album with new regular user access token
    Given I have an access Token of a new regular user to buy an album
    When I consume the web service to buy an album on the platform
    And I bought the same album again
    Then the web service will respond with an error of invalid album id
    And the schema should match with the specification defined in "errors.json"

  Scenario: Buy an album without access token
    When I consume the web service to buy an album on the platform without access token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Buy an album with invalid access token
    When I consume the web service to buy an album on the platform with an invalid access Token
    Then the web service will respond with a validation error of invalid token
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Buy an album list with invalid album id
    Given I have an access Token of a regular user to buy an album
    When I consume the web service to buy an album on the platform with an invalid album id
    Then the web service will respond with a not found error
    And  the schema should match with the specification defined in "errors.json"

  Scenario: Buy an non existent album
    Given I have an access Token of a regular user to buy an album
    When I consume the web service to buy a non existent album on the platform
    Then the web service will respond with a not found error
    And  the schema should match with the specification defined in "errors.json"