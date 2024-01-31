# Recipe Manager

## Description

A simple recipe manager application.
This application allows users to create, read, update, and delete recipes.
Recipes are stored in memory and are not persisted to a database.

## Technologies Used

- Java
- Maven

## Setup and Installation

To set up and run this project locally, follow the steps below:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.
4. Run `mvn spring-boot:run` to start the application.

## Usage

This application is a REST API that can be used with a tool like Postman.
The following endpoints are available:

- GET /recipes - Returns a list of all recipes based on the search criteria.
- GET /recipes/add - Adds a new recipe to the list of recipes.
- GET /recipes/update - Updates an existing recipe.
- GET /recipes/delete - Deletes an existing recipe.
- GET /recipes/count - Returns the number of recipes in the list of recipes.

## Contributing

If you want to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Create a pull request.

## License

This project is released into the public domain.