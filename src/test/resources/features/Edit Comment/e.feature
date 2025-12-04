Feature: Saved update after comment editing
  As a logged-in user
  I want to be able to edit my comments
  So that the updated content and edit date are shown correctly

  Background:
    Given the user is logged in
    And the user has at least one editable comment

  Scenario: Save changes updates the comment and shows the edit date
    When the user locates their comment
    And the user edits the comment text to "Updated comment text"
    And the user saves the changes
    Then the comment text should be updated to "Updated comment text"
    And the edit date should be displayed in format "Month, day, year"
