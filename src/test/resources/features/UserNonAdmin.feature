Feature: User API Testing with user have non admin role
  Background:
    Given I send a login request to "/api/auth/login" with the following credentials:
      | username    | password |
      | user        | 123456   |


  Scenario Outline: User with non admin role try get all users and get user by username
    When I send a GET request to "<endpoint>"
    Then The status code is <statusCode>
    And The response contains an error with field "<field>" and message "<message>"
    Examples:
        | endpoint             | statusCode | field        | message                 |
        | /api/users           | 400        | message      | Access Denied           |
        | /api/users/user      | 400        | message      | Access Denied           |

  Scenario: Get profile
    When I send a GET request to "/api/users/profile"
    Then The status code is 200
    And The response contains an object with the following fields:
      | field    | value           |
      | username | user            |
      | email    | user@gmail.com  |
      | roles    | ROLE_USER       |

  Scenario: Update roles of user
    When I send a PUT request to "/api/users/{username}" with path variable "username" as "user" and the following roles:
      | roles |
      | ROLE_USER|
      | ROLE_MODERATOR |
    Then The status code is 400
    And The response contains an error with field "message" and message "Access Denied"

  Scenario: Delete user by username
    When I send a DELETE request to "/api/users/{username}" with path variable "username" as "user"
    Then The status code is 400
    And The response contains an error with field "message" and message "Access Denied"