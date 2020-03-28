# Semester 3 Exam


### Build Status
* [Link to build](https://travis-ci.org/Castau/3semesterExam?utm_medium=notification&utm_source=github_status)  
__Backend server has been shut down__
* ~~[![Build Status](https://travis-ci.org/Castau/3semesterExam.svg?branch=master)](https://travis-ci.org/Castau/3semesterExam)~~


### Deployed Client Application
* [Frontend Repository - the frontend folder above](https://github.com/Castau/3semesterExam/tree/master/frontend)
* [Deploy - sem3exam-camilla.surge.sh](https://sem3exam-camilla.surge.sh/)

### Backend Application
* [Backend Repository - the src folder above](https://github.com/Castau/3semesterExam/tree/master/src)  
__Backend server has been shut down__
* ~~[Deploy - camillastaunstrup.dk/sem3exam](https://camillastaunstrup.dk/sem3exam/)~~
* ~~[Populate database with testuser - can be done from frontend as well](https://camillastaunstrup.dk/sem3exam/api/info/testdata)~~

## The Assignment
* ~~[Exam Paper](https://docs.google.com/document/d/16N3h0TVzfwPJEr8SwP2l_DAvGsODrjtc13TtTBnjGK8/edit)~~ - __has been removed by the school, se below__

## Movie site with data fetched from external API’s
Your task is to create a proof of concept solution for a site where users can request information related to specific movies. You will use a single API (set up for this exam) for what you must do, but the exercise simulates an architecture as sketched in this figure, and you should interpret each endpoint in the supplied API as a separate server. The single Server which includes all endpoints for this exercise: [https://exam-1187.mydemos.dk/ (has been taken down)](https://exam-1187.mydemos.dk/)  
  
#### These are the initial requirements given for the project.  
* It must be implemented as a modern Single Page Application  
* The backend must be implemented with Java,  JPA, REST (with JAX-RS) and a MySQL database  
* We do expect some Test Cases for the project, but since this project makes external requests and since we have not covered how to mock such requests, you can leave out tests for this part.  
* The frontend must be implemented with React  
* The project must use a modern DevOps pipeline, using Travis as a build server. This should be the VERY FIRST thing you set up, and we expect it to build, run your tests and deploy to your droplet which should be set up with your own domain name and use HTTPS.  
* We expect that you can demonstrate your project, both locally (so we can add changes) and remotely on your Droplet.  
* We assume that the site requires clients to authenticate for some operations. Users and roles are left for you to decide. If you have a ready to use start-project with support for this, feel free to use that.  

#### Backend
●	Implement this endpoint: 	`api/movie-info-simple/:title`
which when called like this: 	`api/movieInfo/Die Hard`
Should return a response like this:

```Javascript
{
  "title": "Die Hard",
  "year": 1988,
  "plot": "John McClane, officer of ….",
  "directors": "John McTiernan",
  "genres": "Action,Thriller",
  "cast": "Bruce Willis,Bonnie Bedelia,Reginald VelJohnson,Paul Gleason",
  "poster":"https://m.med……._.jpg"
}
```
* The server must fetch this information from these two "different" servers (response from the second server is visualized with yellow above) and can be used by non-authenticated users: 
```
http://exam-1187.mydemos.dk/movieInfo/:title
http://exam-1187.mydemos.dk/moviePoster/:title
```
* Implement a new endpoint:	`api/movie-info-all/:title`  
which when called like this: 	`api/movieInfo/Die Hard`  
Should return a response similar to the one above, but this time also with movie-scores from imdb, Rotten Tomatoes and metacritic, fetched by your server, from the relevant "servers" at the API given for this exercise. You decide how you want to present the information, but it must be returned as one single JSON-response. This endpoint must require a client to be authenticated with a sufficient role.  
* Add a number of test-users and the required roles to your database and test the endpoints using a browser and/or Postman.  
* On your backend, create the necessary entity class(es) to store the response for all requests made to `api/movie-info-all/:title`.
This/these Entity class(es) must be used to solve the following requirements, given in prioritized order (focus on the last one, only if you have time):
  * Provide historical information about how often a movie has been searched for.  
  * Provide the full JSON string from the original response, which can be used as a cached value to prevent additional external request to be made,  for this movie. We won’t get updated scores when they change, but don’t think about that for this exercise (add a timestamp, so we could refresh after a given time period if you like)  
  * Allow for local queries (not involving a remote request) like:
    * Find movies from a specific year
    * Find all movies with a specific actor
* Implement a new endpoint:  `api/movie-count/:title`
which should return a JSON-response including the total number of requests made for this specific movie.
This endpoint should require users to be authenticated with an “admin-role”. 
* Change the endpoint `api/movie-info-all/:title` to return the cached value implemented above instead of making the external requests (the count value, must still be updated)

#### Frontend
* If not already supplied by your start-code, provide a login feature
* Create a page that allows all clients to access, and see the response, from this endpoint: `api/movie-info-simple/:title`. Come up with a way for users to supply the title
* Create a page that allows logged-in users to access, and see the response, from this endpoint:
`api/movie-info-all/:title`
* Create a page, accessible only for admins, that will make it possible to use the api/movie-count/:title endpoint and present the result.

__Extra__: If you have time, add any features you find relevant (to showcase your skills) if not present in the requirements given above.



