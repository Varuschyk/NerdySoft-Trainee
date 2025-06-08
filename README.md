## NerdySoft-Trainee Application

## Table of Contents

---
- [Swagger](#swagger)
- [Spring profiles](#spring-profiles)

## Description 
The tech test is a programming exercise to evaluate the candidate's 
technical skills and approach to software development. 
The candidate is expected to produce a solution in code that demonstrates 
they understand how to solve the problem using clear concise code.

When completing the technical test please do:
Include instructions on how to run the code if needed
Keep it simple
Push your code to a PUBLIC github repo and share the link

**!!! Notion:**
Please be patient with the requirements and validate that everything is implemented due to it.

**Objective**: Develop a REST service for library management using Java Spring Boot, JPA, Spring Validator, H2/Postgres database, JUnit and Mockito.

**Requirements**:

**Books**: 
The system should allow the creation, reading, updating, and deleting of books.
A book should have the following attributes: ID, title, author, and amount.
If a book is added with the same name and author that already exists in the database, the amount of the existing book should increase by 1.
If either the name or the author is different, it should be considered a different book.
A book can not be deleted if at least one of it is borrowed.

**Members**:
The system should allow the creation, reading, updating, and deleting of library members.
A member should have the following attributes: ID, name, and membership date (automatically set a date of member creation).
A member can borrow many books but not more than 10. The limit amount should come from the env variable or property file.
After a member borrows a book, the amount of this book should be decreased by 1.
If the book amount is 0, the book can not be borrowed.
After a member returns a book, the amount of this book should be increased by 1.
Member can not be deleted if it has borrowed books.

**Validations**:

**Book validation**:
name - required, should start with a capital letter,
min length - 3 symbols author - required,
should contain two capital words with name and surname and space between. Example: Paulo Coelho.

**User validation:**

name - required


**Also, implement endpoints:**


1. Retrieve all books borrowed by a specific member by name. 
2. Retrieve all borrowed distinct book names. 
3. Retrieve all borrowed distinct book names and amount how much copy of this book was borrowed.

**Additional requirements**: 
1. Write unit tests for services. 
2. Add swagger. 

## Swagger

Swagger available on URL: http://localhost:8080/swagger-ui/index.html

## Spring Profiles

If you want to run application using other environment use command: `-Dspring.profiles.active={profileName}` in `Run configuration`.

At current project available only the `dev` profile.


