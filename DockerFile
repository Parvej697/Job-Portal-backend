 FROM maven:3.8.5-openjdk-21 AS build
 COPY . .
 RUN mvn clean package -DskipTests

 FROM openjdk:21.0.8-jdk-slim
 COPY --from=build /target/Job-Portal-0.0.1-SNAPSHOT.jar Job-Portal.jar
 EXPOSE 8080
 ENTRYPOINT ["java", "-jar", "Job-Portal.jar"]