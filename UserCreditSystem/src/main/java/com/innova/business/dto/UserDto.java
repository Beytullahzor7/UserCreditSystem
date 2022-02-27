package com.innova.business.dto;

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
public class UserDto {

    private Long id;
    private Long identityNumber;
    private String name;
    private String surname;
    private Integer salary;
    private String phoneNumber;
    private Integer creditScore;
}
