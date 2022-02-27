package com.innova.ui.rest;

import com.innova.business.dto.UserDto;
import com.innova.business.services.UserServices;
import com.innova.business.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserRestController {

    @Autowired
    public UserServices userServices;

    @Autowired
    public UserServiceImpl userServiceImp;

    //LIST
    //http://localhost:8080/api/v1/users
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = (List<UserDto>) userServices.getAllUsers();
        return userDtos;
    }

    //FIND
    // http://localhost:8080/api/v1/users/find/1
    @GetMapping("/users/find/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        UserDto userDto = userServices.getUserById(id);
        return userDto;
    }

    //SAVE
    // http://localhost:8080/api/v1/users
    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        userServices.createUser(userDto);
        return userDto;
    }

    //UPDATE
    // http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDetails){
        userServices.updateUser(id, userDetails);
        return ResponseEntity.ok(userDetails);
    }

    //DELETE
    // http://localhost:8080/api/v1/users/1
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        Map<String , Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //CREDIT APPLICATION
    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    public String checkCredit(@PathVariable Long id) {
        return userServiceImp.checkCredit(id);
    }
}
