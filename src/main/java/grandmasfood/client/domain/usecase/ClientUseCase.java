package grandmasfood.client.domain.usecase;

import grandmasfood.client.domain.api.IClientServicePort;
import grandmasfood.client.domain.exceptions.*;
import grandmasfood.client.domain.models.Client;
import grandmasfood.client.domain.spi.IClientPersistencePort;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static grandmasfood.client.utils.Constants.*;
import static grandmasfood.client.utils.ErrorMessages.CLIENT_NOT_FOUND;

public class ClientUseCase implements IClientServicePort {


    private final IClientPersistencePort clientPersistencePort;

    public ClientUseCase(IClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public void createClient(Client client) {
        validateClient(client);
        Optional<Client> existingClient = clientPersistencePort.getClientByDocument(client.getDocument());
        if (existingClient.isPresent()) {
            throw new ClientAlreadyExistsException("Client already exists");
        }
        clientPersistencePort.createClient(client);

    }

    @Override
    public void updateClient(String document, Client client) {
        Client existingClient = clientPersistencePort.getClientByDocument(client.getDocument())
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));

        validateClient(client);

        existingClient.setFullName(client.getFullName());
        existingClient.setEmail(client.getEmail());
        existingClient.setPhone(client.getPhone());
        existingClient.setDeliveryAddress(client.getDeliveryAddress());
        clientPersistencePort.updateClient(client);

    }

    @Override
    public void deleteClient(String document) {
        Client client = clientPersistencePort.getClientByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
        clientPersistencePort.deleteClient(client.getDocument());

    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientPersistencePort.getAllClients();
        if(clients.isEmpty()){
            throw new ClientNotFoundException(CLIENT_NOT_FOUND);
        }
        return clients;
    }

    @Override
    public Optional<Client> getClientByDocument(String document) {
        return Optional.ofNullable(clientPersistencePort.getClientByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND)));
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches() && phone.length() <= MAX_PHONE_LENGTH;
    }
    private boolean validateDocument(String document) {
        Pattern pattern = Pattern.compile(DOCUMENT_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(document);
        return matcher.matches() && document.length() <= MAX_DOCUMENT_LENGTH;
    }
    private void validateClient(Client client) {
        if (!validateEmail(client.getEmail()) || client.getEmail().length() > MAX_EMAIL_LENGTH) {
            throw new InvalidEmailException("Invalid email");
        }
        if (!validatePhone(client.getPhone())) {
            throw new InvalidPhoneException("Invalid phone");
        }
        if (!validateDocument(client.getDocument())) {
            throw new InvalidDocumentException("Invalid document");
        }
        if (client.getFullName().length() > MAX_FULL_NAME_LENGTH) {
            throw new InvalidNameException("Invalid full name");
        }
        if (client.getDeliveryAddress().length() > MAX_ADDRESS_LENGTH) {
            throw new InvalidAddressException("Invalid deliveryAddress");
        }
    }
}
