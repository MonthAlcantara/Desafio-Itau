package io.github.monthalcantara.cadastroclientesapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    @NotBlank(message = "{campo.cep.obrigatorio}")
    @Schema(description = "Apenas valores numéricos", example = "40365365")
    private String cep;

    @NotBlank(message = "{campo.rua.obrigatorio}")
    @Schema(description = "Rua de moradia do Cliente. Campo obrigatório", example = "Rua das Flores")
    private String rua;

    @NotBlank(message = "{campo.bairro.obrigatorio}")
    @Schema(description = "Bairro de moradia do Cliente. Campo obrigatório", example = "Bairro das Mangueiras")
    private String bairro;

    @Schema(description = "Complemento do endereço do Cliente. Campo não obrigatório", example = "57 E")
    private String complemento;

    @NotBlank(message = "{campo.cidade.obrigatorio}")
    @Schema(description = "Cidade de moradia do Cliente. Campo obrigatório", example = "Salvador")
    private String cidade;

    @NotBlank(message = "{campo.estado.obrigatorio}")
    @Schema(description = "Estado de moradia do Cliente. Campo obrigatório", example = "Bahia")
    private String estado;
}
