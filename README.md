## **Checkout microservice**

API to checkout the order

`POST http://localhost:9090/checkoutOrder
{
"productId": 1,
"quantity": 1,
"price": 20,
"userName": "amitd"
}`

API to get checked out orders

`GET http://localhost:9090/orders`