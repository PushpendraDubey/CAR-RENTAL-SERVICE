# Car Rental Service Backend

Spring Boot REST API for managing car rentals.

## Tech Stack
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Gradle 8.5

## Running Locally

### With Gradle
```powershell
./gradlew bootRun
```

### With Docker
```powershell
docker build -t car-rental-backend .
docker run -p 8080:8080 car-rental-backend
```

## API Documentation

### Base URL
`http://localhost:8080/api`

### Endpoints
See main README.md for full API documentation.

## Database
H2 in-memory database (auto-populated with sample data)
- Console: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:carrentaldb`
- Username: `sa`
- Password: (empty)


## Kubernetes
Next Part will be deploying the code to the kubernetes cluster
