# Overview
This repository contains source code for 3 microservices for an ecommerce applications.

# Microservices Compositions
The sytems are composed by several services, which are

1. Product Services
Service for managing the products registered in the application, the service are built with these tac


# Guide

1. Run compose docker before running microservices

Easier testing can be done via Postman!


# How to Run
Follow these steps to run the services in your local machine
1. Run docker compose: `docker compose up`, make sure that all infrastructure are running


# How to Build
When you want to build containerized service, run following

`mvn spring-boot:build-image` (add `-DskipTests` if needed). Once every image for services are built. The images will be displayed on Docker Desktop image lists.

# URLs

### Main Services
Product Service: http://localhost:8082
Order Service: http://localhost:8083

## Infra and Observability
Service Registration & Discovery: http://localhost:8761
Kafka UI: http://localhost:8090/
Zipkin: http://localhost:9411/