# Projet framework - Back
RESTful API create in Spring with H2 to deliver CROUS directly to students house.

## Technical Stack
### Built with
- Java
- Spring boot
- Lombok (avoid generating getters, setters, ...)
- H2
- Swagger UI (documentation)
### Prerequisites
- Install JDK 17+
- IntelliJ Ultimate Edition or Community Edition
### Configuration
- You can change database location on `application.yml` file into `src/resources` folder
#### With local
- Database will be created automatically
## Database
Access console on http://localhost:8080/h2
- username : oopframework
- no password

A database with default values is available at : 
It must be at the root of your user.
- Unix : ~/
- Windows : C:/Users/<User>/
## Documentation
Access documentation on http://localhost:8080/swagger-ui/index.html

! Most of the calls are not usable because of authorisation. Please use Postman or something.