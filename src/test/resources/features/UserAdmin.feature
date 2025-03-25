Feature: User API Testing with user have admin role
  Background:
    Given I send a login request to "/api/auth/login" with the following credentials:
      | username    | password |
      | admin       | 123456   |


  Scenario: Get all users
    When I send a GET request to "/api/users"
    Then The status code is 200
    And The response contains a pagination list with valid metadata
    And The response contains a list of valid users

  Scenario: Get user by username
    When I send a GET request to "/api/users/{username}" with path variable "username" as "user"
    Then The status code is 200
    And The response contains an object with the following fields:
      | field    | value |
      | username | user |
      | email    | user@gmail.com |
      | roles    | ROLE_USER |

  Scenario: Get user by invalid username
    When I send a GET request to "/api/users/{username}" with path variable "username" as "not-found"
    Then The status code is 400
    And The response contains an error with field "message" and message "User not found"

  Scenario: Get profile
    When I send a GET request to "/api/users/profile"
    Then The status code is 200
    And The response contains an object with the following fields:
      | field    | value |
      | username | admin |
      | email    | admin@gmail.com |
      | roles    | ROLE_USER ROLE_ADMIN |

  Scenario: Update roles of user
    When I send a PUT request to "/api/users/{username}" with path variable "username" as "update" and the following roles:
      | roles |
      | ROLE_USER|
      | ROLE_ADMIN |
    Then The status code is 200
    And The response contains an object with the following fields:
      | field    | value |
      | username | update |
      | email    | update@gmail.com |
      | roles    | ROLE_USER ROLE_ADMIN |

    Scenario Outline: Update user with various invalid roles
    When I send a PUT request to "/api/users/{username}" with path variable "username" as "<username>" and the following roles:
      | roles |
      | <roles> |
    Then The status code is <statusCode>
    And The response contains an error with field "<field>" and message "<message>"
      Examples:
        | username  | roles           | field    | message                                  | statusCode |
        | user      | ROLE_INVALID    | message  | Role 'ROLE_INVALID' is not a valid role  | 400        |
        | not-found | ROLE_USER       | message  | User not found                           | 400        |
        | user      |                 | message  | Roles cannot be empty                    | 400        |

  Scenario Outline: Delete user with various username
    When I send a DELETE request to "/api/users/{username}" with path variable "username" as "<username>"
    Then The status code is <statusCode>
    And The response contains an error with field "message" and message "<message>"
      Examples:
        | username  | message                                  | statusCode |
        | delete    | User delete has been deleted successfully  | 200        |
        | not-found | User not found                           | 400        |
