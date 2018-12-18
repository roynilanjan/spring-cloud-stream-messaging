# Designing Event Driven Services with Spring Cloud Stream and RabbitMQ

## Overview :

Spring Cloud Stream is a framework built on top of Spring Boot and Spring Integration that helps in creating event-driven microservices at scale.
In this application I introduce an order processing application using the capability of Spring Cloud Stream , following the  event driven micoservice architecture. It consists of four independent microservices. The order-service microservice first communicates with product-service in order to collect the details of the selected products, and then with customer-service to retrieve information about the customer and his accounts. The orders sent to order-service will be processed asynchronously.There is still an exposed RESTful HTTP API endpoint for submitting new orders by the clients, but they are not processed by the application. The order-service saves new orders,
sends it to a message broker, and then responds to the client that the order has been approved for processing. The main goal of the currently discussed example is to show a point-to-point communication. Here messages would be received by only one application, account-service. The following diagram that illustrates the sample system architecture:


![order service](https://user-images.githubusercontent.com/9249786/50053873-f11a5100-0161-11e9-80e7-af6bb3b96976.png)



##### For the newbies to Spring Cloud stream , Spring Boot and AMQP :

###### RabbitMQ with AMQP: https://docs.google.com/presentation/d/1ruNrcb6T2j_1IAjbBMst4Tbh6fENCO6nM566JJz9Yw4/edit?usp=sharing
###### Spring Cloud Stream: https://docs.google.com/presentation/d/1VWO8s_1OvAieITS4W-DajNMu6eZFGn4UmwuS1FVivQQ/edit?usp=sharing
###### Spring Boot: http://www.slideshare.net/roynilanjan/spring-boot-for-buidling-microservices
###### Spring Cloud Data Flow: https://www.slideshare.net/roynilanjan/building-cloud-native-data-microservice
