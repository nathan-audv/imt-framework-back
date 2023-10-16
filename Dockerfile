FROM ubuntu:22.04

WORKDIR /usr/src/app

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

COPY pom.xml ./
COPY src ./src

RUN mvn clean install

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/imt-framework-back-0.0.1-SNAPSHOT.jar"]