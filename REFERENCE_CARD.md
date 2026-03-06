# 🎯 REFERENCE CARD - Customer-Only System

## One-Page Cheat Sheet

---

## ✅ What You Get Now

| Feature | Status | Details |
|---------|--------|---------|
| **Gallery Upload** | ✅ Public | No login, instant display |
| **Bookings** | ✅ Public + Email | Auto-email to owner |
| **Reviews** | ✅ Public + Instant | No approval needed |
| **Admin Login** | ❌ Removed | Completely gone |
| **JWT Tokens** | ❌ Removed | Not needed |
| **Approvals** | ❌ Removed | Everything instant |

---

## 🚀 Quick Start

```bash
# 1. Configure
# Edit backend/.env with your credentials

# 2. Start
cd backend
npm run dev

# 3. Visit
# http://localhost:5000/api/health
```

---

## 📡 API Calls

### Gallery
```javascript
// Get all photos
apiClient.getGallery()

// Upload photo
apiClient.uploadGalleryItem(title, category, type, file)

// Delete photo
apiClient.deleteGalleryItem(id)
```

### Bookings
```javascript
// Create booking (auto-emails owner)
apiClient.createBooking({ name, phone, address, service, description })

// View all bookings
apiClient.getBookings()

// Delete booking
apiClient.deleteBooking(id)
```

### Reviews
```javascript
// Create review (instant display)
apiClient.createReview({ name, rating, message })

// View all reviews
apiClient.getAllReviews()

// Delete review
apiClient.deleteReview(id)
```

---

## 📧 Email Configuration

```env
EMAIL_USER=shreeaircons2026@gmail.com
EMAIL_PASS=your_app_specific_password
ADMIN_EMAIL=shreeaircons2026@gmail.com
```

When customer books → Email sent to ADMIN_EMAIL with:
- Customer name
- Phone number
- Full address
- Service type
- Description
- Booking ID

---

## 🎨 Frontend (No Changes!)

Same HTML, CSS, UI as before. Just works with public API now.

Load order:
```html
<script src="/js/apiClient.js"></script>      <!-- First -->
<script src="/js/main.js"></script>
<script src="/js/gallery.js"></script>
<script src="/js/contact.js"></script>
<script src="/js/reviews.js"></script>        <!-- Last -->
```

---

## ✨ Key Behavior Changes

| Operation | Before | Now |
|-----------|--------|-----|
| Upload gallery | Admin only | ✅ Anyone |
| Photo appears | Instant | ✅ Instant |
| Delete photo | Admin only | ✅ Anyone |
| Book service | Form only | ✅ Form + Email |
| Review shows | After approval | ✅ Instantly |
| Need password? | Yes | ❌ No |

---

## ✅ Final Checklist

- [ ] `.env` has real credentials
- [ ] Backend runs: `npm run dev`
- [ ] Health check: `/api/health` works
- [ ] Upload works without login
- [ ] Email received on booking
- [ ] Reviews show immediately
- [ ] No console errors

---

## 📞 Support

See these files for help:
- **QUICK_START_CUSTOMER.md** - Quick tests
- **CUSTOMER_ONLY_GUIDE.md** - Full features
- **CONVERSION_COMPLETE.md** - What changed

---

## 🎉 Deploy!

```bash
# Backend ready
npm run dev

# Frontend ready (no changes needed)

# Deploy to production
# Everything is public - no special auth needed
```

**Status: READY TO LAUNCH** 🚀
