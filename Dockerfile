FROM openjdk:8-alpine
MAINTAINER Rafael Mantovani

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/star-wars-api.jar $PROJECT_HOME/star-wars-api.jar

WORKDIR $PROJECT_HOME
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=pro", "/opt/app/star-wars-api.jar"]
