# REST Partner University System
Created By Jackson Scifres
Using the Sutton framework, and REST methodology. 
This system uses a server and In Memory data storage to store and update different partner universities, and the class modules available at these universities. 

# For Integration Testing

To Run the integration tests you simply need to navigate to the folder on your home PC, and run mvn verify in the terminal. 
I ran into some issues with Docker not wanting to run correctly. In case this happens I reccomend briefly turning on the setting 
"Expose daemon on tcp://localhost:2375 without TLS" in the Docker/General settings. I would not keep this setting up in the long term
as it exposes you to remote code execution attacks.

# Running the Program

To run the server outside of integration testing, you need to compile and run the Start.java file. This will host the server on your localHost with 
a port of 8080. From there you can make http requests through a browser or a program such as Postman. 
