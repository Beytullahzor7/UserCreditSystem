package com.innova.ui.rest;

import com.innova.business.dto.CreditDto;
import com.innova.business.services.CreditServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CreditRestController {

    @Autowired
    public CreditServices creditServices;

    //LIST
    // http://localhost:8080/api/v1/credits
    @GetMapping("/credits")
    public List<CreditDto> getAllCredits() {
        List<CreditDto> creditDtos = (List<CreditDto>) creditServices.getAllCredits();
        return creditDtos;
    }

    //CREDIT QUERY
    // http://localhost:8080/api/v1/credits/1
    @GetMapping("/credits/{identityNumber}")
    public ResponseEntity<CreditDto> getCreditByIdentityNumber(@PathVariable Long identityNumber){
        ResponseEntity<CreditDto> dto = creditServices.getCreditByIdentityNumber(identityNumber);
        return dto;
    }

    //SAVE
    // http://localhost:8080/api/v1/credits
    @PostMapping("/credits")
    public CreditDto createCredit(@RequestBody CreditDto creditDto) {
        creditServices.createCredit(creditDto);
        return creditDto;
    }













}
