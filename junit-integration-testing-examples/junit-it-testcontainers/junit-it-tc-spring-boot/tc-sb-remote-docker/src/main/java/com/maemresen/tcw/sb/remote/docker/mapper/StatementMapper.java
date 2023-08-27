package com.maemresen.tcw.sb.remote.docker.mapper;

import com.maemresen.tcw.sb.remote.docker.dto.StatementCreateDto;
import com.maemresen.tcw.sb.remote.docker.dto.StatementDto;
import com.maemresen.tcw.sb.remote.docker.entity.StatementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatementMapper {
    StatementDto mapToStatementDto(StatementEntity statementEntity);

    StatementEntity mapToStatementEntity(StatementCreateDto statementCreateDto);
}
