package com.BeneLoan.BeneLoan.service;


import com.BeneLoan.BeneLoan.entity.Loan;
import com.BeneLoan.BeneLoan.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    public LoanService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Transactional
    public Loan save(Loan loan){
        return loanRepo.save(loan);
    }

}
