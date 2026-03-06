# 🏦 Bank Management System - Full Stack

A modern, responsive full-stack banking application built with **Spring Boot** and **React**. Features a beautiful UI with smooth animations, comprehensive banking operations, and secure data management.

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green) ![React](https://img.shields.io/badge/React-18-blue) ![H2](https://img.shields.io/badge/Database-H2%2FMySQL-yellow) ![License](https://img.shields.io/badge/License-MIT-purple)

## 🌟 Features

### 💼 Banking Operations
- ✅ **Create Accounts** - Open new bank accounts with initial deposits
- 💰 **Deposit Money** - Add funds to any account
- 💸 **Withdraw Money** - Secure withdrawals with balance validation
- 🏦 **Loan Management** - Apply for loans and manage repayments
- 💳 **Loan Repayment** - Flexible loan repayment system
- 🗑️ **Close Accounts** - Permanently close accounts with confirmation

### 🎨 Modern UI/UX
- 📱 **Responsive Design** - Works on desktop, tablet, and mobile
- ✨ **Smooth Animations** - Fade-in, slide-up, and hover effects
- 🎭 **Interactive Icons** - Bouncing transaction icons with hover states
- 🌈 **Gradient Themes** - Beautiful purple-blue gradient design
- 🔔 **Modal Popups** - Clean transaction input modals
- 💫 **Glass Morphism** - Modern frosted glass effects

### 🔧 Technical Features
- 🚀 **Fast Performance** - Optimized React components and Spring Boot backend
- 💾 **Dual Database Support** - H2 (development) and MySQL (production)
- 🔒 **Input Validation** - Client and server-side validation
- 🌐 **RESTful API** - Clean REST endpoints with proper HTTP methods
- 📊 **Real-time Updates** - Instant balance and loan amount updates
- 🎯 **Error Handling** - Comprehensive error management

## 🚀 Quick Start

### Option 1: One-Click Start (Windows)
```bash
# Navigate to project folder
cd Bank-Management-System-

# Start everything at once
start-all.bat
```

### Option 2: Manual Start
```bash
# Terminal 1 - Backend
cd backend
mvn spring-boot:run

# Terminal 2 - Frontend  
cd frontend
npm install
npm start
```

**Access the application:**
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080

## 📋 Prerequisites

- ☕ **Java 17+** - [Download here](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven** - [Download here](https://maven.apache.org/download.cgi)
- 🟢 **Node.js 16+** - [Download here](https://nodejs.org/)

## 🏗️ Project Structure

```
Bank-Management-System-/
├── 🖥️ backend/                    # Spring Boot REST API
│   ├── src/main/java/com/bank/
│   │   ├── 🎮 controller/         # REST Controllers
│   │   ├── 📊 model/              # JPA Entities
│   │   ├── 🗄️ repository/         # Data Access Layer
│   │   ├── ⚙️ service/            # Business Logic
│   │   └── 🚀 BankManagementApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties          # Main config
│   │   ├── application-dev.properties      # H2 Database
│   │   └── application-mysql.properties    # MySQL config
│   └── pom.xml                    # Maven dependencies
├── 🌐 frontend/                   # React Application
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── App.js                 # Main React component
│   │   ├── App.css                # Styles & animations
│   │   ├── index.js               # React entry point
│   │   └── index.css              # Global styles
│   └── package.json               # NPM dependencies
├── 🚀 start-all.bat              # Launch everything
├── 🔧 start-backend.bat          # Backend only
├── 🎨 start-frontend.bat         # Frontend only
├── 📖 README.md                  # This file
├── ⚙️ SETUP.md                   # Detailed setup guide
├── 🚫 .gitignore                 # Git ignore rules
└── 📄 LICENSE                    # MIT License
```

## 🔌 API Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `POST` | `/api/accounts` | Create new account | `{holderName, initialDeposit}` |
| `GET` | `/api/accounts` | Get all accounts | - |
| `GET` | `/api/accounts/{id}` | Get account by ID | - |
| `POST` | `/api/accounts/{id}/deposit` | Deposit money | `{amount}` |
| `POST` | `/api/accounts/{id}/withdraw` | Withdraw money | `{amount}` |
| `POST` | `/api/accounts/{id}/loan` | Apply for loan | `{amount}` |
| `POST` | `/api/accounts/{id}/repay` | Repay loan | `{amount}` |
| `DELETE` | `/api/accounts/{id}` | Close account | - |

## 💾 Database Configuration

### Default: H2 In-Memory Database
No setup required! The application uses H2 database by default.

### Switch to MySQL:
1. **Install MySQL** and create database:
   ```sql
   CREATE DATABASE bank_db;
   ```

2. **Update configuration** in `backend/src/main/resources/application.properties`:
   ```properties
   spring.profiles.active=mysql
   ```

3. **Set credentials** in `application-mysql.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## 🎯 Usage Guide

### Creating Accounts
1. Click **"➕ Create Account"**
2. Enter account holder name
3. Set initial deposit amount (₹)
4. Click **"Create Account"**

### Managing Accounts
1. Click on any account card to view details
2. Use transaction icons for operations:
   - **💵 Deposit** - Add money to account
   - **💸 Withdraw** - Remove money (validates balance)
   - **🏦 Apply Loan** - Request loan (adds to balance)
   - **💳 Repay Loan** - Pay back loan amount

### Closing Accounts
1. Open account details
2. Click **"🗑️ Close Account"** (red button)
3. Confirm deletion in popup

## 🛠️ Development

### Backend Development
```bash
cd backend
mvn spring-boot:run
# Server runs on http://localhost:8080
```

### Frontend Development
```bash
cd frontend
npm start
# App runs on http://localhost:3000
```

### Building for Production
```bash
# Backend
cd backend
mvn clean package

# Frontend
cd frontend
npm run build
```

## 🐛 Troubleshooting

### Common Issues

**Backend won't start:**
- ✅ Verify Java 17+ installed: `java -version`
- ✅ Check Maven installed: `mvn -version`
- ✅ Ensure port 8080 is free

**Frontend won't start:**
- ✅ Verify Node.js installed: `node -version`
- ✅ Delete `node_modules` and run `npm install`
- ✅ Check port 3000 is available

**Database connection error:**
- ✅ Default H2 database requires no setup
- ✅ For MySQL: verify server is running and credentials are correct

**CORS errors:**
- ✅ Backend includes CORS configuration for localhost:3000
- ✅ Ensure both servers are running on correct ports

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Spring Boot** - For the robust backend framework
- **React** - For the dynamic frontend library
- **H2 Database** - For easy development database
- **Maven** - For dependency management
- **NPM** - For frontend package management

## 📞 Support

If you encounter any issues or have questions:

1. Check the [SETUP.md](SETUP.md) for detailed setup instructions
2. Review the troubleshooting section above
3. Open an issue on GitHub

---

**Made with ❤️ for modern banking solutions**
