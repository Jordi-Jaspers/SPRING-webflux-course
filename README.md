<h3 align="center">Spring Webflux Course</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
  [![GitHub Issues](https://img.shields.io/github/issues/Jordi-Jaspers/SPRING-webflux-course.svg)](https://github.com/kylelobo/The-Documentation-Compendium/issues)
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/Jordi-Jaspers/SPRING-webflux-course.svg)](https://github.com/kylelobo/The-Documentation-Compendium/pulls)
  [![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

### Biography  

**Authors:**  
Jordi Jaspers [[Github](https://github.com/Jordi-Jaspers "Github Page"), [Linkedin](https://www.linkedin.com/in/jordi-jaspers/ "Linkedin Page")]  
  
**Date of initial commit:**  
17/01/2022

**Description:**  
At the core of developing scalable web applications is a thorough knowledge of reactive programming. In this course, Spring
WebFlux: Getting Started, you will learn the foundations of reactive programming and Spring WebFlux. First, you will learn exactly
what reactive programming is and why it's so useful. Then, you will see how to work with Spring WebFlux's annotated controllers and
functional endpoints to process large amounts of data. Finally, you will use WebClient to create reactive web clients, and also set up
integration testing with WebTestClient. When you're finished with this course, you will have a foundational knowledge of reactive
programming with Spring WebFlux that will help you as you move forward to build scalable web applications.
---

## 📝 Table of Contents
- [Application](#application)
- [Built Using](#built_using)
- [References](#references)

## Application <a name = "application"></a>
The application must have the following endpoints:

* `GET /products`  
Should return a list of all products.

* `GET /products/{id}`  
Should return a single product.

* `POST /products`  
Should create a new product.

* `PUT /products/{id}`  
Should update an existing product.

* `DELETE /products/{id}`
Should delete an existing product.

* `DELETE /products`
Should delete all products.

* `GET /products/events`
Should Stream all events in a periodic fashion (1 second).

## ⛏️ Built Using <a name = "built_using"></a>
- [Spring Boot](https://spring.io/projects/spring-boot) - Backend Framework
- [Spring WebFlux](https://spring.io/projects/spring-webflux) - Reactive Framework
- [Git](https://git-scm.com/) - Version Control

## ✍️ References <a name = "references"></a>
* Troubleshooting: <https://stackoverflow.com/>
* Spring Boot: <https://spring.io/projects/spring-boot>
* PluralSight: <https://www.pluralsight.com/>