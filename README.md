# Microservice-With-Spring-Cloud

## Monolith Application

- A large application might only have one database
- Challenges
  - Deployment complexity: Minor updates need complete redeployment
  - Tightly coupled components: Changes in one part of application can affect others => longer release cycles
  - Scalability limitations: Scaling can be resource intensive, entire app to be scaled even if only one part of it needs more capacity
  - Technology lock-in: Entire app built on a single technology stack, adopting new technologies is challenging.

## Microservice

- Small autonomous services that work together - Sam Newman
- The microservice architectural style is an approach to developing a single application as a suite of small services, each running in
  its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business
  and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services,
  which may be written in different programming languages and use different data storage technologies.
- Three critical focus areas
  - REST: Built following REST API Standards and Best Practices
  - Small Well Chosen Deployable Units: independently deployable units of small services
  - Dynamic Scaling: Possible to scale up and down independent of each other
- Example: Movie Booking Application - key microservices
  - Movie Service: Central service managing moving details, show times, and availability
  - Booking Service: Handles ticket booking, seat selection, and booking management
  - Pricing Service: Manages ticket pricing, discounts, and special offers
  - Customer Service: manages customer profiles, authentication, and customer support
  - Review Service: Allows users to submit and view reviews, ratings, and comments.
- Three key advantages
  - New technology and process adoption: Teams can adopt new technologies and processes for individual services
    - Flexibility: Choose the best framework, and languages for each service
    - Innovation: Easier to experiment and use emerging technologies
  - Dynamic Scaling: Enable scaling of individual components based on demand
    - Efficiency: Scale only the service that need it, reducing costs
  - Faster release cycles: Smaller, independent services can be developed, tested, and deployed more quickly
    - Agility: Allows for more frequent updates and quicker response to market demands
- Key microservices solutions

  - Spring Boot: Enables rapid development of REST API
  - Spring Cloud: Umbrella project that provides essential microservice needs
    - Centralized Configuration: Manage configuration for multiple microservices in central GIT repository
    - Load Balancing: Distributes requests across active instances of microservices dynamically
    - Service Discovery: Enable automatic discovery of microservices
    - Distributed Tracing: Trace requests across microservices
    - Edge Server: Single Entry Point - Implement common features like authentication
    - Fault Tolerance: Ensure that failure in one microservice does not cascade and make other microservices to fail.
  - Docker: Consistent deployment approach for microservices, programming language and environment independent
  - Kubernetes: Orchestrate thousands of microservices with advanced features (Service Discovery, Load Balancing, Release Mgmt,...)

  ## Centralized Configuration

  - Need for Centralized Configuration
    - Lot of configuration:
      - External Service
      - Database
      - Queue
      - Typical Application Configuration
    - Configuration variations:
      - 1000s of Microservices
      - Multiple Environments
      - Multiple instances in each Environment
    - How to manage all this configuration (Spring Cloud Config Server)
      - Store configuration related multiple microservices, all the different environment in just one git repo.
      - Create a microservice limits-service - Config Client and import config server
        - spring.config.import=optional:configserver:http://localhost:8888
      - Create another microservice spring-cloud-config-server - Config Server
      - Initialize a git repo, and create a configuration file - limits-service.properties
      - Set the uri of config server in spring-cloud-config-server
        - spring.cloud.config.server.git.uri=file:///C:/Users/**\***/Desktop/GitFolder/microservice-with-spring-cloud
      - Enable config server - @EnableConfigServer
      - Ensure the microservice name should be the same as the name of properties files in git repo
        - spring.application.name=limits-service
      - Set configuration for different environments
        - spring.profiles.active=dev
        - spring.cloud.config.profile=dev
        - create corresponding file in git repo - limits-service-dev.properties
          ![centralized configuration](assets/centralized-configuration.png)
