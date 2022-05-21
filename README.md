# Getting Started

## How to test

* Start the application (standard Spring Boot app) in your local environment
    * Or start the application with docker by `cd` to project root and running `./docker-build-run.sh`
* Visit [local Swagger webpage](http://localhost:8080/swagger-ui.html)
* Test the app using the GET /regex endpoint

### NOTES

The app takes for granted

* Input strings contain only `UPPERCASE` letters and numbers (es. AAA123AA12A).
* Input strings always begins with a letter.
