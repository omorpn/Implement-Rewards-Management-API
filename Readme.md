

## **Task Objective:**

The goal of this task is to design and develop a basic backend API using Java Spring Boot. This API will:

1. Provide an endpoint for customers to retrieve their current cashback balance.
2. Provide an endpoint for customers to view their cashback transaction history.
3. Generate token to access endpoints

---

## **Key Components of the Rewards Management API:**

### 1. Customer Rewards Data:
- **Purpose:** Store and manage basic information about customer rewards.
- **Details Included:**
    - **Customer ID:** A unique identifier for each customer.
    - **Total Cashback:** The total amount of cashback earned.
    - **Current Balance:** The available balance for cashout.

### 2. Cashback History:
- **Purpose:** Keep a record of each cashback transaction.
- **Details Included:**
    - **Transaction ID:** A unique identifier for each transaction.
    - **Transaction Date:** The date when the cashback was earned.
    - **Amount Earned:** The cashback amount for each transaction.
    - **Description:** A brief description of the transaction (e.g., booking details).

---

## **API Endpoints:**

### 1. Get Rewards Balance:
- **Endpoint:** `/api/rewards/balance`
- **Request Type:** `GET`
- **Functionality:** Retrieve the total cashback and current balance for a specific customer.
- **Response:**
  ```json
  {
    "customerId": "12345",
    "totalCashback": 100.0,
    "currentBalance": 50.0
  }
  ```

### 2. Get Cashback History:
- **Endpoint:** `/api/rewards/history`
- **Request Type:** `GET`
- **Functionality:** Retrieve a list of cashback transactions for a specific customer.
- **Request Parameters:**
    - `customerId` (query parameter or path variable)
- **Response:**
  ```json
  [
    {
      "transactionId": "txn123",
      "transactionDate": "2023-09-01",
      "amountEarned": 10.0,
      "description": "Booking cashback"
    },
    {
      "transactionId": "txn124",
      "transactionDate": "2023-09-02",
      "amountEarned": 5.0,
      "description": "Referral cashback"
    }
  ]
  ```

---

## **Technical Requirements:**

### 1. **Technology Stack:**
- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MySQL or PostgreSQL

### 2. **Security:**
- **Token-Based Authentication :**
    - **Endpoint:** `/api/token`
- **Request Type:** `POST`
- **Functionality:** Get authorization token 
- **Request Parameters:**
    - `customerId` (query parameter)
- **Response:**
 ```json
  {
    "token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1Nzg2NTc2NCIsImlhdCI6MTcyNTgwOTIwOCwiZXhwIjoxNzI1ODk1NjA4fQ.GmC5QLfVLXivjrHkrG5suUyX8C7MuXgRHT-DApHyS8gx3hMJc0Q0fb4K51qWnejUkWsl2PNFIPuXQyBii5rJ_w"
  }
  ```
---

### **Note:**
#### **You are required to generate bearer token to access  balance and history endpoints:**

