package com.maemresen.tcw.sb.remote.docker.repository;

import com.maemresen.tcw.sb.remote.docker.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
}
