package net.atlassian.vastidev.mscards.application;

import lombok.RequiredArgsConstructor;
import net.atlassian.vastidev.mscards.application.representation.CardSaveRequest;
import net.atlassian.vastidev.mscards.application.representation.CardsPerClientResponse;
import net.atlassian.vastidev.mscards.domain.Card;
import net.atlassian.vastidev.mscards.domain.ClientCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsController {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        Card savedCard = cardService.save(card);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCard.getId())
                .toUri();

        // Criar um mapa ou um objeto para representar a resposta com o ID
        Map<String, Long> responseBody = Collections.singletonMap("id", savedCard.getId());

        return ResponseEntity.created(location).body(responseBody);
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsWithIncomeTill(@RequestParam("income") Long income){
        List <Card> list = cardService.getCardsIncomeLessEqual(income);
        return ResponseEntity.ok(list);

    }
    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerClientResponse>> getCardsByClient(
            @RequestParam("cpf") String cpf){
        List<ClientCard> list = clientCardService.listCardsByCpf(cpf);
        List<CardsPerClientResponse> resultList = list.stream()
                .map(CardsPerClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
