# Weather
The services is designed for an android application [Weather: Any place on the Earth!](https://play.google.com/store/apps/details?id=net.c7j.wna&hl=ru "Google Play")

### Functional services

##### Geomagnetic API

The service requests a geomagnetic forecast from [SWPC NOAA API](https://services.swpc.noaa.gov/text/3-day-geomag-forecast.txt "Geomagnetic Forecast as .txt file") as a common .txt file on a daily basis. After the file with forecast is received and parsed by the service, the result is stored to the database. All the process is entirely self-acting.  
The Weather Application accesses the API via REST.


### Infrastructure services

##### Config Service
The service is used for a loading of properties from a private git repository via ssh. The Config Service is launched by default port: 8888 and has a root path: /config-service

## How to run this project?

### Build
The application can be built via one of the next gradle command:
 
`./gradlew build` on a Linux machine  
`gradlew.bat build` on a Windows machine

### Liquibase
For testing or prod stages must be used [Liquibase Gradle Plugin], the plugin is needed to manual a run liquibase's scripts.
To run correctly you need it to set the next environments:  
`LIQUIBASE_GEOMAGNETIC_DATABASE_URI` - a current database uri  
`LIQUIBASE_GEOMAGNETIC_DATABASE_PORT` - a current database port  
`LIQUIBASE_GEOMAGNETIC_DATABASE_USER` - a current database username  
`LIQUIBASE_GEOMAGNETIC_DATABASE_PASSWORD`- a current database password  
`LIQUIBASE_GEOMAGNETIC_DATABASE_SCHEMA` - a current database schema  
    
A command executing scripts:  
`./gradlew :{liquibase-service-name}:update` on Linux  
`gradlew.bat :{liquibase-service-name}:update`on Windows  
  
A command deleting all (**I recommend to use extremely carefully this command! And only on a test stage!**):  
`./gradlew :{liquibase-service-name}:dropAll` on Linux  
`gradlew.bat :{liquibase-service-name}:dropAll` on Windows

For more details to read an [official page](https://github.com/liquibase/liquibase-gradle-plugin) 


### Local launch

#### Dev mode
If you want to launch the project within an IDE (for example, Intellij IDEA) you should:
* have a PostgreSQL instance
* have a config's git repository
* launch services from under a 'dev' profile of Spring
* create a `bootstrap-dev.yaml` to a `resources` folder of any services except a config-service
* create an `application-dev.yaml` to a `resources` folder of the config-service

A pattern of an `application-dev.yaml` via an username and a password
```
spring:
  application:
    name: config-service
   cloud:
     config:
       server:
         git:
           clone-on-start: true
           search-paths: "{application}/{profile}"
           username: git-repository-username
           password: git-repository-password
           uri: git-repository-uri
server:
  port: 8888
  servlet:
    context-path: /config-service
```
A pattern of an `aplication-dev.yaml` via ssh
```
spring:
  cloud:
    config:
      server:
        git:
          clone-on-start: true
          ignore-local-ssh-settings: true
          search-paths: "{application}/{profile}"
          uri: git-repository-uri
          private-key: ssh-private-key
server:
  port: 8888
  servlet:
    context-path: /config-service
```

##### Notes
* A structure of a git repository must be the next ```{name_of_service}/{profile}/{config.yaml}``` 
* You can find an example of configurations to ```config-service/*/resources/shared/*```
* You should set a`ignore-local-ssh-settings: true` directive only if your private ssh-key differs from a standard private ssh-key
* A .gitignore file contains ```*-dev.*``` so git won't track any 'dev' configurations
* If you don't want to enable Spring Liquibase then you can turn off it the next directive: `spring.liquibase.enabled: false` when you can use a [liquibase gradle plugin](https://github.com/Illine/weather#liquibase)

For more details to see [official Spring documentations](https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html)

### Docker

**Note that** before the first start-up of the application you will need the Internet because a Gradle Wrapper will be downloaded, also you should have an installed Docker and Docker Compose!

**Before you start, make sure a build passes without errors!**

#### Local mode
If you want to launch this project yourself (as a developer, for example) within the local Docker you should have a cloned project,
after that run a command:  
`./docker_start_native.sh` on a Linux machine  
in a root directory of one.   

If you have the Windows machine when you should export next environments:
```
GEOMAGNETIC_OPTS="-Dspring.profiles.active=native"
CONFIG_SERVICE_OPTS="-Dspring.profiles.active=native"
``` 

**Make sure** you have assembled artifacts    
If you don't have the assembled artifacts then read a [build](https://github.com/Illine/weather#build) of the project

After that run a command:  
`docker-compose -f docker-compose.yaml -f docker-compose-native.yaml up -d`    

Images will be created and run:
* _postgres:9.5-alpine as geomagnetic-database, a port 5432_
* _weather/config-service:native as config-service, a port 8888_
* _weather/geomagnetic-api:native as geomagnetic-api, a port 8001_

For a comfortable testing all services are launched via a 'bridge' network mode.

When you will need to stop a running application, input a command:  
`docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml -v --rmi all`  
in a root of the project.  

After completion of the command, the images will be removed (include a base image of the PostgreSQL!), also created networks and volumes will be deleted.

#### Testing mode

#### Production mode

### Tech
The Weather uses next libraries:

* [Spring Boot]
* [Spring Web]
* [Spring JPA]
* [Spring Actuator]
* [Spring Validation]
* [Spring Cloud Config]
* [Spring Cloud Config Server]
* [Hibernate]
* [Model Mapper]
* [Liquibase]
* [Liquibase Gradle Plugin]
* [PostgreSQL]
* [Lombok]
* [JUnit5]
* [H2]
* [P6 SPY]

### License
[MIT](LICENSE)

This project itself is open source with a [public repository][git-repo].


[Spring Boot]: <https://spring.io/projects/spring-boot>
[Spring Web]: <https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/2.1.5.RELEASE>
[Spring JPA]: <https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/2.1.5.RELEASE>
[Spring Actuator]: <https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-actuator/2.1.5.RELEASE>
[Spring Validation]: <https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation/2.1.5.RELEASE>
[Spring Cloud Config]: <https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-config/2.1.3.RELEASE>
[Spring Cloud Config Server]: <https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-config-server/2.1.1.RELEASE>
[Hibernate]: <http://hibernate.org/>
[Model Mapper]: <http://modelmapper.org/>
[Liquibase]: <https://www.liquibase.org/>
[Liquibase Gradle Plugin]: <https://plugins.gradle.org/plugin/org.liquibase.gradle>
[PostgreSQL]: <https://www.postgresql.org/>
[Lombok]: <https://projectlombok.org/>
[JUnit5]: <https://junit.org/junit5/>
[H2]: <https://www.h2database.com/html/main.html>
[P6 SPY]: <https://p6spy.readthedocs.io/en/latest/>
[git-repo]: <https://github.com/Illine/geomagnetic-api>
