# 🏗️ Architecture & Implementation Overview

## System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                      FRONTEND (HTTP Client)                  │
├─────────────────────────────────────────────────────────────┤
│  Admin Panel (index.html)  │  Website (index.html, pages)    │
│  - Login                   │  - View Gallery                 │
│  - Upload Gallery          │  - Book Service                 │
│  - Manage Bookings         │  - Submit Reviews               │
│  - Approve Reviews         │  - View Approved Reviews        │
└─────────────────────────────────────────────────────────────┘
                              ↕
                          HTTP/HTTPS
                              ↕
┌─────────────────────────────────────────────────────────────┐
│                    BACKEND (Express.js)                      │
├─────────────────────────────────────────────────────────────┤
│                       API ENDPOINTS                          │
│  ┌────────────────┐ ┌──────────────┐ ┌────────────┐         │
│  │  Authentication │ │ Gallery      │ │ Bookings   │  Reviews │
│  ├────────────────┤ ├──────────────┤ ├────────────┤ ──────── │
│  │ POST /login    │ │ POST /upload │ │ POST /creat│ POST /cre│
│  │ GET /verify    │ │ GET /        │ │ GET /      │ GET /app │
│  │ POST /change   │ │ DELETE /del  │ │ PUT /updat│ DELETE / │
│  │                │ │              │ │ DELETE /   │          │
│  └────────────────┘ └──────────────┘ └────────────┘ ────────┘
│                                                                │
│  ┌──────────────────────────────────────────────────────┐   │
│  │            MIDDLEWARE LAYER                         │   │
│  │  ┌─────────────┬──────────────┬──────────────────┐  │   │
│  │  │ Auth Check  │ File Upload  │ Error Handler    │  │   │
│  │  │ (JWT)       │ (Multer)     │ (Validation)     │  │   │
│  │  └─────────────┴──────────────┴──────────────────┘  │   │
│  └──────────────────────────────────────────────────────┘   │
│                                                                │
│  ┌──────────────────────────────────────────────────────┐   │
│  │            MODELS & VALIDATION                       │   │
│  │  ┌────────┬────────┬─────────┬────────┐              │   │
│  │  │Admin   │Gallery │Booking  │Review  │              │   │
│  │  └────────┴────────┴─────────┴────────┘              │   │
│  └──────────────────────────────────────────────────────┘   │
│                                                                │
│  ┌──────────────────────────────────────────────────────┐   │
│  │            UTILITIES & CONFIG                        │   │
│  │  ┌──────────────┬─────────────┬──────────────┐       │   │
│  │  │Cloudinary    │Email Helper │JWT Secret    │       │   │
│  │  │(File Upload) │(Nodemailer) │(bcryptjs)    │       │   │
│  │  └──────────────┴─────────────┴──────────────┘       │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
      ↕                  ↕                    ↕
   MongoDB            Cloudinary          Gmail
   (Database)         (File Storage)       (Email)
   
┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│ Collections: │  │ Image Folder:│  │ SMTP Server: │
│ - admins     │  │ shree-aircons│  │ mail.google. │
│ - gallery    │  │ /{category}  │  │ com:587      │
│ - bookings   │  │              │  │              │
│ - reviews    │  │              │  │ Auth:        │
│              │  │ Features:    │  │ Gmail App    │
│              │  │ - Auto CDN   │  │ Password     │
│              │  │ - Responsive │  │              │
│              │  │ - Secure URL │  │              │
└──────────────┘  └──────────────┘  └──────────────┘
```

---

## Authentication Flow

```
ADMIN LOGIN PROCESS:
┌─────────┐                         ┌─────────┐
│Frontend │                         │ Backend │
└────┬────┘                         └────┬────┘
     │                                   │
     │ 1. User enters email & password   │
     │────────────────────────────────→  │
     │    POST /api/auth/login           │
     │                                   │
     │                    2. Find admin by email
     │                       ├─ Check MongoDB
     │                       └─ Compare password with bcryptjs
     │                          (takes ~1 sec - secure)
     │                                   │
     │   3. If valid, generate JWT token │
     │←────────────────────────────────  │
     │    {token, admin, message}        │
     │                                   │
     │ 4. Store token in localStorage    │
     │ 5. Ready to make protected reqs  │
     │                                   │
     │ 6. Protected request with token   │
     │────────────────────────────────→  │
     │    Header: "Authorization: ..."   │
     │                                   │
     │             7. Verify JWT token
     │                ├─ Check signature
     │                ├─ Decode admin ID
     │                └─ Extract claims
     │                                   │
     │  8. Process request, send response│
     │←────────────────────────────────  │
     │    (protected data)               │
     │                                   │
```

---

## Admin Account Auto-Seeding

```
SERVER STARTUP SEQUENCE:

npm run dev
    ↓
Load environment variables (.env)
    ↓
Connect to MongoDB
    ↓
Seed Default Admin Account
    ├─ Check if admin@sac exists
    │   ├─ YES: Skip seeding ✓
    │   └─ NO: Continue...
    │
    ├─ Create new Admin document
    │   ├─ email: "admin@sac"
    │   ├─ password: "Admin@123" (plain text)
    │   └─ createdAt: now
    │
    ├─ Trigger pre-save hook
    │   ├─ Generate salt (10 rounds)
    │   ├─ Hash password using bcryptjs
    │   ├─ Replace plain text with hash
    │   └─ ($2a$10$...xxxxx)
    │
    ├─ Save to MongoDB
    │   └─ "admin@sac" with hashed password
    │
    └─ Log success message
       ✓ Default admin account created
         Email: admin@sac
         Password: Admin@123
    ↓
Start Express Server
    ↓
Listen on port 5000
    ↓
✓ Ready to receive requests!
```

---

## Password Hashing Flow

```
PLAIN TEXT PASSWORD: "Admin@123"
         ↓
    bcryptjs.hash()
         ↓
    Generate random SALT (10 rounds)
    ├─ Round 1: Mix password + salt
    ├─ Round 2: Mix result + salt
    ├─ ... (8 more times)
    └─ Round 10: Final mix
         ↓
HASHED PASSWORD: "$2a$10$u9YvwqH5Pq7K8mPqN3xJ.OqL2kJQ4cF7W5mX2yB9rZ4nX5vC3tD1u"
         ↓
    Stored in MongoDB (NEVER plain text!)
         ↓
LOGIN VERIFICATION:
    User enters: "Admin@123"
         ↓
    bcryptjs.compare(
        "Admin@123",
        "$2a$10$u9YvwqH..."
    )
         ↓
    ├─ Hash entered password same way (10 rounds)
    ├─ Compare to stored hash
    │   ├─ MATCH: Login success ✓
    │   └─ NO MATCH: Login failed ✗
    │
    (Takes ~1 second - prevents brute force)
```

---

## Gallery Upload Flow

```
ADMIN UPLOADS PHOTO/VIDEO:

Admin selects file (photo.jpg)
    ↓
    │ Frontend
    ├─ Validates file (extension, size)
    ├─ Prepares multipart/form-data
    │   ├─ file: binary data
    │   ├─ title: "AC Repair"
    │   ├─ category: "AC"
    │   └─ type: "image"
    │
    └─ Sends to /api/gallery/upload
       ├─ Authorization header with JWT
       └─ Multer middleware stores in memory
    ↓
    │ Backend
    ├─ auth.js validates JWT token
    │   ├─ Checks token signature
    │   └─ Extracts admin ID
    │
    ├─ upload.js middleware (Multer)
    │   ├─ Validates file type (image/video)
    │   └─ Checks file size < 100MB
    │
    ├─ galleryController.js
    │   ├─ Validates form data
    │   ├─ Calls cloudinaryHelper.uploadToCloudinary()
    │   │   ├─ Creates upload stream
    │   │   ├─ Sends file buffer to Cloudinary
    │   │   └─ Cloudinary returns secure URL
    │   │
    │   ├─ Creates Gallery document
    │   │   ├─ title: "AC Repair"
    │   │   ├─ category: "AC"
    │   │   ├─ src: "https://res.cloudinary.com/..."
    │   │   └─ cloudinaryPublicId: "shree-aircons/AC/xyz"
    │   │
    │   └─ Saves to MongoDB
    │       └─ Gallery collection updated
    │
    └─ Returns response with item data
       └─ Frontend shows success message
    ↓
PUBLIC VIEWS GALLERY:

GET /api/gallery (no auth needed)
    ↓
galleryController.getAllItems()
    ├─ Query MongoDB
    ├─ Find all Gallery documents
    └─ Return array of items
    ↓
Frontend displays photos/videos
├─ Render images from Cloudinary URLs
└─ Responsive grid layout
```

---

## Booking Notification Flow

```
CUSTOMER SUBMITS BOOKING:

POST /api/bookings/create
├─ name: "John Doe"
├─ phone: "9876543210"
├─ address: "123 Main St"
├─ service: "AC Repair"
└─ (No login required!)
    ↓
Backend bookingController.createBooking()
    ├─ Validate all required fields
    │   ├─ Name, phone, address, service
    │   └─ Phone must be 10 digits (regex validation)
    │
    ├─ Create Booking document
    │   ├─ status: "pending" (initial)
    │   └─ createdAt: now
    │
    ├─ Save to MongoDB
    │   └─ Booking collection updated
    │
    ├─ Send email to admin
    │   ├─ Call emailHelper.sendBookingEmailToAdmin()
    │   ├─ Create HTML email template
    │   │   ├─ Customer name, phone
    │   │   ├─ Address, service type
    │   │   ├─ Booking ID
    │   │   └─ Instructions to login
    │   │
    │   ├─ Configure Nodemailer
    │   │   ├─ From: AWS_EMAIL
    │   │   ├─ To: process.env.ADMIN_EMAIL
    │   │   ├─ Service: Gmail
    │   │   └─ Auth: App Password
    │   │
    │   └─ Send via SMTP
    │       ├─ If success: log success
    │       └─ If fail: log error (don't break booking)
    │
    └─ Return response to customer
       ├─ success: true
       ├─ message: "Booking created"
       └─ booking: {_id, name, status...}
    ↓
ADMIN RECEIVES EMAIL:
├─ Subject: "🔔 New Booking Request - John Doe"
├─ Body: HTML template with details
├─ From: system@gmail.com
└─ Time: Immediately
    ↓
ADMIN LOGS IN & SEES BOOKING:

Admin logs in with admin@sac / Admin@123
    ├─ JWT token generated
    ├─ Stored in localStorage
    └─ Can now access /api/bookings
    ↓
GET /api/bookings (with JWT token)
    ├─ auth.js verifies token
    ├─ Admin ID extracted
    ├─ Query all bookings
    └─ Return array of bookings
    ↓
Admin views dashboard
├─ Lists all pending bookings
├─ Shows customer details
└─ Can confirm, complete, or cancel
    ↓
Admin updates status:

PUT /api/bookings/update/xyz
├─ status: "confirmed"
└─ (With JWT token)
    ↓
Booking status changed in MongoDB
└─ "pending" → "confirmed"
```

---

## Review Workflow

```
CUSTOMER SUBMITS REVIEW (No Login):

POST /api/reviews/create
├─ name: "Jane Doe"
├─ rating: 5
└─ message: "Excellent service!"
    ↓
Backend creates Review document
├─ approved: false (requires admin approval)
└─ Save to MongoDB
    ↓
Response to customer
└─ "Review submitted! Awaiting approval."
    ↓
ADMIN LOGS IN & SEES PENDING REVIEWS:

GET /api/reviews?approved=false (with JWT)
├─ auth.js verifies token
├─ Query pending reviews
└─ Return array of unapproved reviews
    ↓
Admin views dashboard
├─ Lists pending reviews
├─ Can approve or delete
    ↓
Admin approves review:

PUT /api/reviews/approve/xyz (with JWT)
├─ Update: approved = true
└─ Save to MongoDB
    ↓
CUSTOMERS SEE APPROVED REVIEWS (No Login):

GET /api/reviews/approved
├─ Query only approved reviews
├─ Return array of approved reviews
└─ Frontend displays on website
    ↓
Public displays 5-star review
├─ "Jane Doe: Excellent service!"
└─ Shows on website for all to see
```

---

## JWT Token Flow

```
LOGIN:
POST /api/auth/login
├─ Verify email & password
└─ Generate JWT Token
    ↓
    jwt.sign(
        {
            id: admin._id,
            email: admin.email
        },
        process.env.JWT_SECRET,
        { expiresIn: '7d' }
    )
    ↓
    Header: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
    Payload: eyJpZCI6IjYxMjNhYmM0NTZkZWY3ODl4eXoiLCJlbWFpbCI6ImFkbWluQHNhY...
    Signature: u9YvwqH5Pq7K8mPqN3xJ.OqL2kJQ4cF7W5mX2yB9
    ↓
Token sent to frontend
└─ Stored in localStorage
    ↓
PROTECTED REQUEST:

GET /api/auth/verify
Header: "Authorization: Bearer eyJhbGciOiJIUzI1NiIs..."
    ↓
auth.js middleware:
├─ Extract token from header
├─ Verify signature using JWT_SECRET
├─ Decode token
│   └─ {id: "...", email: "admin@sac", exp: 1234567890}
├─ Check expiration
│   ├─ If expired: return 401 Unauthorized
│   ├─ If valid: continue
│   └─ Extract admin ID
├─ Set req.adminId = admin._id
└─ Call next()
    ↓
Controller processes request
└─ Uses req.adminId to fetch admin data
    ↓
TOKEN EXPIRES:

After 7 days:
├─ exp timestamp < now
├─ jwt.verify() throws error
├─ Middleware returns 401 Unauthorized
└─ Frontend must login again
```

---

## Database Relationships

```
MongoDB Collections:

┌─────────────────────────────────────────────────┐
│              ADMINS COLLECTION                  │
├─────────────────────────────────────────────────┤
│ _id:       ObjectId("6123abc456def789xyz")      │
│ email:     "admin@sac"                          │
│ password:  "$2a$10$u9YvwqH5Pq7K8mPqN3xJ..."   │
│ createdAt: ISODate("2024-03-03T10:30:00Z")     │
└─────────────────────────────────────────────────┘
                     ↑
                     │ (Admin uploads)
                     │
┌─────────────────────────────────────────────────┐
│             GALLERY COLLECTION                  │
├─────────────────────────────────────────────────┤
│ _id:                  ObjectId("123abc...")     │
│ title:                "AC Installation"         │
│ category:             "AC"                      │
│ type:                 "image"                   │
│ src:                  "https://res.cloudinary..│
│ cloudinaryPublicId:   "shree-aircons/AC/..."   │
│ createdAt:            ISODate("2024-03-03...")  │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│            BOOKINGS COLLECTION                  │
├─────────────────────────────────────────────────┤
│ _id:        ObjectId("456def...")               │
│ name:       "John Doe"                          │
│ phone:      "9876543210"                        │
│ address:    "123 Main St"                       │
│ service:    "AC Repair"                         │
│ status:     "pending"                           │
│ createdAt:  ISODate("2024-03-03...")            │
│ updatedAt:  ISODate("2024-03-03...")            │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│             REVIEWS COLLECTION                  │
├─────────────────────────────────────────────────┤
│ _id:        ObjectId("789ghi...")               │
│ name:       "Jane Doe"                          │
│ rating:     5                                   │
│ message:    "Excellent service!"                │
│ approved:   true                                │
│ createdAt:  ISODate("2024-03-03...")            │
└─────────────────────────────────────────────────┘
```

---

## Security Layers

```
┌────────────────────────────────────────┐
│         FRONTEND (User Actions)         │
└────────────────────────────────────────┘
              ↓
        ┌─────────────┐
        │   HTTP/TLS  │ (HTTPS in production)
        └─────────────┘
              ↓
┌────────────────────────────────────────┐
│           LAYER 1: CORS CHECK          │
│  Verify request comes from allowed     │
│  domain (process.env.FRONTEND_URL)     │
└────────────────────────────────────────┘
              ↓
┌────────────────────────────────────────┐
│      LAYER 2: ROUTE VALIDATION        │
│  Check request path is valid endpoint  │
└────────────────────────────────────────┘
              ↓
┌────────────────────────────────────────┐
│  LAYER 3: JWT TOKEN VERIFICATION       │
│  For protected routes:                 │
│  ├─ Extract token from header          │
│  ├─ Verify signature using JWT_SECRET  │
│  ├─ Check expiration (7 days)          │
│  └─ Allow only if valid                │
└────────────────────────────────────────┘
              ↓
┌────────────────────────────────────────┐
│   LAYER 4: INPUT VALIDATION            │
│  ├─ Check required fields              │
│  ├─ Validate email format              │
│  ├─ Validate phone (10 digits)         │
│  ├─ Validate service types             │
│  ├─ Validate rating (1-5)              │
│  └─ Validate file types                │
└────────────────────────────────────────┘
              ↓
┌────────────────────────────────────────┐
│  LAYER 5: DATABASE VALIDATION          │
│  Mongoose schema validation:           │
│  ├─ Required fields check              │
│  ├─ Unique constraints (email)         │
│  ├─ Type checking                      │
│  └─ Enum validation                    │
└────────────────────────────────────────┘
              ↓
┌────────────────────────────────────────┐
│   LAYER 6: ERROR HANDLING              │
│  ├─ No sensitive data in errors        │
│  ├─ Log errors securely                │
│  └─ Return generic error messages      │
└────────────────────────────────────────┘
```

---

## Deployment Architecture

```
PRODUCTION SETUP:

┌─────────────────────────────────────────────┐
│          PRODUCTION ENVIRONMENT              │
├─────────────────────────────────────────────┤
│                                              │
│  ┌──────────────────────────────────────┐   │
│  │  FRONTEND (Static Files)              │   │
│  │  - Hosted on: Netlify/Vercel/AWS S3  │   │
│  │  - Domain: www.shreaircons.com        │   │
│  │  - HTTPS: Yes                         │   │
│  └──────────────────────────────────────┘   │
│           ↓ (API calls)                     │
│  ┌──────────────────────────────────────┐   │
│  │  BACKEND SECURITY                    │   │
│  │  - Firewall enabled                  │   │
│  │  - Rate limiting enabled             │   │
│  │  - HTTPS: Yes (Mandatory)            │   │
│  │  - CORS: shreaircons.com only        │   │
│  └──────────────────────────────────────┘   │
│           ↓                                  │
│  ┌──────────────────────────────────────┐   │
│  │  BACKEND (Node.js APP)                │   │
│  │  - Hosted on: Heroku/Railway/Render  │   │
│  │  - Container: Docker optional        │   │
│  │  - PM2: Process management           │   │
│  │  - Env: Production                   │   │
│  └──────────────────────────────────────┘   │
│       ↓     ↓     ↓                         │
│       │     │     │                         │
│   ┌───┴─┬───┴─┬───┴──┐                     │
│   │   │   │   │      │                     │
│  MongoDB Cloudinary Gmail                   │
│  (Atlas)  (CDN)     (SMTP)                  │
│                                              │
└─────────────────────────────────────────────┘
```

---

All diagrams show the complete implementation flow and architecture!
