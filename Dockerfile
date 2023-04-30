FROM ubuntu:latest as build
RUN apt-get update
RUN apt-get install open jdk-17-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8888
COPY --from-build/build/libs/TrafficAgent-1.jar app.jar