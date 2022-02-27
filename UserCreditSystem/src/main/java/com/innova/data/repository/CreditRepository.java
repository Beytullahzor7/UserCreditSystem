package com.innova.data.repository;

import com.innova.data.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    @Query(value = "select * from credits where user_identity = :identityNumber", nativeQuery = true)
    Optional<CreditEntity> findCreditByIdentity(Long identityNumber);

}
