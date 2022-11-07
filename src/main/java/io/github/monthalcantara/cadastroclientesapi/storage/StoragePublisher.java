package io.github.monthalcantara.cadastroclientesapi.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StoragePublisher {

    String send(MultipartFile file);
}
