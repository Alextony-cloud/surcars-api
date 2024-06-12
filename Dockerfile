FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar ./surcars-0.0.1-SNAPSHOT.jar
ENTRYPOINT java -jar surcars-0.0.1-SNAPSHOT.jar.jar
