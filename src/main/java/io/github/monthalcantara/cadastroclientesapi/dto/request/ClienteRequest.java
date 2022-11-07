package io.github.monthalcantara.cadastroclientesapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.github.monthalcantara.cadastroclientesapi.validation.annotations.MaiorIdade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    @NotBlank(message = "{campo.nome.obrigatorio}")
    @Schema(description = "Nome do cliente", example = "Fulano Silva")
    private String nome;

    @NotBlank(message = "{campo.sobrenome.obrigatorio}")
    private String sobrenome;

    @CPF
    @NotBlank(message = "{campo.cpf.obrigatorio}")
    @Schema(description = "CPF do cliente (apenas números)", example = "90826037054")
    private String cpf;

    @Email(message = "{campo.email.invalido}")
    @NotBlank(message = "{campo.email.obrigatorio}")
    @Schema(description = "Email do cliente respeitando a formatação", example = "mock@email.com.br")
    private String email;

    @NotNull(message = "{campo.data-nascimento.obrigatorio}")
    @MaiorIdade(message = "{campo.data-nascimento.maioridade}")
    @Past(message = "{campo.data-nascimento.invalida}")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
}
