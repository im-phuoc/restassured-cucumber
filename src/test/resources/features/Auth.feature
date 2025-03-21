Feature: Test Authentication APIs (/api/auth/login and /api/auth/register)
  Scenario: Register with empty fields
    Given I send a POST request to "/api/auth/register" with body:
      | username | email       | password |
      |          |             |          |
    When I receive a response
    Then The status code is 400
    And The response contains an error for "username" with message "must not be blank"
    And The response contains an error for "email" with message "must not be blank"
    And The response contains an error for "password" with message "must not be blank"

  Scenario: Register with username less than 2 characters
    Given I send a POST request to "/api/auth/register" with body:
      | username | email       | password |
      | a        | test@email.com | 123456  |
    When I receive a response
    Then The status code is 400
    And The response contains an error for "username" with message "size must be between 2 and 20"

  Scenario: Register with username greater than 20 characters
    Given I send a POST request to "/api/auth/register" with body:
      | username | email       | password |
      | admtest12345678910000in | test@email.com | 123456  |
    When I receive a response
    Then The status code is 400
    And The response contains an error for "username" with message "size must be between 2 and 20"

  Scenario: Register with already existing username
    Given I send a POST request to "/api/auth/register" with body:
      | username | email       | password |
      | admin    | test@email.com | 123456  |
    When I receive a response
    Then The status code is 400
    And The response contains an error with message "Username is already in use"

  Scenario: Register with already existing email
    Given I send a POST request to "/api/auth/register" with body:
      | username | email          | password |
      | test     | admin@gmail.com | 123456  |
    When I receive a response
    Then The status code is 400
    And The response contains an error with message "Email is already in use"

  Scenario: Register with invalid email
    Given I send a POST request to "/api/auth/register" with body:
      | username | email       | password |
      | test     | test        | 123456  |
    When I receive a response
    Then The status code is 400
    And The response contains an error for "email" with message "must be a well-formed email address"

  Scenario: Register successfully
    Given I send a POST request to "/api/auth/register" with body:
      | username | email          | password |
      | test    | test@email.com | 123456  |
    When I receive a response
    Then The status code is 200
    And The response contains the field "username" with value "test"
    And The response contains the field "email" with value "test@email.com"
    And The response contains all roles:
      | role           |
      | ROLE_USER      |

  Scenario: Login successfully with valid credentials
    Given I send a POST request to "/api/auth/login" with body:
      | username | password  |
      | admin    | 123456    |
    When I receive a response
    Then The status code is 200
    And The response contains a valid token
    And The response contains the field "username" with value "admin"
    And The response contains the field "email" with value "admin@gmail.com"
    And The response contains all roles:
      | role           |
      | ROLE_USER      |
      | ROLE_MODERATOR |
      | ROLE_ADMIN     |

  Scenario: Login with invalid username
    Given I send a POST request to "/api/auth/login" with body:
      | username | password  |
      | adm      | 123456    |
    When I receive a response
    Then The status code is 400
    And The response contains an error with message "User not found"

  Scenario: Login with invalid password
    Given I send a POST request to "/api/auth/login" with body:
      | username | password  |
      | admin    | 1234567   |
    When I receive a response
    Then The status code is 400
    And The response contains an error with message "Incorrect username or password"

  Scenario: Login with empty fields
    Given I send a POST request to "/api/auth/login" with body:
      | username | password  |
      |          |           |
    When I receive a response
    Then The status code is 400
    And The response contains an error for "username" with message "must not be blank"
    And The response contains an error for "password" with message "must not be blank"