# Pre-Launch Checklist - Shree Air Cons

## ✅ Development Setup Checklist

Before launching the application, ensure you've completed all these steps:

### Prerequisites (Pre-Installation)
- [ ] Java JDK 17+ installed and configured
- [ ] Maven 3.8+ installed and configured
- [ ] MySQL 8.0+ installed and running
- [ ] Text editor or IDE (VS Code, IntelliJ, Eclipse)
- [ ] Git installed (optional)
- [ ] Postman installed (for API testing)

### Database Setup
- [ ] MySQL server is running
- [ ] Created database: `shreeaircons`
- [ ] Ran db-init.sql script
- [ ] Verified all 5 tables exist
- [ ] Verified sample data is loaded
- [ ] Updated database password in application.properties

### Application Configuration
- [ ] Downloaded/extracted project files
- [ ] Updated `application.properties` with database credentials
- [ ] Set admin phone number: 6395290014
- [ ] Set admin email: shreeaircons2026@gmail.com
- [ ] Changed JWT secret (optional for development)
- [ ] Configured mail settings (optional)

### Build & Deployment
- [ ] Installed Maven dependencies: `mvn clean install`
- [ ] Project builds successfully: `mvn clean package`
- [ ] No compilation errors
- [ ] No test failures (if running tests)
- [ ] JAR file created in target/ directory

### Company Customization
- [ ] Added company logo to `/static/images/`
- [ ] Updated company phone numbers in all files
- [ ] Updated company address
- [ ] Updated contact email
- [ ] Reviewed and adjusted color theme if desired
- [ ] Added service images to `/static/images/`

### Testing Before Launch
- [ ] Application starts without errors
- [ ] Can access http://localhost:8080
- [ ] Homepage loads with all sections
- [ ] Navigation menu works
- [ ] Language switcher toggles
- [ ] Sticky buttons (Call, WhatsApp) visible

### Feature Testing
- [ ] Booking form submits successfully
- [ ] Booking status check works
- [ ] Reviews can be submitted
- [ ] Gallery displays images
- [ ] Contact page displays all info
- [ ] Google Maps embed works
- [ ] Admin dashboard accessible
- [ ] Admin can update bookings
- [ ] Admin can approve/reject reviews
- [ ] Admin can upload images

### Performance Verification
- [ ] Page load time acceptable (<3 seconds)
- [ ] Images optimized and small size
- [ ] No console errors (F12)
- [ ] Mobile responsive working
- [ ] All fonts loading correctly

### Security Verification
- [ ] Default password changed in system
- [ ] Database credentials secure
- [ ] API endpoints accessible
- [ ] Admin login functional
- [ ] Form inputs validated
- [ ] No sensitive data in code

---

## 📋 Post-Launch Checklist

After the application is live, complete these tasks:

### Week 1
- [ ] Monitor application for errors
- [ ] Check database size
- [ ] Review access logs
- [ ] Test email notifications
- [ ] Test WhatsApp integration
- [ ] Verify all pages load correctly

### Week 2-4
- [ ] Collect customer feedback
- [ ] Review booking data
- [ ] Monitor admin usage
- [ ] Check performance metrics
- [ ] Plan any immediate fixes

### Ongoing Maintenance
- [ ] Daily database backups
- [ ] Weekly log review
- [ ] Monthly dependency updates
- [ ] Monthly security review
- [ ] Quarterly performance audit

---

## 🔧 Configuration Checklist

### Essential Settings

```properties
✅ Database Configuration
   [ ] spring.datasource.url
   [ ] spring.datasource.username
   [ ] spring.datasource.password

✅ Admin Settings
   [ ] admin.whatsapp.number
   [ ] admin.email
   [ ] app.name

✅ Server Settings
   [ ] server.port
   [ ] server.servlet.context-path

✅ Logging Settings
   [ ] logging.level.root
   [ ] logging.level.com.shreeaircons
```

### Optional Settings

```properties
✅ Email Configuration
   [ ] spring.mail.host
   [ ] spring.mail.port
   [ ] spring.mail.username
   [ ] spring.mail.password

✅ JWT Configuration
   [ ] jwt.secret
   [ ] jwt.expiration

✅ File Upload
   [ ] spring.servlet.multipart.max-file-size
   [ ] spring.servlet.multipart.max-request-size
```

---

## 🚨 Common Startup Issues

### Issue: Port 8080 Already in Use
```
✅ Solution:
[ ] Stop other applications using port 8080
[ ] Or change port in application.properties
[ ] server.port=8081
```

### Issue: Database Connection Failed
```
✅ Solution:
[ ] Verify MySQL is running
[ ] Check database credentials
[ ] Verify database exists
[ ] Run db-init.sql again
```

### Issue: Images Not Displaying
```
✅ Solution:
[ ] Check image paths in HTML
[ ] Verify files in /static/images/
[ ] Clear browser cache
[ ] Restart application
```

### Issue: Admin Login Fails
```
✅ Solution:
[ ] Verify user in database: 
    SELECT * FROM users;
[ ] Check email case-sensitivity
[ ] Verify password matches
[ ] Reset password in database
```

---

## 📞 Support Resources

### Documentation Files
- [ ] README.md - Read first
- [ ] SETUP.md - Installation guide
- [ ] API.md - API reference
- [ ] ARCHITECTURE.md - System design
- [ ] PROJECT_SUMMARY.md - Overview

### Online Resources
- [ ] Spring Boot Documentation
- [ ] MySQL Documentation
- [ ] Bootstrap Documentation
- [ ] JavaScript MDN Docs

### Contact Information
```
Email: shreeaircons2026@gmail.com
Phone: 6369529001 / 9342017154
Service Area: 65 km from Chennai
Working Hours: 8 AM - 10 PM
```

---

## 📊 Quality Assurance Checklist

### Code Quality
- [ ] No console errors
- [ ] No console warnings
- [ ] All functions working
- [ ] All links functional
- [ ] All forms validated

### Performance
- [ ] Page load time < 3s
- [ ] API response time < 500ms
- [ ] Images optimized
- [ ] No memory leaks
- [ ] Database queries optimized

### User Experience
- [ ] Mobile responsive
- [ ] Touch-friendly buttons
- [ ] Clear UI/UX
- [ ] Proper error messages
- [ ] Smooth navigation

### Security
- [ ] Inputs validated
- [ ] SQL injection prevented
- [ ] XSS prevention
- [ ] CSRF protection (if applicable)
- [ ] Sensitive data encrypted

### Accessibility
- [ ] Keyboard navigation
- [ ] Screen reader compatible
- [ ] Color contrast acceptable
- [ ] Font sizes readable
- [ ] Alt text on images

---

## 🎯 Launch Day Checklist

### Morning (Before Going Live)
- [ ] Full system test
- [ ] Database backup
- [ ] Application restart
- [ ] Load test
- [ ] Security test
- [ ] Smoke test all features

### During Launch
- [ ] Monitor server metrics
- [ ] Monitor error logs
- [ ] Monitor application logs
- [ ] Test critical paths
- [ ] Verify payment (if applicable)
- [ ] Test support channels

### Evening (After Launch)
- [ ] Analyze day's data
- [ ] Review error logs
- [ ] Check user feedback
- [ ] Document issues found
- [ ] Plan fixes if needed
- [ ] Schedule follow-up

---

## 📈 Monitoring Checklist

### Daily Tasks
- [ ] Check application health
- [ ] Review error logs
- [ ] Monitor database size
- [ ] Check uptime
- [ ] Verify backups

### Weekly Tasks
- [ ] Review user activity
- [ ] Analyze booking trends
- [ ] Check system performance
- [ ] Review security logs
- [ ] Plan improvements

### Monthly Tasks
- [ ] Backup database
- [ ] Update dependencies
- [ ] Review analytics
- [ ] Security audit
- [ ] Performance optimization

---

## ✨ Final Sign-Off

Project Lead:
- Name: ________________
- Date: ________________
- Signature: ________________

QA Lead:
- Name: ________________
- Date: ________________
- Signature: ________________

Development Lead:
- Name: ________________
- Date: ________________
- Signature: ________________

---

## 📝 Notes & Comments

```
Use this space for any additional notes, concerns, or special requirements:

_________________________________________________________________

_________________________________________________________________

_________________________________________________________________

_________________________________________________________________

_________________________________________________________________
```

---

**Project**: Shree Air Cons - Full Stack Web Application
**Version**: 1.0.0
**Creation Date**: February 17, 2026
**Status**: Ready for Launch

Remember: A thorough pre-launch checklist prevents issues in production!
