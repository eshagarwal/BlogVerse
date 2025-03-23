# BlogVerse

## Overview
BlogVerse allows users to create, read, update, and delete blogs, manage categories, and add comments. It supports features like retrieving the most recent blogs, filtering blogs by category and date range, and managing blogs by authors.

## Features

- **Create, Update, Delete Blogs**: Users can create, update, or delete blogs.
- **Manage Comments**: Users can add, view comments on blogs.
- **Get Blogs by Category**: Get blogs in a specific category.
- **Get Blogs by Author**: Get blogs written by a specific author.
- **Get Recent Blogs**: Get the most recent blogs.
- **Date Range Filtering**: Get blogs created within a specific date range.
- **Most Commented Blogs**: Get blogs with the highest number of comments.

## Endpoints

<img width="678" alt="Screenshot 2025-03-23 at 5 44 15 PM" src="https://github.com/user-attachments/assets/b79111fb-f75f-409a-8de3-b7e8a9042254" />


## Setup and Installation

### Prerequisites

- Java 11 or above
- Spring Boot 2.x
- MongoDB

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/eshagarwal/BlogVerse.git
   
2. Navigate to the project directory:

    ```bash
    cd BlogVerse

3. Update application properties (`src/main/resources/application.properties`):

    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/blogverse
    ```

4. Build the project:

    ```bash
    mvn clean install
   
5. Run the application:

    ```bash
    mvn spring-boot:run
   
6. The application will be accessible at `http://localhost:8080`.

## Technologies Used

- Java
- Spring Boot
- MongoDB
- Postman
- Git
- IntelliJ IDEA

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Spring Boot Documentation
- MongoDB Documentation
- University instructors and classmates
