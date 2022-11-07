package io.github.monthalcantara.cadastroclientesapi.mapper;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste Mapper de Cliente")
class ClienteMapperTest {


    @Test
    @DisplayName("Deve converter um objeto do tipo ClienteRequest em Cliente")
    void toEntity() {
        final var clienteRequest = ClienteBuilder.clienteRequestBuild();

        final var resultado = ClienteMapper.INSTANCE.toEntity(clienteRequest);

        assertEquals(clienteRequest.getNome(), resultado.getNome());
        assertEquals(clienteRequest.getSobrenome(), resultado.getSobrenome());
        assertEquals(clienteRequest.getDataDeNascimento(), resultado.getDataDeNascimento());
        assertEquals(clienteRequest.getCpf(), resultado.getCpf());
        assertEquals(clienteRequest.getEmail(), resultado.getEmail());
    }

    @Test
    @DisplayName("Deve converter um objeto do tipo Cliente em ClienteResponse")
    void toResponse() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();

        final var resultado = ClienteMapper.INSTANCE.toResponse(cliente);

        assertEquals(cliente.getNome(), resultado.getNome());
        assertEquals(cliente.getSobrenome(), resultado.getSobrenome());
        assertEquals(cliente.getDataDeNascimento(), resultado.getDataDeNascimento());
        assertEquals(cliente.getCpf(), resultado.getCpf());
        assertEquals(cliente.getEmail(), resultado.getEmail());
    }
}