#FROM gradle:7.2.0-jdk17 AS build
#COPY . .
#RUN gradle build -x test
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/ticketracker-0.0.1-SNAPSHOT.jar ticketracker.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","ticketracker.jar"]
FROM openjdk:11-jre-slim
WORKDIR /app
COPY ticketracker-0.0.1-SNAPSHOT.jar ./ticketracker.jar
EXPOSE 8080
CMD ["java", "-jar", "your-application-name.jar"]
