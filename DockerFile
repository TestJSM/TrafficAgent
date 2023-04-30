FROM adoptopenjdk/openjdk17:alpine-jre # Copia el archivo JAR de la aplicación en el contenedor
COPY build/libs/TrafficAgent-1.jar /app.jar # Exponer el puerto 8888
EXPOSE 8888 # Ejecuta el comando para iniciar la aplicación Spring Boot
ENTRYPOINT ["java","-jar","/app.jar"]