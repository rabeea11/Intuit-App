FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/Intuit-App-0.0.1.jar intuit-app-0.0.1.jar
ENTRYPOINT ["java","-jar","/intuit-app-0.0.1.jar"]
RUN mvn clean install
CMD mvn spring-boot:run
