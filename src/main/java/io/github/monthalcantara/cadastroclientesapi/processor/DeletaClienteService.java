package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Slf4j
@Service
public class DeletaClienteService {

    private final ClienteRepository repository;
    private final BuscaClienteService buscaClienteService;

    public DeletaClienteService(ClienteRepository repository, BuscaClienteService buscaClienteService) {
        this.repository = repository;
        this.buscaClienteService = buscaClienteService;
    }

    @Transactional
    public void executa(String email) {
        log.info("Deletando o cliente pelo email {}", email);

        final var clienteSalvo = buscaClienteService.executa(email);

        log.info("Finalizado o processo de delecao do cliente de email {}", email);

        repository.delete(clienteSalvo);
    }
}
