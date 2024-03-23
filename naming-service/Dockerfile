FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar eureka-service.jar
ENTRYPOINT ["java","-jar","/eureka-service.jar"]