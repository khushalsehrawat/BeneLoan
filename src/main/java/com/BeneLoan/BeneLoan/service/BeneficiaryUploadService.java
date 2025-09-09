package com.BeneLoan.BeneLoan.service;


import com.BeneLoan.BeneLoan.dto.RowError;
import com.BeneLoan.BeneLoan.entity.Beneficiary;
import com.BeneLoan.BeneLoan.Enum.Caste;
import com.BeneLoan.BeneLoan.exception.BadRequestException;
import com.BeneLoan.BeneLoan.utils.CsvUtils;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class BeneficiaryUploadService {

    private final BeneficiaryService beneficiaryService;

    public BeneficiaryUploadService(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @Transactional
    public Map<String, Object> upload(MultipartFile file) throws Exception{
        String[] headers = {"name", "beneficiary_id", "caste" };

        List<RowError> errors = new ArrayList<>();
        List<Beneficiary> toSave = new ArrayList<>();
        Set<String> seenInFile = new HashSet<>();

        try(CSVParser parser = CsvUtils.parse(file.getInputStream(),headers)){
            int rowNum = 1;
            for (CSVRecord rec : parser) {
                rowNum++; // data rows start at 2

                String name = get(rec, "name");
                String beneficiaryId = get(rec, "beneficiary_id");
                String casteRaw = get(rec, "caste");

                boolean rowHasError = false;

                // Required fields
                if (name.isBlank()) {
                    errors.add(new RowError(rowNum, "name is required"));
                    rowHasError = true;
                }
                if (beneficiaryId.isBlank()) {
                    errors.add(new RowError(rowNum, "beneficiary_id is required"));
                    rowHasError = true;
                }

                // Uniqueness (in file)
                if (!beneficiaryId.isBlank()) {
                    if (!seenInFile.add(beneficiaryId)) {
                        errors.add(new RowError(rowNum, "duplicate beneficiary_id in file: " + beneficiaryId));
                        rowHasError = true;
                    }
                }
                // Uniqueness (in DB)
                if (!beneficiaryId.isBlank() && beneficiaryService.existsByBeneficiaryId(beneficiaryId)) {
                    errors.add(new RowError(rowNum, "beneficiary_id already exists: " + beneficiaryId));
                    rowHasError = true;
                }

                Caste casteEnum = null;
                if (!casteRaw.isBlank()) {
                    String norm = casteRaw.equalsIgnoreCase("Other") ? "OTHER" : casteRaw.trim().toUpperCase(Locale.ROOT);
                    try {
                        casteEnum = Caste.valueOf(norm);
                    } catch (IllegalArgumentException ex) {
                        errors.add(new RowError(rowNum, "invalid caste: " + casteRaw));
                        rowHasError = true;
                    }
                }

                // If row OK, stage for save
                if (!rowHasError) {
                    Beneficiary b = new Beneficiary();
                    b.setName(name);
                    b.setBeneficiaryId(beneficiaryId);
                    b.setCaste(casteEnum);
                    toSave.add(b);
                }
            }
            }
        // If any error, reject whole file
        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }

        // Save all
        for (Beneficiary b : toSave) {
            beneficiaryService.save(b);
        }
        return Map.of("inserted", toSave.size());
    }

    private String get(CSVRecord rec, String name) {
        return rec.isSet(name) && rec.get(name) != null ? rec.get(name).trim() : "";
    }

}
