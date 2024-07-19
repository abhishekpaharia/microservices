# Microservices

Microservices repo contains concepts and various aspects of creation of microserivces and their code implementation. Each section target a specific acpects that one need to learn
while making microservices.

- Section 2: creation of dummyBank features which are 3 microservices - accounts, loans, cards.
- section 4: Its about conterisation of miroservices using docker.
  - creation of docker images by 3 methods - docker file, buildpacks, google jib
  - port Forwarding, running docker containers, docker compose.
- section 6: It about management of configuration in micoservices
  - 3 ways of accesing properties from properties files
  - spring profiles for dev, QA, and prod environment.
  - Externalizing configurations using command-line, JVM & environment options
  - Building Config Server using Spring Cloud Config
  - Reading configurations by 3 ways in Config Serv - class path of config server, file system of server, github repo for config data.
  - Encryption & Decryption of properties inside Config server
  - Refresh configurations at runtime using refresh actuator path in a microservices without restarting it.
- section 6: It is about service registry, service discovery, and client side load balancing.
  - Setup Service registry using the Eureka server by using the Spring Cloud Netflix project
  - registering accounts, loans, and card microservices to the Eureka server or service registry by setting up as an Eureka discovery client. 
  - synchronous HTTP communication by calling REST API of backing microservice in a Microservice using FeignClient
  - running all microservices on docker
    -  write a docker-compose for setting configurations like linking accounts MS with eureka server url
    -  write docker-compose so that accounts MS starts after ConfigServer MS and EurekaServer MS is ready.
- section 7: It is about developing edge serve to create api gateway, implement cross cutting solution.
  - build a edge server using spring cloud gateway project.
  - customise the routing coming to the api gateway.
  - applied gateway filters to the routes
  - create custom global filters for tracing (a cross cutting feature).
