package net.atlassian.vastidev.mscards.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DatasSolicitEmissionCards {

    private Long idCard;
    private String cpf;
    private String endereco;
    private BigDecimal limitReleased;
}