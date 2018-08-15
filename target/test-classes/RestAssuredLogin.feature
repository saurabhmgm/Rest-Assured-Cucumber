Feature: User Login
	IN ORDER TO access the Website
	AS A user
	I WANT TO be able to login to the site

  Scenario Outline:User should be able to login successfully
	Given a user exists with username <username>
	And password <password>
	When the user logs in to the system
	Then the user should be logged in successfully
	And the user should be welcomed with name and password
	Examples:
	  | username  | password |
	  | test-user | abc123   |


  Scenario Outline:User should be notified for Invalid login
	Given a user does not exists with username <username>
	And password <password>
	When the user logs in to the system
	Then the user should be notified of error
	And the user should be notified as <code> and <message>
	Examples:
	| username   | password | code 				| message 										  |
	| test-user1 | abc123   | UnauthorizedError | UnauthorizedError: invalid username or password |

