package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.EnderecoBuilder;
import io.github.monthalcantara.cadastroclientesapi.messaging.ComunicaCadastroProducer;
import io.github.monthalcantara.cadastroclientesapi.storage.StoragePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils.URl_DEFAULt_S3;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Cadastro de Documento de clientes")
class CadastraDocumentoServiceTest {

    @Mock
    private StoragePublisher storagePublisher;
    @Mock
    private AtualizaClienteService atualizaClienteService;
    @Mock
    private ComunicaCadastroProducer producer;
    private CadastraDocumentoService service;


    @BeforeEach
    public void init() {
        service = new CadastraDocumentoService(storagePublisher, producer, atualizaClienteService);
    }

    @Test
    @DisplayName("Deve cadastrar um novo cliente caso cliente seja válido")
    void cadastraNovoClienteTest() {
        final var endereco = EnderecoBuilder.enderecoBuild();
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var file = Mockito.mock(MultipartFile.class);

        Mockito.when(storagePublisher.send(file)).thenReturn(URl_DEFAULt_S3);
        Mockito.when(atualizaClienteService.executa(endereco.getId(), URl_DEFAULt_S3)).thenReturn(cliente);

        service.executa(endereco.getId(), file);

        Mockito.verify(storagePublisher, Mockito.only()).send(file);
        Mockito.verify(atualizaClienteService, Mockito.only()).executa(endereco.getId(), URl_DEFAULt_S3);
    }
}