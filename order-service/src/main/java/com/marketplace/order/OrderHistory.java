package com.marketplace.order;
import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("order_history") record OrderHistory(@Id String id,Long orderId,String event,Instant occurredAt){}
