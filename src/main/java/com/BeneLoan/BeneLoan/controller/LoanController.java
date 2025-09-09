package com.BeneLoan.BeneLoan.controller;


import com.BeneLoan.BeneLoan.entity.Loan;
import com.BeneLoan.BeneLoan.repository.LoanRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanRepo loanRepo;

    public LoanController(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanRepo.findAll());
    }

}
