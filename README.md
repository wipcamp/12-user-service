# WIP12 User Service

## Requirements
For building and running the application you need:
  * [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  * [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Set up Environment Variable

Environment variables can be specified using the environmentVariables attribute. The following sets the 'ENV1', 'ENV2', 'ENV3', 'ENV4' env variables:

```xml
<project>
  ...
  <build>
    ...
    <plugins>
      ...
      <plugin>
        <groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <environmentVariables>
                <ENV1>5000</ENV1>
                <ENV2>Some Text</ENV2>
                <ENV3/>
                <ENV4></ENV4>
            </environmentVariables>
        </configuration>
        ...
      </plugin>
      ...
    </plugins>
    ...
  </build>
  ...
</project>
```