package com.marketplace.notification;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="notifications") class Notification { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String recipient; @Column(nullable=false) String channel; @Column(nullable=false,length=4000) String message; @Column(nullable=false) String status="PENDING"; @Column(nullable=false) Instant createdAt=Instant.now(); protected Notification(){} Notification(String r,String c,String m){recipient=r;channel=c;message=m;} }
