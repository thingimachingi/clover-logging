FROM openjdk:8-jdk-alpine
COPY target/clover-logging-0.0.1-SNAPSHOT.jar clover-logging.jar
ENTRYPOINT ["java","-jar","/clover-logging.jar"]