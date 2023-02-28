# Spring Boot To-Do List Application
Focuses on back-end web application that provides the REST API for the purpose of managing your tasks.
</br>Created to improve important programming skills used in daily Java and Spring Boot development.


## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Setup](#setup)
* [Documentation](#documentation)
* [Contact](#contact)


## General Information
- The application was built in Java using the Spring Boot framework.
- It provides the REST API endpoint for the GET, PUT, POST and DELETE methods.
- The application uses MySQL for data storage and H2 as an in-memory database for integration testing.
- Application flow is handled with optionals and custom exceptions.
- Unit and integration tests cover 100% of the code, and everything has been tested for the second time using Postman.

![Test Coverage](https://github.com/kacperkadziolka/kacperkadziolka/blob/main/todo-app/tests.PNG)

## Technologies Used
- Java 17
- Spring Boot 3
- MySQL 
- Docker


## Setup
To run the application, first set up the MySQL server with Docker or locally on the computer.

```
docker run --name mysql-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:8

docker start -a mysql-db
```


## Documentation
The documentation was generated using the open-source [springdoc-openapi](https://github.com/springdoc/springdoc-openapi) tool and is compatible with Spring Boot 3.
</br>The swagger documentation is available at:
```
http://localhost:8080/swagger-ui/index.html
```
![Swagger Documentation](https://github.com/kacperkadziolka/kacperkadziolka/blob/main/todo-app/documentation.PNG)



## Contact
Created by [Kacper Kadziolka](https://github.com/kacperkadziolka) - feel free to contact me!