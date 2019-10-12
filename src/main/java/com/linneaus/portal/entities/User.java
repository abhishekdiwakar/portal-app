package com.linneaus.portal.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {

  @Id
  @Column(name = "userID")
  private Long userId;

  private String username;
  private String email;

  @ManyToOne
  @JoinColumn(name = "Role_IDrole")
  private Role role;

  @ManyToOne
  @JoinColumn(name = "Organization")
  private Organization organization;

  @Column(name = "Lat")
  private BigDecimal lat;

  @Column(name = "Long")
  private BigDecimal lng;

  @OneToMany(mappedBy = "doctor")
  private List<Therapy> therapies;
}
