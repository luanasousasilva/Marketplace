FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY customer-service/pom.xml customer-service/pom.xml
COPY product-service/pom.xml product-service/pom.xml
COPY order-service/pom.xml order-service/pom.xml
COPY payment-service/pom.xml payment-service/pom.xml
COPY notification-service/pom.xml notification-service/pom.xml
ARG SERVICE
RUN mvn -q -pl ${SERVICE} -am dependency:go-offline
COPY . .
RUN mvn -q -pl ${SERVICE} -am package -DskipTests
FROM eclipse-temurin:21-jre
ARG SERVICE
COPY --from=build /workspace/${SERVICE}/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
