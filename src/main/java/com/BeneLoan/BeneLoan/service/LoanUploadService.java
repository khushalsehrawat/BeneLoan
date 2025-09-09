package com.BeneLoan.BeneLoan.service;


import com.BeneLoan.BeneLoan.dto.RowError;
import com.BeneLoan.BeneLoan.entity.Beneficiary;
import com.BeneLoan.BeneLoan.entity.Loan;
import com.BeneLoan.BeneLoan.exception.BadRequestException;
import com.BeneLoan.BeneLoan.utils.CsvUtils;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoanUploadService {


    private final LoanService loanService;
    private final BeneficiaryService beneficiaryService;

    public LoanUploadService(LoanService loanService, BeneficiaryService beneficiaryService) {
        this.loanService = loanService;
        this.beneficiaryService = beneficiaryService;
    }

    private static final String IFSC_REGEX = "^[A-Z]{4}0[A-Z0-9]{6}$";


    @Transactional
    public Map<String, Object> upload(MultipartFile file) throws Exception{
        String[] headers = {
                "application_id","beneficiary_id","bank_name","bank_type",
                "account_no","ifsc_code","loan_amount","sanction_amount"
        };
        List<RowError> errors = new ArrayList<>();
        List<Loan> toSave = new ArrayList<>();

        try(CSVParser parser = CsvUtils.parse(file.getInputStream(), headers))
        {
            int rowNum=1;
            for(CSVRecord rec : parser){
                rowNum++;

                String applicationId = get(rec, "application_id");
                String beneficiaryId = get(rec, "beneficiary_id");
                String bankName = get(rec, "bank_name");
                String bankType = get(rec, "bank_type");
                String accountNo = get(rec, "account_no");
                String ifsc = get(rec, "ifsc_code");
                String loanAmountStr = get(rec, "loan_amount");
                String sanctionAmountStr = get(rec, "sanction_amount");

                boolean rowHasError = false;

                //checking requirments
                if (applicationId.isBlank()) { errors.add(new RowError(rowNum, "application_id is required")); rowHasError = true; }
                if (beneficiaryId.isBlank()) { errors.add(new RowError(rowNum, "beneficiary_id is required")); rowHasError = true; }
                if (accountNo.isBlank())     { errors.add(new RowError(rowNum, "account_no is required")); rowHasError = true; }
                if (ifsc.isBlank())          { errors.add(new RowError(rowNum, "ifsc_code is required")); rowHasError = true; }
                if (loanAmountStr.isBlank()) { errors.add(new RowError(rowNum, "loan_amount is required")); rowHasError = true; }
                if (sanctionAmountStr.isBlank()) { errors.add(new RowError(rowNum, "sanction_amount is required")); rowHasError = true; }


                // Beneficiary existence
                Beneficiary beneficiary = null;
                if(!beneficiaryId.isBlank()){
                    var opt = beneficiaryService.findByBeneficiaryId(beneficiaryId);
                    if (opt.isEmpty()){
                        errors.add(new RowError(rowNum, "Beneficiary ID '" + beneficiaryId + "' does not exist"));
                        rowHasError = true;
                    } else {
                        beneficiary = opt.get();
                    }
                }

                // IFSC regex
                if (!ifsc.isBlank() && !ifsc.matches(IFSC_REGEX)) {
                    errors.add(new RowError(rowNum, "Invalid IFSC code: " + ifsc));
                    rowHasError = true;
                }

                // Account no alphanumeric
                if (!accountNo.isBlank() && !accountNo.matches("^[a-zA-Z0-9]+$")) {
                    errors.add(new RowError(rowNum, "account_no must be alphanumeric"));
                    rowHasError = true;
                }


                // Amounts
                BigDecimal loanAmount = null, sanctionAmount = null;
                try { if (!loanAmountStr.isBlank()) loanAmount = new BigDecimal(loanAmountStr); }
                catch (NumberFormatException e) { errors.add(new RowError(rowNum, "loan_amount must be numeric")); rowHasError = true; }

                try { if (!sanctionAmountStr.isBlank()) sanctionAmount = new BigDecimal(sanctionAmountStr); }
                catch (NumberFormatException e) { errors.add(new RowError(rowNum, "sanction_amount must be numeric")); rowHasError = true; }

                if (loanAmount != null && loanAmount.compareTo(BigDecimal.ZERO) < 0) {
                    errors.add(new RowError(rowNum, "loan_amount must be >= 0")); rowHasError = true;
                }
                if (sanctionAmount != null && sanctionAmount.compareTo(BigDecimal.ZERO) < 0) {
                    errors.add(new RowError(rowNum, "sanction_amount must be >= 0")); rowHasError = true;
                }
                if (loanAmount != null && sanctionAmount != null && sanctionAmount.compareTo(loanAmount) > 0) {
                    errors.add(new RowError(rowNum, "sanction_amount cannot be greater than loan_amount")); rowHasError = true;
                }

                if (!rowHasError) {
                    Loan loan = new Loan();
                    loan.setApplicationId(applicationId);
                    loan.setBeneficiary(beneficiary);
                    loan.setBankName(bankName);
                    loan.setBankType(bankType);
                    loan.setAccountNo(accountNo);
                    loan.setIfscCode(ifsc);
                    loan.setLoanAmount(loanAmount);
                    loan.setSanctionAmount(sanctionAmount);
                    toSave.add(loan);
                }
                }
            }

        if(!errors.isEmpty()){
            throw new BadRequestException(errors);
        }

        for (Loan loan : toSave) {
            loanService.save(loan);
        }

        return Map.of("Inserted", toSave.size());
    }

    private String get(CSVRecord record, String name){
        return record.isSet(name) && record.get(name) != null ? record.get(name).trim() : "";
    }

}
