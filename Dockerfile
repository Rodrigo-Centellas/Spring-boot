FROM maven:maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:21-jdk-slim
COPY  --FROM=build /target/demo-0.0.1-SNAPSHOT demo.jar

EXPOSE 8080
ENTRYPOINT ["java","-java","demo.jar"]
