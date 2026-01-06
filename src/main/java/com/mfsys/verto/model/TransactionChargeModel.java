package com.mfsys.verto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction_charges")
@Getter
@Setter
public class TransactionChargeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pch_chrgcode")
    private String pch_chrgcode;

    @Column(name = "pch_chrgdesc")
    private String pch_chrgdesc;

    @Column(name = "pch_chrgprofit")
    private Integer pch_chrgprofit; // must be Integer (null allowed)

    @Column(name = "pch_chrgshort")
    private String pch_chrgshort;

    @Column(name = "pel_elmtcode")
    private String pel_elmtcode;

    @Column(name = "ptr_trancode")
    private String ptr_trancode;

    @Column(name = "soc_charges")
    private String soc_charges;
}
