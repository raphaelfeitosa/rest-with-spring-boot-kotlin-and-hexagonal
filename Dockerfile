## Builder Image
FROM maven:latest AS builder
MAINTAINER Raphael Feitosa
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

## Runner Image
FROM azul/zulu-openjdk:17
COPY --from=builder /usr/src/app/target/*.jar /usr/app/app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]