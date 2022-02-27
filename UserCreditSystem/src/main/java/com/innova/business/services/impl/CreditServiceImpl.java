package com.innova.business.services.impl;

import com.innova.business.dto.CreditDto;
import com.innova.business.services.CreditServices;
import com.innova.data.entity.CreditEntity;
import com.innova.data.repository.CreditRepository;
import com.innova.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditServices {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ModelMapper modelMapper;


    //LIST
    // http://localhost:8080/api/v1/credits
    @GetMapping("/credits")
    @Override
    public List<CreditDto> getAllCredits() {
        List<CreditDto> dtoList = new ArrayList<>();
        Iterable<CreditEntity> creditEntityList = creditRepository.findAll();
        for(CreditEntity credits : creditEntityList){
            CreditDto dto = EntityToDto(credits);
            dtoList.add(dto);
        }
        return dtoList;
    }

    //SAVE
    // http://localhost:8080/api/v1/credits
    @PostMapping("/credits")
    @Override
    public CreditDto createCredit(@RequestBody CreditDto creditDto) {
        CreditEntity creditEntity = DtoToEntity(creditDto);
        creditRepository.save(creditEntity);
        return creditDto;
    }

    //FIND
    // http://localhost:8080/api/v1/credits/1
    // get credit by identity rest api
    @GetMapping("/credits/{identityNumber}")
    @Override
    public ResponseEntity<CreditDto> getCreditByIdentityNumber(@PathVariable Long identityNumber) {
        CreditEntity creditEntity = creditRepository.findCreditByIdentity(identityNumber)
                .orElseThrow( () -> new ResourceNotFoundException("Credit not exist with id: " + identityNumber));
        CreditDto creditDto = EntityToDto(creditEntity);
        return ResponseEntity.ok(creditDto);
    }

    //MODEL MAPPER
    //Model Mapper Entity ==> Dto
    @Override
    public CreditDto EntityToDto(CreditEntity creditEntity) {
        CreditDto creditDto = modelMapper.map(creditEntity, CreditDto.class);
        return creditDto;
    }

    //Model Mapper Dto  ==> Entity
    @Override
    public CreditEntity DtoToEntity(CreditDto creditDto) {
        CreditEntity creditEntity = modelMapper.map(creditDto, CreditEntity.class);
        return creditEntity;
    }
}
