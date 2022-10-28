# Micro Service Student Help
## :deciduous_tree: Tree Structure
```
src
├───main
│   ├───java
│   │   └───be
│   │       └───ecam
│   │           └───ms_studenthelp
│   │               ├───Database
│   │               │   └───mysql
│   │               │       └───MySqlSerializer
│   │               ├───Interfaces
│   │               ├───Object
│   │               └───utils
│   └───resources
└───test
    └───java
        └───be
            └───ecam
                └───ms_studenthelp
```

## Project Context
In a perspective to develop and modify a Remote-Intranet-School-Environment, we have to design a school support microservice called 'Student Help'.
More specifically, this repository is dedicated to the backend of 'Student Help'.  
This microservice must be able to accomplish some specific actions. 
In a such purpose it should be able to allow the users to :
- Make and publish a 'post'.
- Get a specific post, update or delete it.
- React to 'posts' and comment ( answer) them.
- Create a thread and update or delete it.



## Prerequisite
This service is using an API (Application Programming Interface).  
We suggest you to go on these websites to get more informations about API :
- [API](https://www.redhat.com/en/topics/api/what-are-application-programming-interfaces)
- [API French Link](https://www.mulesoft.com/fr/resources/api/what-is-an-api#:~:text=API%20est%20l'acronyme%20d,applications%20de%20communiquer%20entre%20elles)

The service is also using a MySQL/MariaDB database, so you should have a good understanding of relational database.  
Also, to increase flexibility and portability we used Docker to contain and launch our service on several devices. To get more explanations about Docker, we suggest you to read this documentation :  
- [Docker](https://codefresh.io/docs/docs/learn-by-example/java/gradle/?fbclid=IwAR0Ty11lyrUBfOAR9flhZWoXSulKcOi1rNsyMq9tJOPKiVWQMVPH8ZmejwE)


## Installation

"Explain the installation with Docker"

### Launching

"Explain how to launch the service with Docker"

## API

Our OpenAPI schema is in [`schema.yaml`](schema.yaml).
It also has a generated doc available at this link [API online documentation](https://beta.bachelay.eu/ms-studentHelp)

## Database

Uses a MySQL/MariaDB database.

The schema is available in [`ms_studenthelp.sql`](ms_studenthelp.sql)

The credentials are in the following file : [Application.Properties](.\ms_studenthelp\src\main\resources\application.properties)

You can change the credentials manually by changing `dummy` and `1234` in these lines : 
- spring.datasource.username=`dummy`  
- spring.datasource.password=`1234`

## Diagram
### Database
![](ms_studenthelp.png)


### Class
![](https://lucid.app/publicSegments/view/9de2afd8-5cb7-414e-8c9d-8eb506c31ad0/image.png)
