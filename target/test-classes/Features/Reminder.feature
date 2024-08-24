Feature: As a busy tester,I should be able to create reminders so that i can plan my day and not forget key tasks.

Scenario: Creating Single reminder task

Given User is on create reminder landing page
When he creates a reminder "Review TestCases"
Then he should see "Review TestCases" added to the reminder list
And he should see a total of 1 reminder added to the list

