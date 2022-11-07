package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.exceptions.RecursoNaoEncontradoException;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuscaClienteService {
    private final ClienteRepository repository;

    public BuscaClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Page<Cliente> executa(Pageable pageable) {
        log.info("[Busca-Cliente-Service] Buscando todos os clientes");

        return repository.findAll(pageable);
    }

    public Cliente executa(String email) {
        log.info("[Busca-Cliente-Service] Buscando o cliente pelo email {}", email);

        return repository.findByEmail(email)
                .orElseThrow(RecursoNaoEncontradoException::new);
    }

    public Cliente executa(Long id) {
        log.info("[Busca-Cliente-Service] Buscando o cliente pelo id {}", id);

        return repository.findById(id)
                .orElseThrow(RecursoNaoEncontradoException::new);
    }
}
