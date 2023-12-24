package net.atlassian.vastidev.mscards.application;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.atlassian.vastidev.mscards.domain.ClientCard;
import net.atlassian.vastidev.mscards.infra.repository.ClientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository repository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
