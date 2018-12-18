# Designing Event Driven Services with Spring Cloud Stream and RabbitMQ

## Introduction :

Spring Cloud Stream is a framework built on top of Spring Boot and Spring Integration that helps in creating event-driven microservices at scale.
In this application I introduce an order processing application using the capability of Spring Cloud Stream , following the  event driven micoservice architecture. 
## Overview of the application :
It consists of four independent microservices. 
**gateway-service**,
**order-service**,
**account-service** and 
**product-service**

Communication between **order-service** and the **account-service** happens through orders-in and orders-out exchanges . The default topic exchange in spring cloud stream has been overidden with direct exchange to implement a point to point communication . 

Call to the Order-service from an external client comes through the API gateway .Order service receives the order and saves it in the repository and sends the order details message to the broker. Account-service receives the order , validates the customer id and calls the product-service over the rest endpoint and retrieves the product ids . If customer has sufficient balance to place the order it sets the order to ACCEPTED else sets the status to REJECTED and send this order status to the broker . Order-service receives the order status from the message and updates the status to the client when requested over the endpoint. Following diagram shows the architecture of the application.


![order service](https://user-images.githubusercontent.com/9249786/50053873-f11a5100-0161-11e9-80e7-af6bb3b96976.png)



##### For the newbies to Spring Cloud stream , Spring Boot and AMQP :

###### RabbitMQ with AMQP: https://docs.google.com/presentation/d/1ruNrcb6T2j_1IAjbBMst4Tbh6fENCO6nM566JJz9Yw4/edit?usp=sharing
###### Spring Cloud Stream: https://docs.google.com/presentation/d/1VWO8s_1OvAieITS4W-DajNMu6eZFGn4UmwuS1FVivQQ/edit?usp=sharing
###### Spring Boot: http://www.slideshare.net/roynilanjan/spring-boot-for-buidling-microservices
###### Spring Cloud Data Flow: https://www.slideshare.net/roynilanjan/building-cloud-native-data-microservice
