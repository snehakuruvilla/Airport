##AIRPORT

###AIRPORT API ####Java Spring Boot Application

#####Description: The assessment consists of an APIs to fulfill airport related functionality.

#####Requirements:

- Runways for each airport given a country code or country name. 
- Top 10 countries with highest number of airports.

#####Pre Assumptions:

The data is loaded in the MySQL database from CSV files into their respective tables
(Countries, Airport, Runways)

#####Tech Stack

Java 8+ 
Spring Boot 
MySQL database 
JUnit 4 
Mockito 
Maven 
Swagger

#####How to run

$ import the project Airport 
$ convert to maven $ run as maven build -- clean install 
$ run as spring boot app 
$ application swagger will be available in: http://localhost:8080/swagger-ui/index.html



######Sample Data

Sample postman request and response is committed.

#####Table Details:

Countries --> To store the countries 
Airport --> To store the airport details. 
Runways --> To store the runways. 

#####The application apis

POST /api/airport/runways - Runways for each airport given a country code or country name. 
GET /api/airport/topairports - Top 10 countries with highest number of airports. 
GET ​/api/airport/countrycode?code= - Retrieving the information given a partial/fuzzy country code. 
GET ​/api/airport/countryname?name= - Retrieving the information given a partial/fuzzy country name. 

JUnit test are available. 
Code quality checked using Sonar.

####REMARKS

####Example Api Request: 
Avialble in the file Airport.postman_collection.txt
