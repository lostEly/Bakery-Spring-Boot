# Bakery-Spring-Boot
To know what is needed request body -> watch controller parameters & appropriate DTO file
Or you can import bakery.postman_collection.json to postman where you can get all examples.
### In this application you can:
* get the list of available products and search any product in database without an authorization.
* to become user you have to register and confirm your email, after that you'll be able to get authorization token to the front-end and make orders.
* add products to a product basket and remove them. Items from the basket should be stored in front-end.
* make order by sending info about added products and the total cost.
* register admin.

### With admin role you can:
* edit and add products.
* export users info to excel file.
* edit order's status for user.

### Available requests for everyone:
* GetAllProduct **Get** http://localhost:8080/index/
* GetProductByName **Get** http://localhost:8080/index/{name}
* Register **Post** http://localhost:8080/register
* Authenticate **Post** http://localhost:8080/auth  

### Available requests for user:
* AddProductToBasket **Post** http://localhost:8080/user/
* DeleteProductFromBasket **Delete** http://localhost:8080/user/cart  
* MakeOrder **Put** http://localhost:8080/user/cart

### Available requests for admin:
* GetProductToEdit **Get** http://localhost:8080/admin/edit-product/{productId}
* EditProduct **Put** http://localhost:8080/admin/edit-product/{productId}
* AddProduct **Post** http://localhost:8080/admin/add-product
* ExportUsersToExcel **Get** http://localhost:8080/admin/users/export/excel
* getUserInfo **Get** http://localhost:8080/admin/user-info
* editOrderStatus **Put** http://localhost:8080/admin/user-info
