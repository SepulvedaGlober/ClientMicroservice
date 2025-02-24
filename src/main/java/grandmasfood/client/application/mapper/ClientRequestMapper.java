package grandmasfood.client.application.mapper;


import grandmasfood.client.application.dto.ClientRequest;
import grandmasfood.client.application.dto.ClientUpdateRequest;
import grandmasfood.client.domain.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {

    @Mapping(target = "document", source = "document")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    Client toClient(ClientRequest clientRequest);

    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    Client toClient(ClientUpdateRequest clientUpdateRequest);
}
