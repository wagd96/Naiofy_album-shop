Feature: invalidate sessions

  Scenario Outline: Invalidate sessions successfully
    Given I have the access token of an user type '<user>'
    When I consume the web service to invalidate sessions
    Then the web service to invalidate sessions responses successfully
    Examples:
      | user    |
      | admin   |
      | regular |