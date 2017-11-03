# profilizer-server
Profilizer server spring boot

# Profilizer service

The idea is to give a quiz experience to the user, going throghout different questions separated by some category types. The user can start a test, continue a test if it was not completed, and can check and search the answers once the test is finished.

# Server technologies
  - Spring boot
  - Mongodb
  - Docker
  - Rest assured
  
# To run the server you will need
  - Maven https://maven.apache.org/download.cgi
  - Docker https://docs.docker.com/engine/installation/
  - Docker compose https://docs.docker.com/compose/install/
  
First just execute the command to generate the docker application container

  - mvn clean package docker:build -DskipTests=true

And next to make the server goes live just execute:

 - docker-compose up

# To execute the Unit and Integration tests just execute the command in the roor folder
 - mvn verify (The integration tests are dependent of a server instance running to be executed)

Server Integration Tests
============

# Overview
The server integration tests are executed with the use of maven, junit, and rest-assured (http://rest-assured.io/).

# Execution
To run the integration tests, execute the following command from the root directory:

mvn failsafe:integration-test `[-Dserver.host=<url>`] `[-Dserver.port=<port>`]

The default values for the above parameters are:

server.host = http://localhost
server.port = 8080

This will run all tests against the configured server and show the results on the console when finished.

**NOTE: The server should be restarted prior to each test run to ensure that it is in a known state.
