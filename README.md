# People Manager!

Server running on port 8080 by default. 

## Run the service with below commands
```
$ mvn clean install
$ mvn spring-boot:run 

```
## DB setup with mysql

Need to install mysql db, current db properties are as follows in application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/people
spring.datasource.username=root
spring.datasource.password=
```
If your endpoint or user/pass for mysql db is different, please update accordingly.

Table people will be recreated automatically every time the application is deployed