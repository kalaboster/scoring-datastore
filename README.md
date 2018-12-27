# scoring-datastore

### Purpose

**scoring-datastore** is a datastore tool.

### Requirements

Java 1.8, scala and gradle are the primary requirements. 
All other requiremnts are defined in the build.gradle file and related files. 

### Building

*./gradlew bootJar*

### Testing

#### PMD

*./gradlew pmdMain*

#### Unit Testing

*./gradlew test*

#### E2E Testing

1. *./gradlew bootRun*

2. Navigate in browser to: *http://localhost:8080/api/scoring/swagger-ui.html*

3. Use Swagger interface to send requests and view responses.
