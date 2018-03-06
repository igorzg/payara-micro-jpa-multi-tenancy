# Requirements:

1. [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. [Gradle 3.5.x](https://gradle.org/) 
3. [Docker](https://www.docker.com/products/overview)

### Start docker mysql containers

```sh
docker-compose up -d
```


###  Building project

```sh
gradle clean build -Penv=local
```


### Starting embedded server:
```sh
gradle startServer -Penv=local -Pport=8080
```

## REST resources:

Creating dummy data:
```sh
http://localhost:8080/db1/client/dummy
http://localhost:8080/db2/client/dummy
```
Reading data:
```sh
http://localhost:8080/db1/client
http://localhost:8080/db2/client
```


###  Building docker:
```sh
gradle buildDocker -Penv=prod
```

### Starting test:
```sh
gradle test
```

### Starting integration tests:
```sh
docker-compose up -d
gradle clean build itest
```

### Example running apps manually:

* You can simply run them as jar applications or 
you can configure your ide to run jar application so you can debug it.
* To download payara micro server run
```sh
gradle downloadPayaraMicro
```
* Build application
```sh
gradle clean build 
```
* Run it manually as java -jar app
```sh
java -jar ./payara-micro.jar --deploy ./build/libs/icoder-1.0.0.war
```
### AWS command options Publishing docker build to ECR:
```sh
gradle clean build publishToECR -Penv=local -Pversion=8.0.0
```

