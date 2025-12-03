Step Wiki — Extracted Gherkin steps

This document collects all step lines found in the repository's .feature files, grouped by intent, with short descriptions and suggestions for step-definition patterns. Use this as a reference to implement or harmonize step definitions.

Summary
- Source: src/test/resources/features/*.feature (10 files)
- Purpose: centralize step wording, group by domain, and propose regex/parameterization guidance.

How to use
- Copy or adapt step-definition regexes (Java/Cucumber) from the "Suggested pattern" column.
- Prefer parameterized regexes to reduce duplication (placeholders shown as <param> where applicable).

1) Authentication / Authorization

Steps
- Given the user is not logged in to the GreenCity system
- Given the user is logged into the system
- Given the user is logged in
- Given the user is in the state: <Status>
- Then the system displays the login or registration form
- Then the login form is displayed for unlogged users
- Then the user is: <Redirection>
- And the user is not redirected away from the current page

Description
- These steps describe the user's authentication state and expected behavior when interacting with protected actions (e.g., "Start forming a habit!" button).

Suggested patterns
- Given the user is not logged in to the GreenCity system
  - Pattern: ^the user is not logged in( to the GreenCity system)?$
- Given the user is logged in(?:to the system)?
  - Pattern: ^the user is logged in( into the system)?$
- Given the user is in the state: <Status>
  - Pattern: ^the user is in the state: (.+)$
- Then the system displays the login or registration form
  - Pattern: ^the system displays the (login|registration) form$
- Then the user is: <Redirection>
  - Pattern: ^the user is: (.+)$

Notes
- Use parameterized patterns for different redirection outcomes and states.

2) Navigation & Page load

Steps
- And the GreenCity main page is open
- When the user navigates to the advertising section at the top of the page
- When the user scrolls down to the "Receive interesting Eco-news" section
- When the user scrolls down to the Statistics section
- When the user scrolls down until the Statistics section becomes visible
- And the GreenCity Main Page is open (variant - normalized to lower-case main page)
- Then a new page with a list of news articles is opened
- Then the Create News page is loaded
- Then the Eco News page is loaded
- And the user is on the Eco News Item page
- And the user is on the GreenCity News page at "<url>"

Description
- Page navigation and visibility checks; used in Backgrounds and preconditions.

Suggested patterns
- And the GreenCity main page is open
  - Pattern: ^the GreenCity main page is open$
- When the user scrolls down to the "(.+)" section
  - Pattern: ^the user scrolls down to the "(.+)" section$
- When the user navigates to the advertising section at the top of the page
  - Pattern: ^the user navigates to the advertising section( at the top of the page)?$
- Then a new page with a list of news articles is opened
  - Pattern: ^a new page with a list of news articles is opened$

Notes
- Keep URLs as parameters where present. Background steps that load a page are good candidates for shared step defs.

3) Buttons, Clicks and UI actions

Steps
- And the user clicks the "Start forming a habit!" button in the "Eco Bag" section
- And the user clicks the "Start forming a habit!" button in the "Cups" section
- When the user clicks the "Start forming a habit!" button
- When the user clicks on the "Start forming a habit!" button
- When the user clicks "Create News"
- And the user clicks "Publish"
- And the user clicks "Cancel"
- When the user clicks the "Delete" icon on the comment
- When the user clicks "Edit" on a comment
- When the user clicks "Yes, cancel"
- When the user clicks "Continue editing"
- When the user clicks "No" on the confirmation modal
- When the user clicks "Yes" on the confirmation modal
- When the user clicks the "Preview" button
- When the user clicks all "Start forming a habit!" buttons
- And the user closes the form

Description
- Generic click actions. The same step-definition can handle clicks on different named UI elements if parameterized.

Suggested patterns
- When the user clicks "([^"]+)"
  - Pattern: ^the user clicks "([^"]+)"$
- When the user clicks the "([^"]+)" button in the "([^"]+)" section
  - Pattern: ^the user clicks the "([^"]+)" button in the "([^"]+)" section$
- When the user clicks (?:the )?"([^"]+)" icon on the comment
  - Pattern: ^the user clicks the "([^"]+)" icon on the comment$

Notes
- Prefer one reusable step for clicking elements.

4) Forms & Validation (Inputs)

Steps
- And an email input field with the placeholder "Enter your email" is visible
- When the user enters an invalid email such as "eco-news"
- When the user enters a valid email such as "test@example.com"
- Then validation prevents the form from being submitted
- Then a success message or confirmation appears indicating the email was accepted
- When the user types at least one symbol in the "Title" field
- And the user types at least 20 symbols in the "Content" field
- And the user types a source address starting with "https://"
- Then remember the author name
- When the user clicks on the "Title" field
- And the user inputs no symbol into the "Title" field
- When the user inputs a 171-character-long string into the "Title" field
- When the user inputs "Test News" into the "Title" field
- Then the entered text is truncated to 170 characters
- Then the title field border is highlighted in red
- And the character counter shows "0/170"
- And the character counter shows "9/170"
- And the "Publish" button is disabled / enabled
- And the "Back to editing" button has a link
- And the title field is displayed
- And the "Author" field is displayed and prefilled and cannot be edited
- And the "Date" field is displayed and prefilled and cannot be edited

Description
- Field presence, typing, validation and UI feedback for forms (Create News, Subscribe, Comments).

Suggested patterns
- When the user enters (?:an? )?invalid email such as "([^"]+)"
  - Pattern: ^the user enters an? invalid email such as "([^"]+)"$
- When the user enters a valid email such as "([^"]+)"
  - Pattern: ^the user enters a valid email such as "([^"]+)"$
- When the user types at least (\d+) symbols in the "([^"]+)" field
  - Pattern: ^the user types at least (\d+) symbols in the "([^"]+)" field$
- When the user inputs a (\d+)-character-long string into the "([^"]+)" field
  - Pattern: ^the user inputs a (\d+)-character-long string into the "([^"]+)" field$

Notes
- For counters/truncation checks, parameterize the expected lengths. Use assertions in step defs.

5) Subscription & Newsletter section

Steps
- When the user scrolls down to the "Receive interesting Eco-news" section
- Then the section title "Receive interesting Eco-news" is displayed
- And a QR code is present and functional
- And the informational text "Subscribe for our newsletter and always be up to date with news and updates" is present
- And an email input field with the placeholder "Enter your email" is visible
- Then validation prevents the form from being submitted
- Then a success message or confirmation appears indicating the email was accepted

Description
- Specific checks for the subscription area on the homepage. Largely reusable with general form assertions and message checks.

Suggested patterns
- Then the section title "([^"]+)" is displayed
  - Pattern: ^the section title "([^"]+)" is displayed$
- And a QR code is present and functional
  - Pattern: ^a QR code is present and functional$
- And the informational text "([^"]+)" is present
  - Pattern: ^the informational text "([^"]+)" is present$

6) News creation & publishing

Steps
- When the user clicks "Create News"
- And the user selects the tag "News"
- And the user selects the following tags: (table)
- And the user fills in the title field with "Test"
- And the user fills in the main text field with "Test content with 20 chars"
- And the user clicks "Publish"
- Then the news should be published successfully
- And the published news should display the "News" tag
- And the published news should display all selected tags:
- Then the user must select at least one tag to publish
- Then the user should not be able to select a fourth tag "Initiatives"
- And the tag selection should be limited to 3 tags maximum
- Then remember the author name
- Then the Create News preview page is opened
- And the news title equals the appropriate input
- And the main text equals the appropriate input
- And the date is the current date
- And the author name equals the user's name

Description
- Steps cover creating, previewing and publishing news with tag selection and content validation.

Suggested patterns
- And the user selects the tag "([^"]+)"
  - Pattern: ^the user selects the tag "([^"]+)"$
- And the user selects the following tags:
  - Pattern: ^the user selects the following tags:$ (table handling in step def)
- Then the news should be published successfully
  - Pattern: ^the news should be published successfully$

Notes
- Tag selection is best handled via table parsing in step-definition code.

7) Image upload validation

Steps
- And the user uploads a PNG file with size "5MB" in the "Add Image" field
- And the user uploads a JPEG file with size "5MB" in the "Add Image" field
- And the user uploads a GIF file with size "1MB" in the "Add Image" field
- And the user uploads a JPEG file with size "15MB" in the "Add Image" field
- And the user uploads a <format> file with size "<size>" in the "Add Image" field
- Then the image should upload successfully without errors
- Then the "Add Image" field should be highlighted in red
- And the error message "Upload only PNG or JPEG. File size must be less than 10MB." should be displayed
- Then the upload result should be <result>
- And the validation message should be "<message>"

Description
- Validate formats and size limits for image uploads in news creation.

Suggested patterns
- And the user uploads a ([^\s]+) file with size "([^"]+)" in the "([^"]+)" field
  - Pattern: ^the user uploads a ([^\s]+) file with size "([^"]+)" in the "([^"]+)" field$
- Then the (?:image|upload) (?:should )?result should be (success|error)
  - Pattern: ^the upload result should be (success|error)$

Notes
- Use helper utils in step defs to simulate file size/format checks.

8) Comments (add/edit/delete/replies/visibility)

Steps
- When the user leaves the comment field empty
- When the user enters only spaces into the comment field
- When the user enters a valid character into the comment field
- When the user enters more than 8000 characters into the comment field
- Then the submit comment button should be disabled
- Then the submit comment button should remain disabled
- Then the submit comment button should become enabled
- When the user adds three comments
- Then the comments should appear in reverse chronological order
- And the total number of comments should increase by three
- When the user clicks the "Delete" icon on the comment
- Then a confirmation modal should appear with "Yes" and "No" buttons
- When the user clicks "No" on the confirmation modal
- Then the comment should remain visible
- When the user clicks the "Delete" icon on the comment again
- And the user clicks "Yes" on the confirmation modal
- Then the comment should be deleted
- When the user locates the comment
- Then the comment text should be visible
- And the author name should be visible
- And the profile avatar should be visible
- And the comment date should be visible
- And the edit comment button should be visible
- And the delete comment button should be visible
- And the reply comment button should be visible
- When the user clicks "Edit" on a comment
- And the user modifies the comment text without saving
- And the user refreshes the page
- Then the comment should remain unchanged
- And the comment should not have an edit label
- When the user has a comment with at least one reply
- Then deleting the comment removes all replies

Description
- Full lifecycle of comments UI: validation of comment text, posting, counting, ordering, deletion and replies handling.

Suggested patterns
- When the user (?:leaves|enters) the comment field (empty|only spaces|.+)
  - Pattern: ^the user (?:leaves the comment field empty|enters only spaces into the comment field|enters a valid character into the comment field)$
- When the user adds (\d+) comments
  - Pattern: ^the user adds (\d+) comments$

Notes
- Tables and reply trees require stateful step defs; use setup/teardown to clear test state.

9) Statistics block & behavior

Steps
- When the user scrolls down until the Statistics section becomes visible
- Then the section title "There are {N} of us and today we" is displayed
- And the "Eco Bag" section contains: (table of elements)
- And the "Cups" section contains: (table of elements)
- And all displayed links are functional
- When the user clicks all "Start forming a habit!" buttons
- Then the login form is displayed for unlogged users

Description
- Verify presence of multiple elements within statistics block, plus button behavior for unregistered users.

Suggested patterns
- Then the section title "([^"]+)" is displayed
- And the "([^"]+)" section contains:
  - Pattern: ^the "([^"]+)" section contains:$ (table)

Notes
- Prefer table-based assertions to check multiple elements at once.

10) Ads / Advertising block

Steps
- When the user navigates to the advertising section at the top of the page
- Then the advertising block is visible
- And it contains the title "A new way to cultivate useful habits"
- And it contains informative paragraph text
- And it contains an illustration image
- And it contains the button "Start forming a habit!"
- When the user clicks the "Start forming a habit!" button
- Then the user is: <Redirection>

Description
- Advertising block verification and button behavior (redirects or login form for unlogged users).

Suggested patterns
- And it contains the title "([^"]+)"
- And it contains (informative paragraph text|an illustration image)

11) Generic UI assertions / Misc

Steps
- And the "Read all news" button is located directly below the section title
- And the "Eco news" title is centered on the page
- And the articles are sorted by publishing date in descending order (newest first)
- And the "Back to editing" button is displayed
- And all displayed links are functional
- And the published news should display all selected tags:
- And the "Publish" button should remain disabled / become enabled
- And the published news should display the "News" tag

Description
- Miscellaneous UI checks for presence, ordering and visual placement.

Suggested patterns
- And the "([^"]+)" button is located directly below the section title
- And the "([^"]+)" title is centered on the page
- And the articles are sorted by publishing date in descending order\s*\(newest first\)


Appendix: Implementation hints
- Use parameterized step-definitions (regex groups) and reuse them across features.
- For tables, accept DataTable objects in Java step defs: Cucumber DataTable or List<String>.
- For placeholders such as <format>, <size>, <Status>, <Redirection>, accept them via regex capture groups.
- Keep step-definitions tolerant to small wording changes by using simpler natural-language patterns (e.g. "the user clicks \"([^"]+)\"" rather than full-sentence match).

Next steps (optional)
- Run a scan to list which of the above steps already have Java step definitions (I can do this by searching src/test/java for step regexes).
- Create a small set of canonical step-definitions (Java) that cover the most common patterns from this wiki.

---
Generated: automated extraction & grouping


