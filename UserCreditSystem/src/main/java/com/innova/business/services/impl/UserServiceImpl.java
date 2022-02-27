package com.innova.business.services.impl;

import com.innova.business.dto.CreditDto;
import com.innova.business.dto.UserDto;
import com.innova.business.services.UserServices;
import com.innova.data.entity.CreditEntity;
import com.innova.data.entity.UserEntity;
import com.innova.data.enums.CreditSituation;
import com.innova.data.repository.UserRepository;
import com.innova.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditServiceImpl creditServiceImp;

    @Autowired
    private ModelMapper modelMapper;

    //LIST
    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> dtoList = new ArrayList<>();
        Iterable<UserEntity> userEntities = userRepository.findAll();
        for(UserEntity entity : userEntities){
            UserDto userDto = EntityToDto(entity);
            dtoList.add(userDto);
        }
        return dtoList;
    }

    //FIND
    // http://localhost:8080/api/v1/users/find/1
    // I'm gonna use this for react
    @GetMapping("/users/find/{id}")
    @Override
    public UserDto getUserById(@PathVariable Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        UserDto userDto = EntityToDto(user);//model
        return userDto;
    }


    //SAVE
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    @Override
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = DtoToEntity(userDto);
        userRepository.save(userEntity);
        return userDto;
    }

    //CREDIT APPLICATION
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    @Override
    public String checkCredit(@PathVariable Long id) {
         UserEntity userEntity = userRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

         CreditEntity creditEntity = new CreditEntity();
         if(userEntity.getCreditScore() >= 1000){
             int loan = userEntity.getSalary() * 4;
             creditEntity.setUserId(userEntity.getId());
             creditEntity.setIdentityNumber(userEntity.getIdentityNumber());
             creditEntity.setAmount(loan);
             creditEntity.setSituation(CreditSituation.APPROVED);
             CreditDto dto = EntityToDto(creditEntity);
             creditServiceImp.createCredit(dto);
             return CreditSituation.APPROVED + " " + loan;
         }
         else if(userEntity.getCreditScore() >= 500 && userEntity.getCreditScore() < 1000 && userEntity.getSalary() >= 5000){
             creditEntity.setUserId(userEntity.getId());
             creditEntity.setIdentityNumber(userEntity.getIdentityNumber());
             creditEntity.setAmount(20000);
             creditEntity.setSituation(CreditSituation.APPROVED);
             CreditDto dto = EntityToDto(creditEntity);
             creditServiceImp.createCredit(dto);
             return CreditSituation.APPROVED + " 20.000 TL ";
         }
         else if(userEntity.getCreditScore() >= 500 && userEntity.getCreditScore() < 1000 && userEntity.getSalary() < 5000){
             creditEntity.setUserId(userEntity.getId());
             creditEntity.setIdentityNumber(userEntity.getIdentityNumber());
             creditEntity.setAmount(10000);
             creditEntity.setSituation(CreditSituation.APPROVED);
             CreditDto dto = EntityToDto(creditEntity);
             creditServiceImp.createCredit(dto);
             return CreditSituation.APPROVED + " 10.000 TL ";

         }else{
             creditEntity.setUserId(userEntity.getId());
             creditEntity.setIdentityNumber(userEntity.getIdentityNumber());
             creditEntity.setAmount(0);
             creditEntity.setSituation(CreditSituation.DENIED);
             CreditDto dto = EntityToDto(creditEntity);
             creditServiceImp.createCredit(dto);
             return String.valueOf(CreditSituation.DENIED);
         }
    }

    //DELETE
    // http://localhost:8080/api/v1/users/1
    @DeleteMapping("/users/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        userRepository.delete(userEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //UPDATE
    // http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userdetails) {
        UserEntity userEntity = DtoToEntity(userdetails);

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        user.setIdentityNumber(userEntity.getIdentityNumber());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setSalary(userEntity.getSalary());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setCreditScore(userEntity.getCreditScore());

        UserEntity updatedUser = userRepository.save(user);
        UserDto userDto = EntityToDto(updatedUser);
        return ResponseEntity.ok(userDto);
    }

    //MODEL MAPPER
    //Model Mapper Entity ==> Dto
    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    //Model Mapper Dto  ==> Entity
    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

    //Model Mapper Entity ==> Dto
    @Override
    public CreditDto EntityToDto(CreditEntity creditEntity) {
        CreditDto creditDto = modelMapper.map(creditEntity, CreditDto.class);
        return creditDto;
    }
}
