package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.messaging.ComunicaCadastroProducer;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.storage.StoragePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class CadastraDocumentoService {

    private final StoragePublisher storagePublisher;
    private final ComunicaCadastroProducer producer;
    private final AtualizaClienteService atualizaClienteService;

    public CadastraDocumentoService(StoragePublisher storagePublisher, ComunicaCadastroProducer producer, AtualizaClienteService atualizaClienteService) {
        this.storagePublisher = storagePublisher;
        this.producer = producer;
        this.atualizaClienteService = atualizaClienteService;
    }

    public Cliente executa(Long idCliente, MultipartFile file) {

        log.info("Cadastrando documento para o cliente de id {}", idCliente);

        final var urlDocumentoBucket = storagePublisher.send(file);

        final var cliente = atualizaClienteService.executa(idCliente, urlDocumentoBucket);

        log.info("Iniciando postagem no tópico de cliente-cadastrado para o cliente de email {}", cliente.getEmail());

        producer.send(idCliente);

        return cliente;
    }
}
