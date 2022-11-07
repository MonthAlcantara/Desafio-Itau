package io.github.monthalcantara.cadastroclientesapi.mapper;

import io.github.monthalcantara.cadastroclientesapi.dto.request.EnderecoRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.EnderecoResponse;
import io.github.monthalcantara.cadastroclientesapi.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);
    Endereco toEntity(EnderecoRequest request);
    EnderecoResponse toResponse(Endereco endereco);
}
