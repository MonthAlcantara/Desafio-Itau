package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.model.Endereco;
import io.github.monthalcantara.cadastroclientesapi.repositories.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CadastraEnderecoService {

    private final AtualizaClienteService atualizaClienteService;
    private final EnderecoRepository enderecoRepository;

    public CadastraEnderecoService(AtualizaClienteService atualizaClienteService, EnderecoRepository enderecoRepository) {
        this.atualizaClienteService = atualizaClienteService;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Cliente executa(Long idCliente, Endereco endereco) {
        log.info("[Cadastra-Endereco-Service] Cadastrando endereco para o cliente de id {}", idCliente);

        final var enderecoSalvo = enderecoRepository.save(endereco);

        log.info("[Cadastra-Endereco-Service] Finalizado o processo de cadastro de endereco do cliente de id {}", idCliente);

        return atualizaClienteService.executa(idCliente, enderecoSalvo);

    }
}
