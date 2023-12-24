package net.atlassian.vastidev.mscards.application;

import lombok.RequiredArgsConstructor;
import net.atlassian.vastidev.mscards.domain.Card;
import net.atlassian.vastidev.mscards.infra.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Card save(Card card){
        return repository.save(card);
    }

    public List<Card> getCardsIncomeLessEqual(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
