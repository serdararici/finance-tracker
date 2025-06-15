# 💸 Personal Finance Tracker – Java Console App

This is a **console-based personal finance management application** developed using **Java**. The main goal of this project was to **reinforce my object-oriented programming (OOP) knowledge**, gain hands-on experience with **file operations**, and practice working with **Java collections**, **date-time API**, and **unit testing with JUnit 5**.

---

## 🎯 Project Motivation

As a recent graduate in Computer Engineering, I wanted to build a hands-on, real-world inspired project to solidify the core backend concepts I learned through my personal studies, such as the Harvard CS50 course, and other online resources. Instead of just reading theory, I challenged myself to build something practical, interactive, and useful, even if it's a console application.

### Through this project, I aimed to:
- Improve my Java fundamentals and OOP design skills
- Learn more about working with file I/O (reading/writing .txt files)
- Practice **designing and organizing classes** effectively (SOLID principles)
- Understand how to write and run **unit tests** using **JUnit 5**
- Simulate a realistic project workflow (commits, modular development, testing, etc.)

---

## 🛠 Technologies & Concepts Used

| Technology/Concept     | Purpose                             |
|------------------------|-------------------------------------|
| Java 17                  | Main programming language         |
| Object-Oriented Design | Inheritance, Polymorphism, Abstraction |
| Java I/O API           | File reading and writing (`BufferedReader`, `BufferedWriter`) |
| Java Time API          | Parsing and formatting dates        |
| Java Collections       | `ArrayList`, `List`                 |
| Enums                  | Modeling expense categories         |
| JUnit 5                | Unit testing                        |
| IntelliJ IDEA          | Development environment             |

---

## 📁 Project Structure

<pre> ### 📁 Project Structure 
FinanceTracker/
├── src/ 
│   ├── Main.java
│   ├── FinanceManager.java
│   ├── Transaction.java
│   ├── Income.java
│   ├── Expense.java
│   ├── TransactionFileManager.java
│   └── ExpenseCategoryEnum.java
│
├── test/
│   ├── FinanceManagerTest.java
│   ├── IncomeTest.java
│   └── ExpenseTest.java
│
├── transactions.txt
└── README.md
 ``` </pre>

 ---

## 🚀 Features

- ✅ Add income or expense transactions
- 🗂️ Categorize expenses by type (FOOD, TRANSPORT, RENT, etc.)
- 📆 Filter transactions by **custom date range**
- 🔍 View total income, total expense, and remaining balance
- 🧹 Delete specific transactions
- 💾 Store and retrieve transactions from `transactions.txt`
- ✅ Unit testing with **JUnit 5**

---

## 📸 Application in Action
Below are examples from the application, demonstrating each main functionality step-by-step:

## 1️⃣ Add Income
You can add a new income entry by entering the amount, date, and description.

![Add Income Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/1.JPG)

## 2️⃣ Add Expense
You can add an expense with amount, date, description, and category (e.g., FOOD, RENT, etc.).

![Add Expense Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/2.JPG)

## 3️⃣ View All Transactions
Displays all incomes and expenses with their relevant details.

![View All Transactions Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/3.PG.JPG)

## 4️⃣ View Summary
Shows the total income, total expense, and balance.

![View Summary Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/4.JPG)

## 5️⃣ Filter Transactions
You can filter your transactions by date, type  or by category.

![Filter Transactions Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/5.JPG)

## 6️⃣ Delete a Transaction
Enter the index of the transaction to delete it permanently.

![Delete a Transaction Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/6.JPG)

## 0️⃣ Exit
Exits the application safely, saving all transactions.

![Exit Screenshot](https://github.com/serdararici/finance-tracker/blob/main/images/0.JPG)

> 💡 All data is saved to a file and automatically loaded when you restart the application.


---

## 🧪 Tests
Unit tests are included in the test directory and written using JUnit 5. The following functionalities are tested:

1. Income and Expense creation

2. Adding transactions

3. Calculating total income and expenses

---

## 🧪 How to Run the Project

### Prerequisites
- Java SDK 17+
- IntelliJ IDEA (or your favorite IDE)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/serdararici/personal-finance-tracker.git
2. Open it in IntelliJ IDEA or another IDE as a Java project.

3. Run the Main.java class to start the program.

---

## 👤 About Me
Hi, I'm Serdar Arıcı, a recent Computer Engineering graduate from Sakarya University. I enjoy working on both backend and mobile development. This project helped me consolidate my Java knowledge and improve my hands-on skills in:

- Object-oriented programming

- Software design and structuring code

- Writing maintainable and testable code

📧 serdararici3@gmail.com

