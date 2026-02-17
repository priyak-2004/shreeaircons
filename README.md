# Shree Air Cons - Full Stack Web Application

Professional appliance repair service website for Shree Air Cons (SAC) built with Spring Boot, MySQL, and Bootstrap.

## Features

### Frontend
- **Responsive Design**: Mobile-first, responsive layout for all devices
- **Multi-language Support**: English and Tamil language switching
- **Modern UI**: Blue and white professional color theme
- **Service Showcase**: AC, Refrigerator, and Washing Machine services
- **Booking System**: Easy online service booking with status tracking
- **Gallery**: Before/after images of completed work
- **Reviews**: Customer reviews with rating system
- **Contact Page**: Location, phone, email, and embedded Google Maps
- **Sticky Action Buttons**: Quick call and WhatsApp access

### Backend
- **Spring Boot 3.1.5**: Modern Java framework
- **MySQL Database**: Reliable relational database
- **REST APIs**: Complete API for all operations
- **Authentication**: User login system for admin panel
- **Notification System**: WhatsApp notifications for bookings
- **Admin Dashboard**: Comprehensive administrative interface

### Admin Features
- **Booking Management**: View, update, and manage all bookings
- **Review Moderation**: Approve/reject customer reviews
- **Gallery Management**: Upload and manage service images
- **Dashboard Statistics**: Real-time booking and review statistics
- **Dual Admin Roles**: Developer Admin and Client Admin

## Technology Stack

- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap 5
- **Backend**: Java Spring Boot 3.1.5
- **Database**: MySQL 8.0+
- **Build Tool**: Maven
- **Server**: Apache Tomcat (embedded)

## Project Structure

```
ShreeAirCons/
├── src/
│   ├── main/
│   │   ├── java/com/shreeaircons/
│   │   │   ├── controller/        # REST and View Controllers
│   │   │   ├── entity/            # JPA Entities
│   │   │   ├── repository/        # Data Access Layer
│   │   │   ├── service/           # Business Logic Layer
│   │   │   ├── config/            # Configuration Classes
│   │   │   └── util/              # Utility Classes
│   │   ├── resources/
│   │   │   ├── templates/         # Thymeleaf and HTML templates
│   │   │   ├── static/
│   │   │   │   ├── css/           # Stylesheets
│   │   │   │   ├── js/            # JavaScript files
│   │   │   │   └── images/        # Images and assets
│   │   │   ├── application.properties  # Configuration
│   │   │   └── db-init.sql        # Database initialization
│   └── test/
└── pom.xml                        # Maven dependencies
```

## Installation & Setup

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.8+

### Step 1: Database Setup

1. Create the database:
```bash
mysql -u root -p < src/main/resources/db-init.sql
```

2. Or manually in MySQL:
```sql
CREATE DATABASE shreeaircons;
USE shreeaircons;
-- Run the SQL scripts from db-init.sql
```

### Step 2: Update Configuration

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/shreeaircons
spring.datasource.username=root
spring.datasource.password=your_password

# Admin Configuration
admin.whatsapp.number=6395290014
admin.email=shreeaircons2026@gmail.com

# Mail Configuration (for email notifications)
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### Step 3: Build & Run

1. Build the project:
```bash
mvn clean package
```

2. Run the application:
```bash
mvn spring-boot:run
```

Or run the JAR file:
```bash
java -jar target/ShreeAirCons-1.0.0.jar
```

3. Access the application:
- **Website**: http://localhost:8080
- **Admin Dashboard**: http://localhost:8080/admin/dashboard

## API Endpoints

### Public APIs
- `GET /` - Home page
- `GET /services` - Services page
- `GET /booking` - Booking page
- `GET /gallery` - Gallery page
- `GET /reviews` - Reviews page
- `GET /contact` - Contact page

### Booking APIs
- `POST /api/bookings` - Create new booking
- `GET /api/bookings/{id}` - Get booking details
- `GET /api/bookings/phone/{phoneNumber}` - Get bookings by phone

### Review APIs
- `POST /api/reviews` - Submit review
- `GET /api/reviews/approved` - Get approved reviews
- `GET /api/reviews/rating` - Get average rating

### Gallery APIs
- `GET /api/gallery` - Get all gallery images
- `GET /api/gallery/service/{serviceType}` - Get images by service type

### Admin APIs
- `GET /api/admin/bookings` - Get all bookings
- `PUT /api/admin/bookings/{id}` - Update booking
- `DELETE /api/admin/bookings/{id}` - Delete booking
- `GET /api/admin/reviews/pending` - Get pending reviews
- `PUT /api/admin/reviews/{id}/approve` - Approve review
- `POST /api/admin/gallery/upload` - Upload gallery image
- `GET /api/admin/dashboard/stats` - Get dashboard statistics

### Authentication APIs
- `POST /api/auth/login` - Admin login
- `POST /api/auth/register` - User registration

## Default Admin Credentials

- **Email**: shreeaircons2026@gmail.com
- **Password**: Admin@123456 (Change on first login!)
- **Role**: CLIENT_ADMIN

## Customization

### Adding Company Logo
1. Place your logo file in `src/main/resources/static/images/`
2. Update the logo path in HTML templates:
```html
<img src="/images/your-logo.png" alt="SAC Logo">
```

### Changing Company Colors
Edit `src/main/resources/static/css/style.css`:
```css
:root {
    --primary-blue: #1e5ba8;  /* Change primary color */
    --light-blue: #2d7ec7;    /* Change light color */
    --dark-blue: #0f3a6b;     /* Change dark color */
    /* ... other colors ... */
}
```

### WhatsApp Integration
To enable WhatsApp notifications:
1. Set up WhatsApp Business API account
2. Update `src/main/java/com/shreeaircons/service/NotificationService.java`
3. Replace placeholder with actual WhatsApp API credentials

### Email Configuration
For email notifications, configure SMTP:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-specific-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Features in Detail

### Booking System
- Customers can request service with name, phone, address, and problem description
- Admin receives WhatsApp notification
- Admin sets service date/time
- Customer receives status update
- Booking history is stored

### Admin Dashboard
- View all bookings with filters
- Approve/reject customer reviews
- Upload and manage gallery images
- View real-time statistics
- Track service completion

### Multi-language Support
- Toggle between English and Tamil
- All content translatable
- Language preference can be saved (with future enhancement)

### Responsive Design
- Mobile-first approach
- Tested on all screen sizes
- Optimized images for fast loading
- Touch-friendly buttons and forms

## Security Considerations

- Change default admin password immediately
- Use HTTPS in production
- Validate all inputs on backend
- Use environment variables for sensitive data
- Keep dependencies updated
- Implement rate limiting for APIs

## Performance Optimization

- Images are optimized for web
- CSS and JavaScript are minified
- Database queries use indexes
- Pagination implemented for large datasets
- Caching enabled for static assets

## Future Enhancements

- Payment integration (Razorpay, PayPal)
- Customer login and account management
- Invoice generation
- Email notifications
- SMS notifications
- Service reminder alerts
- Technician assignment and tracking
- Real-time chat support
- Mobile app (Android/iOS)
- Advanced analytics and reporting

## Troubleshooting

### Port Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### Database Connection Error
- Verify MySQL is running
- Check database credentials in application.properties
- Ensure database exists

### Images Not Loading
- Check image path in HTML
- Verify files exist in `static/images/`
- Check browser console for 404 errors

## Support & Contact

For issues or support:
- Email: shreeaircons2026@gmail.com
- Phone: 6369529001 / 9342017154
- Service Radius: 65 km from Chennai

## License

All rights reserved © 2026 Shree Air Cons

## Contributing

Contact the development team for contribution guidelines.

---

**Version**: 1.0.0  
**Last Updated**: February 2026
