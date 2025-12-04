Feature: GreenCity Homepage Verification for Unregistered Users

# Subscription section
Scenario: Verify the 'Receive interesting Eco-news' section
  Given the user is not logged in to the GreenCity system
  And the GreenCity main page is open

  When the user scrolls down to the "Receive interesting Eco-news" section

  Then the section title "Receive interesting Eco-news" is displayed
  And a QR code is present and functional
  And the informational text "Subscribe for our newsletter and always be up to date with news and updates" is present
  And an email input field with the placeholder "Enter your email" is visible

  When the user enters an invalid email such as "eco-news"
  Then validation prevents the form from being submitted

  When the user enters a valid email such as "test@example.com"
  And the user clicks the "Subscribe" button
  Then a success message or confirmation appears indicating the email was accepted
