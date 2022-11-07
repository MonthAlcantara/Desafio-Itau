package io.github.monthalcantara.cadastroclientesapi.validation;

import io.github.monthalcantara.cadastroclientesapi.validation.annotations.MaiorIdade;
import io.github.monthalcantara.cadastroclientesapi.validation.validators.MaiorIdadeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.time.LocalDate;
@DisplayName("Teste Validador de maior idade de clientes")
class ValidacaoMaiorIdadeTest {

    @ParameterizedTest
    @CsvSource({"18,true", "17,false", "7,false", "35,true", "40,true", "19, true", "16,false"})
    @DisplayName("Deve validar a data de Nascimento")
    void violacaoMaiorIdadeTest(int idadeTest, boolean resultadoEsperado) {
        final var idade = Mockito.mock(MaiorIdade.class);
        final var idadeValidator = new MaiorIdadeValidator();
        final var dezoitoAnos = LocalDate.now().minusYears(idadeTest);
        idadeValidator.initialize(idade);

        Mockito.when(idade.maiorQue()).thenReturn(18);

        final var resultado = idadeValidator.isValid(dezoitoAnos, null);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }
}

