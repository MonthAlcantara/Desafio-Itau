package io.github.monthalcantara.cadastroclientesapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils;
import io.github.monthalcantara.cadastroclientesapi.dto.request.ClienteRequest;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.CadastraClienteService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste cadastro de clientes")
class CadastraClienteControllerTest {
    @MockBean
    private CadastraClienteService cadastraCliente;
    @MockBean
    private ClienteMapper mapper;
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve cadastrar um cliente")
    void cadastraClienteTest() throws Exception {
        final var clienteRequest = ClienteBuilder.clienteRequestBuild();
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteResponse = ClienteBuilder.clienteResponse();

        Mockito.when(mapper.toEntity(clienteRequest)).thenReturn(cliente);
        Mockito.when(mapper.toResponse(cliente)).thenReturn(clienteResponse);
        Mockito.when(cadastraCliente.executa(any())).thenReturn(cliente);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ConstantsUtils.CLIENTES_API).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(toJson(clienteRequest));

        mvc.perform(request).andExpect(status().isCreated());
    }

    private static String toJson(ClienteRequest clienteRequest) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(clienteRequest);
    }
}