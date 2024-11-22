FROM eclipse-temurin:23-jdk

LABEL MAINTAINER="chloe"
LABEL description="SSF Day 15 Lecture example"
LABEL name="ssf_15l"

ARG APP_DIR=/app

WORKDIR ${APP_DIR}
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true

ENV SERVER_PORT=4000

EXPOSE ${SERVER_PORT}

ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/ssf_15l-0.0.1-SNAPSHOT.jar
# ENTRYPOINT ["java", "-jar", "target/ssf_15l-0.0.1-SNAPSHOT.jar"]