package com.linneaus.portal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "roleID")
    private Long id;

    private String name;
    private String type;

}
