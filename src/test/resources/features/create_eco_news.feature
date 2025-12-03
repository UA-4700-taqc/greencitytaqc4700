Feature: [Create News]Basic Preview Functionality
#Verify that the user can preview news content after entering valid data and that the preview matches the input.
  Background:
    Given User logged into the system
    And User opened clicks "Eco news" in header
    Then Eco News page is loaded
    When User clicks "Create news"
    Then Create News page is loaded

  Scenario:
    When User types at least one symbol in "Title" field
    And User types at least 20 symbols in "Content" field
    And User types Source address starting with "https://"
    Then remember "Author`s name
    When User clicks "Preview" button
    Then Create News Preview page is opened
    And the News title equals to appropriate input symbols
    And the Main text equals to appropriate input symbols
    And the Date is current date
    And the Author`s name equals to User`s name
    And "Back to editing" button is displayed
    And "Back to editing" button has link


Feature: [Create News]Display all Fields
#Verify that the "Create News" form displays all the necessary fields in the correct order.
  Background:
    Given User logged into the system
    And User opened clicks "Eco news" in header
    Then Eco News page is loaded

  Scenario:
    When User clicks "Create news"
    Then Create News page is loaded
    And Title field is displayed
    And five Tag buttons are displayed:
      | News        |
      | Events      |
      | Education   |
      | Initiatives |
      | Ads         |
    And "Add image" field is displayed
    And "Main text" field is displayed
    And "Author" field is displayed
    And "Author" field is prefield and can't be edited
    And "Date" field is displayed
    And "Date" field is prefield and can't be edited
    And "Source" field is displayed
    And "Cancel" button is displayed
    And "Preview" button is displayed
    And "Publish" button is displayed

Feature: [Create News]Title Field
# Verify the validation of the "Title" field (mandatory, maximum 170 characters) and that the "Publish" button remains disabled until both Title and Main Text (Content) fields are filled and at least one tag is chosen.
  Background:
    Given User logged into the system
    And User opened clicks "Eco news" in header
    Then Eco News page is loaded
    When User clicks "Create news"
    Then Create News page is loaded

  Scenario: Verify Publish button availability when no symbol is entered
    When User clicks on "Title" field
    And User inputs no symbol into "Title" field
    Then "Title" field's border is highlighted in red
    And "Publish" button is disabled
    And character counter shows "0/170"

  Scenario: Verify "Title" field when too long title is entered
    When User inputs 171-character-long string into "Title" field
    Then entered text is truncated to 170 characters
    And character counter is highlighted in red

  Scenario: Verify "Title" field with valid title entered
    When User inputs "Test News" into "Title" field
    Then character counter shows "9/170"
    And "Title" field's border isn't highlighted in red
    And "Publish" button is disabled
    When User selects any available tag
    And User types at least 20 symbols in "Content" field
    Then "Publish" button is enabled



