# 1. Aşama: Derleme (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Önce bağımlılıkları ve kodu kopyala
COPY . .
# Docker içinde temiz bir paketleme yap (Repackage otomatik hallolur)
RUN mvn clean package -DskipTests

# 2. Aşama: Çalıştırma (Run)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# Build aşamasında oluşan JAR'ı kopyala
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
