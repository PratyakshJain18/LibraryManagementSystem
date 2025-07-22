# 📚 Library Management System (Java + JDBC)

A simple CLI-based library system using Java and MySQL with role-based access and modular architecture.

---

## 🔑 Authentication & User Roles
- **Register/Login** users
- **Admin Role**: Full access (add/update/delete books)
- **User Role**: Limited access (view, borrow, return books)

---

## 📘 Book Management Features
- View all books in a formatted table
- Add a new book *(Admin only)*
- Update book title or author *(Admin only)*
- Delete a book *(Admin only)*
- Borrow a book with availability check
- Return a borrowed book

---

## 🧩 Project Structure
- `auth/` → Login/Register logic & role verification
- `dao/` → Handles all DB operations
- `controller/` → controls whether the user is admin or a normal user
- `view/` → User interaction via console
- `main/` → Entry point of the application

---

## 🛠 Tech Used
- Java
- JDBC
- MySQL

---

## 🚀 Setup Instructions
1. Clone this repo
2. Open in any IDE (IntelliJ, Eclipse, etc.)
3. Create the MySQL database and required tables
4. Run main.java

---

## 👨‍💻 Author

**Pratyaksh Jain**  
_Pre-1st year, NIT Prayagraj_ 
