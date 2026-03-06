# 🚀 QUICK START GUIDE - Shree Air Cons Backend & Frontend

## 📋 Prerequisites
- Node.js installed (https://nodejs.org/)
- MongoDB Atlas account (https://www.mongodb.com/cloud/atlas)
- Cloudinary account (https://cloudinary.com/)

---

## ⚡ 5-MINUTE QUICK START

### Step 1: Create `.env` File
Create `d:\shree\backend\.env`:

```
PORT=5000
NODE_ENV=development

MONGODB_URI=mongodb+srv://your_user:your_pass@cluster.mongodb.net/shree_aircons?retryWrites=true&w=majority
JWT_SECRET=your_secret_key
JWT_EXPIRY=7d

CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret

EMAIL_SERVICE=gmail
EMAIL_USER=your-email@gmail.com
EMAIL_PASS=your_app_password_16_chars
ADMIN_EMAIL=admin@shreaircons.com

FRONTEND_URL=http://localhost:3000
```

### Step 2: Install & Start Backend
```bash
cd d:\shree\backend
npm install
npm run dev
```

### Step 3: Create Admin Account
```bash
node scripts/createAdmin.js admin@shreaircons.com admin123
```

### Step 4: Open Frontend
Open any of these:
- `d:\shree\index.html` (direct file)
- `http://localhost:8000` (if using Python server)
- `http://localhost:3000` (if using Node.js server)

### Step 5: Test Admin Panel
Go to `d:\shree\admin/index.html` and login with:
- Email: `admin@shreaircons.com`
- Password: `admin123`

---

## 🔍 Frontend - Opening the Website

### Method 1: Direct File
1. Open `D:\shree\index.html` in browser
2. Works offline (uses backend for dynamic data)

### Method 2: Local Server (Python)
```bash
cd d:\shree
python -m http.server 8000
# Open http://localhost:8000
```

### Method 3: Live Server (VS Code)
1. Open `d:\shree` folder in VS Code
2. Install "Live Server" extension
3. Right-click `index.html` → "Open with Live Server"

---

## 📱 Testing Website Features

### 1️⃣ Gallery Page
- View images uploaded via admin panel
- Filter by category (AC, Washing, Refrigerator)
- Images come from Cloudinary

### 2️⃣ Booking Form
- Fill customer details
- Submit booking
- Confirmation appears
- Admin receives email notification

### 3️⃣ Reviews
- Submit review with rating
- After admin approves, appears on site
- Average rating calculated

### 4️⃣ Admin Panel
- Login with your credentials
- Manage bookings (confirm/delete)
- Upload gallery images (to Cloudinary)
- Approve/delete reviews

---

## 🆘 Quick Troubleshooting

| Issue | Solution |
|-------|----------|
| Backend won't start | Check `.env` file exists and MongoDB_URI is correct |
| "Cannot connect to MongoDB" | Check MongoDB Atlas connection string and IP whitelist |
| Gallery uploads not working | Check Cloudinary credentials in `.env` |
| Emails not sending | Check Gmail app password (not regular password) |
| Frontend blank | Check backend is running and API_BASE_URL is correct |

---

## 📚 Detailed Guides

- **[BACKEND_SETUP.md](BACKEND_SETUP.md)** - Complete backend setup guide
- **[NAVIGATION_GUIDE.md](NAVIGATION_GUIDE.md)** - Frontend navigation features

---

## ✅ You're Ready!

## 📱 Testing the Website

### Desktop View
- Open browser at normal size
- All features work perfectly
- Full navigation bar

### Tablet View
- Resize browser to 768px width
- Hamburger menu appears
- Responsive layout adjusts

### Mobile View
- Resize browser to 375px width
- Hamburger menu fully functional
- All buttons and forms optimized

## 🔑 Admin Panel Access

### Login to Admin
1. Go to `http://localhost:8000/admin/`
   OR direct file: `d:\shree\admin\index.html`
2. Enter password: `admin123`
3. Click "Login"

### Admin Features Available

#### Dashboard
- See total bookings, reviews, gallery items
- View recent bookings
- Quick overview of everything

#### Gallery Management
- Upload new gallery items with title, category, and image URL
- Manage AC Services, Washing Machine, or Refrigerator categories
- Delete items (they disappear from public gallery)
- **Demo items included**: AC repair, washing machine, refrigerator

#### Booking Management
- See all service booking requests
- Filter by: All, Pending, Confirmed
- Confirm bookings to change status
- Delete bookings

#### Review Management
- See all submitted reviews
- Filter by: All, Pending, Approved
- Approve reviews to show publicly
- Delete reviews

#### Settings
- Change admin password from default `admin123`
- Export all data as backup JSON file
- Clear all data (with safety warnings)

## 📋 Test the Website Features

### 1. Test Booking (Contact Page)
1. Go to Contact page (or click "Book Service")
2. Fill the booking form:
   - Name: Your Name
   - Phone: 9876543210
   - Address: Your address
   - Service Type: Select any service
3. Click "Book Service"
4. See confirmation modal
5. Check Admin → Bookings to see it

### 2. Test Reviews (Reviews Page)
1. Go to Reviews page
2. Enter your name
3. Click on stars to rate (1-5 stars)
4. Write a review message
5. Click "Submit Review"
6. Go to Admin → Reviews
7. Click "Approve" to show it publicly
8. Back to Reviews page to see your review

### 3. Test Gallery (Gallery Page)
1. Go to Gallery page
2. See demo gallery items
3. Filter by category (AC, Washing, Refrigerator)
4. Hover on images to see overlay
5. Go to Admin → Gallery to add more items

### 4. Test Sticky Buttons
1. From any page, see fixed buttons on right side:
   - ☎️ Phone button (calls the number)
   - 💬 WhatsApp button (opens WhatsApp)
   - ⬆️ Scroll to Top button (appears when scrolled down)

### 5. Test Mobile Menu
1. Resize browser to mobile width (~375px)
2. Click hamburger menu ☰
3. See navigation menu slide in
4. Click a link to close menu

## 🎨 Customizing the Website

### Change Company Phone Number
In `js/main.js`, find:
```javascript
const WHATSAPP_NUMBER = '6395290014';
const CALL_NUMBERS = ['6369529001', '9342017154'];
```
Replace with your numbers.

### Change Company Name/Slogan
Go to `index.html` and find:
```html
<h1>Shree Air Cons</h1>
<p class="slogan">Cooling Comfort, Reliable Repairs — We Fix It Right the First Time</p>
```
Edit these with your company info.

### Change Admin Password
1. Login to admin panel
2. Go to "Settings" tab
3. Change password
4. New password is saved

### Change Services Description
1. Edit `pages/services.html`
2. Find service card descriptions
3. Update text and details

### Change Colors
1. Open `css/style.css`
2. Find `:root` section at top
3. Change color codes:
```css
--primary-blue: #003D7A;      /* Main blue */
--secondary-blue: #1E90FF;    /* Light blue */
--accent-orange: #FF6B35;     /* Orange accent */
```

## 📞 Features Walkthrough

### Navigation Menu
- **Home**: Main landing page with services overview
- **About**: Company description, team, mission & vision
- **Services**: AC, Washing Machine, Refrigerator services
- **Gallery**: View completed work projects
- **Reviews**: See customer reviews and submit your own
- **Contact**: Booking form and contact information

### Sticky Buttons
- **Call Button (Red ☎️)**: Calls the primary phone number
- **WhatsApp Button (Green 💬)**: Opens WhatsApp chat
- **Scroll Top Button (Blue ⬆️)**: Appears when scrolled down, takes to top

### CTA Buttons
- **Book Service**: Opens booking form on Contact page
- **Call Now**: Initiates phone call
- **WhatsApp**: Starts WhatsApp chat

## 🗂️ File Organization

**Main Pages:**
- `index.html` - Homepage
- `pages/about.html` - About page
- `pages/services.html` - Services page
- `pages/gallery.html` - Gallery page
- `pages/reviews.html` - Reviews page
- `pages/contact.html` - Contact & booking page

**Admin:**
- `admin/index.html` - Admin dashboard

**Styles:**
- `css/style.css` - All styling and responsive design

**JavaScript:**
- `js/main.js` - Core functionality
- `js/reviews.js` - Review system
- `js/gallery.js` - Gallery system
- `js/contact.js` - Booking system
- `js/admin.js` - Admin panel

## 🔍 Troubleshooting

### Images Not Loading?
- Website uses SVG graphics (no images needed)
- If adding images, put them in `images/` folder
- Update image paths in HTML

### Mobile Menu Not Working?
- Clear browser cache
- Refresh page
- Check browser console for errors

### Admin Login Not Working?
- Confirm password: `admin123`
- Clear localStorage: Open DevTools → Application → Clear Storage
- Try incognito/private window

### Data Not Saving?
- Check if browser allows localStorage
- Try a different browser
- Disable browser privacy mode

### WhatsApp Not Opening?
- Need WhatsApp installed or web.whatsapp.com access
- Update phone number in `js/main.js`

## 💡 Tips & Tricks

1. **Test on Mobile**: Use Chrome DevTools (F12 → Device toggle)
2. **Inspect Elements**: Right-click → Inspect to see HTML/CSS
3. **Clear Cache**: Ctrl+Shift+Delete to clear browser data
4. **Export Data**: Use Admin → Export to backup all data
5. **Add Images**: Upload image URLs (not files) to gallery

## 🎯 Next Steps

1. ✅ Test all pages and features
2. ✅ Add your company real phone numbers
3. ✅ Change admin password
4. ✅ Upload company photos to gallery
5. ✅ Get reviews from customers
6. ✅ Deploy to web hosting

## 📧 Support Files

- `README.md` - Detailed documentation
- This file - Quick start guide

---

**Happy using the website! Contact support if you need help.**