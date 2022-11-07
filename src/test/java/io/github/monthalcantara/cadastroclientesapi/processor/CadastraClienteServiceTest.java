package io.github.monthalcantara.cadastroclientesapi.processor;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.exceptions.RegraNegocioException;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import io.github.monthalcantara.cadastroclientesapi.validation.ClienteValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Cadastro de clientes")
class CadastraClienteServiceTest {
    @Mock
    private ClienteRepository repository;
    @Mock
    private ClienteValidator clienteValidator;
    private CadastraClienteService service;

    @BeforeEach
    public void init() {
        service = new CadastraClienteService(repository, clienteValidator);
    }

    @Test
    @DisplayName("Deve cadastrar um novo cliente caso cliente seja válido")
    void cadastraNovoClienteTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        Mockito.doNothing().when(clienteValidator).valida(cliente);
        Mockito.when(repository.save(cliente)).thenReturn(cliente);

        service.executa(cliente);

        Mockito.verify(repository, Mockito.only()).save(cliente);
        Mockito.verify(clienteValidator, Mockito.only()).valida(cliente);
    }

    @Test
    @DisplayName("Deve cadastrar um novo cliente caso cliente seja válido")
    void cadastraNovoClienteIrregularTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        Mockito.doThrow(RegraNegocioException.class).when(clienteValidator).valida(cliente);
        Mockito.when(repository.save(cliente)).thenReturn(cliente);

        assertThrows(
                RegraNegocioException.class, () -> service.executa(cliente), "Já existe um cliente cadastrado com esse email ou documento"
        );

        Mockito.verify(repository, Mockito.never()).save(cliente);
        Mockito.verify(clienteValidator, Mockito.only()).valida(cliente);
    }
}