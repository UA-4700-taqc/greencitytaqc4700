Feature: GreenCity Statistics Block Interaction

  Scenario: Unregistered user is prompted to log in when clicking "Start forming a habit!" in Statistics
    Given the user is not logged in to the GreenCity system
    And the GreenCity Main Page is open

    When the user scrolls down to the Statistics section

    # Action on Eco Bag button
    And the user clicks the "Start forming a habit!" button in the "Eco Bag" section

    Then the system displays the **login or registration form**
    And the user is **not redirected** away from the current page

    # Action on Cups button
    When the user closes the form
    And the user clicks the "Start forming a habit!" button in the "Cups" section

    Then the system displays the **login or registration form** again
    And the user is **not redirected** away from the current page