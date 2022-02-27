package com.innova.data.entity;


import com.innova.data.enums.CreditSituation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Log4j2

@Entity
@Table(name = "credits")
public class CreditEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id" ,nullable = false, updatable = false)
    private Long creditId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_identity", nullable = false)
    private Long identityNumber;

    @Column(nullable = false)
    private Integer amount; //Credit Limit

    private CreditSituation situation;

    public CreditEntity(Long userId, Long identityNumber, Integer amount, CreditSituation situation) {
        this.userId = userId;
        this.identityNumber = identityNumber;
        this.amount = amount;
        this.situation = situation;
    }
}
