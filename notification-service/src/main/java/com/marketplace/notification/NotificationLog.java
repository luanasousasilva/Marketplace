package com.marketplace.notification;
import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document; import java.time.Instant;
@Document("notification_logs") record NotificationLog(@Id String id,Long notificationId,String event,String detail,Instant occurredAt){}
