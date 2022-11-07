package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.dto.request.ClienteRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.CadastraClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class CadastraClienteController {

    private final CadastraClienteService cadastraCliente;
    private final ClienteMapper mapper;

    public CadastraClienteController(CadastraClienteService cadastraCliente, ClienteMapper mapper) {
        this.cadastraCliente = cadastraCliente;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente", description = "Retorna o cliente salvo no body e o location para o próximo passo do fluxo de cadastro no header")
    public ResponseEntity<ClienteResponse> cadastra(@Valid @RequestBody ClienteRequest novoCliente) {
        log.info("[Cadastra-Cliente-Controller] Recebida requisicao para cadastro do cliente de email {}", novoCliente.getEmail());

        final var cliente = mapper.toEntity(novoCliente);

        final var clienteSalvo = cadastraCliente.executa(cliente);

        final var clienteResponse = mapper.toResponse(cliente);

        final var uriProximoPasso = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/endereco")
                .buildAndExpand(clienteSalvo.getId())
                .toUri();

        log.info("[Cadastra-Cliente-Controller] Finalizado o processamento de cadastro do cliente de email {}", novoCliente.getEmail());

        return ResponseEntity.created(uriProximoPasso).body(clienteResponse);
    }
}
