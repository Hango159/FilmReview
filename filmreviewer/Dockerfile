# Etapa 1: Build
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /home/app

# Copiem pom.xml separat pentru a profita de cache-ul Docker
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copiem sursele și compilăm aplicația
COPY src ./src
RUN mvn -B clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app

# Rulăm aplicația ca utilizator non-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# JAR-ul Spring Boot generat de Maven
COPY --from=build /home/app/target/*.jar app.jar

ENV JAVA_OPTS=""
EXPOSE 8080

USER appuser

# Respectă PORT dacă platforma de deploy îl setează, altfel folosește 8080
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /opt/app/app.jar"]