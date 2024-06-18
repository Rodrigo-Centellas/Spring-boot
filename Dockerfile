# Etapa 1: Construcción
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY . .
ENV LANG C.UTF-8
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
