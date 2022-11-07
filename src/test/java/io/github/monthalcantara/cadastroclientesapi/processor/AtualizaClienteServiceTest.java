package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Atualizacao de clientes")
class AtualizaClienteServiceTest {
    @Mock
    private ClienteRepository repository;
    @Mock
    private BuscaClienteService buscaClienteService;
    private AtualizaClienteService atualizaService;

    @BeforeEach
    public void init() {
        atualizaService = new AtualizaClienteService(repository, buscaClienteService);
    }

    @Test
    @DisplayName("Deve atualizar cliente com determinado email")
    void atualizaClienteTest() {
        final var cliente = ClienteBuilder.clienteAtualizadoBuild();
        final var clienteSalvo = ClienteBuilder.clienteSalvoBuild();
        final var clienteEmail = cliente.getEmail();

        Mockito.when(buscaClienteService.executa(clienteEmail)).thenReturn(clienteSalvo);
        Mockito.when(repository.save(clienteSalvo)).thenReturn(cliente);

        final var resultado = atualizaService.executa(clienteEmail, cliente);

        Assertions.assertEquals(cliente.getId(), resultado.getId());
        Assertions.assertEquals(cliente.getEmail(), resultado.getEmail());
        Assertions.assertEquals(cliente.getNome(), resultado.getNome());
        Assertions.assertEquals(cliente.getDocumento(), resultado.getDocumento());

        Mockito.verify(buscaClienteService, Mockito.only()).executa(clienteEmail);
        Mockito.verify(repository, Mockito.only()).save(clienteSalvo);
    }

    @Test
    @DisplayName("Deve atualizar endereco do cliente com determinado id")
    void atualizaEnderecoClienteTest() {
        final var cliente = ClienteBuilder.clienteAtualizadoBuild();
        final var clienteSalvo = ClienteBuilder.clienteSalvoBuild();
        final var clienteId = cliente.getId();

        Mockito.when(buscaClienteService.executa(clienteId)).thenReturn(clienteSalvo);
        Mockito.when(repository.save(clienteSalvo)).thenReturn(cliente);

        final var resultado = atualizaService.executa(clienteId, cliente.getEndereco());

        Assertions.assertEquals(cliente.getId(), resultado.getId());
        Assertions.assertEquals(cliente.getEmail(), resultado.getEmail());
        Assertions.assertEquals(cliente.getNome(), resultado.getNome());
        Assertions.assertEquals(cliente.getDocumento(), resultado.getDocumento());

        Mockito.verify(buscaClienteService, Mockito.only()).executa(clienteId);
        Mockito.verify(repository, Mockito.only()).save(clienteSalvo);
    }

    @Test
    @DisplayName("Deve atualizar documento do cliente com determinado id")
    void atualizaDocumentoClienteTest() {
        final var cliente = ClienteBuilder.clienteAtualizadoBuild();
        final var clienteSalvo = ClienteBuilder.clienteSalvoBuild();
        final var clienteId = cliente.getId();

        Mockito.when(buscaClienteService.executa(clienteId)).thenReturn(clienteSalvo);
        Mockito.when(repository.save(clienteSalvo)).thenReturn(cliente);

        final var resultado = atualizaService.executa(clienteId, cliente.getDocumento());

        Assertions.assertEquals(cliente.getId(), resultado.getId());
        Assertions.assertEquals(cliente.getEmail(), resultado.getEmail());
        Assertions.assertEquals(cliente.getNome(), resultado.getNome());
        Assertions.assertEquals(cliente.getDocumento(), resultado.getDocumento());

        Mockito.verify(buscaClienteService, Mockito.only()).executa(clienteId);
        Mockito.verify(repository, Mockito.only()).save(clienteSalvo);
    }
}