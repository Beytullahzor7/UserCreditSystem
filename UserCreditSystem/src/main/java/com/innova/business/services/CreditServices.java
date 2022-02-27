package com.innova.business.services;

import com.innova.business.dto.CreditDto;
import com.innova.data.entity.CreditEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreditServices {

    public List<CreditDto> getAllCredits();
    public CreditDto createCredit(CreditDto creditDto);
    public ResponseEntity<CreditDto> getCreditByIdentityNumber(Long identityNumber);


    //Model Mapper
    public CreditDto EntityToDto(CreditEntity creditEntity);
    public CreditEntity DtoToEntity(CreditDto creditDto);
}
