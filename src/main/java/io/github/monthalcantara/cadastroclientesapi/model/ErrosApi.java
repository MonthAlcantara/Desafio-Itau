package io.github.monthalcantara.cadastroclientesapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrosApi {

    private String campo;
    private String valor;
    private List<String> mensagens = new ArrayList<>();

    public ErrosApi(String mensagem, String campo, String valorRejeitado) {
        this.mensagens.add(mensagem);
        this.campo = campo;
        this.valor = valorRejeitado;
    }

    public ErrosApi(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    private ErrosApi() {
    }
}