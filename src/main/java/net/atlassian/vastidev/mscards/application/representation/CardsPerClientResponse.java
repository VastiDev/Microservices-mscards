package net.atlassian.vastidev.mscards.application.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.atlassian.vastidev.mscards.domain.ClientCard;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerClientResponse {
    private String name;
    private String label;
    private BigDecimal limitReleased;

    public static CardsPerClientResponse fromModel(ClientCard model){
        return new CardsPerClientResponse(
                model.getCard().getName(),
                model.getCard().getLabel().toString(),
                model.getLimit_value()
        );
    }
}
