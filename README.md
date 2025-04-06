# School Management System

A comprehensive system for managing school operations, including user management, student records, staff management, academics, attendance, examinations, and fee tracking.

## Table of Contents
1. [Requirements](#requirements)
2. [User Management](#user-management)
3. [Student Management](#student-management)
4. [Staff Management](#staff-management)
5. [Academic Structure](#academic-structure)
6. [Department Management](#department-management)
7. [Attendance Tracking](#attendance-tracking)
8. [Examination & Grading](#examination--grading)
9. [Fee Management](#fee-management)
10. [Scholarship Management](#scholarship-management)
11. [System Features](#system-features)
12. [Reporting & Analytics](#reporting--analytics)
13. [Notifications & Alerts](#notifications--alerts)
14. [Features List](#features-list)
15. [ERD](#erd)

## Requirements

### User Management
#### 1.1 Authentication & Authorization
- Users can log in with username/email and password
- Passwords are securely hashed
- Role-based access control (admin, principal, teacher, student, staff, guardian)
- Admins can create, update, and deactivate user accounts

#### 1.2 Profile Management
- Users can view and update personal details (name, email, phone, address)
- Students and staff can upload profile photos
- All users have active/inactive status
- Comprehensive audit trails (creation, update timestamps)

### Student Management
#### 2.1 Student Registration & Enrollment
- Admins can register new students with personal details
- Students assigned to classes with unique roll numbers
- Enrollment date and academic year recorded
- Student photos can be stored

#### 2.2 Student Records & Updates
- Admin can update student details (address, contact info, DOB)
- Students can be promoted to next grade level
- Leaving date tracking for transferred/graduated students
- Search/filter students by name, class, or academic year

#### 2.3 Guardian/Parent Management
- Guardians assigned to students with relationship type
- Multiple guardians per student with primary designation
- Guardians can view linked student's attendance, grades, and fees
- Guardian contact information and user accounts

### Staff Management
#### 3.1 Staff Registration & Roles
- Admins can add staff members with positions
- Staff details (hire date, contact info) can be updated
- Leaving date tracking for former staff
- Staff photos can be stored

#### 3.2 Teacher-Subject Assignment
- Teachers assigned to one or more subjects
- Teachers can view assigned classes and subjects
- Class teacher designation for each class

### Academic Structure
#### 4.1 Class & Subject Management
- Admins can create classes (grade level + section)
- Classes linked to specific academic years
- Subjects organized by departments
- Subjects can be added with unique codes

#### 4.2 Timetable Scheduling
- Weekly timetable creation for each class
- Entries include subject, teacher, day, and time slot
- No overlapping classes for same teacher/class
- Academic year context for schedules

### Department Management
#### 5.1 Department Organization
- Subjects grouped by departments
- Unique department codes
- Active/inactive status tracking
- Department descriptions

### Attendance Tracking
#### 6.1 Daily Attendance
- Teachers can mark attendance (Present/Absent/Late)
- Records stored by date, class, and academic year
- Students/guardians can view attendance history

#### 6.2 Attendance Reports
- Generate reports by student, class, or date range
- Identify trends (frequent absences, late arrivals)
- Academic year filtering

### Examination & Grading
#### 7.1 Exam Scheduling
- Admins can create exams with start/end dates
- Exams linked to academic year
- Active/inactive exam status

#### 7.2 Marks Entry & Grading
- Teachers can enter marks (0-100) per subject
- Class context included in grades
- Marks can be updated before finalizing
- Students/guardians can view grades

#### 7.3 Grade Reports
- Generate report cards
- Calculate overall performance (pass/fail, averages)
- Academic year performance tracking

### Fee Management
#### 8.1 Fee Assignment & Tracking
- Admins can assign fees with due dates
- Base amount and final amount after discounts
- Payment status tracking (Paid/Unpaid/Overdue)
- Payment date recording

#### 8.2 Fee Reports & Notifications
- Generate payment reports by student/status
- Scholarship discount calculations
- Send reminders for overdue fees

### Scholarship Management
#### 9.1 Scholarship Schemes
- Percentage or fixed amount discounts
- Date-bound active periods
- Scheme descriptions
- Assign to individual students

#### 9.2 Scholarship Tracking
- View active scholarships
- Fee calculations with discounts
- Historical scholarship records

### System Features
- Soft delete capability for all records
- Active/inactive status tracking
- Comprehensive audit timestamps (created_at, updated_at)
- Data recovery options via deleted_at
- Student and staff photo storage
- Academic year context throughout system

### Reporting & Analytics
#### 11.1 Student Performance Reports
- View grades, attendance trends
- Compare performance across exams
- Academic year comparisons

#### 11.2 Staff & Class Reports
- List of teachers and assigned subjects
- Class-wise student lists with attendance
- Department-wise subject analysis

#### 11.3 Financial Reports
- Total fees collected vs pending
- Scholarship discount summaries
- Overdue fee summaries by class

### Notifications & Alerts
- Guardians receive absence alerts
- Teachers notified of timetable changes
- Fee payment reminders sent
- Scholarship application deadlines
- Academic year transition alerts

## Features List

### Core Features
- Role-based access control (6 user roles)
- Comprehensive audit trails
- Soft delete functionality
- Academic year organization

### Student Management
- Complete student profiles with photos
- Guardian/parent associations
- Class enrollment with roll numbers
- Academic progression tracking

### Staff Management
- Detailed staff records with photos
- Position and department tracking
- Teacher-subject-class assignments
- Employment period tracking

### Academic Organization
- Grade levels 1-12 with sections
- Subject department categorization
- Class teacher designation
- Academic year context

### Operational Features
- Daily attendance with status tracking
- Exam scheduling and grade recording
- Timetable management
- Fee calculation with scholarships

### Financial Features
- Flexible fee structures
- Scholarship discount application
- Payment status tracking
- Financial reporting

## ERD

