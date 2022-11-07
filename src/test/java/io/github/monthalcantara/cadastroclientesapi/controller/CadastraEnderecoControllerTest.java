package io.github.monthalcantara.cadastroclientesapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.EnderecoBuilder;
import io.github.monthalcantara.cadastroclientesapi.dto.request.EnderecoRequest;
import io.github.monthalcantara.cadastroclientesapi.mapper.EnderecoMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.CadastraEnderecoService;
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

import static io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils.CLIENTES_API_PASSO_2;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste Atualizacao de endereco dos clientes")
class CadastraEnderecoControllerTest {

    @MockBean
    private CadastraEnderecoService cadastraEndereco;
    @MockBean
    private EnderecoMapper mapper;
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve atualizar o endereco com email específico")
    void atualizaClienteTest() throws Exception {
        final var enderecoRequest = EnderecoBuilder.enderecoRequestBuild();
        final var endereco = EnderecoBuilder.enderecoBuild();
        final var enderecoResponse = EnderecoBuilder.enderecoResponse();

        Mockito.when(mapper.toEntity(enderecoRequest)).thenReturn(endereco);
        Mockito.when(mapper.toResponse(endereco)).thenReturn(enderecoResponse);
        Mockito.when(cadastraEndereco.executa(any(), any())).thenReturn(ClienteBuilder.clienteSalvoBuild());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch(CLIENTES_API_PASSO_2)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(enderecoRequest));

        mvc.perform(request).andExpect(status().isCreated());
    }

    private static String toJson(EnderecoRequest enderecoRequest) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(enderecoRequest);
    }
}