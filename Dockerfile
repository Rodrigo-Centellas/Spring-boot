# Etapa 1: Construir la imagen base con Maven y OpenJDK 21
FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y maven

# Etapa 2: Construcción
FROM openjdk:21-jdk-slim AS build
COPY --from=0 /usr/share/maven /usr/share/maven
ENV MAVEN_HOME /usr/share/maven
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 3: Imagen final
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]