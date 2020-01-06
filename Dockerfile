FROM openjdk:8
VOLUME /tmp
EXPOSE 8803
ADD ./target/CurrentAccount-0.0.1-SNAPSHOT.jar current-server.jar
ENTRYPOINT ["java","-jar","/current-server.jar"]