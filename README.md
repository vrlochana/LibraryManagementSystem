# Library Management System (Java Console Application)

## Overview

A console-based Library Management System built using Java to manage books, members, issuing, returns, overdue fine calculation, and persistent storage using text files.

## Features

* Add books
* Search books
* Register members
* Issue books
* Return books
* Calculate overdue fines
* Track availability
* Persist data using file storage

## Technologies Used

* Java
* Object-Oriented Programming
* Collections (`ArrayList`, `HashMap`)
* Exception Handling
* File Handling
* Java Time API (`LocalDate`)
* Git & GitHub

## Project Structure

src/
├── Main.java
├── model/
├── service/
├── storage/
├── exception/
└── util/

data/
├── books.txt
└── members.txt

## How to Run

Compile:

javac -d out src/**/*.java src/*.java

Run:

java -cp out Main

## Sample Flow

1. Add Book
2. Register Member
3. Issue Book
4. Return Book
5. Save and Exit

## Future Improvements

* GUI version
* Database integration
* Login system
* Reports and analytics
* Multiple librarian support
