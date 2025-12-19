package grandmasfood.client.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String document;
    private String fullName;
    private String email;
    private String phone;
    private String deliveryAddress;
}
