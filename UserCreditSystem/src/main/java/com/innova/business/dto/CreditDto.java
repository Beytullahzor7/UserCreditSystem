package com.innova.business.dto;

import com.innova.data.enums.CreditSituation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class CreditDto {

    private Long creditId;
    private Long userId;
    private Long identityNumber;
    private Integer amount;
    private CreditSituation situation;
}
