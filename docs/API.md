# API Documentation - Shree Air Cons

Complete API documentation for the Shree Air Cons Full Stack Application.

## Base URL

```
http://localhost:8080/api
```

## Content Type

All requests and responses use:
```
Content-Type: application/json
```

## Authentication

Currently, basic authentication is used for admin endpoints. JWT implementation is available for future enhancement.

---

## Public Endpoints

### 1. Bookings

#### Create Booking
- **Endpoint**: `POST /bookings`
- **Description**: Submit a new service booking
- **Request Body**:
```json
{
    "customerName": "John Doe",
    "phoneNumber": "9876543210",
    "repairType": "AC",
    "address": "123 Main Street, Chennai",
    "problemDescription": "AC is not cooling properly"
}
```

- **Response**:
```json
{
    "success": true,
    "message": "Booking created successfully",
    "bookingId": 1
}
```

#### Get Booking by ID
- **Endpoint**: `GET /bookings/{id}`
- **Description**: Retrieve booking details
- **Response**:
```json
{
    "id": 1,
    "customerName": "John Doe",
    "phoneNumber": "9876543210",
    "repairType": "AC",
    "address": "123 Main Street, Chennai",
    "status": "PENDING",
    "createdAt": "2026-02-17T10:30:00"
}
```

#### Check Booking Status
- **Endpoint**: `GET /bookings/phone/{phoneNumber}`
- **Description**: Get all bookings for a phone number
- **Response**: Array of booking objects

---

### 2. Reviews

#### Submit Review
- **Endpoint**: `POST /reviews`
- **Description**: Submit a new review
- **Request Body**:
```json
{
    "customerName": "Jane Smith",
    "customerEmail": "jane@example.com",
    "customerPhone": "8765432109",
    "serviceType": "AC",
    "rating": 5,
    "reviewText": "Excellent service! Highly recommended."
}
```

- **Response**:
```json
{
    "success": true,
    "message": "Review submitted successfully. Awaiting admin approval.",
    "reviewId": 1
}
```

#### Get Approved Reviews
- **Endpoint**: `GET /reviews/approved`
- **Description**: Get all approved reviews
- **Response**: Array of approved review objects

#### Get Average Rating
- **Endpoint**: `GET /reviews/rating`
- **Description**: Get average rating of all approved reviews
- **Response**:
```json
{
    "averageRating": 4.8
}
```

---

### 3. Gallery

#### Get All Gallery Images
- **Endpoint**: `GET /gallery`
- **Description**: Get all active gallery images
- **Response**: Array of gallery image objects

#### Get Images by Service Type
- **Endpoint**: `GET /gallery/service/{serviceType}`
- **Description**: Get images for specific service type (AC, Refrigerator, Washing Machine)
- **Response**: Array of gallery image objects

---

### 4. Authentication

#### Admin Login
- **Endpoint**: `POST /auth/login`
- **Description**: Authenticate admin user
- **Request Body**:
```json
{
    "email": "shreeaircons2026@gmail.com",
    "password": "password123"
}
```

- **Response**:
```json
{
    "success": true,
    "message": "Login successful",
    "user": {
        "id": 1,
        "email": "shreeaircons2026@gmail.com",
        "role": "CLIENT_ADMIN",
        "fullName": "Admin User"
    }
}
```

#### User Registration
- **Endpoint**: `POST /auth/register`
- **Description**: Register new user
- **Request Body**:
```json
{
    "email": "user@example.com",
    "password": "password123",
    "fullName": "User Name",
    "role": "CLIENT_ADMIN"
}
```

---

## Admin Endpoints

### 1. Booking Management

#### Get All Bookings
- **Endpoint**: `GET /admin/bookings`
- **Authorization**: Required
- **Response**: Array of all bookings

#### Get Specific Booking
- **Endpoint**: `GET /admin/bookings/{id}`
- **Authorization**: Required
- **Response**: Single booking object

#### Update Booking
- **Endpoint**: `PUT /admin/bookings/{id}`
- **Authorization**: Required
- **Request Body**:
```json
{
    "status": "CONFIRMED",
    "serviceDate": "2026-02-20T10:00:00",
    "serviceTime": "10:00 AM",
    "notes": "Technician assigned"
}
```

#### Delete Booking
- **Endpoint**: `DELETE /admin/bookings/{id}`
- **Authorization**: Required

#### Get Bookings by Status
- **Endpoint**: `GET /admin/bookings/status/{status}`
- **Status Values**: PENDING, CONFIRMED, COMPLETED, CANCELLED
- **Authorization**: Required

---

### 2. Review Management

#### Get Pending Reviews
- **Endpoint**: `GET /admin/reviews/pending`
- **Authorization**: Required
- **Response**: Array of unapproved reviews

#### Get All Reviews
- **Endpoint**: `GET /admin/reviews`
- **Authorization**: Required
- **Response**: Array of all reviews

#### Approve Review
- **Endpoint**: `PUT /admin/reviews/{id}/approve`
- **Authorization**: Required
- **Response**: Approved review object

#### Reject Review
- **Endpoint**: `PUT /admin/reviews/{id}/reject`
- **Authorization**: Required
- **Response**: Rejected review object

#### Delete Review
- **Endpoint**: `DELETE /admin/reviews/{id}`
- **Authorization**: Required

---

### 3. Gallery Management

#### Upload Image
- **Endpoint**: `POST /admin/gallery/upload`
- **Authorization**: Required
- **Content-Type**: multipart/form-data
- **Parameters**:
  - `file`: Image file (required)
  - `serviceType`: AC, Refrigerator, Washing Machine (required)
  - `imageType`: before, after, general (optional)
  - `description`: Image description (optional)

- **Response**:
```json
{
    "success": true,
    "message": "Image uploaded successfully",
    "image": {
        "id": 1,
        "imageUrl": "/uploads/gallery/...",
        "serviceType": "AC"
    }
}
```

#### Get All Gallery Images
- **Endpoint**: `GET /admin/gallery`
- **Authorization**: Required
- **Response**: Array of all gallery images

#### Update Gallery Image
- **Endpoint**: `PUT /admin/gallery/{id}`
- **Authorization**: Required
- **Request Body**:
```json
{
    "imageDescription": "New description",
    "imageType": "after",
    "isActive": true
}
```

#### Delete Gallery Image
- **Endpoint**: `DELETE /admin/gallery/{id}`
- **Authorization**: Required

---

### 4. Dashboard

#### Get Dashboard Statistics
- **Endpoint**: `GET /admin/dashboard/stats`
- **Authorization**: Required
- **Response**:
```json
{
    "totalBookings": 25,
    "pendingBookings": 5,
    "completedBookings": 18,
    "pendingReviews": 2,
    "averageRating": 4.8
}
```

---

## Data Models

### Booking
```json
{
    "id": 1,
    "customerName": "String",
    "phoneNumber": "String (10 digits)",
    "repairType": "AC | Refrigerator | Washing Machine",
    "address": "String",
    "problemDescription": "String",
    "status": "PENDING | CONFIRMED | COMPLETED | CANCELLED",
    "createdAt": "ISO 8601 DateTime",
    "confirmedAt": "ISO 8601 DateTime",
    "serviceDate": "ISO 8601 DateTime",
    "serviceTime": "String (HH:MM)",
    "notes": "String"
}
```

### Review
```json
{
    "id": 1,
    "customerName": "String",
    "customerEmail": "String",
    "customerPhone": "String",
    "reviewText": "String",
    "rating": "1-5",
    "serviceType": "AC | Refrigerator | Washing Machine",
    "isApproved": "Boolean",
    "createdAt": "ISO 8601 DateTime",
    "approvedAt": "ISO 8601 DateTime"
}
```

### Gallery Image
```json
{
    "id": 1,
    "imageUrl": "String (URL path)",
    "serviceType": "AC | Refrigerator | Washing Machine",
    "imageType": "before | after | general",
    "imageDescription": "String",
    "isActive": "Boolean",
    "uploadedAt": "ISO 8601 DateTime"
}
```

### User
```json
{
    "id": 1,
    "email": "String",
    "role": "DEVELOPER_ADMIN | CLIENT_ADMIN",
    "fullName": "String",
    "phoneNumber": "String",
    "isActive": "Boolean",
    "createdAt": "ISO 8601 DateTime",
    "lastLogin": "ISO 8601 DateTime"
}
```

---

## Error Responses

### Bad Request (400)
```json
{
    "success": false,
    "message": "Error description"
}
```

### Not Found (404)
```json
{
    "error": "Resource not found"
}
```

### Server Error (500)
```json
{
    "error": "Internal server error"
}
```

---

## Rate Limiting

Currently, no rate limiting is implemented. For production:
- Implement rate limiting on API endpoints
- Limit requests per IP/user
- Monitor API usage

---

## Pagination (Future Implementation)

For performance with large datasets:
```
GET /api/admin/bookings?page=0&size=20&sort=createdAt,desc
```

---

## WebHooks (Future Implementation)

- Booking created: `/webhook/booking-created`
- Booking updated: `/webhook/booking-updated`
- Review submitted: `/webhook/review-submitted`

---

## Testing

### Using cURL

```bash
# Create booking
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "John Doe",
    "phoneNumber": "9876543210",
    "repairType": "AC",
    "address": "123 Main Street",
    "problemDescription": "AC not cooling"
  }'

# Get reviews
curl http://localhost:8080/api/reviews/approved

# Admin login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "shreeaircons2026@gmail.com",
    "password": "password123"
  }'
```

### Using Postman

1. Import this API documentation into Postman
2. Set up environment variables for base URL
3. Create bookings and test workflows
4. Export tests for CI/CD integration

---

## Version

**API Version**: 1.0.0  
**Last Updated**: February 2026

---

For more information, see [README.md](README.md) and [SETUP.md](SETUP.md)
