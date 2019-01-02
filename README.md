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

2. Navigate in browser to: *http://localhost:8080/api/swagger-ui.html*

**End2End Scoring Post (Store)**

1. Click on *scoring-controller*.

2. Click on *Post /scoring/store*.

3. Click on *Try it out*.

4. Click on *Choose File*.

5. Choose the file *<this.root>/src/test/resources/com/scoring/datastore/datastoreInput.txt*.

6. Click *Execute*

**End2End Scoring Post (Query)**

1. Click on *scoring-controller*.

2. Click on *Post /scoring/query*.

3. Click on *Try it out*.

4. Click on *Choose File*.

5. Copy the contents of the file *<this.root>/src/test/resources/scoringQueryFilterSelect.json*.

6. Paste the contents of *scoringQueryFilterSelect.json* into the request body field.

7. Click *Execute*
            
