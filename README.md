# EcoDriveIQ 🚗📊  
**Intelligent Automotive Data Analytics Platform**

## 🌐 Overview
EcoDriveIQ is an innovative web-based platform designed to offer **real-time insights** into a vehicle's performance, fuel efficiency, emission patterns, and predictive maintenance. It aims to promote **eco-conscious driving** through intuitive dashboards, smart alerts, and comparative analytics.

---

## 📌 Problem Statement
With increasing fuel costs and strict environmental norms, vehicle owners often lack **comprehensive tools** to monitor and optimize their driving behavior. Existing platforms offer **limited or static metrics** without actionable insights or predictive intelligence. EcoDriveIQ bridges this gap with a unified platform offering **real-time, data-driven decision support**.

---

## 🎯 Objectives
- 📈 Simulate and analyze **real-time vehicle data** (e.g., speed, fuel, emissions).
- 💡 Generate an **EcoScore** based on driving efficiency and emission behavior.
- 🔧 Predict **maintenance alerts** such as low mileage, brake wear, and more.
- 🔍 Enable **vehicle-to-vehicle comparisons** for performance evaluation.
- 💻 Develop a **responsive web app** using modern technologies.

---

## ⚙️ Features

### 👤 User Management
- Register, login, update profile, delete account

### 🚘 Vehicle Management
- Add, update, and delete vehicles with detailed attributes

### 📊 Data Visualization
- Real-time simulation of vehicle metrics (speed, fuel, CO₂, engine temp)
- Interactive dashboards with **Chart.js** graphs and performance trends

### 🔔 Predictive Alerts
- Smart alerts for high emissions, poor mileage, maintenance needs

### 📉 Performance Comparison
- Compare multiple vehicles using dynamic, visual insights

---

## 🧰 Tech Stack

### Backend
| Technology      | Purpose |
|-----------------|---------|
| Java (JDK 17)   | Core backend logic |
| Spring Boot     | RESTful services, dependency management |
| Hibernate       | ORM for mapping Java entities to DB |
| PostgreSQL      | Relational database for persistent storage |
| Maven           | Build and dependency management |
| Lombok          | Reduces boilerplate code |

### Frontend
| Technology      | Purpose |
|-----------------|---------|
| HTML5, CSS3     | UI structure and styling |
| JavaScript (ES6)| Client-side interactivity |
| Bootstrap 5     | Responsive and mobile-first design |
| Chart.js        | Real-time visualizations |
| jQuery (optional)| Simplified DOM manipulations |

### Tools
| Tool           | Use |
|----------------|-----|
| Postman        | API testing |
| pgAdmin        | PostgreSQL administration |
| IntelliJ IDEA  | Backend IDE |
| VS Code + Live Server | Frontend testing |
| Git            | Version control |

---

## 🗂️ Project Structure

```bash
EcoDriveIQ/
│
├── backend/                 # Spring Boot backend
│   └── src/main/java/com/p2/
│       ├── controller/      # REST API controllers
│       ├── dto/             # Data Transfer Objects
│       ├── entity/          # JPA entities
│       ├── repository/      # Repositories (Spring Data JPA)
│       ├── service/         # Business logic
│       └── util/            # Utility classes
│
├── frontend/                # Web UI
│   ├── index.html           # Login / Landing Page
│   ├── register.html        # Register Page
│   ├── dashboard.html       # Performance Dashboard
│   ├── profile.html         # Profile Management
│   ├── vehicle.html         # Vehicle Management
│   ├── compare.html         # Vehicle Comparison
│   └── js/                  # JavaScript logic
│
├── database/
│   └── schema.sql           # PostgreSQL schema
│
├── pom.xml                  # Maven config
└── README.md                # Project documentation
## ✅ Functional Requirements

### 👤 User
- Register / Login  
- Update Profile  
- Delete Account  

### 🚗 Vehicle
- Add / Update / Delete Vehicle  
- Link to user  

### 📊 Dashboard
- Simulate metrics like speed, fuel consumption, temperature, CO₂  
- Display insights and graphs  

### 🔔 Alert System
- Predictive alerts based on thresholds  

### 📈 Comparison
- Compare EcoScores and efficiency across vehicles  

---

## 📈 Non-Functional Requirements
- Responsive and mobile-friendly UI  
- Secure password handling  
- Scalable and modular backend  
- Real-time simulation support  
- Maintainable codebase using MVC pattern  

---

## 🎯 Target Users
- Individual car owners  
- Eco-conscious drivers  
- Auto-tech enthusiasts  

---

## 🌿 Benefits
- Promote green and sustainable driving habits  
- Get alerts before critical issues arise  
- Simplify maintenance and cost-saving decisions  
- Engage users with visual, interactive analytics  

---

## 📬 Contact
**Tejas Bhame**  
📧 bhametejas49@gmail.com  
📍 Pune, Maharashtra, India  

---

## 📌 License
This project is for **academic and demonstrative purposes only**.
