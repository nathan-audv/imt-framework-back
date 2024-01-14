# Projet framework - Back
RESTful API create in Spring with H2 to deliver CROUS directly to students house.
Now using PostgreSQL to deploy it.

## Technical Stack
### Built with
- Java
- Spring boot
- Lombok (avoid generating getters, setters, ...)
- PostgreSQL
- Swagger UI (documentation)
- Testcontainers (integration tests)
### Prerequisites
- Install JDK 17+
- IntelliJ Ultimate Edition or Community Edition
### Configuration
- You can change database location on `application.yml` file into `src/resources` folder
#### With local
- Change credential on `application.yml`
### Tests
- Run `mvn test` to run tests, database is created by Testcontainers
## Documentation
Access documentation on http://localhost:8080/swagger-ui/index.html

! Most of the calls are not usable because of authorisation. Please use Postman or something using Bearer token.
## Presentation support 
https://www.canva.com/design/DAFxVhRW8BA/HV6-3ofM27r6CeInWyM-Jw/edit?utm_content=DAFxVhRW8BA&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton*https://www.canva.com/design/DAFxVhRW8BA/HV6-3ofM27r6CeInWyM-Jw/edit?utm_content=DAFxVhRW8BA&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton*