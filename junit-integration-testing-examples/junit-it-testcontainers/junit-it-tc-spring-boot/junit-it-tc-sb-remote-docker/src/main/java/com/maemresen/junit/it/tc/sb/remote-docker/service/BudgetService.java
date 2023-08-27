package com.maemresen.tcw.sb.remote.docker.service;

import com.maemresen.tcw.sb.remote.docker.dto.BudgetCreateRequestDto;
import com.maemresen.tcw.sb.remote.docker.dto.BudgetDto;
import com.maemresen.tcw.sb.remote.docker.dto.StatementCreateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface BudgetService {
    Optional<BudgetDto> findById(@NotNull Long mockBudgetId1);

    List<BudgetDto> findAll();

    BudgetDto create(@Valid BudgetCreateRequestDto budgetCreateRequestDto);

    BudgetDto addStatement(@NotNull(message = "Budget Id cannot be null") Long budgetId, @Valid StatementCreateDto statementCreateDto);

    BudgetDto removeStatement(@NotNull(message = "Budget Id cannot be null") Long budgetId, @NotNull(message = "Statement Id cannot be null") Long statementId);
}
