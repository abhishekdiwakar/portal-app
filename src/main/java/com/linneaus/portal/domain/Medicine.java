package com.linneaus.portal.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Medicine")
public class Medicine {
    @Id
    @Column(name = "medicineID")
    private Long id;
    private String name;
}
