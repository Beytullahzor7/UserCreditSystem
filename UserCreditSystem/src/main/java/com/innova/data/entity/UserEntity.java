package com.innova.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntityAudit implements Serializable {

    @Column(name = "identity_number" ,nullable = false, unique = true)
    private Long identityNumber;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "surname" ,nullable = false)
    private String surname;

    @Column(name = "salary" ,nullable = false)
    @Min(0)
    private Integer salary;

    @Column(name = "phone_number" ,nullable = false)
    private String phoneNumber;

    @Column(name = "credit_score" ,nullable = false)
    @Min(0)
    private Integer creditScore;

    public UserEntity(Long identityNumber, String name, String surname, Integer salary, String phoneNumber, Integer creditScore) {
        super();
        this.identityNumber = identityNumber;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.creditScore = creditScore;
    }
}
