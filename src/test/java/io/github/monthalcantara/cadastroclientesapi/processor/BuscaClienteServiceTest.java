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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Busca de clientes")
class BuscaClienteServiceTest {
    @Mock
    private ClienteRepository repository;
    private BuscaClienteService buscaClienteService;

    @BeforeEach
    public void init() {
        buscaClienteService = new BuscaClienteService(repository);
    }

    @Test
    @DisplayName("Deve buscar todos os clientes retornando uma lista paginada")
    void buscaTodosClientesTest() {
        final var pageable = PageRequest.of(1, 1);

        final var clientes = List.of(ClienteBuilder.clienteSalvoBuild());
        Mockito.when(repository.findAll(pageable)).thenReturn(new PageImpl<>(clientes));

        buscaClienteService.executa(pageable);

        Mockito.verify(repository, Mockito.only()).findAll(pageable);
    }

    @Test
    @DisplayName("Deve buscar um cliente com específico email com sucesso")
    void buscaClienteEspecificoTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteEmail = cliente.getEmail();

        Mockito.when(repository.findByEmail(clienteEmail)).thenReturn(Optional.of(cliente));

        buscaClienteService.executa(clienteEmail);
        Mockito.verify(repository, Mockito.only()).findByEmail(clienteEmail);
    }

    @Test
    @DisplayName("Deve lançar excessão de RecursoNaoEncontradoException ao buscar um cliente com email que não existe")
    void buscaClienteInexistenteTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteEmail = cliente.getEmail();

        Mockito.when(repository.findByEmail(clienteEmail)).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(
                RecursoNaoEncontradoException.class, () -> buscaClienteService.executa(clienteEmail)
        );

        assertTrue(runtimeException.getMessage().contains("Recurso não encontrado"));
        Mockito.verify(repository, Mockito.only()).findByEmail(clienteEmail);
    }

    @Test
    @DisplayName("Deve buscar um cliente com específico email com sucesso")
    void buscaClientePorIdEspecificoTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteId = cliente.getId();

        Mockito.when(repository.findById(clienteId)).thenReturn(Optional.of(cliente));

        buscaClienteService.executa(clienteId);
        Mockito.verify(repository, Mockito.only()).findById(clienteId);
    }

    @Test
    @DisplayName("Deve lançar excessão de RecursoNaoEncontradoException ao buscar um cliente com email que não existe")
    void buscaClienteDeIdInexistenteTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        final var clienteId = cliente.getId();

        Mockito.when(repository.findById(clienteId)).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(
                RecursoNaoEncontradoException.class, () -> buscaClienteService.executa(clienteId)
        );

        assertTrue(runtimeException.getMessage().contains("Recurso não encontrado"));
        Mockito.verify(repository, Mockito.only()).findById(clienteId);
    }
}