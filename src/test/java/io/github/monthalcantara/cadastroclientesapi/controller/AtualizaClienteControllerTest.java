package io.github.monthalcantara.cadastroclientesapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils;
import io.github.monthalcantara.cadastroclientesapi.dto.request.ClienteRequest;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import io.github.monthalcantara.cadastroclientesapi.processor.AtualizaClienteService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste Atualizacao dos dados básicos de clientes")
class AtualizaClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AtualizaClienteService clienteService;
    @MockBean
    private ClienteMapper mapper;
    @Autowired
    private MockMvc mvc;


    @Test
    @DisplayName("Deve atualizar o cliente com email específico")
    void atualizaClienteTest() throws Exception {
        final var clienteRequest = ClienteBuilder.clienteRequestBuild();
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteResponse = ClienteBuilder.clienteResponse();

        Mockito.when(mapper.toEntity(clienteRequest)).thenReturn(cliente);
        Mockito.when(mapper.toResponse(cliente)).thenReturn(clienteResponse);
        Mockito.when(clienteService.executa(anyString(), any(Cliente.class))).thenReturn(cliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(ConstantsUtils.CLIENTES_API.concat(ConstantsUtils.EMAIL_DEFAULT)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(toJson(clienteRequest));

        mvc.perform(request).andExpect(status().isOk());
    }

    private static String toJson(ClienteRequest clienteRequest) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(clienteRequest);
    }
}