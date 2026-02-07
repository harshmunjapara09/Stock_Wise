# Stock Market App - Spring Boot Application 📈🚀

Welcome to the Stock Market App, a cutting-edge Spring Boot application designed to provide real-time stock market data, portfolio management, and secure transactions. This project aims to redefine stock market experiences by offering a comprehensive set of features.

## Frameworks and Languages

The Stock Market App is developed using the following frameworks and languages:

- **Java:** The programming language used for backend development.
- **Spring Boot:** A Java-based framework for building web applications.
- **Spring Data JPA:** Simplifying data access for a smoother user experience.
- **MySQL:** The chosen database management system.
- **Swagger:** Creating interactive API documentation.
- **SMTP:** Facilitating email communication, including password resets and notifications.
- **Lombok:** Reducing boilerplate code, allowing a focus on innovative features.

---

## Data Flow

The project follows a standard Spring Boot MVC structure and consists of the following components:

- **Controller:** Contains the API endpoints and request mappings.
- **Service:** Implements business logic and interacts with the repository.
- **Repository:** Handles data access to the MySQL database.
- **DTO (Data Transfer Object):** Represents the data structure exchanged between the API and the client.
- **Model:** Represents the data model(entity) mapped to the MySQL database.

## Features and Functionality

### User Management API

- **Signup:**
  - Endpoint: `/user/signup`
  - Method: POST
  - Description: Sign up for a new account.

- **Login:**
  - Endpoint: `/user/login`
  - Method: GET
  - Description: Log in securely to your account.

- **Deposit Funds:**
  - Endpoint: `/user/addFund`
  - Method: POST
  - Description: Deposit funds into your account.

- **Reset Password:**
  - Endpoint: `/user/resetPassword`
  - Method: POST
  - Description: Reset your password.

- **Verify OTP for Password Reset:**
  - Endpoint: `/user/verifyOTPRestPassword`
  - Method: POST
  - Description: Verify your identity for password reset.

- **Logout:**
  - Endpoint: `/user/logout`
  - Method: GET
  - Description: Log out of your account.

### Stock Information and Transactions API

- **Get Info for a Specific Stock:**
  - Endpoint: `/api/stocks/getInfoStockByName`
  - Method: GET
  - Description: Retrieve real-time data for a specific stock symbol.

- **Get Comprehensive Information about All Stocks:**
  - Endpoint: `/api/stocks/getAllStockInfo`
  - Method: GET
  - Description: Get comprehensive information about all available stocks.

- **Get Transaction History:**
  - Endpoint: `/api/stocks/statement`
  - Method: GET
  - Description: Access your transaction history to track your investment journey.

### Activity and Portfolio Management API

- **Buy Stock:**
  - Endpoint: `/api/activity/buyStock`
  - Method: POST
  - Description: Purchase stocks securely.

- **Sell Stock:**
  - Endpoint: `/api/activity/sellStock`
  - Method: POST
  - Description: Sell stocks confidently.

- **Get Portfolio:**
  - Endpoint: `/portfolio/get`
  - Method: GET
  - Description: Securely purchase stocks and manage your investment portfolio.

---


## Getting Started

To get started with this Banking System, follow these steps:


1. Clone the repository to your local machine:

   ```shell
   git clone https://github.com/harshmunjapara09/Stock_Wise.git
2. **Set up the required database (MySQL) and configure the database connection** in the `application.properties` file, and also you have to set up SMTP for email OTP.

3. **Build and run the application** using Maven or your preferred IDE.
  


## Contact

Feel free to connect with me to learn more or discuss the technology behind it:

- Email: harshmunjapara005@email.com
- LinkedIn: [harshmunjapara](https://www.linkedin.com/in/harsh-munjapara/)

- ### The below video shows how the app works:
https://github.com/kevinchangani96/Stock_Wise_Web_Application/assets/86094750/4a1d0dfd-4969-4d75-aa99-b162e172cc84
