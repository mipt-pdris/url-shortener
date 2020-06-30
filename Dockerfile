FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/url-shortener-1.0.jar shortener.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/shortener.jar"]
