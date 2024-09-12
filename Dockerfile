FROM openjdk:21 as LATTANZ
LABEL authors="m.lattanzio"


COPY Organizations/target/Organizations-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "Organizations-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080