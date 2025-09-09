package com.BeneLoan.BeneLoan.entity;


import com.BeneLoan.BeneLoan.Enum.Caste;
import jakarta.persistence.*;

@Entity
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "beneficiary_id", unique = true, nullable = false)
    private String beneficiaryId;


    @Enumerated(EnumType.STRING)   // store enum name (GEN, OBC, SC, ST, OTHER)
    private Caste caste;

    public Beneficiary(){}

    public Beneficiary(Long id, String name, String beneficiaryId, Caste caste) {
        this.id = id;
        this.name = name;
        this.beneficiaryId = beneficiaryId;
        this.caste = caste;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Caste getCaste() {
        return caste;
    }

    public void setCaste(Caste caste) {
        this.caste = caste;
    }
}
