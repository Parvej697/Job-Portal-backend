# Build stage (Java 21 + Maven latest)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/Job-Portal-0.0.1-SNAPSHOT.jar Job-Portal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Job-Portal.jar"]
