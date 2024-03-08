# Microservices with Kafka and Saga pattern using Spring Boot
This project demonstrates a system for processing orders using microservices architecture, Kafka messaging, and the Saga pattern. It consists of four microservices: order, stock, payment, and notification service.
## High level architecture:
<p align="center">
<img src="https://raw.githubusercontent.com/moizmhb/kafka--microservices/master/resources/readme-images/architecture.png" alt="" width="50%"/>
</p>


## Getting Started / Installation

### Option 1: Running locally
1. Clone this repository.
2. Navigate to the "resources/docker files/Run project locally" directory.
3. Run the Docker Compose file (docker-compose up). If mongo-express fails to initialize, rerun the command.
4. Start all microservices (the order of initialization doesn't matter).

### Option 2: Running on Docker
1. Execute the Docker Compose file located in the "resources/docker files/Run project on docker" directory (docker-compose up). If mongo-express fails to initialize, rerun the command.


## Instructions:

1. Submit orders through the order service's endpoint /api/v1/order. Specify the number of orders and items in each order using query parameters o for orders and i for items.
Example: localhost:8080/api/v1/order?o=2&i=3 will send 2 orders, each containing 3 items.
2. Check the final status of each order through the notification service's console or inspect each topic through Kafka topics (localhost:9000/).
3. Adjust the expected order result (success/failure) and other configurations in shared.properties inside the common module.


## How it works:
Upon receiving a new order, the order service initiates processing and sends an event to Kafka:
<p align="center">
<img src="https://raw.githubusercontent.com/moizmhb/kafka--microservices/master/resources/readme-images/step1.png" alt="" width="50%"/>
</p>
All microservices involved perform their respective operations and report back to Kafka with confirmation (success/failure).
<p align="center">
<img src="https://raw.githubusercontent.com/moizmhb/kafka--microservices/master/resources/readme-images/step2.png" alt="" width="50%"/>
</p>
The order service uses Kafka Streams to aggregate all confirmations received. If all services report success, the order is considered complete. If any service reports failure, the order service triggers a rollback event processed by all other services.
Order service also sends the final order status to Kafka, notification service simulates then a notification message to user informing the final status of his/her order:
<p align="center">
<img src="https://raw.githubusercontent.com/moizmhb/kafka--microservices/main/resources/readme-images/step3.png" alt="" width="50%"/>
</p>

## Configurations:
- When running locally, microservice configurations can be modified through shared.application located at common/src/resources/. Relevant configurations are explained in this file.