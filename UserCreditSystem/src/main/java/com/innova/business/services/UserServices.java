package com.innova.business.services;

import com.innova.business.dto.CreditDto;
import com.innova.business.dto.UserDto;
import com.innova.data.entity.CreditEntity;
import com.innova.data.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserServices {

    //CRUD
    public List<UserDto> getAllUsers();
    public UserDto createUser(UserDto userDto);
    public UserDto getUserById(Long id);
    public String checkCredit(Long id);
    public ResponseEntity<UserDto> updateUser(Long id, UserDto employeeDto);
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id);


    //Model Mapper
    public UserDto EntityToDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);
    public CreditDto EntityToDto(CreditEntity creditEntity);

}
