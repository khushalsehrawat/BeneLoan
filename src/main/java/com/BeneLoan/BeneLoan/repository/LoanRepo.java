package com.BeneLoan.BeneLoan.repository;

import com.BeneLoan.BeneLoan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Long> {
}
