package grandmasfood.client.application.mapper;


import grandmasfood.client.application.dto.ClientRequest;
import grandmasfood.client.application.dto.ClientUpdateRequest;
import grandmasfood.client.domain.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {


    Client toClient(ClientRequest clientRequest);


    Client toClient(ClientUpdateRequest clientUpdateRequest);
}
