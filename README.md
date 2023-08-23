# Bookstore Inventory System
## Brief Description
This System is built as an API using Spring Boot and connecting to Postgres Database as its persistent storage. It is implemented using Singleton design pattern by having a single instance of repository connected to the Database. 
## Setup the Database Connection
Go to Java/bookStore/src/main/resources/application.properties and change the value to your own url, username and password.
   
   ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/book
    spring.datasource.username=postgres
    spring.datasource.password=XXXXX 
   ```
## Database Schema 
It is defined in the Java/bookStore/src/main/java/com/bookStore/bookStore/model/Book.java. It will create the Database table when the code is first built and run.
## Future Improvement
1. Store the database password as a secret and retrieve it as an environment variable.
2. Implement more error handling and logging
