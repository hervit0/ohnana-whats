FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ADD target/ohnana-0.1.0.jar /app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar","-Dspring.profiles.active=docker","/app.jar"]
