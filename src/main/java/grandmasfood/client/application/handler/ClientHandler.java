package grandmasfood.client.application.handler;

import grandmasfood.client.application.dto.ClientRequest;
import grandmasfood.client.application.dto.ClientResponse;
import grandmasfood.client.application.dto.ClientUpdateRequest;
import grandmasfood.client.application.mapper.ClientRequestMapper;
import grandmasfood.client.application.mapper.ClientResponseMapper;
import grandmasfood.client.domain.api.IClientServicePort;
import grandmasfood.client.domain.exceptions.InvalidDocumentException;
import grandmasfood.client.domain.models.Client;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientHandler implements IClientHandler{

    private final IClientServicePort clientServicePort;
    private final ClientRequestMapper clientRequestMapper;
    private final ClientResponseMapper clientResponseMapper;


    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = clientRequestMapper.toClient(clientRequest);
        clientServicePort.createClient(client);
        return clientResponseMapper.toResponse(client);
    }

    @Override
    public void updateClient(String document, ClientUpdateRequest clientUpdateRequest) {
        Client client = clientRequestMapper.toClient(clientUpdateRequest);
        client.setDocument(document);
        clientServicePort.updateClient(document, client);
    }

    @Override
    public void deleteClient(String document) {
        clientServicePort.deleteClient(document);
    }

    @Override
    public Optional<ClientResponse> getClientByDocument(String document) {
        return clientServicePort.getClientByDocument(document)
                .map(clientResponseMapper::toResponse);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientServicePort.getAllClients()
                .stream()
                .map(clientResponseMapper::toResponse)
                .toList();
    }
}
