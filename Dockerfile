FROM ubuntu:latest as build
RUN apt-get update
RUN apt-get install open jdk-17-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM adoptopenjdk/openjdk17:alpine-jre
EXPOSE 8888
COPY build/libs/TrafficAgent-1.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]