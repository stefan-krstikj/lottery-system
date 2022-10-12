# Lottery System
A web API built in Spring Boot for a lottery system.

There is a lottery that runs every day. At exactly 00:00:01, a winner is drawn from the ongoing lottery 
and a new lottery is started. Any person can submit ballots for the lottery, as long as they are registered 
and authenticated in the system.

Any registered participant can:
- Create a ballot for the current ongoing lottery
- Get a list of all of their current active ballots
- Check if any ballot is a winning ballot
- Get information for the current and for any historic lotteries

## How to build/run your application
**Requirements**
- Java 11
- [PostgreSQL](https://www.postgresql.org/download/)

### Set up the database

1. Create a new database and user in postgres. Start the postgreSQL CLI by typing `psql` in your terminal
and enter the following, replacing '**lotterysystem**' with your own values
```
create database lotterysystem;
create user lotterysystem with encrypted password 'lotterysystem';
grant all privileges on database lotterysystem to lotterysystem;

```
2. _(Recommended)_ 

Open `application-dev.yml` and edit the following, replacing '**lotterysystem**' with your own values
```
url: jdbc:postgresql://localhost:5432/lotterysystem
username: lotterysystem
password: lotterysystem
```

### Start up the project
Start the application in your favorite IDE by running `LotterySystemApplication.class`
or by running `./gradlew bootRun` in the terminal

## API Documentation
The Swagger API Documentation is available at `localhost:8085/swagger-ui.html` once the application has been started

## UML Diagram
![diagram](img/diagram.png)

## Libraries and tools used
- **Spring Boot** - Helps us quickly get started with building applications on the Spring framework. 
- **Spring Data JPA** - Used for implementing JPA based repositories and accessing the data layer.
- **Spring Security** - An authentication and access-control framework
- **PostgreSQL** - Our database of choice
- **Gradle** - Build automation tool
- **Lombok** - Java library that helps us write cleaner and quicker code through smart annotations
- **Flyway Database Migration** - Used for automatic managing of our data 
- **dbdiagram** - For the UML diagram above 🙂

## What I'm particularly proud of


