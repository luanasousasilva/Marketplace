package com.marketplace.productservice;
import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("product_reviews") record Review(@Id String id,Long productId,Long customerId,int rating,String comment,Instant createdAt){}
