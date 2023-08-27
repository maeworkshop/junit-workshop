package com.maemresen.tcw.sb.remote.docker.dto;

import com.maemresen.tcw.sb.remote.docker.dto.base.BaseDatedDto;
import com.maemresen.tcw.sb.remote.docker.entity.enums.Currency;
import com.maemresen.tcw.sb.remote.docker.entity.enums.StatementType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class StatementDto extends BaseDatedDto {
    private String description;
    private Double amount;
    private StatementType type;
    private Currency currency;
    private LocalDateTime date;
    private String category;
}
