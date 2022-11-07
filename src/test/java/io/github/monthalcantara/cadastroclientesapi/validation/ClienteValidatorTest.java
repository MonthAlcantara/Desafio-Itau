package io.github.monthalcantara.cadastroclientesapi.validation;

import io.github.monthalcantara.cadastroclientesapi.commons.ClienteBuilder;
import io.github.monthalcantara.cadastroclientesapi.exceptions.RegraNegocioException;
import io.github.monthalcantara.cadastroclientesapi.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste Validador de unicidade de clientes")
class ClienteValidatorTest {

    @Mock
    private ClienteRepository repository;
    private ClienteValidator validator;

    @BeforeEach
    void init() {
        validator = new ClienteValidator(repository);
    }

    @Test
    @DisplayName("Deve lançar excessão quando validação de cliente possuir erros")
    void validaClienteInvalidoTest() {
        final var cliente = ClienteBuilder.clienteNaoSalvoBuild();

        Mockito.when(repository.existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf())).thenReturn(true);

        RuntimeException runtimeException = assertThrows(
                RegraNegocioException.class, () -> validator.valida(cliente)
        );

        assertTrue(runtimeException.getMessage().contains("Já existe um cliente cadastrado com esse email ou documento"));
        Mockito.verify(repository, Mockito.only()).existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf());

    }

    @Test
    @DisplayName("Deve concluir validação de cliente sem erros")
    void validaClienteTest() {
        final var cliente = ClienteBuilder.clienteNaoSalvoBuild();
        Mockito.when(repository.existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf())).thenReturn(false);

        validator.valida(cliente);

        Mockito.verify(repository, Mockito.only()).existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf());
    }

    @Test
    @DisplayName("Deve não realizar validação de cliente quando fluxo for de atualização")
    void naoValidaClienteTest() {
        final var cliente = ClienteBuilder.clienteSalvoBuild();
        Mockito.when(repository.existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf())).thenReturn(false);

        validator.valida(cliente);

        Mockito.verify(repository, Mockito.never()).existsByEmailOrCpf(cliente.getEmail(), cliente.getCpf());
    }
}