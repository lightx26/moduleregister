FROM alpine:3.19

# Install Java Runtime Environment
RUN apk add openjdk21-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY build/libs/ModuleRegister-0.0.1-SNAPSHOT.jar ./

# Expose port 8080 for the application
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "ModuleRegister-0.0.1-SNAPSHOT.jar"]