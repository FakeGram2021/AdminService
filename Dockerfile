FROM maven:3.8.1-adoptopenjdk-11 AS appServerBuild
WORKDIR /usr/src/server
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests

FROM openjdk:11.0.11-jdk-slim AS appServerRuntime
WORKDIR /app
COPY --from=appServerBuild /usr/src/server/target/Admin-0.1.jar ./
EXPOSE 8080
CMD java -jar Admin-0.1.jar