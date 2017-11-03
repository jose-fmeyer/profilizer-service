# profilizer-server
Profilizer server spring boot

# Profilizer service

The idea is to give a quiz experience to the user, going throghout different questions separated by some category types. The user can start a test, continue a test if it was not completed, and can check and search the answers once the test is finished.

# Server technologies
  - Spring boot
  - Mongodb with 
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
 - mvn verify
