FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar naming-service.jar
ENTRYPOINT ["java","-jar","/naming-service.jar"]