FROM openjdk:8-alpine
MAINTAINER Rafael Mantovani

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/star-wars-api.jar star-wars-api.jar

WORKDIR $PROJECT_HOME
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]