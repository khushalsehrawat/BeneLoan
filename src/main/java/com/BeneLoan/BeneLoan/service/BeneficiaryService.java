package com.BeneLoan.BeneLoan.service;


import com.BeneLoan.BeneLoan.entity.Beneficiary;
import com.BeneLoan.BeneLoan.repository.BeneficiaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    private final BeneficiaryRepo beneficiaryRepo;

    public BeneficiaryService(BeneficiaryRepo beneficiaryRepo) {
        this.beneficiaryRepo = beneficiaryRepo;
    }

    public boolean existsByBeneficiaryId(String beneficiaryId) {
        return beneficiaryRepo.existsByBeneficiaryId(beneficiaryId);
    }

    public Optional<Beneficiary> findByBeneficiaryId(String beneficiaryId) {
        return beneficiaryRepo.findByBeneficiaryId(beneficiaryId);
    }

    @Transactional
    public Beneficiary save(Beneficiary beneficiary){
        return beneficiaryRepo.save(beneficiary);
    }

}
