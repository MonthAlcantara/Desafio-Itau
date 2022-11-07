package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.exceptions.RecursoNaoEncontradoException;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Delecao de clientes")
class DeletaClienteServiceTest {

    private DeletaClienteService deletaClienteService;
    @Mock
    private ClienteRepository repository;
    @Mock
    private BuscaClienteService buscaClienteService;

    @BeforeEach
    public void init() {
        deletaClienteService = new DeletaClienteService(repository, buscaClienteService);
    }

    @Test
    @DisplayName("Deve Deletar um cliente com email específico")
    void executa() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteEmail = cliente.getEmail();

        Mockito.doNothing().when(repository).delete(cliente);
        Mockito.when(buscaClienteService.executa(clienteEmail)).thenReturn(cliente);

        deletaClienteService.executa(clienteEmail);

        Mockito.verify(buscaClienteService, Mockito.only()).executa(clienteEmail);
        Mockito.verify(repository, Mockito.only()).delete(cliente);
    }

    @Test
    @DisplayName("Deve lançar excessão ao tentar Deletar um cliente com email inexistente")
    void executaTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteEmail = cliente.getEmail();
        Mockito.doThrow(RecursoNaoEncontradoException.class).when(buscaClienteService).executa(cliente.getEmail());
        Mockito.when(repository.save(cliente)).thenReturn(cliente);

        assertThrows(RecursoNaoEncontradoException.class, () -> deletaClienteService.executa(clienteEmail));


        Mockito.verify(buscaClienteService, Mockito.only()).executa(clienteEmail);
        Mockito.verify(repository, Mockito.never()).delete(cliente);
    }
}