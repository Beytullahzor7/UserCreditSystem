package com.innova.unit;

import com.innova.data.entity.UserEntity;
import com.innova.data.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class _1_TestUserEntity {

    @Autowired
    private UserRepository userRepository;
    private UserEntity userTest;

    @BeforeEach
    void setUp(){
        userTest = new UserEntity();
    }

    //CREATE
    @Test
    void createTest(){
        userTest.setIdentityNumber(20L);
        userTest.setName("Gizem");
        userTest.setSurname("Aydın");
        userTest.setSalary(5000);
        userTest.setPhoneNumber("0560");
        userTest.setCreditScore(550);
        userRepository.save(userTest);
        Assertions.assertNotNull(userRepository.findById(11L).get());
    }

    //LIST
    @Test
    void listTest(){
        List<UserEntity> myList = userRepository.findAll();
        assertThat(myList).size().isGreaterThan(0);
    }

    //FIND
    @Test
    void findTest(){
        UserEntity find = userRepository.findById(11L).get();
        assertEquals("Gizem",find.getName());
    }

    //UPDATE
    @Test
    void updateTest(){
        UserEntity update = userRepository.findById(11L).get();
        update.setSurname("Aydın updated");
        userRepository.save(update);
        assertNotEquals("Old Surname", update.getSurname());
    }

    //DELETE
    @Test
    void deleteTest(){
        userRepository.deleteById(11L);
        assertThat(userRepository.existsById(11L)).isFalse();
    }
}
