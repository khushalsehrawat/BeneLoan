package com.BeneLoan.BeneLoan.controller;


import com.BeneLoan.BeneLoan.entity.Beneficiary;
import com.BeneLoan.BeneLoan.repository.BeneficiaryRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class BeneficiaryController {

    private final BeneficiaryRepo beneficiaryRepo;

    public BeneficiaryController(BeneficiaryRepo beneficiaryRepo) {
        this.beneficiaryRepo = beneficiaryRepo;
    }

    @GetMapping
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaries() {
        return ResponseEntity.ok(beneficiaryRepo.findAll());
    }

    @GetMapping("/beneficiaries")
    public List<Beneficiary> listAll(){
        return beneficiaryRepo.findAll();
    }

}
