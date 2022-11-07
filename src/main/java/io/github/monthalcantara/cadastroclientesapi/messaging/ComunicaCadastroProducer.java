package io.github.monthalcantara.cadastroclientesapi.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComunicaCadastroProducer {

    public void send(Long idCliente) {
        log.info("Cliente do cliente de id {} sendo enviado para o tópico", idCliente);
    }
}
