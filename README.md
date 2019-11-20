# NYTimesSample

Sample Android App with developed in Kotlin using MVVM Architecture


##  Hightlights:

- MVVM Architecture Pattern
- Using Architectural components ( LiveData, DataBinding, LifeCycle)
- Retrofit2 Network Library
- Caching support
- Usage of AndroidX libraries
- SonarQube integration

The application has inbuilt cache support to avoid redundant network requests. 
After the first network request, the response is stored in the cache - in memory. 

## Architecture

![alt text](https://github.com/jitendar7/NYTimesSampleApp/blob/master/architecture/MVVM_Architecture.png)



## Best Android App Development Practices followed

- Written in Pure Kotlin
- Kodein for dependency injection
- JUnit4, Mockito & MockWebServer for writing the testcases
- Repository Pattern
- Testable code 
- Latest Retrofit2 library for Networking
- MVVM Architecture 


## Screenshots 

![alt text](https://github.com/jitendar7/NYTimesSampleApp/blob/master/screenshots/device-2019-11-20-042420.png)


## How to Build? 

Open the terminal and type in the below command to generate

for debug build
```
./gradlew assembleDebug
```


for release build
```
./gradlew assembleRelease
```

## Generating Sonarqube report
Make sure in build.gradle ( app level ), we have the configuration of sonarqube as per our server configuration

Add sonarqube server url in gradle.properties
```
systemProp.sonar.host.url=http://localhost:9000
```

## Generating code coverage report

use the below command 
```
./gradlew clean jacocoTestReport
```
the reports are generated on the following path
```
app/build/reports
```
