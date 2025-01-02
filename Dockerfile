# Use a slim JDK image for the build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only the files needed for dependency resolution
COPY pom.xml .
COPY src ./src

# Resolve dependencies and build the application in one step
RUN mvn clean package -DskipTests

# Use a minimal runtime image for the final stage
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy only the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]