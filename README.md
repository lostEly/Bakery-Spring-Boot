# Bakery-Spring-Boot
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
