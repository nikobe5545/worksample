package se.beis.worksample.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewAccountRequest {
    private Long customerId;
    private BigDecimal initialCredit;
}
