# Stage 1: Build the JAR file
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY /portfolioApi/pom.xml .
COPY /portfolioApi/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/portfolio-api.jar /app/portfolio-api.jar
EXPOSE 5000
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/portfolio-api.jar"]
