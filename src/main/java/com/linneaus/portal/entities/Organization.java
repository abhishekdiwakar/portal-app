package com.linneaus.portal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    Long organizationID;

    String name;
}