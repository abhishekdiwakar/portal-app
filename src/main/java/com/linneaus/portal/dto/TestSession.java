package com.linneaus.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TestSession {
  private int type;
  private String x;
  private String y;
  private String time;
  private String button;
  private String correct;

  public TestSession(int type, String x, String y, String time) {
    this.type = type;
    this.x = x;
    this.y = y;
    this.time = time;
  }
}