package com.BeneLoan.BeneLoan.controller;

import com.BeneLoan.BeneLoan.service.BeneficiaryUploadService;
import com.BeneLoan.BeneLoan.service.LoanUploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final BeneficiaryUploadService beneficiaryUploadService;
    private final LoanUploadService loanUploadService;

    public UploadController(BeneficiaryUploadService beneficiaryUploadService, LoanUploadService loanUploadService) {
        this.beneficiaryUploadService = beneficiaryUploadService;
        this.loanUploadService = loanUploadService;
    }
    // Upload beneficiaries CSV
    @PostMapping(value = "/beneficiaries", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> uploadBeneficiaries(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(beneficiaryUploadService.upload(file));
    }

    // Upload loans CSV
    @PostMapping(value = "/loans", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> uploadLoans(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(loanUploadService.upload(file));
    }

}
