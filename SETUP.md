# Shree Air Cons - Installation & Setup Guide

## Complete Project Setup Instructions

This guide will help you set up and run the Shree Air Cons web application on your local machine.

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Java Development Kit (JDK) 17+**
   - Download: https://www.oracle.com/java/technologies/downloads/
   - Verify: `java -version`

2. **Maven 3.8+**
   - Download: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **MySQL Server 8.0+**
   - Download: https://dev.mysql.com/downloads/mysql/
   - Windows: https://dev.mysql.com/downloads/windows/installer/

4. **Git** (Optional, for version control)
   - Download: https://git-scm.com/

## Step 1: Project Setup

### 1.1 Extract or Clone the Project

```bash
# If you have a zip file, extract it
unzip ShreeAirCons.zip
cd ShreeAirCons

# Or if using git
git clone <repository-url>
cd ShreeAirCons
```

### 1.2 Verify Project Structure

Check that you have:
```
ShreeAirCons/
├── src/
│   ├── main/
│   │   ├── java/com/shreeaircons/...
│   │   ├── resources/
│   │   │   ├── templates/...
│   │   │   ├── static/...
│   │   │   ├── application.properties
│   │   │   └── db-init.sql
│   └── test/
├── pom.xml
├── README.md
└── SETUP.md
```

## Step 2: Database Configuration

### 2.1 Start MySQL Server

**Windows:**
```bash
# MySQL should auto-start or you can start it via Services
# Or if installed on system path:
mysqld
```

**Linux/Mac:**
```bash
brew services start mysql
# or
sudo service mysql start
```

### 2.2 Create Database and Tables

**Option A: Using MySQL Command Line**

```bash
# Connect to MySQL
mysql -u root -p

# Enter your password when prompted
# Then paste the following SQL:

CREATE DATABASE shreeaircons;
USE shreeaircons;

-- Copy and paste all SQL from src/main/resources/db-init.sql here
-- Or use the command below

exit
```

**Option B: Using SQL Script File**

```bash
# Navigate to project directory, then:
mysql -u root -p < src/main/resources/db-init.sql

# When prompted, enter your MySQL password
```

**Option C: Using MySQL Workbench or phpMyAdmin**

1. Open MySQL Workbench or phpMyAdmin
2. Create new database: `shreeaircons`
3. Import SQL file: `src/main/resources/db-init.sql`

### 2.3 Verify Database Creation

```bash
mysql -u root -p
mysql> USE shreeaircons;
mysql> SHOW TABLES;
```

You should see these tables:
- bookings
- gallery
- reviews
- service_types
- users

## Step 3: Configure Application

### 3.1 Edit Database Configuration

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/shreeaircons
spring.datasource.username=root
spring.datasource.password=your_mysql_password   # Change this!

spring.jpa.hibernate.ddl-auto=update
```

**Important**: Replace `your_mysql_password` with your actual MySQL root password.

### 3.2 Configure Admin Settings

In the same file, update:

```properties
# Admin Configuration
admin.whatsapp.number=6395290014
admin.email=shreeaircons2026@gmail.com

# JWT Secret (Change this in production!)
jwt.secret=shreeaircons_secret_key_2026_change_in_production
```

### 3.3 (Optional) Email Configuration

For email notifications, configure SMTP:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-specific-password  # Use App Password, not regular password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

**For Gmail Users:**
1. Enable 2-Factor Authentication in Gmail
2. Generate App Password: https://myaccount.google.com/apppasswords
3. Use the App Password in the configuration

## Step 4: Build the Project

### 4.1 Download Dependencies

```bash
# Navigate to project root directory
cd ShreeAirCons

# Clean and build
mvn clean install
```

This will download all required dependencies (may take 2-5 minutes on first run).

### 4.2 Check for Build Errors

```bash
# If there are errors, run:
mvn clean package

# Check the output for any compilation errors
# Common errors:
# - Database connection issues
# - Port already in use
# - Java version mismatch
```

## Step 5: Run the Application

### 5.1 Using Maven

```bash
# From project root directory:
mvn spring-boot:run
```

### 5.2 Using JAR File

```bash
# First, build the JAR
mvn clean package

# Run the JAR
java -jar target/ShreeAirCons-1.0.0.jar
```

### 5.3 Expected Output

You should see something like:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_|\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                   (v3.1.5)

2026-02-17 10:30:00.000  INFO 1234 --- [main] com.shreeaircons.ShreeAirConsApplication  : Starting ShreeAirConsApplication
...
2026-02-17 10:30:05.123  INFO 1234 --- [main] ... Tomcat started on port(s): 8080 (http)
2026-02-17 10:30:05.456  INFO 1234 --- [main] com.shreeaircons.ShreeAirConsApplication  : Started in 5.234 seconds
```

## Step 6: Access the Application

### 6.1 Website

Open your browser and go to:
```
http://localhost:8080
```

You should see the Shree Air Cons homepage.

### 6.2 Admin Dashboard

Login to admin panel:
```
URL: http://localhost:8080/admin/dashboard

Email: shreeaircons2026@gmail.com
Password: Admin@123456
```

**IMPORTANT: Change the password immediately after first login!**

### 6.3 Test Features

1. **Homepage**: Check all sections load correctly
2. **Booking**: Submit a test booking
3. **Reviews**: Submit a test review
4. **Gallery**: Check responsive design
5. **Contact**: Verify all contact details display
6. **Admin Dashboard**: Check all statistics load

## Troubleshooting

### Issue: Port 8080 Already in Use

**Solution**: Change port in `application.properties`

```properties
server.port=8081
```

Then access: `http://localhost:8081`

### Issue: Database Connection Error

```
com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure
```

**Solutions:**
1. Verify MySQL is running
2. Check database credentials
3. Verify database exists: `mysql -u root -p -e "SHOW DATABASES;"`

### Issue: Java Version Error

```
UnsupportedClassVersionError: Unsupported major.minor version 62.0
```

**Solution**: Your Java version is too old. Install JDK 17+
```bash
java -version
# Should show: openjdk version "17" or higher
```

### Issue: Maven Build Fails

```bash
# Clear cache and rebuild
mvn clean install -U

# If still failing, check Java path
mvn --version
```

### Issue: CSS/JS Not Loading

1. Clear browser cache (Ctrl+Shift+Delete)
2. Try incognito/private mode
3. Check browser console for errors (F12)

### Issue: Images Not Displaying

1. Check image paths in HTML (should be `/images/filename`)
2. Verify files exist in `src/main/resources/static/images/`
3. Restart the application after adding images

## Development Tips

### 1. Hot Reload

To enable automatic reload during development:

```bash
mvn spring-boot:run
```

The application will reload when you change Java files.

### 2. Live CSS/JS Reload

CSS and JS files update immediately in browser (just refresh).

### 3. Database Query Logs

To see SQL queries, add to `application.properties`:

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
```

### 4. Debug Mode

Run with debugging available:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"
```

## Production Deployment

### Before Going Live

1. **Change Security Credentials**
   ```properties
   jwt.secret=your-very-secure-random-string
   ```

2. **Update Database Password**
   - Don't use default passwords
   - Use strong passwords (minimum 12 characters)

3. **Configure HTTPS**
   - Get SSL certificate
   - Update `application.properties` with SSL settings

4. **Set Production Properties**
   ```properties
   logging.level.root=INFO
   spring.jpa.hibernate.ddl-auto=validate  # Don't auto-create tables
   ```

5. **Optimize Database**
   - Add indexes
   - Backup database regularly
   - Monitor performance

6. **Setup WhatsApp API**
   - Get official WhatsApp Business API access
   - Update `NotificationService.java` with API credentials

7. **Email Configuration**
   - Use professional email service
   - Configure SMTP properly
   - Test email sending

## Starting the Application (Quick Reference)

```bash
# 1. Navigate to project
cd ShreeAirCons

# 2. Start MySQL (if not auto-starting)
# Windows: Start MySQL from Services
# Linux: sudo service mysql start

# 3. Run application
mvn spring-boot:run

# 4. Open browser
# http://localhost:8080
```

## Common Ports

- Application: 8080
- MySQL: 3306
- Admin Dashboard: 8080/admin/dashboard
- API Endpoints: 8080/api/**

## Files to Modify for Customization

1. **Company Logo**: Place in `src/main/resources/static/images/`
   - Update path in all HTML templates

2. **Colors**: Edit `src/main/resources/static/css/style.css`
   - Change `:root` CSS variables

3. **Phone Numbers**: Update in:
   - `application.properties`
   - HTML templates (`contact.html`)

4. **Coordinates**: Google Maps in `contact.html`
   - Update iframe coordinates

## Next Steps

After initial setup:

1. Change admin password
2. Add company logo
3. Customize colors (optional)
4. Update contact information
5. Upload service images
6. Configure WhatsApp Business API
7. Test all features thoroughly
8. Deploy to production

## Support & Contact

For issues or questions:
- Email: shreeaircons2026@gmail.com
- Phone: 6369529001 / 9342017154

## Documentation

- [README.md](README.md) - Project overview
- [API Documentation](docs/API.md) - API endpoints
- [Architecture](docs/ARCHITECTURE.md) - System design

---

**Last Updated**: February 2026
**Version**: 1.0.0

Happy coding! 🚀
