# profilizer-server
Profilizer server spring boot

# Profilizer service

The idea is to give a quiz experience to the user, going throghout different questions separated by some category types. The user can start a test, continue a test if it was not completed, and can check and search the answers once the test is finished.

# Server technologies
  - Spring boot
  - Mongodb with docker
  
# To run the server you will need
  - Maven https://maven.apache.org/download.cgi
  - Docker https://docs.docker.com/engine/installation/
  - Docker compose https://docs.docker.com/compose/install/ (Just the mongodb will use docker)
  
After install the requirements, you first will need to generate the jar file of the application.
  
- After clone the project go the root folder and execute "mvn install"
- You will see Inside the folder /target the "profilizer-service-0.0.1-SNAPSHOT.jar"
    
  Now it's docker time
   - In the same root folder just execute docker-compose up and the database will be ready
    
  Now you can just execute the jar file with the command: java -jar profilizer-service-0.0.1-SNAPSHOT.jar in the terminal
  and the application will start and you can get your IP address and configure in the Android app
  
# To execute the Unit and Integration tests just execute the command in the roor folder
 - mvn verify
