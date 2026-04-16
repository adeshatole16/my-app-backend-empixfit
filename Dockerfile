FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Fix permission issue
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]