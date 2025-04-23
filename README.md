# 🛠️ Issue Tracker System (Java + MySQL)

A lightweight, console-based **Issue Tracker System** built with **Core Java** and **MySQL**. Inspired by tools like Jira, it streamlines project tracking and bug reporting with a clean **layered architecture** and seamless **JDBC** integration.

## 🚀 Overview

The **Issue Tracker System** is a Java-powered CLI app that simplifies managing users, issues, and comments for project or bug tracking. Backed by **MySQL**, it ensures robust data persistence and efficient workflows.

**What It Does:**

- **User Management**: Create and list users with `name` and `email`.
- **Issue Tracking**: Add issues, set `status` (`OPEN`, `IN_PROGRESS`, `RESOLVED`, `CLOSED`) and `priority` (`HIGH`, `MEDIUM`, `LOW`), and assign to users.
- **Comments**: Attach comments to issues for team updates or discussions.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Setup Instructions](#-setup-instructions)
- [Database Schema & Sample Data](#-database-schema--sample-data)
- [Modules Breakdown](#-modules-breakdown)
- [Sample Outputs](#-sample-outputs)
- [Technical Highlights](#-technical-highlights)

## 🌟 Features

- ➕ **Create, update, delete issues** with flexible details
- 🔄 **Manage issue status**: `OPEN`, `IN_PROGRESS`, `RESOLVED`, `CLOSED`
- 👨‍💻 **Assign developers** via `assignee_id`
- 💬 **Add/view comments** with automatic timestamps
- 🔍 **Filter issues** by `ID`, `status`, `priority`, or `assignee`
- 🧱 **Layered Architecture**: Model → Repository → Service → CLI
- 💾 **Persistent storage** with **MySQL** and **JDBC**
- 🚀 **Scalable design** for cloud or API expansion

## 🛠 Tech Stack

- **Language**: Java (Core Java, no frameworks)
- **Database**: MySQL 8.0
- **Database Access**: JDBC with MySQL Connector/J
- **Architecture**: Layered (Model → Repository → Service → CLI)
- **Concepts Implemented**:
  - **OOP**: Encapsulation, Abstraction, Composition
  - **JDBC**: Secure queries, connection management
  - **Exception Handling**: Robust error recovery
  - **Collections**: `ArrayList` for dynamic data
  - **Low-Level Design**: Modular, maintainable structure

## 📁 Project Structure

```plaintext
IssueTracker/
├── src/
│   ├── com.IssueTracker.models/         # 🧩 Model Layer (POJOs)
│   │   ├── User.java                    # Represents a user in the system
│   │   ├── Issue.java                   # Represents an issue/bug/task
│   │   ├── Comment.java                 # Represents a comment on an issue
│
│   ├── com.IssueTracker.repository/     # 💾 Repository Layer (DB Operations)
│   │   ├── UserRepository.java          # Handles CRUD operations for users
│   │   ├── IssueRepository.java         # Handles CRUD + filters for issues
│   │   ├── CommentRepository.java       # Handles comments for each issue
│
│   ├── com.IssueTracker.service/        # ⚙️ Service Layer (Business Logic)
│   │   ├── UserService.java             # Logic for managing users
│   │   ├── IssueService.java            # Logic for managing issues & comments
│
│   ├── com.IssueTracker.utils/          # 🛠️ Utility Layer
│   │   ├── DBConnection.java            # JDBC connection helper for MySQL
│
│   ├── com.IssueTracker.runner/         # 🚀 Application Entry Point
│   │   ├── Main.java                    # Main CLI application
│
├── README.md                            # 📘 Project Documentation
```

---

## ⚙️ Setup Instructions

1. **Install MySQL**:

   - Set up **MySQL Server** and **Workbench**.
   - Create a database named `issue_tracker`.
   - Update `DBConnection.java` with your credentials:

     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/issue_tracker?useSSL=false&serverTimezone=UTC";
     private static final String USER = "root";
     private static final String PASSWORD = "mysqlpassword";

     # ✅ Issue Tracker Project: MySQL Setup & Sample Data
     ```

---

## 📂 Step 2: Create Database Schema

```sql
CREATE DATABASE issue_tracker;
USE issue_tracker;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE issues (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    assignee_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (assignee_id) REFERENCES users(id)
);

CREATE TABLE comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    issue_id INT,
    author VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (issue_id) REFERENCES issues(id)
);
```

---

## ⚙️ Step 3: Add MySQL JDBC Driver

If you're **not using Maven or Gradle**, you can manually add the MySQL JDBC `.jar` file to your project.

### ✅ Manual Setup

1. **Download the JAR**  
   [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

2. **Place it in your project**  
   Move the downloaded `.jar` (e.g., `mysql-connector-java-8.0.33.jar`) into a `lib/` directory in your project.

## ▶️ Step 4: Compile and Run the Project

**Compile and run with classpath**  
 Use the following commands to compile and run your Java application with the JDBC driver:

```bash
javac -cp ".;lib/mysql-connector-java-8.0.33.jar" Main.java
java -cp ".;lib/mysql-connector-java-8.0.33.jar" Main
```

---

## 🧠 Database Schema Overview

### `users` table

| Column | Type    | Description        |
| ------ | ------- | ------------------ |
| id     | INT     | Primary key        |
| name   | VARCHAR | User's full name   |
| email  | VARCHAR | User email address |

---

### `issues` table

| Column      | Type      | Description                       |
| ----------- | --------- | --------------------------------- |
| id          | INT       | Primary key                       |
| title       | VARCHAR   | Issue title                       |
| description | TEXT      | Detailed description              |
| status      | VARCHAR   | OPEN, IN_PROGRESS, RESOLVED, etc. |
| priority    | VARCHAR   | LOW, MEDIUM, HIGH                 |
| assignee_id | INT       | Foreign key to `users(id)`        |
| created_at  | TIMESTAMP | Auto-created timestamp            |
| updated_at  | TIMESTAMP | Auto-updated on modification      |

---

### `comments` table

| Column     | Type      | Description                 |
| ---------- | --------- | --------------------------- |
| id         | INT       | Primary key                 |
| issue_id   | INT       | Foreign key to `issues(id)` |
| author     | VARCHAR   | Name of comment writer      |
| content    | TEXT      | The comment text            |
| created_at | TIMESTAMP | When the comment was made   |

---

## 🧪 Sample Data

### 👤 `users`

```sql
SELECT * FROM users;
```

```
+----+----------+----------------------+
| id | name     | email                |
+----+----------+----------------------+
|  1 | Chenna   | chenna@example.com   |
|  2 | Dana     | dana@example.com     |
|  3 | Khabib   | khabib@example.com   |
|  4 | Connor   | connor@example.com   |
|  5 | Illi     | illi@example.com     |
|  6 | Volk     | volk@example.com     |
|  7 | makachev | makachev@example.com |
|  8 | dricus   | dricus@example.com   |
|  9 | chimaev  | chimaev@example.com  |
| 10 | dustin   | dustin@example.com   |
+----+----------+----------------------+
```

---

### 🐞 `issues`

```sql
SELECT * FROM issues;
```

```
+----+---------------------------+------------------------------------+-------------+----------+---------------------+---------------------+-------------+
| id | title                     | description                        | status      | priority | created_at          | updated_at          | assignee_id |
+----+---------------------------+------------------------------------+-------------+----------+---------------------+---------------------+-------------+
|  1 | Bug in Login              | Login fails with 500               | OPEN        | HIGH     | 2025-04-22 01:48:29 | 2025-04-23 15:08:10 |           1 |
|  2 | UI Overlap                | Home screen buttons overlap        | RESOLVED    | MEDIUM   | 2025-04-22 01:48:29 | 2025-04-23 15:08:23 |           2 |
|  3 | App Crash                 | Home screen not launching          | IN_PROGRESS | HIGH     | 2025-04-22 01:48:29 | 2025-04-23 15:08:53 |           3 |
|  5 | cors issue                | can't fetch the data               | CLOSED      | HIGH     | 2025-04-22 01:55:17 | 2025-04-22 14:16:08 |           7 |
|  6 | appinstalld component bug | can't able to install flutter apps | IN_PROGRESS | MEDIUM   | 2025-04-22 14:13:05 | 2025-04-23 15:07:42 |           1 |
|  8 | sam app lauch error       | sam can't able to lauch home app   | IN_PROGRESS | LOW      | 2025-04-23 15:05:39 | 2025-04-23 15:10:29 |          10 |
+----+---------------------------+------------------------------------+-------------+----------+---------------------+---------------------+-------------+
```

---

### 💬 `comments`

```sql
SELECT * FROM comments;
```

```
+----+----------+--------+---------------------------------------------------+---------------------+
| id | issue_id | author | content                                           | created_at          |
+----+----------+--------+---------------------------------------------------+---------------------+
|  1 |        2 | Chenna | issue should be fixed by eod                      | 2025-04-23 15:05:21 |
|  2 |        1 | illi   | give me the status of the bug by eod              | 2025-04-23 15:11:36 |
|  3 |        6 | dana   | prepare a report on the issue why it is happening | 2025-04-23 15:15:24 |
|  4 |        5 | dustin | fix the cors error asap                           | 2025-04-23 15:22:59 |
+----+----------+--------+---------------------------------------------------+---------------------+
```

---

# 🧪 Sample Outputs

Showcases key interactions using the database state.

## Main Menu

--- Issue Tracker ---

1. **Create User**
2. **View All Users**
3. **Create Issue**
4. **View All Issues**
5. **Update Issue**
6. **Delete Issue**
7. **Filter Issues by Status**
8. **Filter Issues by Priority**
9. **Filter Issues by Assigned User**
10. **Add Comment to Issue**
11. **View Comments of an Issue**
12. **Exit**

Choose option:

---

## Create User

Choose option: **1**  
**Enter name**: Alex  
**Enter email**: alex@example.com  
**User created!**

---

## View All Users

Choose option: **2**  
Here are the current users:

- **User 1**:

  - **ID**: 1
  - **Name**: Chenna
  - **Email**: chenna@example.com

- **User 2**:

  - **ID**: 2
  - **Name**: Dana
  - **Email**: dana@example.com

- **User 3**:

  - **ID**: 3
  - **Name**: Khabib
  - **Email**: khabib@example.com

- **User 4**:

  - **ID**: 4
  - **Name**: Connor
  - **Email**: connor@example.com

- **User 5**:

  - **ID**: 5
  - **Name**: Illi
  - **Email**: illi@example.com

- **User 6**:

  - **ID**: 6
  - **Name**: Volk
  - **Email**: volk@example.com

- **User 7**:

  - **ID**: 7
  - **Name**: makachev
  - **Email**: makachev@example.com

- **User 8**:

  - **ID**: 8
  - **Name**: dricus
  - **Email**: dricus@example.com

- **User 9**:

  - **ID**: 9
  - **Name**: chimaev
  - **Email**: chimaev@example.com

- **User 10**:

  - **ID**: 10
  - **Name**: dustin
  - **Email**: dustin@example.com

- **User 11**:
  - **ID**: 11
  - **Name**: Alex
  - **Email**: alex@example.com

---

## Create Issue

Choose option: **3**  
**Enter title**: Payment Gateway Failure  
**Enter description**: Transactions failing with timeout  
**Enter status** (e.g., OPEN, IN_PROGRESS, RESOLVED, CLOSED): OPEN  
**Enter priority** (e.g., High, Medium, Low): HIGH  
**Enter assigned user ID**: 4  
**Issue created!**

---

## View All Issues

Choose option: **4**  
Here are the current issues:

- **Issue 1**:

  - **ID**: 1
  - **Title**: Bug in Login
  - **Description**: Login fails with 500
  - **Status**: OPEN
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T15:08:10
  - **Assignee ID**: 1

- **Issue 2**:

  - **ID**: 2
  - **Title**: UI Overlap
  - **Description**: Home screen buttons overlap
  - **Status**: RESOLVED
  - **Priority**: MEDIUM
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T15:08:23
  - **Assignee ID**: 2

- **Issue 3**:

  - **ID**: 3
  - **Title**: App Crash
  - **Description**: Home screen not launching
  - **Status**: IN_PROGRESS
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T15:08:53
  - **Assignee ID**: 3

- **Issue 5**:

  - **ID**: 5
  - **Title**: cors issue
  - **Description**: can't fetch the data
  - **Status**: CLOSED
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:55:17
  - **Updated At**: 2025-04-22T14:16:08
  - **Assignee ID**: 7

- **Issue 6**:

  - **ID**: 6
  - **Title**: appinstalld component bug
  - **Description**: can't able to install flutter apps
  - **Status**: IN_PROGRESS
  - **Priority**: MEDIUM
  - **Created At**: 2025-04-22T14:13:05
  - **Updated At**: 2025-04-23T15:07:42
  - **Assignee ID**: 1

- **Issue 8**:

  - **ID**: 8
  - **Title**: sam app launch error
  - **Description**: sam can't able to launch home app
  - **Status**: IN_PROGRESS
  - **Priority**: LOW
  - **Created At**: 2025-04-23T15:05:39
  - **Updated At**: 2025-04-23T15:10:29
  - **Assignee ID**: 10

- **Issue 9**:
  - **ID**: 9
  - **Title**: Payment Gateway Failure
  - **Description**: Transactions failing with timeout
  - **Status**: OPEN
  - **Priority**: HIGH
  - **Created At**: 2025-04-23T16:00:00
  - **Updated At**: 2025-04-23T16:00:00
  - **Assignee ID**: 4

---

## Add Comment

Choose option: **10**  
**Enter issue ID**: 1  
**Enter your name**: Khabib  
**Enter comment**: Need server logs to debug  
**Comment added!**

---

## View Comments

Choose option: **11**  
**Enter issue ID**: 1  
Here are the comments for this issue:

- **Comment 1**:

  - **ID**: 2
  - **Author**: Illi
  - **Content**: give me the status of the bug by eod

- **Comment 2**:
  - **ID**: 5
  - **Author**: Khabib
  - **Content**: Need server logs to debug

---

## Update Issue

Choose option: **5**  
**Enter issue ID to update**: 1  
**Enter new title** (leave blank to keep same):  
**Enter new description** (leave blank to keep same):  
**Enter new status** (e.g., OPEN, IN_PROGRESS, RESOLVED, CLOSED, leave blank to keep same): IN_PROGRESS  
**Enter new priority** (e.g., High, Medium, Low, leave blank to keep same):  
**Enter new assignee ID** (leave blank to keep same): 5  
**Issue updated!**

---

## Filter Issues by Status

Choose option: **7**  
**Enter status to filter**: IN_PROGRESS  
Here are the issues with the status "IN_PROGRESS":

- **Issue 1**:

  - **ID**: 1
  - **Title**: Bug in Login
  - **Description**: Login fails with 500
  - **Status**: IN_PROGRESS
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T16:05:00
  - **Assignee ID**: 5

- **Issue 2**:

  - **ID**: 3
  - **Title**: App Crash
  - **Description**: Home screen not launching
  - **Status**: IN_PROGRESS
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T15:08:53
  - **Assignee ID**: 3

- **Issue 3**:

  - **ID**: 6
  - **Title**: appinstalld component bug
  - **Description**: can't able to install flutter apps
  - **Status**: IN_PROGRESS
  - **Priority**: MEDIUM
  - **Created At**: 2025-04-22T14:13:05
  - **Updated At**: 2025-04-23T15:07:42
  - **Assignee ID**: 1

- **Issue 4**:
  - **ID**: 8
  - **Title**: sam app launch error
  - **Description**: sam can't able to launch home app
  - **Status**: IN_PROGRESS
  - **Priority**: LOW
  - **Created At**: 2025-04-23T15:05:39
  - **Updated At**: 2025-04-23T15:10:29
  - **Assignee ID**: 10

---

## Filter Issues by Priority

Choose option: **8**  
**Enter priority to filter**: HIGH  
Here are the issues with the priority "HIGH":

- **Issue 1**:

  - **ID**: 1
  - **Title**: Bug in Login
  - **Description**: Login fails with 500
  - **Status**: OPEN
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T15:08:10
  - **Assignee ID**: 1

- **Issue 2**:
  - **ID**: 9
  - **Title**: Payment Gateway Failure
  - **Description**: Transactions failing with timeout
  - **Status**: OPEN
  - **Priority**: HIGH
  - **Created At**: 2025-04-23T16:00:00
  - **Updated At**: 2025-04-23T16:00:00
  - **Assignee ID**: 4

---

## Filter Issues by Assigned User

Choose option: **9**  
**Enter user ID to filter**: 5  
Here are the issues assigned to user 5:

- **Issue 1**:
  - **ID**: 1
  - **Title**: Bug in Login
  - **Description**: Login fails with 500
  - **Status**: OPEN
  - **Priority**: HIGH
  - **Created At**: 2025-04-22T01:48:29
  - **Updated At**: 2025-04-23T15:08:10
  - **Assignee ID**: 5

---

## 📈 Technical Highlights

### 🧱 OOP Principles

- **Encapsulation**: Private fields with public getters/setters.
- **Composition**: `Issue` contains a list of `Comment` objects.
- **Abstraction**: Repository and Service layers hide implementation details.

### 🛢️ JDBC & MySQL

- **Secure Queries**: Used `PreparedStatement` to prevent SQL injection.
- **Resource Management**: Applied `try-with-resources` to close connections.
- **ID Handling**: Used `RETURN_GENERATED_KEYS` for auto-incremented IDs.

### ⚠️ Exception Handling

- **Repository Layer**: Catches and logs `SQLException` precisely.
- **Service Layer**: Validates inputs and handles invalid updates cleanly.

### 📚 Collections Usage

- Used `ArrayList` for efficient dynamic storage and in-memory filtering.

### ⏱️ Time Management

- Used `LocalDateTime` to map with MySQL `TIMESTAMP` for precision tracking.

### 🎯 Design Patterns

- **Repository Pattern**: Isolates data access logic.
- **Service Pattern**: Centralizes business rules and validation.
- **Singleton-like**: `DBConnection` ensures single connection pool management.

### 🚀 Scalability

- **Modular Design**: Easy to upgrade to REST API or migrate to cloud setup.
- **Optimized Schema**: Uses foreign keys and indexes for efficient querying.

---

## 🤝 Contributing

Contributions are welcome!  
If you'd like to **suggest a feature**, **fix a bug**, or **improve the documentation**:

- Fork this repository
- Create a new branch (`git checkout -b feature/your-feature-name`)
- Commit your changes with clear messages
- Open a Pull Request (PR) describing your changes

For any questions or discussions, feel free to open an [Issue](https://github.com/your-repo/issues) or connect with me directly.

📬 **Contact**: [kckr118@gmail.com](mailto:kckr118@gmail.com)

---

## 🧩 UML Diagram

The UML class diagram helps visualize the system structure and relationships between classes (User, Issue, Comment, etc.).  
You can find it here:

![Issue Tracker System_UML Class Diagram](https://github.com/user-attachments/assets/fc5fadc6-7afe-47b2-b9c8-080d96c1be97)
