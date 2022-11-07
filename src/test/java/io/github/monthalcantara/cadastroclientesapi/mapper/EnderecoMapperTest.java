package io.github.monthalcantara.cadastroclientesapi.mapper;

import io.github.monthalcantara.cadastroclientesapi.commons.EnderecoBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste Mapper de Endereco")
class EnderecoMapperTest {

    @Test
    @DisplayName("Deve converter um objeto do tipo EnderecoRequest em Endereco")
    void toEntity() {
        final var request = EnderecoBuilder.enderecoRequestBuild();

        final var resultado = EnderecoMapper.INSTANCE.toEntity(request);

        assertEquals(request.getCep(), resultado.getCep());
        assertEquals(request.getBairro(), resultado.getBairro());
        assertEquals(request.getCidade(), resultado.getCidade());
        assertEquals(request.getComplemento(), resultado.getComplemento());
        assertEquals(request.getRua(), resultado.getRua());
        assertEquals(request.getEstado(), resultado.getEstado());
    }

    @Test
    @DisplayName("Deve converter um objeto do tipo Endereco em EnderecoResponse")
    void toResponse() {
        final var endereco = EnderecoBuilder.enderecoBuild();

        final var resultado = EnderecoMapper.INSTANCE.toResponse(endereco);

        assertEquals(endereco.getCep(), resultado.getCep());
        assertEquals(endereco.getBairro(), resultado.getBairro());
        assertEquals(endereco.getCidade(), resultado.getCidade());
        assertEquals(endereco.getComplemento(), resultado.getComplemento());
        assertEquals(endereco.getRua(), resultado.getRua());
        assertEquals(endereco.getEstado(), resultado.getEstado());
    }
}