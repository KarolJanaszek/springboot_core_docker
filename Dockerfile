FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /api/src
COPY pom.xml /api
RUN mvn -f /api/pom.xml clean package -DskipTests

FROM openjdk:17
MAINTAINER Top-Team TT Karol Janaszek
WORKDIR /opt/topteam/ttus
RUN mkdir images
COPY target/ttus-0.0.1-SNAPSHOT.jar ttus-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ttus-0.0.1-SNAPSHOT.jar"]
