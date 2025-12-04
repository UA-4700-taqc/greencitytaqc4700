Feature: Comment Metadata Visibility
  As a logged-in user
  I want the avatar, username, and comment date to be visible and non-editable
  So that comment metadata is always accurate and consistent

  Background:
    Given the user is logged in
    And a comment with visible meta-data is present

  Scenario: Ensure avatar, username, and date are visible and read-only
    When the user locates a comment
    Then the comment should display an avatar image
    And the comment should display the username
    And the comment should display the comment date
    When the user attempts to modify the avatar, username, or comment date
    Then the avatar, username, and comment date fields must not be editable