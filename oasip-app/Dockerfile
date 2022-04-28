FROM maven:3.8.5-jdk-11
WORKDIR /oasip-app
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
CMD mvn spring-boot:run
