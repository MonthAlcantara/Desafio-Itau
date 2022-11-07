package io.github.monthalcantara.cadastroclientesapi.commons;

import io.github.monthalcantara.cadastroclientesapi.dto.request.EnderecoRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.EnderecoResponse;
import io.github.monthalcantara.cadastroclientesapi.model.Endereco;

public class EnderecoBuilder {
    public static EnderecoRequest enderecoRequestBuild() {
        return new EnderecoRequest("40000000", "ruaa A", "bairro B", "Complemento C", "Cidade A", "BA");

    }

    public static Endereco enderecoBuild() {
        return new Endereco(1L, "40000000", "ruaa A", "bairro B", "Complemento C", "Cidade A", "BA");
    }

    public static EnderecoResponse enderecoResponse() {
        return new EnderecoResponse("40000000", "ruaa A", "bairro B", "Complemento C", "Cidade A", "BA");
    }
}
