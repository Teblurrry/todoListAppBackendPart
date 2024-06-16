FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/todoList-0.0.1-SNAPSHOT.jar todoList.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "todoList.jar"]