# set of automated tests based on 051023-529 test set for “Single-Choice Question - Show-stopper” feature

# test case: 051023-530
  Feature: Single-Choice Question - Show-stopper"
    Scenario: Log in as a Teacher, create a quiz with a "Single-Choice Question - Show-Stopper" and verify the result
      Given I open url "title page"
      When I type "***change_me***" into field "Email *"
      And I type "***change_me***" into field "Password *"
      And I click on button "Sign In"
      And I wait for 3 sec

      Then element with xpath "//div[@class='info']/p" should contain text "TEACHER"
      And element "Quizzes left menu option" should be displayed

      Then I select option "Quizzes" in the left menu
      And I wait for 3 sec

      When I click on button "Create New Quiz"
      And I wait for 2 sec
      And I type "New Quiz - testing single-choice question - show-stopper" into field "Title Of The Quiz *"

      And I click on button "Add Question"
      And I select question number 1 for setup
      And I wait for 2 sec
      And I set question type as "Single-Choice"
      And I set question price as 7
      And I wait for 2 sec
      And I set question title as "First question"
      And I set option 1 as "Incorrect answer"
      And I set option 2 as "Incorrect Answer"
      And I add one more option in the question selected
      And I wait for 1 sec
      And I set option 3 as "Correct Answer"
      And I add one more option in the question selected
      And I wait for 1 sec
      And I set option 4 as "Incorrect Answer"
      And I set option 3 as correct answer
      And I wait for 3 sec
      And I set question as show-stopper


      And I click on button "Add Question"
      And I wait for 5 sec
      And I select question number 2 for setup
      And I set question type as "Multiple-Choice"
      And I set question price as 9
      And I set question title as "Second question"
      And I add one more option in the question selected
      And I add one more option in the question selected
      And I set option 1 as "Correct answer"
      And I set option 2 as "Incorrect answer"
      And I set option 3 as "Correct answer"
      And I set option 4 as "Incorrect answer"
      And I wait for 3 sec
      And I set option 1 as correct answer
      And I set option 3 as correct answer
      And I wait for 3 sec
    
      And I click on button "Add Question"
      And I select question number 3 for setup
      And I wait for 2 sec
      And I set question type as "Textual"
      And I wait for 2 sec
      And I set question price as 4
      And I set question title as "Third question"

      And I click on button "Save Quiz"
      And I wait for 5 sec

      Then I select quiz by title "New Quiz - testing single-choice question - show-stopper"
      And I wait for 3 sec
      And I verify that number of questions in the quiz is equal to 3

      When I open preview window of the quiz
      Then element with xpath "//ac-quiz-preview/h3" should contain text "PREVIEW MODE"
      And element with xpath "//ac-quiz-preview//ac-take-quiz/h4" should contain text "New Quiz - testing single-choice question - show-stopper"

      And I verify that question 1 is marked as Show-Stopper
      And I verify only one question in quiz is marked as Show-Stopper
      And I verify that type of question 1 is "Single-Choice"

      When I close preview window of the quiz
      And I delete quiz selected
      And I wait for 3 sec
      Then I verify that quiz with title "New Quiz - testing single-choice question - show-stopper" is not listed



# test case: 051023-531
  Scenario: Log in as Teacher, open the quiz (created before) with a "Single-Choice Question - Show-Stopper" question, add one more question and mark it as "Show-Stopper", and then verify that only one question has such mark after all
    Given I open url "title page"
    When I type "***change_me***" into field "Email *"
    And I type "***change_me***" into field "Password *"
    And I click on button "Sign In"
    And I wait for 3 sec

    Then I select option "Quizzes" in the left menu
    And I wait for 3 sec

    When I select quiz by title "New Quiz - testing single-choice question - show-stopper"
    And I wait for 2 sec
    And I start to edit quiz
    And I wait for 3 sec

    And I click on button "Add Question"
    And I wait for 2 sec
    And I select question number 4 for setup
    And I set question type as "Single-Choice"
    And I set question price as 7
    And I set question title as "Forth question"
    And I add one more option in the question selected
    And I add one more option in the question selected
    And I set option 1 as "Incorrect answer"
    And I set option 2 as "Incorrect answer"
    And I set option 3 as "Correct answer"
    And I set option 4 as "Incorrect answer"
    And I set option 3 as correct answer
    And I set question as show-stopper

    And I select question number 1 for setup
    Then I verify that question is not checked as "Show-stopper"

    When I click on button "Save Quiz"
    And I wait for 4 sec

    And I select quiz by title "New Quiz - testing single-choice question - show-stopper"
    And I start to edit quiz
    And I wait for 3 sec

    And I select question number 4 for setup
    Then I verify that question is checked as "Show-Stopper"
    And I select question number 1 for setup
    Then I verify that question is not checked as "Show-stopper"
    # the question number 2 is skipped in this verification because
    # the type of the question is "Multiple-Choice", and so there is no checkbox to select it as show-stopper
    And I select question number 3 for setup
    Then I verify that question is not checked as "Show-stopper"

