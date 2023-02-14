# Intuit-App
Payment System Craft Demonstration Application
This app Manages Payments system, Web based (Maven and Spring boot)
It receives Payments by Rest API calls, it publishes the Payments to Kafka after it Validates all the Fields of a Payment are sent and Valid,
then another app (Risk-Engine) is the consumer for Payments events, where it consumes the Kafka events from the same topic, calculate the risk
of each Payment, and the Risk-Engine accepts 70% of the coming payments, then saves all the payments in (MySql) Database.

The app connects to MySQL and Kafka dockers, to manage all the data.

To run the app, first need to run docker ** docker-compose up -d ** to run build and run the Containers inside the docker-compose file. 
Then need to run both apps (Intuit-App, Risk-engine) as Spring boot projects.

As it's a web based app, I created rest API urls, to manage the Payments system.
The Application is Secured by Spring Security,
**spring.security.user.name = rabia
**spring.security.user.password = rabia
so for each API rest call, need to provide UserName and Password as Basic Authentication,
for example, to create a new Payment: 

**curl --location --request POST 'localhost:8081/payments/payment' \
--header 'Authorization: Basic cmFiaWE6cmFiaWE=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=6643AB29C0C8EF3D5F2DEA4612766880' \
--data-raw '{

"amount": "4", 
"currency": "ILS", 
"userId": "e8af92bd-1910-421e-8de0-cb3dcf9bf44d",
"payeeId": "4c3e304e-ce79-4f53-bb26-4e198e6c780a", 
"paymentMethodId": "8e28af1b-a3a0-43a9-96cc-57d66dd22494" 
}'**


Below Request, creates a new Payment in DB : 
POST : localhost:8081/payments/payment 
by proving JSON Payment in body request (as the example above). 

GET: localhost:8081/payees
Will return the Payees who created all the existing payments in DB. 

GET: localhost:8081/paymentMethods
Will return the Paymet Methods used for the existing payments in DB. 

GET: localhost:8081/payments/paymentMethods 
This API returns all the PaymentMethods used for all the previos payments. 

localhost:8081/payments/payees
This API returns all the Payees created previous payments.

GET : localhost:8081/payments/allPayments 
This api retrieves all the Payments from DB. 

GET : localhost:8081/payments/payment/{id}
Returns a Payment (by ID) from DB (if it exists, otherwise returns null)

DELETE: localhost:8081/payments/deletePayment/{id}
Deletes a payment from DB (by ID) if exists.
