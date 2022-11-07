package io.github.monthalcantara.cadastroclientesapi.mapper;

import io.github.monthalcantara.cadastroclientesapi.dto.request.ClienteRequest;
import io.github.monthalcantara.cadastroclientesapi.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastroclientesapi.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    Cliente toEntity(ClienteRequest request);

    ClienteResponse toResponse(Cliente person);
}
