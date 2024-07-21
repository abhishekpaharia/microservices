# Microservices

Microservices repo contains concepts and various aspects of the creation of microservices and their code implementation. Each section targets specific aspects that one needs to learn
while making microservices.

- Section 2: the creation of dummyBank features which are 3 microservices - accounts, loans, cards.
- section 4: It is about containerization of microservices using docker.
  - creation of docker images by 3 methods - docker file, buildpacks, google jib
  - port Forwarding, running docker containers, docker compose.
- section 6: It is about the management of configuration in microservices
  - 3 ways of accessing properties from properties files
  - spring profiles for dev, QA, and prod environment.
  - Externalizing configurations using command-line, JVM & environment options
  - Building Config Server using Spring Cloud Config
  - Reading configurations in 3 ways in Config Serv - the classpath of the config server, the file system of the server, GitHub repo for config data.
  - Encryption & Decryption of properties inside the Config server
  - Refresh configurations at runtime using the refresh actuator path in microservices without restarting it.
- section 8: It is about service registry, service discovery, and client-side load balancing.
  - Setup Service registry using the Eureka server by using the Spring Cloud Netflix project
  - registering accounts, loans, and card microservices to the Eureka server or service registry by setting up as an Eureka discovery client. 
  - synchronous HTTP communication by calling REST API of backing microservice in a Microservice using FeignClient
  - running all microservices on docker
    -  write a docker-compose for setting configurations like linking accounts MS with Eureka server URL.
    -  write docker-compose so that accounts MS starts after ConfigServer MS and EurekaServer MS is ready.
- section 9: It is about developing an edge server to create an API gateway, and implement the cross-cutting solution.
  - build an edge server using the Spring Cloud gateway project.
  - customize the routing coming to the API gateway.
  - applied gateway filters to the routes
  - create custom global filters for tracing (a cross-cutting feature).
- section 10: it is about making microservices resilient using resilience4j.
  - created circuit breaker pattern in edge server using circuit breaker GatewayFiliter factory.
  - created a circuit pattern in account service (calling Microservice) with FeignClient. Also created a Fallback class for the FignClient.
  - set timeout for routes in the edge server
  - created a retry pattern in the edge server using the Retry GatewayFiliter factory.
  - created a retry pattern in account service (receiving Microservice) and a fallback method if all retries fail.
  - created a rate limiter in the edge server using RequestRateLimiter gatewayFilter Factory, particularly the Redis rate limiter.
  - created a rate limiter in account service (receiving Microservice) and a fallback method if all requests rate exceeds.
