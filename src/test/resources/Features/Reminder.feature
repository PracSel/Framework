Feature: As a obediant tester, I should be able to strike out completed reminders, so that I see my accomplishment

  @complete-single
  Scenario: Completing a single reminder task
    Given user is on create reminder landing page
    When he creates a reminder "Review TestCases"
    Then he should see "Review TestCases" added to the reminder list
    And he should see a total of 1 reminders added to the list
    And he sees the active count to be "1"
    When he strikes out "Review TestCases"
    Then he sees the active count to be "0"

  @complete-multiple
  Scenario: Completing multiple reminder tasks
    Given user is on create reminder landing page
    When he creates a reminder "Review TestCases"
    And he creates a reminder "Automate TestCases"
    And he creates a reminder "Setup CI/CD"
    And he should see a total of 3 reminders added to the list
    And he sees the active count to be "3"
    When he strikes out "Review TestCases"
    And he strikes out "Automate TestCases"
    Then he sees the active count to be "1"

  @refresh
  Scenario: Verifying reminders are maintained in list, even on browser refresh
    Given user is on create reminder landing page
    When he creates following reminders
      | Review TestPlan A |
      | Review TestPlan B |
      | Review TestPlan C |
      | Review TestPlan D |
    Then he should see a total of 4 reminders added to the list
    When user refreshes the reminder landing page
    Then he should see a total of 4 reminders added to the list
