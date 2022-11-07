package io.github.monthalcantara.cadastroclientesapi.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

    private static final String MESSAGE = "Recurso não encontrado";

    public RecursoNaoEncontradoException() {
        super(MESSAGE);
    }

}
