package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.dto.request.EnderecoRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.EnderecoResponse;
import io.github.monthalcantara.cadastroclientesapi.mapper.EnderecoMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.CadastraEnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class CadastraEnderecoController {

    private final CadastraEnderecoService cadastraEndereco;
    private final EnderecoMapper mapper;

    public CadastraEnderecoController(CadastraEnderecoService enderecoService, EnderecoMapper mapper) {
        this.cadastraEndereco = enderecoService;
        this.mapper = mapper;
    }


    @PatchMapping("/{id}/endereco")
    @Operation(summary = "Cadastra um endero para o cliente", description = "Retorna o endereco atualzado no body e o location para o próximo passo do fluxo de cadastro no header")
    public ResponseEntity<EnderecoResponse> cadastra(@RequestBody @Valid EnderecoRequest atualizaEnderecoRequest, @PathVariable("id") Long id) {
        log.info("[Cadastra-Endereco-Controller] Recebida requisicao para cadastro de endereco do cliente de id {}", id);

        final var endereco = mapper.toEntity(atualizaEnderecoRequest);
        cadastraEndereco.executa(id, endereco);

        final var uriProximoPasso =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}/arquivo")
                        .buildAndExpand(1)
                        .toUri();

        log.info("[Cadastra-Endereco-Controller] Finalizado o processamento de cadastro de endereco do cliente de id {}", id);

        final var enderecoResponse = mapper.toResponse(endereco);

        return ResponseEntity.created(uriProximoPasso).body(enderecoResponse);
    }
}
