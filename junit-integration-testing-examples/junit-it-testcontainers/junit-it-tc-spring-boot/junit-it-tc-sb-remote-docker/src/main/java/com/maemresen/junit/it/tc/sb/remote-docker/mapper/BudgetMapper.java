package com.maemresen.tcw.sb.remote.docker.mapper;

import com.maemresen.tcw.sb.remote.docker.dto.BudgetCreateRequestDto;
import com.maemresen.tcw.sb.remote.docker.dto.BudgetDto;
import com.maemresen.tcw.sb.remote.docker.entity.BudgetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StatementMapper.class})
public interface BudgetMapper {
    BudgetDto mapToBudgetDto(BudgetEntity budgetEntity);

    BudgetEntity mapToBudgetEntity(BudgetCreateRequestDto budgetCreateRequestDto);
}
