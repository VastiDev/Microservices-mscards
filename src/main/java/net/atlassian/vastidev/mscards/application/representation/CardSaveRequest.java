package net.atlassian.vastidev.mscards.application.representation;

import lombok.Data;
import net.atlassian.vastidev.mscards.domain.Card;
import net.atlassian.vastidev.mscards.domain.LabelCard;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private LabelCard label;
    private BigDecimal income;
    private BigDecimal limit;

    public Card toModel(){
        return new Card(name, label, income, limit);
    }
}
