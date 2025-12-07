package grandmasfood.client.infrastructure.rest;

import grandmasfood.client.application.dto.ClientRequest;
import grandmasfood.client.application.dto.ClientResponse;
import grandmasfood.client.application.dto.ClientUpdateRequest;
import grandmasfood.client.application.handler.IClientHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientRestControllerTest {

    @Mock
    private IClientHandler clientHandler;

    @InjectMocks
    private ClientRestController clientRestController;

    private ClientRequest validClientRequest;
    private ClientResponse validClientResponse;

    @BeforeEach
    void setUp() {
        validClientRequest = new ClientRequest();
        validClientRequest.setDocument("CC-123456");
        validClientRequest.setEmail("test@example.com");
        validClientRequest.setPhone("3001234567");
        validClientRequest.setFullName("John Doe");
        validClientRequest.setDeliveryAddress("Some address");

        validClientResponse = new ClientResponse();
        validClientResponse.setDocument("12345678");
        validClientResponse.setEmail("test@example.com");
        validClientResponse.setPhone("3001234567");
        validClientResponse.setFullName("John Doe");
        validClientResponse.setDeliveryAddress("Some address");
    }


    @Test
    void createClient_Success() {
        when(clientHandler.createClient(any(ClientRequest.class))).thenReturn(validClientResponse);

        ResponseEntity<ClientResponse> response = clientRestController.createClient(validClientRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(validClientResponse.getDocument(), response.getBody().getDocument());

        verify(clientHandler, times(1)).createClient(validClientRequest);
    }

    @Test
    void createClient_IllegalArgumentException_ReturnsBadRequest() {
        when(clientHandler.createClient(any(ClientRequest.class))).thenThrow(new IllegalArgumentException("Invalid data"));

        ResponseEntity<ClientResponse> response = clientRestController.createClient(validClientRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(clientHandler, times(1)).createClient(validClientRequest);
    }


    @Test
    void getClientByDocument_Found() {
        when(clientHandler.getClientByDocument("12345678")).thenReturn(Optional.of(validClientResponse));

        ResponseEntity<ClientResponse> response = clientRestController.getClientByDocument("12345678");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("12345678", response.getBody().getDocument());

        verify(clientHandler, times(1)).getClientByDocument("12345678");
    }

    @Test
    void getClientByDocument_NotFound() {
        when(clientHandler.getClientByDocument("99999999")).thenReturn(Optional.empty());

        ResponseEntity<ClientResponse> response = clientRestController.getClientByDocument("99999999");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(clientHandler, times(1)).getClientByDocument("99999999");
    }


    @Test
    void updateClient_Success() {
        // Dado que no hay excepción
        doNothing().when(clientHandler).updateClient(anyString(), any(ClientUpdateRequest.class));

        ClientUpdateRequest updateRequest = new ClientUpdateRequest();
        updateRequest.setFullName("New Name");
        updateRequest.setEmail("new_email@example.com");
        updateRequest.setPhone("3111111111");
        updateRequest.setDeliveryAddress("New Address");

        ResponseEntity<ClientResponse> response = clientRestController.updateClient("12345678", updateRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(clientHandler, times(1)).updateClient("12345678", updateRequest);
    }

    @Test
    void updateClient_IllegalArgumentException_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("Invalid data")).when(clientHandler).updateClient(anyString(), any(ClientUpdateRequest.class));

        ClientUpdateRequest updateRequest = new ClientUpdateRequest();
        updateRequest.setFullName("New Name");

        ResponseEntity<ClientResponse> response = clientRestController.updateClient("12345678", updateRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(clientHandler, times(1)).updateClient("12345678", updateRequest);
    }

    @Test
    void deleteClient_Success() {
        doNothing().when(clientHandler).deleteClient("12345678");

        ResponseEntity<Void> response = clientRestController.deleteClient("12345678");

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(clientHandler, times(1)).deleteClient("12345678");
    }


    @Test
    void getAllClients_Found() {
        ClientResponse anotherClient = new ClientResponse();
        anotherClient.setDocument("87654321");

        when(clientHandler.getAllClients()).thenReturn(List.of(validClientResponse, anotherClient));

        ResponseEntity<List<ClientResponse>> response = clientRestController.getAllClients();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        verify(clientHandler, times(1)).getAllClients();
    }

    @Test
    void getAllClients_Empty() {
        when(clientHandler.getAllClients()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ClientResponse>> response = clientRestController.getAllClients();

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(clientHandler, times(1)).getAllClients();
    }
}