FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar report-service.jar
ENTRYPOINT ["java","-jar","/report-service.jar"]