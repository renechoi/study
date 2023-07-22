package com.rene.core.domain.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rene.core.domain.entity.Engagement;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
