FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/todoList-0.0.1-SNAPSHOT.jar todoList.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "todoList.jar"]