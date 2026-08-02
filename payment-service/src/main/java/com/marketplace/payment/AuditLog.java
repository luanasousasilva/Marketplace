package com.marketplace.payment;
import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("payment_audit") record AuditLog(@Id String id,Long paymentId,String action,Instant timestamp){}
