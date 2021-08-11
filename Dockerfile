FROM maven:3.8.1-openjdk-11

RUN mkdir -p /app
WORKDIR /app
ADD . /app

RUN mvn package
RUN mkdir /usr/src/myapp && mv /app/target/iter-*.jar /usr/src/myapp/iter.jar

EXPOSE 8080

WORKDIR /usr/src/myapp
CMD ["java", "-jar", "./iter.jar"]