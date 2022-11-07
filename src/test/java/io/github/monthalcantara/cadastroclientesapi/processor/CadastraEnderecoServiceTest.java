package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.commons.EnderecoBuilder;
import io.github.monthalcantara.cadastroclientesapi.repositories.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Cadastro de clientes")
class CadastraEnderecoServiceTest {
    @Mock
    private EnderecoRepository repository;
    @Mock
    private AtualizaClienteService clienteService;
    private CadastraEnderecoService service;

    @BeforeEach
    public void init() {
        service = new CadastraEnderecoService(clienteService, repository);
    }

    @Test
    @DisplayName("Deve cadastrar um novo cliente caso cliente seja válido")
    void cadastraNovoClienteTest() {
        final var endereco = EnderecoBuilder.enderecoBuild();
        final var cliente = ClienteBuilder.clienteSalvoBuild();

        Mockito.when(repository.save(endereco)).thenReturn(endereco);
        Mockito.when(clienteService.executa(endereco.getId(), endereco)).thenReturn(cliente);

        service.executa(endereco.getId(), endereco);

        Mockito.verify(repository, Mockito.only()).save(endereco);
    }
}