package com.example.springbatchprojecthouse.core.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatchprojecthouse.core.entity.Apt;

public interface AptRepository extends JpaRepository<Apt, Long> {

    /**
     * api 에서 제공한 unique 속성을 사용한 것은 아니나
     * 데이터를 분석하여 최대한 unique하도록 가져오도록 쿼리 작성
     */
    Optional<Apt> findAptByAptNameAndJibun(String aptName, String jibun);
}
