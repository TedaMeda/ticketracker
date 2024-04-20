#FROM gradle:7.2.0-jdk17 AS build
#COPY . .
#RUN gradle build -x test
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/ticketracker-0.0.1-SNAPSHOT.jar ticketracker.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","ticketracker.jar"]
# Use a Gradle image as the build environment
FROM gradle:7.2.0-jdk17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the entire project directory into the container
COPY . .

# Build the application, excluding tests
RUN gradle build -x test

# Use a slim OpenJDK image for the runtime environment
FROM openjdk:17.0.1-jdk-slim AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage into the current stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
