FROM adoptopenjdk/openjdk17:alpine-jre
COPY build/libs/TrafficAgent-1.jar /app.jar
EXPOSE 8888 
ENTRYPOINT ["java","-jar","/app.jar"]