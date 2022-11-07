package io.github.monthalcantara.cadastroclientesapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EnderecoResponse endereco;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String documento;


}
