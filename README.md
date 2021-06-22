# BoardGame REST API

Is a REST API that can be used for Implementing game throug HTTP Requests

----

Group

- Alex Zanetti

- Stuflesser Lisa

---

## Requisite

- Java 11

- [Tomcat](http://tomcat.apache.org/)
  
  - Installation Guide [Link](https://www.baeldung.com/tomcat)

- Maven

---

## Building

```bash
mvn clean package
```

It will create a .war in the target folder that need to be place inside the tomcat folder

---

### Testing

```bash
mvn test
```

For running all the test

---

## Documentation

[REST API Documentation](https://documenter.getpostman.com/view/15807989/Tzeaj6C7)



WebSocket Game Stream

```http
ws://localhost:9090/games/[GAMEUUID]
```

For connecting to the Websocket

---

## Programming Techniques used

- Abstract Classes

- Collections

- Custom exceptions

- Exception handling

- Method overriding

- Lambdas

- Streams

- Optionals

- Serialization (to JSON, XML, CSV)

- Deserialization (from JSON, XML,CSV)

- HTTP

### Technologies Used

- Spring Framework

- Jackson

- Database(H2)

---
