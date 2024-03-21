# Challenge
Creating a Java web application where:
As a user of the application I can:
- Register the clients of my company
- Register the services my company offers
- Record that my client has hired a service from my company where I can define the start and end date of the service
- View which services my client has hired and how many days are left until the end date of the service.
I would also like to view the value of the services contracted by a client, where:
- Gold clients have a 10% discount
- Silver clients have a 5% discount
- If payment is made up to 10 days before the end date, give an additional 5% discount in the value calculation (considering the current date)
Additionally, I would like to provide a REST service that returns a JSON containing the data of all services offered by my company. This service can be consumed by another application.
Technical requirements:
- Backend in Java
- Should use an ORM framework for data persistence
- The database can be MySQL
- Front-end can be done in any way you want

Create the application database `create database servicos;`

The `application.properties` file located in the `servicos\src\main\resources` folder contains the username and password of the MySQL Server for application access, as well as the port that will be used by Tomcat.

For access to **services** in JSON format, visit `localhost:port/rest/services/`

For more details on what was used in the project, consult the `pom.xml` file.
