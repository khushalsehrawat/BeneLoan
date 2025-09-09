package com.BeneLoan.BeneLoan.service;


import com.BeneLoan.BeneLoan.dto.LoanRow;
import com.BeneLoan.BeneLoan.entity.Loan;
import com.BeneLoan.BeneLoan.repository.LoanRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanQueryService {

    private final LoanRepo loanRepo;

    public LoanQueryService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    public List<LoanRow> listAll() {
        List<Loan> loans = loanRepo.findAll(); // simple fetch
        return loans.stream().map(l -> {
            String bid = (l.getBeneficiary() != null) ? l.getBeneficiary().getBeneficiaryId() : null;
            String bname = (l.getBeneficiary() != null) ? l.getBeneficiary().getName() : null;
            return new LoanRow(
                    l.getId(),
                    l.getApplicationId(),
                    bid,
                    bname,
                    l.getBankName(),
                    l.getBankType(),
                    l.getAccountNo(),
                    l.getIfscCode(),
                    l.getLoanAmount(),
                    l.getSanctionAmount()
            );
        }).collect(Collectors.toList());
    }

}
