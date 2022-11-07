package io.github.monthalcantara.cadastroclientesapi.controller;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils;
import io.github.monthalcantara.cadastroclientesapi.mapper.ClienteMapper;
import io.github.monthalcantara.cadastroclientesapi.processor.BuscaClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Teste Busca clientes cadastrados")
class BuscaClienteControllerTest {

    @MockBean
    private BuscaClienteService clienteService;
    @MockBean
    private ClienteMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve buscar o cliente com email específico")
    void buscaClientePorEmailTest() throws Exception {
        final var clienteRequest = ClienteBuilder.clienteRequestBuild();
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteResponse = ClienteBuilder.clienteResponse();

        Mockito.when(mapper.toEntity(clienteRequest)).thenReturn(cliente);
        Mockito.when(mapper.toResponse(cliente)).thenReturn(clienteResponse);
        Mockito.when(clienteService.executa(anyString())).thenReturn(cliente);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ConstantsUtils.CLIENTES_API.concat(ConstantsUtils.EMAIL_DEFAULT));

        mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("nome").value(clienteResponse.getNome())).andExpect(jsonPath("sobrenome").value(clienteResponse.getSobrenome())).andExpect(jsonPath("cpf").value(clienteResponse.getCpf())).andExpect(jsonPath("email").value(clienteResponse.getEmail()));
    }

    @Test
    @DisplayName("Deve buscar todos os clientes de forma paginada")
    void buscaClientesTest() throws Exception {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clientes = List.of(cliente);

        Mockito.when(clienteService.executa(any(Pageable.class))).thenReturn(new PageImpl<>(clientes));
        Mockito.when(mapper.toResponse(cliente)).thenReturn(ClienteBuilder.clienteResponse());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ConstantsUtils.CLIENTES_API);

        mvc.perform(request);
    }
}