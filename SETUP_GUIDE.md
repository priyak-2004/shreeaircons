# ============================================
# SHREE AIR CONS - COMPLETE PROJECT SETUP
# ============================================

## Project Structure

```
shree/
├── frontend/              # All frontend files
│   ├── index.html         # Main homepage
│   ├── pages/            # HTML pages (gallery, contact, reviews, etc.)
│   ├── js/               # JavaScript files (apiClient, gallery, contact, reviews, main)
│   ├── css/              # Stylesheets
│   └── images/           # Image assets (logo, etc.)
│
├── backend/              # Express server & API
│   ├── server.js         # Main server file
│   ├── package.json      # Dependencies
│   ├── .env              # Environment variables
│   ├── config/           # Configuration files
│   │   ├── database.js   # MongoDB connection
│   │   ├── cloudinary.js # Cloudinary setup
│   │   └── email.js      # Email configuration
│   ├── controllers/      # Business logic
│   │   ├── bookingController.js
│   │   ├── galleryController.js
│   │   └── reviewController.js
│   ├── models/           # **MOVED TO database/models**
│   ├── routes/           # API routes
│   │   ├── bookingRoutes.js
│   │   ├── galleryRoutes.js
│   │   └── reviewRoutes.js
│   ├── middleware/       # Custom middleware
│   │   ├── auth.js
│   │   ├── errorHandler.js
│   │   └── upload.js
│   └── utils/            # Helper functions
│       ├── cloudinaryHelper.js
│       └── emailHelper.js
│
└── database/             # Database schemas & models
    └── models/           # Mongoose models
        ├── Admin.js      # (Deprecated - no longer used)
        ├── Booking.js
        ├── Gallery.js
        └── Review.js
```

## Installation & Setup

### Prerequisites
- Node.js (v14 or higher)
- MongoDB (local or Atlas)
- Cloudinary account (for file uploads)
- Gmail account with app password (for emails)

### Backend Setup

1. **Navigate to backend folder:**
   ```
   cd backend
   ```

2. **Install dependencies:**
   ```
   npm install
   ```

3. **Create .env file** (already included) and update:
   - `MONGODB_URI`: Your MongoDB connection string
   - `CLOUDINARY_*`: Your Cloudinary credentials
   - `EMAIL_USER` & `EMAIL_PASSWORD`: Gmail credentials
   - `FRONTEND_URL`: http://localhost:3000 (for development)

4. **Start the backend server:**
   ```
   npm start        # Production
   npm run dev      # Development (with nodemon)
   ```

The server will run on http://localhost:5000

### Frontend Setup

1. **Just open the HTML files in a browser OR use a simple HTTP server:**
   
   Option A: Using Python:
   ```
   cd frontend
   python -m http.server 3000
   ```
   
   Option B: Using Node:
   ```
   cd frontend
   npx http-server -p 3000
   ```

Then open http://localhost:3000 in your browser.

## API Endpoints

### Gallery API
- `GET /api/gallery` - Get all gallery items
- `GET /api/gallery?category=AC` - Get by category
- `POST /api/gallery/upload` - Upload new item
- `DELETE /api/gallery/delete/:id` - Delete item

### Booking API
- `POST /api/bookings/create` - Create new booking
- `GET /api/bookings` - Get all bookings
- `GET /api/bookings/single/:id` - Get single booking
- `DELETE /api/bookings/delete/:id` - Delete booking

### Reviews API
- `POST /api/reviews/create` - Submit new review
- `GET /api/reviews` - Get approved reviews (public)
- `GET /api/reviews/single/:id` - Get single review
- `DELETE /api/reviews/delete/:id` - Delete review

## Features

✅ **No Admin/ Login Required**
- Gallery upload/display works for ALL users
- Reviews submitted instantly appear on website
- Booking confirmations sent via email & WhatsApp

✅ **Email Notifications**
- Booking confirmation sent to customer
- Booking notification sent to admin
- Automated email system via Gmail

✅ **File Upload System**
- Photos and videos uploadable via Cloudinary
- Automatic file validation
- Organized gallery by category

✅ **Reviews System**
- Public reviews auto-approved
- Star ratings display
- Average rating calculation

✅ **Booking System**
- Form validation
- Phone number verification
- Status tracking

## Configuration Files

### Database Connection (backend/config/database.js)
- Connects to MongoDB using Mongoose
- Error handling included
- Async connection with retries

### Email Setup (backend/config/email.js)
- Uses Nodemailer
- Gmail SMTP configuration
- Secure password via environment variables

### Cloudinary Setup (backend/config/cloudinary.js)
- File upload provider
- Image & video handling
- Public ID tracking for deletion

## Troubleshooting

### MongoDB Connection Error
- Ensure MongoDB service is running locally
- Check `MONGODB_URI` in .env file
- Verify connection string format

### File Upload Fails
- Check Cloudinary credentials in .env
- Verify file size < 100MB
- Check allowed MIME types (images/videos only)

### Email Not Sending
- Enable "Less secure app access" on Gmail
- Use app-specific password (not main password)
- Check SMTP settings in .env
- Verify admin email is correct

### CORS Errors
- Check `CORS_ORIGIN` in backend configuration
- Ensure frontend and backend URLs match
- Verify backend is running first

## Important Notes

❌ **Admin files have been removed** - This is a public system
❌ **No authentication/login required** - Anyone can upload
✅ **All reviews are auto-approved** - Instant display on website
✅ **Emails sent automatically** - For bookings and confirmations

## Security Recommendations

For production deployment:
1. Enable JWT authentication if needed
2. Add admin approval for uploads
3. Implement rate limiting
4. Use HTTPS/SSL
5. Hide sensitive .env variables
6. Add input sanitization
7. Enable database backups

## Support

For issues or questions:
- Email: admin@shreeaircons.com
- Phone: 6369529001, 9342017154

---

**Last Updated:** March 2026
**Version:** 1.0.0
