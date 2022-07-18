# Uml Designer Api

![vulnerabilities](https://img.shields.io/snyk/vulnerabilities/github/d-najd/UmlDesignerAppApi)
![reposize](https://img.shields.io/github/repo-size/d-najd/UmlDesignerAppApi)

# Getting Started

Api for an app used for generating uml diagrams in multiple different ways

Follow the instructions below to get a copy of the project running on your local machine

## Prerequisites

* Java JDK 17
* Docker (WORK IN PROGRESS)
* Git

## Installing

* clone the repository using [Git](https://git-scm.com/downloads)
```Git
git clone https://github.com/d-najd/UmlDesignerAppApi
```
* Set datasource url of your MYSQL DB application.properties -> spring.datasource.url
* Set mysql user value into application.properties -> spring.datasource.username
* Set mysql user password value into application.properties -> spring.datasource.password

## Build and Start the project

* Go to root directory of the project
* mvn clean package 
* java -jar .\target\umldesigner-0.0.1-SNAPSHOT.jar

## Docker Setup (WORK IN PROGESS)
 there is file called db_tables_uml.txt which has the infrastructure of the database, that will have to do until docker is implemented

# Features
 - OAuth 2.0 auth (work in progress)
 - Json support (work in progress)
   - Ability to generate uml diagrams from json files
 - Mysql support (work in progress)
   - Ability to generate uml diagrams from mysql code

# Demo
 Visit https://www.postman.com/collections/e3ef56648f12f06fc6e4 to see available mappings

# Downloads (WORK IN PROGRESS)
# Related Links
The app which the api is built for https://github.com/d-najd/UmlDesigner
