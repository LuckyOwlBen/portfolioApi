FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/portfolio-api.jar /app/portfolio-api.jar
COPY src/main/resources/application-dev.properties /app/application-dev.properties
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/app/portfolio-api.jar", "--spring.profiles.active=dev", "--spring.config.location=/app/application-dev.properties"]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar portfolioapi.jar
