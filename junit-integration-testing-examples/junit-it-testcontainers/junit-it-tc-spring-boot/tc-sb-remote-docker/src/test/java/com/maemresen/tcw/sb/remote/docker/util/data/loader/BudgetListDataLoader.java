package com.maemresen.tcw.sb.remote.docker.util.data.loader;


import com.fasterxml.jackson.core.type.TypeReference;
import com.maemresen.tcw.sb.remote.docker.base.BaseAbstractDataLoader;
import com.maemresen.tcw.sb.remote.docker.entity.BudgetEntity;
import com.maemresen.tcw.sb.remote.docker.repository.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class BudgetListDataLoader extends BaseAbstractDataLoader<List<BudgetEntity>, List<BudgetEntity>> {

    @Override
    protected TypeReference<List<BudgetEntity>> getTypeReference() {
        return new TypeReference<>() {
        };
    }

    @Transactional
    @Override
    public List<BudgetEntity> loadData(ApplicationContext applicationContext, List<BudgetEntity> data) {
        var budgetRepository = applicationContext.getBean(BudgetRepository.class);
        return data.stream().map(budget -> {
            budget.getStatements().forEach(statement -> statement.setBudget(budget));
            return budgetRepository.save(budget);
        }).toList();
    }
}
