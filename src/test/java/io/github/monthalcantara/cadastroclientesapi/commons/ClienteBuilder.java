package io.github.monthalcantara.cadastroclientesapi.commons;

import io.github.monthalcantara.cadastroclientesapi.dto.request.ClienteRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;

import java.time.LocalDate;

public class ClienteBuilder {
    public static ClienteRequest clienteRequestBuild() {
        return new ClienteRequest("Erick", "Cartman", "13112939069", "erick@email.com", LocalDate.of(1990, 10, 24));
    }

    public static Cliente clienteSalvoBuild() {
        return new Cliente(1L, "Erick", "Cartman", "13112939069", "erick@email.com", LocalDate.of(1990, 10, 24));
    }

    public static Cliente clienteNaoSalvoBuild() {
        return new Cliente(null, "Erick", "Cartman", "13112939069", "erick@email.com", LocalDate.of(1990, 10, 24));
    }

    public static Cliente clienteAtualizadoBuild() {
        return new Cliente(1L, "Erick", "Cartman", "13112939069", "url_arrquivo_storage", "erick@email.com", LocalDate.of(1990, 10, 24), EnderecoBuilder.enderecoBuild());
    }

    public static ClienteResponse clienteResponse() {
        return new ClienteResponse(1L, "Erick", "Cartman", "13112939069", "erick@email.com", LocalDate.of(1990, 10, 24), EnderecoBuilder.enderecoResponse(), "url_arrquivo_storage");
    }
}
