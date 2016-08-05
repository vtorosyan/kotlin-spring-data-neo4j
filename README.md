# Kotlin example with Spring Data Neo4J and Spring Web

A basic application which uses Kotlin as a programming language to demonstrate power of Kotlin and its basic principles. 

The application uses [Neo4J Movie demo] (https://neo4j.com/developer/example-project/) database as data set and demonstrates connection to and usage of [Neo4J] (https://neo4j.com/) Graph Database using Spring Data Neo4j. 

## Pre-requisites
* JDK 1.6 or 1.7 or 1.8
* Neo4J installed and running, with the Movie Dataset
* Maven

## The Stack
Kotlin is the programming language used in the application, plus
* Spring Boot
* Spring Data Neo4J
* Spring Web and Spring Data Rest
* Neo4J Server
* Kotlin JUnit, Hamcrest, JUnit, Neo4J OGM Test
* Embedded Neo4J Server is used for tests

## Run on your local
1. Download, install and start [Neo4J Server] (https://neo4j.com/download/)
2. Setup Movie Dataset
   * Open the Web Interface of Neo4J by typing http://localhost:7474 in your browser
   * Configure 'kotlin' as password
   * Run `:play movies`, click and run the Cypher statement
3. Go to the project root, and run `mvn spring-boot:run`
4. Play with the following endpoints, by either typing them in your browser or using [`curl`] (https://en.wikipedia.org/wiki/CURL)
```
   // Search persons by name (example name - Tykwer) 
   http://localhost:8080/persons/:name
   
   // Search movies by name - (example title - matrix) 
   http://localhost:8080/movies/:title
   
   // Display graph of movies with meta info
   http://localhost:8080/movies/graph
```
