# Shree Air Cons - Professional Appliance Repair Website

A modern, fully responsive website for **Shree Air Cons (SAC)**, a professional appliance repair service company in Chennai.

## 🚀 Features

### Public Website Features
- **Responsive Design**: Works perfectly on mobile, tablet, and desktop devices
- **Multi-page Structure**: Home, About, Services, Gallery, Reviews, Contact pages
- **Modern UI**: Professional blue and white color theme with smooth animations
- **Dynamic Services**: AC services detail expansion showing 10+ services
- **Service Booking**: Complete booking form with WhatsApp integration
- **Customer Reviews**: Submit and view approved customer reviews with star ratings
- **Gallery**: Display completed work with hover effects and filtering
- **Sticky Buttons**: Fixed call and WhatsApp buttons for easy access
- **Google Maps Integration**: Embedded location map with 65 km service radius
- **Fast Loading**: Optimized SVG graphics instead of heavy images
- **SEO Friendly**: Proper meta tags and semantic HTML

### Admin Panel Features
- **Secure Login**: Demo login with password protection (default: admin123)
- **Dashboard**: Overview of bookings, reviews, gallery items, and statistics
- **Gallery Management**: Upload and delete gallery items by category
- **Booking Management**: View, confirm, or delete service bookings
- **Review Management**: Approve or reject customer reviews before publishing
- **Settings**: Change admin password
- **Data Export**: Export all data as JSON backup
- **Data Clear**: Clear all data (with safety confirmation)

## 📁 File Structure

```
d:\shree\
├── index.html              # Home page
├── pages/
│   ├── about.html         # About page
│   ├── services.html      # Services page
│   ├── gallery.html       # Gallery page
│   ├── reviews.html       # Reviews page
│   └── contact.html       # Contact/Booking page
├── admin/
│   ├── index.html         # Admin panel
│   └── admin.js           # Admin functionality
├── css/
│   └── style.css          # Main stylesheet
├── js/
│   ├── main.js            # Common functionality
│   ├── reviews.js         # Reviews functionality
│   ├── gallery.js         # Gallery functionality
│   └── contact.js         # Contact/Booking functionality
└── images/                # Images folder (for future use)
```

## 🎯 Quick Start

### Option 1: Open in Browser
1. Navigate to `d:\shree` folder
2. Double-click `index.html` to open the website

### Option 2: Using Local Server
For better performance, use a local server:

**Using Python (3.x):**
```bash
cd d:\shree
python -m http.server 8000
```

**Using Node.js (with http-server):**
```bash
cd d:\shree
npx http-server
```

Then open `http://localhost:8000` in your browser

## 👤 Admin Access

1. Go to `http://localhost:8000/admin/`
2. Login with password: `admin123`
3. Access all admin features from the dashboard

## 📞 Company Information

- **Name**: Shree Air Cons (SAC)
- **Phone**: 6369529001, 9342017154
- **WhatsApp**: 6395290014
- **Address**: No 2 Rajiv Gandhi Street, Varadharajapuram, Township Town Panchayat Office, Paadasalai Road, Thirumazhisai, Chennai – 600123
- **Service Radius**: 65 km from Chennai

## 🎨 Color Scheme

- **Primary Blue**: #003D7A
- **Secondary Blue**: #1E90FF
- **Light Blue**: #E8F4F8
- **Accent Orange**: #FF6B35
- **White**: #FFFFFF
- **Dark Text**: #333333

## 💾 Data Storage

The website uses browser's localStorage to store:
- Gallery items
- Service bookings
- Customer reviews
- Admin settings

**Note**: Data is stored locally in the browser and will be lost if browser data is cleared. For production use, implement a backend database.

## 🔧 Key Features Explained

### 1. Service Booking
- Fill the booking form with customer details
- Select service type (AC, Washing Machine, Refrigerator)
- Automatically sends WhatsApp message to admin
- Shows confirmation modal with booking details

### 2. Dynamic AC Services
- Click "View Services" button on AC card
- Shows detailed list of 10+ AC services
- Expandable/collapsible for better UX

### 3. Star Ratings
- Click stars to give rating (1-5)
- Hover effects for interactivity
- Visual feedback

### 4. Gallery Filtering
- Filter gallery by service type
- Smooth transitions between filters
- Hover overlay with item details

### 5. Admin Dashboard
- Real-time statistics
- Quick approval/rejection of reviews
- Booking status management
- Data export functionality

## 🌐 Browser Compatibility

- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+
- Mobile browsers (iOS Safari, Chrome Mobile)

## ⚙️ Customization

### Change Company Information
Edit these files:
- `index.html` - Company name, slogan, contact info
- `pages/contact.html` - Contact details, WhatsApp number
- `pages/about.html` - Company description

### Change Colors
Edit `css/style.css`:
```css
:root {
    --primary-blue: #003D7A;
    --secondary-blue: #1E90FF;
    --accent-orange: #FF6B35;
    /* ... other colors ... */
}
```

### Change Logo
Replace SVG logos in HTML files with actual image file:
```html
<img src="images/logo.png" alt="Logo" height="50">
```

## 🔒 Security Notes

- Default admin password: `admin123` (Change immediately for production!)
- Password is stored in localStorage (not secure for production)
- For production, implement server-side authentication
- All data is stored in browser (use backend database for production)

## 📱 Mobile Optimization

- Fully responsive hamburger menu
- Touch-friendly buttons and forms
- Optimized images and SVG graphics
- Mobile-first design approach
- Fast loading on slow connections

## 🚀 Performance

- Lightweight SVG graphics instead of PNG/JPG
- CSS animations for smooth transitions
- Lazy loading for images
- Minified CSS and optimized JavaScript
- No external dependencies (self-contained)

## 📝 License

This website template is created for Shree Air Cons. Please customize it with your own company information.

## 💡 Future Enhancements

- Backend database integration
- Email notifications
- SMS notifications
- Payment gateway integration
- Video demonstrations
- Before/After gallery slider
- Live chat support
- SEO optimization
- Google Analytics integration
- Multi-language support

## 🤝 Support

For issues or customization requests, refer to the file structure and comments in the code.

---

**Version**: 1.0  
**Last Updated**: 2024  
**Created for**: Shree Air Cons (SAC)