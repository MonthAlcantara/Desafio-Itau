package io.github.monthalcantara.cadastroclientesapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(indexes = @Index(name = "email_index", columnList = "email", unique = true))
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String documento;

    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Past
    private LocalDate dataDeNascimento;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente(Long id, String nome, String sobrenome, String cpf, String email, LocalDate dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
    }

    public void atualizaDocumento(String documento) {
        this.documento = documento;
    }

    public void atualizaEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean naoEstaSalvo(){
        return this.id == null;
    }
}
