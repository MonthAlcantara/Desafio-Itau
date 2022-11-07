package io.github.monthalcantara.cadastroclientesapi.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements StoragePublisher {
    @Override
    public String send(MultipartFile file) {
        return "https://mock.aws.s3/arquivo_cliente_1.jpg";
    }
}
