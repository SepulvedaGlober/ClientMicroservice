package grandmasfood.client.infrastructure.jpa.mapper;


import grandmasfood.client.domain.models.Client;
import grandmasfood.client.infrastructure.jpa.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {

    @Mapping(target = "document", source = "document")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    ClientEntity toEntity(Client client);
    @Mapping(target = "document", source = "document")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    Client toClient(ClientEntity clientEntity);
    List<Client> toClientList(List<ClientEntity> clientEntities);

}
