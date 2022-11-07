package io.github.monthalcantara.cadastroclientesapi.repositories;

import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);

    boolean existsByEmailOrCpf(String email, String documento);
}
