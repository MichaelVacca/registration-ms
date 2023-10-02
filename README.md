# Registration-ms
Reactive microservices
# Description
This is my second complete microservice project where we made the microservices reactive  
# Installation Links
### Docker 
https://docs.docker.com/engine/install/
- Choose the most recent version for your machine
### Postman 
https://www.postman.com/downloads/
- Choose the most recent version for your machine
# Run Process
1. Open Docker on your machine  
2. Navigate to the root folder of the project and open a terminal     
3. Run the command: docker-compose build    
4. Once that's done run docker-compose up   
5. When you want to shut the app off precc CTRL + C in the terminal  
6. After all the services stop (in the terminal) run the command docker-compose down   
7. Can verify in Docker app (no services are displayed)
# Run Tests
### Navigate to the root folder of the project and open a terminal than run the: ./gradlew build    
1 .Once the tests all pass in the terminal you can view the tests by going to the service you want to view test coverage for   
2. Navigate to the "build" folder    
3. "reports" folder    
4. "jacoco"    
5. "tests"    
6. "html"   
7. Than open the index.html in any browser  
