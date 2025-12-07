package grandmasfood.client.application.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientUpdateRequest {
    private String fullName;
    private String email;
    private String phone;
    private String deliveryAddress;
}
