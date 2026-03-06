# Dependencies and Environment Configuration

## Backend Dependencies (package.json)

```json
{
  "name": "shree-aircons-backend",
  "version": "1.0.0",
  "description": "Backend API for Shree Air Cons - Professional Appliance Repair Services",
  "main": "server.js",
  "scripts": {
    "start": "node server.js",
    "dev": "nodemon server.js"
  },
  "dependencies": {
    "express": "^4.18.2",
    "mongoose": "^7.0.0",
    "dotenv": "^16.0.3",
    "cloudinary": "^1.32.0",
    "multer": "^1.4.5-lts.1",
    "nodemailer": "^6.9.1",
    "cors": "^2.8.5",
    "express-async-errors": "^3.1.1"
  },
  "devDependencies": {
    "nodemon": "^2.0.20"
  }
}
```

## Environment Variables (.env)

Create `backend/.env` with:

```env
# Server Configuration
NODE_ENV=development
PORT=5000
BACKEND_URL=http://localhost:5000
FRONTEND_URL=http://localhost:3000

# Database
MONGODB_URI=mongodb://localhost:27017/shreeaircons

# Cloudinary (File Uploads)
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret

# Email (Gmail)
EMAIL_USER=your_email@gmail.com
EMAIL_PASSWORD=your_app_password
ADMIN_EMAIL=admin@shreeaircons.com

# Contact
WHATSAPP_NUMBER=6395290014

# Security
JWT_SECRET=your_secret_key_here_change_this
```

## Setup Instructions

### 1. MongoDB Setup

**Local:**
- Download MongoDB Community Edition
- Start MongoDB service
- Connection URL: `mongodb://localhost:27017/shreeaircons`

**Cloud (MongoDB Atlas):**
- Create account at mongodb.com/cloud/atlas
- Create cluster
- Get connection string
- Update .env: `mongodb+srv://user:pass@cluster.mongodb.net/shreeaircons`

### 2. Cloudinary Setup

- Sign up at cloudinary.com
- Go to Dashboard
- Copy: Cloud Name, API Key, API Secret
- Update .env file

### 3. Gmail Setup

- Enable 2-factor authentication
- Generate app password (NOT regular password)
- Use in EMAIL_PASSWORD field
- Other email services: Update SMTP settings

## Verification Checklist

- [ ] MongoDB running and accessible
- [ ] .env file created in backend folder
- [ ] All required variables filled
- [ ] Cloudinary credentials valid
- [ ] Gmail app password generated
- [ ] Node modules installed (`npm install`)
- [ ] Frontend accessible at :3000
- [ ] Backend accessible at :5000
- [ ] Gallery upload working
- [ ] Bookings saving to database
- [ ] Emails being sent

## Common Issues & Solutions

**Connection Refused Error:**
- Check MongoDB is running
- Verify port numbers (5000 for backend)
- Check firewall settings

**Module Not Found:**
- Run `npm install` in backend folder
- Delete node_modules and reinstall if issues persist

**Cloudinary Upload Error:**
- Verify credentials in .env
- Check file size < 100MB
- Ensure correct file type

**Email Not Sending:**
- Use app-specific password (Gmail)
- Check admin email is valid
- Verify SMTP settings

---

**Need help?** Check SETUP_GUIDE.md or contact admin@shreeaircons.com
