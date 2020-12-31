# The Docker file for jar
FROM openjdk:8-jdk-alpine
MAINTAINER SUMIT CHOUKSEY "sumitchouksey2315@gmail.com"
ADD /target/adrixus-api.jar adrixus-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/adrixus-api.jar"]
