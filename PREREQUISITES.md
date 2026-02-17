# Prerequisites Installation Guide

Your system needs the following tools to run the Shree Air Cons application:

## ❌ Current Status
- ✅ Java found: Java 8 (version 1.8.0_481)
- ❌ Java 17+ required (mismatch)
- ❌ Maven not installed
- ❌ MySQL not installed

## Required Tools

### 1. Install Java 17 or Later
**Current:** Java 8  
**Required:** Java 17+

#### Option A: Download Java 17 (Recommended)
1. Visit: https://www.oracle.com/java/technologies/downloads/#java17
2. Download Java 17 (Windows x64 installer)
3. Run installer and follow setup
4. Add to PATH:
   - Right-click "This PC" → Properties
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Add JDK installation path to PATH

**Verify installation:**
```powershell
java -version
```
Should show version 17 or higher.

### 2. Install Maven
**Required:** Maven 3.6+

#### Option A: Direct Download
1. Visit: https://maven.apache.org/download.cgi
2. Download: apache-maven-3.9.6-bin.zip
3. Extract to: `C:\Program Files\apache-maven-3.9.6`
4. Add to PATH:
   - Environment Variables → New → MAVEN_HOME = `C:\Program Files\apache-maven-3.9.6`
   - Edit PATH → Add `%MAVEN_HOME%\bin`

**Verify installation:**
```powershell
mvn --version
```

### 3. Install MySQL 8.0+
**Required:** MySQL Community Server 8.0+

#### Option A: Official Installer
1. Visit: https://dev.mysql.com/downloads/mysql/
2. Download: MySQL Community Server (Windows)
3. Run installer → Select "Developer Default"
4. Configure MySQL Server:
   - Port: 3306 (default)
   - Root password: Set a secure password (remember it!)
5. Start MySQL Service

#### Option B: Quick Local Testing
If you just want to test locally, you can use:
- MySQL environment via Docker (if installed)
- XAMPP with MySQL included

**Verify installation:**
```powershell
mysql --version
```

## Quick Setup Steps

### After Installing All Tools:

1. **Open PowerShell or Command Prompt**
   ```powershell
   cd "d:\Ac service\ShreeAirCons"
   ```

2. **Create MySQL Database**
   ```powershell
   mysql -u root -p < src/main/resources/db-init.sql
   ```
   When prompted, enter your MySQL root password.

3. **Update application.properties** (if needed)
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.password=your_mysql_password
   ```

4. **Build the Project**
   ```powershell
   mvn clean install
   ```

5. **Run the Application**
   ```powershell
   mvn spring-boot:run
   ```

6. **Access the Application**
   - Open browser and go to: http://localhost:8080
   - Homepage should load

## Troubleshooting

**Problem:** "mvn is not recognized"
- Solution: Maven not in PATH, reinstall with PATH setup

**Problem:** "java version not supported"
- Solution: Verify Java 17: `java -version` should show 17.x

**Problem:** "Connection refused" to MySQL
- Solution: 
  - Check MySQL service is running
  - Verify password in application.properties
  - Try: `mysql -u root -p` to test connection

**Problem:** "Database does not exist"
- Solution: Run the db-init.sql script:
  ```powershell
  mysql -u root -p shreeaircons < src/main/resources/db-init.sql
  ```

## Need Help?

Once all prerequisites are installed, run the following steps in order:
1. Copy all commands from "Quick Setup Steps" above
2. Execute step by step
3. Report any error messages

---

**Current Workspace:** `d:\Ac service\ShreeAirCons`
**Application Port:** http://localhost:8080
**Admin Dashboard:** http://localhost:8080/admin/dashboard
