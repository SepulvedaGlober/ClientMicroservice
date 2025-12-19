FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp

COPY target/*.jar app.jar

# 4. Definimos el comando de inicio
ENTRYPOINT ["java","-jar","/app.jar"]