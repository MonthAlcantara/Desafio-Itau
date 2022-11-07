package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.processor.DeletaClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class DeletaClienteController {

    private final DeletaClienteService deletaService;

    public DeletaClienteController(DeletaClienteService deletaService) {
        this.deletaService = deletaService;
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Remove cliente pelo email", description = "O email precisa existir para poder ser removido, caso contrário será lançado erro")
    public ResponseEntity<Void> deleta(@PathVariable("email") String email) {

        log.info("[Deleta-Cliente-Controller] Recebida requisicao para delecao do ciente de email {}", email);

        deletaService.executa(email);

        log.info("[Deleta-Cliente-Controller] Finalizado processo de delecao do ciente de email {}", email);

        return ResponseEntity.noContent().build();
    }
}
