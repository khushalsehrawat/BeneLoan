package com.BeneLoan.BeneLoan.dto;

import java.math.BigDecimal;

public class LoanRow {

    private Long id;
    private String application_id;
    private String beneficiary_id;
    private String beneficiary_name;
    private String bank_name;
    private String bank_type;
    private String account_no;
    private String ifsc_code;
    private BigDecimal loan_amount;
    private BigDecimal sanction_amount;

    public LoanRow() {}

    public LoanRow(Long id, String application_id, String beneficiary_id, String beneficiary_name, String bank_name, String bank_type, String account_no, String ifsc_code, BigDecimal loan_amount, BigDecimal sanction_amount) {
        this.id = id;
        this.application_id = application_id;
        this.beneficiary_id = beneficiary_id;
        this.beneficiary_name = beneficiary_name;
        this.bank_name = bank_name;
        this.bank_type = bank_type;
        this.account_no = account_no;
        this.ifsc_code = ifsc_code;
        this.loan_amount = loan_amount;
        this.sanction_amount = sanction_amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public String getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(String beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public String getBeneficiary_name() {
        return beneficiary_name;
    }

    public void setBeneficiary_name(String beneficiary_name) {
        this.beneficiary_name = beneficiary_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public BigDecimal getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(BigDecimal loan_amount) {
        this.loan_amount = loan_amount;
    }

    public BigDecimal getSanction_amount() {
        return sanction_amount;
    }

    public void setSanction_amount(BigDecimal sanction_amount) {
        this.sanction_amount = sanction_amount;
    }
}
