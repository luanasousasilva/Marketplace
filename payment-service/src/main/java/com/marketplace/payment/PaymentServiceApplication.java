package com.marketplace.payment;
import org.springframework.boot.*; import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication @EnableCaching public class PaymentServiceApplication { public static void main(String[] args){SpringApplication.run(PaymentServiceApplication.class,args);} }
