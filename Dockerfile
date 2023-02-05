FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
EXPOSE 8081
COPY target/Intuit-App-0.0.1.jar intuit-app-0.0.1.jar
ENTRYPOINT ["java","-jar","/intuit-app-0.0.1.jar"]

#Maven Build
FROM maven:3.8.3-openjdk-11-slim AS builder
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests
