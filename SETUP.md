# Bank Management System - Setup Guide

## Prerequisites

Before running the application, ensure you have:

1. **Java 17 or higher**
   - Check: `java -version`
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **Maven**
   - Check: `mvn -version`
   - Download: https://maven.apache.org/download.cgi

3. **Node.js 16 or higher**
   - Check: `node -version`
   - Download: https://nodejs.org/

## Quick Start (Windows)

### Option 1: Start Everything at Once
Double-click `start-all.bat` - This will start both backend and frontend automatically.

### Option 2: Start Separately
1. Double-click `start-backend.bat` to start the backend server
2. Double-click `start-frontend.bat` to start the frontend

## Manual Start

### Backend
```bash
cd backend
mvn spring-boot:run
```
Backend will run on: http://localhost:8080

### Frontend
```bash
cd frontend
npm install
npm start
```
Frontend will run on: http://localhost:3000

## Database Configuration

The application is configured to use **H2 in-memory database** by default (no setup required).

### To Switch to MySQL:

1. Ensure MySQL is installed and running
2. Create the database:
   ```sql
   CREATE DATABASE bank_db;
   ```

3. Edit `backend/src/main/resources/application.properties`:
   ```properties
   spring.profiles.active=mysql
   ```

4. Update MySQL credentials in `backend/src/main/resources/application-mysql.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## Accessing the Application

1. Open your browser
2. Go to: http://localhost:3000
3. Start creating accounts and performing transactions!

## Features

- ✅ Create bank accounts
- ✅ Deposit money
- ✅ Withdraw money
- ✅ Apply for loans
- ✅ Repay loans
- ✅ View all accounts
- ✅ Real-time balance updates

## Troubleshooting

### Backend won't start
- Ensure Java 17+ is installed: `java -version`
- Ensure Maven is installed: `mvn -version`
- Check if port 8080 is available

### Frontend won't start
- Ensure Node.js is installed: `node -version`
- Delete `node_modules` folder and run `npm install` again
- Check if port 3000 is available

### Database connection error
- If using MySQL, verify MySQL is running
- Check credentials in `application-mysql.properties`
- Default setup uses H2 (no MySQL needed)

## Project Structure

```
Bank-Management-System-/
├── backend/                 # Spring Boot REST API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/bank/
│   │   │   │   ├── controller/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   ├── service/
│   │   │   │   └── BankManagementApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── application-dev.properties (H2)
│   │   │       └── application-mysql.properties (MySQL)
│   │   └── pom.xml
│   └── target/
├── frontend/                # React Application
│   ├── public/
│   ├── src/
│   │   ├── App.js
│   │   ├── App.css
│   │   ├── index.js
│   │   └── index.css
│   └── package.json
├── start-all.bat           # Start both servers
├── start-backend.bat       # Start backend only
├── start-frontend.bat      # Start frontend only
└── README.md
```

## API Endpoints

- `POST /api/accounts` - Create new account
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/{id}` - Get account by ID
- `POST /api/accounts/{id}/deposit` - Deposit money
- `POST /api/accounts/{id}/withdraw` - Withdraw money
- `POST /api/accounts/{id}/loan` - Apply for loan
- `POST /api/accounts/{id}/repay` - Repay loan

## Support

For issues or questions, check the troubleshooting section above.
