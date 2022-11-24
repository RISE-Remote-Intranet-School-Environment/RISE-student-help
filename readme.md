# Micro Service Student Help

# Table of contents

* [About the project](#❓-about-the-project)
  * [Thread](#thread)
  * [Post](#post)
* [Getting started](#🚀-getting-started)
  * [Frameworks](#⚡-frameworks)
  * [Prerequesites](#❗-prerequesites)
  * [Set up the database](#📚-setup-the-database)
  * [Installation](#🔨-installation)
    * [Setup with Docker (Recommended)](#🐳-setup-with-docker-recommended)
    * [Setup with Gradle](#🐘-setup-with-gradle)
  * [Run](#🏃-run)
    * [Run with Docker (Recommended)](#🐳-run-with-docker-recommended)
    * [Run with Gradle](#🐘-run-with-gradle)
  * [Run tests](#🎲-run-tests)
* [Tree structure](#🌳-tree-structure)
* [API](#💥-api)
* [Database](#📚-database)
* [Acknowledgments](#📝-acknowledgments)
* [License](#🔒-license)

## :question: About the project

The purpose of this project is to help student during their studies. Every student is able to ask a question about a topic of a specific course, that's what we call a `thread`. Also, student are able to answer to question asked by others, that's what we call a `post`.

In more technical words, **Student Help** is a microservice used as backend for a student forum. The service can be accessed using API REST requests.

### Thread

As explain above, a student can ask a question about a topic of a school subject. That is the way a `thread` is created.

A `thread` can be put in a group of word that are called `tags`. Also, every `thread` must be linked to a school subject, that's what we call a `category`.

The purpose of a `thread` is to start a discussion upon a subject, so that every student can discuss with each other by `posts`.

### Post

A `post` is the way that student can discuss with each other and is always related to a `thread`.

Some `reactions` can be put to `post`. For the moment, the only `reactions` implemented are **likes** and **dislikes**.

## :rocket: Getting started

### :zap: Frameworks

The project is built using the **Java Programming Language** under the **Spring Boot** framework.

Spring Boot is a framework used to build stand-alone application and production ready spring applications. It facilitates the way to launch and deploy API REST based application without huge configurations.

For more information about Spring Boot, view the [official documentation][spring-boot].

### :exclamation: Prerequesites

Before launch the application, you have to satisfy the following requirements.

[Git][git] must be installed and configured on your machine in order to clone the repository and pull the new updates.

A [MySql][mysql] server must be installed on your machine so that the application can connect to the database. It is recommended to have a good knowledge of relational databases. Click here to download [MySql community server][mysql-download].

To increase flexibility and portability, we used [Docker][docker] to contain and launch our service on several devices. If you want to deploy your application using containers, you must have Docker installed on your machine. Otherwise, if you don't want to use Docker you can just install a valid Java JDK and launch it with Gradle.

This step is optional, if you don't want to use the [gradlew](ms_studenthelp\gradlew) file that comes with the repository and want to use [Gradle][gradle] instead, you have to download Gradle by following the [installation procedure][gradle-download].

### :books: Setup the database

Before to launch the application, you must create your database. For that, use the [MySQL database script][database-script]. If you run this file in MySQL Workbench, it will automatically create the database with all its tables.

When the database is set on your server, you must configure the database informations so that the application can connect to it. The configurations must be set in the [configuration file][configuration-file].

You must specify the following parameters:

* application-port
  * Port used by your application. `8080` by default.
* database-ip
  * IP address or domain name of your database
* database-port
  * `3306` by default
* database-name
* database-username
* database-password
* application-name

```properties
server.port = <application-port>
spring.jpa.hibernate.ddl-auto = none
spring.datasource.url = jdbc:mysql://${MYSQL_HOST:<database-ip>}:<database-port>/<database-name>
spring.datasource.username = <database-username>
spring.datasource.password = <database-password>
spring.datasource.driver-class-name = com.mysql.jdbc.Driver
spring.application.name = <application-name>
```

Here is an example of configuration :

```properties
server.port = 8080
spring.jpa.hibernate.ddl-auto = none
spring.datasource.url = jdbc:mysql://${MYSQL_HOST:localhost}:3306/ms_studenthelp
spring.datasource.username = dummy
spring.datasource.password = 1234
spring.datasource.driver-class-name = com.mysql.jdbc.Driver
spring.application.name = demoservice
```

### :hammer: Installation

#### :whale: Setup with Docker (Recommended)

Go to the main repository.

```bash
cd ms_studenthelp
```

Build the docker image.

```docker
docker build -t <your-username>/studenthelp .
```

Example.

```docker
docker build -t alex210501/studenthelp .
```

To check that your Docker image exists, use the following command.

```docker
docker images
```

#### :elephant: Setup with Gradle

If you don't want to install the application with Docker, for example if you are a developper to the application, you can use Gradle instead.

```bash
cd ms_studenthelp
```

Build the gradle task.

```bash
./gradlew build
```

Check that your Gradle file is build with the following command. If it worked, you must see `bootRun` in the Gradle tasks.

```bash
./gradlew tasks
```

Note: You can use Gradle instead of the Gradle Wrapper (gradlew) if you have installed it on your machine.

### :runner: Run

#### :whale: Run with Docker (Recommended)

Supposing that you are on the main repository.

Launch the Docker image. The `<application-port>` is set in the [configuration file][configuration-file].

```docker
docker run -p <application-port>:<application-port> <your-username>/studenthelp
```

You must be able to make HTTP request to the port defined by `<application-port>`.

Example.

```docker
docker run -p 8080:8080 alex210501/studenthelp
```

#### :elephant: Run with Gradle

Supposing that you are on the main repository.

Launch the application using Gradle.

```bash
./gradlew bootRun
```

You must be able to make HTTP request to the port defined in the [configuration file][configuration-file].

Note: You can use Gradle instead of the Gradle Wrapper (gradlew) if you have installed it on your machine.

### :game_die: Run tests

The unit test framework used is [JUnit][junit].

To run the tests, we use the following command.

```bash
./gradlew test
```

Note: You can use Gradle instead of the Gradle Wrapper (gradlew) if you have installed it on your machine.

## :deciduous_tree: Tree Structure

```bash
ms_studenthelp
    ├───gradle
    │   └───wrapper
    └───src
        ├───main
        │   ├───java
        │   │   └───be
        │   │       └───ecam
        │   │           └───ms_studenthelp
        │   │               ├───Database
        │   │               │   ├───entities
        │   │               │   └───repositories
        │   │               ├───Interfaces
        │   │               ├───JsonSerializers
        │   │               ├───Object
        │   │               └───utils
        │   └───resources
        └───test
            └───java
                └───be
                    └───ecam
                        └───ms_studenthelp
```

## :boom: API

For our API REST, we generate our documentation schema using OpenAPI. We have done all of our tests using [Swagger UI][swagger-ui].

We integrated the database in our code using [JPA][jpa].

The OpenAPI schema is in [schema.yaml][open-api-schema].

With the schema generated from Swagger UI, we host our API documentation online using [Stoplight][stoplight]. The documentation is available at [API online documentation][api-documentation].

## :books: Database

As explaing above, we use a relational database such as MySQL or MariaDB.

Here is the database relational diagram used in the project.

![Database diagram][database-diagram]

### Class

**WIP**

## :pencil: Acknowledgments

* [Spring Boot Reference Documentation][spring-boot-documentation]
* [Spring Framework Documentation][spring-documentation]
* [Gradle Guides][gradle-guides]
* [Docker Documentation][docker-documentation]
* [JUnit User Guide][junit-guide]
* [Postman][postman]
* [Introduction to Spring Data JPA][jpa-tutorial]

## :lock: License

Distributed under the AGPL-3.0 License. See [LICENSE.md][license] for more information.

<!-- Internal file links -->
[configuration-file]: ./ms_studenthelp\src\main\resources\application.properties
[open-api-schema]: ./schema.yaml
[database-diagram]: ./ms_studenthelp.png
[license]: ./LICENSE.md
[database-script]: ./ms_studenthelp.sql

<!-- Links -->
[spring-boot]: https://spring.io/projects/spring-boot
[spring-boot-documentation]: https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiM1Z3Gg777AhUKiP0HHb88AK8QFnoECBYQAQ&url=https%3A%2F%2Fdocs.spring.io%2Fspring-boot%2Fdocs%2Fcurrent%2Freference%2Fhtmlsingle%2F&usg=AOvVaw1hehprHejWPlOVUg-kvg1V
[git]: https://git-scm.com/
[mysql]: https://www.mysql.com
[mysql-download]: https://dev.mysql.com/downloads/mysql/
[docker]: https://www.docker.com/
[docker-documentation]: https://docs.docker.com/get-started/
[gradle]: https://gradle.org/
[gradle-download]: https://gradle.org/install/
[gradle-guides]: <https://gradle.org/guides/>
[swagger-ui]: https://swagger.io/tools/swagger-ui/
[stoplight]: https://stoplight.io/
[api-documentation]: https://beta.bachelay.eu/ms-studentHelp
[postman]: https://www.postman.com/
[spring-documentation]: https://docs.spring.io/spring-framework/docs/current/javadoc-api/index.html
[junit]: https://junit.org/junit5/
[junit-guide]: https://junit.org/junit5/docs/current/user-guide/
[jpa]: https://spring.io/guides/gs/accessing-data-jpa/
[jpa-tutorial]: https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
