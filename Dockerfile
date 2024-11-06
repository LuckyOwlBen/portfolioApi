FROM openjdk:17-jdk-alpine
COPY /portfolioApi/target/portfolio-api.jar app/portfolio-api.jar
EXPOSE 5000
ENTRYPOINT exec java -D java.security.egd=file:/dev/./urandom -jar app/portfolio-api.jar
