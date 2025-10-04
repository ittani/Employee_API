# Stage 1: Build the application
# Use a Maven container with JDK 17 to build the application
FROM eclipse-temurin:17-jdk-jammy as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper files first to leverage Docker layer caching
COPY .mvn/ .mvn
COPY mvnw .
COPY mvnw.cmd .

# Copy the Maven project file
COPY pom.xml .

# Copy the rest of the application source code
COPY src ./src

# Make the mvnw script executable
RUN chmod +x mvnw

# Package the application, skipping the tests for a faster build
RUN ./mvnw package -DskipTests


# Stage 2: Create the final, smaller runtime image
# Use a JRE image which is smaller than a full JDK
FROM eclipse-temurin:17-jre-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR from the builder stage
# The artifactId and version are taken from your pom.xml
COPY --from=builder /app/target/employee-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

