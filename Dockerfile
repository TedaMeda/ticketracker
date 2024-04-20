#FROM gradle:7.2.0-jdk17 AS build
#COPY . .
#RUN gradle build -x test
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/ticketracker-0.0.1-SNAPSHOT.jar ticketracker.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","ticketracker.jar"]
# Use a Gradle image as the build environment
# Use a base image with OpenJDK 17 and Gradle
# Use a base image with OpenJDK 17 and Gradle
FROM gradle:7.2.0-jdk17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle Wrapper files into the container
COPY gradlew .
COPY gradle gradle

# Copy the project files needed for dependency resolution
COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .

# Copy the entire project directory into the container
COPY . .

# Ensure Gradle Wrapper and scripts have execution permissions
RUN chmod +x gradlew

# Build the application using the Gradle Wrapper
RUN ./gradlew build

# Use a slim OpenJDK image for the runtime environment
FROM adoptopenjdk/openjdk17:alpine-jre AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage into the current stage
COPY --from=builder /app/build/libs/*.jar ticketracker.jar

# Expose port 8080
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "ticketracker.jar"]
