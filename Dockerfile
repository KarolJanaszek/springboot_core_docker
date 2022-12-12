FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /api/src
COPY pom.xml /api
RUN mvn -f /api/pom.xml clean package -DskipTests

FROM openjdk:17
MAINTAINER Karol Janaszek
WORKDIR /opt/jonasz/snadboxone
RUN mkdir images
COPY target/springboot_core_docker-0.0.1-SNAPSHOT.jar springboot_core_docker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","springboot_core_docker-0.0.1-SNAPSHOT.jar"]
