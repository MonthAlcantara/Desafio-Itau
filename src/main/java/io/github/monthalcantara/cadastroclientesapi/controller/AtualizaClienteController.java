package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.dto.request.ClienteRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.AtualizaClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class AtualizaClienteController {

    private final AtualizaClienteService atualizaService;
    private final ClienteMapper mapper;

    public AtualizaClienteController(AtualizaClienteService atualizaService, ClienteMapper instance) {
        this.atualizaService = atualizaService;
        mapper = instance;
    }

    @PutMapping("/{email}")
    @Operation(summary = "Atualiza um cliente salvo", description = "Retorna o cliente atualizado no body. Campo 'Documento' não é atualizável")
    public ResponseEntity<ClienteResponse> atualiza(@PathVariable("email") String email, @Valid @RequestBody ClienteRequest novoCliente) {
        log.info("[Atualiza-Cliente-Controller] Recebida requisicao para atualizacao do ciente de email {}", email);

        final var cliente = mapper.toEntity(novoCliente);

        final var clienteAtualizado = atualizaService.executa(email, cliente);

        log.info("[Atualiza-Cliente-Controller] Finalizado processo de atualizacao do ciente de email {}", email);

        return ResponseEntity.ok(mapper.toResponse(clienteAtualizado));
    }
}
