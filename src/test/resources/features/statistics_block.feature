Feature: GreenCity Homepage Statistics Verification

  Scenario: Verify the visibility and elements of the Eco-Savings Statistics block
    Given the user is not logged in to the GreenCity system
    And the GreenCity Main Page is open

    When the user scrolls down until the Statistics section becomes visible

    Then the section title "There are {N} of us and today we" is displayed

    # Verification of the Eco Bag Section
    And the "Eco Bag" section contains:
      | Element Type | Expected Content/Label |
      | Image        | labeled "ECO BAG"      |
      | Text         | "Did not take {X} bags"|
      | Question     | "And how many packages did you not take today?"|
      | Button       | "Start forming a habit!"|
      | Link         | "you can buy eco-bags here"|

    # Verification of the Cups Section
    And the "Cups" section contains:
      | Element Type | Expected Content/Label |
      | Image        | of a cup with recycle symbol|
      | Text         | "Did not throw away {X} cups"|
      | Question     | "And how many cups did you not throw away today?"|
      | Button       | "Start forming a habit!"|
      | Link         | "places that make a discount on a drink in your cup"|

    And all displayed links are functional
    And all "Start forming a habit!" buttons behave as expected for unlogged users (i.e., display login form)