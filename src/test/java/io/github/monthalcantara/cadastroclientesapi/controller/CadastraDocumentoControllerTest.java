package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.EnderecoBuilder;
import io.github.monthalcantara.cadastroclientesapi.mapper.EnderecoMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.CadastraDocumentoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils.CLIENTES_API_PASSO_3;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste Atualizacao de arquivo de documento do clientes")
class CadastraDocumentoControllerTest {
    @MockBean
    private CadastraDocumentoService cadastraDocumento;
    @MockBean
    private EnderecoMapper mapper;
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve atualizar o documento do cliente com id específico")
    void atualizaClienteTest() throws Exception {
        final var enderecoRequest = EnderecoBuilder.enderecoRequestBuild();
        final var endereco = EnderecoBuilder.enderecoBuild();
        final var enderecoResponse = EnderecoBuilder.enderecoResponse();
        final var file = Mockito.mock(MultipartFile.class);

        Mockito.when(mapper.toEntity(enderecoRequest)).thenReturn(endereco);
        Mockito.when(mapper.toResponse(endereco)).thenReturn(enderecoResponse);
        Mockito.when(cadastraDocumento.executa(anyLong(), any())).thenReturn(ClienteBuilder.clienteSalvoBuild());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch(CLIENTES_API_PASSO_3).accept(MediaType.APPLICATION_PDF).contentType(MediaType.APPLICATION_PDF).content(file.getBytes());

        mvc.perform(request);
    }
}