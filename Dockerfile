FROM eclipse-temurin:21

# Copy the fat JAR from the build stage
WORKDIR /opt/app
COPY target/*.jar app.jar
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
