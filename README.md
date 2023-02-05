# Intuit-App
Payment System Craft Demonstration Application
This app Manages Payments system, Web based (Maven and Spring boot)
It receives Payments by Rest API calls, it publishes the Payments to Kafka after it Validates all the Fields of a Payment are sent and Valid,
then another app (Risk-Engine) is the consumer for Payments events, where it consumes the Kafka events from the same topic, calculate the risk
of each Payment, and the Risk-Engine accepts 70% of the coming payments, then saves all the payments in (MySql) Database.

The app connects to MySQL and Kafka dockers, to manage all the data.
