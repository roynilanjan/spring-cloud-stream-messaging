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

Communication between **order-service** the **account-service** happens through orders-in and orders-out exchanges . The default topic exchange in spring cloud stream has been overidden with direct exchange to implement a point to point communication . 



in order to get the details of the customer and his acount balance. Upon receiving the message,account-service calls the  **product-service** calls the method exposed by product-service in order to find out its price. Upon receiving the product price details account-service withdraws money from the customer account and then sends back the response to order-service with the current order status. through the message broker. The order-service saves new orders,sends it to a message broker, and then responds to the client that the order has been approved for processing. The main goal of the currently discussed example is to show a point-to-point communication. Here messages would be received by only one application, account-service. The following diagram that illustrates the sample system architecture:


![order service](https://user-images.githubusercontent.com/9249786/50053873-f11a5100-0161-11e9-80e7-af6bb3b96976.png)



##### For the newbies to Spring Cloud stream , Spring Boot and AMQP :

###### RabbitMQ with AMQP: https://docs.google.com/presentation/d/1ruNrcb6T2j_1IAjbBMst4Tbh6fENCO6nM566JJz9Yw4/edit?usp=sharing
###### Spring Cloud Stream: https://docs.google.com/presentation/d/1VWO8s_1OvAieITS4W-DajNMu6eZFGn4UmwuS1FVivQQ/edit?usp=sharing
###### Spring Boot: http://www.slideshare.net/roynilanjan/spring-boot-for-buidling-microservices
###### Spring Cloud Data Flow: https://www.slideshare.net/roynilanjan/building-cloud-native-data-microservice
