Bulletin
========

Bulletin is a `Spring Boot` application. It uses `Spring Data` and `JPA` to manage data in the database. It developed base on microservice architecture which contains two layers

1. Backend will be using Spring REST MVC for providing the REST endpoint
2. Frontend use `Angular.JS` + `Bootstrap` and consume the data from Backend via REST.


### 1. System Requirement
1. JDK 8
2. MySQL version 5.7

### 2. Setting up database

Bulletin use `MySQL` as a database. Please create a  database and grant user privilege with this information

```
database name : bulletin
database user name : bulletin
database user password : bulletin
```
or you can edit the database configuration with this file

```
config/application.properties
```

### 3. Installation

Bulletin uses `Maven`. We can install all of the dependencies that we need with this following command.

```
mvn clean package
```

### 4. Database Migration

Bulletin use `Flyway` for handling database migration. Every database schema will be on this path.

```
src/resources/db/migration
```

### 5. Start the application

After the application build successfully. It will produce `jar` file in `target` directory. We can use java command to start it.

```
java -jar target/bulletin.jar
```

In development, You can use `Spring Boot Dev plugin` to start the application

```
mvn spring-boot:run
```

both of these commands will do the database migration automatically.
