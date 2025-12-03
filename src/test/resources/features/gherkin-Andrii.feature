Feature: Create News - Tag Selection
  As a logged-in user
  I want to select between 1 and 3 tags when creating news
  So that I can properly categorize my news content

  Background:
    Given the user is logged into the system
    And the user is on the GreenCity News page at "https://www.greencity.cx.ua/#/greenCity/news"

  Scenario: Successfully publish news with one tag
    When the user clicks "Create News"
    And the user selects the tag "News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "Test content with 20 chars"
    And the user clicks "Publish"
    Then the news should be published successfully
    And the published news should display the "News" tag

  Scenario: Successfully publish news with three tags
    When the user clicks "Create News"
    And the user selects the following tags:
      | News      |
      | Events    |
      | Education |
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "Test content with 20 chars"
    And the user clicks "Publish"
    Then the news should be published successfully
    And the published news should display all selected tags:
      | News      |
      | Events    |
      | Education |

  Scenario: Prevent selecting more than three tags
    When the user clicks "Create News"
    And the user selects the following tags:
      | News      |
      | Events    |
      | Education |
    Then the user should not be able to select a fourth tag "Initiatives"
    And the tag selection should be limited to 3 tags maximum

  Scenario: Verify minimum tag requirement
    When the user clicks "Create News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "Test content with 20 chars"
    Then the user must select at least one tag to publish

Feature: Create News - Image Upload Validation
  As a logged-in user
  I want to upload images with proper format and size validation
  So that only valid images (PNG/JPG, max 10MB) are accepted for news content

  Background:
    Given the user is logged into the system
    And the user is on the GreenCity News page at "https://www.greencity.cx.ua/#/greenCity/news"

  Scenario: Successfully upload a valid PNG image
    When the user clicks "Create News"
    And the user uploads a PNG file with size "5MB" in the "Add Image" field
    Then the image should upload successfully without errors

  Scenario: Successfully upload a valid JPEG image
    When the user clicks "Create News"
    And the user uploads a JPEG file with size "5MB" in the "Add Image" field
    Then the image should upload successfully without errors

  Scenario: Reject unsupported image format (GIF)
    When the user clicks "Create News"
    And the user uploads a GIF file with size "1MB" in the "Add Image" field
    Then the "Add Image" field should be highlighted in red
    And the error message "Upload only PNG or JPEG. File size must be less than 10MB." should be displayed

  Scenario: Reject image exceeding maximum file size
    When the user clicks "Create News"
    And the user uploads a JPEG file with size "15MB" in the "Add Image" field
    Then the "Add Image" field should be highlighted in red
    And the error message "Upload only PNG or JPEG. File size must be less than 10MB." should be displayed

  Scenario Outline: Validate image upload with various file formats and sizes
    When the user clicks "Create News"
    And the user uploads a <format> file with size "<size>" in the "Add Image" field
    Then the upload result should be <result>
    And the validation message should be "<message>"

    Examples:
      | format | size  | result  | message                                                     |
      | PNG    | 5MB   | success |                                                             |
      | JPEG   | 9MB   | success |                                                             |
      | JPG    | 9.9MB | success |                                                             |
      | GIF    | 1MB   | error   | Upload only PNG or JPEG. File size must be less than 10MB.  |
      | BMP    | 2MB   | error   | Upload only PNG or JPEG. File size must be less than 10MB.  |
      | PNG    | 10MB  | error   | Upload only PNG or JPEG. File size must be less than 10MB.  |
      | PNG    | 11MB  | error   | Upload only PNG or JPEG. File size must be less than 10MB.  |
      | JPEG   | 15MB  | error   | Upload only PNG or JPEG. File size must be less than 10MB.  |

Feature: Create News - Content Field Validation
  As a logged-in user
  I want the content field to validate text length between 20 and 63,206 characters
  So that only properly sized content can be published

  Background:
    Given the user is logged into the system
    And the user is on the GreenCity News page at "https://www.greencity.cx.ua/#/greenCity/news"

  Scenario: Reject content with less than minimum characters
    When the user clicks "Create News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "Short text"
    And the user clicks "Publish"
    Then the error message "Must be a minimum of 20 and a maximum of 63,206 symbols." should be displayed in red
    And the "Publish" button should remain disabled

  Scenario: Truncate content exceeding maximum characters
    When the user clicks "Create News"
    And the user fills in the main text field with "63207" characters
    Then the text should be truncated to "63206" characters
    And no error message should appear
    And the "Publish" button should become enabled

  Scenario: Successfully publish news with valid content length
    When the user clicks "Create News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "This is a valid test content"
    Then the error message should disappear
    And the "Publish" button should become enabled
    When the user clicks "Publish"
    Then the news should be published successfully without errors

  Scenario Outline: Validate content field with various character lengths
    When the user clicks "Create News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "<characters>" characters
    Then the validation result should be "<result>"
    And the "Publish" button state should be "<button_state>"
    And the error message display should be "<error_display>"

    Examples:
      | characters | result    | button_state | error_display |
      | 10         | invalid   | disabled     | shown         |
      | 19         | invalid   | disabled     | shown         |
      | 20         | valid     | enabled      | hidden        |
      | 100        | valid     | enabled      | hidden        |
      | 63206      | valid     | enabled      | hidden        |
      | 63207      | truncated | enabled      | hidden        |

Feature: Create News - Cancel Button Behavior
  As a logged-in user
  I want to cancel news creation with confirmation
  So that I can prevent accidental loss of content

  Background:
    Given the user is logged into the system
    And the user is on the GreenCity News page at "https://www.greencity.cx.ua/#/greenCity/news"

  Scenario: Cancel news creation and confirm cancellation
    When the user clicks "Create News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "Test content with 20 chars"
    And the user clicks "Cancel"
    Then a confirmation modal should appear with the message "All created content will be lost. Do you still want to cancel news creating?"
    When the user clicks "Yes, cancel"
    Then the form should close
    And the user should be redirected to the GreenCity News page

  Scenario: Cancel news creation but continue editing
    When the user clicks "Create News"
    And the user fills in the title field with "Test"
    And the user fills in the main text field with "Test content with 20 chars"
    And the user clicks "Cancel"
    Then a confirmation modal should appear with the message "All created content will be lost. Do you still want to cancel news creating?"
    When the user clicks "Continue editing"
    Then the modal should close
    And the user should remain on the "Create News" form
    And the title field should contain "Test"
    And the main text field should contain "Test content with 20 chars"