package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils;
import io.github.monthalcantara.cadastroclientesapi.processor.BuscaClienteService;
import io.github.monthalcantara.cadastroclientesapi.processor.DeletaClienteService;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste de deleção de clientes")
class DeletaClienteControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DeletaClienteService deletaService;
    @MockBean
    private BuscaClienteService buscaClienteService;

    @Test
    @DisplayName("Deve deletar o cliente que tiver o email específico")
    void testeDeletaCliente() throws Exception {
        final var cliente = ClienteBuilder.clienteSalvoBuild();

        Mockito.doNothing().when(deletaService).executa(cliente.getEmail());
        Mockito.when(buscaClienteService.executa(anyString())).thenReturn(cliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(ConstantsUtils.CLIENTES_API.concat(ConstantsUtils.EMAIL_DEFAULT))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isNoContent());
    }
}