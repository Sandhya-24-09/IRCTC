# Train Booking System
## Introduction
The Train Booking System is a Java-based console application developed to simulate the basic functionalities
of an online railway reservation system. The application enables users to register, log in securely, search
for trains between stations, reserve seats, view booked tickets, and cancel reservations. All data is stored
persistently using JSON files, making the application lightweight while demonstrating real-world concepts of
data management.
The project follows Object-Oriented Programming principles by separating the application into entities,
services, and utility classes. Jackson Databind is used for JSON serialization and deserialization, while
BCrypt is used to securely hash user passwords before storage.

## Objectives
The objectives of this project are:

- Develop a railway reservation system using Java.
- Implement secure user authentication.
- Demonstrate CRUD operations using JSON files.
- Simulate train seat reservation.
- Apply Object-Oriented Programming concepts.
- Learn file handling and data persistence.
- Understand service-based architecture.

## Features

- User Registration
- Secure User Login
- Password Hashing using BCrypt
- Search Trains by Source and Destination
- View Train Details
- Seat Availability Display
- Book Available Seats
- View Booking History
- Cancel Booked Tickets
- Automatic Seat Status Update
- JSON-based Data Storage

## Technologies Used
Java: Core Programming Language 
Gradle : Build Automation 
Jackson Databind :JSON Serialization & Deserialization 
BCrypt : Password Encryption
JSON : Data Storage 
Java Collections: Data Management 
OOP Concepts: Application Design 

## Project Structure
app
│
├── entities
│   ├── User.java
│   ├── Train.java
│   └── Ticket.java
│
├── services
│   ├── UserBookingService.java
│   └── TrainService.java
│
├── util
│   └── UserServiceUtil.java
│
├── localDb
│   ├── users.json
│   └── trains.json
│
└── App.java
```
## System Modules
### User Module

The User Module is responsible for handling user registration and authentication. During registration,
the user's password is encrypted using BCrypt before being stored in the JSON file. During login, the
entered password is verified against the stored hashed password.

Functions:
- User Registration
- User Login
- Password Encryption
- Booking Retrieval

### Train Module
The Train Module manages train information stored in the JSON database. It allows users to search trains
based on their source and destination while displaying train information and station timings.

Functions:
- Train Search
- Seat Availability
- Train Details
- Station Timings

### Booking Module
The Booking Module handles the reservation process. It checks seat availability before booking, updates
the seat status, creates a ticket, and stores it in the user's booking history.

Functions:
- Seat Reservation
- Ticket Generation
- Booking Storage
- Seat Status Update

### Cancellation Module
The Cancellation Module removes booked tickets from the user's account and updates the train seat status,
making the cancelled seat available again.

Functions:
- Ticket Cancellation
- Seat Release
- Booking Update

## Workflow

                 Start
                   │
                   ▼
          Display Main Menu
                   │
                   ▼
          ┌─────────────────┐
          │ Sign Up / Login │
          └─────────────────┘
                   │
         Login Successful?
            │             │
          No│             │Yes
            ▼             ▼
      Return to Menu   Search Train
                            │
                            ▼
                  Enter Source & Destination
                            │
                            ▼
                  Display Available Trains
                            │
                            ▼
                     Select a Train
                            │
                            ▼
                   Display Seat Layout
                            │
                            ▼
                     Select a Seat
                            │
                            ▼
                    Seat Available?
                     │            │
                  No │            │ Yes
                     ▼            ▼
              Display Error   Book Seat
                                  │
                                  ▼
                          Generate Ticket
                                  │
                                  ▼
                       Update JSON Database
                                  │
                                  ▼
                      View or Cancel Booking
                                  │
                                  ▼
                                 Exit

## Data Storage
he application stores data using JSON files instead of a relational database.
### users.json
Stores:
- User ID
- Username
- Password Hash
- Booking History

### trains.json
Stores:
- Train ID
- Train Number
- Station List
- Station Timings
- Seat Matrix

## Booking Process
1. User logs into the application.
2. User searches trains by entering source and destination.
3. Matching trains are displayed.
4. User selects a train.
5. Available seats are displayed.
6. User selects a seat.
7. The application checks seat availability.
8. A ticket is generated.
9. The booking is stored in the user's booking history.
10. The seat status is updated in the train database.

## Cancellation Process
1. User selects Cancel Booking.
2. Ticket ID is entered.
3. Ticket is searched.
4. Booking is removed from the user's account.
5. Seat status is updated from booked to available.
6. JSON database is updated.

## Object-Oriented Programming Concepts Used
### Encapsulation
All entity classes use private data members with public getter and setter methods.
### Abstraction
Business logic is separated into service classes such as `UserBookingService` and `TrainService`.
### Modularity
The project is divided into entities, services, utilities, and local database files to improve maintainability.
### Reusability
Common operations such as password hashing and authentication are implemented in utility classes and reused throughout the application.

## Advantages

- Easy to understand and maintain.
- Demonstrates real-world booking workflow.
- Secure password storage.
- Persistent data using JSON.
- Modular architecture.
- Beginner-friendly implementation.
- Easily extendable into a web application.

## Future Enhancements

- JavaFX graphical user interface.
- Spring Boot REST API.
- MySQL database integration.
- React-based frontend.
- Online payment gateway.
- QR code ticket generation.
- Email notifications.
- Admin dashboard.
- Live train tracking.
- Passenger profile management.

## Learning Outcomes

This project helped in understanding:

- Java programming fundamentals
- Object-Oriented Programming
- File handling
- JSON serialization and deserialization
- Password hashing using BCrypt
- Collection Framework
- Exception handling
- CRUD operations
- Service-based architecture
- Data persistence techniques







