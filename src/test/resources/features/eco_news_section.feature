Feature: GreenCity Homepage Verification for Unregistered Users

  Scenario: Verify "Eco news" section display and functionality
    Given the user is not logged in to the GreenCity system
    And the GreenCity main page is open

    When the user scrolls down to the "Eco news" section

    Then the "Eco news" title is centered on the page
    And the "Read all news" button is located directly below the section title

    When the user clicks the "Read all news" button

    Then a new page with a list of news articles is opened
    And the articles are sorted by publishing date in descending order (newest first)