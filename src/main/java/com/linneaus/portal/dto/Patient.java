package com.linneaus.portal.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long userId;
    private String username;
    private String email;
    private BigDecimal lat;
    private BigDecimal lng;
    private List<Therapy> therapy;
}
