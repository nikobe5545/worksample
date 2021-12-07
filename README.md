## Prerequisites
1. Java 11 https://openjdk.java.net/projects/jdk/11/
2. Node >= 16.13.0. https://nodejs.org/en/ Earlier versions will probably work, but it hasn't been tested.

## Run locally

Instructions for running on mac and linux boxes. The below will probably work on Windows as well with possibly minor modifications.
1. Clone repository
2. Open a terminal and cd into the project directory
3. Build the Spring Boot application by running `./gradlew build`
4. Start the Spring Boot application by running `./gradlew bootRun --args='--spring.profiles.active=local'`
5. Open a new terminal and cd into the project directory
6. Change directory to the frontend directory: `cd frontend`
7. Build/install the frontend by running `npm install`
8. Start the frontend by running `npm start`

Now open http://localhost:3000 in your Chrome browser (other browsers might work too, but has not been tested) 
and try it out.

If you want to access the backend directly: http://localhost:8080

A Postman (https://www.postman.com/) collection for the backend can be found in `devtools/postman` folder.

## Development considerations
I chose to implement the backend solution using java with Spring Boot. I could have chosen Python too which
I think generally needs less boilerplate code and is also more readable in my opinion. But in this case
I chose Java as it was preferred along with C#.

When it comes to testing the code I generally prefer unit tests over integration tests. I also
tend to restrict testing to real logic, not testing getter/setters, not testing framework code etc.
In this particular mini project that meant that there was not much to test.

The frontend setup is not done by a pro as you might see. I'm not a frontend guy, but I still enjoy coding in javascript.
I'm eager to learn more in that field.