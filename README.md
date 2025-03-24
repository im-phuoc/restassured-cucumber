# REST Assured Cucumber API Testing Framework

## 📝 Overview
A modern API testing framework built with REST Assured and Cucumber, implementing BDD approach for testing RESTful APIs. This framework demonstrates testing of authentication and user management endpoints with different role-based scenarios.

## 🏗️ Architecture
The framework follows a layered architecture:
- Feature Files (BDD Scenarios)
- Step Definitions (Test Implementation)
- Service Layer (API Interactions)
- Utilities (Helpers & Common Functions)
- Test Context (State Management)

## 🛠️ Tech Stack
- Java 17
- REST Assured 5.4.0
- Cucumber 7.15.0
- JUnit
- Allure Reports 2.27.0
- Logback 1.5.6 + SLF4J 2.0.13
- Maven

## 📋 Features
- BDD style test scenarios
- Role-based access testing (Admin/Non-Admin)
- JWT Authentication handling
- Parallel execution support
- Detailed reporting with Allure
- Comprehensive logging
- Thread-safe design

## 🗂️ Project Structure
```
src/
├── test/
│   ├── java/
│   │   ├── config/
│   │   │   └── TestConfig.java
│   │   ├── context/
│   │   │   └── TestContext.java
│   │   ├── hooks/
│   │   │   └── Hooks.java
│   │   ├── payload/
│   │   │   ├── UserRequest.java
│   │   │   └── UserResponse.java
│   │   ├── runner/
│   │   │   └── TestRunner.java
│   │   ├── services/
│   │   │   ├── AuthService.java
│   │   │   └── UserService.java
│   │   ├── stepDefinitions/
│   │   │   ├── AuthSteps.java
│   │   │   ├── CommonSteps.java
│   │   │   └── UserSteps.java
│   │   └── utils/
│   │       ├── RequestBuilder.java
│   │       └── ResponseValidator.java
│   └── resources/
│       ├── features/
│       │   ├── Auth.feature
│       │   ├── UserAdmin.feature
│       │   └── UserNonAdmin.feature
│       └── logback.xml
```

## 🔍 Test Scenarios

### 1. Authentication
- Login with valid credentials
- Login with invalid credentials validation
- User registration with validation
- JWT Token handling

### 2. User Management (Admin Role)
- Get all users with pagination
- Get user by username
- Update user roles
- Delete users
- Profile management

### 3. User Management (Non-Admin Role)
- Access control validation
- Permission restrictions
- Profile access

## 🚀 Getting Started

### Prerequisites
- Java JDK 17
- Maven 3.8+
- Git

### Installation
```bash
# Clone the repository
git clone https://github.com/im-phuoc/restassured-cucumber.git

# Navigate to project directory
cd restassured-cucumber

# Install dependencies
mvn clean install
```

### Running Tests
```bash
# Run all tests
mvn test

# Run specific feature
mvn test -Dcucumber.features="src/test/resources/features/Auth.feature"

# Generate Allure report
mvn allure:serve
```

## 📊 Test Reports
- **Allure Reports**: Detailed test execution reports with steps, attachments, and statistics
- **Cucumber HTML Reports**: BDD style reporting with feature and scenario details
- **Logging**: Comprehensive logging with Logback

## 🔐 API Endpoints Tested

### Authentication
- POST `/api/auth/login`: User authentication
- POST `/api/auth/register`: New user registration

### User Management
- GET `/api/users`: Get all users (Admin only)
- GET `/api/users/{username}`: Get user by username (Admin only)
- GET `/api/users/profile`: Get own profile
- PUT `/api/users/{username}`: Update user roles (Admin only)
- DELETE `/api/users/{username}`: Delete user (Admin only)

## 🏷️ Best Practices Implemented
- Thread-safe test execution
- Modular and reusable code
- Proper exception handling
- Comprehensive logging
- Clean code principles
- Design patterns usage

## 📈 Test Coverage
- Authentication flows
  - Valid/Invalid credentials
  - Registration validation
  - Token handling
- Role-based access control
  - Admin permissions
  - Non-admin restrictions
- Input validation
  - Required fields
  - Field format validation
  - Business rules
- Error scenarios
  - Invalid inputs
  - Unauthorized access
  - Not found cases


## 📝 License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


## 🙏 Acknowledgments
- REST Assured Documentation
- Cucumber Documentation
- API Testing Best Practices 