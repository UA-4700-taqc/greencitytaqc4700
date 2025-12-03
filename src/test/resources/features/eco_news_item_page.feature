Feature: Comment input validation

  Background:
    Given the user is logged in
    And the user is on the Eco News Item page

  Scenario: Validation of Comment Input and Submit Comment button behavior
    When the user leaves the comment field empty
    Then the submit comment button should be disabled

    When the user enters only spaces into the comment field
    Then the submit comment button should remain disabled

    When the user enters a valid character into the comment field
    Then the submit comment button should become enabled

    When the user enters more than 8000 characters into the comment field
    Then the submit comment button should be disabled
    And an error message should be displayed



Feature: Comment order and count

  Background:
    Given the user is logged in
    And the user is on the Eco News Item page

  Scenario: Add multiple comments and verify their order and count
    When the user adds three comments
    Then the comments should appear in reverse chronological order
    And the total number of comments should increase by three



Feature: Comment deletion confirmation

  Background:
    Given the user is logged in
    And the user has at least one comment

  Scenario: Verify confirmation modal on deleting a comment
    When the user clicks the "Delete" icon on the comment
    Then a confirmation modal should appear with "Yes" and "No" buttons
    When the user clicks "No" on the confirmation modal
    Then the comment should remain visible
    When the user clicks the "Delete" icon on the comment again
    And the user clicks "Yes" on the confirmation modal
    Then the comment should be deleted



Feature: Comment component visual elements

  Background:
    Given the user has added at least one comment
    And the user is on the Eco News Item page

  Scenario: Verify visual display of a saved comment
    When the user locates the comment
    Then the comment text should be visible
    And the author name should be visible
    And the profile avatar should be visible
    And the comment date should be visible
    And the edit comment button should be visible
    And the delete comment button should be visible
    And the reply comment button should be visible



Feature: Comment editing without saving

  Background:
    Given the user is logged in
    And the user has at least one comment

  Scenario: Changes are not saved without clicking "Save changes"
    When the user clicks "Edit" on a comment
    And the user modifies the comment text without saving
    And the user refreshes the page
    Then the comment should remain unchanged
    And the comment should not have an edit label



Feature: Deleting a comment with replies

  Background:
    Given the user is logged in
    And the user has a comment with at least one reply

  Scenario: Deleting a comment removes all related replies
    When the user clicks the "Delete" icon on the comment
    And the user confirms deletion by clicking "Yes"
    Then the comment should be deleted
    And all replies under the comment should also be deleted
