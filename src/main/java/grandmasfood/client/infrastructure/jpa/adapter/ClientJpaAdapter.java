package grandmasfood.client.infrastructure.jpa.adapter;


import grandmasfood.client.domain.exceptions.ClientNotFoundException;
import grandmasfood.client.domain.models.Client;
import grandmasfood.client.domain.spi.IClientPersistencePort;
import grandmasfood.client.infrastructure.exceptions.DuplicateDocumentException;
import grandmasfood.client.infrastructure.jpa.entity.ClientEntity;
import grandmasfood.client.infrastructure.jpa.mapper.ClientEntityMapper;
import grandmasfood.client.infrastructure.jpa.repository.IClientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static grandmasfood.client.utils.ErrorMessages.CLIENT_NOT_FOUND;
import static grandmasfood.client.utils.ErrorMessages.DUPLICATE_DOCUMENT;

@RequiredArgsConstructor
public class ClientJpaAdapter implements IClientPersistencePort {

    private final IClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;


    @Override
    public void createClient(Client client) {
        if(clientRepository.findByDocument(client.getDocument()).isPresent()){
            throw new DuplicateDocumentException(DUPLICATE_DOCUMENT);
        }
        ClientEntity clientEntity = clientEntityMapper.toEntity(client);
        clientRepository.save(clientEntity);

    }

    @Override
    public void updateClient(Client client) {
        ClientEntity existingClient = clientRepository.findByDocument(client.getDocument())
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));

        existingClient.setFullName(client.getFullName());
        existingClient.setEmail(client.getEmail());
        existingClient.setPhone(client.getPhone());
        existingClient.setDeliveryAddress(client.getDeliveryAddress());
        clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(String document) {
        ClientEntity existingClient = clientRepository.findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
        clientRepository.delete(existingClient);
    }

    @Override
    public List<Client> getAllClients() {
        List<ClientEntity> clientEntities = clientRepository.findAll();
        if(clientEntities.isEmpty()){
            throw new ClientNotFoundException(CLIENT_NOT_FOUND);
        }
        return clientEntityMapper.toClientList(clientEntities);
    }

    @Override
    public Optional<Client> getClientByDocument(String document) {
        return clientRepository.findByDocument(document)
                .map(clientEntityMapper::toClient);
    }
}
