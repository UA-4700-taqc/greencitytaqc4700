Feature: GreenCity Homepage Verification for Unregistered Users

  Scenario Outline: Verify Advertising Block and "Start forming a habit!" Button behavior
    Given the user is in the state: <Status>
    And the GreenCity Main Page is open

    When the user navigates to the advertising section at the top of the page

    Then the advertising block is visible
    And it contains the Title: "A new way to cultivate useful habits"
    And it contains Informative paragraph text
    And it contains an Illustration image
    And it contains the button: "Start forming a habit!"

    When the user clicks on the "Start forming a habit!" button

    Then the user is: <Redirection>

    Examples:
      | Status          | Redirection                      |
      | not logged in   | the login form is displayed      |
      | logged in       | redirected to the My Space page  |