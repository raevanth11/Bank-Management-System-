# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-03-05

### Added
- **Full-Stack Architecture**: Complete Spring Boot backend with React frontend
- **Account Management**: Create, view, and close bank accounts
- **Transaction Operations**: 
  - Deposit money with real-time balance updates
  - Withdraw money with insufficient funds validation
  - Apply for loans with automatic balance addition
  - Repay loans with overpayment handling
- **Modern UI/UX**:
  - Responsive design with gradient themes
  - Smooth animations and hover effects
  - Interactive transaction icons with modal popups
  - Glass morphism design elements
- **Database Support**: 
  - H2 in-memory database for development
  - MySQL support for production
  - Automatic schema generation
- **Security Features**:
  - Input validation on client and server
  - CORS configuration for secure API access
  - Confirmation dialogs for destructive actions
- **Developer Experience**:
  - One-click startup scripts for Windows
  - Comprehensive documentation
  - Easy environment switching
  - Hot reload for development

### Technical Features
- **Backend**: Spring Boot 3.2, Spring Data JPA, Maven
- **Frontend**: React 18, Axios, Modern CSS
- **Database**: H2/MySQL with JPA entities
- **API**: RESTful endpoints with proper HTTP methods
- **Build**: Maven for backend, NPM for frontend

### Documentation
- Complete README with setup instructions
- Detailed SETUP.md for troubleshooting
- API documentation with endpoint descriptions
- Contributing guidelines
- MIT License

## [0.1.0] - Initial Console Version

### Added
- Basic console-based banking system
- Account creation and management
- Simple transaction operations
- MySQL database integration
- Command-line interface

---

**Legend:**
- `Added` for new features
- `Changed` for changes in existing functionality
- `Deprecated` for soon-to-be removed features
- `Removed` for now removed features
- `Fixed` for any bug fixes
- `Security` for vulnerability fixes