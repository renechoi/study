package org.example.javaconcurrencyproblem.api.infrastructure;

import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 데이터베이스 수준의 동시성 해결 방법은 고려하지 않습니다.
 * @author : Rene Choi
 * @since : 2024/03/19
 */
@Repository
public interface PortfolioRepository extends JpaRepository<StockPortfolio, Long> {
}
