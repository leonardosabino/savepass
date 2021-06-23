# Introduction 
 This project was developed with the aim of managing the different passwords we use on a daily basis

### Requirements

- [Java 11](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) ou superior
- [Gradle](https://gradle.org/)
- [Docker](https://docs.docker.com/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)

###	Getting Started
  1) Clone or download the project
  2) Use the follow command to build the project
  ```
  .\gradlew build
  ```
  3) Start the mongodb database
  ```
  docker-compose up
  ```
  4) The last step is execute the spring application
  ```
  .\gradlew bootRun
  ```

###	API references
  You can find the API documentation after starter the project
  ```
  localhost:8081/swagger-ui/
  ```

