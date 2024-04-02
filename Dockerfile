FROM openjdk:17
EXPOSE 8080
ADD ShiftO-Backend/target/ShiftO-Backend-1.0-SNAPSHOT.jar shiftO-backend.jar
ENTRYPOINT ["java", "-jar", "/shiftO-backend.jar"]
