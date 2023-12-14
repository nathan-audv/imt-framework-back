FROM eclipse-temurin:17
LABEL author="Brice REDON"

ADD target/imt-framework-back-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]