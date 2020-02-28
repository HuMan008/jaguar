FROM registry.petroun.com/base/java8:latest

LABEL maintainer="master@pgt"

RUN mkdir /application
RUN mkdir /application/config
RUN mkdir /application/logs

COPY ./build/libs/jaguar-0.0.1-SNAPSHOT.jar /application/jaguar.jar

COPY ./apns /application/apns


WORKDIR /application


CMD ["java", "-Dfile.encoding=UTF-8","-jar", "jaguar.jar"]

#CMD ["tail", "-f", "/dev/null"]