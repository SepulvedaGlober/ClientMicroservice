package grandmasfood.client.domain.usecase;

import grandmasfood.client.domain.exceptions.*;
import grandmasfood.client.domain.models.Client;
import grandmasfood.client.domain.spi.IClientPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static grandmasfood.client.utils.ErrorMessages.CLIENT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientUseCaseTest {
    @Mock
    private IClientPersistencePort clientPersistencePort;

    @InjectMocks
    private ClientUseCase clientUseCase;

    private Client validClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validClient = new Client();
        validClient.setDocument("CC-12356");
        validClient.setEmail("test@example.com");
        validClient.setPhone("3001234567");
        validClient.setFullName("John Doe");
        validClient.setDeliveryAddress("Address");
    }

    @Test
    void createClient_Success() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        clientUseCase.createClient(validClient);

        verify(clientPersistencePort, times(1)).createClient(validClient);
    }

    @Test
    void createClient_AlreadyExists_ThrowsException() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.of(validClient));

        assertThrows(ClientAlreadyExistsException.class, () ->
                clientUseCase.createClient(validClient)
        );

        verify(clientPersistencePort, never()).createClient(any(Client.class));
    }

    @Test
    void createClient_InvalidEmail_ThrowsException() {
        validClient.setEmail("invalid_email");

        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidEmailException.class, () ->
                clientUseCase.createClient(validClient)
        );
    }

    @Test
    void createClient_InvalidPhone_ThrowsException() {
        validClient.setPhone("abc123");
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidPhoneException.class, () ->
                clientUseCase.createClient(validClient)
        );
    }

    @Test
    void createClient_InvalidDocument_ThrowsException() {
        validClient.setDocument("doc_invalido");
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidDocumentException.class, () ->
                clientUseCase.createClient(validClient)
        );
    }


    @Test
    void updateClient_Success() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.of(validClient));

        clientUseCase.updateClient(validClient.getDocument(), validClient);

        verify(clientPersistencePort, times(1)).updateClient(validClient);
    }

    @Test
    void updateClient_ClientNotFound_ThrowsException() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        String document = validClient.getDocument();
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () ->
                clientUseCase.getClientByDocument(document)
        );

        assertEquals(CLIENT_NOT_FOUND, exception.getMessage());
        verify(clientPersistencePort, never()).updateClient(any(Client.class));
    }

    @Test
    void updateClient_InvalidEmail_ThrowsException() {
        validClient.setEmail("invalid_email");
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.of(validClient));

        String clientDoc = validClient.getDocument();
        assertThrows(InvalidEmailException.class, () ->
                clientUseCase.updateClient(clientDoc, validClient)
        );
    }


    @Test
    void deleteClient_Success() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.of(validClient));

        clientUseCase.deleteClient(validClient.getDocument());

        verify(clientPersistencePort, times(1)).deleteClient(validClient.getDocument());
    }

    @Test
    void deleteClient_ClientNotFound_ThrowsException() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        String document = validClient.getDocument();
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () ->
                clientUseCase.getClientByDocument(document)
        );

        assertEquals(CLIENT_NOT_FOUND, exception.getMessage());
        verify(clientPersistencePort, never()).deleteClient(anyString());
    }


    @Test
    void getAllClients_Success() {
        when(clientPersistencePort.getAllClients())
                .thenReturn(List.of(validClient, validClient));

        List<Client> result = clientUseCase.getAllClients();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void getAllClients_Empty_ThrowsException() {
        when(clientPersistencePort.getAllClients()).thenReturn(Collections.emptyList());

        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () ->
                clientUseCase.getAllClients()
        );

        assertEquals(CLIENT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getClientByDocument_Success() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.of(validClient));

        Optional<Client> result = clientUseCase.getClientByDocument(validClient.getDocument());
        assertTrue(result.isPresent());
        assertEquals(validClient.getDocument(), result.get().getDocument());
    }

    @Test
    void getClientByDocument_NotFound_ThrowsException() {
        when(clientPersistencePort.getClientByDocument(validClient.getDocument()))
                .thenReturn(Optional.empty());

        String document = validClient.getDocument();
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () ->
                clientUseCase.getClientByDocument(document)
        );

        assertEquals(CLIENT_NOT_FOUND, exception.getMessage());
    }
}