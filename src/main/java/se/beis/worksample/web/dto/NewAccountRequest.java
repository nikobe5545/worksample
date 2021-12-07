package se.beis.worksample.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewAccountRequest {
    private BigDecimal initialCredit;
}
