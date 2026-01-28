# Task Manager

---

## About
A task management system where teams can create, assign, and track tasks with categories and collaboration features.

### Prerequisites ###

- Java Version 25 `java --version`
- Docker `docker --version`

### Tech Stack ###
- Spring Boot 4 and Spring Framework 7

### Setup Instructions ###
 1. Clone Repo

 2. docker compose up -d
To verify if the container is up and running you can try running `docker exec -it taskmanager-db psql -U shoyu -d taskmanager_db` if there are no errors, your database is up and running. You can quit it by `\q`.

 3. ./gradlew bootRun - 
If you see (Tomcat started on port 8080 (http) with context path '/') in the termical it mean application startd sucessfully and connected to the database
"Note: Since no REST endpoints are implemented yet, visiting http://localhost:8080 will show a 404 error - this is expected."

## Features

### Current Status ###
 - [x] Spring Boot 4 SetUp
 - [x] PostgreSQL with Docker

### Roadmap ###
 - 🚧 User Authentication
 - 🚧 Role-based Authorization
 - 🚧 Task CRUD
 - 🚧 Categories