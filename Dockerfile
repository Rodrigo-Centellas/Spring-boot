# Etapa 1: Construcción
FROM openjdk:21-jdk-slim
RUN apt-get update && \
    apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:21-jdk-slim
COPY --from=0 /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]