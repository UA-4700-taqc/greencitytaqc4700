Feature: Create News - Tag Selection
  As a logged-in user
  I want to select tags when creating news
  So that my news is properly categorized

  Background:
    Given the user is logged into the system
    And the user navigates to "https://www.greencity.cx.ua/#/greenCity/news"
    And the user clicks "Create News"

  Scenario: Successfully publish news with one tag
    When the user selects the tag "News"
    And the user fills in the "Title" field with "Test"
    And the user fills in the "Main Text" field with "Test content with 20 chars"
    And the user clicks "Publish"
    Then the news should be published successfully
    And the published news should contain the tag "News"

  Scenario: Successfully publish news with three tags
    When the user selects the tag "News"
    And the user selects the tag "Events"
    And the user selects the tag "Education"
    And the user fills in the "Title" field with "Test"
    And the user fills in the "Main Text" field with "Test content with 20 chars"
    And the user clicks "Publish"
    Then the news should be published successfully
    And the published news should contain the tag "News"
    And the published news should contain the tag "Events"
    And the published news should contain the tag "Education"

  Scenario: User cannot select more than three tags
    When the user selects the tag "News"
    And the user selects the tag "Events"
    And the user selects the tag "Education"
    Then the user should not be able to select the tag "Initiatives"
    And exactly 3 tags should be selected

  Scenario Outline: Validate tag selection limits
    When the user selects <number> tags
    Then the user should <result>

    Examples:
      | number | result                                    |
      | 1      | be able to publish news                   |
      | 2      | be able to publish news                   |
      | 3      | be able to publish news                   |
      | 4      | not be able to select the fourth tag      |

  Rule: Tag selection must be between 1 and 3 tags
    Example: Minimum tag requirement
      Given the user has not selected any tags
      When the user attempts to publish news
      Then the user should see a validation error
      And the news should not be published

    Example: Maximum tag limit
      Given the user has selected 3 tags
      When the user attempts to select a fourth tag
      Then the fourth tag should not be selectable
      And only 3 tags should remain selected