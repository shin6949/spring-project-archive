FROM circleci/openjdk:11-jdk

ADD ./target/iter-*.jar /usr/src/myapp/iter.jar

EXPOSE 8080

WORKDIR /usr/src/myapp
CMD ["java", "-jar", "./iter.jar"]