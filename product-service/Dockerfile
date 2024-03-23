FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]