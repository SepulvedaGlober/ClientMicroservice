package grandmasfood.client.application.mapper;


import grandmasfood.client.application.dto.ClientResponse;
import grandmasfood.client.domain.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper {

    @Mapping(target = "document", source = "document")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    ClientResponse toResponse(Client client);

    default List<ClientResponse> toResponseList(List<Client> clients) {
        return clients.stream().map(this::toResponse).toList();
    }
}
