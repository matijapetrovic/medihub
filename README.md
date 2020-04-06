MediHub
============
Introduction
------------
MediHub is a university project for the *Internet Software Architectures* course used to showcase knowledge of enterprise web application development. The main purpose of the application is to enable clinical center patients to register and schedule appointments at clinics as well as doctors of their choice. It also supports management of entities such as doctors, clinics, rooms and enables medical staff to check their working calendar and request vacation days.

How to run
----------
The technologies used to develop the application are a back-end API supported by Java and the Spring framework and a front-end UI made with Vue.js.

### Running the API
The requirements for the API are:
* Java JDK 14
* Maven
#### IntellIJ
In order to start the API project using Intellij IDEA, you should choose
> New -> Project from Existing Sources

and select medihub-api as the directory
```
medihub
│   README.md
│   .gitignore
└───medihub-ui
└───medihub-api     <- select
    │   pom.xml
```
Afterwards, use the built-in maven functionalities to compile and run the project.
It is now running on http://localhost:8081.
```
medihub
│   README.md
│   .gitignore
└───medihub-ui
└───medihub-api
    │   pom.xml
    └───spring-configuration
         └───src
             └───main
                  └───resources
                       └───application.properties   <- change    
```

### Running the Client
In order to start the client from the command line, you need to run the following commands
```
cd medihub-ui
npm install
```

and after the installation is finished
```
npm run serve
```
The client is now running on http://localhost:8080

Authors
-------
* Matija Petrović [GitHub](https://github.com/matijapetrovic/)
* Nikola Kabašaj [GitHub](https://github.com/nikolakabasaj/)
* Jovan Bodroža [GitHub](https://github.com/roza44/)
