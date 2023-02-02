FROM openjdk:8-jdk-alpine
RUN mkdir app
MAINTAINER baeldung.com
COPY target/Intuit-App-0.0.1.jar intuit-app-0.0.1.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar"," /Intuit-App-0.0.1.jar"]

