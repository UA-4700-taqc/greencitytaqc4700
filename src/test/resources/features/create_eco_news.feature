Feature: Create News — Basic Preview and Field Validation

Background:
  Given the user is logged into the system
  And the user clicks 'Eco news' in the header
  Then the Eco News page is loaded


Scenario: Preview shows entered data
  When the user clicks 'Create news'
  Then the Create News page is loaded
  When the user types at least one symbol in the 'Title' field
  And the user types at least 20 symbols in the 'Content' field
  And the user types a source address starting with https
  Then remember the author name
  When the user clicks the 'Preview' button
  Then the Create News preview page is opened
  And the news title equals the appropriate input
  And the main text equals the appropriate input
  And the date is the current date
  And the author name equals the user's name
  And the 'Back to editing' button is displayed
  And the 'Back to editing' button has a link

Scenario: Create News form displays required fields
  When the user clicks 'Create news'
  Then the Create News page is loaded
  And the title field is displayed
  And five tag buttons are displayed:
    | News        |
    | Events      |
    | Education   |
    | Initiatives |
    | Advertising |
  And the 'Add image' field is displayed
  And the 'Main text' field is displayed
  And the 'Author' field is displayed and prefilled and cannot be edited
  And the 'Date' field is displayed and prefilled and cannot be edited
  And the 'Source' field is displayed
  And the 'Cancel' button is displayed
  And the 'Preview' button is displayed
  And the 'Publish' button is displayed

Scenario: Publish button disabled when title is empty
  When the user clicks 'Create news'
  Then the Create News page is loaded
  When the user inputs no symbol into the 'Title' field
  Then the title field border is highlighted in red
  And the 'Publish' button is disabled
  And the character counter shows '0-170'

Scenario: Title is truncated when too long
  When the user inputs a 171-character-long string into the 'Title' field
  Then the entered text is truncated to 170 characters
  And the character counter is highlighted in red

Scenario: Valid title allows publish when other requirements met
  When the user inputs 'Test News' into the 'Title' field
  Then the character counter shows '9-170'
  And the title field border is not highlighted in red
  And the 'Publish' button is disabled
  When the user selects any available tag
  And the user types at least 20 symbols in the 'Content' field
  Then the 'Publish' button is enabled
