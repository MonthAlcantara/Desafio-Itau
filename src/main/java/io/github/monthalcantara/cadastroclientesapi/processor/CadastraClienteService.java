package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import io.github.monthalcantara.cadastroclientesapi.validation.ClienteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CadastraClienteService {

    private final ClienteRepository repository;
    private final ClienteValidator clienteValidator;

    public CadastraClienteService(ClienteRepository repository, ClienteValidator clienteValidator) {
        this.repository = repository;
        this.clienteValidator = clienteValidator;
    }

    @Transactional
    public Cliente executa(Cliente cliente) {
        log.info("[Cadastra-Cliente-Service] Iniciando processo de cadastro para o cliente do email {}", cliente.getEmail());

        clienteValidator.valida(cliente);

        log.info("[Cadastra-Cliente-Service] Cliente de email {} validado com sucesso", cliente.getEmail());

        return repository.save(cliente);
    }
}
