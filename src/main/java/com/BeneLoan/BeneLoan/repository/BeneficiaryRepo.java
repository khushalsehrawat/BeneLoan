package com.BeneLoan.BeneLoan.repository;

import com.BeneLoan.BeneLoan.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Long> {

    Optional<Beneficiary> findByBeneficiaryId(String beneficiaryId);

    boolean existsByBeneficiaryId(String beneficiaryId);
}
