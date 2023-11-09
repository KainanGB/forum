FROM azul/zulu-openjdk:17.0.9

COPY target/forum-0.0.1-SNAPSHOT.jar forum-api.jar

CMD ["java", "-jar", "forum-api.jar"]