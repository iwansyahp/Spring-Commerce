FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]