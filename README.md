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

Below Request, creates a new Payment in DB : 
POST : localhost:8081/payments/payment 
by proving JSON Payment in body request. 

GET : localhost:8081/payments/allPayments 
This api retrieves all the Payments from DB. 

GET : localhost:8081/payments/payment/{id}
Returns a Payment (by ID) from DB (if it exists, otherwise returns null)

DELETE: localhost:8081/payments/deletePayment/{id}
Deletes a payment from DB (by ID) if exists.
