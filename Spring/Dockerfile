# First stage: Build the Java application
FROM maven:3.8.6-openjdk-19 as builder
# Set the working directory
WORKDIR /app
# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
# Build the project
RUN mvn clean package
# Second stage: Create the final runtime image
FROM alpine:3.19
# Install necessary packages
RUN apk update && apk add --no-cache openjdk19-jre
# Expose port 8080
EXPOSE 8080
# Set a docker volume if you want
VOLUME /assignment_volume
# Copy the built jar file from the builder stage
COPY --from=builder /app/target/DOC1_ASSIGNMENT-0.0.1-SNAPSHOT.jar DOC1_ASSIGNMENT-0.0.1-SNAPSHOT.jar
# Start the application
ENTRYPOINT ["java", "-jar", "/DOC1_ASSIGNMENT-0.0.1-SNAPSHOT.jar"]
