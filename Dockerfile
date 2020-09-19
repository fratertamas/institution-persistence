FROM openjdk:8-jdk
COPY target/institution-persistence-*.jar usr/src/institution-persistence.jar
WORKDIR /usr/src/
ENTRYPOINT java -jar institution-persistence.jar