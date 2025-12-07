package grandmasfood.client.infrastructure.jpa.mapper;


import grandmasfood.client.domain.models.Client;
import grandmasfood.client.infrastructure.jpa.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {


    ClientEntity toEntity(Client client);

    Client toClient(ClientEntity clientEntity);
    List<Client> toClientList(List<ClientEntity> clientEntities);

}
