
## Product CRUD API

This is a simple RESTful API for managing products. 
It allows performing CRUD (Create, Read, Update, Delete) operations on product entities.

## Features
- List Products: Get a list of all products stored in the database.
- View Product by ID: Retrieve a specific product by its ID.
- Create Product: Create a new product with the provided information.
- Update Product: Update the information of an existing product.
- Delete Product: Delete a product by its ID.
  
## Technologies Used
- Java
- Spring Boot
- Maven
- Hibernate
- H2 Database (for testing)

## Endpoints
- GET /api/v1/products: Get all products.
- GET /api/v1/products/{id}: Get a product by its ID.
- POST /api/v1/products: Create a new product.
- PUT /api/v1/products/{id}: Update an existing product by its ID.
- DELETE /api/v1/products/{id}: Delete a product by its ID.
