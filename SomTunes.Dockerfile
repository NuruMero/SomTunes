FROM openjdk:19

COPY target/song-0.0.1-SNAPSHOT.jar /somtunes.jar

CMD ["java", "-jar", "/somtunes.jar"]