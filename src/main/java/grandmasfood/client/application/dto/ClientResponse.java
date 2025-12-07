package grandmasfood.client.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {
    private String document;
    private String fullName;
    private String email;
    private String phone;
    private String deliveryAddress;
}
