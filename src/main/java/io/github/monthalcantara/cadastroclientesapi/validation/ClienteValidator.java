package io.github.monthalcantara.cadastroclientesapi.validation;

import io.github.monthalcantara.cadastroclientesapi.exceptions.RegraNegocioException;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteValidator {

    private final ClienteRepository repository;

    public ClienteValidator(ClienteRepository repository) {
        this.repository = repository;
    }

    public void valida(Cliente cliente) {
        if (cliente.naoEstaSalvo() && repository.existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf())) {
            log.error("[Cliente-Validator] Erro de validacao para o cliente de email {}", cliente.getEmail());
            throw new RegraNegocioException("Já existe um cliente cadastrado com esse email ou documento");
        }
    }
}
