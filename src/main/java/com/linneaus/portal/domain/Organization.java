package com.linneaus.portal.domain;

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