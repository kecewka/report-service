# Use a base image with Java 17 installed
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/report-service-0.0.1-SNAPSHOT.jar /app

# Expose the port your application runs on
EXPOSE 8888

# Define the command to run your application
CMD ["java", "-jar", "report-service-0.0.1-SNAPSHOT.jar"]
