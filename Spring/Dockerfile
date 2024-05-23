# Fetch the Java
FROM alpine:3.19
# Install necessary packages
RUN apk update
RUN apk add --no-cache openjdk19-jre
# Expose port 8080
EXPOSE 8080
# set a docker volume if you want
VOLUME /assignment_volume
# Add the jar file
ADD /target/*.jar DOC1_ASSIGNMENT-0.0.1-SNAPSHOT.jar
# Start the application
ENTRYPOINT ["java", "-jar", "/DOC1_ASSIGNMENT-0.0.1-SNAPSHOT.jar"]
