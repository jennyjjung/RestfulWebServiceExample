# RestfulWebService with StudentList
## Web Services?
- a service offered by an electronic device to another electronic device, communicationg with each other via the **World Wide Web**, or
- a server running on a computer device, listening for requests at a particular port over a network, serving web documents (HTML, JSON, XML, images).
(Wikipedia)

## ReST
- **Re**presentational **S**tate **T**ransfer 
- ReSTful : web API that obeys the REST constraints

## Use Nouns instead of Verbs
- We named our URL like "/addStudent", "/editStudent", "/processStudent", "/deletestudent" or so on.
- Using HTTP methods, it describes what we are doing rather than the URL.
- For example,
  - GET/students/001
  - DELETE/students/001
  - POST/students
  - PUT/students/001

### HTTP methods for a collection
- GET: retrieve the entire collection
- PUT: replace the entire collection
- POST: create a new entry in the collection then return its id
- DELETE: remove the entire collection

### HTTP methods for an element
- GET: retrieve an individual entry from the collection
- PUT: replace an individual entry in the collection
- POST: NOT used
- DELETE: Remove an individual entry from the collection

## Evaluate : POSTMAN
- POSTMAN is an application used for API testing.
- It is an HTTP client that tests HTTP requests, utilizing a graphical user interface, through which we obtain different types of responses that need to be subsequently validated.
