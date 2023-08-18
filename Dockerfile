# Use a base image with Java 20 JDK or whichever version you're using.
FROM openjdk:20-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Make port 8080 available outside of the container.
EXPOSE 8080

# Navigate to the app directory in the container.
WORKDIR /app

# Copy the Maven pom.xml and effectively cache dependencies.
COPY pom.xml ./

RUN mvn dependency:go-offline

# Copy the source code and build our application.
COPY src ./src

RUN mvn package

# Copy the built JAR into the image.
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Run the application.
ENTRYPOINT ["java", "-jar", "app.jar"]
