# WorkNest – Enterprise Employee Management System

WorkNest is an **enterprise-grade Employee Management System** designed to streamline HR operations, department workflows, and employee activities within an organization.  
It goes beyond a basic CRUD application by incorporating **secure role-based access, real-time updates, and interactive features** to simulate real-world enterprise requirements.

---

## 🚀 Features

### 🔹 Employee & Department Management
- Add, update, and manage employees with department assignments.
- Role-based operations for **Admin, Super Admin, HR, and Employees**.
- Department-level management with access restrictions.

### 🔹 Secure Authentication & Authorization
- **Spring Security with role-based access control (RBAC)**.
- Secure session handling and password encryption.
- Access segregation (e.g., only Admin/Super Admin can add employees/departments).

### 🔹 Live Activity Feed (WebSockets)
- Real-time employee activities such as leave requests, approvals, and updates.
- Powered by **WebSockets**, ensuring immediate visibility of changes across sessions.
- Eliminates the need for manual refresh to see updates.

### 🔹 Integrated Chatbot (Global Access)
- A chatbot integrated across **all application pages** without code duplication.
- Provides interactive support and guides employees through system actions.
- Inspired by enterprise solutions, ensuring **seamless communication** within the app.

### 🔹 Additional Features
- Leave application & approval workflow.
- Audit-friendly activity logs.
- Responsive UI with modern dashboards.

---

## 🛠️ Tech Stack

| Layer               | Technology Used |
|----------------------|-----------------|
| **Frontend**        | Thymeleaf, HTML5, CSS3, Bootstrap, JavaScript (ES6) |
| **Backend**         | Spring Boot (REST APIs, Controllers, Services) |
| **Security**        | Spring Security (RBAC, authentication & authorization) |
| **AI**              | Ollama (llama3.2:latest) Model |
| **Database**        | MySQL with JPA/Hibernate ORM |
| **Real-Time Updates** | WebSockets for live activity feeds |
| **Chat Integration** | Reusable chatbot module with JavaScript & CSS |
| **Build Tool**      | Gradle / Maven |
| **Version Control** | Git & GitHub |

---

## ⚙️ Architecture

WorkNest follows a **layered architecture** ensuring scalability and maintainability:

- **Presentation Layer (UI)** → Thymeleaf templates + responsive CSS for views.  
- **Business Layer (Service)** → Spring Boot services handle logic for employees, departments, and activities.  
- **Persistence Layer (Repository)** → JPA repositories interact with MySQL database.  
- **Security Layer** → Spring Security with role-based access.  
- **Real-Time Layer** → WebSockets push live updates to connected clients.  
- **Chatbot Layer** → Global JS + CSS module integrated once, available in all pages.  

---

## 📊 Dashboard & Modules

- **Dashboard** – Overview of employees, active staff, pending requests, and leave status.  
- **Employee Management** – Add, edit, list employees with role/department filters.  
- **Department Management** – Add and list departments with admin-only controls.  
- **Leave Requests** – Apply, approve, or reject leave requests in real time.  
- **Activity Feed** – See what’s happening across the organization instantly.  
- **Profile Management** – View and edit employee profile details securely.  

---

## 🔒 Why WorkNest Stands Out

Unlike a traditional CRUD-based employee management project, WorkNest demonstrates **real enterprise-level skills**:

- **Security-first design** → Implemented Spring Security with RBAC.  
- **Modern async communication** → WebSocket-based activity feed.  
- **Reusable UI components** → Chatbot designed once and injected globally.  
- **Scalable architecture** → Clearly separated layers for maintainability.  

This makes WorkNest not just a project, but a **miniature enterprise solution** built with real-world practices.

---

## 📸 Screenshots

### Dashboard  
![Dashboard](screenshots/dashboard.png)

### Employee Requests with Live Updates  
![Employee Requests](screenshots/employee-requests.png)

### Integrated Chatbot  
![Chatbot](screenshots/chatbot.png)

---

## 🧑‍💻 Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/worknest.git
