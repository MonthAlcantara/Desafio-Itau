package io.github.monthalcantara.cadastroclientesapi.repositories;

import io.github.monthalcantara.cadastroclientesapi.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
