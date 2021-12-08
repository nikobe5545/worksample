## Prerequisites
1. Java 11 https://openjdk.java.net/projects/jdk/11/
2. Node >= 16.13.0. https://nodejs.org/en/ Earlier versions will probably work, but it hasn't been tested.

## Run
Instructions for running on mac and linux boxes. The below will probably work on Windows as well possibly with some minor modifications.
1. Clone repository
2. Open a terminal and cd into the project directory
3. Build the Spring Boot application by running `./gradlew build`
4. Start the Spring Boot application by running `./gradlew bootRun --args='--spring.profiles.active=local'`
5. Open a new terminal and cd into the project directory
6. Change directory to the frontend directory: `cd frontend`
7. Build/install the frontend by running `npm install`
8. Start the frontend by running `npm start`

Now open http://localhost:3000 in your Chrome browser and try it out. Other browsers might work too, but it hasn't been tested.

If you want to access the backend directly: http://localhost:8080

A Postman (https://www.postman.com/) collection for the backend can be found in `devtools/postman` folder.

## Development considerations
I chose to implement the backend solution using Java with Spring Boot. The database is an in-memory DB and the schema
is created using Liquibase. A default customer (me!) is inserted on startup when using the `local` profile.

Nowadays I generally prefer Python which I think typically needs less boilerplate code and is also more readable in my opinion. 
In this case I felt Java was a better fit as it was preferred along with C# (as per the task description), 
and completing the task was perfectly feasible with java.

When it comes to testing the code I typically focus more on unit tests over integration tests. I also
tend to restrict testing to the business logic, not testing getter/setters, framework code etc.
In this particular mini project that meant that there was not much to test.

The frontend setup is not done by a pro as you might see. I'm not a frontend guy, but I still enjoy coding in javascript.
I'm eager to learn more in that field.
