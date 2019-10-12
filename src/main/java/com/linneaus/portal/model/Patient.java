package com.linneaus.portal.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Patient {
    private Long userId;
    private String username;
    private String email;
    private BigDecimal lat;
    private BigDecimal lng;
}
