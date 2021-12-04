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
- (Mapping Example codes)
```java
// GET - collection - read	localhost:8080/students
@GetMapping("/students")
public List<Student> getAllStudents() {
  return stuRepo.getStudents();
}
  
// POST - collection - add	localhost:8080/students
// *** Not done for a single element *** POST - add
@PostMapping(value="/students", headers={"Content-type=application/json"})
public String addStudent(@RequestBody Student student) {
  stuRepo.addStudent(student);
  return "Student was added";
  // why do we need return statement? 
  // To ensure that the request was successfully sent with no errors
}
```

### HTTP methods for an element
- GET: retrieve an individual entry from the collection
- PUT: replace an individual entry in the collection
- POST: NOT used
- DELETE: Remove an individual entry from the collection
- (Mapping Example Codes)
```java
// DELETE - element - delete localhost:8080/students/{id}
@DeleteMapping(value="/students/{id}", headers={"Content-type=application/json"})
public String deleteOneStudent(@PathVariable int id) {
  stuRepo.deleteStudentById(id);
  return "Student ID " + id + " deleted";
}

// PUT - element - edit	localhost:8080/students/{id}
@PutMapping(value="/students/{id}", headers={"Content-type=application/json"})
public String replaceOneStudent(@PathVariable int id, @RequestBody Student student) {
  stuRepo.updateStudent(student, id);
  return "Student Id " + id + " Updated";
}
```

## Evaluate : POSTMAN
- POSTMAN is an application used for **API testing**.
- It is an HTTP client that tests HTTP requests, utilizing a graphical user interface, through which we obtain different types of responses that need to be subsequently validated.


