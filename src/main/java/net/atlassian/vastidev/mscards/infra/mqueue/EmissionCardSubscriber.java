package net.atlassian.vastidev.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.atlassian.vastidev.mscards.domain.Card;
import net.atlassian.vastidev.mscards.domain.ClientCard;
import net.atlassian.vastidev.mscards.domain.DatasSolicitEmissionCards;
import net.atlassian.vastidev.mscards.infra.repository.CardRepository;
import net.atlassian.vastidev.mscards.infra.repository.ClientCardRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissionCardSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;


    @RabbitListener(queues = "${mq.queues.emissao-cards}")
    public void receiveQueryEmission (@Payload String payload){
        try{
            var mapper = new ObjectMapper();

            DatasSolicitEmissionCards datas = mapper.readValue(payload, DatasSolicitEmissionCards.class);
            Card card = cardRepository.findById(datas.getIdCard()).orElseThrow();

            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(datas.getCpf());
            clientCard.setLimit_value(datas.getLimitReleased());

            clientCardRepository.save(clientCard);

        }catch(Exception e){
            log.error("Erro ao receber solicitação de emissão de cartão: {}", e.getMessage());
        }

        }
}
