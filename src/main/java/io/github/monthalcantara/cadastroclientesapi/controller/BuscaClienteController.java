package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.processor.BuscaClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class BuscaClienteController {

    private final BuscaClienteService clienteService;
    private final ClienteMapper mapper;

    public BuscaClienteController(BuscaClienteService clienteService, ClienteMapper instance) {
        this.clienteService = clienteService;
        mapper = instance;
    }

    @GetMapping
    @Operation(summary = "Lista todos os clientes de forma paginada", description = "O tamanho default é de 20 registros por página, ordenados pelo id")
    public ResponseEntity<Page<ClienteResponse>> busca(@ParameterObject @PageableDefault(size = 20, sort = {"nome"}) Pageable pageable) {
        log.info("[Busca-Cliente-Controller] Recebida requisicao para busca de todos os clientes");

        final var clienteResponseList = clienteService.executa(pageable)
                .getContent()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        log.info("[Busca-Cliente-Controller] Finalizado processamento para busca de todos os clientes");

        return ResponseEntity.ok(new PageImpl<>(clienteResponseList));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Busca um usuário pelo email", description = "email formatado xyz@xyz.com")
    public ResponseEntity<ClienteResponse> busca(@PathVariable String email) {
        log.info("[Busca-Cliente-Controller] Recebida requisicao para busca do cliente de email {}", email);

        Cliente cliente = clienteService.executa(email);

        log.info("[Busca-Cliente-Controller] Finalizado processamento para busca do cliente de email {}", email);

        return ResponseEntity.ok(mapper.toResponse(cliente));
    }
}
