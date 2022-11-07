package io.github.monthalcantara.cadastroclientesapi.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static io.github.monthalcantara.cadastroclientesapi.commons.ConstantsUtils.URl_DEFAULt_S3;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste envio de arquivo para Bucket S3")
class FileServiceTest {
    private FileService service;

    @BeforeEach
    void init() {
        service = new FileService();
    }

    @Test
    @DisplayName("Deve enviar arquivo para bucket S3")
    void enviaArtquivoTest() {
        final var file = Mockito.mock(MultipartFile.class);

        final var result = service.send(file);

        Assertions.assertEquals(URl_DEFAULt_S3, result);
    }
}