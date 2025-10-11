package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
