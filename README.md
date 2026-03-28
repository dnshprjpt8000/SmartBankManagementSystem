<div align="center">

# 🏦 Smart Bank Management System

### A Feature-Rich Console-Based Banking Application in Java

[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)]()
[![Version](https://img.shields.io/badge/Version-1.0.0-purple?style=for-the-badge)]()

> A fully functional, multi-layered bank management system built entirely in Java — featuring secure authentication, multiple account types, real-time transactions, file-based persistence, and a beautiful colored CLI interface.

</div>

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Architecture](#class-architecture)
- [Design Patterns Used](#design-patterns-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [How to Use](#how-to-use)
  - [Register a New Customer](#register-a-new-customer)
  - [Login](#login)
  - [Deposit Money](#deposit-money)
  - [Withdraw Money](#withdraw-money)
  - [Transfer Funds](#transfer-funds)
  - [View Transactions](#view-transactions)
- [Account Types](#account-types)
  - [Savings Account](#savings-account)
  - [Current Account](#current-account)
- [Data Persistence](#data-persistence)
- [Security](#security)
- [Technologies Used](#technologies-used)
- [Java Concepts Covered](#java-concepts-covered)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

---

## 📖 About the Project

The **Smart Bank Management System** is a console-based Java application that simulates a real-world banking environment. The system allows customers to register accounts, perform deposits, withdrawals, and fund transfers, and view detailed transaction histories — all stored persistently in text files.

This project was built to demonstrate:
- Clean, modular Java architecture
- Object-Oriented Programming principles in a real use case
- File I/O for persistent data storage
- Custom exception handling
- Input validation and password hashing

It is ideal for students, Java learners, and developers who want to see how a mid-sized real-world Java project is structured.

---

## ✨ Features

| Feature | Description |
|--------|-------------|
| 🔐 **Secure Authentication** | SHA-256 password hashing, login attempt limits |
| 🏦 **Two Account Types** | Savings (with interest) and Current (with overdraft) |
| 💸 **Deposit & Withdraw** | Instant transactions with live balance updates |
| 🔄 **Fund Transfer** | Transfer money between any two accounts in the system |
| 📜 **Transaction History** | Full timestamped transaction log per account |
| 💾 **File Persistence** | All data saved to local files — survives restarts |
| 🎨 **Colored CLI UI** | ANSI color-coded console with borders and icons |
| ⚡ **Interest Engine** | Monthly interest on Savings, overdraft fee on Current |
| 🛡️ **Custom Exceptions** | Domain-specific exceptions for clean error handling |
| ✅ **Input Validation** | Email, phone, password, and amount validators |

---

## 📁 Project Structure

```
SmartBankManagementSystem/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── bankapp/
│                   │
│                   ├── Main.java                        # Entry point, CLI menus
│                   │
│                   ├── models/
│                   │   ├── Account.java                 # Abstract base account class
│                   │   ├── SavingsAccount.java          # Savings account (extends Account)
│                   │   ├── CurrentAccount.java          # Current account (extends Account)
│                   │   ├── Customer.java                # Customer profile model
│                   │   └── Transaction.java             # Transaction record model
│                   │
│                   ├── services/
│                   │   ├── BankService.java             # Core business logic
│                   │   └── AuthService.java             # Login / authentication logic
│                   │
│                   ├── utils/
│                   │   ├── FileHandler.java             # File read/write operations
│                   │   ├── InputValidator.java          # Input validation + password hashing
│                   │   └── ConsoleUI.java               # ANSI colors, banners, menus
│                   │
│                   └── exceptions/
│                       ├── InsufficientFundsException.java
│                       ├── AccountNotFoundException.java
│                       └── InvalidCredentialsException.java
│
├── data/                                               # Auto-created at runtime
│   ├── customers.txt                                   # Persisted customer data
│   ├── accounts.txt                                    # Persisted account data
│   └── transactions.txt                               # Persisted transaction log
│
├── docs/
│   └── architecture.md                               # System architecture diagram
│
├── .gitignore
└── README.md
```

---

## 🏗️ Class Architecture

```
┌────────────────────────────────────────────┐
│                  Main.java                 │   ← Entry point / CLI Menu
└──────────────────────┬─────────────────────┘
                       │
          ┌────────────┴────────────┐
          ▼                         ▼
  ┌───────────────┐        ┌──────────────────┐
  │  AuthService  │        │   BankService    │   ← Core business logic
  └───────────────┘        └────────┬─────────┘
                                    │
              ┌─────────────────────┼──────────────────┐
              ▼                     ▼                   ▼
     ┌──────────────┐     ┌──────────────────┐  ┌────────────────┐
     │   Customer   │     │     Account      │  │  Transaction   │
     │   (model)    │     │  (abstract model)│  │    (model)     │
     └──────────────┘     └────────┬─────────┘  └────────────────┘
                                   │
                      ┌────────────┴──────────────┐
                      ▼                            ▼
            ┌──────────────────┐       ┌───────────────────┐
            │  SavingsAccount  │       │  CurrentAccount   │
            └──────────────────┘       └───────────────────┘
```

### Layer Responsibilities

| Layer | Classes | Responsibility |
|-------|---------|---------------|
| **Entry** | `Main.java` | CLI menus, user input, routing |
| **Services** | `BankService`, `AuthService` | Business rules, orchestration |
| **Models** | `Account`, `Customer`, `Transaction` | Core data structures |
| **Utils** | `FileHandler`, `InputValidator`, `ConsoleUI` | I/O, validation, display |
| **Exceptions** | `InsufficientFundsException`, etc. | Domain-specific error types |

---

## 🧩 Design Patterns Used

### 1. Inheritance & Polymorphism
`SavingsAccount` and `CurrentAccount` both extend the abstract `Account` class. Each overrides `withdraw()` with its own business rules (minimum balance vs overdraft).

```java
// Polymorphism in action
Account account = new SavingsAccount(...);
account.withdraw(500); // Calls SavingsAccount's override
```

### 2. Encapsulation
All model fields are `private`. Data is accessed and modified only through public getters/setters, protecting invariants.

### 3. Single Responsibility Principle
Each class has one clear job:
- `FileHandler` → only handles file I/O
- `InputValidator` → only validates input
- `ConsoleUI` → only handles display
- `BankService` → only handles business logic

### 4. Custom Exception Hierarchy
Domain-specific exceptions make error handling clean and meaningful:
```java
try {
    bankService.withdraw(accountNo, amount);
} catch (InsufficientFundsException e) {
    ConsoleUI.printError(e.getMessage());
}
```

### 5. DAO Pattern (Data Access Object)
`FileHandler` abstracts all file operations from the business layer, making it easy to swap the storage mechanism (e.g., to a database) in the future.

---

## 🚀 Getting Started


## 🖥️ How to Use

### Register a New Customer

1. From the Main Menu, select **[2] Register as a new customer**
2. Enter your:
   - Full Name
   - Email address
   - 10-digit Indian mobile number
   - Address
   - Password (minimum 6 characters)
3. A unique **Customer ID** (e.g., `CUS00001`) will be generated for you
4. Use this ID to login

### Login

1. Select **[1] Login to your account**
2. Enter your **Customer ID** and **Password**
3. You have **3 attempts** before being returned to the main menu
4. On success, you're taken to your personal dashboard

### Open a New Account

From your dashboard, select **[2] Open New Account**:
- Choose **Savings** or **Current**
- Enter an initial deposit amount
- A unique **Account Number** is generated (e.g., `ACC0000000001`)

### Deposit Money

1. Select **[3] Deposit Money**
2. Choose the account (if you have multiple)
3. Enter the deposit amount
4. Balance is updated instantly and persisted to file

### Withdraw Money

1. Select **[4] Withdraw Money**
2. Choose the account
3. Enter the amount
4. System validates:
   - For Savings: ensures minimum balance of ₹500 is maintained
   - For Current: ensures amount is within overdraft limit
   - Fee applied on Savings after 5 withdrawals in a month

### Transfer Funds

1. Select **[5] Transfer Money**
2. Choose your source account
3. Enter the **recipient's account number**
4. Enter the transfer amount
5. Both accounts updated simultaneously

### View Transactions

1. Select **[6] Transaction History**
2. Displays the last 20 transactions with:
   - Transaction ID
   - Type (DEPOSIT / WITHDRAWAL / TRANSFER_IN / TRANSFER_OUT)
   - Amount
   - Balance after transaction
   - Date and time

---

## 💳 Account Types

### Savings Account

| Feature | Details |
|---------|---------|
| Minimum Balance | ₹500 |
| Annual Interest Rate | 4% (applied monthly) |
| Free Withdrawals/Month | 5 |
| Extra Withdrawal Fee | ₹10 per transaction after 5 |
| Best For | Personal savings, students, salaried individuals |

**Rules:**
- Initial deposit must be at least ₹500
- You cannot withdraw if balance would fall below ₹500
- Monthly interest is credited on the 1st of every month (manual trigger via admin)

### Current Account

| Feature | Details |
|---------|---------|
| Minimum Balance | None |
| Overdraft Facility | Up to ₹10,000 |
| Overdraft Interest | 12% per annum (applied monthly) |
| Transaction Limit | Unlimited |
| Best For | Business owners, traders, frequent transactions |

**Rules:**
- You can withdraw beyond your balance up to the overdraft limit of ₹10,000
- If balance is negative, monthly overdraft interest is charged at 12% p.a.

---

## 💾 Data Persistence

All data is stored in plain `.txt` files inside the `data/` folder:

### `data/customers.txt`
Stores one customer per line in pipe-separated format:
```
CUS00001|John Doe|john@email.com|9876543210|Bhopal|<hashed_password>|2025-01-15|ACC0000000001
```

### `data/accounts.txt`
Stores one account per line:
```
SAVINGS,ACC0000000001,CUS00001,15000.0,2,true
CURRENT,ACC0000000002,CUS00002,50000.0,true
```

### `data/transactions.txt`
Appended log of all transactions:
```
TXN1720000001,ACC0000000001,DEPOSIT,5000.0,15000.0,Deposit,2025-01-15T10:30:00
```

> ⚠️ The `data/` folder is excluded from git via `.gitignore` to protect sensitive information.

---

## 🔐 Security

| Feature | Implementation |
|---------|---------------|
| Password Hashing | SHA-256 one-way hash |
| Login Attempt Limit | Max 3 failed attempts before lockout |
| Input Validation | Email regex, phone regex, amount checks |
| Data Isolation | Each customer sees only their own accounts |

> 🔒 **Note**: For production use, passwords should be hashed with **BCrypt** or **PBKDF2** with a salt. SHA-256 is used here for educational simplicity.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java 17** | Core programming language |
| **Java Collections** | `HashMap`, `ArrayList` for in-memory data |
| **Java File I/O** | `BufferedReader`, `BufferedWriter`, `FileWriter` |
| **Java Time API** | `LocalDate`, `LocalDateTime` for timestamps |
| **Java Streams** | Filtering and sorting collections |
| **MessageDigest** | SHA-256 password hashing |
| **ANSI Escape Codes** | Colored terminal output |
| **Regex** | Email and phone validation |

---

## 📚 Java Concepts Covered

This project is a great reference for the following Java topics:

- ✅ **Classes & Objects** — Customer, Account, Transaction
- ✅ **Inheritance** — SavingsAccount and CurrentAccount extend Account
- ✅ **Abstraction** — Abstract `Account` class with abstract methods
- ✅ **Encapsulation** — Private fields, public getters/setters
- ✅ **Polymorphism** — `withdraw()` behaves differently per account type
- ✅ **Exception Handling** — Custom checked exceptions, try-catch-finally
- ✅ **Collections** — HashMap, ArrayList, Stream API
- ✅ **File I/O** — Reading and writing persistent text data
- ✅ **Enums** — `TransactionType` enum
- ✅ **String Formatting** — `String.format()`, `printf()`
- ✅ **Regex** — Pattern matching for validation
- ✅ **Hashing** — SHA-256 via `MessageDigest`
- ✅ **Java Time API** — `LocalDate`, `LocalDateTime`, `DateTimeFormatter`
- ✅ **Switch Expressions** — Modern Java switch with arrow syntax (Java 14+)

---

## 📸 Screenshots

### Welcome Banner
```
╔══════════════════════════════════════════════════════════╗
║          SMART BANK MANAGEMENT SYSTEM  v1.0              ║
║              Secure | Reliable | Modern                  ║
╚══════════════════════════════════════════════════════════╝
```

### Dashboard Menu
```
┌─ DASHBOARD — John Doe ──────────────────────────────────┐
  [1]  View My Accounts
  [2]  Open New Account
  [3]  Deposit Money
  [4]  Withdraw Money
  [5]  Transfer Money
  [6]  Transaction History
  [7]  My Profile
  [0]  Exit / Go Back
└───────────────────────────────────────────────────────────┘
```

### Account Summary
```
Account No   : ACC0000000001
Type         : Savings Account
Balance      : ₹15,000.00
Min Balance  : ₹500.00
Withdrawable : ₹14,500.00
Interest Rate: 4% p.a.
Withdrawals  : 2 / 5 free this month
Status       : Active
```

---

## 🔮 Future Enhancements

Here are features planned for future versions:

| Feature | Description |
|---------|-------------|
| 🗄️ **Database Integration** | Replace file storage with MySQL via JDBC |
| 🌐 **REST API** | Expose features via Spring Boot REST endpoints |
| 🎨 **JavaFX GUI** | Build a graphical desktop interface |
| 📧 **Email Alerts** | Send notifications on transactions via JavaMail |
| 🔒 **BCrypt Passwords** | Upgrade from SHA-256 to BCrypt hashing |
| 📊 **Monthly Statements** | Generate PDF statements using iText |
| 🏧 **ATM Simulation** | Simulate ATM withdrawals with PIN |
| 🧪 **Unit Tests** | Add JUnit 5 test coverage |
| 🌍 **Multi-Currency** | Support USD, EUR alongside INR |

---


---

## 👨‍💻 Author

**Your Name Here**

- GitHub: [@dnshprjpt8000](https://github.com/your-username)
- LinkedIn: [www.linkedin.com/in/dinesh-prajapat-21628a33b](https://linkedin.com/in/your-profile)
- Email: dinesh69534@gmail.com

---

<div align="center">



Made with ❤️ and Java

</div>
