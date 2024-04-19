FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM gradle:7.2.0-jdk17 AS build
#COPY . .
#RUN gradle build -x test
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/ticketracker-0.0.1-SNAPSHOT.jar ticketracker.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","ticketracker.jar"]