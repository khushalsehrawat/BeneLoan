package com.BeneLoan.BeneLoan.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id", nullable = false)
    private String applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id_ref", referencedColumnName = "beneficiary_id")
    private Beneficiary beneficiary;

    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_type")
    private String bankType;
    @Column(name = "account_no")
    private String accountNo;
    @Column(name = "ifsc_code")
    private String ifscCode;
    @Column(name = "loan_amount")
    private BigDecimal loanAmount;
    @Column(name = "sanction_amount")
    private BigDecimal sanctionAmount;


    public Loan(){}

    public Loan(Long id, String applicationId, Beneficiary beneficiary, String bankName, String bankType, String accountNo, String ifscCode, BigDecimal loanAmount, BigDecimal sanctionAmount) {
        this.id = id;
        this.applicationId = applicationId;
        this.beneficiary = beneficiary;
        this.bankName = bankName;
        this.bankType = bankType;
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;
        this.loanAmount = loanAmount;
        this.sanctionAmount = sanctionAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getSanctionAmount() {
        return sanctionAmount;
    }

    public void setSanctionAmount(BigDecimal sanctionAmount) {
        this.sanctionAmount = sanctionAmount;
    }
}
