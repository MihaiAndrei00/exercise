* 1- clone the project in a folder: git clone https://github.com/MihaiAndrei00/exercise.git

* 2- To start the server execute mvn spring-boot:run on the root of the project

* 3- the h2 database Url is http://localhost:8080/h2-console/login.jsp and acording to the properties the Driver Class should be:  org.h2.Driver , JDBC URL: jdbc:h2:mem:testdb, User Name: admin , Password: 1234

* 4- The commands to execute to test the endpoints on console or postman are:

PRODUCT CONTROLLER

 -CREATE PRODUCT ENDPOINT TEST

curl -X POST "http://localhost:8080/api/products/create-product" \
     -H "Content-Type: application/json" \
     -d '{
           "name": "New Product",
           "description": "Description of the new product",
           "price": 19.99
         }'

 -UPDATE PRODUCT ENDPOINT TEST

curl -X PUT "http://localhost:8080/api/products/update-product/1" \
     -H "Content-Type: application/json" \
     -d '{
           "name": "Updated Product Name",
           "description": "Updated description",
           "price": 29.99
         }'

 -GET PRODUCT BY ID TEST

curl -X GET http://localhost:8080/api/products/get-product-by-id/1

-GET ALL PRODUCTS TEST

curl -X GET "http://localhost:8080/api/products/get-all-products"

-DELETE A PRODUCT TEST

curl -X DELETE http://localhost:8080/api/products/delete-product/1

USER CONTROLLER

-CREATE USER

curl -X POST "http://localhost:8080/api/users/create-user" \
     -H "Content-Type: application/json" \
     -d '{
           "username": "newuser",
           "email": "newuser@example.com"
         }'


-GET USER BY ID

curl -X GET http://localhost:8080/api/users/get-user-by-id/1

-DELETE USER BY ID

curl -X DELETE http://localhost:8080/api/users/delete-user-by-id/1


CART CONTROLLER

-GET CART BY USER ID

curl -X GET "http://localhost:8080/api/cart/1"

-ADD ITEM TO CART

curl -X POST "http://localhost:8080/api/cart/add-item" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "userId=1&productId=1&quantity=2"

-DELETE AN ITEM FORM A CART

curl -X DELETE "http://localhost:8080/api/cart/remove-item/1/1"

-CLEAR CART

curl -X DELETE "http://localhost:8080/api/cart/clear-cart/1"

