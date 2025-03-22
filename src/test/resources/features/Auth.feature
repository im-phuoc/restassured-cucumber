Feature: Authentication API Testing

  Scenario: Successful login with valid credentials
    Given I send a login request to "/api/auth/login" with the following credentials:
      | username    | password |
      | user       | 123456   |
    Then The status code is 200
    And The response contains an object with the following fields:
        | field    | value |
        | token    | <any> |
        | username | user |
        | email    | user@gmail.com |
        | roles    | [ROLE_USER] |

  Scenario Outline: Login with various invalid credentials
    Given I send a login request to "/api/auth/login" with the following credentials:
      | username    | password |
      | <username>  | <password> |
    Then The status code is <statusCode>
    And The response contains an error with field "<field>" and message "<message>"
    Examples:
        | username  | password | field    | message                          | statusCode |
        | user      | 12345    | password | size must be between 6 and 20  | 400        |
        | not-found | 123456   | message  | User not found                 | 400        |
        | user      | 1234567  | message  | Incorrect username or password | 400        |
        |           | 123456   | username | must not be blank              | 400        |
        | user      |          | password | must not be blank              | 400        |
        | a         | 123456   | username | size must be between 2 and 20  | 400        |

  Scenario: Register successful with random credentials
    Given I send a register request to "/api/auth/register" with random credentials
    Then The status code is 200
    And The response contains an object with the following fields:
      | field    | value                |
      | username | <generated_username> |
      | email    | <generated_email>    |
      | roles    | [ROLE_USER]          |

  Scenario Outline: Register with various invalid credentials
    Given I send a register request to "/api/auth/register" with the following credentials:
      | username    | email             | password |
      | <username>  | <email>           | <password> |
    Then The status code is <statusCode>
    And The response contains an error with field "<field>" and message "<message>"
    Examples:
        | username  | email             | password | field    | message                              | statusCode |
        | user      | user@gmail.com    | 123456    | message | Username is already in use           | 400        |
        |           | user@gmail.com    | 123456    | username | must not be blank                   | 400        |
        | user      |                   | 123456    | email    | must not be blank                   | 400        |
        | user      | user@gmail.com    |           | password | must not be blank                   | 400        |
        | a         | user@gmail.com    | 123456    | username | size must be between 2 and 20       | 400        |
        | user      | a                 | 123456    | email    | must be a well-formed email address | 400        |
        | user      | user@gmail.com    | 12345     | password | size must be between 6 and 20       | 400        |



