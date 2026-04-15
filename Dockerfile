FROM maven:3.8-openjdk-17 AS BUILD
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build app/target/*.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff-agendador.jar"]