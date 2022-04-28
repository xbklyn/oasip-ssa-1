### STAGE 1: Build ###
FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY ./oasip/pom.xml /workspace
COPY ./oasip/src /workspace/src
RUN mvn -f pom.xml clean package

### STAGE 2: Run ###
FROM openjdk:8-alpine
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
