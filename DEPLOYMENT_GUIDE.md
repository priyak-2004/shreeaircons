# Production Deployment Guide - Shree Air Cons

## Pre-Deployment Checklist

### Code Review
- [ ] All admin files removed
- [ ] No login/authentication pages
- [ ] No hardcoded URLs (use .env)
- [ ] Error handling implemented
- [ ] Database models created in correct location
- [ ] All imports use correct paths

### Configuration  
- [ ] .env file created with all required variables
- [ ] MongoDB credentials verified
- [ ] Cloudinary account and credentials obtained
- [ ] Gmail account with app password ready
- [ ] WhatsApp number updated
- [ ] CORS settings correct for domain
- [ ] Database name and collections verified

### Testing (Local)
- [ ] Backend starts without errors
- [ ] Frontend loads without console errors
- [ ] Gallery upload works
- [ ] Booking form submits successfully
- [ ] Email notifications received
- [ ] Reviews display instantly
- [ ] Responsive design on mobile

## Deployment Options

### Option 1: Local Server
Best for: Testing, small team

```bash
# Terminal 1: Backend
cd backend
npm install
npm start

# Terminal 2: Frontend
cd frontend
python -m http.server 3000
# OR
npx http-server -p 3000
```

### Option 2: Heroku (Backend)

```bash
# Install Heroku CLI
heroku login
heroku create your-app-name
git push heroku main

# Configure environment variables
heroku config:set MONGODB_URI=your_uri
heroku config:set CLOUDINARY_CLOUD_NAME=your_name
# ... set all .env variables
```

### Option 3: Vercel (Frontend)

```bash
# Deploy frontend
cd frontend
npm install -g vercel
vercel --prod

# Update FRONTEND_URL in backend .env
```

### Option 4: AWS EC2

1. Launch EC2 instance (Ubuntu)
2. Install Node.js and MongoDB
3. Clone repository
4. Install dependencies
5. Configure .env
6. Use PM2 for process management
7. Set up Nginx reverse proxy

```bash
# Install PM2
npm install -g pm2

# Start backend with PM2
pm2 start backend/server.js --name "shree-backend"
pm2 save
pm2 startup

# Nginx configuration
sudo apt-get install nginx
# Configure /etc/nginx/sites-enabled/default
```

### Option 5: DigitalOcean App Platform

1. Connect GitHub repository
2. Set environment variables
3. Specify start command: `npm start` (in backend)
4. Deploy

## Production Environment Variables

```env
NODE_ENV=production
PORT=5000
FRONTEND_URL=https://yourdomain.com
BACKEND_URL=https://api.yourdomain.com

# Database (Use cloud MongoDB)
MONGODB_URI=mongodb+srv://user:pass@cluster.mongodb.net/shreeaircons

# Cloudinary
CLOUDINARY_CLOUD_NAME=your_cloud
CLOUDINARY_API_KEY=your_key
CLOUDINARY_API_SECRET=your_secret

# Email
EMAIL_USER=your_email@gmail.com
EMAIL_PASSWORD=your_app_password
ADMIN_EMAIL=admin@yourdomain.com

# Other
WHATSAPP_NUMBER=your_number
JWT_SECRET=generate_secure_random_string
```

## Domain & SSL Setup

### Get Domain
- Register on GoDaddy, Namecheap, AWS Route53, etc.
- Update nameservers to point to deployment platform

### SSL Certificate
- Let's Encrypt (free)
- Cloudflare (free with domain)
- AWS Certificate Manager
- Automatic with Heroku/Vercel

## Database Backup

### MongoDB Atlas
```bash
# Automated backups on mongoDB Atlas
# Daily backup snapshots
# Point-in-time recovery
```

### Manual Backup
```bash
# Export database
mongodump --uri="mongodb+srv://..." --out=backup

# Import database
mongorestore --uri="mongodb+srv://..." backup
```

## Monitoring & Logging

```bash
# PM2 Monitoring
pm2 start server.js
pm2 logs
pm2 status

# Application Insights (Azure)
# CloudWatch (AWS)
# Datadog (Cloud monitoring)
```

## Performance Optimization

- [ ] Enable GZIP compression
- [ ] Cache static files (CSS, JS, images)
- [ ] Use CDN for media (already using Cloudinary)
- [ ] Optimize database queries
- [ ] Implement rate limiting
- [ ] Use environment-specific configurations

## Security Checklist

- [ ] HTTPS/SSL enabled
- [ ] Sensitive data in .env (not in code)
- [ ] CORS properly configured
- [ ] Input validation on all endpoints
- [ ] SQL injection prevention (using Mongoose)
- [ ] XSS prevention (escaping user input)
- [ ] CSRF tokens if needed
- [ ] Regular security audits
- [ ] Database backups automated
- [ ] Access logging enabled

## Scaling Strategies

### Load Balancing
- Nginx reverse proxy
- HAProxy
- AWS ELB

### Caching
- Redis for session storage
- Database query caching
- Client-side caching

### Database Scaling
- MongoDB Atlas automatic scaling
- Read replicas for analytics
- Sharding for large datasets

## Troubleshooting Production

### 500 Errors
- Check server logs: `pm2 logs`
- Verify .env variables
- Check database connection
- Review recent code changes

### Slow Performance
- Monitor database queries
- Check file upload sizes
- Verify Cloudinary limits
- Review API response times

### High Memory Usage
- Check for memory leaks
- Monitor PM2 processes
- Restart services periodically

### CORS Issues
- Verify FRONTEND_URL in .env
- Check CORS configuration in server.js
- Verify domain in deployment

## Rollback Procedure

```bash
# If using Heroku
heroku releases
heroku rollback v123

# If using Git
git revert <commit_hash>
git push heroku main
```

## Post-Deployment

1. Monitor error logs
2. Test all features
3. Verify email notifications
4. Check file uploads
5. Monitor database size
6. Set up automated backups
7. Configure monitoring alerts
8. Document any custom changes

## Support & Maintenance

- Monthly security updates
- Database cleanup (old files)
- Performance monitoring
- Backup verification
- Error logging review
- User feedback collection

---

**Ready for production!** 🚀

For questions, contact: admin@shreeaircons.com
