package grandmasfood.client.application.handler;

import grandmasfood.client.application.dto.ClientRequest;
import grandmasfood.client.application.dto.ClientResponse;
import grandmasfood.client.application.dto.ClientUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface IClientHandler {
    ClientResponse createClient(ClientRequest clientRequest);
    void updateClient(String document, ClientUpdateRequest clientUpdateRequest);
    void deleteClient(String document);
    Optional<ClientResponse> getClientByDocument(String document);
    List<ClientResponse> getAllClients();
}
