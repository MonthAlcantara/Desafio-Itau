package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.CadastraDocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class CadastraDocumentoController {

    private final CadastraDocumentoService cadastraDocumento;
    private final ClienteMapper mapper;

    public CadastraDocumentoController(CadastraDocumentoService cadastraDocumento, ClienteMapper mapper) {
        this.cadastraDocumento = cadastraDocumento;
        this.mapper = mapper;
    }

    @PatchMapping("/{id}/arquivo")
    @Operation(summary = "Cadastra o arquivo de documento de cliente", description = "Arquivo de identificacao do cliente deve ser enviado no formato pdf")
    public ResponseEntity<ClienteResponse> cadastraArquivo(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        log.info("[Cadastra-Documento-Controller] Recebida requisicao para cadastro de arquivo de documento do cliente de id {}", id);

        final var cliente = cadastraDocumento.executa(id, file);

        final var clienteResponse = mapper.toResponse(cliente);

        log.info("[Cadastra-Documento-Controller] Finalizado processamento de cadastro de documento do cliente de id {}", id);

        return ResponseEntity.ok(clienteResponse);
    }
}
