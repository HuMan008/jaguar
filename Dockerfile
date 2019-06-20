FROM registry.pgt/base/java8:latest

LABEL maintainer="master@pgt"

RUN mkdir /application
RUN mkdir /application/config
RUN mkdir /application/logs


COPY ./build/libs/jaguar-0.0.1-SNAPSHOT.jar /application/jaguar.jar

ADD apns /appplication

WORKDIR /application


CMD ["java", "-Dfile.encoding=UTF-8","-jar", "/application/jaguar.jar"]