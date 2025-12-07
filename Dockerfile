# =========================
# 1º ESTÁGIO: BUILD
# =========================
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

#docker build -t clinica .

# =========================
# 2º ESTÁGIO: RUNTIME
# =========================
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#docker run -p 8080:8080 clinica