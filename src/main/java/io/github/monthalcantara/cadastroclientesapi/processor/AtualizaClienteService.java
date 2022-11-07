package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.model.Endereco;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class AtualizaClienteService {

    private final ClienteRepository repository;
    private final BuscaClienteService buscaCliente;

    public AtualizaClienteService(ClienteRepository repository, BuscaClienteService buscaCliente) {
        this.repository = repository;
        this.buscaCliente = buscaCliente;
    }

    @Transactional
    public Cliente executa(String email, Cliente cliente) {
        log.info("[Atualiza-Cliente-Service] Iniciando processo de atualização de dados basicos do cliente de email {}", email);

        final var clienteSalvo = buscaCliente.executa(email);

        BeanUtils.copyProperties(cliente, clienteSalvo, "id", "endereco", "documento");

        log.info("[Atualiza-Cliente-Service] Atualizando dados basicos do cliente de email {}", email);

        return repository.save(clienteSalvo);
    }

    public Cliente executa(Long idCliente, Endereco endereco) {
        log.info("[Atualiza-Cliente-Service] Iniciando processo de atualização de endereco do cliente de id {}", idCliente);

        final var clienteSalvo = buscaCliente.executa(idCliente);
        clienteSalvo.atualizaEndereco(endereco);

        log.info("[Atualiza-Cliente-Service] Atualizando endereco do cliente de id {}", idCliente);

        return repository.save(clienteSalvo);
    }

    public Cliente executa(Long idCliente, String documento) {
        log.info("[Atualiza-Cliente-Service] Iniciando processo de atualização de documento cliente pelo id {}", idCliente);

        final var clienteSalvo = buscaCliente.executa(idCliente);
        clienteSalvo.atualizaDocumento(documento);

        log.info("[Atualiza-Cliente-Service] Atualizando arquivo de documento do cliente de id {}", idCliente);

        return repository.save(clienteSalvo);
    }
}
