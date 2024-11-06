FROM openjdk:17-jdk-alpine
COPY target/portfolio-api.jar app/portfolio-api.jar
EXPOSE 5000
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar portfolio-api.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar portfolioapi.jar
