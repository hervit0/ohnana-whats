FROM openjdk:8-jdk-alpine
MAINTAINER @herveah
EXPOSE 8080
VOLUME /tmp
ADD target/ohnana-0.1.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
