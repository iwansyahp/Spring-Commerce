# Overview
This repository contains source code for 3 microservices for an ecommerce applications based on **Spring Boot 3** and utilizes **Spring Cloud Tools** for building scalable products. All services are registered to **Eureka**, a Service Registration and Discovery tool. **Zipkin** is used for observability and distributed tracing facility. Communication between servers are done via **Apache Kafka**, which provides asynchronous communication. **Dockerfile** for each service are available for builidng containerized applications.

# Microservices Compositions
The sytems are composed by several services, which are:

## 1. Product Services
Service for managing the products registered in the application, all products added will be replicated to DB or order service. This services uses **Postgres** via Spring Data for storing the products data, **Apache Kafka** for sending products data to order services.
## 2. Order Service
Service for recording ordering, purchasing of the products in the application. Main data that are managed by this service are **Orders** and **OrderedProducts** data that is stored in **Postgres**. All ordered products that are **checked out, paid, cancelled** will be sent to **Report Srevice** via **Apache Kafka**. Products data are stored in this service after replicated from Product Service. **Redis** is used in this application for caching mechanism that reduce communication to database.
****
## 3. Report Service
Every processed orders (checkout, payment, cancelation) are managed by this service and stored in a NoSQL database, **MongoDB**. These data are produced by activities in **Order Service**, which are received by this service via **Apache Kafka** event streaming platform.

## 4. Naming Service
Eureka based service that acts as Service Registration and Discovery

# Guide

1. Run `docker compose up` before running the services
Testing can be done by running the application and executing API definition in Postman Collection file.


## How to Build
Building containerized services and be done by running following command inside each service project:

`mvn spring-boot:build-image` (add `-DskipTests` if needed). Once every image for services are built. The images will be displayed on Docker Desktop image lists. After running this, you can build dockerized applications via `docker compose up`

## How to Run Locally

In development mode, you can run the Docker Compose with defined configuration in `docker-compose-only-infra-ops` that provide Database (Postgresql, Redis, MongoDB), Event Streaming Tools (Apache Kafka w/ extra UI tools), Inter services tracing tools (Zipkin).
Follow these steps to run the services in your local machine
1. Run docker compose: `docker compose up`, make sure that all infrastructure are running. This runs the services, suppporting infrastruture and operation tools defined in `docker-compose.yaml` file. Make sure you built the services before running the service via Docker Compose.

# URLs
## Main Services
These are URLs for each services and supporting tools:

- Product Service: http://localhost:8082
- Order Service: http://localhost:8083
- Report Service: http://localhost:8084

## Infra and Observability
- Service Registration & Discovery (Eureka): http://localhost:8761
- Kafka UI: http://localhost:8090/
- Zipkin: http://localhost:9411/