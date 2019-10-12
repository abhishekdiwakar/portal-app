package com.linneaus.portal.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Therapy {
  private String therapyName;
  private String medicine;
  private String dosage;
  List<Test> tests;
}