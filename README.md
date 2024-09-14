# Backend - Movies Reservation System

## Overview

This is the backend service for a movies reservation system, built using Spring Boot. It provides RESTful APIs for managing reservations, movies, showtimes, theaters, and customer accounts.

## Features

- **User Management**: Register and authenticate users.
- **Reservation Management**: Create, view, and cancel reservations for users and admins.
- **Movie Management**: Add, delete, and view movies.
- **Theater Management**: Manage theaters.
- **Showtime Management**: Schedule and manage movie showtimes.

## Technologies

- **Spring Boot**: Framework for building the RESTful APIs.
- **Spring Security**: Authentication and authorization.
- **Spring Data JPA**: Database interactions.
- **MySQL**: Database.
- **Hibernate**: ORM for database operations.

## Getting Started

### Prerequisites

- Java 17 or higher
- MySQL database
- Maven (for building the project)

### Configuration

1. **Clone the Repository**

   ```bash
   git clone https://github.com/Electromayonaise/MoviesBackend.git
   cd MoviesBackend
   ```
2. **Database Setup**

   Ensure you have MySQL running and create a database named moviesdb. Update the application.properties file with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/moviesdb
   spring.datasource.username=root
   spring.datasource.password=
   ```
3. **Build and Run**

Use the vscode `run` button on `Application.java` or run 
```bash
mvn clean install
mvn spring-boot:run
```

4. **Access the Application**

   The backend service will be running on `http://localhost:8080`.

## Endpoints

- **User Endpoints**
  - POST `/users/register` - Register a new user.
  - POST `/login` - Authenticate a user.

- **Reservation Endpoints**
  - POST `/reservations/user` - Create a reservation for the logged-in user.
  - DELETE `/reservations/user/{id}` - Cancel a reservation for the logged-in user.
  - POST `/reservations/admin` - Create a reservation for any user (admin only).
  - DELETE `/reservations/admin/{id}` - Cancel any reservation (admin only).
  - GET `/reservations/user` - Get reservations for the logged-in user.
  - GET `/reservations/admin` - Get all reservations (admin only).

- **Movie Endpoints**
  - POST `/movies` - Add a new movie (admin only).
  - DELETE `/movies/{id}` - Delete a movie (admin only).
  - GET `/movies` - Get all movies.

- **Theater Endpoints**
  - POST `/theaters` - Add a new theater (admin only).
  - DELETE `/theaters/{id}` - Delete a theater (admin only).
  - GET `/theaters` - Get all theaters.

- **Showtime Endpoints**
  - POST `/showtimes` - Add a new showtime (admin only).
  - DELETE `/showtimes/{id}` - Delete a showtime (admin only).
  - GET `/showtimes` - Get all showtimes.
