FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD travel-club-boot/build/libs/travel-club-boot*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]