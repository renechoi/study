package com.example.springbatchprojecthouse.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatchprojecthouse.core.entity.AptNotification;

public interface AptNotificationRepository extends JpaRepository<AptNotification, Long> {
    /**
     * 알림이 켜져있는 알림을 가져온다.
     */
    Page<AptNotification> findByEnabledIsTrue(Pageable pageable);
}
