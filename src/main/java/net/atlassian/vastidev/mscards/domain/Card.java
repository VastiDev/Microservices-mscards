package net.atlassian.vastidev.mscards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LabelCard label;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card(String name, LabelCard label, BigDecimal income, BigDecimal basicLimit ){
        this.name = name;
        this.label = label;
        this.income = income;
        this.basicLimit = basicLimit;

    }
}
