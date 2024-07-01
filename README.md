# Medichronicle

This is a Java Spring Boot application.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Maven
- PostgreSQL, SQL Server, or MySQL

### Installing

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean install` to build the project
4. Run `mvn spring-boot:run` to start the application
## API Endpoints

The application provides the following RESTful endpoints:

- `POST /pharmacy/create`: Creates a new pharmacy.
- `GET /pharmacy/get-pharmacy-details-by-pharmacyAddress/{pharmacyAddress}`: Retrieves the details of a pharmacy by its address.
- `PUT /pharmacy/update-pharmacy-by-pharmacyAddress/{pharmacyAddress}`: Updates the details of a pharmacy by its address.
- `DELETE /pharmacy/delete-pharmacy-by-pharmacyAddress/{pharmacyAddress}`: Deletes a pharmacy by its address.
- `GET /pharmacy`: Retrieves all pharmacies.

## Configuring application.properties

The `application.properties` file is used to configure application-specific settings, such as database connection information. Here's an example of how you might set it up for a PostgreSQL database:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Running the tests

Run `mvn test` to execute the unit tests.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)

## Authors

- Kudzai Murira