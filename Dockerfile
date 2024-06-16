FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw ./
COPY mvnw.cmd ./
COPY pom.xml ./
RUN ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw package -DskipTests
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

FROM openjdk:17-ea-28-jdk-slim-buster
COPY --from=build /target/todoList-0.0.1-SNAPSHOT.jar todoList.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "todoList.jar"]