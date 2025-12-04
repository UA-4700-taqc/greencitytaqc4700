Feature: Eco News Item Page Comments Behaviour Validation

  Background:
    Given the user is logged into the system
    And the user is on the Eco News Item page

  Scenario: Validation of comment input and submit comment button behavior
    When the user leaves the comment field empty
    Then the submit comment button should be disabled

    When the user enters only spaces into the comment field
    Then the submit comment button should be disabled

    When the user enters a valid character into the comment field
    Then the submit comment button should be enabled

    When the user enters more than 8000 characters into the comment field
    Then the submit comment button should be disabled
    And an error message should be displayed


  Scenario: Add multiple comments and verify their order and count
    When the user adds three comments
    Then the comments should appear in reverse chronological order
    And the total number of comments should increase by 3


  Scenario: Verify confirmation modal on deleting a comment
    Given the user has at least one comment
    When the user clicks 'Delete' icon on the comment
    Then a confirmation modal should appear
    When the user clicks 'No' on the confirmation modal
    Then the comment should be visible
    When the user clicks 'Delete' icon on the comment
    And the user clicks 'Yes' on the confirmation modal
    Then the comment should be deleted


  Scenario: Verify visual display of a saved comment
    Given the user has at least one comment
    Then the comment text should be visible
    And the author name should be visible
    And the profile avatar should be visible
    And the comment date should be visible
    And the edit comment button should be visible
    And the delete comment button should be visible
    And the reply comment button should be visible


  Scenario: Changes are not saved without clicking "Save changes"
    Given the user has at least one comment
    When the user clicks 'Edit' icon on the comment
    And the user modifies the comment text without saving
    And the user refreshes the page
    Then the comment should remain unchanged
    And the comment should not have an edit label


  Scenario: Deleting a comment removes all related replies
    Given the user has a comment with at least one reply
    When the user clicks "Delete" icon on the comment
    And the user clicks 'Yes' on the confirmation modal
    Then the comment should be deleted
    And all replies under the comment should also be deleted
