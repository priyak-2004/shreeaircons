# System Architecture - Shree Air Cons

## Overview

Shree Air Cons is a modern, full-stack web application built with Spring Boot, MySQL, and Bootstrap. The application follows a layered architecture pattern with clear separation of concerns.

## Architecture Diagram

```
┌─────────────────────────────────────────────┐
│          Client Layer (Browser)             │
│  HTML | CSS | JavaScript | Bootstrap 5     │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│      Presentation Layer (REST APIs)         │
│  Controllers | Requests | Responses         │
│  - HomeController                          │
│  - BookingController                       │
│  - ReviewController                        │
│  - GalleryController                       │
│  - AdminRestController                     │
│  - AuthController                          │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│      Business Logic Layer (Services)        │
│  Business Rules | Processing Logic         │
│  - BookingService                          │
│  - ReviewService                           │
│  - GalleryService                          │
│  - UserService                             │
│  - NotificationService                     │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│     Data Access Layer (Repositories)        │
│  Database Query Interface (JPA)             │
│  - BookingRepository                       │
│  - ReviewRepository                        │
│  - GalleryRepository                       │
│  - UserRepository                          │
│  - ServiceTypeRepository                   │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│      Database Layer (MySQL)                 │
│  - users                                   │
│  - bookings                                │
│  - reviews                                 │
│  - gallery                                 │
│  - service_types                           │
└─────────────────────────────────────────────┘
```

## Layered Architecture

### 1. **Client Layer** (Frontend)
- **Technology**: HTML5, CSS3, JavaScript, Bootstrap 5
- **Location**: `src/main/resources/templates/`, `src/main/resources/static/`
- **Components**:
  - HTML Templates (Thymeleaf)
  - CSS Stylesheets
  - JavaScript Utilities
  - Bootstrap Framework

### 2. **Presentation Layer** (API Controllers)
- **Technology**: Spring MVC
- **Location**: `src/main/java/.../controller/`
- **Responsibilities**:
  - Handle HTTP requests
  - Validate input parameters
  - Return JSON responses
  - Route requests to services
- **Key Classes**:
  - `HomeController` - View rendering
  - `BookingController` - Booking APIs
  - `ReviewController` - Review APIs
  - `GalleryController` - Gallery APIs
  - `AdminRestController` - Admin APIs
  - `AuthController` - Authentication

### 3. **Business Logic Layer** (Services)
- **Technology**: Spring Services
- **Location**: `src/main/java/.../service/`
- **Responsibilities**:
  - Business rule enforcement
  - Data validation
  - Notification handling
  - Complex calculations
- **Key Classes**:
  - `BookingService` - Booking management
  - `ReviewService` - Review processing
  - `GalleryService` - Image management
  - `UserService` - User management
  - `NotificationService` - WhatsApp notifications

### 4. **Data Access Layer** (Repositories)
- **Technology**: Spring Data JPA
- **Location**: `src/main/java/.../repository/`
- **Responsibilities**:
  - Database query abstraction
  - CRUD operations
  - Custom queries
  - Data persistence
- **Key Interfaces**:
  - `BookingRepository` - Booking data access
  - `ReviewRepository` - Review data access
  - `GalleryRepository` - Gallery data access
  - `UserRepository` - User data access
  - `ServiceTypeRepository` - Service type data access

### 5. **Database Layer** (MySQL)
- **Technology**: MySQL 8.0+
- **Database**: `shreeaircons`
- **Tables**:
  - `users` - Admin users
  - `bookings` - Service bookings
  - `reviews` - Customer reviews
  - `gallery` - Service images
  - `service_types` - Service definitions

## Design Patterns Used

### 1. **MVC (Model-View-Controller)**
- Models: Entity classes
- Views: HTML templates
- Controllers: REST controllers

### 2. **Repository Pattern**
- Abstraction for data access
- Easy to test
- Database independence

### 3. **Service Locator Pattern**
- Services provide business logic
- Dependency injection via Spring

### 4. **DTO Pattern** (Data Transfer Objects)
- Used in API requests/responses
- Decouples entities from API

### 5. **Singleton Pattern**
- Spring Beans are singletons
- Services, Repositories, Controllers

## Request/Response Flow

```
1. Browser sends HTTP request
   ↓
2. DispatcherServlet routes to Controller
   ↓
3. Controller calls Service
   ↓
4. Service applies business logic
   ↓
5. Service calls Repository
   ↓
6. Repository executes database query
   ↓
7. Database returns data
   ↓
8. Repository returns Entity
   ↓
9. Service processes and returns result
   ↓
10. Controller formats response
    ↓
11. JSON response sent to browser
    ↓
12. JavaScript processes response
    ↓
13. DOM updated and displayed
```

## Component Structure

### Frontend Components

```
templates/
├── index.html           (Homepage)
├── services.html        (Services page)
├── booking.html         (Booking form)
├── gallery.html         (Photo gallery)
├── reviews.html         (Reviews page)
├── contact.html         (Contact page)
└── admin/
    └── dashboard.html   (Admin panel)

static/
├── css/
│   └── style.css        (Main stylesheet)
├── js/
│   └── app.js           (Utilities)
└── images/
    ├── sac-logo.png     (Company logo)
    ├── ac-repair.jpg    (Service images)
    ├── ref-repair.jpg
    └── wm-repair.jpg
```

### Backend Components

```
controller/
├── HomeController.java
├── BookingController.java
├── ReviewController.java
├── GalleryController.java
├── AdminRestController.java
└── AuthController.java

service/
├── BookingService.java
├── ReviewService.java
├── GalleryService.java
├── UserService.java
└── NotificationService.java

repository/
├── BookingRepository.java
├── ReviewRepository.java
├── GalleryRepository.java
├── UserRepository.java
└── ServiceTypeRepository.java

entity/
├── Booking.java
├── Review.java
├── GalleryImage.java
├── User.java
└── ServiceType.java

config/
├── WebConfig.java
└── (Other configurations)

util/
├── DateUtil.java
├── ValidationUtil.java
└── WhatsAppUtil.java
```

## Data Flow Examples

### Booking Creation Flow

```
1. Customer fills booking form (HTML)
   ↓
2. JavaScript validates input (app.js)
   ↓
3. POST /api/bookings with JSON data
   ↓
4. BookingController.createBooking()
   ↓
5. BookingService.createBooking()
   ↓
6. Booking entity created
   ↓
7. BookingRepository.save()
   ↓
8. MySQL stores booking
   ↓
9. Service calls NotificationService
   ↓
10. WhatsApp message sent to admin
   ↓
11. JSON response returned
   ↓
12. JavaScript displays success message
```

### Review Moderation Flow

```
1. Customer submits review
   ↓
2. ReviewService.createReview() with isApproved = false
   ↓
3. Review saved to database
   ↓
4. Admin views pending reviews
   ↓
5. Admin clicks "Approve"
   ↓
6. AdminRestController.approveReview()
   ↓
7. ReviewService.approveReview()
   ↓
8. Review isApproved = true
   ↓
9. Updated review saved
   ↓
10. Customer sees review on website
```

## Security Architecture

### Authentication & Authorization

```
User Login
   ↓
AuthController.login()
   ↓
UserService.authenticateUser()
   ↓
Verify credentials
   ↓
Update lastLogin timestamp
   ↓
Return user with role (DEVELOPER_ADMIN, CLIENT_ADMIN)
   ↓
Session stored (or JWT token if implemented)
```

### Data Validation

```
Request received
   ↓
ValidationUtil checks format
   ↓
Controller validates parameters
   ↓
Service applies business rules
   ↓
Repository saves to database
```

## Scalability Considerations

### Current Architecture
- Single-instance deployment
- One database connection pool
- Filesystem-based image storage
- In-memory notifications

### Future Enhancements
- **Load Balancing**: Multiple server instances
- **Caching**: Redis for frequently accessed data
- **Asynchronous Processing**: Message queues for notifications
- **CDN**: Cloud-based image storage (AWS S3)
- **Microservices**: Split into separate services
- **API Gateway**: Centralized request handling

## Performance Optimization

### Database
- Indexes on frequently queried columns
- Query optimization
- Connection pooling
- Pagination for large datasets

### Caching
- HTTP caching headers
- Spring Cache abstraction
- Browser caching

### Frontend
- CSS minification
- JavaScript minification
- Image optimization
- Responsive images
- Lazy loading

## Monitoring & Logging

### Logging Levels
```properties
logging.level.root=INFO
logging.level.com.shreeaircons=DEBUG
```

### Logs Location
- Console output during development
- Application logs in production
- Database query logs (optional)

## Deployment Architecture

```
Production Environment

┌──────────────────────┐
│   Load Balancer      │
└──────────┬───────────┘
           │
    ┌──────┴──────┐
    ▼             ▼
┌────────┐   ┌────────┐
│App 1   │   │App 2   │
│(8080)  │   │(8080)  │
└─────┬──┘   └──┬─────┘
      │         │
      └────┬────┘
           │
    ┌──────▼──────┐
    │ MySQL DB    │
    │(Read/Write) │
    └─────────────┘

Additional Services:
│
├── WhatsApp API
├── Email Service (SMTP)
├── File Storage (Uploads)
└── Monitoring Tools
```

## Technology Stack Summary

| Layer | Technology | Version |
|-------|-----------|---------|
| Frontend | HTML5, CSS3, JavaScript | Latest |
| UI Framework | Bootstrap | 5.x |
| Backend | Spring Boot | 3.1.5 |
| Database | MySQL | 8.0+ |
| ORM | JPA/Hibernate | Latest |
| Build Tool | Maven | 3.8+ |
| Java Version | JDK | 17+ |
| Server | Apache Tomcat | Embedded |

## Configuration Management

### Environment-Specific Config
```
application.properties          (Development)
application-prod.properties     (Production)
application-test.properties     (Testing)
```

### Configuration Sources
1. application.properties
2. Environment variables
3. Command-line arguments
4. System properties

## Testing Architecture

### Unit Tests
- Service layer test
- Utility function tests
- Validation tests

### Integration Tests
- Controller tests
- Repository tests
- End-to-end flows

### Test Framework
- JUnit 5
- Mockito for mocking
- SpringBootTest for integration tests

---

## Summary

The architecture is designed to be:
- **Scalable**: Easy to add new features
- **Maintainable**: Clear separation of concerns
- **Testable**: Mockable service layer
- **Secure**: Input validation and authentication
- **Performant**: Database indexes, caching

For more information, see:
- [README.md](README.md) - Project overview
- [SETUP.md](SETUP.md) - Installation guide
- [API.md](docs/API.md) - API documentation
