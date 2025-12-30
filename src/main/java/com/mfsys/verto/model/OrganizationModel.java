package com.mfsys.verto.model;

import com.mfsys.verto.constants.ColumnConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ORGANIZATION")
public class OrganizationModel {
    @Id
    @Column(name = ColumnConstants.POR_ORGACODE, length = 50, nullable = false)
    private String porOrgacode;

    @Column(name = ColumnConstants.POR_ORGADESC, length = 255)
    private String porOrgaDesc;
}
