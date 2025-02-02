# Event Registration System with Visitor Badge Generation

## Overview
This is a Django-based **Event Registration System** that allows users to register for an event, validates their input, stores the data in a PostgreSQL database, and generates a **visitor badge** containing the user’s photo, name, and the label "Visitor." The system also provides an **admin panel** for managing registrations and supports **email notifications with badge attachments via SendGrid**.

---

## Features
-  **User Registration Form** (Full Name, Email, Phone, Photo Upload)  
-  **Form Validation** (Client & Server-side)  
-  **Database Storage (PostgreSQL)**  
-  **Visitor Badge Generation (PNG + PDF Download)**  
-  **Email Notification with Badge Attachment (SendGrid)**  
-  **Admin Panel for Managing Registrations**  
-  **Mobile-Responsive UI (Bootstrap)**  
-  **Secure Configuration Storage (`.env` file)**  

---

## Technologies Used
- **Backend:** Django (Python)
- **Database:** PostgreSQL
- **Frontend:** HTML, CSS, Bootstrap
- **Email Integration:** SendGrid API
- **Badge Generation:** Pillow (PIL), WeasyPrint
- **Environment Variables:** `python-dotenv`

---

## Installation Guide
### ** Clone the Repository**
```bash
git clone https://github.com/yourusername/event-registration.git
cd event-registration
```

### ** Set Up a Virtual Environment**
```bash
python3 -m venv venv
source venv/bin/activate  # macOS/Linux
venv\Scripts\activate    # Windows
```

### ** Install Dependencies**
```bash
pip install -r requirements.txt
```

### ** Set Up `.env` File**
Create a `.env` file in the project root and add the following configurations:
```ini
# Database Config
DATABASE_NAME=event_registration
DATABASE_USER=event_admin
DATABASE_PASSWORD=yourpassword
DATABASE_HOST=localhost
DATABASE_PORT=5432

# Email (SendGrid)
SENDGRID_API_KEY=your-sendgrid-api-key
SENDGRID_SENDER_EMAIL=your-verified-email@example.com
```

### ** Set Up the Database**
```bash
python manage.py makemigrations
python manage.py migrate
```

### ** Create a Superuser for Admin Panel**
```bash
python manage.py createsuperuser
```
Follow the prompts to set a **username, email, and password**.

### ** Run the Server**
```bash
python manage.py runserver
```



## Usage
### **Register a Visitor**
1. Open **`http://127.0.0.1:8000/`** in your browser.
2. Fill out the registration form.
3. Submit the form to generate a **visitor badge**.
4. The badge can be **viewed and downloaded as a PDF**.
5. The visitor will receive an **email with their badge attached**.

### **Access the Admin Panel**
1. Go to **`http://127.0.0.1:8000/admin/`**.
2. Log in using your superuser credentials.
3. Manage visitor registrations.





## Author
Developed by **Pravin Timalsina**



## Contributions
Pull requests are welcome! If you'd like to contribute, please submit an issue or fork the repository.


